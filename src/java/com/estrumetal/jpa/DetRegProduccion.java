/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author twix
 */
@Entity
@Table(name = "DET_REG_PRODUCCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetRegProduccion.findAll", query = "SELECT d FROM DetRegProduccion d"),
    @NamedQuery(name = "DetRegProduccion.findByIdDetRegProduccion", query = "SELECT d FROM DetRegProduccion d WHERE d.idDetRegProduccion = :idDetRegProduccion"),
    @NamedQuery(name = "DetRegProduccion.findByCodPieza", query = "SELECT d FROM DetRegProduccion d WHERE d.codPieza = :codPieza"),
    @NamedQuery(name = "DetRegProduccion.findByCantidad", query = "SELECT d FROM DetRegProduccion d WHERE d.cantidad = :cantidad")})
public class DetRegProduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_det_reg_produccion")
    private Integer idDetRegProduccion;
    @Column(name = "cod_pieza")
    private Integer codPieza;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "RP_id_registroproduccion", referencedColumnName = "id_registroproduccion")
    @ManyToOne
    private RegistroProduccion rPidregistroproduccion;

    public DetRegProduccion() {
    }

    public DetRegProduccion(Integer idDetRegProduccion) {
        this.idDetRegProduccion = idDetRegProduccion;
    }

    public Integer getIdDetRegProduccion() {
        return idDetRegProduccion;
    }

    public void setIdDetRegProduccion(Integer idDetRegProduccion) {
        this.idDetRegProduccion = idDetRegProduccion;
    }

    public Integer getCodPieza() {
        return codPieza;
    }

    public void setCodPieza(Integer codPieza) {
        this.codPieza = codPieza;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public RegistroProduccion getRPidregistroproduccion() {
        return rPidregistroproduccion;
    }

    public void setRPidregistroproduccion(RegistroProduccion rPidregistroproduccion) {
        this.rPidregistroproduccion = rPidregistroproduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetRegProduccion != null ? idDetRegProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetRegProduccion)) {
            return false;
        }
        DetRegProduccion other = (DetRegProduccion) object;
        if ((this.idDetRegProduccion == null && other.idDetRegProduccion != null) || (this.idDetRegProduccion != null && !this.idDetRegProduccion.equals(other.idDetRegProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.DetRegProduccion[ idDetRegProduccion=" + idDetRegProduccion + " ]";
    }
    
}
