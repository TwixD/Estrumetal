/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.Plano;
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
public class PlanoFacade extends AbstractFacade<Plano> {

    @PersistenceContext(unitName = "projectEstrumetalPU")
    private EntityManager em;
    private Plano plano;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanoFacade() {
        super(Plano.class);
    }

    public String asignar(int idPlano, String observacion) {
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

    public String prestamo(int idPlano, String observacion) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            Query q = emx.createQuery("UPDATE Plano p SET p.actual_prestamo = :observacion WHERE p.idPlano = :idPlano");
            q.setParameter("observacion", observacion);
            q.setParameter("idPlano", idPlano);
            q.executeUpdate();
            return "0";
        } catch (Exception e) {
            return null;
        }
    }

    public String recibir(int idPlano) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            Query qx = emx.createQuery("SELECT p FROM Plano p WHERE p.idPlano = :idPlano");
            qx.setParameter("idPlano", idPlano);
            plano = (Plano) qx.getSingleResult();
            
            Query q = emx.createQuery("UPDATE Plano p SET p.historial_prestamo = :historialPlano , p.actual_prestamo = :thenull WHERE p.idPlano = :idPlano");
            q.setParameter("idPlano", idPlano);
            q.setParameter("historialPlano", plano.getActual_prestamo());
            q.setParameter("thenull", null);
            q.executeUpdate();
            return "0";
        } catch (Exception e) {
            return null;
        }
    }

    public boolean planoDisponible(int idPlano) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            Query q = emx.createQuery("SELECT p FROM Plano p WHERE p.idPlano = :idPlano");
            q.setParameter("idPlano", idPlano);
            plano = (Plano) q.getSingleResult();
            if (plano.getActual_prestamo() == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public Plano getPlanoById(int id) {
        String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
        EntityManagerFactory factory;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emx = factory.createEntityManager();
        Query q = emx.createQuery("SELECT p FROM Plano p WHERE p.idPlano = :idPlano");
        q.setParameter("idPlano", id);
        try {
            Plano plano;   
            plano  = (Plano) q.getSingleResult();
            return plano;
        } catch (NoResultException e) {
            System.out.println("PlanoFacade.validate "+ e);
            return null;
        }
    }
    

}
