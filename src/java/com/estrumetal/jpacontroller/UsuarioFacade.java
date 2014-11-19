/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Rol;
import com.estrumetal.jpa.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author twix
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;
    private String username; 
    private int rol;

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
   

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean loginControl(String user, String password) {
        String flag = validate(user, password);
        if (!flag.equalsIgnoreCase("success")) {
            return false;
        } else {
            return true;
        }
    }

    public String validate(String userName, String password) {
        String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
        EntityManagerFactory factory;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emx = factory.createEntityManager();
        Query q = emx.createQuery("SELECT u FROM Usuario u WHERE u.user = :user AND u.password = :password");
        q.setParameter("user", userName);
        q.setParameter("password", password);
        try {
            Usuario usuario;
            Rol rol;    
            usuario  = (Usuario) q.getSingleResult();
            rol = usuario.getROLidrol();
            this.setUsername(usuario.getNombre());
            this.setRol(rol.getIdRol());
            return "success";
        } catch (NoResultException e) {
            return "failure";
        }
    }

}
