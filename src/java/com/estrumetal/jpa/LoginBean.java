/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpa;

import com.estrumetal.jpacontroller.UsuarioFacade;
import com.estrumetal.jsf.GalleriController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private UsuarioFacade usuarioFacade;

    Usuario usuario = new Usuario();
    private static final long serialVersionUID = -2152389656664659476L;
    private String nombre;
    private String clave;
    private boolean logeado = false;
    private int rol = 99;
    private String galleriImg = "", galleriDesc = "", galleriTitle = "";
    GalleriController galleriController;
    private List<GalleriController> images;

    @PostConstruct
    public void init() {

        images = new ArrayList<GalleriController>();
        for (int i = 1; i <= 11; i++) {
            galleriImg = "index" + i + ".png";
            switch (i) {
                case 1:
                    galleriTitle = "Calima Centro Comercial";
                    galleriDesc = "Ubicación: Cra. 30 en Calles 19 y 22  Bogotá - Colombia";
                    break;
                case 2:
                    galleriTitle = "Destilería Ingenio Manuelita";
                    galleriDesc = "Planta de Alcohol Carburante Manuelita.";
                    break;
                case 3:
                    galleriTitle = "Planta Cervecería Del Valle - Bavaria";
                    galleriDesc = "Ubicación: ACOPI - Yumbo   Valle del Cauca - Colombia";
                    break;
                case 4:
                    galleriTitle = "Complejo Vial Calle 25 con Carrera 15";
                    galleriDesc = "Puente vehicular y peatonal  Santiago de Cali - Colombia";
                    break;
                case 5:
                    galleriTitle = "Planta Cementos San Marcos";
                    galleriDesc = "Yumbo corregimiento de San Marcos Valle del Cauca";
                    break;
                case 6:
                    galleriTitle = "Centro Comercial Centenario";
                    galleriDesc = "Ubicación: Oeste Cali  Santiago de Cali -  Colombia";
                    break;
                case 7:
                    galleriTitle = "Complejo Vida Centro Profesional de Salud";
                    galleriDesc = "Ubicación: Cll. 5D No. 38A-35 B. Tequendama  Santiago de Cali - Colombia";
                    break;
                case 8:
                    galleriTitle = "Ciudadela educativa Nuevo Latir";
                    galleriDesc = "Valle del Cauca - Colombia";
                    break;
                case 9:
                    galleriTitle = "Edificio de Cogeneración Ingenio Providencia";
                    galleriDesc = "Valle del Cauca - Colombia";
                    break;
                case 10:
                    galleriTitle = "Edificio de Caldera y Economizador Planta Propal";
                    galleriDesc = "Carvajal pulpa y papel";
                    break;
                case 11:
                    galleriTitle = "Complejo Acuático Simón Bolívar";
                    galleriDesc = "Ubicación: Calle 63 Nº 47 - 00, Bogotá";
                    break;
            }
            galleriController = new GalleriController(galleriImg, galleriTitle, galleriDesc);
            images.add(galleriController);
        }
    }

    public List<GalleriController> getImages() {
        return images;
    }

    public boolean estaLogeado() {
        return logeado;
    }

    public int getRol() {
        return rol;
    }

    public boolean admin() {
        if (rol == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUserName() {
        return usuarioFacade.getUsername();
    }

    public void login(ActionEvent actionEvent) {
        this.usuarioFacade = new UsuarioFacade();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        if (usuarioFacade.loginControl(nombre, clave)) {
            rol = usuarioFacade.getRol();
            logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombre);
        } else {
            rol = 99;
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("rol", rol);
        context.addCallbackParam("estaLogeado", logeado);
        if (logeado) {
            context.addCallbackParam("view", "faces/index.xhtml");
        }
    }

    public void logoutActionListener() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }
}
