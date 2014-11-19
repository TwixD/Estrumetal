/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrumetal.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author twix
 */
@Entity
@Table(name = "PIEZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pieza.findAll", query = "SELECT p FROM Pieza p"),
    @NamedQuery(name = "Pieza.findByIdPieza", query = "SELECT p FROM Pieza p WHERE p.idPieza = :idPieza"),
    @NamedQuery(name = "Pieza.findByCantidad", query = "SELECT p FROM Pieza p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pieza.findByLongitud", query = "SELECT p FROM Pieza p WHERE p.longitud = :longitud"),
    @NamedQuery(name = "Pieza.findByAncho", query = "SELECT p FROM Pieza p WHERE p.ancho = :ancho"),
    @NamedQuery(name = "Pieza.findByCantidadPerforaciones", query = "SELECT p FROM Pieza p WHERE p.cantidadPerforaciones = :cantidadPerforaciones"),
    @NamedQuery(name = "Pieza.findByAreaCorte", query = "SELECT p FROM Pieza p WHERE p.areaCorte = :areaCorte"),
    @NamedQuery(name = "Pieza.findByBisel", query = "SELECT p FROM Pieza p WHERE p.bisel = :bisel")})
public class Pieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pieza")
    private Integer idPieza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitud")
    private int longitud;
    @Column(name = "ancho")
    private Integer ancho;
    @Column(name = "cantidad_perforaciones")
    private Integer cantidadPerforaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "area_corte")
    private BigDecimal areaCorte;
    @Size(max = 45)
    @Column(name = "bisel")
    private String bisel;
    @JoinColumn(name = "PLANO_id_plano", referencedColumnName = "id_plano")
    @ManyToOne(optional = false)
    private Plano pLANOidplano;
    @JoinColumn(name = "MATERIA_PRIMA_id_materiaprima", referencedColumnName = "id_materiaprima")
    @ManyToOne(optional = false)
    private MateriaPrima mATERIAPRIMAidmateriaprima;

    public Pieza() {
    }

    public Pieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public Pieza(Integer idPieza, int cantidad, int longitud) {
        this.idPieza = idPieza;
        this.cantidad = cantidad;
        this.longitud = longitud;
    }

    public Integer getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getCantidadPerforaciones() {
        return cantidadPerforaciones;
    }

    public void setCantidadPerforaciones(Integer cantidadPerforaciones) {
        this.cantidadPerforaciones = cantidadPerforaciones;
    }

    public BigDecimal getAreaCorte() {
        return areaCorte;
    }

    public void setAreaCorte(BigDecimal areaCorte) {
        this.areaCorte = areaCorte;
    }

    public String getBisel() {
        return bisel;
    }

    public void setBisel(String bisel) {
        this.bisel = bisel;
    }

    public Plano getPLANOidplano() {
        return pLANOidplano;
    }

    public void setPLANOidplano(Plano pLANOidplano) {
        this.pLANOidplano = pLANOidplano;
    }

    public MateriaPrima getMATERIAPRIMAidmateriaprima() {
        return mATERIAPRIMAidmateriaprima;
    }

    public void setMATERIAPRIMAidmateriaprima(MateriaPrima mATERIAPRIMAidmateriaprima) {
        this.mATERIAPRIMAidmateriaprima = mATERIAPRIMAidmateriaprima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPieza != null ? idPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieza)) {
            return false;
        }
        Pieza other = (Pieza) object;
        if ((this.idPieza == null && other.idPieza != null) || (this.idPieza != null && !this.idPieza.equals(other.idPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.estrumetal.jpa.Pieza[ idPieza=" + idPieza + " ]";
    }
    
}
