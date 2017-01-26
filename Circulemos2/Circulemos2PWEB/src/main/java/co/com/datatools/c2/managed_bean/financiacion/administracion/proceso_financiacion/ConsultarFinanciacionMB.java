package co.com.datatools.c2.managed_bean.financiacion.administracion.proceso_financiacion;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.managed_bean.administracion.calendario.DiaNoHabilFL;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;

/**
 * Se encarga de consultar las financiaciones
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultarFinanciacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ConsultarFinanciacionMB.class.getName());

    @EJB
    private IRFinanciacion iRFinanciacion;
    @EJB
    private IRPersona iRPersona;

    /**
     * Cosnulta las financiaciones dependiendo de los filtros ingresados
     * 
     * @author giovanni.velandia
     */
    public void consultarFinanciaciones() {
        LOGGER.debug("ConsultarFinanciacionMB::consultarFinanciaciones()");
        ConsultaFinanciacionHolderFL consultaFinanciacionHolderFL = findFlowObject(ConsultaFinanciacionHolderFL.class,
                ConsultaFinanciacionHolderFL.NOMBRE_BEAN);
        // limpiar persona
        consultaFinanciacionHolderFL.setPersonaFiltroDTO(new PersonaDTO());
        consultaFinanciacionHolderFL.setConsultaFinanciacionSelecDTO(null);
        consultaFinanciacionHolderFL.setDetalle(false);

        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DiaNoHabilFL.NOMBRE_BEAN);
        consultaFinanciacionHolderFL.getFiltroConsultaFinanciacionDTO()
                .setAnoFinanciacion(diaNoHabilFL.getAnioSeleccionado());

        consultaFinanciacionHolderFL.setConsultaFinanciacionDTOs(iRFinanciacion
                .consultarProcesoFinanciacion(consultaFinanciacionHolderFL.getFiltroConsultaFinanciacionDTO()));

        if (diaNoHabilFL.getAnioSeleccionado() > Calendar.getInstance().getWeekYear()) {
            CirculemosAccesoBundleGeneral.addMensajeAnoMayorActual();
            return;
        } else {
            consultarPersona();
        }

        if (consultaFinanciacionHolderFL.getConsultaFinanciacionDTOs() == null
                || consultaFinanciacionHolderFL.getConsultaFinanciacionDTOs().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(consultaFinanciacionHolderFL.getConsultaFinanciacionDTOs().size());
        }
    }

    /**
     * Se encarga de habilitar las opciones para la financiacion
     * 
     * @author giovanni.velandia
     */
    public void opcionesFinanciacion() {
        ConsultaFinanciacionHolderFL consultaFinanciacionHolderFL = findFlowObject(ConsultaFinanciacionHolderFL.class,
                ConsultaFinanciacionHolderFL.NOMBRE_BEAN);
        if (consultaFinanciacionHolderFL.getConsultaFinanciacionSelecDTO() != null) {
            consultaFinanciacionHolderFL.setDetalle(true);
        } else {
            consultaFinanciacionHolderFL.setDetalle(false);
        }
    }

    /**
     * Metodo que se encarga de realizar la consulta de la persona en los filtros
     * 
     * @author giovanni.velandia
     */
    public void consultarPersona() {
        LOGGER.debug("ConsultarFinanciacionMB::consultarPersona()");
        ConsultaFinanciacionHolderFL consultaFinanciacionHolderFL = findFlowObject(ConsultaFinanciacionHolderFL.class,
                ConsultaFinanciacionHolderFL.NOMBRE_BEAN);

        consultaFinanciacionHolderFL.setPersonaFiltroDTO(iRPersona.consultarPersona(getCodigoOrganismoTransito(),
                consultaFinanciacionHolderFL.getFiltroConsultaFinanciacionDTO().getIdTipoIdentificacion(),
                consultaFinanciacionHolderFL.getFiltroConsultaFinanciacionDTO().getNumeroIdentificacion()));
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

}
