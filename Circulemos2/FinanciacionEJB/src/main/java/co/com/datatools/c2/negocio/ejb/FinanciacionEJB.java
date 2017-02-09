package co.com.datatools.c2.negocio.ejb;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import com.google.common.base.Preconditions;

import co.com.datatools.c2.adaptador.cartera.IRCarteraFinanciacion;
import co.com.datatools.c2.adaptador.comparendo.IRComparendoFinanciacion;
import co.com.datatools.c2.adaptador.dto.CarteraFinanciacionDTO;
import co.com.datatools.c2.adaptador.dto.RegistroCarteraFinanciacionDTO;
import co.com.datatools.c2.adaptador.dto.TasaInteresDTO;
import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.CapturaFirmaDTO;
import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultaObligacionesFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.DejarFirmeDTO;
import co.com.datatools.c2.dto.DejarFirmeMetaDataDTO;
import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.FiltroConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.InconsistenciaDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaFinanciacionDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.RespuestaFinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaReplicarFinanciacionDTO;
import co.com.datatools.c2.dto.SimulacionFinanciacionDTO;
import co.com.datatools.c2.dto.TipoDocumentoProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.ValidaPagoFinanciacionDTO;
import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.VariableCondicionFinanDTO;
import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.entidades.DetalleFinanciacion;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.ObligacionFinanciacion;
import co.com.datatools.c2.entidades.ParticipanteProceso;
import co.com.datatools.c2.entidades.TipoParticipante;
import co.com.datatools.c2.entidades.VariableCondicionFinan;
import co.com.datatools.c2.enumeracion.EnumClaseInteres;
import co.com.datatools.c2.enumeracion.EnumEstadoTransaccion;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoNotificacion;
import co.com.datatools.c2.enumeracion.financiacion.EnumErrorInconsistenciaFinanciacion;
import co.com.datatools.c2.enumeracion.financiacion.EnumVariableCondicionFinanciacion;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion.EnumErroDocumentoFinanciacion;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion.EnumErrorSimulacionFinanciacion;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion.EnumErroresDejarFirmeFinanciacion;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion.EnumErroresResultadoFinanciacion;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.helpers.DetalleFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.FinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ObligacionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ReplicarFinanciacionTercerosHelper;
import co.com.datatools.c2.negocio.helpers.VariableCondicionFinanHelper;
import co.com.datatools.c2.negocio.helpers.extend.FinanciacionHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionFinanciacion;
import co.com.datatools.c2.negocio.interfaces.ILFinanciacion;
import co.com.datatools.c2.negocio.interfaces.ILInconsistenciaFinanciacion;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRClienteWSAXIS;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRFirma;
import co.com.datatools.c2.negocio.interfaces.IRUbicabilidad;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.negocio.util.InconsistenciaFinanciacionUtil;
import co.com.datatools.c2.negocio.util.log.LogReplicarFinanciacion;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.mail.EmailValidator;

@Stateless(mappedName = "FinanciacionEJB")
@LocalBean
public class FinanciacionEJB implements IRFinanciacion, ILFinanciacion {

    private static final Logger logger = Logger.getLogger(FinanciacionEJB.class);

    private static final ILog loggerReplicar = LoggerC2.getLogger(EnumLogSistema.REPLICAR_FINANCIACIONES_DETALLE);

    @EJB
    private IRFachadaIntegracionTerceros iRFachadaIntegracionTerceros;
    @EJB
    private IRFachadaConfiguracion fachadaConfiguracionEJB;
    @EJB
    private IRFachadaAdminGeneral iFachadaAdminGeneral;
    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;
    @EJB
    private ILFinanciacion financiacionEJB;
    @EJB
    private IRCarteraFinanciacion iRCarteraFinanciacion;
    @EJB
    private IRFachadaAdminNegocio iFachadaAdminNegocio;
    @EJB
    private IRComparendoFinanciacion iRComparendoFinanciacion;
    @EJB
    private IRAdministracionFinanciacion irAdministracionFinanciacion;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private ILAdministracionFinanciacion administracionFinanciacionEJB;

    @EJB
    private IRFachadaProceso iRFachadaProceso;

    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;

    @EJB
    private ILFinanciacion iLFinanciacion;

    @EJB
    private ILInconsistenciaFinanciacion iLinconsistenciaFinanciacion;
    @EJB

    private IRClienteWSAXIS clienteWSAXISEJB;
    @EJB

    private IRFirma iRfirma;
    @EJB
    private IRUbicabilidad iRUbicabilidad;

    @EJB
    private IRComparendo iRComparendo;

    @EJB
    private IRCarteraContable iRCartera;

    @EJB
    private IRCoactivo iRCoactivo;

    @EJB
    private IRFachadaNotificacionTerceros iRFachadaNotificaciones;

    private static final String VALOR_NO_MAPEADO = "VALOR NO MAPEADO :";
    private static final String UNIQUE_EXCEPTION = "org.hibernate.exception.ConstraintViolationException";
    private static final String NOMBRE_ARCHIVO = "CONVENIO_POR_MULTAS_DE_TRANSITO_AMORTIZACION_MENSUAL.pdf";
    private static final Integer PRIMER_CUOTA = 0;
    private static final Integer ESCALA = 2;
    private static final Integer ESCALA_PORCENTAJE = 16;
    private static final BigDecimal CIEN = BigDecimal.valueOf(100.0);
    private static final BigDecimal CENTESIMA = new BigDecimal("0.01");

    @PersistenceContext(name = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<ConsultaFinanciacionDTO> consultarProcesoFinanciacion(
            FiltroConsultaFinanciacionDTO filtroConsultaFinanciacionDTO) {
        logger.debug("FinanciacionEJB.consultarProcesoFinanciacion()");
        List<ConsultaFinanciacionDTO> consultaFinanciacionDTOs = null;
        FinanciacionDTO financiacionFiltrosDTO = new FinanciacionDTO();

        // Año
        if (filtroConsultaFinanciacionDTO.getAnoFinanciacion() != null
                && filtroConsultaFinanciacionDTO.getAnoFinanciacion() > 0) {
            financiacionFiltrosDTO.setAnio(filtroConsultaFinanciacionDTO.getAnoFinanciacion());
        }
        // Numero de financiacion
        if (filtroConsultaFinanciacionDTO.getNumeroFinanciacion() != null) {
            financiacionFiltrosDTO.setNumeroFinanciacion(filtroConsultaFinanciacionDTO.getNumeroFinanciacion());
        }
        // Persona deudor
        if (filtroConsultaFinanciacionDTO.getIdTipoIdentificacion() != null
                && filtroConsultaFinanciacionDTO.getNumeroIdentificacion() != null) {
            PersonaDTO personaDTO = new PersonaDTO();
            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            tipoIdentificacionPersonaDTO.setId(filtroConsultaFinanciacionDTO.getIdTipoIdentificacion());
            personaDTO.setTipoIdentificacion(tipoIdentificacionPersonaDTO);
            personaDTO.setNumeroIdentificacion(filtroConsultaFinanciacionDTO.getNumeroIdentificacion());
            financiacionFiltrosDTO.setDeudor(personaDTO);
        }
        List<FinanciacionDTO> financiacionDTOs = consultarFinanciacion(financiacionFiltrosDTO);

        if (financiacionDTOs != null && !financiacionDTOs.isEmpty()) {
            consultaFinanciacionDTOs = new ArrayList<ConsultaFinanciacionDTO>();
            for (FinanciacionDTO financiacionDTO : financiacionDTOs) {
                ConsultaFinanciacionDTO consultaFinanciacionDTO = FinanciacionHelperExtend
                        .toConsultaFinanciacion(financiacionDTO);
                EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso
                        .consultarEstadoProceso(financiacionDTO.getProceso().getId());

                // Estado finaciacion
                if (estadoProcesoDTO != null) {
                    consultaFinanciacionDTO.setEstadoFinaciacion(estadoProcesoDTO.getNombre());
                    consultaFinanciacionDTO.setIdEstadoFinanciacion(estadoProcesoDTO.getId());
                }
                // valor saldo a pagar
                CarteraFinanciacionDTO cartera = iRCarteraFinanciacion
                        .consultarCarteraFinanciacion(financiacionDTO.getNumeroFinanciacion());
                if (cartera != null) {
                    consultaFinanciacionDTO
                            .setSaldoPagar(cartera.getValorSaldoCapital().add(cartera.getValorSaldoInteres()));
                } else {
                    consultaFinanciacionDTO.setSaldoPagar(financiacionDTO.getValorTotalFinanciarInteres());
                }

                consultaFinanciacionDTOs.add(consultaFinanciacionDTO);
            }
        }
        return consultaFinanciacionDTOs;
    }

    @Override
    public ConsultaFinanciacionDTO consultaDetalleFinanciacion(String numeroFinanciacion) {
        logger.debug("FinanciacionEJB.consultaDetalleFinanciacion()");
        FinanciacionDTO financiacionDTO = consultarFinanciacion(numeroFinanciacion);
        ConsultaFinanciacionDTO consultaFinanciacionDTO = FinanciacionHelperExtend
                .toConsultaDetalleFinanciacion(financiacionDTO);

        // Estado Proceso
        EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso
                .consultarEstadoProceso(financiacionDTO.getProceso().getId());

        List<PersonaDTO> personaDTOs = iFachadaAdminNegocio.consultarPersona(financiacionDTO.getDeudor());

        // Tipo Documento
        consultaFinanciacionDTO.setTipoDocumento(personaDTOs.get(0).getTipoIdentificacion().getNombre());

        // Estado finaciacion
        if (estadoProcesoDTO != null) {
            consultaFinanciacionDTO.setEstadoFinaciacion(estadoProcesoDTO.getNombre());
            consultaFinanciacionDTO.setIdEstadoFinanciacion(estadoProcesoDTO.getId());
        }

        // Obligaciones

        for (ConsultaObligacionesFinanciacionDTO consulta : consultaFinanciacionDTO
                .getConsultaObligacionesFinanciacionDTOs()) {
            String medioImposicion = iRComparendoFinanciacion.consultarMedioImposicionComparendo(
                    consulta.getNumeroObligacion(),
                    seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());
            if (medioImposicion != null) {
                consulta.setNombreTipoObligacion(medioImposicion);
            }
        }

        return consultaFinanciacionDTO;
    }

    @Override
    public List<FinanciacionDTO> consultarFinanciacion(FinanciacionDTO financiacionDTO) {
        logger.debug("FinanciacionEJB.consultarFinanciacion()");
        List<FinanciacionDTO> financiacionDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT f FROM Financiacion f");
        jpql.append(" WHERE 1=1");

        // Buscar por la persona deudor
        if (financiacionDTO.getDeudor() != null) {
            if (financiacionDTO.getDeudor().getTipoIdentificacion() != null
                    && financiacionDTO.getDeudor().getNumeroIdentificacion() != null) {
                jpql.append(" AND f.deudor.tipoIdentificacion.id = :idTipoIdentificacion");
                jpql.append(" AND f.deudor.numeroIdentificacion = :numeroIdentificacion");
            }
        }

        // Numero financiacion
        if (financiacionDTO.getNumeroFinanciacion() != null) {
            jpql.append(" AND f.numeroFinanciacion = :numeroFinanciacion");
        }

        // Año
        if (financiacionDTO.getAnio() != null && financiacionDTO.getAnio() != 0) {
            jpql.append(" AND f.anio = :anio");
        }

        Query query = em.createQuery(jpql.toString());

        // Persona deudor
        if (financiacionDTO.getDeudor() != null) {
            if (financiacionDTO.getDeudor().getTipoIdentificacion().getId() != null
                    && financiacionDTO.getDeudor().getNumeroIdentificacion() != null) {
                query.setParameter("idTipoIdentificacion", financiacionDTO.getDeudor().getTipoIdentificacion().getId());
                query.setParameter("numeroIdentificacion", financiacionDTO.getDeudor().getNumeroIdentificacion());
            }
        }

        // Numero financiacion
        if (financiacionDTO.getNumeroFinanciacion() != null) {
            query.setParameter("numeroFinanciacion", financiacionDTO.getNumeroFinanciacion());
        }

        // Año
        if (financiacionDTO.getAnio() != null && financiacionDTO.getAnio() != 0) {
            query.setParameter("anio", financiacionDTO.getAnio());
        }

        @SuppressWarnings("unchecked")
        List<Financiacion> financiacions = query.getResultList();
        if (financiacions != null && !financiacions.isEmpty()) {
            financiacionDTOs = FinanciacionHelper.toListLevel1DTO(financiacions);
        }
        return financiacionDTOs;
    }

    @Override
    public FinanciacionDTO consultarFinanciacion(String numeroFinanciacion) {
        logger.debug("FinanciacionEJB.consultarFinanciacion(String)");

        Preconditions.checkNotNull(numeroFinanciacion, "Se requiere parametro de numero de financiacion");
        FinanciacionDTO financiacionDTO = null;

        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT f FROM Financiacion f");
        jpql.append(" JOIN f.proceso p");
        jpql.append(" LEFT JOIN f.detallesFinanciacion d");
        jpql.append(" LEFT JOIN f.obligacionesFinanciacion o");
        jpql.append(" LEFT JOIN f.financiacionCarteraList c");
        jpql.append(" WHERE f.numeroFinanciacion = :numeroFinanciacion");

        final Map<String, Object> params = new HashMap<>();
        params.put("numeroFinanciacion", numeroFinanciacion);

        final GenericDao<Financiacion> dao = new GenericDao<>(Financiacion.class, em);
        final List<Financiacion> financiaciones = dao.buildAndExecuteQuery(jpql.toString(), params);
        if (!financiaciones.isEmpty()) {
            Financiacion financiacion = financiaciones.get(0);
            financiacionDTO = FinanciacionHelperExtend.toLevel2DTO(financiacion);
        }

        return financiacionDTO;
    }

    @Override
    public FinanciacionDTO registrarFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.registrarFinanciacion(FinanciacionDTO)");

        // Crea proceso de financiacion
        ProcesoDTO proceso = registrarProcesoFinanciacion(financiacionDTO.getFuenteInformacion().getId(),
                financiacionDTO.getFechaFinanciacion());
        financiacionDTO.setProceso(proceso);
        financiacionDTO.setNumeroReferenciaTerceros(proceso.getNumeroProceso());
        // Calcular numero de financiacion
        financiacionDTO = calcularNumeroFinanciacion(financiacionDTO);

        // Crea los participantes del proceso
        crearParticipanteFinanciacion(financiacionDTO.getDeudor(), proceso);

        // Persiste la financiacion
        Financiacion financiacion = FinanciacionHelper.toLevel1Entity(financiacionDTO, null);
        em.persist(financiacion);
        financiacionDTO.setId(financiacion.getId());

        // Guarda el detalle de las cuotas de la financiacion
        registrarDetalleFinanciacion(financiacionDTO.getDetallesFinanciacion(), financiacionDTO);

        // Guarda el detalle de las obligaciones de la financiacion
        registrarDetalleObligaciones(financiacionDTO.getObligacionesFinanciacion(), financiacionDTO);

        // Si es generado por AXIS realiza ajuste a cartera
        ajustesCarteraAxis(financiacionDTO);

        return financiacionDTO;
    }

