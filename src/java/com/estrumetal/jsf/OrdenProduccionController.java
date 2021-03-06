package com.estrumetal.jsf;

import com.estrumetal.jpa.OrdenProduccion;
import com.estrumetal.jsf.util.JsfUtil;
import com.estrumetal.jsf.util.PaginationHelper;
import com.estrumetal.jpacontroller.OrdenProduccionFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "ordenProduccionController")
@SessionScoped
public class OrdenProduccionController implements Serializable {

    private OrdenProduccion current;
    private DataModel items = null;
    @EJB
    private com.estrumetal.jpacontroller.OrdenProduccionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }

    public OrdenProduccionController() {
    }

    public OrdenProduccion getSelected() {
        if (current == null) {
            current = new OrdenProduccion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private OrdenProduccionFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(9999999) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        if (getItems().getRowIndex() == -1) {
            return "List";
        } else {
            current = (OrdenProduccion) getItems().getRowData();
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        }
        return "View";
    }

    public String prepareCreate() {
        current = new OrdenProduccion();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        if (getFacade().validateRuta(current.getRUTAidruta().getIdRuta()) != 0) {
            JsfUtil.addErrorMessage("La Ruta ya se encuentra en producción");
            return null;
        }
        int idPlano = current.getPLANOidplano().getIdPlano();
        int aviableCant = getFacade().getPlanoCantidad(idPlano);
        if (current.getCantidad() <= 0) {
            JsfUtil.addErrorMessage("Cantidad debe ser mayor a '0'.");
            return null;
        } else {
            if (aviableCant <= 0) {
                JsfUtil.addErrorMessage("El plano seleccionado ya no tiene la cantidad solicitada.");
                return null;
            } else {
                if (current.getCantidad() > aviableCant) {
                    JsfUtil.addErrorMessage("La cantidad del Plano solo tiene disponible :" + aviableCant + ", por favor ingrese una cantidad dentro de el rango.");
                    return null;
                } else {
                    try {
                        getFacade().create(current);
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenProduccionCreated"));
                        return prepareCreate();
                    } catch (Exception e) {
                        JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        return null;
                    }
                }
            }
        }
    }

    public String prepareEdit() {
        if (getItems().getRowIndex() == -1) {
            return "List";
        } else {
            current = (OrdenProduccion) getItems().getRowData();
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        }
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenProduccionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (OrdenProduccion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenProduccionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = OrdenProduccion.class)
    public static class OrdenProduccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdenProduccionController controller = (OrdenProduccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordenProduccionController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof OrdenProduccion) {
                OrdenProduccion o = (OrdenProduccion) object;
                return getStringKey(o.getIdOrdenproduccion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OrdenProduccion.class.getName());
            }
        }
    }

}
