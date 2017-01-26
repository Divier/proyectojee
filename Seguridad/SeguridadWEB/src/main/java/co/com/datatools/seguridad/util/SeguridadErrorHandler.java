package co.com.datatools.seguridad.util;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import co.com.datatools.seguridad.excepciones.ErrorWrapper;
import co.com.datatools.seguridad.excepciones.SeguridadException;

/**
 * Clase encargada de procesar todos los bundle de negocio y facilitar la presentacion de los mensajes de error derivados de
 * {@link SeguridadException}
 * 
 * @author Felipe Martinez
 */
public class SeguridadErrorHandler {

    private SeguridadErrorHandler() {

    }

    /**
     * Procesa la informacion del ErrorInfo y despliega el mensaje internacionlizado para el codigo del error
     * 
     * @param errInfo
     *            informacion del error
     */
    public static void handleError(ErrorWrapper errInfo) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        Locale locale = null;
        if (fc.getViewRoot() != null)
            locale = fc.getViewRoot().getLocale();

        fc.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "", findAppObj(InicioAppSeguridad.class,
                        InicioAppSeguridad.NOMBRE_BEAN).getResourceBundle(locale).getString(errInfo.getCodigoError())));
    }

    /**
     * Procesa la informacion del ErrorInfo dentro de la excepcion de negocio y despliega el mensaje internacionlizado para el codigo del error
     * 
     * @param exc
     *            excepcion con la informacion del error
     */
    public static void handleException(SeguridadException exc) {
        handleError(exc.getErrorInfo());
    }

    @SuppressWarnings("unchecked")
    private static <T> T findAppObj(Class<T> objType, String name) {
        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        final Object attribute = context.getAttribute(name);
        if (attribute != null) {
            return (T) attribute;
        }
        return null;
    }
}
