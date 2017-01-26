package co.com.datatools.seguridad.web.excepciones;

import javax.ejb.ApplicationException;

/**
 * Excepcion utilizada cuando ocurre una peticion a una sesion que no tiene validez
 * 
 * @author giovanni.velandia
 */
@ApplicationException(rollback = true)
public class SesionInvalidaRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con mensaje adicional a la informacion de excepcion causante
     * 
     * @param message
     *            mensaje adicional
     * @param cause
     *            causa real de la excepcion
     */
    public SesionInvalidaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de excepcion con mensaje simple
     * 
     * @param message
     *            mensaje basico de la excepcion
     */
    public SesionInvalidaRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor de excepcion con la causa
     * 
     * @param cause
     *            causa de la excepcion
     */
    public SesionInvalidaRuntimeException(Throwable cause) {
        super(cause);
    }
}
