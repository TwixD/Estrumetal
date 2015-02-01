package com.estrumetal.jpa;

import com.estrumetal.jpa.Obra;
import com.estrumetal.jpa.OrdenProduccion;
import com.estrumetal.jpa.Pieza;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-01-31T16:06:11")
@StaticMetamodel(Plano.class)
public class Plano_ { 

    public static volatile SingularAttribute<Plano, String> nombre;
    public static volatile SingularAttribute<Plano, String> revision;
    public static volatile SingularAttribute<Plano, String> observacion;
    public static volatile SingularAttribute<Plano, Integer> idPlano;
    public static volatile SingularAttribute<Plano, Date> fecha;
    public static volatile CollectionAttribute<Plano, OrdenProduccion> ordenProduccionCollection;
    public static volatile CollectionAttribute<Plano, Pieza> piezaCollection;
    public static volatile SingularAttribute<Plano, BigDecimal> pesoUnitario;
    public static volatile SingularAttribute<Plano, Integer> cantidad;
    public static volatile SingularAttribute<Plano, BigDecimal> areaUnitaria;
    public static volatile SingularAttribute<Plano, Obra> oBRAidobra;

}