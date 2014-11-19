/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author twix
 */
@Entity
@Table(name = "RUTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r"),
    @NamedQuery(name = "Ruta.findByIdRuta", query = "SELECT r FROM Ruta r WHERE r.idRuta = :idRuta"),
    @NamedQuery(name = "Ruta.findByFechaProduccion", query = "SELECT r FROM Ruta r WHERE r.fechaProduccion = :fechaProduccion"),
    @NamedQuery(name = "Ruta.findByFechaTerminacion", query = "SELECT r FROM Ruta r WHERE r.fechaTerminacion = :fechaTerminacion")})
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta")
    private Integer idRuta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_produccion")
    @Temporal(TemporalType.DATE)
    private Date fechaProduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_terminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminacion;
    @JoinColumn(name = "MAQUINA_id_maquina", referencedColumnName = "id_maquina")
    @ManyToOne(optional = false)
    private Maquina mAQUINAidmaquina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rUTAidruta")
    private Collection<OrdenProduccion> ordenProduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rUTAidruta")
    private Collection<RegistroProduccion> registroProduccionCollection;

    public Ruta() {
    }

    public Ruta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Ruta(Integer idRuta, Date fechaProduccion, Date fechaTerminacion) {
        this.idRuta = idRuta;
        this.fechaProduccion = fechaProduccion;
        this.fechaTerminacion = fechaTerminacion;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Date getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(Date fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Maquina getMAQUINAidmaquina() {
        return mAQUINAidmaquina;
    }

    public void setMAQUINAidmaquina(Maquina mAQUINAidmaquina) {
        this.mAQUINAidmaquina = mAQUINAidmaquina;
    }

    @XmlTransient
    public Collection<OrdenProduccion> getOrdenProduccionCollection() {
        return ordenProduccionCollection;
    }

    public void setOrdenProduccionCollection(Collection<OrdenProduccion> ordenProduccionCollection) {
        this.ordenProduccionCollection = ordenProduccionCollection;
    }

    @XmlTransient
    public Collection<RegistroProduccion> getRegistroProduccionCollection() {
        return registroProduccionCollection;
    }

    public void setRegistroProduccionCollection(Collection<RegistroProduccion> registroProduccionCollection) {
        this.registroProduccionCollection = registroProduccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRuta != null ? idRuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.idRuta == null && other.idRuta != null) || (this.idRuta != null && !this.idRuta.equals(other.idRuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.Ruta[ idRuta=" + idRuta + " ]";
    }
    
}
