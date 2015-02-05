/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Plano;
import com.estrumetal.jsf.util.JsfUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author twix
 */
@Stateless
public class PlanoFacade extends AbstractFacade<Plano> {

    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanoFacade() {
        super(Plano.class);
    }

    public String asignar(int idPlano,String observacion) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            Query q = emx.createQuery("UPDATE Plano p SET p.observacion = :observacion WHERE p.idPlano = :idPlano");
            q.setParameter("observacion", observacion);
            q.setParameter("idPlano", idPlano);
            q.executeUpdate();
            return "0";
        } catch (Exception e) {
            return null;
        }
    }

}
