package co.com.datatools.c2.managed_bean.administracion.eventos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EventoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IREvento;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.util.date.UtilFecha;

/**
 * Managed Bean para manipulación administración de personas CU_CIR20_UBI_002
 * 
 * @author Dixon.Alvarez
 * 
 */
@ManagedBean
@SessionScoped
public class AdministracionEventosMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AdministracionEventosMB.class.getName());

    private static final String BUNDLE_PERSONA = "labelAdminEventos";

    @EJB
    private IREvento iREvento;

    public void init() {
        logger.debug(AdministracionEventosMB.class.getName().concat("init()"));
        // inicializar();
    }

    public void consultar() {
        logger.debug(AdministracionEventosMB.class.getName().concat("consultar()"));
        AdministracionEventosHolderFL administracionEventosHolderFL = findFlowObject(
                AdministracionEventosHolderFL.class, AdministracionEventosHolderFL.NOMBRE_BEAN);
        List<EventoDTO> eventos = iREvento.consultarEvento(administracionEventosHolderFL.getEventoConsulta());
        administracionEventosHolderFL.setResultadoConsulta(eventos);

        if (eventos == null || eventos.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        }
    }

    public boolean guardar() {
        logger.debug(AdministracionEventosMB.class.getName().concat("guardar()"));
        AdministracionEventosHolderFL administracionEventosHolderFL = findFlowObject(
                AdministracionEventosHolderFL.class, AdministracionEventosHolderFL.NOMBRE_BEAN);

        // Valida la olbigatoriedad de la fecha del evento
        if (administracionEventosHolderFL.getEventoCrea().getFecha() == null) {
            getFacesContext().addMessage("form-contenido:fechaEvento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return false;
        }

        // Valida que la fecha no sea menor a la fecha actual
        if (administracionEventosHolderFL.getEventoCrea().getFecha().before(fechaActual())) {
            getFacesContext().addMessage("form-contenido:fechaEvento", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(BUNDLE_PERSONA).getString("msg_fecha_menor")));
            return false;
        }

        // Valida la obligatoriedad de la hora inicial del evento
        if (administracionEventosHolderFL.getEventoCrea().getHoraInicio() == null) {
            getFacesContext().addMessage("form-contenido:horaIniEvento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return false;
        }

        // Valida la obligatoriedad de la hora final del evento
        if (administracionEventosHolderFL.getEventoCrea().getHoraFin() == null) {
            getFacesContext().addMessage("form-contenido:horaFinEvento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return false;
        }

        // Valida si existe ya un evento con este nombre
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setNombreEvento(administracionEventosHolderFL.getEventoCrea().getNombreEvento());
        List<EventoDTO> eventos = iREvento.consultarEvento(eventoDTO);

        if (!eventos.isEmpty() && eventos != null) {
            addInfoMessage(BUNDLE_PERSONA, "msg_existe_evento");
            return false;
        }

        try {
            iREvento.registrarEvento(administracionEventosHolderFL.getEventoCrea());
        } catch (CirculemosNegocioException cx) {
            return false;
        }
        administracionEventosHolderFL.inicializar();
        CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
        return true;
    }

    public String getHoraString(Date fecha) {
        return Utilidades.hourToStringFormatApp(fecha);
    }

    public Date fechaActual() {
        return UtilFecha.currentZeroTimeDate();
    }
}
