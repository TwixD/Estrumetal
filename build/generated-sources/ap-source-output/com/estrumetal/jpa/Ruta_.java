package com.estrumetal.jpa;

import com.estrumetal.jpa.Maquina;
import com.estrumetal.jpa.OrdenProduccion;
import com.estrumetal.jpa.RegistroProduccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-03-23T19:55:37")
@StaticMetamodel(Ruta.class)
public class Ruta_ { 

    public static volatile SingularAttribute<Ruta, Date> fechaProduccion;
    public static volatile SingularAttribute<Ruta, Maquina> mAQUINAidmaquina;
    public static volatile CollectionAttribute<Ruta, OrdenProduccion> ordenProduccionCollection;
    public static volatile SingularAttribute<Ruta, Integer> idRuta;
    public static volatile SingularAttribute<Ruta, Date> fechaTerminacion;
    public static volatile CollectionAttribute<Ruta, RegistroProduccion> registroProduccionCollection;

}