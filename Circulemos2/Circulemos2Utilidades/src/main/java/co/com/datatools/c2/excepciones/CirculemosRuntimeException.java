package co.com.datatools.c2.excepciones;

import java.text.MessageFormat;

import javax.ejb.ApplicationException;

/**
 * Clase que tiene el manejo o control de las excepciones de RunTime de Circulemos2
 * 
 * @author luis.martinez<br>
 *         felipe.martinez: creacion de constructores adicionales
 */
@ApplicationException(rollback = true)
public class CirculemosRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CirculemosRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor de excepcion con mensaje simple
     * 
     * @param message
     *            mensaje basico de la excepcion
     */
    public CirculemosRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepcion con mensaje de estilo {@link java.text.MessageFormat}
     * 
     * @param message
     *            mensaje formateado
     * @param params
     *            parametros del mensaje
     */
    public CirculemosRuntimeException(String message, Object... params) {
        super(MessageFormat.format(String.valueOf(message), params));
    }

    /**
     * Constructor con mensaje adicional a la informacion de excepcion causante
     * 
     * @param message
     *            mensaje adicional
     * @param cause
     *            causa real de la excepcion
     */
    public CirculemosRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de la excepcion con mensaje de estilo {@link java.text.MessageFormat} y causa de la excepcion
     * 
     * @param message
     *            mensaje formateado
     * @param cause
     *            causa real de la excepcion
     * @param params
     *            parametros del mensaje
     */
    public CirculemosRuntimeException(String message, Throwable cause, Object... params) {
        super(MessageFormat.format(String.valueOf(message), params), cause);
    }

}
