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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "OrdenProduccion.findByIdOrdenproduccion", query = "SELECT o FROM OrdenProduccion o WHERE o.idOrdenproduccion = :idOrdenproduccion"),
    @NamedQuery(name = "OrdenProduccion.findByFecha", query = "SELECT o FROM OrdenProduccion o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "OrdenProduccion.findByCantidad", query = "SELECT o FROM OrdenProduccion o WHERE o.cantidad = :cantidad")})
public class OrdenProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ordenproduccion")
    private Integer idOrdenproduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
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

    public OrdenProduccion(Integer idOrdenproduccion) {
        this.idOrdenproduccion = idOrdenproduccion;
    }

    public OrdenProduccion(Integer idOrdenproduccion, int cantidad, Date fecha, Ruta rUTAidruta, Plano pLANOidplano) {
        this.idOrdenproduccion = idOrdenproduccion;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.rUTAidruta = rUTAidruta;
        this.pLANOidplano = pLANOidplano;
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

    public int getIdOrdenproduccion() {
        return idOrdenproduccion;
    }

    public void setIdOrdenproduccion(int idOrdenproduccion) {
        this.idOrdenproduccion = idOrdenproduccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenproduccion != null ? idOrdenproduccion.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenProduccion)) {
            return false;
        }
        OrdenProduccion other = (OrdenProduccion) object;
        if ((this.idOrdenproduccion == null && other.idOrdenproduccion != null) || (this.idOrdenproduccion != null && !this.idOrdenproduccion.equals(other.idOrdenproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idOrdenproduccion + "";
    }

}
