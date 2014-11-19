/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author twix
 */
@Entity
@Table(name = "PLANO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plano.findAll", query = "SELECT p FROM Plano p"),
    @NamedQuery(name = "Plano.findByIdPlano", query = "SELECT p FROM Plano p WHERE p.idPlano = :idPlano"),
    @NamedQuery(name = "Plano.findByNombre", query = "SELECT p FROM Plano p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Plano.findByPesoUnitario", query = "SELECT p FROM Plano p WHERE p.pesoUnitario = :pesoUnitario"),
    @NamedQuery(name = "Plano.findByAreaUnitaria", query = "SELECT p FROM Plano p WHERE p.areaUnitaria = :areaUnitaria"),
    @NamedQuery(name = "Plano.findByCantidad", query = "SELECT p FROM Plano p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Plano.findByRevision", query = "SELECT p FROM Plano p WHERE p.revision = :revision"),
    @NamedQuery(name = "Plano.findByObservacion", query = "SELECT p FROM Plano p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "Plano.findByFecha", query = "SELECT p FROM Plano p WHERE p.fecha = :fecha")})
public class Plano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_plano")
    private Integer idPlano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso_unitario")
    private BigDecimal pesoUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "area_unitaria")
    private BigDecimal areaUnitaria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Size(max = 45)
    @Column(name = "revision")
    private String revision;
    @Size(max = 45)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pLANOidplano")
    private Collection<OrdenProduccion> ordenProduccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pLANOidplano")
    private Collection<Pieza> piezaCollection;
    @JoinColumn(name = "OBRA_id_obra", referencedColumnName = "id_obra")
    @ManyToOne(optional = false)
    private Obra oBRAidobra;

    public Plano() {
    }

    public Plano(Integer idPlano) {
        this.idPlano = idPlano;
    }

    public Plano(Integer idPlano, String nombre, BigDecimal pesoUnitario, BigDecimal areaUnitaria, int cantidad) {
        this.idPlano = idPlano;
        this.nombre = nombre;
        this.pesoUnitario = pesoUnitario;
        this.areaUnitaria = areaUnitaria;
        this.cantidad = cantidad;
    }

    public Integer getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Integer idPlano) {
        this.idPlano = idPlano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(BigDecimal pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    public BigDecimal getAreaUnitaria() {
        return areaUnitaria;
    }

    public void setAreaUnitaria(BigDecimal areaUnitaria) {
        this.areaUnitaria = areaUnitaria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<OrdenProduccion> getOrdenProduccionCollection() {
        return ordenProduccionCollection;
    }

    public void setOrdenProduccionCollection(Collection<OrdenProduccion> ordenProduccionCollection) {
        this.ordenProduccionCollection = ordenProduccionCollection;
    }

    @XmlTransient
    public Collection<Pieza> getPiezaCollection() {
        return piezaCollection;
    }

    public void setPiezaCollection(Collection<Pieza> piezaCollection) {
        this.piezaCollection = piezaCollection;
    }

    public Obra getOBRAidobra() {
        return oBRAidobra;
    }

    public void setOBRAidobra(Obra oBRAidobra) {
        this.oBRAidobra = oBRAidobra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlano != null ? idPlano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plano)) {
            return false;
        }
        Plano other = (Plano) object;
        if ((this.idPlano == null && other.idPlano != null) || (this.idPlano != null && !this.idPlano.equals(other.idPlano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.Plano[ idPlano=" + idPlano + " ]";
    }
    
}
