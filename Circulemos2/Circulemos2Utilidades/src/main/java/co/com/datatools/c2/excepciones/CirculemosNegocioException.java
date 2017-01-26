package co.com.datatools.c2.excepciones;

import javax.ejb.ApplicationException;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Excepcion para manejar errores de negocio catalogado de Circulemos
 * 
 * @author luis.martinez
 * 
 */
@ApplicationException(rollback = true)
public class CirculemosNegocioException extends Exception {

    private static final String MSG_ERROR_REQUERIDO = "La excepcion debe contener el error catalogado";
    private static final long serialVersionUID = 1L;

    /**
     * Objeto con la informacion de error
     */
    ErrorInfo errorInfo;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public CirculemosNegocioException(ErrorInfo errorInfo) {
        super(errorInfo != null ? (errorInfo.getCodigoError() + " - " + errorInfo.getDescError()) : "Unknow");
        this.errorInfo = errorInfo;
        if (this.errorInfo == null) {
            throw new CirculemosIllegalArgumentException(MSG_ERROR_REQUERIDO);
        }
    }

    public CirculemosNegocioException(ErrorInfo errorInfo, String contextualInfo) {
        super(errorInfo != null ? (errorInfo.getCodigoError() + " - " + errorInfo.getDescError()) : "Unknow" + "[ "
                + contextualInfo + " ]");
        this.errorInfo = errorInfo;
        if (this.errorInfo == null) {
            throw new CirculemosIllegalArgumentException(MSG_ERROR_REQUERIDO);
        }
    }

    @Deprecated
    public CirculemosNegocioException(String message) {
        super(message);
    }

}
