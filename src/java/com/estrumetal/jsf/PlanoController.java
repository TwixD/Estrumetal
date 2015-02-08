package com.estrumetal.jsf;

import com.estrumetal.jpa.Pieza;
import com.estrumetal.jpa.Plano;
import com.estrumetal.jpa.Usuario;
import com.estrumetal.jpacontroller.PiezaFacade;
import com.estrumetal.jsf.util.JsfUtil;
import com.estrumetal.jsf.util.PaginationHelper;
import com.estrumetal.jpacontroller.PlanoFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

@ManagedBean(name = "planoController")
@SessionScoped
public class PlanoController implements Serializable {

    private Plano current;
    private Pieza pieza = new Pieza();
    private Pieza pieza1 = new Pieza();
    private Pieza pieza2 = new Pieza();
    private Pieza pieza3 = new Pieza();
    private Pieza pieza4 = new Pieza();
    private Pieza pieza5 = new Pieza();
    private Pieza pieza6 = new Pieza();
    private Pieza pieza7 = new Pieza();
    private Pieza pieza8 = new Pieza();
    private Pieza pieza9 = new Pieza();
    private String nombreOperario;
    private String obra;
    private String idPlano;
    private String seccion;
    private Usuario usuario;
    private Plano plano;

    private DataModel items = null;
    @EJB
    private com.estrumetal.jpacontroller.PlanoFacade ejbFacade;
    @EJB
    private com.estrumetal.jpacontroller.PiezaFacade ejbFacade2;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PlanoController() {
    }

    public Plano getSelected() {
        if (current == null) {
            current = new Plano();
            selectedItemIndex = -1;
        }
        pieza.setPLANOidplano(current);
        pieza1.setPLANOidplano(current);
        pieza2.setPLANOidplano(current);
        pieza3.setPLANOidplano(current);
        pieza4.setPLANOidplano(current);
        pieza5.setPLANOidplano(current);
        pieza6.setPLANOidplano(current);
        pieza7.setPLANOidplano(current);
        pieza8.setPLANOidplano(current);
        pieza9.setPLANOidplano(current);
        return current;
    }

    private PlanoFacade getFacade() {
        return ejbFacade;
    }

