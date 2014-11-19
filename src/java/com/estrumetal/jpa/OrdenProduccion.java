/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author twix
 */
@Entity
@Table(name = "ORDEN_PRODUCCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenProduccion.findAll", query = "SELECT o FROM OrdenProduccion o"),
    @NamedQuery(name = "OrdenProduccion.findByIdOrdenproduccion", query = "SELECT o FROM OrdenProduccion o WHERE o.ordenProduccionPK.idOrdenproduccion = :idOrdenproduccion"),
    @NamedQuery(name = "OrdenProduccion.findByFecha", query = "SELECT o FROM OrdenProduccion o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "OrdenProduccion.findByCantidad", query = "SELECT o FROM OrdenProduccion o WHERE o.ordenProduccionPK.cantidad = :cantidad")})
public class OrdenProduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenProduccionPK ordenProduccionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "RUTA_id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta rUTAidruta;
    @JoinColumn(name = "PLANO_id_plano", referencedColumnName = "id_plano")
    @ManyToOne(optional = false)
    private Plano pLANOidplano;

    public OrdenProduccion() {
    }

    public OrdenProduccion(OrdenProduccionPK ordenProduccionPK) {
        this.ordenProduccionPK = ordenProduccionPK;
    }

    public OrdenProduccion(OrdenProduccionPK ordenProduccionPK, Date fecha) {
        this.ordenProduccionPK = ordenProduccionPK;
        this.fecha = fecha;
    }

    public OrdenProduccion(int idOrdenproduccion, int cantidad) {
        this.ordenProduccionPK = new OrdenProduccionPK(idOrdenproduccion, cantidad);
    }

    public OrdenProduccionPK getOrdenProduccionPK() {
        return ordenProduccionPK;
    }

    public void setOrdenProduccionPK(OrdenProduccionPK ordenProduccionPK) {
        this.ordenProduccionPK = ordenProduccionPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ruta getRUTAidruta() {
        return rUTAidruta;
    }

    public void setRUTAidruta(Ruta rUTAidruta) {
        this.rUTAidruta = rUTAidruta;
    }

    public Plano getPLANOidplano() {
        return pLANOidplano;
    }

    public void setPLANOidplano(Plano pLANOidplano) {
        this.pLANOidplano = pLANOidplano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenProduccionPK != null ? ordenProduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenProduccion)) {
            return false;
        }
        OrdenProduccion other = (OrdenProduccion) object;
        if ((this.ordenProduccionPK == null && other.ordenProduccionPK != null) || (this.ordenProduccionPK != null && !this.ordenProduccionPK.equals(other.ordenProduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.OrdenProduccion[ ordenProduccionPK=" + ordenProduccionPK + " ]";
    }
    
}
