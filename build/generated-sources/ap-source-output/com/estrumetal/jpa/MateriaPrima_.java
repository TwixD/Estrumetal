package com.estrumetal.jpa;

import com.estrumetal.jpa.Pieza;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-11-29T19:13:02")
@StaticMetamodel(MateriaPrima.class)
public class MateriaPrima_ { 

    public static volatile SingularAttribute<MateriaPrima, Integer> idMateriaprima;
    public static volatile SingularAttribute<MateriaPrima, BigDecimal> pesoMl;
    public static volatile CollectionAttribute<MateriaPrima, Pieza> piezaCollection;
    public static volatile SingularAttribute<MateriaPrima, String> tipo;
    public static volatile SingularAttribute<MateriaPrima, String> descripcion;

}