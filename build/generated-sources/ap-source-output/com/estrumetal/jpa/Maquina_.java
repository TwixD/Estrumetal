package com.estrumetal.jpa;

import com.estrumetal.jpa.Ruta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-11-17T16:21:51")
@StaticMetamodel(Maquina.class)
public class Maquina_ { 

    public static volatile SingularAttribute<Maquina, String> nombre;
    public static volatile SingularAttribute<Maquina, Integer> idMaquina;
    public static volatile SingularAttribute<Maquina, String> tipo;
    public static volatile SingularAttribute<Maquina, String> ubicacion;
    public static volatile CollectionAttribute<Maquina, Ruta> rutaCollection;

}