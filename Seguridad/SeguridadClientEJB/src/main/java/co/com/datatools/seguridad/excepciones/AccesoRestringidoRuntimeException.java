package co.com.datatools.seguridad.excepciones;

import javax.ejb.ApplicationException;

/**
 * Excepcion utilizada cuando ocurre una peticion a un recurso u operacion no permitida
 * 
 * @author Felipe Martinez
 */
@ApplicationException(rollback = true)
public class AccesoRestringidoRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con mensaje adicional a la informacion de excepcion causante
     * 
     * @param message
     *            mensaje adicional
     * @param cause
     *            causa real de la excepcion
     */
    public AccesoRestringidoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de excepcion con mensaje simple
     * 
     * @param message
     *            mensaje basico de la excepcion
     */
    public AccesoRestringidoRuntimeException(String message) {
        super(message);

    }

    /**
     * Constructor de excepcion con la causa
     * 
     * @param cause
     *            causa de la excepcion
     */
    public AccesoRestringidoRuntimeException(Throwable cause) {
        super(cause);

    }
}
