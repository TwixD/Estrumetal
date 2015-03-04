/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Maquina;
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
public class MaquinaFacade extends AbstractFacade<Maquina> {
    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaquinaFacade() {
        super(Maquina.class);
    }
    
    public String getMaxId(String pkColumn,String table) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT MAX("+pkColumn+") FROM "+table;
            Query q = emx.createNativeQuery(sql);
            Object singleResult = q.getSingleResult();
            return singleResult.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
