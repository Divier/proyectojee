package co.com.datatools.c2.excepciones;

import java.lang.reflect.Field;
import java.text.MessageFormat;

/**
 * Tipo de excepcion para controlar el acceso a drools
 * 
 * @author felipe.martinez
 */
public class ReglaNegocioInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 296214407974450740L;

    private static final String ERROR_CAMPO_NULL = "La regla de negocio asociada a la clase: {0}, es NULL para el campo: {1}";
    private static final String ERROR_INESPERADO = "Error ejecutando la regla de negocio asociada a la clase: {0}";

    /**
     * Constructor de excepcion cuando el valor de <b>campo</b> es null pero esta marcado con la anotacion {@link NotNull}
     * 
     * @param regla
     *            clase del objeto de regla de negocio que no se encuentra correctamente parametrizado
     * @param campo
     *            campo del objeto de la regla consumida que tiene el valor inesperado NULL
     */
    public ReglaNegocioInvalidaException(Class<?> regla, Field campo) {
        super(MessageFormat.format(ERROR_CAMPO_NULL, regla.getName(), campo.getName()));
    }

    /**
     * Constructor para manejo de excepciones inesperada de drools, esperado que se presente cuando hay problemas de comunicacion con el servidor
     * drools parametrizado
     * 
     * @param regla
     *            clase del objeto de regla de negocio que se consumio cuando se genero el error
     * @param cause
     *            excepcion causante del error
     */
    public ReglaNegocioInvalidaException(Class<?> regla, Throwable cause) {
        super(MessageFormat.format(ERROR_INESPERADO, regla.getName()), cause);
    }

}
