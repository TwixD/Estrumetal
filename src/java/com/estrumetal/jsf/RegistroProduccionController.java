package com.estrumetal.jsf;

import com.estrumetal.jpa.DetRegProduccion;
import com.estrumetal.jpa.OrdenProduccion;
import com.estrumetal.jpa.Pieza;
import com.estrumetal.jpa.Plano;
import com.estrumetal.jpa.RegistroProduccion;
import com.estrumetal.jpa.Ruta;
import com.estrumetal.jpacontroller.DetRegProduccionFacade;
import com.estrumetal.jpacontroller.MaquinaFacade;
import com.estrumetal.jpacontroller.OrdenProduccionFacade;
import com.estrumetal.jsf.util.JsfUtil;
import com.estrumetal.jsf.util.PaginationHelper;
import com.estrumetal.jpacontroller.RegistroProduccionFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@ManagedBean(name = "registroProduccionController")
@SessionScoped
public class RegistroProduccionController implements Serializable {

    private RegistroProduccion current;
    private OrdenProduccionFacade facade;
    private DetRegProduccion detRegProduccion;
    private MaquinaFacade maquinaFacacade;
    private DataModel items = null;
    @EJB
    private com.estrumetal.jpacontroller.RegistroProduccionFacade ejbFacade;
    @EJB
    private com.estrumetal.jpacontroller.DetRegProduccionFacade detRegProduccionFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Date fechaInicial;
    private Date fechaFinal;
    private int idMaquina;
    private List<Pieza> listaPieza;
    private List<Pieza> selectedPiezas;

    List<String> listMaquinaRango1 = new ArrayList<String>();
    List<String> listMaquinaRango2 = new ArrayList<String>();
    List<String> listMaquinaRango3 = new ArrayList<String>();
    List<String> listMaquinaRango4 = new ArrayList<String>();

    public RegistroProduccionController() {
        facade = new OrdenProduccionFacade();
        detRegProduccion = new DetRegProduccion();
    }

