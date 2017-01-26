package co.com.datatools.c2.web.util;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import co.com.datatools.c2.error.ErrorInfo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral.MensajesGeneral;

/**
 * Clase encargada de procesar todos los bundle de negocio y facilitar la presentacion de los mensajes de error derivados de
 * {@link CirculemosNegocioException}
 * 
 * @author felipe.martinez
 */
public class CirculemosErrorHandler extends AbstractC2ManagedBean {

    private static final long serialVersionUID = -7333361147075607954L;

    private static final CirculemosErrorHandler instance = new CirculemosErrorHandler();

    /**
     * Procesa la informacion del ErrorInfo y despliega el mensaje internacionlizado para el codigo del error
     * 
     * @param errInfo
     *            informacion del error
     */
    public static void handleError(ErrorInfo errInfo) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        Locale locale = null;
        if (fc.getViewRoot() != null)
            locale = fc.getViewRoot().getLocale();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                CirculemosAccesoBundleGeneral.getStringGeneral(MensajesGeneral.title_transaction_ko),
                instance.findApplicationObject(InicioAppCirculemos.class, ConstantesManagedBean.NOMBRE_MB_INICO_APP)
                        .getResourceBundle(locale).getString(errInfo.getCodigoError())));
    }

    /**
     * Procesa la informacion del ErrorInfo y despliega el mensaje internacionalizado para el codigo del error de acuerdo al nivel de severidad
     * indicado
     * 
     * @param errInfo
     *            informacion del error
     * @param severity
     *            severidad del mensaje
     */
    public static void handleError(ErrorInfo errInfo, Severity severity) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        Locale locale = null;
        if (fc.getViewRoot() != null)
            locale = fc.getViewRoot().getLocale();
        fc.addMessage(null, new FacesMessage(severity,
                CirculemosAccesoBundleGeneral.getStringGeneral(MensajesGeneral.title_transaction_ko),
                instance.findApplicationObject(InicioAppCirculemos.class, ConstantesManagedBean.NOMBRE_MB_INICO_APP)
                        .getResourceBundle(locale).getString(errInfo.getCodigoError())));
    }

    /**
     * Procesa la informacion del ErrorInfo dentro de la excepcion de negocio y despliega el mensaje internacionlizado para el codigo del error
     * 
     * @param exc
     *            excepcion con la informacion del error
     */
    public static void handleException(CirculemosNegocioException exc) {
        handleError(exc.getErrorInfo());
    }

    /**
     * Procesa la informacion del ErrorInfo dentro de la excepcion de negocio y despliega el mensaje internacionlizado para el codigo del error de
     * acuerdo al nivel de severidad indicado
     * 
     * @param exc
     *            excepcion con la informacion del error
     * @param severity
     *            severidad del mensaje
     */
    public static void handleException(CirculemosNegocioException exc, Severity severity) {
        handleError(exc.getErrorInfo(), severity);
    }

    /**
     * Procesa la informacion del ErrorInfo dentro de la excepcion de negocio alertada y despliega el mensaje internacionlizado para el codigo del
     * error
     * 
     * @param exc
     *            excepcion con la informacion del error
     */
    public static void handleException(CirculemosAlertaException exc) {
        handleError(exc.getErrorInfo());
    }

}
