package com.estrumetal.jpa;

import com.estrumetal.jpa.Plano;
import com.estrumetal.jpa.Ruta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-03-23T19:55:37")
@StaticMetamodel(OrdenProduccion.class)
public class OrdenProduccion_ { 

    public static volatile SingularAttribute<OrdenProduccion, Integer> idOrdenproduccion;
    public static volatile SingularAttribute<OrdenProduccion, Date> fecha;
    public static volatile SingularAttribute<OrdenProduccion, Ruta> rUTAidruta;
    public static volatile SingularAttribute<OrdenProduccion, Integer> cantidad;
    public static volatile SingularAttribute<OrdenProduccion, Date> fechaRegistro;
    public static volatile SingularAttribute<OrdenProduccion, Plano> pLANOidplano;

}