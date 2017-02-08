package co.com.datatools.c2.managed_bean.administracion.notificacion.notificacion_proceso;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.NotificacionProcesoDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.dto.eventos.DiaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.maganed_bean.persona.datos_demograficos.DatosDemograficosHolderFL;
import co.com.datatools.c2.negocio.interfaces.IRCargo;
import co.com.datatools.c2.negocio.interfaces.IRNotificacion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Manejo proceso de impugnacion
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class NotificacionProcesoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(NotificacionProcesoMB.class.getName());

    private static final String NOMBRE_BUNDLE = "labelNotificacion";

    @EJB
    IRNotificacion iRnotificacion;

    public void consultar() {
        LOGGER.debug("NotificacionProcesoMB::consultar()");
        NotificacionProcesoHolderFL notificacionProcesoHolderFL = findFlowObject(NotificacionProcesoHolderFL.class,
                NotificacionProcesoHolderFL.NOMBRE_BEAN);

        notificacionProcesoHolderFL.setNotificaciones(new ArrayList<NotificacionProcesoDTO>());

        notificacionProcesoHolderFL.setNotificaciones(
                iRnotificacion.consultaNotificacionProcesos(notificacionProcesoHolderFL.getNotificacionProcesoDTO()));
    }

    public void enviarCorreo() {
        LOGGER.debug("NotificacionProcesoMB::enviarCorreo()");
        NotificacionProcesoHolderFL notificacionProcesoHolderFL = findFlowObject(NotificacionProcesoHolderFL.class,
                NotificacionProcesoHolderFL.NOMBRE_BEAN);

        iRnotificacion.prepararNotificacion(notificacionProcesoHolderFL.getNotificaciones());

        CirculemosAccesoBundleGeneral
                .addMensajeResultadoConsulta(notificacionProcesoHolderFL.getNotificaciones().size());

    }

}
