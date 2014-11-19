/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.RegistroProduccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author twix
 */
@Stateless
public class RegistroProduccionFacade extends AbstractFacade<RegistroProduccion> {
    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroProduccionFacade() {
        super(RegistroProduccion.class);
    }
    
}
