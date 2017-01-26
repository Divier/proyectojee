package co.com.datatools.c2.managed_bean.comparendo.administracion.agente;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteInconsistenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumTipoInconsistenciaAgente;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAgente;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * Manejo del proceso de Anulación
 * 
 * @author ricardo.chavarro
 * 
 */
@ManagedBean
@SessionScoped
public class AgenteMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AgenteMB.class.getName());

    private static final String NOMBRE_BUNDLE_ACTUALIZAR_AGENTE = "labelActualizarAgente";

    private int diasMaximo;
    private Date fechaActualizacionDiasMaximo;

    @EJB
    IRComparendo comparendoEJB;

    @EJB
    IRAgente agenteEJB;

    @EJB
    IRParametro parametroEJB;

    @PostConstruct
    public void init() {
        logger.debug("AgenteMB::init()");
        ValorParametroDTO cantMaximoDiasConsultaAnulacion = parametroEJB.consultarParametro(
                EnumParametro.DIAS_CONSULTA_COMPARENDOS_SIN_AGENTE_VALIDO, getCodigoOrganismoTransito(), true);

        diasMaximo = cantMaximoDiasConsultaAnulacion.getValorParamInt();
    }

    public void calcularRangoFechaActualizacion() {
        logger.debug("AnulacionMB::calcularRangoConsultaFechaActualizacionAgente()");

        AgenteHolderFL agenteHolderFL = findFlowObject(AgenteHolderFL.class, AgenteHolderFL.NOMBRE_BEAN);
        fechaActualizacionDiasMaximo = UtilFecha.sumarDias(agenteHolderFL.getFechaInicialImposicion(), diasMaximo);
    }

    // private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelAnulacion";
    public void consultar() {
        logger.debug("AgenteMB::consultar()");
        AgenteHolderFL agenteHolderFL = findFlowObject(AgenteHolderFL.class, AgenteHolderFL.NOMBRE_BEAN);
        agenteHolderFL.getComparendosResultado().clear();

        try {
            EnumTipoInconsistenciaAgente inconsistencia = EnumTipoInconsistenciaAgente
                    .obtenerPorCodigo(agenteHolderFL.getItemInconsistencia());
            Date fechaInicialImposision = agenteHolderFL.getFechaInicialImposicion();
            Date fechaFinalImposision = agenteHolderFL.getFechaFinalImposicion();
            if (fechaInicialImposision.after(fechaFinalImposision)) {

                getFacesContext().addMessage("form-contenido:idFechaInicialImposicion",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(NOMBRE_BUNDLE_ACTUALIZAR_AGENTE).getString("msg_fecha_incial")));
                return;
            }

            if (fechaFinalImposision.before(fechaInicialImposision)) {

                getFacesContext().addMessage("form-contenido:idFechaFinalImposicion",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(NOMBRE_BUNDLE_ACTUALIZAR_AGENTE).getString("msg_fecha_final")));
                return;
            }
            List<ComparendoAgenteInconsistenteDTO> comparendos = comparendoEJB.consultarComparendosIncosistenteAgente(
                    inconsistencia, fechaInicialImposision, fechaFinalImposision);
            if (comparendos != null && !comparendos.isEmpty()) {
                agenteHolderFL.setComparendosResultado(comparendos);
                CirculemosAccesoBundleGeneral
                        .addMensajeResultadoConsulta(agenteHolderFL.getComparendosResultado().size());
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Actualiza los agente de los comaprendos consultados
     */
    public void actualizarAgentes() {
        AgenteHolderFL agenteHolderFL = findFlowObject(AgenteHolderFL.class, AgenteHolderFL.NOMBRE_BEAN);
        AgenteFL agenteFL = findFlowObject(AgenteFL.class, AgenteFL.NOMBRE_BEAN);

        List<ComparendoDTO> comparendos = new ArrayList<>();
        for (ComparendoAgenteInconsistenteDTO compInconsistente : agenteHolderFL.getComparendosResultado()) {
            ComparendoDTO comparendo = new ComparendoDTO();
            comparendo.setCicomparendo(compInconsistente.getCicomparendo());
            comparendo.setFechaInfraccion(compInconsistente.getFechaInfraccion());

            comparendos.add(comparendo);
        }

        try {
            ArrayList<Integer> cantidadDeRegistrosAfectados = comparendoEJB
                    .actualizarAgenteComparendoMasivo(comparendos, agenteFL.getAgenteDTO());

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('popUpEditarAgenteComparendo').hide();");
            context.execute("PF('popUpConfirmarActu').show();");
            agenteHolderFL.setConfirmarActu(true);
            agenteHolderFL.setMensajeConfirmar("");
            agenteHolderFL.setMensajeNoConfirmar("");
            agenteHolderFL.setMensajeConfirmar(MessageFormat.format(
                    getBundle(NOMBRE_BUNDLE_ACTUALIZAR_AGENTE).getString("msg_cantidad_resultado_exitoso"),
                    cantidadDeRegistrosAfectados.get(0)));
            if (cantidadDeRegistrosAfectados.get(1) > 0) {
                agenteHolderFL.setMensajeNoConfirmar(MessageFormat.format(
                        getBundle(NOMBRE_BUNDLE_ACTUALIZAR_AGENTE).getString("msg_cantidad_resultado_no_exitoso"),
                        cantidadDeRegistrosAfectados.get(1)));
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Cierra la ventana de confirmacion
     */
    public void confirmacionActualizarComparendo() {
        AgenteHolderFL agenteHolderFL = findFlowObject(AgenteHolderFL.class, AgenteHolderFL.NOMBRE_BEAN);

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('popUpConfirmarActu').hide();");

        agenteHolderFL.setConfirmarActu(false);
        consultar();
    }

    public void consultarAgente() {
        AgenteFL agenteFL = findFlowObject(AgenteFL.class, AgenteFL.NOMBRE_BEAN);

        if (!agenteFL.getPlacaAgente().matches(getPatternCodigoAgente())) {

            getFacesContext().addMessage("formEditarAgenteComparendos:txtPlacaAgente",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(NOMBRE_BUNDLE_ACTUALIZAR_AGENTE).getString("msg_error_placa")));
            return;
        }

        AgenteDTO agentetDto = new AgenteDTO();

        agenteFL.setAgenteDTO(null);
        agentetDto.setPlaca(agenteFL.getPlacaAgente());

        agenteFL.setNombreAgente("");
        try {
            AgenteDTO agente = agenteEJB.consultarAgenteValido(agentetDto);
            agenteFL.setAgenteDTO(agente);
            agenteFL.setNombreAgente(agente.getPersona().getNombreCompleto());
            agenteFL.setPlacaAgente(null);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Concatena la fecha y hora del comparendo
     * 
     * @param comparendoDTO
     *            comparnedo a concatenar datos de fecha y hora imposicion del comparendo.
     * @author ricardo.chavarro
     */
    public Date concatenarHoraFechaInfraccion(ComparendoAgenteInconsistenteDTO comparendoDTO) {
        return UtilFecha.setHoraFecha(comparendoDTO.getFechaInfraccion(), comparendoDTO.getHoraInfraccion());
    }

    public Date getFechaActual() {
        logger.debug("AnulacionMB::getFechaActual()");
        return UtilFecha.currentZeroTimeDate();
    }

    public Date getFechaActualizacionDiasMaximo() {
        logger.debug("AnulacionMB::getFechaAnulacionDiasMaximo()");
        return fechaActualizacionDiasMaximo;
    }

    public void limpiarAgente(Integer opcion) {
        logger.debug("AnulacionMB::getFechaAnulacionDiasMaximo()");

        AgenteFL agenteFL = findFlowObject(AgenteFL.class, AgenteFL.NOMBRE_BEAN);
        if (opcion != null) {
            agenteFL.setPlacaAgente(null);
        }
        agenteFL.setAgenteDTO(null);
        agenteFL.setNombreAgente(null);
    }

    public String getPatternCodigoAgente() {
        return ExpresionesRegulares.REGEX_ALFANUMERICO_OBLIGATORIO;
    }

}
