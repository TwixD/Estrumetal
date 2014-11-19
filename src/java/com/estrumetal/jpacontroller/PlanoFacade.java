/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Plano;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
