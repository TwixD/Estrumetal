/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrumetal.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author twix
 */
@Entity
@Table(name = "MATERIA_PRIMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPrima.findAll", query = "SELECT m FROM MateriaPrima m"),
    @NamedQuery(name = "MateriaPrima.findByIdMateriaprima", query = "SELECT m FROM MateriaPrima m WHERE m.idMateriaprima = :idMateriaprima"),
    @NamedQuery(name = "MateriaPrima.findByDescripcion", query = "SELECT m FROM MateriaPrima m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MateriaPrima.findByTipo", query = "SELECT m FROM MateriaPrima m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MateriaPrima.findByPesoMl", query = "SELECT m FROM MateriaPrima m WHERE m.pesoMl = :pesoMl")})
public class MateriaPrima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materiaprima")
    private Integer idMateriaprima;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso_ml")
    private BigDecimal pesoMl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mATERIAPRIMAidmateriaprima")
    private Collection<Pieza> piezaCollection;

    public MateriaPrima() {
    }

    public MateriaPrima(Integer idMateriaprima) {
        this.idMateriaprima = idMateriaprima;
    }

    public MateriaPrima(Integer idMateriaprima, String descripcion, String tipo, BigDecimal pesoMl) {
        this.idMateriaprima = idMateriaprima;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.pesoMl = pesoMl;
    }

    public Integer getIdMateriaprima() {
        return idMateriaprima;
    }

    public void setIdMateriaprima(Integer idMateriaprima) {
        this.idMateriaprima = idMateriaprima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPesoMl() {
        return pesoMl;
    }

    public void setPesoMl(BigDecimal pesoMl) {
        this.pesoMl = pesoMl;
    }

    @XmlTransient
    public Collection<Pieza> getPiezaCollection() {
        return piezaCollection;
    }

    public void setPiezaCollection(Collection<Pieza> piezaCollection) {
        this.piezaCollection = piezaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaprima != null ? idMateriaprima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPrima)) {
            return false;
        }
        MateriaPrima other = (MateriaPrima) object;
        if ((this.idMateriaprima == null && other.idMateriaprima != null) || (this.idMateriaprima != null && !this.idMateriaprima.equals(other.idMateriaprima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  idMateriaprima + " - " + descripcion;
    }

}
