package co.com.datatools.seguridad.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto.EstadoAutenticacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.web.mb.AutenticacionBean;

/**
 * SessionListener que invoca el metodo de negocio que cierra el ingreso del usuario en el evento sessionDestroyed
 * 
 * @author claudia.rodriguez
 * 
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = Logger.getLogger(SessionListener.class.getName());

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.debug("Crea sesion:" + event.getSession().getId());
        event.getSession().setAttribute("seguridadUtilMB", new SeguridadUtilMB());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.debug("SessionListener::sessionDestroyed: " + event.getSession().getId());
        AutenticacionBean autenticacionBean = (AutenticacionBean) event.getSession().getAttribute(
                ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN);
        if (autenticacionBean != null && autenticacionBean.getResultadoAutenticacion() != null) {
            if (autenticacionBean.getResultadoAutenticacion().getEstadoAutenticacion().equals(EstadoAutenticacion.OK)) {
                logger.debug("Se encuentra bean de autenticacion, se solicita cierre de sesion");
                autenticacionBean.cerrarSession();
                logger.debug("Destruye sesion:" + event.getSession().getId());
            }
        }

    }
}