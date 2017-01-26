package co.com.datatools.seguridad.excepciones;

import javax.ejb.ApplicationException;

/**
 * Excepcion para manejar los errores controlados de negocio de Seguridad
 * 
 * @author felipe.martinez
 */
@ApplicationException(rollback = true)
public class SeguridadException extends Exception {

    private static final long serialVersionUID = 1L;
    /**
     * Objeto para almacenar la informacion del error de la funcionalidad de seguridad
     */
    private final ErrorWrapper errorInfo;

    /**
     * Constructor de la excepcion con los datos del error
     * 
     * @param errorInfo
     *            Objeto con los datos del error
     */
    public SeguridadException(ErrorWrapper errorInfo) {
        super(errorInfo.toString());
        this.errorInfo = errorInfo;
    }

    /**
     * Constructor de la excepcion con los datos del error y un mensaje adicional
     * 
     * @param errorInfo
     *            Objeto con los datos del error
     * @param contextualInfo
     *            mensaje adicional
     */
    public SeguridadException(ErrorWrapper errorInfo, String contextualInfo) {
        super(errorInfo.toString() + "[ " + contextualInfo + " ]");
        this.errorInfo = errorInfo;
    }

    public ErrorWrapper getErrorInfo() {
        return errorInfo;
    }

}
