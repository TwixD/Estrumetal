/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Ruta;
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
public class RutaFacade extends AbstractFacade<Ruta> {

    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RutaFacade() {
        super(Ruta.class);
    }

    public List<Date> datesRutaMaquinaIdInicio(int idMaquina) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT fecha_produccion FROM RUTA WHERE MAQUINA_id_maquina = " + idMaquina;
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Date> datesRutaMaquinaIdFin(int idMaquina) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT fecha_terminacion FROM RUTA WHERE MAQUINA_id_maquina = " + idMaquina;
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