    /**
     * Calcula el numero de finnciacion
     * 
     * @author giovanni.velandia
     * @param financiacionDTO
     * @return
     */
    private FinanciacionDTO calcularNumeroFinanciacion(FinanciacionDTO financiacionDTO)
            throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.calcularNumeroFinanciacion(FinanciacionDTO)");
        if (EnumTipoFuenteInformacion.CIRCULEMOS2.getValue().equals(financiacionDTO.getFuenteInformacion().getId())) {
            // Valida si debe registrar la financiacion en axis
            ValorParametroDTO parametroFinanciacionTerceros = iFachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.REGISTRAR_FINANCIACION_TERCEROS,
                    financiacionDTO.getOrganismoTransito().getCodigoOrganismo(), true);

            if (parametroFinanciacionTerceros != null && parametroFinanciacionTerceros.getValorParamBoolean()) {
                // Enviar financiacion a AXIS
                financiacionDTO = clienteWSAXISEJB.registrarFinanciacion(financiacionDTO);
            } else if (StringUtils.isBlank(financiacionDTO.getNumeroFinanciacion())) {
                financiacionDTO.setNumeroFinanciacion(financiacionDTO.getNumeroReferenciaTerceros());
            }
        }

        return financiacionDTO;
    }

    /**
     * Ajustes de cartera de es de Axis la financiacion
     * 
     * @author giovanni.velandia
     * @param financiacionDTO
     * @throws CirculemosNegocioException
     */
    private void ajustesCarteraAxis(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.ajustesCarteraAxis(FinanciacionDTO)");
        if (financiacionDTO.getFuenteInformacion().getId().equals(EnumTipoFuenteInformacion.AXIS.getValue())) {
            // Registra la cartera
            Long idCartera = registrarCarteraFinanciacion(financiacionDTO);

            // Activa la cartera
            iRCarteraFinanciacion.activarCarteraFinanciacion(idCartera, financiacionDTO.getProceso().getFechaInicio());
        } else if (financiacionDTO.getFuenteInformacion().getId()
                .equals(EnumTipoFuenteInformacion.CIRCULEMOS2.getValue())) {

            // Registra el moviemiento on los intereses
            iRCarteraFinanciacion.registrarMovimientoCartera(financiacionDTO);
        }
    }

    /**
     * Se encarga de crear un participante para una financiacion
     * 
     * @author giovanni.velandia
     */
    private void crearParticipanteFinanciacion(PersonaDTO personaDTO, ProcesoDTO procesoDTO) {
        logger.debug("FinanciacionEJB.crearParticipanteFinanciacion(PersonaDTO,ProcesoDTO)");
        ParticipanteProceso participante = new ParticipanteProceso();
        participante.setPersona(PersonaHelper.toLevel0Entity(personaDTO, null));
        participante.setTipoParticipante(em.find(TipoParticipante.class, EnumTipoParticipante.INFRACTOR.getValue()));
        participante.setProceso(ProcesoHelper.toLevel0Entity(procesoDTO, null));
        em.persist(participante);
    }

    /**
     * creacion de un proceso para una financiacion
     * 
     * @author giovanni.velandia
     */
    private ProcesoDTO registrarProcesoFinanciacion(Integer tipoFuenteInfo, Date fechaFinanciacion) {
        logger.debug("FinanciacionEJB.crearParticipanteFinanciacion(Integer,Date)");
        // Estado dependiendo de la fuente de informacion
        EnumEstadoProceso estadoFinanciacion = EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO;
        if (EnumTipoFuenteInformacion.AXIS.getValue().equals(tipoFuenteInfo)) {
            estadoFinanciacion = EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME;
        }

        RegistraProcesoDTO registro = new RegistraProcesoDTO();
        registro.setObservacion(EnumTipoProceso.FINANCIACION_COMPARENDO.name());
        registro.setTipoProceso(EnumTipoProceso.FINANCIACION_COMPARENDO);
        registro.setEstado(estadoFinanciacion);
        registro.setConsecutivo(EnumConsecutivo.NUMERO_FINANCIACION_ECUADOR);
        registro.setFechaInicio(fechaFinanciacion);
        return iRFachadaProceso.crearProceso(registro);
    }

    /**
     * // Guarda el detalle de las cuotas de la financiacion
     * 
     * @author giovanni.velandia
     */
    private void registrarDetalleFinanciacion(List<DetalleFinanciacionDTO> detalleFinanciacionDTOs,
            FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.crearParticipanteFinanciacion(List<DetalleFinanciacionDTO>,FinanciacionDTO)");
        try {
            List<DetalleFinanciacionDTO> detalles = Utilidades.safeList(detalleFinanciacionDTOs);

            for (DetalleFinanciacionDTO detalleFinanciacionDTO : detalles) {
                DetalleFinanciacion detalleFinanciacion = DetalleFinanciacionHelper
                        .toLevel1Entity(detalleFinanciacionDTO, null);
                detalleFinanciacion.setFinanciacion(FinanciacionHelper.toLevel0Entity(financiacionDTO, null));
                em.persist(detalleFinanciacion);
                detalleFinanciacionDTO.setId(detalleFinanciacion.getId());
            }
        } catch (PersistenceException e) {
            if (e.getMessage().contains(UNIQUE_EXCEPTION)) {
                throw new CirculemosNegocioException(ErrorFinanciacion.ValidarReglaNegocioFinanciacion.FIN_014_012);
            } else {
                throw e;
            }
        }
    }

    /**
     * Guarda el detalle de las obligaciones de la financiacion
     * 
     * @author giovanni.velandia
     * @param detalleFinanciacionDTOs
     * @param financiacionDTO
     * @throws CirculemosNegocioException
     */
    private void registrarDetalleObligaciones(List<ObligacionFinanciacionDTO> obligacionFinanciacionDTOs,
            FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.registrarDetalleObligaciones(List<ObligacionFinanciacionDTO>,FinanciacionDTO)");
        List<ObligacionFinanciacionDTO> obligaciones = Utilidades.safeList(obligacionFinanciacionDTOs);

        try {

            for (ObligacionFinanciacionDTO obligacionFinanciacionDTO : obligaciones) {
                // Persiste la obligacion
                ObligacionFinanciacion obligacionFinanciacion = ObligacionFinanciacionHelper
                        .toLevel1Entity(obligacionFinanciacionDTO, null);
                obligacionFinanciacion.setFinanciacion(FinanciacionHelper.toLevel0Entity(financiacionDTO, null));
                em.persist(obligacionFinanciacion);
                obligacionFinanciacionDTO.setId(obligacionFinanciacion.getId());

                // Si es generado por AXIS financia el comparendo
                if (financiacionDTO.getFuenteInformacion().getId().equals(EnumTipoFuenteInformacion.AXIS.getValue())) {
                    // Actualiza estados de comparendos a financiado y los asocia al proceso
                    iRComparendoFinanciacion.financiarComparendo(obligacionFinanciacionDTO.getNumeroObligacion(),
                            financiacionDTO.getOrganismoTransito().getCodigoOrganismo(), new Date(),
                            financiacionDTO.getProceso().getId());
                    // Cambia el estado de las obligaciones asociadas a financiadas
                    iRCarteraFinanciacion.financiarCarteraFinanciacion(obligacionFinanciacionDTO.getIdCartera(),
                            new Date());
                } else if (financiacionDTO.getFuenteInformacion().getId()
                        .equals(EnumTipoFuenteInformacion.CIRCULEMOS2.getValue())) {
                    // Si es generado por C2 prefinancia el comparendo
                    iRComparendoFinanciacion.preFinanciarComparendo(obligacionFinanciacionDTO.getNumeroObligacion(),
                            financiacionDTO.getOrganismoTransito().getCodigoOrganismo(), new Date(),
                            financiacionDTO.getProceso().getId());
                    // Cambia el estado de las obligaciones asociadas a prefinanciadas
                    iRCarteraFinanciacion.preFinanciarCarteraFinanciacion(obligacionFinanciacionDTO.getIdCartera(),
                            new Date());
                }
            }
        } catch (PersistenceException e) {
            if (e.getMessage().contains(UNIQUE_EXCEPTION)) {
                throw new CirculemosNegocioException(ErrorFinanciacion.ValidarReglaNegocioFinanciacion.FIN_014_013);
            } else {
                throw e;
            }
        }

    }

    @Override
    public Long registrarCarteraFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.registrarCarteraFinanciacion(FinanciacionDTO)");
        // Registra cartera
        RegistroCarteraFinanciacionDTO registroCarteraDTO = new RegistroCarteraFinanciacionDTO();
        if (financiacionDTO.getDeudor() != null) {
            registroCarteraDTO.setIdDeudor(financiacionDTO.getDeudor().getId());
        } else if (financiacionDTO.getCodeudor() != null) {
            registroCarteraDTO.setIdDeudor(financiacionDTO.getCodeudor().getId());
        }
        // Fecha Obligacion
        registroCarteraDTO.setFechaFinanciacion(financiacionDTO.getProceso().getFechaInicio());
        // Origen
        registroCarteraDTO.setOrigenObligacion(financiacionDTO.getOrganismoTransito().getCodigoOrganismo());
        // Referencia Obligacion
        registroCarteraDTO.setNumeroFinanciacion(financiacionDTO.getNumeroFinanciacion());
        // Saldo de la financiacion
        registroCarteraDTO.setValorFinanciacion(financiacionDTO.getValorTotalFinanciarInteres());
        // Id de la financiacion
        registroCarteraDTO.setIdFinanciacion(financiacionDTO.getId());
        // Registra cartera
        Long idCartera = iRCarteraFinanciacion.registrarCarteraFinanciacion(registroCarteraDTO);
        return idCartera;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaReplicarFinanciacionDTO replicarFinanciacionTerceros(Integer codigoOrganismo) {
        logger.debug("FinanciacionEJB.replicarFinanciacionTerceros(Integer)");
        RespuestaReplicarFinanciacionDTO respuesta = new RespuestaReplicarFinanciacionDTO();

        String loginUsuario = seguridadCirculemosEJB.obtenerUsuarioDto().getLogin();

        // Consulta los registros nuevos de financiaciones
        List<ItFinanciacionDTO> financiaciones = iRFachadaIntegracionTerceros.consultarFinanciaciones(codigoOrganismo,
                EnumEstadoLectura.NUEVO);
        respuesta.setTotalFinanciacionesLeidos(financiaciones.size());
        int totalFinanciacionesRecibidos = 0;
        int totalFinanciacionesNoRecibidos = 0;
        for (ItFinanciacionDTO financiacion : financiaciones) {
            EnumEstadoLectura estadoLectura = EnumEstadoLectura.NO_RECIBIDO;
            try {
                boolean recibido = financiacionEJB.recibirFinanciacionTerceros(financiacion, loginUsuario);
                if (recibido) {
                    totalFinanciacionesRecibidos++;
                    estadoLectura = EnumEstadoLectura.RECIBIDO;
                } else {
                    totalFinanciacionesNoRecibidos++;
                }
            } catch (Exception e) {
                logger.error("Error al guardar financiacion desde AXIS :", e);
                totalFinanciacionesNoRecibidos++;
            }
            // Actualiza la informacion de los recaudos
            iRFachadaIntegracionTerceros.actualizarEstadoFinanciacion(financiacion.getNumeroFinanciacion(),
                    estadoLectura);
        }
        respuesta.setTotalFinanciacionesRecibidos(totalFinanciacionesRecibidos);
        respuesta.setTotalFinanciacionesNoRecibidos(totalFinanciacionesNoRecibidos);
        return respuesta;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public boolean recibirFinanciacionTerceros(ItFinanciacionDTO itFinanciacion, String loginUsuario)
            throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.replicarFinanciacionTerceros(ItFinanciacionDTO, String)");
        boolean recibido = false;
        LogReplicarFinanciacion logReplicarFinanciacion = new LogReplicarFinanciacion(
                itFinanciacion.getNumeroFinanciacion() + "", null);
        try {
            InconsistenciaFinanciacionDTO inconsistenciaFinanciacionDTO = InconsistenciaFinanciacionUtil
                    .cargarInconsistenciaFinanciacion(itFinanciacion);

            List<EnumErrorInconsistenciaFinanciacion> inconsistenciasFinanciacion = new ArrayList<>();
            Map<InconsistenciaDetalleFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalles = new HashMap<>();
            Map<InconsistenciaDetalleCuotasFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalleCuotas = new HashMap<>();

            if (itFinanciacion.getDetalleCuotasFinanciacionList() == null
                    || itFinanciacion.getDetalleCuotasFinanciacionList().isEmpty()) {
                // Inconsistencia
                inconsistenciasFinanciacion
                        .add(EnumErrorInconsistenciaFinanciacion.FINANCIACION_DEBE_TENER_AL_MENOS_UNA_CUOTA);
            }

            if (itFinanciacion.getDetalleFinanciacionList() == null
                    || itFinanciacion.getDetalleFinanciacionList().isEmpty()) {
                // Inconsistencia
                inconsistenciasFinanciacion
                        .add(EnumErrorInconsistenciaFinanciacion.FINANCIACION_DEBE_TENER_AL_MENOS_UNA_OBLIGACION);
            }
            // Convertir recaudos de IT a pagos
            FinanciacionDTO financiacionDTO = ReplicarFinanciacionTercerosHelper.toFinanciacionDTO(itFinanciacion);

            // Coloca el anio
            Calendar fechaFinanciacion = Calendar.getInstance();
            fechaFinanciacion.setTime(itFinanciacion.getFechaFinanciacion());
            financiacionDTO.setAnio(fechaFinanciacion.get(Calendar.YEAR));

            // verificar si ya existe financiacion
            FinanciacionDTO financiacionDTOTemp = new FinanciacionDTO();
            financiacionDTOTemp = consultarFinanciacion(financiacionDTO.getNumeroFinanciacion());
            if (financiacionDTOTemp == null) {
                // Validar obligatoriedad datos
                validarDatosFinanciacion(itFinanciacion, inconsistenciasFinanciacion);

                // Homologacion de catalogos
                List<EnumCatalogo> catalogosError = homologarCatalogosReplicarFinanciacion(financiacionDTO,
                        itFinanciacion);
                if (catalogosError.isEmpty()) {
                    // Consultar persona codeudor, deudor
                    Long idDeudor = obtenerPersona(financiacionDTO.getDeudor(), itFinanciacion.getCodigoOrganismo());
                    financiacionDTO.getDeudor().setId(idDeudor);
                    if (financiacionDTO.getCodeudor() != null) {
                        idDeudor = obtenerPersona(financiacionDTO.getCodeudor(), itFinanciacion.getCodigoOrganismo());
                        financiacionDTO.getCodeudor().setId(idDeudor);
                    }
                    // Verifica la cartera de las obligaciones
                    Map<Integer, String> detalleObligacionFinanciacion = new HashMap<>();
                    for (int i = 0; i < financiacionDTO.getObligacionesFinanciacion().size(); i++) {

                        List<EnumErrorInconsistenciaFinanciacion> inconsistenciasdetalle = new ArrayList<>();

                        CarteraFinanciacionDTO cartera = iRCarteraFinanciacion.consultarCartera(
                                financiacionDTO.getObligacionesFinanciacion().get(i).getNumeroObligacion(),
                                financiacionDTO.getObligacionesFinanciacion().get(i).getCodigoTipoObligacion());
                        // Existe obligacion
                        if (cartera == null) {
                            // Inconsistencia
                            inconsistenciasdetalle.add(EnumErrorInconsistenciaFinanciacion.CARTERA_NO_EXISTE);
                            inconsistenciasDetalles.put(
                                    inconsistenciaFinanciacionDTO.getDetalleFinanciacionList().get(i),
                                    inconsistenciasdetalle);
                            break;
                        }
                        // Saldo obligacion conincide con valor de la obligacion
                        if (cartera.getValorSaldoCapital().add(cartera.getValorSaldoInteres()).compareTo(
                                financiacionDTO.getObligacionesFinanciacion().get(i).getValorObligacion()) != 0) {
                            // Inconsistencia
                            inconsistenciasdetalle.add(EnumErrorInconsistenciaFinanciacion.SALDO_CARTERA_NO_COINCIDE);
                        }
                        // Estado de la obligacion debe estar activo
                        if (!cartera.getEstado().getValue()
                                .equals(IRCarteraFinanciacion.EnumCarteraFinanciacion.OBLIGACION_ACTIVO.getValue())) {
                            // Inconsistencia
                            inconsistenciasdetalle
                                    .add(EnumErrorInconsistenciaFinanciacion.OBLIGACION_EN_OTRA_FINANCIACION_EN_FIRME);
                        }
                        financiacionDTO.getObligacionesFinanciacion().get(i).setIdCartera(cartera.getId());

                        if (!inconsistenciasdetalle.isEmpty()) {
                            inconsistenciasDetalles.put(
                                    inconsistenciaFinanciacionDTO.getDetalleFinanciacionList().get(i),
                                    inconsistenciasdetalle);
                        }
                        detalleObligacionFinanciacion.put(
                                financiacionDTO.getObligacionesFinanciacion().get(i).getCodigoTipoObligacion(),
                                financiacionDTO.getObligacionesFinanciacion().get(i).getNumeroObligacion());
                    }
                    List<Integer> cuotasFinanciacion = new ArrayList<>();
                    for (int i = 0; i < inconsistenciaFinanciacionDTO.getDetalleCuotasFinanciacionList().size(); i++) {
                        List<EnumErrorInconsistenciaFinanciacion> inconsistenciasDetalleCuotasList = new ArrayList<>();
                        if (cuotasFinanciacion.contains(inconsistenciaFinanciacionDTO.getDetalleCuotasFinanciacionList()
                                .get(i).getNumeroCuota())) {
                            inconsistenciasDetalleCuotasList.add(EnumErrorInconsistenciaFinanciacion.CUOTA_REPETIDA);

                            inconsistenciasDetalleCuotas.put(
                                    inconsistenciaFinanciacionDTO.getDetalleCuotasFinanciacionList().get(i),
                                    inconsistenciasDetalleCuotasList);
                        }
                        cuotasFinanciacion.add(inconsistenciaFinanciacionDTO.getDetalleCuotasFinanciacionList().get(i)
                                .getNumeroCuota());

                    }

                    if (inconsistenciasFinanciacion.isEmpty() && inconsistenciasDetalles.isEmpty()
                            && inconsistenciasDetalleCuotas.isEmpty()) {
                        // Crear financiacion
                        financiacionDTO = registrarFinanciacion(financiacionDTO);

                        recibido = true;
                        logReplicarFinanciacion.setEstadoTransaccion(EnumEstadoTransaccion.REGISTRADO.getValue());
                    }

                } else {
                    // Inconsistencia
                    // Cambia la transaccion del log
                    logReplicarFinanciacion.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
                    StringBuilder detalle = new StringBuilder(VALOR_NO_MAPEADO);
                    for (EnumCatalogo enumCatalogo : catalogosError) {
                        String valor = null;
                        if (enumCatalogo.equals(EnumCatalogo.TipoIdentificacionPersona)
                                && financiacionDTO.getDeudor() != null) {
                            valor = itFinanciacion.getCodigoTipoIdentificacionDeudor();
                        } else if (enumCatalogo.equals(EnumCatalogo.TipoIdentificacionPersona)
                                && financiacionDTO.getCodeudor() != null) {
                            valor = itFinanciacion.getCodigoTipoIdentificacionDeudor();
                        } else if (enumCatalogo.equals(EnumCatalogo.TipoObligacion)) {
                            valor = "";
                        }
                        detalle.append(enumCatalogo.toString() + ":" + valor + ", ");
                    }
                    logReplicarFinanciacion.setDetalle(detalle.toString());
                }
            } else {
                // Inconsistencia
                // La financiacion ya existe
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.FINANCIACION_YA_EXISTE);
                logReplicarFinanciacion.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
            }
            // Guarda las inconsistencias encontradas
            if (!inconsistenciasFinanciacion.isEmpty() || !inconsistenciasDetalles.isEmpty()
                    || !inconsistenciasDetalleCuotas.isEmpty()) {
                InconsistenciaFinanciacionDTO financiacionInconsistente = iLinconsistenciaFinanciacion
                        .registrarInconsistenciaFinanciacion(inconsistenciaFinanciacionDTO,
                                inconsistenciasFinanciacion);

                if (!inconsistenciasDetalles.isEmpty()) {
                    iLinconsistenciaFinanciacion.registrarInconsistenciaDetalleFinanciacion(inconsistenciasDetalles,
                            financiacionInconsistente);
                }

                if (!inconsistenciasDetalleCuotas.isEmpty()) {
                    iLinconsistenciaFinanciacion.registrarInconsistenciaDetalleCuotasFinanciacion(
                            inconsistenciasDetalleCuotas, financiacionInconsistente);
                }
                logReplicarFinanciacion.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
            }
        } catch (CirculemosNegocioException e) {
            // Inconsistencia
            logger.error("Error al procesar financiacion: " + e.getMessage());

            logReplicarFinanciacion.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
            logReplicarFinanciacion.setDetalle(e.getMessage());
            throw e;
        } catch (Exception e) {
            // Inconsistencia
            logger.error("Error al procesar financiacion:", e);

            logReplicarFinanciacion.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
            logReplicarFinanciacion.setDetalle(e.getMessage());
            throw new CirculemosNegocioException(ErrorFinanciacion.ValidarReglaNegocioFinanciacion.FIN_014_014);
        } finally {
            loggerReplicar.escribir(loginUsuario, logReplicarFinanciacion);
        }
        return recibido;
    }

    /**
     * Obtiene los datos de la persona registrandola o modificandola
     * 
     * @return Id de la persona
     * @author julio.pinzon 2016-05-18
     * @throws CirculemosNegocioException
     */
    private Long obtenerPersona(PersonaDTO persona, int codigoOrganismo) throws CirculemosNegocioException {
        // Consultar persona codeudor, deudor
        PersonaDTO deudor = iFachadaAdminNegocio.consultarPersona(codigoOrganismo,
                persona.getTipoIdentificacion().getId(), persona.getNumeroIdentificacion());
        Long idDeudor = 0L;
        if (deudor == null) {
            // Si no existe crear
            persona.setFuenteInformacion(
                    new TipoFuenteInformacionDTO(EnumTipoFuenteInformacion.FINANCIACION.getValue()));
            idDeudor = iFachadaAdminNegocio.registrarPersona(persona);
        } else {
            deudor.setApellido1(persona.getApellido1());
            if (StringUtils.isNotBlank(persona.getApellido2())) {
                deudor.setApellido2(persona.getApellido2());
            }
            deudor.setNombre1(persona.getNombre1());
            if (StringUtils.isNotBlank(persona.getNombre2())) {
                deudor.setNombre2(persona.getNombre2());
            }
            // TODO: CAMBIO DISENIO UBICABILIDAD
            // if (StringUtils.isNotBlank(persona.getCorreoElectronico())) {
            // deudor.setCorreoElectronico(persona.getCorreoElectronico());
            // }
            // if (StringUtils.isNotBlank(persona.getNumeroTelefonico())) {
            // deudor.setNumeroTelefonico(persona.getNumeroTelefonico());
            // }
            iFachadaAdminNegocio.modificarPersona(deudor);
            idDeudor = deudor.getId();
        }
        return idDeudor;
    }

    /**
     * Validacion de catalogos de financiacion
     * 
     * @param financiacionDTO
     * @param itfinanciacion
     * @return Lista de catalogos con error
     * @author julio.pinzon 2016-05-18
     */
    private List<EnumCatalogo> homologarCatalogosReplicarFinanciacion(FinanciacionDTO financiacionDTO,
            ItFinanciacionDTO itfinanciacion) {
        logger.debug("FinanciacionEJB.homologarCatalogosReplicarFinanciacion(FinanciacionDTO, ItFinanciacionDTO)");
        List<EnumCatalogo> catalogosError = new ArrayList<EnumCatalogo>();

        // **************************************
        // Homologacion de catalogos
        // **************************************

        // CODIGO TIPO DE IDENTIFICACION DEL DEUDOR
        if (StringUtils.isNotBlank(itfinanciacion.getCodigoTipoIdentificacionDeudor())) {
            Integer id = consultarIdCatalogo(EnumCatalogo.TipoIdentificacionPersona,
                    itfinanciacion.getCodigoTipoIdentificacionDeudor());
            if (id != null) {
                financiacionDTO.getDeudor().getTipoIdentificacion().setId(id);
            } else {
                financiacionDTO.setDeudor(null);
                catalogosError.add(EnumCatalogo.TipoIdentificacionPersona);
            }
        }

        // CODIGO TIPO DE IDENTIFICACION DEL coDEUDOR
        if (StringUtils.isNotBlank(itfinanciacion.getCodigoTipoIdentificacionCodeudor())) {
            Integer id = consultarIdCatalogo(EnumCatalogo.TipoIdentificacionPersona,
                    itfinanciacion.getCodigoTipoIdentificacionCodeudor());
            if (id != null) {
                financiacionDTO.getCodeudor().getTipoIdentificacion().setId(id);
            } else {
                financiacionDTO.setCodeudor(null);
                catalogosError.add(EnumCatalogo.TipoIdentificacionPersona);
            }
        }

        int index = 0;
        for (ObligacionFinanciacionDTO detalle : financiacionDTO.getObligacionesFinanciacion()) {
            // CODIGO TIPO DE OBLIGACION
            if (StringUtils.isNotBlank(itfinanciacion.getDetalleFinanciacionList().get(index).getTipoObligacion())) {
                Integer id = consultarIdCatalogo(EnumCatalogo.TipoObligacion,
                        itfinanciacion.getDetalleFinanciacionList().get(index).getTipoObligacion());
                if (id != null) {
                    detalle.setCodigoTipoObligacion(id);
                } else {
                    catalogosError.add(EnumCatalogo.TipoObligacion);
                }
            }
            index++;
        }

        return catalogosError;

    }

    /**
     * Consulta el id de un determinado item de catalogos desde modulos externos.
     * 
     * @param catalogo
     *            Enumeracion del catalogo a buscar
     * @param codigo
     *            el codigo del item del catalogo a consultar
     * @return id o identificador del catalogo ingresado.
     * @author julio.pinzon (2016-05-05)
     */
    private Integer consultarIdCatalogo(EnumCatalogo catalogo, String codigo) {
        logger.debug("RecaudoEJB.consultarIdCatalogo(ItRecaudoDTO, String)");
        ItemCatalogoDTO item = new ItemCatalogoDTO();
        item.setCodigo(codigo);
        item.setActivo(true);
        List<ItemCatalogoDTO> result = fachadaConfiguracionEJB.consultarItemsCatalogo(catalogo.toString(), item);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0).getId();
    }

    private void validarDatosFinanciacion(ItFinanciacionDTO itFinanciacionDTO,
            List<EnumErrorInconsistenciaFinanciacion> inconsistenciasFinanciacion) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB::validarDatosFinanciacion(ItFinanciacionDTO)");
        if (itFinanciacionDTO != null) {
            if (StringUtils.isBlank(itFinanciacionDTO.getCodigoTipoIdentificacionDeudor())) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.CAMPO_TIPO_DOCUMENTO_DEUDOR_VACIO);
            }
            ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.PAIS_INSTALACION, itFinanciacionDTO.getCodigoOrganismo(), true);

            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaJuridica = iFachadaAdminNegocio
                    .consultarTipoIdentificacionPersonaJuridica(valorParametroDTO.getValorParamInt());
            if (itFinanciacionDTO.getCodigoTipoIdentificacionDeudor()
                    .equals(tipoIdentificacionPersonaJuridica.getCodigo())
                    && StringUtils.isBlank(itFinanciacionDTO.getRazonSocial())) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.CAMPO_RAZON_SOCIAL_NECESARIO);
            }
            if (StringUtils.isBlank(itFinanciacionDTO.getNumeroIdentificacionDeudor())) {
                inconsistenciasFinanciacion
                        .add(EnumErrorInconsistenciaFinanciacion.CAMPO_NUMERO_DOCUMENTO_DEUDOR_VACIO);
            }
            if (!itFinanciacionDTO.getCodigoTipoIdentificacionDeudor()
                    .equals(tipoIdentificacionPersonaJuridica.getCodigo())
                    && StringUtils.isBlank(itFinanciacionDTO.getPrimerNombreDeudor())) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.CAMPO_PRIMER_NOMBRE_DEUDOR_VACIO);
            }
            if (!itFinanciacionDTO.getCodigoTipoIdentificacionDeudor()
                    .equals(tipoIdentificacionPersonaJuridica.getCodigo())
                    && StringUtils.isBlank(itFinanciacionDTO.getPrimerApellidoDeudor())) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.CAMPO_PRIMER_APELLIDO_DEUDOR_VACIO);
            }
            if (StringUtils.isNotBlank(itFinanciacionDTO.getEmailDeudor())
                    && !EmailValidator.validate(itFinanciacionDTO.getEmailDeudor())) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.FORMATO_CORREO_DEUDOR_INVALIDO);
            }
            if (StringUtils.isNotBlank(itFinanciacionDTO.getEmailCodeudor())
                    && !EmailValidator.validate(itFinanciacionDTO.getEmailCodeudor())) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.FORMATO_CORREO_CODEUDOR_INVALIDO);
            }
            if (StringUtils.isNotBlank(itFinanciacionDTO.getTelefonoDeudor()) && !(itFinanciacionDTO.getTelefonoDeudor()
                    .matches(ExpresionesRegulares.REGEX_NUMERICO_AL_MENOS_SIETE))) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.FORMATO_TELEFONO_DEUDOR_INVALIDO);
            }
            if (StringUtils.isNotBlank(itFinanciacionDTO.getTelefonoCodeudor()) && !(itFinanciacionDTO
                    .getTelefonoCodeudor().matches(ExpresionesRegulares.REGEX_NUMERICO_AL_MENOS_SIETE))) {
                inconsistenciasFinanciacion.add(EnumErrorInconsistenciaFinanciacion.FORMATO_CORREO_CODEUDOR_INVALIDO);
            }
        }
    }

    @Override
    public List<VariableCondicionFinanDTO> consultarVariablesCondicionFinanciacion(
            CondicionFinanciacionDTO pCondicionFinanciacionDTO) throws CirculemosNegocioException {
        List<VariableCondicionFinanDTO> lstVariablesCondicionFinanDTO = new ArrayList<VariableCondicionFinanDTO>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT vcf FROM VariableCondicionFinan vcf");
        sql.append(" WHERE vcf.condicionFinanciacion.codigo = :pCodCondicion");
        sql.append(" ORDER BY vcf.orden ASC");

        TypedQuery<VariableCondicionFinan> query = em.createQuery(sql.toString(), VariableCondicionFinan.class);
        query.setParameter("pCodCondicion", pCondicionFinanciacionDTO.getCodigo());

        List<VariableCondicionFinan> lstResultados = query.getResultList();
        if (!lstResultados.isEmpty()) {
            lstVariablesCondicionFinanDTO = VariableCondicionFinanHelper.toListLevel1DTO(lstResultados);
        }

        return lstVariablesCondicionFinanDTO;
    }

    @Override
    public SimulacionFinanciacionDTO calcularSimulacionFinanciacion(SimulacionFinanciacionDTO simulacionFinanciacionDTO)
            throws CirculemosNegocioException {
        logger.debug(
                FinanciacionEJB.class.getName().concat("::calcularSimulacionFinanciacion(SimulacionFinanciacionDTO)"));
        // Configuracion de financiacion vigente
        if (simulacionFinanciacionDTO.getConfiguracionFinanciacion() == null) {
            ConfiguracionFinanciacionDTO pFiltroConfesFinanDTO = new ConfiguracionFinanciacionDTO();
            pFiltroConfesFinanDTO.setOrganismoTransito(seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario());

            List<ConfiguracionFinanciacionDTO> lstConfVigentes = administracionFinanciacionEJB
                    .consultarConfiguracionFinanciacion(pFiltroConfesFinanDTO, true);
            if (lstConfVigentes.isEmpty()) {
                throw new CirculemosNegocioException(EnumErrorSimulacionFinanciacion.FIN_018_001);
            }
            simulacionFinanciacionDTO.setConfiguracionFinanciacion(lstConfVigentes.get(0));
        }

        // Se lleva a cabo la extraccion de los intereses de financiacion
        Date fechaActual = UtilFecha.currentZeroTimeDate();
        if (simulacionFinanciacionDTO.getInteresFinanciacion() == null) {
            TasaInteresDTO tasaInteresVigente = iRCarteraFinanciacion
                    .consultarTasaInteresVigente(EnumClaseInteres.FINANCIACIONES.getValue(), fechaActual);
            if (tasaInteresVigente == null || tasaInteresVigente.getPorcentajeTasaInteres() == null) {
                throw new CirculemosNegocioException(EnumErrorSimulacionFinanciacion.FIN_018_006);
            }
            // NOTA: Valor se encuentra ya el porcentaje es decir para 20% el valor es 0.20
            simulacionFinanciacionDTO.setInteresFinanciacion(tasaInteresVigente.getPorcentajeTasaInteres());
        }

        // Calculo de valores de encabezado de la Financiacion
        BigDecimal valorTotal = BigDecimal.ZERO;
        if (simulacionFinanciacionDTO.getValorTotalFinanciarInteres() == null) {
            if (simulacionFinanciacionDTO.getObligacionesInfractor() != null) {
                BigDecimal valorTotalObligaciones = BigDecimal.ZERO;
                BigDecimal valorTotalIntereses = BigDecimal.ZERO;
                BigDecimal valorTotalCostas = BigDecimal.ZERO;
                for (ConsultaObligacionesDTO obligacionDTO : simulacionFinanciacionDTO.getObligacionesInfractor()) {
                    valorTotalObligaciones = valorTotalObligaciones.add(obligacionDTO.getSaldoObligacion());
                    valorTotalIntereses = valorTotalIntereses.add(obligacionDTO.getValorRecargo());
                    valorTotalCostas = valorTotalCostas.add(obligacionDTO.getValorCostaProcesal());

                    ObligacionFinanciacionDTO obligacionFinanciacionDTO = new ObligacionFinanciacionDTO();
                    obligacionFinanciacionDTO.setCodigoTipoObligacion(obligacionDTO.getIdTipoObligacion());
                    obligacionFinanciacionDTO.setFechaObligacion(obligacionDTO.getFechaObligacion());
                    obligacionFinanciacionDTO.setNumeroObligacion(obligacionDTO.getNumeroObligacion());
                    obligacionFinanciacionDTO.setValorCostasProcesales(obligacionDTO.getValorCostaProcesal());
                    obligacionFinanciacionDTO.setValorInteresMoratorios(obligacionDTO.getValorRecargo());
                    obligacionFinanciacionDTO.setValorObligacion(obligacionDTO.getSaldoObligacion());
                    obligacionFinanciacionDTO.setIdCartera(obligacionDTO.getIdCartera());
                    simulacionFinanciacionDTO.getObligacionesFinanciacion().add(obligacionFinanciacionDTO);
                }
                simulacionFinanciacionDTO.setValorTotalInteresesMoratorios(valorTotalIntereses);
                simulacionFinanciacionDTO.setValorTotalCostasProcesales(valorTotalCostas);
                simulacionFinanciacionDTO.setValorTotalSaldoCapitalObligaciones(valorTotalObligaciones);

                valorTotal = valorTotalCostas.add(valorTotalIntereses).add(valorTotalObligaciones);

                simulacionFinanciacionDTO.setValorTotal(valorTotal.setScale(ESCALA, BigDecimal.ROUND_HALF_UP));
                // BigDecimal interesFinanciacion = valorTotal
                // .multiply(simulacionFinanciacionDTO.getInteresFinanciacion());

                // simulacionFinanciacionDTO.setValorTotalFinanciarInteres(valorTotal.add(interesFinanciacion).setScale(
                // ESCALA, BigDecimal.ROUND_HALF_UP));
            }
        }

        // Extraccion de condiciones para calculo de cuotas de la configuracion vigente
        // Cantidad de cuotas
        if (simulacionFinanciacionDTO.getCantidadCuotasAplica() == null) {
            DetalleCantidadCuotaDTO cantidadCuotas = getCantidadCuotas(
                    simulacionFinanciacionDTO.getConfiguracionFinanciacion(),
                    simulacionFinanciacionDTO.getValorTotal());
            if (cantidadCuotas == null) {
                throw new CirculemosNegocioException(EnumErrorSimulacionFinanciacion.FIN_018_002);
            }
            simulacionFinanciacionDTO.setCantidadCuotasAplica(cantidadCuotas);
            simulacionFinanciacionDTO.setNumeroCuotas(cantidadCuotas.getCantidadMaximaCouta());
        }

        // Porcentaje de cuota inicial, se encuentra el valor sin dividir por 100
        if (simulacionFinanciacionDTO.getPorcentajeCuotaInicialAplica() == null) {
            DetallePorcentajeCuotaIniciDTO porcentajeCuotaInicial = getPorcentajeCuotaInicial(
                    simulacionFinanciacionDTO.getConfiguracionFinanciacion(),
                    simulacionFinanciacionDTO.getValorTotal());
            if (porcentajeCuotaInicial == null) {
                throw new CirculemosNegocioException(EnumErrorSimulacionFinanciacion.FIN_018_003);
            }
            simulacionFinanciacionDTO.setPorcentajeCuotaInicialAplica(porcentajeCuotaInicial);
        }

        // Creacion y distribucion de cuotas
        BigDecimal porcentajePrimeraCuotaAplica = simulacionFinanciacionDTO.getPorcentajeCuotaInicialAplica()
                .getPorcentajeCuotaInicial().divide(CIEN, ESCALA_PORCENTAJE, BigDecimal.ROUND_HALF_UP);
        BigDecimal valorPrimeraCuota = BigDecimal.ZERO;
        BigDecimal valorMinimo = simulacionFinanciacionDTO.getValorTotal().multiply(porcentajePrimeraCuotaAplica);

        Boolean incluirInteresesPrimerPago = Boolean.valueOf(true);
        if (simulacionFinanciacionDTO.getConfiguracionFinanciacion() != null && simulacionFinanciacionDTO
                .getConfiguracionFinanciacion().getLstValorCondicionFinanciacion() != null) {
            for (ValorCondicionFinanciacionDTO valor : simulacionFinanciacionDTO.getConfiguracionFinanciacion()
                    .getLstValorCondicionFinanciacion()) {
                if (valor.getVariableCondicionFinan().getId()
                        .intValue() == EnumVariableCondicionFinanciacion.INCLUIR_INTERESES_OBLIGACIONES.getValue()) {
                    incluirInteresesPrimerPago = Boolean.valueOf(valor.getValor());
                    break;
                }
            }
        }

        if (incluirInteresesPrimerPago) {
            if (valorMinimo.compareTo(simulacionFinanciacionDTO.getValorTotalInteresesMoratorios()) == -1) {
                valorMinimo = BigDecimal
                        .valueOf(simulacionFinanciacionDTO.getValorTotalInteresesMoratorios().doubleValue());
            }
        }

        if (simulacionFinanciacionDTO.getDetallesFinanciacion().isEmpty()) {
            valorPrimeraCuota = valorMinimo;
        } else {
            valorPrimeraCuota = simulacionFinanciacionDTO.getValorPrimeraCuota();
        }

        if (valorPrimeraCuota.compareTo(valorMinimo) < 0) {
            throw new CirculemosNegocioException(EnumErrorSimulacionFinanciacion.FIN_018_004);
        }
        simulacionFinanciacionDTO.setValorPrimeraCuota(valorPrimeraCuota);

        Integer codigoOrganismo = seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo();
        int cantDiasPermPagoCuotaIni = iFachadaAdminGeneral
                .consultarValorParametro(EnumParametro.CANT_DIAS_PERMITIR_PAGO_CUOTA_INICIAL, codigoOrganismo, true)
                .getValorParamInt();

        // Calculo de la fecha de pago oportuna para la primera cuota
        Date fecPagOportunoCuotaInicial = iFachadaAdminGeneral.sumarDias(codigoOrganismo, fechaActual,
                cantDiasPermPagoCuotaIni, true);
        while (iFachadaAdminGeneral.esDiaNoHabil(codigoOrganismo, fecPagOportunoCuotaInicial)) {
            fecPagOportunoCuotaInicial = UtilFecha.sumarDias(fecPagOportunoCuotaInicial, 1);
        }

        // Calcula los valores de las cuotas
        calcularCuotasSimulacion(simulacionFinanciacionDTO, fecPagOportunoCuotaInicial, codigoOrganismo, valorMinimo);

        return simulacionFinanciacionDTO;
    }

    /**
     * Calcula la fecha de pago oportuna de una determinada cuota.
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito sobre el cual se lleva a cabo el calculo
     * @param fecPagOportunoCuoIni
     *            fecha oportuna de la primera cuota
     * @param cuota
     *            permite identificar cuantos meses tiene que sumar a la fecha
     * @return retorna la fecha para la cuota teniendo en cuenta los dias no habiles.
     */
    private Date calcularFechaPagoCuota(int codigoOrganismo, Date fecPagOportunoCuoIni, int cuota) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecPagOportunoCuoIni);
        calendar.add(Calendar.MONTH, cuota);

        Date fechaCuota = calendar.getTime();
        int i = 1;
        if (UtilFecha.monthFromDate(fechaCuota) < UtilFecha.monthFromDate(UtilFecha.sumarDias(fechaCuota, i))) {
            i = -1;
        }
        while (iFachadaAdminGeneral.esDiaNoHabil(codigoOrganismo, fechaCuota)) {
            fechaCuota = UtilFecha.sumarDias(fechaCuota, i);
        }

        return fechaCuota;

    }

    /**
     * Construye la cuota con base a unos valores
     * 
     * @param valorCuotaCapital
     *            volor correspondiente al salado a capital de la cuota.
     * @param porcFinanciacion
     *            porcentaje de la financiacion para llevar a cabo el calculo del valor que aplica a los intereses de la financiacion.
     * @param fechaOportunaPago
     *            fecha en que deberia pagarse la cuota
     * @param numeroCuota
     *            corresponde al numero de la cuota
     * @return nueva instancia de un detalle de financiacion o cuota.
     * @author luis.forero
     */
    private static DetalleFinanciacionDTO construirCuota(BigDecimal valorCuotaCapital, BigDecimal porcFinanciacion,
            Date fechaOportunaPago, int numeroCuota) {
        BigDecimal valorIntereses = valorCuotaCapital.multiply(porcFinanciacion);
        DetalleFinanciacionDTO detalleFinanciacionDTO = new DetalleFinanciacionDTO();
        detalleFinanciacionDTO.setFechaPagoOportuno(fechaOportunaPago);
        detalleFinanciacionDTO.setNumeroCuota(numeroCuota);
        detalleFinanciacionDTO.setValorIntereses(valorIntereses.setScale(ESCALA, BigDecimal.ROUND_HALF_UP));
        detalleFinanciacionDTO.setValorCapital(valorCuotaCapital.setScale(ESCALA, BigDecimal.ROUND_HALF_UP));
        detalleFinanciacionDTO.setValorTotal(
                detalleFinanciacionDTO.getValorIntereses().add(detalleFinanciacionDTO.getValorCapital()));
        return detalleFinanciacionDTO;
    }

    /**
     * Permite identificar con cual rango y que numero de cuotas se ingresa la financiacion.
     * 
     * @param configuracionFinanciacion
     *            configuracion vigente de donde se extrae el dato correspondiente
     * @param valorAFinanciar
     *            valor total que se va a financiar con intereses
     * @return el rango de cuotas configurado para el valor ingresado
     * @author luis.forero
     */
    private static DetalleCantidadCuotaDTO getCantidadCuotas(ConfiguracionFinanciacionDTO configuracionFinanciacion,
            BigDecimal valorAFinanciar) {
        for (DetalleCantidadCuotaDTO cantidadCuotaDTO : configuracionFinanciacion.getLstDetalleCantidadCuota()) {
            if (valorAFinanciar.compareTo(cantidadCuotaDTO.getValorMinimoFinanciar()) >= 0
                    && valorAFinanciar.compareTo(cantidadCuotaDTO.getValorMaximoFinanciar()) <= 0) {
                return cantidadCuotaDTO;
            }
        }
        return null;
    }

    /**
     * Permite identificar con cual rango se ingresa la financiación.
     * 
     * @param configuracionFinanciacion
     *            configuracion de donde se extrae el dato correspondiente
     * @param valorAFinanciar
     *            valor total que se va a financiar con intereses
     * @return El porcentaje correspondiente al rango ingresado
     * @author luis.forero
     */
    private static DetallePorcentajeCuotaIniciDTO getPorcentajeCuotaInicial(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO, BigDecimal valorAFinanciar) {
        if (configuracionFinanciacionDTO != null
                && configuracionFinanciacionDTO.getLstDetallePorcentajeCuotaInici() != null) {
            for (DetallePorcentajeCuotaIniciDTO porcCuotaIniDTO : configuracionFinanciacionDTO
                    .getLstDetallePorcentajeCuotaInici()) {
                if (valorAFinanciar.compareTo(porcCuotaIniDTO.getValorMinimoFinanciar()) >= 0
                        && valorAFinanciar.compareTo(porcCuotaIniDTO.getValorMaximoFinanciar()) <= 0) {
                    return porcCuotaIniDTO;
                }
            }
        }
        return null;
    }

    @Override
    public RespuestaFinanciacionDTO registrarSimulacionFinanciacion(FinanciacionDTO financiacionDTO)
            throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.registrarSimulacionFinanciacion(FinanciacionDTO)");
        RespuestaFinanciacionDTO respuesta = null;
        try {
            respuesta = irAdministracionFinanciacion.validarFinanciacion(financiacionDTO);
            if (respuesta.getCodigoError()
                    .equals(EnumErroresResultadoFinanciacion.RESULTADO_VALIDACION_EXITOSO.getValue())) {
                // persiste la financiacion
                financiacionDTO.setFuenteInformacion(
                        new TipoFuenteInformacionDTO(EnumTipoFuenteInformacion.CIRCULEMOS2.getValue()));
                financiacionDTO
                        .setConfiguracionFinanciacion(respuesta.getFinanciacion().getConfiguracionFinanciacion());
                financiacionDTO = registrarFinanciacion(financiacionDTO);
                em.flush();

                GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
                Map<String, Object> valoresVistaPreliminar = new HashMap<>();

                // Se guarda la firma de la persona
                if (financiacionDTO.getFirma() != null) {
                    CapturaFirmaDTO capturaFirma = new CapturaFirmaDTO();
                    capturaFirma.setPersonaDTO(financiacionDTO.getDeudor());
                    capturaFirma.setFirma(financiacionDTO.getFirma());
                    Long numeroFirma = iRfirma.registrarFirma(capturaFirma, true);
                    valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
                }
                // Generacion documento acuerdo de pago
                generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
                generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.ACUERDO_PAGO);
                Object[] valoresParametros = { financiacionDTO.getId() };
                generaDocumento.setValoresParametros(valoresParametros);
                // generaAcuerdoPagoDTO.setIdPersona(financiacionDTO.getDeudor().getId());
                Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);
                respuesta.setIdDocumento(idDocumento);

                // persistir el documento generado
                TrazabilidadProcesoDTO trazabilidad = new TrazabilidadProcesoDTO();
                trazabilidad.setProceso(financiacionDTO.getProceso());
                List<TrazabilidadProcesoDTO> trazas = iRFachadaProceso.consultarTrazabilidad(trazabilidad);
                DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
                documentoProceso.setNumeroDocumento(idDocumento);
                documentoProceso.setTrazabilidadProceso(trazas.get(0));
                TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
                tipoDocumento.setId(EnumTipoDocumentoProceso.FINANCIACION_ACUERDO_PAGO.getValue());
                documentoProceso.setTipoDocumento(tipoDocumento);
                documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);

                // generacion de documento para primera cuota

                DetalleFinanciacionDTO detalle = financiacionDTO.getDetallesFinanciacion().get(0);
                GeneraDocumentoDTO generaAcuerdoPagoCuotaDTO = new GeneraDocumentoDTO();
                valoresVistaPreliminar = new HashMap<>();
                valoresVistaPreliminar.put(ConstantesDocumentosC2.FECHA_SOLICITUD, UtilFecha.buildCalendar().getTime());
                // obtiene el consecutivo correspondiente al numero de volante
                valoresVistaPreliminar.put(ConstantesDocumentosC2.NUMERO_VOLANTE,
                        Long.valueOf(iFachadaAdminGeneral.generarConsecutivo(
                                seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                                EnumConsecutivo.NUMERO_VOLANTE_PAGO_ECUADOR)));
                generaAcuerdoPagoCuotaDTO.setValoresVistaPreliminar(valoresVistaPreliminar);
                generaAcuerdoPagoCuotaDTO.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                generaAcuerdoPagoCuotaDTO.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.VOLANTE_PAGO);
                Object[] valoresParametrosVolante = { detalle.getId() };
                generaAcuerdoPagoCuotaDTO.setValoresParametros(valoresParametrosVolante);
                detalle.setIdDocumento(iRDocumentosCirculemos.generarDocumento(generaAcuerdoPagoCuotaDTO));
                detalle.setNumeroVolante((Long) valoresVistaPreliminar.get(ConstantesDocumentosC2.NUMERO_VOLANTE));
                detalle.setFinanciacion(financiacionDTO);
                em.merge(DetalleFinanciacionHelper.toLevel1Entity(detalle, null));

                respuesta.setFinanciacion(financiacionDTO);
            }
        } catch (CirculemosAlertaException e) {
            respuesta.setCodigoError(EnumErroresResultadoFinanciacion.RESULTADO_VALIDACION_ERROR.getValue());
            logger.error("Error al generar documento de financiación", e);
            throw new CirculemosNegocioException(EnumErrorSimulacionFinanciacion.FIN_018_005);
        }
        return respuesta;
    }

    @Override
    public DejarFirmeMetaDataDTO dejarFirmeFinanciacion(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.dejarFirmeFinanciacion(String)");

        // Se consulta la primera cuota de la financiacíon
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT df FROM DetalleFinanciacion df");
        jpql.append(" JOIN FETCH df.financiacion fn");
        jpql.append(" WHERE fn.id=:idFinanciacion");
        jpql.append(" AND df.fechaPago IS NOT NULL");
        jpql.append(" AND df.numeroCuota=:numeroCuota");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idFinanciacion", dejarFirmeDTO.getIdFinanciacion());
        filtros.put("numeroCuota", PRIMER_CUOTA);

        GenericDao<DetalleFinanciacion> dao = new GenericDao<DetalleFinanciacion>(DetalleFinanciacion.class, em);
        List<DetalleFinanciacion> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);

        Financiacion financiacion = resultadoConsulta.get(0).getFinanciacion();
        try {

            // Se actualiza el estado del proceso de financiación a en firme
            TrazabilidadProcesoDTO trazaActual = iRFachadaProceso.actualizarEstadoProceso(
                    financiacion.getProceso().getId(), EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME, false);

            Date fechaSistema = UtilFecha.buildCalendar().getTime();
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.FECHA_SOLICITUD,
                    financiacion.getProceso().getFechaInicio());

            // Guarda la firma y obtiene Id de la firma para el doc
            if (dejarFirmeDTO.getNumeroFirma() != null) {
                // Cargo DTO con datos de la persona
                PersonaDTO persona = new PersonaDTO();
                persona.setId(financiacion.getDeudor().getId());
                // Cardo DTO con info de la firma
                CapturaFirmaDTO capturaFirma = new CapturaFirmaDTO();
                capturaFirma.setFirma(dejarFirmeDTO.getNumeroFirma());
                capturaFirma.setPersonaDTO(persona);
                Long numeroFirma = iRfirma.registrarFirma(capturaFirma, true);
                valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
            } else {
                // Consultar la firma de la persona
                Long numeroFirma = iRfirma.consultarNumeroFirma(financiacion.getDeudor().getId());
                if (numeroFirma == null) {
                    throw new CirculemosNegocioException(EnumErroDocumentoFinanciacion.FIN_029003);
                }
                valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
            }
            // Genera documento Convenio por multas de tránsito amortización mensual sin interés
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.PONER_FIRME_FINANCIACION);
            Object[] valoresParametros = { dejarFirmeDTO.getIdFinanciacion() };
            generaDocumento.setValoresParametros(valoresParametros);

            // Identificador de archivo generado por Documentos
            Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);
            DejarFirmeMetaDataDTO dejarFirmeMetaData = new DejarFirmeMetaDataDTO();
            dejarFirmeMetaData.setIdDocumento(idDocumento);

            // Guarda el documento generado
            DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
            documentoProceso.setNumeroDocumento(idDocumento);
            documentoProceso.setTrazabilidadProceso(trazaActual);
            TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
            tipoDocumento.setId(EnumTipoDocumentoProceso.FINANCIACION_PONER_FIRME.getValue());
            documentoProceso.setTipoDocumento(tipoDocumento);
            documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);

            // Se actualizan los estados de los comparendos
            iRComparendoFinanciacion.financiarProceso(new Date(), financiacion.getProceso().getId());

            // Se actualiza el estado de la cartera de las obligaciones a Financiado
            for (ObligacionFinanciacion obligacion : financiacion.getObligacionesFinanciacion()) {
                CarteraFinanciacionDTO carteraFinanciacionDTO = iRCarteraFinanciacion
                        .consultarCartera(obligacion.getNumeroObligacion(), obligacion.getCodigoTipoObligacion());
                iRCarteraFinanciacion.financiarCarteraFinanciacion(carteraFinanciacionDTO.getId(), fechaSistema);
            }
            // Consulta cartera de la financiacion
            CarteraFinanciacionDTO carteraFinanciacion = iRCarteraFinanciacion
                    .consultarCarteraFinanciacion(financiacion.getNumeroFinanciacion());
            // Activa cartera finaciacion
            iRCarteraFinanciacion.activarCarteraFinanciacion(carteraFinanciacion.getId(), fechaSistema);
            dejarFirmeMetaData.setFinanciacionDTO(FinanciacionHelperExtend.toLevel1DTO(financiacion));
            return dejarFirmeMetaData;
        } catch (CirculemosAlertaException e) {
            throw new CirculemosRuntimeException("No se pudo generar el documento de dejar en firme la financiación");
        }
    }

    @Override
    public void enviarCorreoDejarFirme(DejarFirmeMetaDataDTO dejarFirmeMetaData) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB::enviarCorreoDejarFirme(DejarFirmeMetaDataDTO)");

        if (dejarFirmeMetaData != null) {
            OrganismoTransitoDTO organismo = seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario();
            Map<String, Object> variables = new HashMap<>();
            SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
            variables.put("fecha_actual", escapeHtml4(formateador.format(UtilFecha.buildCalendar().getTime())));
            variables.put("numero_financiacion", dejarFirmeMetaData.getFinanciacionDTO().getNumeroFinanciacion());
            variables.put("organismo", escapeHtml4(organismo.getNombreOrganismo()));
            variables.put("ciudad", escapeHtml4(organismo.getMunicipio().getNombre()));
            variables.put("pais", escapeHtml4(seguridadCirculemosEJB.obtenerPais().getNombre()));

            // Calcula el nombre dependiendo del tipo de persona
            List<PersonaDTO> lsPersona = iFachadaAdminNegocio
                    .consultarPersona(dejarFirmeMetaData.getFinanciacionDTO().getDeudor());
            if (lsPersona != null && !lsPersona.isEmpty()) {
                PersonaDTO persona = lsPersona.get(0);
                if (persona instanceof PersonaJuridicaDTO) {
                    PersonaJuridicaDTO personaJuridica = new PersonaJuridicaDTO();
                    personaJuridica = (PersonaJuridicaDTO) persona;
                    variables.put("nombre_deudor", escapeHtml4(personaJuridica.getNombreComercial().toUpperCase()));
                } else {
                    variables
                            .put("nombre_deudor",
                                    escapeHtml4(
                                            (persona.getNombre1() + " "
                                                    + ((persona.getNombre2() == null) ? " "
                                                            : persona.getNombre2() + " ")
                                                    + persona.getApellido1() + " "
                                                    + ((persona.getApellido2() == null) ? "" : persona.getApellido2()))
                                                            .toUpperCase()));
                }
            }

            TrazabilidadProcesoDTO trazaProcDTO = new TrazabilidadProcesoDTO();
            EstadoProcesoDTO estadoProceso = new EstadoProcesoDTO();
            estadoProceso.setId(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME.getValue());
            trazaProcDTO.setEstadoProceso(estadoProceso);
            trazaProcDTO.setProceso(dejarFirmeMetaData.getFinanciacionDTO().getProceso());
            List<TrazabilidadProcesoDTO> lsTraza = iRFachadaProceso.consultarTrazabilidad(trazaProcDTO);
            TrazabilidadProcesoDTO traza = null;
            if (lsTraza != null && !lsTraza.isEmpty()) {
                traza = lsTraza.get(0);
            }

            ValorParametroDTO parametro = iRFachadaNotificaciones
                    .consultarParametroEnvioNotificaciones(organismo.getCodigoOrganismo());
            String[] aDireccionesDestino = null;
            PersonaDTO personaDTO = new PersonaDTO();
            personaDTO.setId(dejarFirmeMetaData.getFinanciacionDTO().getDeudor().getId());

            List<String> correos = new ArrayList<>();
            List<CorreoPersonaDTO> lsCorreoPersona = iFachadaAdminNegocio
                    .consultarCorreosNotificables(personaDTO.getId());
            for (CorreoPersonaDTO correoPersona : lsCorreoPersona) {
                correos.add(correoPersona.getCorreoElectronico());
            }
            aDireccionesDestino = correos.toArray(new String[correos.size()]);

            List<ArchivoTransportableDTO> lsArchivos = new ArrayList<ArchivoTransportableDTO>();
            ArchivoTransportableDTO archivo = null;
            if (dejarFirmeMetaData.getIdDocumento() != null) {
                try {
                    // Consulta el documento para enviarlo en el correo
                    ArrayList<Long> documentos = new ArrayList<Long>();
                    documentos.add(dejarFirmeMetaData.getIdDocumento());
                    archivo = new ArchivoTransportableDTO(NOMBRE_ARCHIVO,
                            iRDocumentosCirculemos.consultarDocumentosPDF(documentos));
                    archivo.setNumeroDocumento(String.valueOf(dejarFirmeMetaData.getIdDocumento()));
                    lsArchivos.add(archivo);
                } catch (CirculemosAlertaException e) {
                    logger.error("No se encontró documento para envio de correo de notificacion dejar en firme", e);
                }
            }

            // Envio de correo
            if (aDireccionesDestino != null && aDireccionesDestino.length > BigInteger.ZERO.intValue()) {
                if (parametro != null && parametro.getValorParamBoolean()) {
                    EnvioNotificacionDTO envioNotificacion = new EnvioNotificacionDTO();
                    List<ConsultarNotificacionesDTO> lsNotificacion = new ArrayList<>();
                    ConsultarNotificacionesDTO notificacion = new ConsultarNotificacionesDTO();
                    notificacion.setLsCorreoElectronico(Arrays.asList(aDireccionesDestino));
                    notificacion.setCodSeguimientoInt(dejarFirmeMetaData.getFinanciacionDTO().getProceso().getId());
                    notificacion.setExternalId(traza.getId());
                    notificacion.setLsArchivos(lsArchivos);
                    lsNotificacion.add(notificacion);
                    envioNotificacion.setLsNotificaciones(lsNotificacion);
                    envioNotificacion.setTipoCorreo(EnumTipoCorreo.PONER_FIRME_FINANCIACION_ENOTIFICA);
                    envioNotificacion.setTipoNotificacion(EnumTipoNotificacion.NOTIFICACION_FINANCIACIONES);
                    envioNotificacion.setVariablesMensaje(variables);
                    Integer[] aEstados = iRFachadaNotificaciones.enviaNotificaciones(envioNotificacion);
                    if (aEstados[0] > 0) {
                        throw new CirculemosNegocioException(
                                ErrorFinanciacion.EnumErrorEnvioNotificacionDejarEnFirme.FIN_027001);
                    }
                } else {
                    LogEnvioCorreoDTO log = iRCirculemosMailSender.enviarCorreo(organismo.getCodigoOrganismo(),
                            EnumTipoCorreo.PONER_FIRME_FINANCIACION, aDireccionesDestino, variables, lsArchivos);
                    // Actualizacion de auditoria de correo
                    log.setTablaSolicitud("trazabilidad_proceso");
                    log.setIdTablaSolicitud(traza.getId());
                    em.merge(LogEnvioCorreoHelper.toLevel1Entity(log, null));
                }
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarEstadosAsociadosFinanciacion(Long idProceso, String numeroFinanciacion,
            EnumEstadoProceso enumEstadoProceso, boolean cerrarProceso, Integer codigoOrganismo)
            throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB::actualizarEstadosAsociadosFinanciacion(Long, EnumEstadoProceso, boolean, int)");
        if (idProceso != null && enumEstadoProceso != null) {
            iRFachadaProceso.actualizarEstadoProceso(idProceso, enumEstadoProceso, cerrarProceso);
            if (EnumEstadoProceso.ECUADOR_FINANCIACION_ANULADO.equals(enumEstadoProceso)) {
                iRCarteraFinanciacion.actualizarEstadoAnteriorCartera(idProceso);
                iRComparendoFinanciacion.actualizarEstadoAnteriorComparendo(idProceso);

                // Valida si debe registrar la financiacion en axis
                ValorParametroDTO parametroFinanciacionTerceros = iFachadaAdminGeneral
                        .consultarValorParametro(EnumParametro.REGISTRAR_FINANCIACION_TERCEROS, codigoOrganismo, true);

                if (parametroFinanciacionTerceros != null && parametroFinanciacionTerceros.getValorParamBoolean()) {
                    // Actualizacion en AXIS
                    clienteWSAXISEJB.anularFinanciacion(numeroFinanciacion);
                }
            }
        }
    }

    @Override
    public byte[] imprimirReciboPago(Long idDetalleFinanciacion) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB::imprimirReciboPago(Long)");
        try {

            // Genera documento
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.FECHA_SOLICITUD, UtilFecha.buildCalendar().getTime());
            // obtiene el consecutivo correspondiente al numero de volante
            valoresVistaPreliminar.put(ConstantesDocumentosC2.NUMERO_VOLANTE,
                    Long.valueOf(iFachadaAdminGeneral.generarConsecutivo(
                            seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                            EnumConsecutivo.NUMERO_VOLANTE_PAGO_ECUADOR)));
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.VOLANTE_PAGO);
            Object[] valoresParametros = { idDetalleFinanciacion };
            generaDocumento.setValoresParametros(valoresParametros);
            Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

            // Adiciona el documento al DTO de pantalla
            ArrayList<Long> documentos = new ArrayList<Long>();
            documentos.add(idDocumento);
            return iRDocumentosCirculemos.consultarDocumentosPDF(documentos);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorFinanciacion.EnumErroDocumentoFinanciacion.FIN_029001);
        }

    }

    @Override
    public byte[] imprimirCuadroAmortizacion(Long idFinanciacion) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB::imprimirCuadroAmortizacion(Long)");
        try {

            // Genera documento
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.FECHA_SOLICITUD, UtilFecha.buildCalendar().getTime());
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.CUADRO_AMORTIZACION);
            Object[] valoresParametros = { idFinanciacion };
            generaDocumento.setValoresParametros(valoresParametros);
            Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

            // Adiciona el documento al DTO de pantalla
            ArrayList<Long> documentos = new ArrayList<Long>();
            documentos.add(idDocumento);
            return iRDocumentosCirculemos.consultarDocumentosPDF(documentos);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorFinanciacion.EnumErroDocumentoFinanciacion.FIN_029002);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ValidaPagoFinanciacionDTO validarPagosFinanciaciones(Integer codigoOrganismo) {
        logger.debug("CoactivoEJB::validarPagosFinanciaciones(Integer)");

        ValorParametroDTO anularSolicitudesFinanciacion = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.REGISTRAR_ANULACION_SOLICITUDES_FINANCIACION, codigoOrganismo, true);

        ValorParametroDTO financiacionesIcumplidas = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.REGISTRAR_FINANCIACIONES_INCUMPLIDAS_COACTIVO, codigoOrganismo, true);

        ValidaPagoFinanciacionDTO validaPagoPreFinanciacionDTO = new ValidaPagoFinanciacionDTO();
        ValidaPagoFinanciacionDTO validaPagoFinanciacionDTO = new ValidaPagoFinanciacionDTO();

        if (anularSolicitudesFinanciacion != null && anularSolicitudesFinanciacion.getValorParamBoolean()) {
            validaPagoPreFinanciacionDTO = consultarPagosFinanciaciones(codigoOrganismo,
                    EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO);
        } else {
            validaPagoPreFinanciacionDTO.setCantFinanAnuladas(0);
            validaPagoPreFinanciacionDTO.setCantFinanValidadas(0);
        }

        if (financiacionesIcumplidas != null && financiacionesIcumplidas.getValorParamBoolean()) {
            validaPagoFinanciacionDTO = consultarPagosFinanciaciones(codigoOrganismo,
                    EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME);
        } else {
            validaPagoFinanciacionDTO.setCantFinanAnuladas(0);
            validaPagoFinanciacionDTO.setCantFinanValidadas(0);
        }

        validaPagoFinanciacionDTO.setCantFinanValidadas(validaPagoPreFinanciacionDTO.getCantFinanValidadas()
                + validaPagoFinanciacionDTO.getCantFinanValidadas());
        validaPagoFinanciacionDTO.setCantFinanAnuladas(
                validaPagoPreFinanciacionDTO.getCantFinanAnuladas() + validaPagoFinanciacionDTO.getCantFinanAnuladas());
        return validaPagoFinanciacionDTO;
    }

    /**
     * Consulta las financiaciones con sus pagos para cambio de estado a anulado e incumplido
     * 
     * @param codigoOrganismo
     * @param estadoProceso
     * @return DTO de validacion
     * @author julio.pinzon 2016-08-08
     */
    private ValidaPagoFinanciacionDTO consultarPagosFinanciaciones(Integer codigoOrganismo,
            EnumEstadoProceso estadoProceso) {
        logger.debug("FinanciacionEJB::validarPagosFinanciaciones()");
        Date ahora = UtilFecha.buildCalendar().getTime();
        ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral
                .consultarValorParametro(EnumParametro.CANT_DIAS_ESPERA_REPORTE_PAGO_TERCERO, codigoOrganismo, true);
        int finanValidadas = 0;
        int finanAnuladas = 0;
        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT ");
        consulta.append("f.id_proceso, ");
        consulta.append("f.numero_financiacion, ");
        consulta.append("MIN(df.fecha_pago_oportuno) as fecha_pago_oportuno ");
        consulta.append("FROM financiacion f ");
        consulta.append("JOIN proceso p ");
        consulta.append("ON f.id_proceso = p.id_proceso ");
        consulta.append("JOIN detalle_financiacion df ");
        consulta.append("ON df.id_financiacion = f.id_financiacion ");
        consulta.append("WHERE p.id_estado_proceso = :estadoFinanciacion ");
        consulta.append("AND df.fecha_pago IS NULL ");
        consulta.append("AND df.fecha_pago_oportuno < :fechaActual ");
        if (estadoProceso.equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO)) {
            consulta.append("AND df.numero_cuota = :numeroCuota ");
        } else if (estadoProceso.equals(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME)) {
            consulta.append("AND df.numero_cuota > :numeroCuota ");
        }
        consulta.append("GROUP BY f.id_proceso, f.numero_financiacion ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("estadoFinanciacion", estadoProceso.getValue());
        query.setParameter("fechaActual", ahora);
        if (estadoProceso.equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO)
                || estadoProceso.equals(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME)) {
            query.setParameter("numeroCuota", PRIMER_CUOTA);
        }

        @SuppressWarnings({ "unchecked" })
        List<Object[]> lsFinanciaciones = Utilidades.safeList(query.getResultList());

        if (!lsFinanciaciones.isEmpty()) {
            BigInteger idProceso = null;
            Date fechaPagoOportunoDB = null;
            String numeroFinanciacion = null;

            for (Object[] financiacion : lsFinanciaciones) {
                idProceso = (BigInteger) financiacion[0];
                numeroFinanciacion = (String) financiacion[1];
                fechaPagoOportunoDB = (Date) financiacion[2];

                try {
                    Date fechaPagoOportuno = null;
                    fechaPagoOportuno = iFachadaAdminGeneral.sumarDias(codigoOrganismo, fechaPagoOportunoDB,
                            valorParametroDTO.getValorParamInt(), false);
                    if (fechaPagoOportuno.compareTo(ahora) < 0) {
                        if (estadoProceso.equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO)) {
                            iLFinanciacion.actualizarEstadosAsociadosFinanciacion(idProceso.longValue(),
                                    numeroFinanciacion, EnumEstadoProceso.ECUADOR_FINANCIACION_ANULADO, true,
                                    codigoOrganismo);
                        } else if (estadoProceso.equals(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME)) {
                            iLFinanciacion.actualizarEstadosAsociadosFinanciacion(idProceso.longValue(),
                                    numeroFinanciacion, EnumEstadoProceso.ECUADOR_FINANCIACION_INCUMPLIDO, false,
                                    codigoOrganismo);
                        }
                        ++finanAnuladas;
                    }
                } catch (Exception e) {
                    logger.error("Error al cambiar estado de financiacion con proceso : " + idProceso, e);
                }
                ++finanValidadas;
            }
        }
        ValidaPagoFinanciacionDTO validaPagoFinanciacionDTO = new ValidaPagoFinanciacionDTO();
        validaPagoFinanciacionDTO.setCantFinanValidadas(finanValidadas);
        validaPagoFinanciacionDTO.setCantFinanAnuladas(finanAnuladas);
        return validaPagoFinanciacionDTO;

    }

    /**
     * Calcula las cuotas de simulaciones
     * 
     * @param simulacionFinanciacionDTO
     * @param fecPagOportunoCuotaInicial
     * @param codigoOrganismo
     * @param valorMinimo
     * @author julio.pinzon 2016-08-23
     */
    private void calcularCuotasSimulacion(SimulacionFinanciacionDTO simulacionFinanciacionDTO,
            Date fecPagOportunoCuotaInicial, int codigoOrganismo, BigDecimal valorMinimo) {

        // Valores para calcular los valores de las cuotas
        BigDecimal porcentajePrimeraCuota = simulacionFinanciacionDTO.getValorPrimeraCuota()
                .divide(simulacionFinanciacionDTO.getValorTotal(), ESCALA_PORCENTAJE, BigDecimal.ROUND_HALF_UP);
        BigDecimal divisor = new BigDecimal(String.valueOf(simulacionFinanciacionDTO.getNumeroCuotas()));

        BigDecimal porcentajeRepartirCuotas = BigDecimal.ONE.subtract(porcentajePrimeraCuota).divide(divisor,
                ESCALA_PORCENTAJE, BigDecimal.ROUND_HALF_UP);

        // Valores para calcular los saldos en cada cuota
        BigDecimal saldoRepartirCuotas = simulacionFinanciacionDTO.getValorTotal()
                .subtract(simulacionFinanciacionDTO.getValorPrimeraCuota());
        BigDecimal totalConIntereses = BigDecimal.ZERO;

        List<DetalleFinanciacionDTO> detallesFinanciacion = new ArrayList<>();

        // Construir cuota inicial
        DetalleFinanciacionDTO primeraCuota = construirCuota(simulacionFinanciacionDTO.getValorPrimeraCuota(),
                BigDecimal.ZERO, fecPagOportunoCuotaInicial, 0);
        primeraCuota.setValorSaldoObligacion(saldoRepartirCuotas);
        detallesFinanciacion.add(primeraCuota);
        totalConIntereses = totalConIntereses.add(simulacionFinanciacionDTO.getValorPrimeraCuota());

        // Construir demas cuotas
        BigDecimal valorCapitalCuota = saldoRepartirCuotas.divide(divisor, ESCALA, BigDecimal.ROUND_HALF_UP);
        for (int i = 1; i <= simulacionFinanciacionDTO.getNumeroCuotas(); i++) {
            DetalleFinanciacionDTO cuota = construirCuota(valorCapitalCuota, porcentajeRepartirCuotas,
                    calcularFechaPagoCuota(codigoOrganismo, fecPagOportunoCuotaInicial, i), i);
            cuota.setValorIntereses(saldoRepartirCuotas.multiply(simulacionFinanciacionDTO.getInteresFinanciacion())
                    .setScale(ESCALA, BigDecimal.ROUND_HALF_UP));
            cuota.setValorTotal(cuota.getValorCapital().add(cuota.getValorIntereses()));
            saldoRepartirCuotas = saldoRepartirCuotas.subtract(valorCapitalCuota);
            cuota.setValorSaldoObligacion(saldoRepartirCuotas);
            detallesFinanciacion.add(cuota);
            totalConIntereses = totalConIntereses.add(cuota.getValorTotal());
        }
        simulacionFinanciacionDTO.setDetallesFinanciacion(detallesFinanciacion);
        simulacionFinanciacionDTO.setValorTotalFinanciarInteres(totalConIntereses);

        // Para que la suma de los valores a capital que se pagan en cada cuota sea igual
        // al valor total a capital de la financiacion se suman o quitan centesimas de la cuota inicial
        BigDecimal valorTotalCapitalCuotas = valorCapitalCuota.setScale(ESCALA, BigDecimal.ROUND_HALF_UP)
                .multiply(divisor)
                .add(simulacionFinanciacionDTO.getValorPrimeraCuota().setScale(ESCALA, BigDecimal.ROUND_HALF_UP));
        BigDecimal diferencia = simulacionFinanciacionDTO.getValorTotal().setScale(ESCALA, BigDecimal.ROUND_HALF_UP)
                .subtract(valorTotalCapitalCuotas);
        // Se repite el calculo para que cuadren los totales si hay una diferencia <> 0
        if (diferencia.compareTo(BigDecimal.ZERO) != 0) {
            simulacionFinanciacionDTO.setValorPrimeraCuota(simulacionFinanciacionDTO.getValorPrimeraCuota()
                    .setScale(ESCALA, BigDecimal.ROUND_HALF_UP).add(diferencia));
            if (valorMinimo.compareTo(simulacionFinanciacionDTO.getValorPrimeraCuota()) > 0) {
                simulacionFinanciacionDTO.setValorPrimeraCuota(
                        simulacionFinanciacionDTO.getValorPrimeraCuota().add(divisor.multiply(CENTESIMA)));
            }
            calcularCuotasSimulacion(simulacionFinanciacionDTO, fecPagOportunoCuotaInicial, codigoOrganismo,
                    valorMinimo);
        }
    }

    @Override
    public boolean validarDejarEnFirme(DejarFirmeDTO dejarFirmeDTO)
            throws CirculemosNegocioException, CirculemosIllegalArgumentException {
        logger.debug("FinanciacionEJB.validarFinanciacion(String)");

        // Valida que exista un id de financiacion
        if (dejarFirmeDTO.getIdFinanciacion() == null) {
            throw new CirculemosNegocioException(EnumErroresDejarFirmeFinanciacion.FIN_024003);
        }

        // Se consulta la primera cuota de la financiacíon
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT df FROM DetalleFinanciacion df");
        jpql.append(" JOIN df.financiacion fn");
        jpql.append(" WHERE fn.id=:idFinanciacion");
        jpql.append(" AND df.fechaPago IS NOT NULL");
        jpql.append(" AND df.numeroCuota=:numeroCuota");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idFinanciacion", dejarFirmeDTO.getIdFinanciacion());
        filtros.put("numeroCuota", PRIMER_CUOTA);

        GenericDao<DetalleFinanciacion> dao = new GenericDao<DetalleFinanciacion>(DetalleFinanciacion.class, em);
        List<DetalleFinanciacion> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);

        // Valida que exista la financiacions y que la primera cuota se encuentre paga
        if (resultadoConsulta == null || resultadoConsulta.isEmpty()) {
            // La financiación NO tiene pagada la primera cuota
            throw new CirculemosNegocioException(EnumErroresDejarFirmeFinanciacion.FIN_024001);
        }

        // Se consulta el estado actual del proceso de financiación
        EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso
                .consultarEstadoProceso(resultadoConsulta.get(0).getFinanciacion().getProceso().getId());
        // La financiación NO está prefinanciada
        if (!(estadoProcesoDTO.getCodigo().equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO.getCodigo()))) {
            throw new CirculemosNegocioException(EnumErroresDejarFirmeFinanciacion.FIN_024002);
        }

        return true;
    }

    public void validarDejarFirmePago(DetalleFinanciacionDTO detalleFinanciacionDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.validarDejarFirmePago(DetalleFinanciacionDTO)");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT df FROM DetalleFinanciacion df");
        jpql.append(" JOIN df.financiacion fn");
        jpql.append(" WHERE fn.id=:idFinanciacion");
        jpql.append(" AND df.fechaPago IS NOT NULL");
        jpql.append(" AND df.numeroCuota=:numeroCuota");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idFinanciacion", detalleFinanciacionDTO.getFinanciacion().getId());
        filtros.put("numeroCuota", PRIMER_CUOTA);

        GenericDao<DetalleFinanciacion> dao = new GenericDao<DetalleFinanciacion>(DetalleFinanciacion.class, em);
        List<DetalleFinanciacion> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);

        // La financiación NO tiene pagada la primera cuota
        EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso
                .consultarEstadoProceso(resultadoConsulta.get(0).getFinanciacion().getProceso().getId());

        // Valida que exista la financiacions y que la primera cuota se encuentre paga
        if ((resultadoConsulta != null || !resultadoConsulta.isEmpty()) && (estadoProcesoDTO.getCodigo()
                .equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO.getCodigo()))) {
            DejarFirmeDTO dejarFirmeDTO = new DejarFirmeDTO();
            dejarFirmeDTO.setIdFinanciacion(detalleFinanciacionDTO.getFinanciacion().getId());

            DejarFirmeMetaDataDTO dejarFirmeMetaData = dejarFirmeFinanciacion(dejarFirmeDTO);
            dejarFirmeMetaData.getIdDocumento();
            Long id = dejarFirmeMetaData.getIdDocumento();
            return;
        }
        return;
    }

    @Override
    public void dejarFirmeFinanciacionMasivo() {
        logger.debug("FinanciacionEJB.dejarFirmeFinanciacion()");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT fn FROM DetalleFinanciacion df");
        jpql.append(" JOIN df.financiacion fn");
        jpql.append(" JOIN fn.proceso p");
        jpql.append(" WHERE p.estadoProceso.id = :idEstadoProceso");
        jpql.append(" AND fn.fuenteInformacion.id = :idTipoFuenteInformacion");
        jpql.append(" AND df.fechaPago IS NOT NULL");
        jpql.append(" AND df.numeroCuota = :numeroCuota");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idEstadoProceso", EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO.getId());
        query.setParameter("idTipoFuenteInformacion", EnumTipoFuenteInformacion.CIRCULEMOS2.getValue());
        query.setParameter("numeroCuota", PRIMER_CUOTA);

        @SuppressWarnings("unchecked")
        List<Financiacion> financiacions = query.getResultList();
        if (financiacions != null && !financiacions.isEmpty()) {
            for (Financiacion financiacion : financiacions) {
                DejarFirmeDTO dejarFirmeDTO = new DejarFirmeDTO();
                // Id financiacion
                dejarFirmeDTO.setIdFinanciacion(financiacion.getId());
                financiacionEJB.dejarFirmeFinanciacionNT(dejarFirmeDTO);
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void dejarFirmeFinanciacionNT(DejarFirmeDTO dejarFirmeDTO) {
        try {
            financiacionEJB.dejarFirmeFinanciacionNEW(dejarFirmeDTO);
        } catch (CirculemosNegocioException e) {
            logger.error("Error en dejar en fiemre : " + dejarFirmeDTO.getIdFinanciacion(), e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void dejarFirmeFinanciacionNEW(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException {
        dejarFirmeFinanciacion(dejarFirmeDTO);

    }

    @Override
    public void generarDocumentoFirmeFinanciacionMasivo() {
        logger.debug("FinanciacionEJB.generarDocumentoFirmeFinanciacionMasivo()");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT f FROM Financiacion f");
        jpql.append(" JOIN f.proceso p");
        jpql.append(" JOIN p.trazabilidadProceso tp");
        jpql.append(" LEFT JOIN tp.documentos dp");
        jpql.append(" WHERE tp.estadoProceso.id = :idEstadoProceso");
        jpql.append(" AND dp.tipoDocumento IS NULL");
        jpql.append(" AND f.fuenteInformacion.id = :idTipoFuenteInformacion");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idEstadoProceso", EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME.getId());
        query.setParameter("idTipoFuenteInformacion", EnumTipoFuenteInformacion.CIRCULEMOS2.getValue());

        @SuppressWarnings("unchecked")
        List<Financiacion> financiacions = query.getResultList();
        if (financiacions != null && !financiacions.isEmpty()) {
            for (Financiacion financiacion : financiacions) {
                DejarFirmeDTO dejarFirmeDTO = new DejarFirmeDTO();
                // Id financiacion
                dejarFirmeDTO.setIdFinanciacion(financiacion.getId());
                financiacionEJB.crearDocFirmeFinanciacionNT(dejarFirmeDTO);
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void crearDocFirmeFinanciacionNT(DejarFirmeDTO dejarFirmeDTO) {
        logger.debug("FinanciacionEJB.crearDocFirmeFinanciacion(DejarFirmeDTO)");
        try {
            financiacionEJB.crearDocFirmeFinanciacion(dejarFirmeDTO);
        } catch (CirculemosNegocioException e) {
            logger.error("Error en dejar en fiemre : " + dejarFirmeDTO.getIdFinanciacion(), e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void crearDocFirmeFinanciacion(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException {
        logger.debug("FinanciacionEJB.crearDocFirmeFinanciacion(DejarFirmeDTO)");

        // Se consulta la primera cuota de la financiacíon
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT df FROM DetalleFinanciacion df");
        jpql.append(" JOIN df.financiacion fn");
        jpql.append(" WHERE fn.id=:idFinanciacion");
        jpql.append(" AND df.fechaPago IS NOT NULL");
        jpql.append(" AND df.numeroCuota=:numeroCuota");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idFinanciacion", dejarFirmeDTO.getIdFinanciacion());
        filtros.put("numeroCuota", PRIMER_CUOTA);

        GenericDao<DetalleFinanciacion> dao = new GenericDao<DetalleFinanciacion>(DetalleFinanciacion.class, em);
        List<DetalleFinanciacion> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);

        Financiacion financiacion = resultadoConsulta.get(0).getFinanciacion();
        try {

            // Se consulta el estado del proceso de financiación a en firme
            TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
            // Estado proceso
            EstadoProcesoDTO estadoProcesoDTO = new EstadoProcesoDTO();
            estadoProcesoDTO.setId(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME.getId());
            // Proceso
            trazabilidadProcesoDTO.setProceso(ProcesoHelper.toLevel0DTO(financiacion.getProceso()));

            trazabilidadProcesoDTO.setEstadoProceso(estadoProcesoDTO);
            List<TrazabilidadProcesoDTO> trazabilidadProcesoDTOs = iRFachadaProceso
                    .consultarTrazabilidad(trazabilidadProcesoDTO);

            TrazabilidadProcesoDTO trazaActual = null;
            if (trazabilidadProcesoDTOs != null && !trazabilidadProcesoDTOs.isEmpty()) {
                trazaActual = trazabilidadProcesoDTOs.get(0);
            }

            Date fechaSistema = UtilFecha.buildCalendar().getTime();
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.FECHA_SOLICITUD,
                    financiacion.getProceso().getFechaInicio());

            // Guarda la firma y obtiene Id de la firma para el doc
            if (dejarFirmeDTO.getNumeroFirma() != null) {
                // Cargo DTO con datos de la persona
                PersonaDTO persona = new PersonaDTO();
                persona.setId(financiacion.getDeudor().getId());
                // Cardo DTO con info de la firma
                CapturaFirmaDTO capturaFirma = new CapturaFirmaDTO();
                capturaFirma.setFirma(dejarFirmeDTO.getNumeroFirma());
                capturaFirma.setPersonaDTO(persona);
                Long numeroFirma = iRfirma.registrarFirma(capturaFirma, true);
                valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
            } else {
                // Consultar la firma de la persona
                Long numeroFirma = iRfirma.consultarNumeroFirma(financiacion.getDeudor().getId());
                if (numeroFirma == null) {
                    throw new CirculemosNegocioException(EnumErroDocumentoFinanciacion.FIN_029003);
                }
                valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
            }
            // Genera documento Convenio por multas de tránsito amortización mensual sin interés
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.PONER_FIRME_FINANCIACION);
            Object[] valoresParametros = { dejarFirmeDTO.getIdFinanciacion() };
            generaDocumento.setValoresParametros(valoresParametros);

            // Identificador de archivo generado por Documentos
            Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

            // Guarda el documento generado
            DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
            documentoProceso.setNumeroDocumento(idDocumento);
            documentoProceso.setTrazabilidadProceso(trazaActual);
            TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
            tipoDocumento.setId(EnumTipoDocumentoProceso.FINANCIACION_PONER_FIRME.getValue());
            documentoProceso.setTipoDocumento(tipoDocumento);
            documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosRuntimeException("No se pudo generar el documento de dejar en firme la financiación");
        }

    }

}