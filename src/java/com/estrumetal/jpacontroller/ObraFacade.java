/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Obra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author twix
 */
@Stateless
public class ObraFacade extends AbstractFacade<Obra> {
    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObraFacade() {
        super(Obra.class);
    }
    
}
