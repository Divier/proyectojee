package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.dto.CapturaFirmaDTO;
import co.com.datatools.c2.dto.ConsultaImpugnacionDTO;
import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EncabezadoImpugnacionDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.EvaluarImpugnacionBackDTO;
import co.com.datatools.c2.dto.EvaluarImpugnacionDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.HistoricoFalloDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.MotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.ProcesoPlantillaDTO;
import co.com.datatools.c2.dto.RadicarExpedienteDTO;
import co.com.datatools.c2.dto.RechazoImpugnacionDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.RespuestaImpugnacionDTO;
import co.com.datatools.c2.dto.TipoDocumentoProcesoDTO;
import co.com.datatools.c2.dto.TipoProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.EstadoObligacionDTO;
import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoSaldoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoProcesoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.entidades.AccionImpugnacionBack;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.entidades.EvaluarImpugnacion;
import co.com.datatools.c2.entidades.EvaluarImpugnacionBack;
import co.com.datatools.c2.entidades.FalloImpugnacion;
import co.com.datatools.c2.entidades.MotivacionImpugnacion;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.RechazoImpugnacion;
import co.com.datatools.c2.entidades.TipoFallo;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoNotificacion;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumProcesoPlantilla;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoFallo;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorImpugnacion;
import co.com.datatools.c2.negocio.error.ErrorImpugnacion.EvaluarExpediente;
import co.com.datatools.c2.negocio.error.ErrorImpugnacion.EvaluarExpedienteBack;
import co.com.datatools.c2.negocio.error.ErrorImpugnacion.RegistrarAcciones;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaCartera;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.helpers.AccionImpugnacionBackHelper;
import co.com.datatools.c2.negocio.helpers.EvaluarImpugnacionBackHelper;
import co.com.datatools.c2.negocio.helpers.EvaluarImpugnacionHelper;
import co.com.datatools.c2.negocio.helpers.FalloImpugnacionHelper;
import co.com.datatools.c2.negocio.helpers.MotivacionImpugnacionHelper;
import co.com.datatools.c2.negocio.helpers.ParticipanteProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.RechazoImpugnacionHelper;
import co.com.datatools.c2.negocio.helpers.TrazabilidadProcesoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelperExtend;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILImpugnacion;
import co.com.datatools.c2.negocio.interfaces.IRClienteWSAXIS;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRFirma;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

@Stateless(name = "ImpugnacionEJB")
@LocalBean
public class ImpugnacionEJB implements ILImpugnacion, IRImpugnacion {

