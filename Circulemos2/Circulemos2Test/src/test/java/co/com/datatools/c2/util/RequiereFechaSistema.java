package co.com.datatools.c2.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * Documentacion que me permite saber que un metodo de pruebas requiere una fecha de sistema especifica para su correcta ejecucion 
 * @author felipe.martinez
 */
public @interface RequiereFechaSistema {

    /**
     * fecha en formato dd/mm/YYYY
     * 
     * @return
     */
    String fecha();
}