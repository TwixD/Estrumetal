/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author twix
 */
@Embeddable
public class OrdenProduccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_ordenproduccion")
    private int idOrdenproduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    public OrdenProduccionPK() {
    }

    public OrdenProduccionPK(int idOrdenproduccion, int cantidad) {
        this.idOrdenproduccion = idOrdenproduccion;
        this.cantidad = cantidad;
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
        hash += (int) idOrdenproduccion;
        hash += (int) cantidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenProduccionPK)) {
            return false;
        }
        OrdenProduccionPK other = (OrdenProduccionPK) object;
        if (this.idOrdenproduccion != other.idOrdenproduccion) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.OrdenProduccionPK[ idOrdenproduccion=" + idOrdenproduccion + ", cantidad=" + cantidad + " ]";
    }
    
}
