/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.OrdenProduccion;
import java.util.Date;
import java.util.List;
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
public class OrdenProduccionFacade extends AbstractFacade<OrdenProduccion> {

    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenProduccionFacade() {
        super(OrdenProduccion.class);
    }

    public List<Date> listDatesOrdenProduccion() {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT fecha FROM ORDEN_PRODUCCION";
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List listDatesOrdenProduccionID() {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT id_ordenproduccion FROM ORDEN_PRODUCCION";
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public int getPlanoCantidad(int idPlano) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT cantidad FROM PLANO WHERE id_plano = " + idPlano;
            Query q = emx.createNativeQuery(sql);
            return  (int) q.getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }
}
