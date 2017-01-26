package co.com.datatools.c2.web.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto.EstadoAutenticacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.web.mb.AutenticacionBean;

/**
 * Esta clase sirve como punto central para inicializar los parametros de la session e interceptar el sessionDestroyed de HttpSession
 * 
 * @author felipe.martinez
 * 
 */
@WebListener
public class InicioSesionListener implements HttpSessionListener {

    private final static Logger logger = Logger.getLogger(InicioSesionListener.class.getName());

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.debug("InicioSesionListener::sessionCreated");

        se.getSession().setAttribute("circulemosUtilMB", new CirculemosUtilMB());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.debug("InicioSesionListener::sessionDestroyed");
        AutenticacionBean autenticacionBean = (AutenticacionBean) event.getSession().getAttribute(
                ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN);
        if (autenticacionBean != null && autenticacionBean.getResultadoAutenticacion() != null) {
            if (autenticacionBean.getResultadoAutenticacion().getEstadoAutenticacion().equals(EstadoAutenticacion.OK)) {
                autenticacionBean.cerrarSession();
                logger.debug("Destruye sesion:" + event.getSession().getId());
            }
        }
    }

}
