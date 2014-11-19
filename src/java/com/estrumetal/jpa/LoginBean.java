/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpa;

import com.estrumetal.jpacontroller.UsuarioFacade;
import java.io.Serializable;
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