    private final static Logger logger = Logger.getLogger(ImpugnacionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminNegocio fachadaAdminNegocio;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;

    @EJB
    private IRFachadaCartera iRFachadaCartera;

    @EJB
    private IRFachadaProceso iRFachadaProceso;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRFachadaComparendo iRFachadaComparendo;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;

    @EJB
    private IRClienteWSAXIS clienteWSAXISEJB;

    @EJB
    private IRFirma iRfirma;

    @EJB
    private IRFachadaNotificacionTerceros iRFachadaNotificaciones;

    @EJB
    private IRRepositorioArchivo iRepositorioArchivoEjb;

    /**
     * Nombre de archivo de fallo
     */
    private final String NOMBRE_ARCHIVO_FALLO = "Fallo_citacion.pdf";
    private final String NOMBRE_ARCHIVO_EVALUAR = "Impugnacion_citacion.pdf";

    @Override
    public List<RespuestaImpugnacionDTO> consultarImpugnacion(ConsultaImpugnacionDTO consulta)
            throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::consultarImpugnacion(ConsultaImpugnacionDTO)");

        List<ComparendoDTO> lsComparendoDTO = new ArrayList<>(0);
        List<RespuestaImpugnacionDTO> lsRespuestaImpugnacionDTO = new ArrayList<>(0);

        if (consulta != null) {
            GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();

            jpql.append("SELECT DISTINCT c FROM Comparendo c");
            boolean filtrosProceso = consulta.getNumeroProceso() != null || consulta.getIdEstadoProceso() != null
                    || (consulta.getFechaInicioAperturaProceso() != null
                            && consulta.getFechaFinAperturaProceso() != null);
            if (filtrosProceso) {
                jpql.append(", Proceso p");
                jpql.append(" LEFT JOIN c.comparendoProcesos cp");
                jpql.append(" LEFT JOIN p.trazabilidadProceso tp");
            }
            jpql.append(" LEFT JOIN c.personaList plInf");
            jpql.append(" LEFT JOIN plInf.tipoPersonaComparendo tpcInf");
            jpql.append(" JOIN FETCH c.infraccion AS i");
            jpql.append(" JOIN FETCH i.configuracionInfraccionList AS ci");
            jpql.append(" WHERE 1=1");

            if (filtrosProceso) {
                jpql.append(" AND cp.idProceso = p.id");
                jpql.append(" AND (c.cicomparendo = cp.comparendo.cicomparendo OR cp.comparendo.cicomparendo IS NULL)");

                if (consulta.getNumeroProceso() != null) {
                    jpql.append(" AND p.numeroProceso = :numeroProceso");
                    filtros.put("numeroProceso", consulta.getNumeroProceso());
                }
                if (consulta.getAnioProceso() != null) {
                    jpql.append(" AND p.fechaInicio >= :fechaInicioAnio AND p.fechaInicio <= :fechaFinAnio ");
                    Calendar fechaInicio = Calendar.getInstance();
                    fechaInicio.set(consulta.getAnioProceso(), 0, 1, 0, 0, 0);
                    filtros.put("fechaInicioAnio", fechaInicio.getTime());
                    Calendar fechaFinal = Calendar.getInstance();
                    fechaFinal.set(consulta.getAnioProceso(), 11, 31, 23, 59, 59);
                    filtros.put("fechaFinAnio", fechaFinal.getTime());
                }
                if (consulta.getIdEstadoProceso() != null) {
                    jpql.append(" AND tp.estadoProceso.id = :idEstadoProceso");
                    filtros.put("idEstadoProceso", consulta.getIdEstadoProceso());
                }
                if (consulta.getFechaInicioAperturaProceso() != null && consulta.getFechaFinAperturaProceso() != null) {

                    // Fecha inicial no debe ser mayor a hoy
                    if (consulta.getFechaInicioAperturaProceso().after(UtilFecha.currentZeroTimeDate())) {
                        throw new CirculemosNegocioException(ErrorImpugnacion.ValidarInfraccionImpugnacion.JUR_001001);
                    }

                    // Debe ser menor al valor del campo "Fecha final de apertura del proceso".
                    if (!(consulta.getFechaInicioAperturaProceso().before(consulta.getFechaFinAperturaProceso()))) {
                        throw new CirculemosNegocioException(ErrorImpugnacion.ValidarInfraccionImpugnacion.JUR_001002);
                    }

                    // Fecha final no debe ser mayor a hoy
                    if (consulta.getFechaFinAperturaProceso().after(UtilFecha.currentZeroTimeDate())) {
                        throw new CirculemosNegocioException(ErrorImpugnacion.ValidarInfraccionImpugnacion.JUR_001003);
                    }

                    jpql.append(
                            " AND p.fechaInicio >= :fechaInicioAperturaProceso AND p.fechaInicio <= :fechaFinAperturaProceso ");
                    Calendar fechaInicio = Calendar.getInstance();
                    fechaInicio.setTime(consulta.getFechaInicioAperturaProceso());
                    fechaInicio.set(Calendar.HOUR, 0);
                    fechaInicio.set(Calendar.MINUTE, 0);
                    fechaInicio.set(Calendar.SECOND, 0);
                    filtros.put("fechaInicioAperturaProceso", fechaInicio.getTime());
                    Calendar fechaFinal = Calendar.getInstance();
                    fechaFinal.setTime(consulta.getFechaFinAperturaProceso());
                    fechaFinal.set(Calendar.HOUR, 23);
                    fechaFinal.set(Calendar.MINUTE, 59);
                    fechaFinal.set(Calendar.SECOND, 59);
                    filtros.put("fechaFinAperturaProceso", fechaFinal.getTime());
                }

                jpql.append(" AND p.tipoProceso.id = :idTipoProceso");
                filtros.put("idTipoProceso", EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue());
                jpql.append(" AND tp.fechaFin IS NULL");
            }

            if (consulta.getCodigoOrganismo() != null) {
                jpql.append(" AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
                filtros.put("codigoOrganismo", consulta.getCodigoOrganismo());
            }

            if (consulta.getIdEstadoComparendo() != null) {
                jpql.append(" AND c.estadoComparendo.id = :idEstadoComparendo");
                filtros.put("idEstadoComparendo", consulta.getIdEstadoComparendo());
            }

            if (consulta.getNumeroComparendo() != null) {
                jpql.append(" AND c.ordenComparendoNacional.numeroComparendo = :numeroComparendo");
                filtros.put("numeroComparendo", consulta.getNumeroComparendo());
            }

            if (consulta.getNumeroCitacion() != null) {
                jpql.append(" AND c.numeroCitacion = :numeroCitacion");
                filtros.put("numeroCitacion", consulta.getNumeroCitacion());
            }

            if (consulta.getTipoDocumentoInfractor() != null && consulta.getNumeroDocumentoInfractor() != null) {
                jpql.append(" AND plInf.tipoIdentificacion.id = :idTipoDocInfractor");
                filtros.put("idTipoDocInfractor", consulta.getTipoDocumentoInfractor());
                jpql.append(" AND plInf.numeroIdentificacion = :numeroIdentificacionInfractor");
                filtros.put("numeroIdentificacionInfractor", consulta.getNumeroDocumentoInfractor());
                jpql.append(" AND tpcInf.codigo = :tipoPersonaComparendoInf");
                filtros.put("tipoPersonaComparendoInf", EnumTipoPersonaComparendo.INFRACTOR.getCodigo());
            }

            jpql.append(" ORDER BY c.fechaInfraccion DESC");

            // Realiza consulta de comparendos
            List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                lsComparendoDTO = ComparendoHelperExtend.toListLevel1DTOExtComparendoCarteraProceso(resultadoConsulta);
            }

            RespuestaImpugnacionDTO respuestaImpugnacionDTO = null;

            if (!lsComparendoDTO.isEmpty()) {
                for (ComparendoDTO comparendoDTO : lsComparendoDTO) {
                    respuestaImpugnacionDTO = new RespuestaImpugnacionDTO();
                    respuestaImpugnacionDTO.setIdComparendo(comparendoDTO.getCicomparendo());
                    respuestaImpugnacionDTO.setTipoComparendo(comparendoDTO.getTipoComparendo().getNombre());
                    respuestaImpugnacionDTO.setMedioImposicion(comparendoDTO.getMedioImposicion().getNombre());
                    respuestaImpugnacionDTO
                            .setNumeroComparendo(comparendoDTO.getOrdenComparendoNacional().getNumeroComparendo());
                    respuestaImpugnacionDTO.setFechaImposicion(UtilFecha
                            .setHoraFecha(comparendoDTO.getFechaInfraccion(), comparendoDTO.getHoraInfraccion()));
                    respuestaImpugnacionDTO.setFechaNotificacion(comparendoDTO.getFechaNotificacion());
                    respuestaImpugnacionDTO.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());
                    respuestaImpugnacionDTO.setNumeroCitacion(comparendoDTO.getNumeroCitacion());
                    respuestaImpugnacionDTO.setDetalleInfraccion(
                            comparendoDTO.getInfraccion().getConfiguracionInfraccionList().get(0).getDescripcion());
                    if (comparendoDTO.getComparendoVehiculo() != null
                            && comparendoDTO.getComparendoVehiculo().getPlacaVehiculo() != null) {
                        respuestaImpugnacionDTO.setPlaca(comparendoDTO.getComparendoVehiculo().getPlacaVehiculo());
                    }

                    List<ComparendoCarteraDTO> lsComparendoCarteraDTO = comparendoDTO.getComparendoCarteraList();
                    BigDecimal valor = BigDecimal.ZERO;
                    if (lsComparendoCarteraDTO != null && !lsComparendoCarteraDTO.isEmpty()) {
                        for (ComparendoCarteraDTO comparendoCarteraDTO : lsComparendoCarteraDTO) {
                            CarteraDTO carteraDTO = iRFachadaCartera
                                    .consultarCartera(comparendoCarteraDTO.getIdCartera());
                            if (carteraDTO.getTipoObligacion().getId()
                                    .equals(EnumTipoObligacion.COMPARENDO.getValue())) {
                                valor = carteraDTO.getSaldoCapital().add(carteraDTO.getSaldoInteres());
                            }
                        }
                    }

                    respuestaImpugnacionDTO.setValor(valor);
                    respuestaImpugnacionDTO.setEstadoComparendo(comparendoDTO.getEstadoComparendo().getNombre());
                    respuestaImpugnacionDTO.setImpugnable(isImpugnable(comparendoDTO));

                    List<ComparendoProcesoDTO> lsComparendoProcesoDTO = comparendoDTO.getComparendoProcesoList();
                    if (lsComparendoProcesoDTO != null && !lsComparendoProcesoDTO.isEmpty()) {
                        for (ComparendoProcesoDTO comparendoProcesoDTO : lsComparendoProcesoDTO) {
                            ProcesoDTO procesoDTO = new ProcesoDTO();
                            TipoProcesoDTO tipoProcesoDTO = new TipoProcesoDTO();
                            tipoProcesoDTO.setId(EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue());
                            procesoDTO.setTipoProceso(tipoProcesoDTO);
                            procesoDTO.setId(comparendoProcesoDTO.getIdProceso());
                            List<ProcesoDTO> lsProcesoDTO = iRFachadaProceso.consultarProceso(procesoDTO);
                            if (lsProcesoDTO != null && !lsProcesoDTO.isEmpty() && procesoDTO.getFechaFin() == null) {
                                respuestaImpugnacionDTO.setIdProceso(lsProcesoDTO.get(0).getId());
                                respuestaImpugnacionDTO.setProceso(lsProcesoDTO.get(0).getNumeroProceso());
                                respuestaImpugnacionDTO
                                        .setEstadoProceso(lsProcesoDTO.get(0).getEstadoProceso().getNombre());
                                respuestaImpugnacionDTO
                                        .setIdEstadoProceso(lsProcesoDTO.get(0).getEstadoProceso().getId());
                                break;
                            }
                        }
                    }
                    lsRespuestaImpugnacionDTO.add(respuestaImpugnacionDTO);
                }
            }
        }
        return lsRespuestaImpugnacionDTO;
    }

    /**
     * Metodo que permite saber si un comparendo puede ser o no impugnable
     * 
     * @param comparendoDTO
     * @return true si el comparendo es impuganble, false en caso contrario
     */
    private boolean isImpugnable(ComparendoDTO comparendoDTO) {

        if (comparendoDTO.getEstadoComparendo().getId().equals(EnumEstadoComparendo.PAGADO.getValue())
                || comparendoDTO.getEstadoComparendo().getId().equals(EnumEstadoComparendo.ANULADO.getValue())
                || comparendoDTO.getEstadoComparendo().getId().equals(EnumEstadoComparendo.FINANCIADO.getValue())
                || comparendoDTO.getEstadoComparendo().getId().equals(EnumEstadoComparendo.PREFINANCIADO.getValue())
                || comparendoDTO.getEstadoComparendo().getId().equals(EnumEstadoComparendo.IMPUGNADO_AXIS.getValue())) {
            return false;
        } else if (comparendoDTO.getComparendoProcesoList() != null
                && !comparendoDTO.getComparendoProcesoList().isEmpty()) {
            for (ComparendoProcesoDTO comparendoProcesoDTO : comparendoDTO.getComparendoProcesoList()) {
                ProcesoDTO procesoDTO = iRFachadaProceso.consultarProceso(comparendoProcesoDTO.getIdProceso());
                if (procesoDTO != null) {
                    // Verifica si es un proceso de impugnacion
                    if (procesoDTO.getTipoProceso().getId().equals(EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue())) {
                        return false;
                    }

                    // Verificamos si es un proceso de financiacion abierto
                    if (procesoDTO.getFechaFin() == null && procesoDTO.getTipoProceso().getId()
                            .equals(EnumTipoProceso.FINANCIACION_COMPARENDO.getValue())) {
                        return false;
                    }

                    // Verificamos si es un proceso de anulacion abierto
                    if (procesoDTO.getFechaFin() == null && procesoDTO.getTipoProceso().getId()
                            .equals(EnumTipoProceso.ANULACION_COMPARENDO.getValue())) {
                        return false;
                    }

                    // Verificamos si es un proceso coactivo abierto
                    if (procesoDTO.getFechaFin() == null
                            && procesoDTO.getTipoProceso().getId().equals(EnumTipoProceso.COACTIVO.getValue())) {
                        if (procesoDTO.getEstadoProceso().getCodigo()
                                .equals(EnumEstadoProceso.ECUADOR_COACTIVO_NOTIFICADO.getCodigo())) {
                            return false;
                        }
                    }
                }
            }
        } else {
            ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.DIAS_IMPUGAR,
                    comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(), true);
            if (comparendoDTO.getTipoComparendo().getId().equals(EnumTipoComparendo.COMPARENDO_NACIONAL.getCodigo())) {
                Date fechaMaxImpugnar = fachadaAdminGeneral.sumarDias(
                        comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(),
                        comparendoDTO.getFechaInfraccion(), Integer.valueOf(valorParametroDTO.getValorParam()), true);
                Date fechaActual = UtilFecha.buildCalendar().getTime();
                if (fechaActual.compareTo(comparendoDTO.getFechaInfraccion()) < BigInteger.ZERO.intValue()
                        || fechaActual.compareTo(fechaMaxImpugnar) > BigInteger.ZERO.intValue()) {
                    return false;
                }
            } else {
                Date fechaMaxImpugnar = fachadaAdminGeneral.sumarDias(
                        comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(),
                        comparendoDTO.getFechaNotificacion(), Integer.valueOf(valorParametroDTO.getValorParam()), true);
                Date fechaActual = UtilFecha.buildCalendar().getTime();
                if (fechaActual.compareTo(comparendoDTO.getFechaNotificacion()) < BigInteger.ZERO.intValue()
                        || fechaActual.compareTo(fechaMaxImpugnar) > BigInteger.ZERO.intValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public RadicarExpedienteDTO radicarExpediente(RadicarExpedienteDTO expediente) throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::radicarExpediente(RadicarExpedienteDTO)");
        try {
            checkNotNull(expediente.getCicomparendo(), "Comparendo es obligatorio");
            Comparendo comparendo = em.find(Comparendo.class, expediente.getCicomparendo());
            if (comparendo != null) {

                // Crea proceso
                RegistraProcesoDTO registra = new RegistraProcesoDTO();
                registra.setObservacion(EnumTipoProceso.IMPUGNACION_COMPARENDO.name());
                registra.setTipoProceso(EnumTipoProceso.IMPUGNACION_COMPARENDO);
                registra.setEstado(EnumEstadoProceso.ECUADOR_IMPUGNACION_IMPUGNADO);
                registra.setConsecutivo(EnumConsecutivo.NUMERO_IMPUGNACION_ECUADOR);
                ProcesoDTO proceso = iRFachadaProceso.crearProceso(registra);
                TrazabilidadProcesoDTO trazabilidad = new TrazabilidadProcesoDTO();
                trazabilidad.setProceso(proceso);
                List<TrazabilidadProcesoDTO> trazas = iRFachadaProceso.consultarTrazabilidad(trazabilidad);

                // Crea Motivacion
                expediente.getMotivacionImpugnacionDTO().setTrazabilidadProceso(trazas.get(0));
                MotivacionImpugnacion motivacion = MotivacionImpugnacionHelper
                        .toLevel1Entity(expediente.getMotivacionImpugnacionDTO(), null);
                ArchivoTransportableDTO archivoDTO = new ArchivoTransportableDTO();
                if (expediente.getAdjunto() != null) {
                    archivoDTO.setContenido(expediente.getAdjunto().getArchivo());
                    archivoDTO.setNombre(expediente.getAdjunto().getNombreArchivo());
                    String numeroArchivo = iRepositorioArchivoEjb
                            .registrarDocumento(EnumCategoriaDocumento.DOCUMENTO_ESCRITO_MOTIVACION, archivoDTO);
                    motivacion.setNumeroArchivoEscrito(numeroArchivo);
                    motivacion.setFechaCargueArchivo(expediente.getAdjunto().getFechaCargue());
                }
                em.persist(motivacion);

                // Crea Participantes
                if (expediente.getParticipanteProcesoDTOs() != null
                        && !expediente.getParticipanteProcesoDTOs().isEmpty()) {
                    for (ParticipanteProcesoDTO participante : expediente.getParticipanteProcesoDTOs()) {
                        participante.setProceso(proceso);
                        em.persist(ParticipanteProcesoHelper.toLevel1Entity(participante, null));
                    }
                }

                // Crea ComparendoProceso
                ComparendoProceso comparendoProcesoEntidad = new ComparendoProceso();
                comparendoProcesoEntidad.setComparendo(comparendo);
                comparendoProcesoEntidad.setIdProceso(proceso.getId());
                em.persist(comparendoProcesoEntidad);

                // Cambia de estado el comparendo
                CambioEstadoComparendoDTO cambio = new CambioEstadoComparendoDTO();
                cambio.setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
                cambio.setCodigoOrganismo(
                        comparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
                cambio.setEstadoComparendo(EnumEstadoComparendo.IMPUGNADO);
                cambio.setActividad(EnumActividad.RADICACION_PROCESO_IMPUGNACION);
                cambio.setFechaCambio(new Date());
                iRFachadaComparendo.actualizarEstadoComparendo(cambio);

                GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
                Map<String, Object> valoresVistaPreliminar = new HashMap<>();

                // Guarda la firma y obtiene el Id de la firma para el Doc
                if (expediente.getNumeroFirma() != null) {
                    // Se carga el id del infractor para guardar la firma
                    for (ParticipanteProcesoDTO participante : expediente.getParticipanteProcesoDTOs()) {
                        if (participante.getTipoParticipante().getId()
                                .equals(EnumTipoParticipante.INFRACTOR.getValue())) {
                            CapturaFirmaDTO capturaFirma = new CapturaFirmaDTO();
                            capturaFirma.setFirma(expediente.getNumeroFirma());
                            capturaFirma.setPersonaDTO(participante.getPersona());
                            Long numeroFirma = iRfirma.registrarFirma(capturaFirma, true);
                            valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
                            break;
                        }
                    }

                }
                // Selecciona la plantilla dependiento si tiene participante
                if (expediente.getParticipanteProcesoDTOs() != null
                        && !expediente.getParticipanteProcesoDTOs().isEmpty()
                        && expediente.getParticipanteProcesoDTOs().size() > 1) {
                    generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.APERTURA_IMPUGNACION);
                } else {
                    generaDocumento
                            .setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.APERTURA_IMPUGNACION_SIN_TERCERO);
                }

                // Genera documento
                generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
                generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                Object[] valoresParametros = { proceso.getId() };
                generaDocumento.setValoresParametros(valoresParametros);
                Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

                // Guarda el documento generado
                DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
                documentoProceso.setNumeroDocumento(idDocumento);
                documentoProceso.setTrazabilidadProceso(trazas.get(0));
                TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
                tipoDocumento.setId(EnumTipoDocumentoProceso.IMPUGNACION_APERTURA.getValue());
                documentoProceso.setTipoDocumento(tipoDocumento);
                documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);

                // Adiciona el documento al DTO de pantalla
                ArrayList<Long> documentos = new ArrayList<Long>();
                documentos.add(idDocumento);
                expediente.setDocumento(iRDocumentosCirculemos.consultarDocumentosPDF(documentos));

                // Valida si debe registrar la impugnacion en axis
                ValorParametroDTO parametroImpugnacionTerceros = fachadaAdminGeneral.consultarValorParametro(
                        EnumParametro.REGISTRAR_IMPUGNACION_TERCEROS,
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);
                if (parametroImpugnacionTerceros != null && parametroImpugnacionTerceros.getValorParamBoolean()) {
                    // Actualizacion en AXIS
                    clienteWSAXISEJB.impugnarComparendo(ComparendoHelper.toLevel0DTO(comparendo), proceso);
                }
            }
        } catch (CirculemosAlertaException e) {
            logger.error("Error al generar documento de apertura de impugnacion", e);
            throw new CirculemosNegocioException(ErrorImpugnacion.AperturaImpugnacion.COM_059001);
        }

        return expediente;
    }

    @Override
    public EncabezadoImpugnacionDTO consultarEncabezado(ProcesoDTO proceso) {
        logger.debug("ImpugnacionEJB::consultarEncabezado(ProcesoDTO)");
        EncabezadoImpugnacionDTO encabezadoImpugnacionDTO = new EncabezadoImpugnacionDTO();
        final StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
        jpql.append("SELECT DISTINCT c FROM Comparendo c ");
        jpql.append("JOIN c.comparendoProcesos cp ");
        jpql.append("JOIN FETCH c.infraccion AS i ");
        jpql.append("JOIN FETCH i.configuracionInfraccionList AS ci ");
        jpql.append("JOIN FETCH ci.normatividad nor ");
        jpql.append("WHERE cp.idProceso = :idProceso");
        filtros.put("idProceso", proceso.getId());

        List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
        ComparendoDTO comparendoDTO = new ComparendoDTO();
        List<ComparendoDTO> compa = new ArrayList<>(0);
        if (!resultadoConsulta.isEmpty()) {
            comparendoDTO = ComparendoHelperExtend.toLevel1DTOExtTipoPersona(resultadoConsulta.get(0));
            compa = ComparendoHelperExtend.toListLevel1DTOExtComparendoCarteraProceso(resultadoConsulta);
            encabezadoImpugnacionDTO.setNumeroComparendo(String.valueOf(comparendoDTO.getIdFacturaAxis()));
            encabezadoImpugnacionDTO.setNumeroCitacion(comparendoDTO.getNumeroCitacion());
            encabezadoImpugnacionDTO.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());
            encabezadoImpugnacionDTO.setDescripcionInfraccion(
                    "Artículo " + compa.get(0).getInfraccion().getConfiguracionInfraccionList().get(0).getArticulo()
                            + " numeral " + compa.get(0).getInfraccion().getNumeral() + " - "
                            + compa.get(0).getInfraccion().getConfiguracionInfraccionList().get(0).getNormatividad()
                                    .getNombre()
                            + " - "
                            + compa.get(0).getInfraccion().getConfiguracionInfraccionList().get(0).getDescripcion());
            if (comparendoDTO.getPersonaList() != null && !comparendoDTO.getPersonaList().isEmpty()) {
                PersonaDTO personaDTO = comparendoDTO.getPersonaList().get(0).getPersona();
                encabezadoImpugnacionDTO.setNombreInfractor(PersonaHelperExtend.construirNombrePersona(personaDTO));
                encabezadoImpugnacionDTO
                        .setDocumentoInfractor(comparendoDTO.getPersonaList().get(0).getTipoIdentificacion().getSigla()
                                + "-" + personaDTO.getNumeroIdentificacion());
            }
            ProcesoDTO procesoDTO = ProcesoHelper.toLevel0DTO(em.find(Proceso.class, proceso.getId()));
            encabezadoImpugnacionDTO.setFechaApertura(procesoDTO.getFechaInicio());
            encabezadoImpugnacionDTO.setNumeroProceso(procesoDTO.getNumeroProceso());
            if (comparendoDTO.getComparendoVehiculo() != null) {
                encabezadoImpugnacionDTO.setPlaca(comparendoDTO.getComparendoVehiculo().getPlacaVehiculo());
            }
        }

        return encabezadoImpugnacionDTO;
    }

    @Override
    public EvaluarImpugnacionDTO evaluarExpediente(EvaluarImpugnacionDTO evaluar) throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::evaluarExpediente(EvaluarImpugnacionDTO)");
        EvaluarImpugnacionDTO evaluarImpugnacionDTO = null;
        EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso
                .consultarEstadoProceso(evaluar.getTrazabilidadProceso().getProceso().getId());
        EnumEstadoProceso estadoActualizar = EnumEstadoProceso.ECUADOR_IMPUGNACION_IMPUGNADO;
        if (estadoProcesoDTO != null && estadoProcesoDTO.getId().equals(estadoActualizar.getId())) {
            if (evaluar.getSolucionInmediata().equals(true)) {
                estadoActualizar = EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO;
            } else {
                estadoActualizar = EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION;
            }

            // Actualizar estado proceso
            TrazabilidadProcesoDTO trazabilidad = iRFachadaProceso.actualizarEstadoProceso(
                    evaluar.getTrazabilidadProceso().getProceso().getId(), estadoActualizar, false);
            EvaluarImpugnacion evaluarImpugnacion = EvaluarImpugnacionHelper.toLevel1Entity(evaluar, null);
            evaluarImpugnacion.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0Entity(trazabilidad, null));
            em.persist(evaluarImpugnacion);
            evaluarImpugnacionDTO = EvaluarImpugnacionHelper.toLevel1DTO(evaluarImpugnacion);

            if (evaluar.getCodigoPlantilla() != null) {
                // Genera documento de Apertura
                GeneraDocumentoDTO aperturaImpugnacionDTO = new GeneraDocumentoDTO();
                aperturaImpugnacionDTO.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                ProcesoPlantillaDTO proceso = new ProcesoPlantillaDTO();
                proceso.setEnumProcesoPlantilla(EnumProcesoPlantilla.IMPUGNACION_SOLUCION_INMEDIATA);
                proceso.setCodigoPlantilla(evaluar.getCodigoPlantilla());
                OrganismoTransitoDTO organismo = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
                aperturaImpugnacionDTO.setCodigoOrganismo(organismo.getCodigoOrganismo());
                aperturaImpugnacionDTO.setIdTipoDocumentoGenerado(proceso);
                Object[] valoresParametros = { evaluar.getTrazabilidadProceso().getProceso().getId() };
                aperturaImpugnacionDTO.setValoresParametros(valoresParametros);

                try {

                    Long idDocumento = iRDocumentosCirculemos.generarDocumento(aperturaImpugnacionDTO);
                    // Guarda el documento generado
                    DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
                    documentoProceso.setNumeroDocumento(idDocumento);
                    documentoProceso.setTrazabilidadProceso(trazabilidad);
                    TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
                    tipoDocumento.setId(EnumTipoDocumentoProceso.IMPUGNACION_APERTURA.getValue());
                    documentoProceso.setTipoDocumento(tipoDocumento);
                    documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);
                    List<DocumentoProcesoDTO> documentos = new ArrayList<DocumentoProcesoDTO>();
                    documentos.add(documentoProceso);
                    evaluarImpugnacionDTO.getTrazabilidadProceso().setDocumentos(documentos);
                } catch (CirculemosAlertaException e) {
                    logger.error("Error al generar documento de apertura de impugnacion", e);
                    throw new CirculemosNegocioException(ErrorImpugnacion.EvaluarExpediente.JUR_004002);
                }
            }
        } else {
            throw new CirculemosNegocioException(EvaluarExpediente.JUR_004001);
        }

        return evaluarImpugnacionDTO;
    }

    @Override
    public FalloImpugnacionDTO registrarFallo(FalloImpugnacionDTO fallo) throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::registrarFallo(FalloImpugnacionDTO)");

        // Valida a que estado debe pasar el proceso
        List<ProcesoDTO> procesosDTO = iRFachadaProceso.consultarProceso(fallo.getTrazabilidadProceso().getProceso());
        EnumEstadoProceso estadoProceso = EnumEstadoProceso.ECUADOR_IMPUGNACION_FALLADO;
        if (!procesosDTO.isEmpty()) {
            ProcesoDTO procesoDTO = procesosDTO.get(0);
            // Si viene del back,viene de estos estados
            if (procesoDTO.getEstadoProceso().getId()
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue())
                    || procesoDTO.getEstadoProceso().getId()
                            .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_CIERRE_PRUEBAS.getValue())
                    || procesoDTO.getEstadoProceso().getId()
                            .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO_ACONTINUACION.getValue())) {
                estadoProceso = EnumEstadoProceso.ECUADOR_IMPUGNACION_FALLADO_ACONTINUACION;
            }

            // Pasa el proceso a fallado
            TrazabilidadProcesoDTO traza = iRFachadaProceso
                    .actualizarEstadoProceso(fallo.getTrazabilidadProceso().getProceso().getId(), estadoProceso, false);
            fallo.setTrazabilidadProceso(traza);

            // Forma el objeto de traza de fallo
            FalloImpugnacion falloImpugnacion = FalloImpugnacionHelper.toLevel1Entity(fallo, null);
            em.persist(falloImpugnacion);
            fallo.setId(falloImpugnacion.getId());

            // Genera el documento de fallo
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            ProcesoPlantillaDTO proceso = new ProcesoPlantillaDTO();
            proceso.setEnumProcesoPlantilla(EnumProcesoPlantilla.IMPUGNACION_FALLO);
            proceso.setCodigoPlantilla(fallo.getCodigoPlantilla());
            proceso.setDescripcion(fallo.getCodigoPlantilla());
            generaDocumento.setIdTipoDocumentoGenerado(proceso);
            Object[] valoresParametros = { procesoDTO.getId() };
            generaDocumento.setValoresParametros(valoresParametros);
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.REGISTRO_FALLO, fallo.getMotivo());
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);

            try {
                Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

                // Guarda el documento generado
                DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
                documentoProceso.setNumeroDocumento(idDocumento);
                documentoProceso.setTrazabilidadProceso(traza);
                TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
                tipoDocumento.setId(EnumTipoDocumentoProceso.IMPUGNACION_REALIZAR_FALLO.getValue());
                documentoProceso.setTipoDocumento(tipoDocumento);
                documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);
            } catch (CirculemosAlertaException e) {
                logger.error("Error al generar documento de fallo de impugnacion", e);
                throw new CirculemosNegocioException(ErrorImpugnacion.RealizarFallo.JUR_005001);
            }
        }

        return fallo;
    }

    /**
     * Guarda el documento de cierre de proceso y cierra el proceso
     * 
     * @param fallo
     * @param idProceso
     * @param comparendoDTO2
     * @throws CirculemosAlertaException
     * @author julio.pinzon 2016-06-15
     * @throws CirculemosNegocioException
     */
    private TrazabilidadProcesoDTO cerrarProcesoImpugnacion(FalloImpugnacionDTO fallo, Long idProceso,
            ComparendoDTO comparendoDTO) throws CirculemosAlertaException, CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::cerrarProcesoImpugnacion(FalloImpugnacionDTO, Long, Long)");
        fallo.setCicomparendo(comparendoDTO.getCicomparendo());

        CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
        cambioEstadoComparendo.setCodigoOrganismo(
                comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
        cambioEstadoComparendo.setFechaCambio(UtilFecha.buildCalendar().getTime());
        cambioEstadoComparendo.setNumeroComparendo(comparendoDTO.getOrdenComparendoNacional().getNumeroComparendo());
        cambioEstadoComparendo.setActividad(EnumActividad.APROBAR_PROCESO_IMPUGNACION);
        if (fallo.getTipoFallo().getId().equals(EnumTipoFallo.ABSOLUTORIO.getValue())) {
            cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.EXONERADO);
        } else {
            cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.VIGENTE);
        }
        iRFachadaComparendo.actualizarEstadoComparendo(cambioEstadoComparendo);

        // Hacer paso de proceso a cerrado
        TrazabilidadProcesoDTO traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                EnumEstadoProceso.ECUADOR_IMPUGNACION_CERRADO, true);

        Long idDocumento = null;

        // Consultar trazabilidad del fallo para obtener el documento de aprobacion
        TrazabilidadProcesoDTO trazabilidad = new TrazabilidadProcesoDTO(fallo.getTrazabilidadProceso().getId());
        trazabilidad.setProceso(new ProcesoDTO(idProceso));
        List<TrazabilidadProcesoDTO> trazas = iRFachadaProceso.consultarTrazabilidad(trazabilidad);
        if (!trazas.isEmpty() && trazas.get(0).getDocumentos() != null && !trazas.get(0).getDocumentos().isEmpty()) {
            idDocumento = trazas.get(0).getDocumentos().get(0).getNumeroDocumento();
            // Guarda el documento generado
            DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
            documentoProceso.setNumeroDocumento(idDocumento);
            documentoProceso.setTrazabilidadProceso(traza);
            TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
            tipoDocumento.setId(EnumTipoDocumentoProceso.IMPUGNACION_REALIZAR_FALLO.getValue());
            documentoProceso.setTipoDocumento(tipoDocumento);
            documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);
        }

        // Valida si debe registrar la impugnacion en axis
        ValorParametroDTO parametroImpugnacionTerceros = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.REGISTRAR_IMPUGNACION_TERCEROS,
                comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(), true);
        if (parametroImpugnacionTerceros != null && parametroImpugnacionTerceros.getValorParamBoolean()) {
            // Actualizacion en AXIS
            Long nuevaFactura = clienteWSAXISEJB.registrarFalloImpugnacion(fallo, comparendoDTO, idProceso);
            if (!fallo.getTipoFallo().getId().equals(EnumTipoFallo.ABSOLUTORIO.getValue()) && nuevaFactura != 0l) {
                // Cambiar informacion de la factura en comparendo y cartera
                iRFachadaComparendo.cambiarNumeroFactura(comparendoDTO.getCicomparendo(), nuevaFactura);
            }
        }
        return traza;
    }

    @Override
    public void enviarCorreoFalloImpugnacion(Long idProceso, Long ciComparendo, TrazabilidadProcesoDTO trazaProceso)
            throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::enviarCorreoFalloImpugnacion(Long, ComparendoDTO, Long)");

        FalloImpugnacionDTO falloImpugnacionDTO = consultarUltimoFalloImpugnacion(idProceso);
        ComparendoDTO comparendoDTO = iRFachadaComparendo.consultarComparendo(ciComparendo);
        List<TrazabilidadProcesoDTO> trazas = iRFachadaProceso.consultarTrazabilidad(trazaProceso);
        Long idDocumento = null;

        if (!trazas.isEmpty() && trazas.get(0).getDocumentos() != null && !trazas.get(0).getDocumentos().isEmpty()) {
            idDocumento = trazas.get(0).getDocumentos().get(0).getNumeroDocumento();
        }

        Map<String, Object> variables = new HashMap<>();
        String[] aDireccionesDestino = null;
        Proceso proceso = em.find(Proceso.class, idProceso);
        variables.put("numero_proceso", escapeHtml4(proceso.getNumeroProceso()));
        ParticipanteProcesoDTO participante = new ParticipanteProcesoDTO();
        participante.setProceso(new ProcesoDTO(idProceso));
        List<ParticipanteProcesoDTO> participantes = iRFachadaProceso.consultarParticipantesProceso(participante);
        PersonaDTO persona = null;
        if (!participantes.isEmpty()) {
            List<String> correos = new ArrayList<>();
            for (ParticipanteProcesoDTO part : participantes) {
                persona = part.getPersona();
                List<CorreoPersonaDTO> lsCorreoPersona = fachadaAdminNegocio
                        .consultarCorreosNotificables(persona.getId());
                for (CorreoPersonaDTO correoPersona : lsCorreoPersona) {
                    correos.add(correoPersona.getCorreoElectronico());
                }
            }
            aDireccionesDestino = correos.toArray(new String[correos.size()]);
        }

        TipoFallo tipoFallo = em.find(TipoFallo.class, falloImpugnacionDTO.getTipoFallo().getId());
        variables.put("fallo", escapeHtml4(tipoFallo.getDescripcion()));

        OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
        organismo.setCodigoOrganismo(
                comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
        List<OrganismoTransitoDTO> organismoTransitoList = fachadaAdminNegocio.consultarOrganismoTransito(organismo);
        variables.put("organismo", escapeHtml4(organismoTransitoList.get(0).getNombreOrganismo()));

        ValorParametroDTO parametro = iRFachadaNotificaciones.consultarParametroEnvioNotificaciones(
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());

        ArrayList<ArchivoTransportableDTO> archivos = new ArrayList<ArchivoTransportableDTO>();
        ArchivoTransportableDTO archivo = null;
        if (idDocumento != null) {
            try {
                // Consulta el documento para enviarlo en el correo
                ArrayList<Long> documentos = new ArrayList<Long>();
                documentos.add(idDocumento);
                archivo = new ArchivoTransportableDTO(NOMBRE_ARCHIVO_FALLO,
                        iRDocumentosCirculemos.consultarDocumentosPDF(documentos));
                archivo.setNumeroDocumento(String.valueOf(idDocumento));
                archivos.add(archivo);
            } catch (CirculemosAlertaException e) {
                logger.error("No se encontró documento para envio de correo de fallo", e);
            }
        }

        // Envio de correo
        if (aDireccionesDestino != null && aDireccionesDestino.length > BigInteger.ZERO.intValue()) {

            if (parametro != null && parametro.getValorParamBoolean()) {
                EnvioNotificacionDTO envioNotificacion = new EnvioNotificacionDTO();
                List<ConsultarNotificacionesDTO> lsNotificacion = new ArrayList<>();
                ConsultarNotificacionesDTO notificacion = new ConsultarNotificacionesDTO();
                notificacion.setLsCorreoElectronico(Arrays.asList(aDireccionesDestino));
                notificacion.setNombreInfractor(persona.getNombreCompleto());
                notificacion.setCodSeguimientoInt(idProceso);
                notificacion.setExternalId(falloImpugnacionDTO.getTrazabilidadProceso().getId());
                notificacion.setLsArchivos(archivos);
                lsNotificacion.add(notificacion);
                envioNotificacion.setLsNotificaciones(lsNotificacion);
                envioNotificacion.setTipoCorreo(EnumTipoCorreo.NOTIFICACION_FALLO_IMPUGNACION_ENOTIFICA);
                envioNotificacion.setTipoNotificacion(EnumTipoNotificacion.NOTIFICACION_IMPUGNACIONES);
                envioNotificacion.setVariablesMensaje(variables);
                Integer[] aEstados = iRFachadaNotificaciones.enviaNotificaciones(envioNotificacion);
                if (aEstados[0] > 0) {
                    throw new CirculemosNegocioException(ErrorImpugnacion.ValidarFalloImpugnacion.JUR_014001);
                }
            } else {
                LogEnvioCorreoDTO log = iRCirculemosMailSender.enviarCorreo(
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        EnumTipoCorreo.NOTIFICACION_FALLO_IMPUGNACION, aDireccionesDestino, variables, archivos);
                // Actualizacion de auditoria de correo
                log.setTablaSolicitud("trazabilidad_proceso");
                log.setIdTablaSolicitud(falloImpugnacionDTO.getTrazabilidadProceso().getId());
                em.merge(LogEnvioCorreoHelper.toLevel1Entity(log, null));
            }
        }
    }

    @Override
    public RechazoImpugnacionDTO rechazarFallo(ProcesoDTO proceso, RechazoImpugnacionDTO rechazoImpugnacionDTO) {
        logger.debug("ImpugnacionEJB::rechazarFallo(ProcesoDTO, Integer)");
        boolean controlarCambioEstado = false;
        TrazabilidadProcesoDTO traza = null;

        if (iRFachadaProceso.consultarEstadoProceso(proceso.getId()).getId()
                .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION.getId())) {
            // Pasa el proceso a rechazado a back
            traza = iRFachadaProceso.actualizarEstadoProceso(proceso.getId(),
                    EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO_ACONTINUACION, false);
            controlarCambioEstado = true;
        }
        if (!controlarCambioEstado) {
            // Pasa el proceso a rechazado
            traza = iRFachadaProceso.actualizarEstadoProceso(proceso.getId(),
                    EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO, false);
        }
        rechazoImpugnacionDTO.setTrazabilidadProceso(traza);
        RechazoImpugnacion rechazoImpugnacion = RechazoImpugnacionHelper.toLevel1Entity(rechazoImpugnacionDTO, null);
        em.persist(rechazoImpugnacion);
        return rechazoImpugnacionDTO;
    }

    @Override
    public TrazabilidadProcesoDTO aprobarImpugnacion(ProcesoDTO proceso, Long cicomparendo)
            throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::aprobarImpugnacion(ProcesoDTO, Long)");

        FalloImpugnacionDTO falloImpugnacionDTO = consultarUltimoFalloImpugnacion(proceso.getId());
        ComparendoDTO comparendoDTO = iRFachadaComparendo.consultarComparendo(cicomparendo);

        List<ComparendoCarteraDTO> lsComparendoCarteraDTO = comparendoDTO.getComparendoCarteraList();
        if (lsComparendoCarteraDTO != null && !lsComparendoCarteraDTO.isEmpty()) {
            for (ComparendoCarteraDTO comparendoCarteraDTO : lsComparendoCarteraDTO) {
                CarteraDTO carteraDTO = iRFachadaCartera.consultarCartera(comparendoCarteraDTO.getIdCartera());
                if (carteraDTO.getTipoObligacion().getId().equals(EnumTipoObligacion.COMPARENDO.getValue())) {
                    // Consulta el fallo definitivo
                    FalloImpugnacion fallo = em.find(FalloImpugnacion.class, falloImpugnacionDTO.getId());
                    if (!falloImpugnacionDTO.getTipoFallo().getId().equals(EnumTipoFallo.CONDENATORIO.getValue())) {
                        // Si no es condenatorio implica cambios en cartera
                        MovimientoCarteraDTO movimiento = new MovimientoCarteraDTO();
                        movimiento.setIdActividad(EnumActividadCartera.EXONERACION_CARTERA.getValue());
                        MovimientosCarteraDTO movimientosCarteraDTO = new MovimientosCarteraDTO();
                        movimientosCarteraDTO.setCartera(carteraDTO);
                        ConceptoCarteraDTO conceptoCarteraDTO = new ConceptoCarteraDTO();
                        TipoConceptoCarteraDTO tipoConceptoCarteraDTO = new TipoConceptoCarteraDTO();
                        tipoConceptoCarteraDTO.setId(EnumTipoConceptoCartera.CREDITO.getCodigo());
                        conceptoCarteraDTO.setTipoConceptoCartera(tipoConceptoCarteraDTO);
                        conceptoCarteraDTO.setId(EnumConceptoCartera.EXONERACION.getValue());
                        conceptoCarteraDTO.setCodigo(EnumConceptoCartera.EXONERACION.getCodigo());
                        TipoSaldoDTO tipoSaldoDTO = new TipoSaldoDTO();
                        tipoSaldoDTO.setId(EnumTipoSaldo.CAPITAL.getId());
                        conceptoCarteraDTO.setTipoSaldo(tipoSaldoDTO);
                        movimientosCarteraDTO.setConceptoCartera(conceptoCarteraDTO);
                        movimientosCarteraDTO.setFechaCreacion(UtilFecha.buildCalendar().getTime());
                        movimientosCarteraDTO.setFechaMovimiento(UtilFecha.buildCalendar().getTime());
                        movimientosCarteraDTO.setLoginUsuario(iRSeguridadCirculemos.obtenerUsuarioDto().getLogin());
                        movimientosCarteraDTO.setObservaciones(EnumConceptoCartera.EXONERACION.name());
                        if (falloImpugnacionDTO.getTipoFallo().getId()
                                .equals(EnumTipoFallo.CONDENATORIO_PARCIAL.getValue())) {
                            // Aplica un movimiento credito quitandole el porcentaje restante al porcentaje fallado
                            BigDecimal valorDescuento = carteraDTO.getSaldoCapital()
                                    .multiply(
                                            BigDecimal.valueOf((new Double(100) - falloImpugnacionDTO.getPorcentaje())))
                                    .divide(new BigDecimal(100));
                            movimientosCarteraDTO.setValorMovimiento(valorDescuento);
                            movimiento.setMovimientosCartera(movimientosCarteraDTO);
                            movimiento.setSaldoCapital(carteraDTO.getSaldoCapital().subtract(valorDescuento));
                            movimiento.setSaldoIntereses(BigDecimal.ZERO);
                        } else if (falloImpugnacionDTO.getTipoFallo().getId()
                                .equals(EnumTipoFallo.ABSOLUTORIO.getValue())) {
                            movimientosCarteraDTO.setValorMovimiento(carteraDTO.getSaldoCapital());
                            movimiento.setMovimientosCartera(movimientosCarteraDTO);
                            movimiento.setSaldoCapital(BigDecimal.ZERO);
                            movimiento.setSaldoIntereses(BigDecimal.ZERO);
                            carteraDTO.setEstadoObligacion(
                                    new EstadoObligacionDTO(EnumEstadoObligacion.PAGADO.getValue()));
                            carteraDTO.getEstadoObligacion().setCodigo(EnumEstadoObligacion.PAGADO.getCodigo());
                        }
                        iRFachadaCartera.registrarMovimiento(movimiento);
                        // Actualiza valores al fallo definitivo
                        fallo.setValorDescuento(movimientosCarteraDTO.getValorMovimiento());
                        fallo.setValorObligacion(movimiento.getSaldoCapital());
                        em.merge(fallo);
                    } else {
                        // toma los valores actuales en cartera
                        // Actualiza valores al fallo definitivo
                        fallo.setValorDescuento(BigDecimal.ZERO);
                        fallo.setValorObligacion(carteraDTO.getSaldoCapital());
                        em.merge(fallo);
                    }
                    // Actualiza valores en el dto para el cierre del proceso
                    falloImpugnacionDTO.setValorDescuento(fallo.getValorDescuento());
                    falloImpugnacionDTO.setValorObligacion(fallo.getValorObligacion());
                }
            }
        }
        try {
            return cerrarProcesoImpugnacion(falloImpugnacionDTO, proceso.getId(), comparendoDTO);
        } catch (CirculemosAlertaException e) {
            logger.error("Error al generar documento de fallo de impugnacion", e);
            throw new CirculemosNegocioException(ErrorImpugnacion.AprobarFallo.JUR_006001);
        }
    }

    @Override
    public FalloImpugnacionDTO consultarUltimoFalloImpugnacion(Long idProceso) {
        logger.debug("ImpugnacionEJB::consultarUltimoFalloImpugnacion(Long)");
        FalloImpugnacionDTO fallo = null;
        if (idProceso != null) {
            GenericDao<FalloImpugnacion> falloDao = new GenericDao<>(FalloImpugnacion.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();

            jpql.append("SELECT f FROM FalloImpugnacion f");
            jpql.append(" JOIN f.trazabilidadProceso tp");
            jpql.append(" JOIN tp.proceso p");
            jpql.append(" WHERE p.id = :idProceso");
            jpql.append(" ORDER BY tp.fechaInicio DESC");

            filtros.put("idProceso", idProceso);

            List<FalloImpugnacion> fallos = falloDao.buildAndExecuteQuery(jpql, filtros);

            if (!fallos.isEmpty()) {
                fallo = FalloImpugnacionHelper.toLevel1DTO(fallos.get(0));
            }
        }
        return fallo;
    }

    @Override
    public EvaluarImpugnacionBackDTO evaluarExpedienteBack(EvaluarImpugnacionBackDTO evaluar, Long idProceso)
            throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::evaluarExpedienteBack(EvaluarImpugnacionBackDTO,Long)");

        EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso.consultarEstadoProceso(idProceso);
        if (estadoProcesoDTO != null && estadoProcesoDTO.getId()
                .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION.getId())) {
            TrazabilidadProcesoDTO traza = null;

            if (idProceso != null) {
                if (evaluar.getEnviarEspecialista()) {
                    // Cambios de estado
                    traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                            EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION, false);

                    traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                            EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_GESTION_ESPECIALISTA, false);

                    if (traza != null) {
                        // Persistir evaluacion
                        evaluar.setTrazabilidadProceso(traza);
                        EvaluarImpugnacionBack ev = EvaluarImpugnacionBackHelper.toLevel1Entity(evaluar, null);
                        em.persist(ev);
                        return EvaluarImpugnacionBackHelper.toLevel0DTO(ev);
                    }

                } else if (evaluar.getTienePruebas() != null) {

                    // Cambios de estado
                    traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                            EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION, false);

                    if (evaluar.getTienePruebas()) {
                        // Cambios de estado
                        traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                                EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_PRUEBAS, false);

                    }

                    if (traza != null) {
                        // Persistir evaluacion
                        evaluar.setTrazabilidadProceso(traza);
                        EvaluarImpugnacionBack ev = EvaluarImpugnacionBackHelper.toLevel1Entity(evaluar, null);
                        em.persist(ev);
                        return EvaluarImpugnacionBackHelper.toLevel0DTO(ev);
                    }

                }
            } else {
                throw new CirculemosNegocioException(EvaluarExpedienteBack.JUR_007002);
            }

        } else {
            throw new CirculemosNegocioException(EvaluarExpedienteBack.JUR_007001);
        }

        return null;
    }

    @Override
    public void registrarAccionesBack(List<AccionImpugnacionBackDTO> acciones, Long idProceso)
            throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::registrarAccionesBack(List<AccionImpugnacionBackDTO>, Long)");

        checkNotNull(idProceso, "Proceso es obligatorio");

        if (acciones != null && !acciones.isEmpty()) {
            TrazabilidadProcesoDTO traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                    EnumEstadoProceso.ECUADOR_IMPUGNACION_REGISTRO_ACCIONES_ESPECIALISTA, false);

            for (AccionImpugnacionBackDTO accion : acciones) {
                accion.setTrazabilidadProceso(traza);
                em.persist(AccionImpugnacionBackHelper.toLevel1Entity(accion, null));
            }

            iRFachadaProceso.actualizarEstadoProceso(idProceso,
                    EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION, false);
        } else {
            throw new CirculemosNegocioException(RegistrarAcciones.JUR_008001);
        }

    }

    @Override
    public List<AccionImpugnacionBackDTO> consultarAccionesBack(Long idProceso) {
        logger.debug("ImpugnacionEJB::consultarAccionesBack(Long)");

        checkNotNull(idProceso, "Proceso es obligatorio");

        GenericDao<AccionImpugnacionBack> accionDao = new GenericDao<>(AccionImpugnacionBack.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT DISTINCT a FROM AccionImpugnacionBack a ");
        jpql.append("JOIN a.trazabilidadProceso tp ");
        jpql.append("JOIN tp.proceso p ");
        jpql.append("WHERE p.id = :idProceso ");
        jpql.append("ORDER BY a.fechaAccion DESC ");

        filtros.put("idProceso", idProceso);

        List<AccionImpugnacionBack> acciones = accionDao.buildAndExecuteQuery(jpql, filtros);

        if (!acciones.isEmpty()) {
            return AccionImpugnacionBackHelper.toListLevel1DTO(acciones);
        }

        return new ArrayList<AccionImpugnacionBackDTO>();
    }

    @Override
    public void enviarCorreoEvaluarImpugnacion(Long idProceso, Long idDocumento, Long idTraza)
            throws CirculemosNegocioException {
        logger.debug("ImpugnacionEJB::enviarCorreoEvaluarImpugnacion(Long, String, Long, Long)");
        Map<String, Object> variables = new HashMap<>();
        String[] aDireccionesDestino = null;
        Proceso proceso = em.find(Proceso.class, idProceso);
        variables.put("numero_proceso", escapeHtml4(proceso.getNumeroProceso()));
        ParticipanteProcesoDTO participante = new ParticipanteProcesoDTO();
        participante.setProceso(new ProcesoDTO(idProceso));
        List<ParticipanteProcesoDTO> participantes = iRFachadaProceso.consultarParticipantesProceso(participante);
        PersonaDTO persona = null;

        if (!participantes.isEmpty()) {
            List<String> correos = new ArrayList<>();
            for (ParticipanteProcesoDTO part : participantes) {
                persona = part.getPersona();
                List<CorreoPersonaDTO> lsCorreoPersona = fachadaAdminNegocio
                        .consultarCorreosNotificables(persona.getId());
                for (CorreoPersonaDTO correoPersona : lsCorreoPersona) {
                    correos.add(correoPersona.getCorreoElectronico());
                }
            }
            aDireccionesDestino = correos.toArray(new String[correos.size()]);
        }

        OrganismoTransitoDTO organismo = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
        variables.put("organismo", escapeHtml4(organismo.getNombreOrganismo()));

        ValorParametroDTO parametro = iRFachadaNotificaciones.consultarParametroEnvioNotificaciones(
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());

        ArrayList<ArchivoTransportableDTO> archivos = new ArrayList<ArchivoTransportableDTO>();
        ArchivoTransportableDTO archivo = null;
        if (idDocumento != null) {
            try {
                // Consulta el documento para enviarlo en el correo
                ArrayList<Long> documentos = new ArrayList<Long>();
                documentos.add(idDocumento);
                archivo = new ArchivoTransportableDTO(NOMBRE_ARCHIVO_EVALUAR,
                        iRDocumentosCirculemos.consultarDocumentosPDF(documentos));
                archivo.setNumeroDocumento(String.valueOf(idDocumento));
                archivos.add(archivo);
            } catch (CirculemosAlertaException e) {
                logger.error("No se encontró documento para envio de correo de evaluar", e);
            }
        }

        // Envio de correo
        if (aDireccionesDestino != null && aDireccionesDestino.length > BigInteger.ZERO.intValue()) {

            if (parametro != null && parametro.getValorParamBoolean()) {
                EnvioNotificacionDTO envioNotificacion = new EnvioNotificacionDTO();
                List<ConsultarNotificacionesDTO> lsNotificacion = new ArrayList<>();
                ConsultarNotificacionesDTO notificacion = new ConsultarNotificacionesDTO();
                notificacion.setLsCorreoElectronico(Arrays.asList(aDireccionesDestino));
                notificacion.setNombreInfractor(persona.getNombreCompleto());
                notificacion.setCodSeguimientoInt(idProceso);
                notificacion.setExternalId(idTraza);
                notificacion.setLsArchivos(archivos);
                lsNotificacion.add(notificacion);
                envioNotificacion.setLsNotificaciones(lsNotificacion);
                envioNotificacion.setTipoCorreo(EnumTipoCorreo.NOTIFICACION_EVALUACION_IMPUGNACION_ENOTIFICA);
                envioNotificacion.setTipoNotificacion(EnumTipoNotificacion.NOTIFICACION_IMPUGNACIONES);
                envioNotificacion.setVariablesMensaje(variables);
                Integer[] aEstados = iRFachadaNotificaciones.enviaNotificaciones(envioNotificacion);
                if (aEstados[0] > 0) {
                    throw new CirculemosNegocioException(ErrorImpugnacion.EvaluarExpediente.JUR_004003);
                }
            } else {
                LogEnvioCorreoDTO log = iRCirculemosMailSender.enviarCorreo(
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        EnumTipoCorreo.NOTIFICACION_EVALUACION_IMPUGNACION, aDireccionesDestino, variables, archivos);
                // Actualizacion de auditoria de correo
                log.setTablaSolicitud("trazabilidad_proceso");
                log.setIdTablaSolicitud(idTraza);
                em.merge(LogEnvioCorreoHelper.toLevel1Entity(log, null));
            }
        }
    }

    @Override
    public List<HistoricoFalloDTO> consultarHistroricoFallos(Long idProceso) {
        logger.debug("ImpugnacionEJB::consultarHistroricoFallos(Long)");

        checkNotNull(idProceso, "Proceso es obligatorio");
        List<HistoricoFalloDTO> historico = new ArrayList<>();
        StringBuilder jpql = new StringBuilder();

        jpql.append(
                "select p.nombre as revision, c.fecha_inicio as fecha_revision,b.nombre observacion_revision, a.observacion ");
        jpql.append("from trazabilidad_proceso c ");
        jpql.append("left join rechazo_impugnacion a ");
        jpql.append("on a.id_trazabilidad_proceso = c.id_trazabilidad_proceso ");
        jpql.append("left join tipo_rechazo b ");
        jpql.append("on a.id_tipo_rechazo = b.id_tipo_rechazo ");
        jpql.append("left join estado_proceso p ");
        jpql.append("on p.id_estado_proceso = c.id_estado_proceso ");
        jpql.append("where c.id_estado_proceso in (4,5,11) ");
        jpql.append("and c.id_proceso = :idProceso ");
        jpql.append("order by c.fecha_inicio desc ");

        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter("idProceso", idProceso);

        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());
        if (detalles != null) {
            for (Object[] campo : detalles) {
                HistoricoFalloDTO his = new HistoricoFalloDTO();

                his.setRevision(String.valueOf(campo[0]));
                his.setFechaRevision((Date) campo[1]);
                if (campo[2] != null) {
                    his.setObservacionRevision(String.valueOf(campo[2]));
                }

                if (campo[3] != null) {
                    his.setObservacion(String.valueOf(campo[3]));
                }
                historico.add(his);
            }
        }

        return historico;

    }

    @Override
    public MotivacionImpugnacionDTO consultarMotivacionIpugnacion(Long idProceso) {
        logger.debug("ImpugnacionEJB::consultarMotivacionIpugnacion(Long)");

        MotivacionImpugnacionDTO motivacionImpugnacionDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT mi FROM MotivacionImpugnacion mi");
        jpql.append(" JOIN mi.trazabilidadProceso tp");
        jpql.append(" JOIN tp.proceso p");
        jpql.append(" WHERE p.id = :idProceso");
        jpql.append(" ORDER BY tp.fechaInicio DESC");

        Query query = em.createQuery(jpql.toString());

        query.setParameter("idProceso", idProceso);

        @SuppressWarnings("unchecked")
        List<MotivacionImpugnacion> motivacionImpugnacions = query.getResultList();
        if (motivacionImpugnacions != null && !motivacionImpugnacions.isEmpty()) {
            motivacionImpugnacionDTO = MotivacionImpugnacionHelper.toLevel1DTO(motivacionImpugnacions.get(0));
        }

        return motivacionImpugnacionDTO;
    }

}