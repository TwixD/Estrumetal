package com.estrumetal.jpa;

import com.estrumetal.jpa.MateriaPrima;
import com.estrumetal.jpa.Plano;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-01-31T16:06:11")
@StaticMetamodel(Pieza.class)
public class Pieza_ { 

    public static volatile SingularAttribute<Pieza, Integer> cantidad;
    public static volatile SingularAttribute<Pieza, Integer> cantidadPerforaciones;
    public static volatile SingularAttribute<Pieza, Integer> ancho;
    public static volatile SingularAttribute<Pieza, String> bisel;
    public static volatile SingularAttribute<Pieza, Integer> longitud;
    public static volatile SingularAttribute<Pieza, Integer> idPieza;
    public static volatile SingularAttribute<Pieza, MateriaPrima> mATERIAPRIMAidmateriaprima;
    public static volatile SingularAttribute<Pieza, BigDecimal> areaCorte;
    public static volatile SingularAttribute<Pieza, Plano> pLANOidplano;

}