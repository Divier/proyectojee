package co.com.datatools.c2.managed_bean.financiacion.administracion.proceso_financiacion;

import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.util.date.UtilFecha;

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

    @PostConstruct
    public void init() {
        LOGGER.debug("ConsultarFinanciacionMB::init()");
        calcAniosSogit();
    }

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

        consultaFinanciacionHolderFL.setConsultaFinanciacionDTOs(iRFinanciacion
                .consultarProcesoFinanciacion(consultaFinanciacionHolderFL.getFiltroConsultaFinanciacionDTO()));

        // Consulta persona
        consultarPersona();

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

    /**
     * Metodo para la consulta de sogit, ya que se requiere que sea desde el inicio del sistema que es 2015 y que el año sea actual
     * 
     * @author giovanni.velandia
     */
    public void calcAniosSogit() {
        LOGGER.debug("ConsultarFinanciacionMB::calcAniosSogit()");
        ConsultaFinanciacionHolderFL consultaFinanciacionHolderFL = findFlowObject(ConsultaFinanciacionHolderFL.class,
                ConsultaFinanciacionHolderFL.NOMBRE_BEAN);

        /**
         * Extraccion de fecha
         */
        Calendar calendar = Calendar.getInstance();
        calendar = UtilFecha.resetTime(calendar);

        int anioSeleccionadoActual = calendar.get(Calendar.YEAR);
        int anioSeleccionadoInicioSistema = 2015;

        consultaFinanciacionHolderFL.setAnios(new ArrayList<SelectItem>());
        for (int i = anioSeleccionadoInicioSistema; i <= anioSeleccionadoActual; i++) {
            consultaFinanciacionHolderFL.getAnios().add(new SelectItem(i, String.valueOf(i)));
        }
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }
}
