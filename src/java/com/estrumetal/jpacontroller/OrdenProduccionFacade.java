/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.OrdenProduccion;
import java.math.BigDecimal;
import java.math.BigInteger;
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

    public List<Date> datesOPUsuarioInicio(int idUsuario) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT fecha_inicio FROM REGISTRO_PRODUCCION WHERE USUARIO_id_usuario = "+idUsuario;
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
        public List<Date> datesOPUsuarioTerminacion(int idUsuario) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT fecha_terminacion FROM REGISTRO_PRODUCCION WHERE USUARIO_id_usuario = "+idUsuario;
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
        int i = 0;
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT sum(cantidad) FROM ORDEN_PRODUCCION WHERE PLANO_id_plano = " + idPlano;
            int x = getFPlano(idPlano);
            Query q = emx.createNativeQuery(sql);
            Object singleResult = q.getSingleResult();
            if (singleResult != null) {
                BigDecimal a = getBigDecimal(singleResult);
                i = a.intValue();
            }
            int total = x - i;
            return total;
        } catch (Exception e) {
            System.out.println(e + "");
            return 0;

        }
    }

    public int getFPlano(int idPlano) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT cantidad FROM PLANO WHERE id_plano = " + idPlano;
            Query q = emx.createNativeQuery(sql);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }

}