    private PiezaFacade getFacade2() {
        return ejbFacade2;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(999999) {

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

    public String prepareListHistorial() {
        recreateModel();
        return "HistorialPrestamo";
    }

    public String prepareView() {
        current = (Plano) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Plano();
        selectedItemIndex = -1;
        return "Create";
    }

    public String prepareCreatePlanoPieza() {
        pieza = new Pieza();
        pieza1 = new Pieza();
        pieza2 = new Pieza();
        pieza3 = new Pieza();
        pieza4 = new Pieza();
        pieza5 = new Pieza();
        pieza6 = new Pieza();
        pieza7 = new Pieza();
        pieza8 = new Pieza();
        pieza9 = new Pieza();
        current = new Plano();
        selectedItemIndex = -1;
        return "CreatePlanoPieza";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PlanoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createPlanoPieza() {
        try {
            getFacade().create(current);
            if (pieza.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza);
            }
            if (pieza1.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza1);
            }
            if (pieza2.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza2);
            }
            if (pieza3.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza3);
            }
            if (pieza4.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza4);
            }
            if (pieza5.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza5);
            }
            if (pieza6.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza6);
            }
            if (pieza7.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza7);
            }
            if (pieza8.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza8);
            }
            if (pieza9.getMATERIAPRIMAidmateriaprima() != null) {
                getFacade2().create(pieza9);
            }
            JsfUtil.addSuccessMessage("Plano creado con multiples piezas.");
            return prepareCreatePlanoPieza();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("El código de plano se encuentra ya registrado.");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Plano) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PlanoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String asignar() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        if (!getFacade().asignar(Integer.parseInt(idPlano), "ASIGNADO[" + nombreOperario + " - Fecha:[" + format2.format(date) + "] - Hora:[" + format.format(date) + "]- Seccion : " + seccion + " - OBRA : [" + obra + "]]").equals(null)) {
            JsfUtil.addSuccessMessage("Plano asignado exitosamente");
            return "AsignarPlano";
        } else {
            JsfUtil.addErrorMessage("El plano no se puedo asignar");
            return null;
        }
    }

    public String prestamo() {
        if (getFacade().planoDisponible(plano.getIdPlano())) {
            getFacade().prestamo(plano.getIdPlano(), usuario.getIdUsuario() + " - " + usuario.getNombre() + " - " + usuario.getDocumento());
            JsfUtil.addSuccessMessage("El plano se presto a : " + usuario.getNombre() + ", con documento :" + usuario.getDocumento());
            return "PrestamoPlano";
        } else {
            JsfUtil.addErrorMessage("El plano se encuentra actualmente prestado.");
            return "PrestamoPlano";
        }
    }

    public String recibir() {
        if (!getFacade().planoDisponible(plano.getIdPlano())) {
            getFacade().recibir(plano.getIdPlano());
            JsfUtil.addSuccessMessage("El plano se recibio correctamente.");
            return "RecibirPlano";
        } else {
            JsfUtil.addErrorMessage("El plano no se encuentra actualmente prestado.");
            return "RecibirPlano";
        }
    }

    public String recepcion() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        if (!getFacade().asignar(Integer.parseInt(idPlano), "RECIBIDO[" + nombreOperario + " - Fecha:[" + format2.format(date) + "] - Hora:[" + format.format(date) + "] - OBRA : [" + obra + "]]").equals(null)) {
            JsfUtil.addSuccessMessage("Recepción de plano exitoso.");
            return "RecepcionPlano";
        } else {
            JsfUtil.addErrorMessage("Recepción de plano con dificultades.");
            return null;
        }
    }

    public String destroy() {
        current = (Plano) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PlanoDeleted"));
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

    @FacesConverter(forClass = Plano.class)
    public static class PlanoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlanoController controller = (PlanoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "planoController");
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
            if (object instanceof Plano) {
                Plano o = (Plano) object;
                return getStringKey(o.getIdPlano());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Plano.class.getName());
            }
        }

    }

    public Plano getCurrent() {
        return current;
    }

    public void setCurrent(Plano current) {
        this.current = current;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Pieza getPieza1() {
        return pieza1;
    }

    public void setPieza1(Pieza pieza1) {
        this.pieza1 = pieza1;
    }

    public Pieza getPieza2() {
        return pieza2;
    }

    public void setPieza2(Pieza pieza2) {
        this.pieza2 = pieza2;
    }

    public Pieza getPieza3() {
        return pieza3;
    }

    public void setPieza3(Pieza pieza3) {
        this.pieza3 = pieza3;
    }

    public Pieza getPieza4() {
        return pieza4;
    }

    public void setPieza4(Pieza pieza4) {
        this.pieza4 = pieza4;
    }

    public Pieza getPieza5() {
        return pieza5;
    }

    public void setPieza5(Pieza pieza5) {
        this.pieza5 = pieza5;
    }

    public Pieza getPieza6() {
        return pieza6;
    }

    public void setPieza6(Pieza pieza6) {
        this.pieza6 = pieza6;
    }

    public Pieza getPieza7() {
        return pieza7;
    }

    public void setPieza7(Pieza pieza7) {
        this.pieza7 = pieza7;
    }

    public Pieza getPieza8() {
        return pieza8;
    }

    public void setPieza8(Pieza pieza8) {
        this.pieza8 = pieza8;
    }

    public Pieza getPieza9() {
        return pieza9;
    }

    public void setPieza9(Pieza pieza9) {
        this.pieza9 = pieza9;
    }

    public PlanoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PlanoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public PiezaFacade getEjbFacade2() {
        return ejbFacade2;
    }

    public void setEjbFacade2(PiezaFacade ejbFacade2) {
        this.ejbFacade2 = ejbFacade2;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(String idPlano) {
        this.idPlano = idPlano;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}
