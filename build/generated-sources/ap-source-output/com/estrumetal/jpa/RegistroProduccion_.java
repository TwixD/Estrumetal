package com.estrumetal.jpa;

import com.estrumetal.jpa.Ruta;
import com.estrumetal.jpa.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-03-23T19:55:37")
@StaticMetamodel(RegistroProduccion.class)
public class RegistroProduccion_ { 

    public static volatile SingularAttribute<RegistroProduccion, Integer> totalProduccion;
    public static volatile SingularAttribute<RegistroProduccion, String> estado;
    public static volatile SingularAttribute<RegistroProduccion, Usuario> uSUARIOidusuario;
    public static volatile SingularAttribute<RegistroProduccion, Ruta> rUTAidruta;
    public static volatile SingularAttribute<RegistroProduccion, Integer> idRegistroproduccion;
    public static volatile SingularAttribute<RegistroProduccion, Date> fechaInicio;
    public static volatile SingularAttribute<RegistroProduccion, Date> fechaTerminacion;

}