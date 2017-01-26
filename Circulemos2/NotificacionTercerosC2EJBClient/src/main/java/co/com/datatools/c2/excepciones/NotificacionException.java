package co.com.datatools.c2.excepciones;

import javax.ejb.ApplicationException;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Excepcion para manejar errores de negocio catalogado de Notificaciones
 * 
 * @author divier.casas
 * 
 */
@ApplicationException(rollback = true)
public class NotificacionException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Objeto con la informacion de error
     */
    ErrorInfo errorInfo;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public NotificacionException(ErrorInfo errorInfo) {
        super(errorInfo != null ? (errorInfo.getCodigoError() + " - " + errorInfo.getDescError()) : "Unknow");
        this.errorInfo = errorInfo;
    }

    public NotificacionException(String message) {
        super(message);
    }
}