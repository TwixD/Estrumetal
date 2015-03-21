/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpacontroller;

import com.estrumetal.jpa.RegistroProduccion;
import java.util.List;
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

    public List listMaquinaProduccion(String fechaInicial, String fechaFinal) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT CONCAT(MAQUINA.id_maquina,' ',MAQUINA.nombre) as MAQUINA,PIEZA.cantidad as CANTIDAD,IF(MATERIA_PRIMA.tipo = 'LAMINA', ((PIEZA.longitud * PIEZA.ancho)/1000000) * MATERIA_PRIMA.peso_ml,(PIEZA.longitud/1000) * MATERIA_PRIMA.peso_ml) as PESO\n"
                    + "FROM PIEZA\n"
                    + "LEFT JOIN MATERIA_PRIMA ON MATERIA_PRIMA.id_materiaprima = PIEZA.MATERIA_PRIMA_id_materiaprima\n"
                    + "LEFT JOIN PLANO ON PIEZA.PLANO_id_plano = PLANO.id_plano\n"
                    + "LEFT JOIN ORDEN_PRODUCCION ON PLANO.id_plano = ORDEN_PRODUCCION.PLANO_id_plano\n"
                    + "LEFT JOIN RUTA ON ORDEN_PRODUCCION.RUTA_id_ruta = RUTA.id_ruta\n"
                    + "LEFT JOIN MAQUINA ON RUTA.MAQUINA_id_maquina = MAQUINA.id_maquina\n"
                    + "WHERE RUTA.fecha_produccion >= '" + fechaInicial + "'\n"
                    + "AND RUTA.fecha_terminacion <= '" + fechaFinal + "'";
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List listOperarioProduccion(String fechaInicial, String fechaFinal) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT CONCAT(MAQUINA.id_maquina,' ',MAQUINA.nombre) as MAQUINA,CONCAT(USUARIO.documento,'-',USUARIO.nombre) as OPERARIO,PIEZA.cantidad as CANTIDAD,IF(MATERIA_PRIMA.tipo = 'LAMINA', ((PIEZA.longitud * PIEZA.ancho)/1000000) * MATERIA_PRIMA.peso_ml,(PIEZA.longitud/1000) * MATERIA_PRIMA.peso_ml) as PESO\n"
                    + "FROM PIEZA\n"
                    + "LEFT JOIN MATERIA_PRIMA ON MATERIA_PRIMA.id_materiaprima = PIEZA.MATERIA_PRIMA_id_materiaprima\n"
                    + "LEFT JOIN PLANO ON PIEZA.PLANO_id_plano = PLANO.id_plano\n"
                    + "LEFT JOIN ORDEN_PRODUCCION ON PLANO.id_plano = ORDEN_PRODUCCION.PLANO_id_plano\n"
                    + "LEFT JOIN RUTA ON ORDEN_PRODUCCION.RUTA_id_ruta = RUTA.id_ruta\n"
                    + "LEFT JOIN MAQUINA ON RUTA.MAQUINA_id_maquina = MAQUINA.id_maquina\n"
                    + "LEFT JOIN REGISTRO_PRODUCCION ON RUTA.id_ruta = REGISTRO_PRODUCCION.RUTA_id_ruta\n"
                    + "LEFT JOIN USUARIO ON REGISTRO_PRODUCCION.USUARIO_id_usuario = USUARIO.id_usuario\n"
                    + "WHERE RUTA.fecha_produccion >= '" + fechaInicial + "' \n"
                    + "AND RUTA.fecha_terminacion <= '" + fechaFinal + "'";
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List listReporteProduccion(int idMaquina) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT PIEZA.id_pieza as PIEZA,PIEZA.cantidad as CANTIDAD\n"
                    + "FROM PIEZA\n"
                    + "LEFT JOIN MATERIA_PRIMA ON MATERIA_PRIMA.id_materiaprima = PIEZA.MATERIA_PRIMA_id_materiaprima\n"
                    + "LEFT JOIN PLANO ON PIEZA.PLANO_id_plano = PLANO.id_plano\n"
                    + "LEFT JOIN ORDEN_PRODUCCION ON PLANO.id_plano = ORDEN_PRODUCCION.PLANO_id_plano\n"
                    + "LEFT JOIN RUTA ON ORDEN_PRODUCCION.RUTA_id_ruta = RUTA.id_ruta\n"
                    + "LEFT JOIN MAQUINA ON RUTA.MAQUINA_id_maquina = MAQUINA.id_maquina\n"
                    + "WHERE MAQUINA.id_maquina =" + idMaquina;
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List listPendienteProduccion(int idMaquina) {
        try {
            String PERSISTENCE_UNIT_NAME = "projectEstrumetalPU";
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager emx = factory.createEntityManager();
            String sql = "SELECT MAQUINA.nombre as NOMBRE,OBRA.id_obra as OBRA,CONCAT(PLANO.id_plano,'-',PLANO.nombre) as PLANO,PIEZA.id_pieza as PIEZA,CONCAT(MATERIA_PRIMA.id_materiaprima,'-',MATERIA_PRIMA.descripcion) as MATERIA_PRIMA,PIEZA.longitud as LONGITUD,PIEZA.ancho as ANCHO,ORDEN_PRODUCCION.cantidad as CANTIDAD_PENDIENTE\n"
                    + "FROM PIEZA\n"
                    + "LEFT JOIN MATERIA_PRIMA ON MATERIA_PRIMA.id_materiaprima = PIEZA.MATERIA_PRIMA_id_materiaprima\n"
                    + "LEFT JOIN PLANO ON PIEZA.PLANO_id_plano = PLANO.id_plano\n"
                    + "LEFT JOIN OBRA ON PLANO.OBRA_id_obra = OBRA.id_obra\n"
                    + "LEFT JOIN ORDEN_PRODUCCION ON PLANO.id_plano = ORDEN_PRODUCCION.PLANO_id_plano\n"
                    + "LEFT JOIN RUTA ON ORDEN_PRODUCCION.RUTA_id_ruta = RUTA.id_ruta\n"
                    + "LEFT JOIN MAQUINA ON RUTA.MAQUINA_id_maquina = MAQUINA.id_maquina\n"
                    + "LEFT JOIN REGISTRO_PRODUCCION ON RUTA.id_ruta = REGISTRO_PRODUCCION.RUTA_id_ruta\n"
                    + "LEFT JOIN USUARIO ON REGISTRO_PRODUCCION.USUARIO_id_usuario = USUARIO.id_usuario\n"
                    + "WHERE MAQUINA.id_maquina = " + idMaquina;
            Query q = emx.createNativeQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
