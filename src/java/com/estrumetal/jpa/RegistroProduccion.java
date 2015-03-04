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
@Table(name = "REGISTRO_PRODUCCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroProduccion.findAll", query = "SELECT r FROM RegistroProduccion r"),
    @NamedQuery(name = "RegistroProduccion.findByIdRegistroproduccion", query = "SELECT r FROM RegistroProduccion r WHERE r.idRegistroproduccion = :idRegistroproduccion"),
    @NamedQuery(name = "RegistroProduccion.findByFechaInicio", query = "SELECT r FROM RegistroProduccion r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "RegistroProduccion.findByFechaTerminacion", query = "SELECT r FROM RegistroProduccion r WHERE r.fechaTerminacion = :fechaTerminacion"),
    @NamedQuery(name = "RegistroProduccion.findByTotalProduccion", query = "SELECT r FROM RegistroProduccion r WHERE r.totalProduccion = :totalProduccion")})
public class RegistroProduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registroproduccion")
    private Integer idRegistroproduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_terminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_produccion")
    private Integer totalProduccion;
    @JoinColumn(name = "USUARIO_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario uSUARIOidusuario;
    @JoinColumn(name = "RUTA_id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false)
    private Ruta rUTAidruta;

    public RegistroProduccion() {
    }

    public RegistroProduccion(Integer idRegistroproduccion) {
        this.idRegistroproduccion = idRegistroproduccion;
    }

    public RegistroProduccion(Integer idRegistroproduccion, Date fechaInicio, int totalProduccion) {
        this.idRegistroproduccion = idRegistroproduccion;
        this.fechaInicio = fechaInicio;
        this.totalProduccion = totalProduccion;
    }

    public Integer getIdRegistroproduccion() {
        return idRegistroproduccion;
    }

    public void setIdRegistroproduccion(Integer idRegistroproduccion) {
        this.idRegistroproduccion = idRegistroproduccion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Integer getTotalProduccion() {
        return totalProduccion;
    }

    public void setTotalProduccion(Integer totalProduccion) {
        this.totalProduccion = totalProduccion;
    }

    public Usuario getUSUARIOidusuario() {
        return uSUARIOidusuario;
    }

    public void setUSUARIOidusuario(Usuario uSUARIOidusuario) {
        this.uSUARIOidusuario = uSUARIOidusuario;
    }

    public Ruta getRUTAidruta() {
        return rUTAidruta;
    }

    public void setRUTAidruta(Ruta rUTAidruta) {
        this.rUTAidruta = rUTAidruta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroproduccion != null ? idRegistroproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroProduccion)) {
            return false;
        }
        RegistroProduccion other = (RegistroProduccion) object;
        if ((this.idRegistroproduccion == null && other.idRegistroproduccion != null) || (this.idRegistroproduccion != null && !this.idRegistroproduccion.equals(other.idRegistroproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.RegistroProduccion[ idRegistroproduccion=" + idRegistroproduccion + " ]";
    }
    
}
