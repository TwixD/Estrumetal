package com.estrumetal.jpa;

import com.estrumetal.jpa.Plano;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-02-02T13:36:40")
@StaticMetamodel(Obra.class)
public class Obra_ { 

    public static volatile SingularAttribute<Obra, String> nombre;
    public static volatile SingularAttribute<Obra, Integer> idObra;
    public static volatile CollectionAttribute<Obra, Plano> planoCollection;

}