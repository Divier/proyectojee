/**
 * 
 */
package co.com.datatools.c2.excepciones;

import javax.ejb.ApplicationException;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Excepcion manejada para flujos generados por un determinado error definido en el caso de uso que no generan un rollback y reciben un mensaje
 * 
 * @author luis.forero 2015-04-28
 * 
 */
@ApplicationException(rollback = false)
public class CirculemosAlertaException extends Exception {
    private static final String MSG_ERROR_REQUERIDO = "La excepcion debe contener el error catalogado";
    private static final long serialVersionUID = 1L;

    /**
     * Objeto con la informacion de error
     */
    ErrorInfo errorInfo;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public CirculemosAlertaException(ErrorInfo errorInfo) {
        super(errorInfo != null ? (errorInfo.getCodigoError() + " - " + errorInfo.getDescError()) : "Unknow");
        this.errorInfo = errorInfo;
        if (this.errorInfo == null) {
            throw new CirculemosIllegalArgumentException(MSG_ERROR_REQUERIDO);
        }
    }

    public CirculemosAlertaException(ErrorInfo errorInfo, String contextualInfo) {
        super(errorInfo != null ? (errorInfo.getCodigoError() + " - " + errorInfo.getDescError()) : "Unknow" + "[ "
                + contextualInfo + " ]");
        this.errorInfo = errorInfo;
        if (this.errorInfo == null) {
            throw new CirculemosIllegalArgumentException(MSG_ERROR_REQUERIDO);
        }
    }
}