    public RegistroProduccion getSelected() {
        if (current == null) {
            current = new RegistroProduccion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private RegistroProduccionFacade getFacade() {
        return ejbFacade;
    }

    public DetRegProduccionFacade getDetRegProduccionFacade() {
        return detRegProduccionFacade;
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

    public String prepareView() {
        if (getItems().getRowIndex() == -1) {
            return "List";
        } else {
            current = (RegistroProduccion) getItems().getRowData();
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        }
        return "View";
    }

    public String prepareCreate() {
        current = new RegistroProduccion();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        if (selectedPiezas.size() <= 0) {
            JsfUtil.addErrorMessage("Debe seleccionar por lo menos una pieza para producción.");
            JsfUtil.addErrorMessage("Si no encuentra Piezas, debe crearlas para el Plano.");
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(current.getFechaInicio());
        String format2 = dateFormat.format(current.getFechaTerminacion());
        Date fechaini = null, fechafini = null;
        try {
            fechaini = dateFormat.parse(format);
            fechafini = dateFormat.parse(format2);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroProduccionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Date> datesOPUsuarioInicio = facade.datesOPUsuarioInicio(current.getUSUARIOidusuario().getIdUsuario());
        List<Date> datesOPUsuarioTerminacion = facade.datesOPUsuarioTerminacion(current.getUSUARIOidusuario().getIdUsuario());
        for (int i = 0; i < datesOPUsuarioInicio.size(); i++) {
            if (fechaini.after(datesOPUsuarioInicio.get(i)) && fechafini.before(datesOPUsuarioTerminacion.get(i)) || fechaini.equals(datesOPUsuarioInicio.get(i)) || fechafini.equals(datesOPUsuarioTerminacion.get(i))) {
                JsfUtil.addErrorMessage("El operario se encuentra en una producción entre estas fechas.");
                JsfUtil.addErrorMessage("Seleccione 'Atras' para ver el cronograma del operario.");
                return null;
            }
        }

        String formatR = dateFormat.format(current.getRUTAidruta().getFechaProduccion());
        String formatR2 = dateFormat.format(current.getRUTAidruta().getFechaTerminacion());
        Date fechainiR = null, fechafiniR = null;
        try {
            fechainiR = dateFormat.parse(formatR);
            fechafiniR = dateFormat.parse(formatR2);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroProduccionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (current.getFechaInicio().before(current.getRUTAidruta().getFechaProduccion()) || current.getFechaTerminacion().after(current.getRUTAidruta().getFechaTerminacion())) {
            JsfUtil.addErrorMessage("La fecha de producción no se encuentra dentro del rango de la Ruta.");
            JsfUtil.addErrorMessage("Seleccione 'Ruta' para ver el rango de producción valido.");
            return null;
        }
        try {
            current.setTotalProduccion(0);
            current.setEstado("A");
            maquinaFacacade = new MaquinaFacade();
            String idMazi = maquinaFacacade.getMaxId("id_registroproduccion", "REGISTRO_PRODUCCION");
            int x = Integer.parseInt(idMazi);
            x++;
            getFacade().create(current);
            current.setIdRegistroproduccion(x);
            detRegProduccion.setRPidregistroproduccion(current);
            for (int i = 0; i < selectedPiezas.size(); i++) {
                detRegProduccion.setCantidad(selectedPiezas.get(i).getCantidad());
                detRegProduccion.setCodPieza(selectedPiezas.get(i).getIdPieza());
                getDetRegProduccionFacade().create(detRegProduccion);
            }

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroProduccionCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        if (getItems().getRowIndex() == -1) {
            return "List";
        } else {
            current = (RegistroProduccion) getItems().getRowData();
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        }
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroProduccionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (RegistroProduccion) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroProduccionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public String getOP(Ruta idRuta, String code) {
        try {
            if (code.equalsIgnoreCase("idOrdenproduccion")) {
                return facade.getOrdenProduccionById(idRuta).getIdOrdenproduccion() + "";
            }
            if (code.equalsIgnoreCase("fecha")) {
                return facade.getOrdenProduccionById(idRuta).getFecha() + "";
            }
            if (code.equalsIgnoreCase("cantidad")) {
                return facade.getOrdenProduccionById(idRuta).getCantidad() + "";
            }
            if (code.equalsIgnoreCase("idPlano")) {
                return facade.getOrdenProduccionById(idRuta).getPLANOidplano().getIdPlano() + "";
            }
            return "0";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("La RUTA no tiene una Orden de producción asociada.");
            return null;
        }

    }

    public Plano getPlanoSelected(Ruta idRuta) {
        return facade.getOrdenProduccionById(idRuta).getPLANOidplano();
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
        //return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
        List<RegistroProduccion> findAll = ejbFacade.findAll();
        System.out.println(findAll.size());
        for (int i = 0; i < findAll.size(); i++) {
            System.out.println();
            if (findAll.get(i).getEstado() == null || findAll.get(i).getEstado().equalsIgnoreCase("I")) {
                System.out.println(findAll.get(i).getEstado() + " REMOVE CODE:" + findAll.get(i).getIdRegistroproduccion());
                findAll.remove(i);
            }
//            if (!findAll.get(i).getEstado().equalsIgnoreCase("A")) {
//               
//                System.out.println(findAll.get(i).getEstado());
//                
//                //
//            }
        }
        return JsfUtil.getSelectItems(findAll, true);

    }

    @FacesConverter(forClass = RegistroProduccion.class)
    public static class RegistroProduccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RegistroProduccionController controller = (RegistroProduccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "registroProduccionController");
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
            if (object instanceof RegistroProduccion) {
                RegistroProduccion o = (RegistroProduccion) object;
                return getStringKey(o.getIdRegistroproduccion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + RegistroProduccion.class.getName());
            }
        }

    }

    public List<String> getListMaquinaRango1() {
        return listMaquinaRango1;
    }

    public void setListMaquinaRango1(List<String> listMaquinaRango1) {
        this.listMaquinaRango1 = listMaquinaRango1;
    }

    public List<String> getListMaquinaRango2() {
        return listMaquinaRango2;
    }

    public void setListMaquinaRango2(List<String> listMaquinaRango2) {
        this.listMaquinaRango2 = listMaquinaRango2;
    }

    public List<String> getListMaquinaRango3() {
        return listMaquinaRango3;
    }

    public void setListMaquinaRango3(List<String> listMaquinaRango3) {
        this.listMaquinaRango3 = listMaquinaRango3;
    }

    public List<String> getListMaquinaRango4() {
        return listMaquinaRango4;
    }

    public void setListMaquinaRango4(List<String> listMaquinaRango4) {
        this.listMaquinaRango4 = listMaquinaRango4;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public List<Pieza> getListaPieza(Plano idPlano) {
        String cods = "";
        List<DetRegProduccion> detRegProduccion1 = facade.getDetRegProduccion();
        if (!detRegProduccion1.isEmpty()) {
            for (int i = 0; i < detRegProduccion1.size(); i++) {
                cods += detRegProduccion1.get(i).getCodPieza();
                if (i != detRegProduccion1.size() - 1) {
                    cods += ",";
                }
            }
        }
        return facade.getPiezasById(idPlano, cods);
    }

    public void setListaPieza(List<Pieza> listaPieza) {
        this.listaPieza = listaPieza;
    }

    public List<Pieza> getSelectedPiezas() {
        return selectedPiezas;
    }

    public void setSelectedPiezas(List<Pieza> selectedPiezas) {
        this.selectedPiezas = selectedPiezas;
    }

    public void doPopulateMaquinaRango() {
        listMaquinaRango2.clear();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicialIn = df.format(fechaInicial);
        String fechaFinalIn = df.format(fechaFinal);
        List<Object> list = getFacade().listMaquinaProduccion(fechaInicialIn, fechaFinalIn);
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            listMaquinaRango2.add(Arrays.toString(row).replace("[", "").replace("]", ""));
        }
    }

    public void doPopulateOperarioRango() {
        listMaquinaRango3.clear();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicialIn = df.format(fechaInicial);
        String fechaFinalIn = df.format(fechaFinal);
        List<Object> list = getFacade().listOperarioProduccion(fechaInicialIn, fechaFinalIn);
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            listMaquinaRango3.add(Arrays.toString(row).replace("[", "").replace("]", ""));
        }
    }

    public void doPopulateMaquina() {
        listMaquinaRango4.clear();
        List<Object> list = getFacade().listReporteProduccion(idMaquina);
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            listMaquinaRango4.add(Arrays.toString(row).replace("[", "").replace("]", ""));
        }
    }

    public void doPopulatePendiente() {
        listMaquinaRango1.clear();
        List<Object> list = getFacade().listPendienteProduccion(idMaquina);
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            listMaquinaRango1.add(Arrays.toString(row).replace("[", "").replace("]", ""));
        }
    }

}
