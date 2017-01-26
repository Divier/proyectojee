package co.com.datatools.c2.negocio.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.cartera.ILCarteraComparendo;
import co.com.datatools.c2.adaptador.dto.RegistroCarteraComparendoDTO;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.dto.EstadoResolucionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.NotificacionComparendoTerceroDTO;
import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.dto.ResolucionRectificacionResoluble;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.TipoResolucionDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ErrorProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.EstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RecibirComparendoLogDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaRecibirComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudOrdenCompaNacioDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.dto.comparendo.TipoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TrazabilidadComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.configuracion.AsociaComparendoFormularioDTO;
import co.com.datatools.c2.dto.configuracion.AsociaDiasIngresoComparendoDTO;
import co.com.datatools.c2.dto.configuracion.AsociaMedioImposicionTiposFechaDTO;
import co.com.datatools.c2.dto.configuracion.ConfiguraMediosImposicionNotificacionDTO;
import co.com.datatools.c2.dto.configuracion.ConfiguracionGradosAlcoholDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.entidades.BloqueoComparendo;
import co.com.datatools.c2.entidades.CampoRectificaComparendo;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoPersona;
import co.com.datatools.c2.entidades.ComparendoResolucion;
import co.com.datatools.c2.entidades.DetalleBloqueo;
import co.com.datatools.c2.entidades.DetalleProcesamiento;
import co.com.datatools.c2.entidades.ErrorProcesamiento;
import co.com.datatools.c2.entidades.OrdenComparendoNacional;
import co.com.datatools.c2.entidades.ProcesaComparendo;
import co.com.datatools.c2.entidades.ProcesaComparendoPersona;
import co.com.datatools.c2.entidades.ProcesaDireccion;
import co.com.datatools.c2.entidades.ProcesaEvidencia;
import co.com.datatools.c2.entidades.RectificaComparendo;
import co.com.datatools.c2.entidades.TipoEvidencia;
import co.com.datatools.c2.entidades.TrazabilidadComparendo;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumOrigenNotificacionTercero;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumProceso;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoSancion;
import co.com.datatools.c2.enumeraciones.EnumCausalCambioEstado;
import co.com.datatools.c2.enumeraciones.EnumComportamientoCartera;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.enumeraciones.EnumEstadoResolucion;
import co.com.datatools.c2.enumeraciones.EnumExtensionArchivoEvidencia;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.enumeraciones.EnumTipoResolucion;
import co.com.datatools.c2.enumeraciones.EnumTipoServicio;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.error.ErrorComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminResolucion;
import co.com.datatools.c2.negocio.fachada.IRFachadaRegistroVehicular;
import co.com.datatools.c2.negocio.helpers.comparendos.CampoEntidadHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.CampoRectificaComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.DetalleProcesamientoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ErrorProcesamientoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.OrdenComparendoNacionalHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaComparendoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaDireccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaEvidenciaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.RecibirComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TrazabilidadComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.ProcesaComparendoHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILAgente;
import co.com.datatools.c2.negocio.interfaces.ILComparendo;
import co.com.datatools.c2.negocio.interfaces.ILProcesaComparendo;
import co.com.datatools.c2.negocio.interfaces.ILRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.ILTrazabilidadComparendo;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoTercero;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.Resoluble;
import co.com.datatools.c2.negocio.interfaces.administracion.ILAdministracionComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.ILInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.ILTarifaInfraccion;
import co.com.datatools.c2.negocio.interfaces.formularios.ILAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.ILFormulario;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.negocio.local.interfaces.IProcesarRecibirComparendo;
import co.com.datatools.c2.negocio.opciones.OpcionGestorFileSystem;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.util.date.UtilFecha;

/**
 * Logica asociada al proceso de recibir y procesar comparendo
 * 
 * @author giovanni.velandia
 * @version 28-Mar-2016 2.21
 */
@Stateless(name = "ProcesarRecibirComparendoEJB")
@LocalBean
public class ProcesarRecibirComparendoEJB implements IProcesarRecibirComparendo {
    private final static Logger logger = Logger.getLogger(ProcesarRecibirComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILComparendo iLComparendo;
    @EJB
    private ILInfraccion iLInfraccion;
    @EJB
    private IRFachadaAdminGeneral iFachadaAdminGeneral;
    @EJB
    private IRFachadaAdminNegocio iFachadaAdminNegocio;
    @EJB
    private IRFachadaConfiguracion iRFachadaConfiguracion;
    @EJB
    private ILAdministracionComparendo adminComparendoEjb;
    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;
    @EJB
    private IRRepositorioArchivo iRRepositorioArchivo;
    @EJB
    private ILAdministracionFormularios iLAdministracionFormularios;
    @EJB
    private ILFormulario iLFormulario;
    @EJB
    private ILAgente iLAgente;
    @EJB
    private ILCarteraComparendo iCarteraComparendo;
    @EJB
    private ILTarifaInfraccion iLTarifaInfraccion;
    @EJB
    private IRFachadaRegistroVehicular iRFachadaRegistroVehicular;
    @EJB
    private ILTrazabilidadComparendo iLTrazabilidadComparendo;
    @EJB
    private IRFachadaAdminResolucion fachadaAdminResolucionEJB;
    @EJB
    private IRNotificacionComparendoTercero irNotificacionComparendoTercero;
    @EJB
    private IProcesarRecibirComparendo iProcesarComparendo;
    @EJB
    private ILRecibirComparendo ilRecibirComparendo;
    @EJB
    private ILProcesaComparendo iLProcesaComparendo;

    private static String CEDULA_CERO = "0";

    private final static ILog logC2 = LoggerC2.getLogger(EnumLogSistema.PROCESAR_COMPARENDO);

    @Override
    public void registroDetalleBloqueo(RespuestaValidacionDTO respuestaValidacionDTO,
            ProcesaComparendoDTO procesaComparendoDTO) {

        BloqueoComparendo bloqueoComparendo = new BloqueoComparendo();
        // Lista de detalles bloqueantes
        List<DetalleBloqueo> detalleBloqueos = new ArrayList<DetalleBloqueo>();
        for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacionDTO.getDetalleProcesamientoDTOs()) {
            DetalleBloqueo detalleBloqueo = new DetalleBloqueo();
            // bloqueo comparendo
            detalleBloqueo.setBloqueoComparendo(bloqueoComparendo);
            // Campo entidad
            detalleBloqueo.setCampoEntidad(CampoEntidadHelper.toLevel1Entity(detalleProcesamientoDTO.getCampoEntidad(),
                    detalleBloqueo.getCampoEntidad()));
            // Error procesamiento
            detalleBloqueo.setErrorProcesamiento(ErrorProcesamientoHelper.toLevel1Entity(
                    detalleProcesamientoDTO.getErrorProcesamiento(), detalleBloqueo.getErrorProcesamiento()));
            detalleBloqueos.add(detalleBloqueo);
        }
        // lista de detalles bloqueos
        bloqueoComparendo.setDetalleBloqueantes(detalleBloqueos);
        // Fecha de registro
        bloqueoComparendo.setFechaRegistro(Calendar.getInstance().getTime());
        // Numero de comparendo
        bloqueoComparendo.setNumeroComparendo(procesaComparendoDTO.getNumeroComparendo());
        // codigo del origen del comparendo
        bloqueoComparendo.setCodigoOrigen(procesaComparendoDTO.getCodigoOrigen());
        // Codigo infraccion
        bloqueoComparendo.setCodigoInfraccion(procesaComparendoDTO.getCodigoInfraccion());
        // Fecha infraccion
        bloqueoComparendo.setFechaInfraccion(procesaComparendoDTO.getFechaInfraccion());
        // Hora infraccion
        bloqueoComparendo.setHoraInfraccion(procesaComparendoDTO.getHoraInfraccion());
        // Placa Vehiculo
        bloqueoComparendo.setPlacaVehiculo(procesaComparendoDTO.getPlacaVehiculo());
        // Infractor
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getValue())) {
                // Apellido infractor 1
                bloqueoComparendo.setApellido1Infractor(procesaComparendoPersonaDTO.getApellido1());
                // Apelliso infractor 2
                bloqueoComparendo.setApellido2Infractor(procesaComparendoPersonaDTO.getApellido2());
                // Tipo Identificacion Infractor
                bloqueoComparendo
                        .setIdTipoIdentificacionInfractor(procesaComparendoPersonaDTO.getIdTipoIdentificacion());
                // Nombre infractor 1
                bloqueoComparendo.setNombre1Infractor(procesaComparendoPersonaDTO.getNombre1());
                // Nombre infractor 2
                bloqueoComparendo.setApellido2Infractor(procesaComparendoPersonaDTO.getNombre2());
                // Numero identificacion infractor
                bloqueoComparendo
                        .setNumeroIdentificacionInfractor(procesaComparendoPersonaDTO.getNumeroIdentificacion());
                break;
            }
        }

        // Usuario
        // Obtiene usuario que registra el comparendo
        if (procesaComparendoDTO.getUsuarioPersona() != null) {
            if (procesaComparendoDTO.getUsuarioPersona().getUsuario().getId() == null) {
                bloqueoComparendo.setUsuarioPersona(UsuarioPersonaHelper.toLevel1Entity(
                        iRSeguridadCirculemos.obtenerUsuarioDto(), bloqueoComparendo.getUsuarioPersona()));
            } else {
                // Usuario
                bloqueoComparendo.setUsuarioPersona(UsuarioPersonaHelper.toLevel1Entity(
                        procesaComparendoDTO.getUsuarioPersona(), bloqueoComparendo.getUsuarioPersona()));
            }
        } else {
            bloqueoComparendo.setUsuarioPersona(UsuarioPersonaHelper
                    .toLevel1Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), bloqueoComparendo.getUsuarioPersona()));
        }
        em.persist(bloqueoComparendo);
        bloqueoComparendo.setDetalleBloqueantes(detalleBloqueos);
    }

    @Override
    public boolean verificarComparendoResevado(String numeroComparendo) {
        logger.debug("RecibirComparendoEJB::verificarComparendoResevado(String)");
        SolicitudOrdenCompaNacioDTO solicitudOrdenCompaNacioDTO = new SolicitudOrdenCompaNacioDTO();
        solicitudOrdenCompaNacioDTO.setNumeroComparendo(numeroComparendo);
        return iLComparendo.existeSolicitudOrdenComparendoNacional(solicitudOrdenCompaNacioDTO);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public TarifaLiquidacionDTO liquidarTarifaInfraccion(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::liquidarTarifaInfraccion(ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");
        TarifaLiquidacionDTO tarifaLiquidacionDTO = null;

        // Consultamos el parametro para liquidar cartera
        ValorParametroDTO valorParametroDTO = consultarValorParametro(EnumParametro.ORGANISMO_LIQUIDA,
                procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());
        boolean liquidaTarifa = valorParametroDTO.getValorParamBoolean();
        if (liquidaTarifa) {
            tarifaLiquidacionDTO = iLTarifaInfraccion.liquidarTarifaInfraccion(
                    RecibirComparendoHelper.toLiquidarTarifa(procesaComparendoDTO, configuracionInfraccionDTO));
        } else {
            // Valor enviado por el cliente para la liquidacion de la tarifa
            tarifaLiquidacionDTO = new TarifaLiquidacionDTO();
            tarifaLiquidacionDTO.setValorLiquidado(procesaComparendoDTO.getValorConcepto());
        }

        return tarifaLiquidacionDTO;
    }

    /**
     * Metodo que se encarga de validar campos que son necesario para la consulta de la configuracion de infraccion sin estos campos no se puede
     * continuar con el procesamiento
     * 
     * @param procesaComparendoDTO
     * @return
     */
    public void validacionesCamposProcesarComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validacionesCamposProcesarComparendo(ProcesarComparendoDTO)");

        // Codigo de la infraccion
        if (procesaComparendoDTO.getCodigoInfraccion() == null
                || procesaComparendoDTO.getCodigoInfraccion().isEmpty()) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.INFRACCION,
                    EnumErrorProcesamiento.INFRACCION_INVALIDA, respuestaValidacionDTO);
        }

        // Fecha Infraccion (imposicion comparendo)
        if (procesaComparendoDTO.getFechaInfraccion() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.FECHA_INFRACCION,
                    EnumErrorProcesamiento.FECHA_INFRACCION_INVALIDA, respuestaValidacionDTO);
        } else {
            if (procesaComparendoDTO.getFechaInfraccion().after(Calendar.getInstance().getTime())) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.FECHA_INFRACCION,
                        EnumErrorProcesamiento.FECHA_INFRACCION_INVALIDA_DIA, respuestaValidacionDTO);
            }
        }

        // Hora de imposicion del comparendo (Infraccion)
        if (procesaComparendoDTO.getHoraInfraccion() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.HORA_INFRACCION,
                    EnumErrorProcesamiento.HORA_INFRACCION_INVALIDAD, respuestaValidacionDTO);
        }
    }

    @Override
    public void origenValidacion(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::origenValidacion(ProcesarComparendoDTO)");

        // Origen validacion
        if (procesarComparendoDTO.getEnumProcesamiento() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FALTA_CAMPO_ORIGEN_VALIDACION,
                    respuestaValidacionDTO);
        } else {

            // **** PASO 3 ****
            // Validacion numero comparendo no se encuentre procesado con las condiciones de procesamiento validacion CORREGIR_INCONSISTENCIA
            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.CORREGIR_INCONSISTENCIA)) {

                // Valida que el “Número de Comparendo” modificado mediante la corrección de inconsistencias NO este PROCESADO
                // Verificamos la existencia del comparendo
                boolean existe = existeComparendo(procesarComparendoDTO.getProcesaComparendoDTO());

                if (existe) {
                    // ERROR
                    errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                            EnumErrorProcesamiento.COMPARENDO_YA_EXISTE, respuestaValidacionDTO);
                }

                /*
                 * Verificamos que el id del procesa comparendo se encuentre de lo contrario se busca por el numero del comparendo
                 */

                ProcesaComparendoDTO procesaComparendoDTO = iProcesarComparendo
                        .consultarProcesaComparendo(procesarComparendoDTO.getProcesaComparendoDTO().getId());
                // Valida que el “Número de Comparendo” este almacenado como INCONSISTENTE
                // Verificamos la existencia del procesa comparendo
                boolean existeComparendo = false;
                if (procesaComparendoDTO != null) {
                    existeComparendo = true;
                }

                if (!existeComparendo) {
                    // ERROR
                    errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                            EnumErrorProcesamiento.NO_EXISTE_COMPARENDO_INCONSISTENTE, respuestaValidacionDTO);
                }

                // Validar que el codigo de origen del comparendo modificado sea igual al comparendo a correguir
                if (procesaComparendoDTO.getCodigoOrigen() != null) {
                    if (!procesaComparendoDTO.getCodigoOrigen()
                            .equals(procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrigen())) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.CORRECCION_CODIGO_ORIGEN_DISTINTO,
                                respuestaValidacionDTO);
                    }
                } else {
                    if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrigen() != null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.CORRECCION_CODIGO_ORIGEN_DISTINTO,
                                respuestaValidacionDTO);
                    }
                }

                // Verificar que le numero del comparendo no se modifico en el proceso de correccion de inconsistencias
                // Verificamos que no tenga informacion el origen de validacion
                if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrigen() == null) {
                    if (!procesaComparendoDTO.getNumeroComparendo()
                            .equals(procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo())) {
                        validarExistenciaComparendo(procesarComparendoDTO.getProcesaComparendoDTO(),
                                respuestaValidacionDTO);

                    }
                }
            }

            // Para el caso de recibir comparendo verificamos que no se encuentre almacenado en procesar comparendo
            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECIBIR_COMPARENDO)) {
                validarExistenciaComparendo(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);
            }

            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
                validarRectificaComparendo(
                        procesarComparendoDTO.getProcesaComparendoDTO().getComparendo().getCicomparendo());
            }
        }
    }

    @Override
    public RespuestaRecibirComparendoDTO validarRectificaComparendo(Long cicomparendo) {
        logger.debug("RecibirComparendoEJB::validarRectificaComparendo(ProcesarComparendoDTO)");
        RespuestaRecibirComparendoDTO respuestaValidacionDTO = null;

        ComparendoDTO comparendoDTO = iLComparendo.consultarComparendo(cicomparendo);

        // Valida que el “Número de comprendo” a rectificar tenga un “estado de comparendo” IGUAL a Vigente.
        if (comparendoDTO.getEstadoComparendo().getCodigo().equals(EnumEstadoComparendo.VIGENTE.getCodigo())) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_NO_VIGENTE, respuestaValidacionDTO);
        }

        // Valida que la “Cantidad de veces rectificado” del “Número de comprendo” a rectificar NO supere la cantidad configurada en el
        // parámetro
        // general “Cantidad de veces a rectificar”.
        ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.MAX_CANT_RECTIFICAR_COMPA,
                comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(), true);

        Integer cantidadRect = iLComparendo.consultarCantidadRectificaciones(comparendoDTO.getCicomparendo());
        if (cantidadRect >= valorParametroDTO.getValorParamInt()) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.NUMERO_RECTIFICACIONES_PERMITIDAS_SUPERADO, respuestaValidacionDTO);
        }

        // Valida en la trazabilidad del “Número de comprendo” a rectificar que NO tenga un registro indicando que el comparendo esta o ha estado
        // en
        // un proceso de impugnación.
        boolean existeProcesoImpugnacio = iLTrazabilidadComparendo.existeProceso(comparendoDTO.getCicomparendo(),
                EnumProceso.PROCESO_IMPUGNACION.getCodigo());
        if (existeProcesoImpugnacio) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_PROCESO_INPUGNACION, respuestaValidacionDTO);
        }

        // Valida en la trazabilidad del “Número de comprendo” a rectificar que NO tenga un registro que indique que el comparendo es o ha sido
        // parte
        // de una financiación.
        boolean existeProcesoFinanciancion = iLTrazabilidadComparendo.existeProceso(comparendoDTO.getCicomparendo(),
                EnumProceso.PROCESO_FINANCIACION.getCodigo());
        if (existeProcesoFinanciancion) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_PROCESO_FINANCIACION, respuestaValidacionDTO);
        }

        // Valida en la trazabilidad del “Número de comprendo” a rectificar que NO tenga un registro indicando que el comparendo esta o ha estado
        // en
        // un Cobro Coactivo.
        boolean existeProcesoCobroCoactivo = iLTrazabilidadComparendo.existeProceso(comparendoDTO.getCicomparendo(),
                EnumProceso.PROCESO_COBRO_COACTIVO.getCodigo());
        if (existeProcesoCobroCoactivo) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_PROCESO_COACTIVO, respuestaValidacionDTO);
        }

        // Valida que para el “Número de comprendo” NO TIENE pagos asociados a la respectiva obligación en cartera, bajo la inclusión del caso
        // de uso
        // “Consultar Pagos”.
        boolean existePagos = iLComparendo.existePago(comparendoDTO.getCicomparendo());
        if (existePagos) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_PROCESO_PAGOS_REALIZADOS, respuestaValidacionDTO);
        }
        return respuestaValidacionDTO;
    }

    @Override
    public Long registrarProcesaComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::registrarProcesaComparendo(ProcesarComparendoDTO,RespuestaValidacionDTO)");
        ProcesaComparendo procesaComparendo = null;
        Long idProcesaComparendo = null;
        if (procesarComparendoDTO.getProcesaComparendoDTO().getDetalleProcesamientos() != null
                || !procesarComparendoDTO.getProcesaComparendoDTO().getDetalleProcesamientos().isEmpty()) {

            // Usuario
            // Obtiene usuario que registra el comparendo
            if (procesarComparendoDTO.getProcesaComparendoDTO().getUsuarioPersona() != null) {
                if (procesarComparendoDTO.getProcesaComparendoDTO().getUsuarioPersona().getUsuario().getId() == null) {
                    procesarComparendoDTO.getProcesaComparendoDTO()
                            .setUsuarioPersona(iRSeguridadCirculemos.obtenerUsuarioDto());
                } else {
                    // Usuario
                    procesarComparendoDTO.getProcesaComparendoDTO()
                            .setUsuarioPersona(procesarComparendoDTO.getProcesaComparendoDTO().getUsuarioPersona());
                }
            } else {
                procesarComparendoDTO.getProcesaComparendoDTO()
                        .setUsuarioPersona(iRSeguridadCirculemos.obtenerUsuarioDto());
            }

            procesaComparendo = ProcesaComparendoHelper.toLevel1Entity(procesarComparendoDTO.getProcesaComparendoDTO(),
                    new ProcesaComparendo());
            // Fecha operacion
            procesaComparendo.setFechaOperacion(Calendar.getInstance().getTime());

            /*
             * Registro detalle procesadmiento
             */
            procesaComparendo.setDetalleProcesamientos(new ArrayList<DetalleProcesamiento>());
            for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacionDTO
                    .getDetalleProcesamientoDTOs()) {
                DetalleProcesamiento detalleProcesamiento = DetalleProcesamientoHelper
                        .toLevel1Entity(detalleProcesamientoDTO, new DetalleProcesamiento());
                detalleProcesamiento.setProcesaComparendo(procesaComparendo);
                procesaComparendo.getDetalleProcesamientos().add(detalleProcesamiento);
            }

            /*
             * Procesa direccion
             */
            // Patio
            if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionPatio() != null) {
                ProcesaDireccion procesaDireccionPatio = ProcesaDireccionHelper.toLevel1Entity(
                        procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionPatio(),
                        new ProcesaDireccion());
                em.persist(procesaDireccionPatio);
                procesaComparendo.setProcesaDireccionPatio(procesaDireccionPatio);
            }

            // Comparendo
            ProcesaDireccion procesaDireccionComparendo = ProcesaDireccionHelper.toLevel1Entity(
                    procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionComparendo(),
                    new ProcesaDireccion());
            em.persist(procesaDireccionComparendo);
            procesaComparendo.setProcesaDireccionComparendo(procesaDireccionComparendo);

            em.persist(procesaComparendo);
            procesarComparendoDTO.getProcesaComparendoDTO().setId(procesaComparendo.getId());
            idProcesaComparendo = procesaComparendo.getId();

            /*
             * Procesa comparendo persona
             */
            registrarProcesaComparendoPersona(procesarComparendoDTO);

            /*
             * Procesa evidencia
             */
            try {
                registrarProcesaEvidencias(procesarComparendoDTO);
            } catch (CirculemosAlertaException e) {
                logger.error("Error al guardar archivo de procesa evidencia en el repositorio");

                respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                        .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.EVIDENCIA,
                                EnumErrorProcesamiento.PROBLEMAS_ALMACENANDO_EVIDENCIAS));
                respuestaValidacionDTO.setExisteAlerta(true);
            }
        }
        return idProcesaComparendo;
    }

    /**
     * Metodo que se encarga de registrar un comparendo con todas sus trazabilidades, validando la informacion de detalle proceso
     * 
     * @param procesarComparendoDTO
     * @author giovanni.velandia
     * @param respuestaValidacionDTO
     * @throws CirculemosAlertaException
     * @throws CirculemosNegocioException
     */
    public ComparendoDTO registrarComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO, ConfiguracionInfraccionDTO configuracionInfraccionDTO) {
        logger.debug("RecibirComparendoEJB::registrarComparendo(ProcesarComparendoDTO)");

        ComparendoDTO comparendoDTO = null;

        if (procesarComparendoDTO.getProcesaComparendoDTO().getDetalleProcesamientos() == null
                || procesarComparendoDTO.getProcesaComparendoDTO().getDetalleProcesamientos().isEmpty()) {

            // Guarda OCN
            Long idOcn = registrarOrdenComparendoNaciona(procesarComparendoDTO.getProcesaComparendoDTO());

            // Crea dto para poner la relacion con las entidades
            ComparendoDTO comparendoDTOTemp = new ComparendoDTO();
            comparendoDTOTemp.setCicomparendo(idOcn);
            procesarComparendoDTO.getProcesaComparendoDTO().setComparendo(comparendoDTOTemp);

            // Grado de alcoholemia
            consultarGradoAlcoholemia(procesarComparendoDTO.getProcesaComparendoDTO());

            comparendoDTO = ProcesaComparendoHelperExtend.ToComparendoDto
                    .convert(procesarComparendoDTO.getProcesaComparendoDTO());

            if (comparendoDTO.getComparendoAgente() != null) {// TODO cambiado para que lo encuentre Giovanni
                comparendoDTO.setPlacaAgenteTransito(comparendoDTO.getComparendoAgente().getPlaca());
                if (comparendoDTO.getComparendoAgente().getComparendo() == null) {
                    comparendoDTO.setComparendoAgente(null);
                }
            }

            // Usuario
            // Obtiene usuario que registra el comparendo
            if (procesarComparendoDTO.getProcesaComparendoDTO().getUsuarioPersona() != null) {
                if (procesarComparendoDTO.getProcesaComparendoDTO().getUsuarioPersona().getUsuario().getId() == null) {
                    comparendoDTO.setUsuarioPersona(iRSeguridadCirculemos.obtenerUsuarioDto());
                } else {
                    // Usuario
                    comparendoDTO
                            .setUsuarioPersona(procesarComparendoDTO.getProcesaComparendoDTO().getUsuarioPersona());
                }
            } else {
                comparendoDTO.setUsuarioPersona(iRSeguridadCirculemos.obtenerUsuarioDto());
            }
            // Infraccion
            comparendoDTO.getInfraccion().setId(configuracionInfraccionDTO.getInfraccion().getId());
            // Fecha registro
            comparendoDTO.setFechaModificacion(comparendoDTO.getFechaRegistro());
            comparendoDTO.setCarteraGenerada(false);

            // Guardar las direcciones
            if (comparendoDTO.getDireccion() != null) {
                RespuestaIngresarDireccionDTO respuestaDir = iFachadaAdminGeneral
                        .registrarDireccion(comparendoDTO.getDireccion(), EnumTipoValidacionDireccion.INFRACCION);
                comparendoDTO.getDireccion().setId(respuestaDir.getIdDireccion());
            }
            if (comparendoDTO.getComparendoPatio() != null
                    && comparendoDTO.getComparendoPatio().getDireccion() != null) {
                RespuestaIngresarDireccionDTO respuestaDir = iFachadaAdminGeneral.registrarDireccion(
                        comparendoDTO.getComparendoPatio().getDireccion(), EnumTipoValidacionDireccion.INFRACCION);
                comparendoDTO.getComparendoPatio().getDireccion().setId(respuestaDir.getIdDireccion());
            }
            if (comparendoDTO.getPersonaList() != null) {
                for (ComparendoPersonaDTO comparendoPersonaDTO : comparendoDTO.getPersonaList()) {
                    if (comparendoPersonaDTO != null) {
                        comparendoPersonaDTO
                                .setFechaInicio(procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion());
                        if (comparendoPersonaDTO.getDireccion() != null) {
                            RespuestaIngresarDireccionDTO respuestaDir = iFachadaAdminGeneral.registrarDireccion(
                                    comparendoPersonaDTO.getDireccion(), EnumTipoValidacionDireccion.INFRACCION);
                            comparendoPersonaDTO.getDireccion().setId(respuestaDir.getIdDireccion());
                        }

                        /*
                         * Licencia de conducir Se debe consultar si existe la licencia de tránsito y asociarla a comparendo_persona implementando
                         */
                        if (comparendoPersonaDTO.getNumeroLicencia() != null) {
                            iRFachadaRegistroVehicular.consultarLicencia(comparendoPersonaDTO.getNumeroLicencia(),
                                    comparendoPersonaDTO.getOrganismoTransito().getCodigoOrganismo());
                        }
                    }
                }
            }

            // Estado comparendo
            EstadoComparendoDTO estadoComparendo = new EstadoComparendoDTO();
            estadoComparendo.setId(EnumEstadoComparendo.REGISTRADO.getCodigo());
            comparendoDTO.setEstadoComparendo(estadoComparendo);

            // **** **** PASO 23 FLUJO BASICO **** ****
            // Genera la trazabilidad PASO 23 Flujo Basico
            comparendoDTO.setTrazabilidadComparendoList(new ArrayList<TrazabilidadComparendoDTO>());
            TrazabilidadComparendoDTO trazabilidad = null;
            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.CORREGIR_INCONSISTENCIA)
                    || procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECIBIR_COMPARENDO)) {
                trazabilidad = RecibirComparendoHelper.generarTrazabilidadComparendoDTO(comparendoDTO,
                        EnumActividad.IMPOSICION_DEL_COMPARENDO,
                        UtilFecha.setHoraFecha(comparendoDTO.getFechaInfraccion(), comparendoDTO.getHoraInfraccion()),
                        iRSeguridadCirculemos.obtenerUsuarioDto());
                comparendoDTO.getTrazabilidadComparendoList().add(trazabilidad);

                trazabilidad = RecibirComparendoHelper.generarTrazabilidadComparendoDTO(comparendoDTO,
                        EnumActividad.REGISTRO_DEL_COMPARENDO, Calendar.getInstance().getTime(),
                        iRSeguridadCirculemos.obtenerUsuarioDto());
                comparendoDTO.getTrazabilidadComparendoList().add(trazabilidad);
            }

            // Registra Evidencias PASO 23
            try {
                registrarArchivosEvidencias(comparendoDTO);
            } catch (CirculemosAlertaException e) {
                logger.error("Error al guardar archivo de evidencia en el repositorio");
                comparendoDTO.setEvidenciaList(null);
                respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                        .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.EVIDENCIA,
                                EnumErrorProcesamiento.PROBLEMAS_ALMACENANDO_EVIDENCIAS));
                respuestaValidacionDTO.setExisteAlerta(true);
            }

            // Registramos el compRarendo agregando el estado de registro
            Comparendo comparendo = ComparendoHelperExtend.toComparendo(comparendoDTO, null);

            // Guarda comparendo
            em.persist(comparendo);
            // Actualizar los datos del ComparendoDTO
            comparendoDTO = ComparendoHelperExtend.toComparendo(comparendo);
        }
        return comparendoDTO;
    }

    @Override
    public DetalleProcesamientoDTO crearDetalleProcesamiento(EnumCamposDetalleComparendo enumCamposDetalleComparendo,
            EnumErrorProcesamiento enumErrorProcesamiento) {
        logger.debug(
                "RecibirComparendoEJB::crearDetalleProcesamiento(Integer,ProcesarComparendoDTO,EnumErrorProcesamiento)");
        DetalleProcesamientoDTO detalleProcesamientoDTO = new DetalleProcesamientoDTO();

        // Campo entidad
        if (enumCamposDetalleComparendo != null) {
            CampoEntidadDTO campoEntidadDTO = new CampoEntidadDTO();
            campoEntidadDTO.setCodigo(enumCamposDetalleComparendo.getCodigoCampo());
            detalleProcesamientoDTO.setCampoEntidad(campoEntidadDTO);
        }

        // Error Procesamiento
        ErrorProcesamientoDTO errorProcesamientoDTO = consultarErrorProcesamiento(enumErrorProcesamiento);
        detalleProcesamientoDTO.setErrorProcesamiento(errorProcesamientoDTO);

        return detalleProcesamientoDTO;
    }

    /**
     * Metodo que se encarga de guardar los archivos de evidencias del comparendo
     * 
     * @author julio.pinzon
     * @param procesarComparendoDTO
     * @throws CirculemosAlertaException
     */
    private void registrarArchivosEvidencias(ComparendoDTO comparendoDTO) throws CirculemosAlertaException {
        logger.debug("RecibirComparendoEJB::registrarEvidencias(ComparendoDTO)");
        // Validamos que las evidencias llege diligenciadas
        if (comparendoDTO.getEvidenciaList() != null && !comparendoDTO.getEvidenciaList().isEmpty()) {
            int consecutivoImagen = 0;
            for (EvidenciaDTO evidenciaDTO : comparendoDTO.getEvidenciaList()) {
                consecutivoImagen++;

                Calendar fechaInfraccion = Calendar.getInstance();
                fechaInfraccion.setTime(comparendoDTO.getFechaInfraccion());

                // A�o
                int ano = fechaInfraccion.get(Calendar.YEAR);
                // Mes
                int mes = fechaInfraccion.get(Calendar.DAY_OF_MONTH);
                mes = mes + 1;

                evidenciaDTO.getArchivoTransportable()
                        .setRuta(String.format(EnumCategoriaDocumento.EVIDENCIA_COMPARENDO.getFormato(), ano, mes));

                OpcionGestorFileSystem ogfs = new OpcionGestorFileSystem();
                evidenciaDTO.getArchivoTransportable()
                        .setNombre(comparendoDTO.getIdFacturaAxis() + "_" + consecutivoImagen + "."
                                + FilenameUtils.getExtension(evidenciaDTO.getArchivoTransportable().getNombre()));

                ogfs.setUbicacion(evidenciaDTO.getArchivoTransportable().getRuta());

                String idDocumento = iRRepositorioArchivo.registrarDocumento(null,
                        evidenciaDTO.getArchivoTransportable(), ogfs);
                evidenciaDTO.setIdDocumento(idDocumento);
                evidenciaDTO.setFechaEvidencia(new Date());
            }
        }
    }

    @Override
    public void validacionesCamposProcesarComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, RespuestaValidacionDTO respuestaValidacionDTO)
            throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::validacionesCamposProcesarComparendo(ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");

        // Tipo agente impositor
        if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoAgenteImpositor() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.TIPO_AGENTE_IMPOSITOR,
                    EnumErrorProcesamiento.FALTA_TIPO_AGENTE_IMPOSITOR, respuestaValidacionDTO);
        }

        // INFRACCION

        // Medio de imposicion (Tipo de comparendo)
        if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.MEDIO_IMPOSICION,
                    EnumErrorProcesamiento.FALTA_TIPO_COMPARENDO, respuestaValidacionDTO);
        }

        // Numero de comparendo
        // Esta validacion se deja a negocio de cada pais

        // Fecha de registro
        if (procesarComparendoDTO.getProcesaComparendoDTO().getFechaRecepcion() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.FECHA_RECEPCION,
                    EnumErrorProcesamiento.FALTA_FECHA_REGISTRO, respuestaValidacionDTO);
        }

        // Fecha Infraccion(imposicion comparendo) esta se realiza al comienzo del metodo recibir el comparendo, es necesario para la consulta de las
        // configuraciones

        // Hora de imposicion del comparendo (Infraccion) esta se realiza al comienzo del metodo recibir el comparendo

        // Campos alcoholemia
        validarCamposAlcoholemia(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);

        // Organismo de transito
        if (procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito() == null || procesarComparendoDTO
                .getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.ORGANISMO_TRANSITO,
                    EnumErrorProcesamiento.FALTA_CAMPO_ORGANISMO_TRANSITO, respuestaValidacionDTO);
        } else {
            OrganismoTransitoDTO organismoTransitoDTO = consultarOrganismoTransito(
                    procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
            if (organismoTransitoDTO != null) {
                if (!organismoTransitoDTO.getActivo()) {
                    // ERROR
                    errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.ORGANISMO_TRANSITO,
                            EnumErrorProcesamiento.ORGANISMO_TRANSITO_INACTIVO, respuestaValidacionDTO);
                }
            } else {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.ORGANISMO_TRANSITO,
                        EnumErrorProcesamiento.ORGANISMO_TRANSITO_INEXISTENTE, respuestaValidacionDTO);
            }
        }

        // Dirección de imposición del comparendo
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionComparendo() == null) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.DIRECCION_COMPARENDO,
                    EnumErrorProcesamiento.FALTA_DIRECCION_IMPOSICION, respuestaValidacionDTO);
        }

        // Codigo de la infraccion se valida al comienzo del metodo recibir el comparendo

        // Fecha de notificacion
        if (procesarComparendoDTO.getProcesaComparendoDTO().getFechaNotificacion() != null) {
            if (procesarComparendoDTO.getProcesaComparendoDTO().getFechaNotificacion()
                    .after(Calendar.getInstance().getTime())) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.FECHA_NOTIFICACION,
                        EnumErrorProcesamiento.FECHA_NOTIFICACION_INVALIDA_DIA, respuestaValidacionDTO);
            }
            if (procesarComparendoDTO.getProcesaComparendoDTO().getFechaNotificacion()
                    .before(procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion())) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.FECHA_NOTIFICACION,
                        EnumErrorProcesamiento.FECHA_NOTIFICACION_INVALIDA, respuestaValidacionDTO);
            }
        }

        // Evidencia del comparendo
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaEvidencias() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaEvidencias().isEmpty()) {
            // Tipo de evidencia
            validarCamposEvidencias(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);
        }

        // Origen validacion
        // Se realizar en validacion posterior des pues de acabar este metodo

        // DATOS INFRACTOR
        // Infractor
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas().isEmpty()) {
            validarCamposInfractor(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);

        }

        // Organismo de transito de la licencia de transito
        if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrganismoLicenciaTransito() != null) {
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(
                    procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrganismoLicenciaTransito());
            OrganismoTransitoDTO organismoTransitoRDTO = consultarOrganismoTransito(
                    organismoTransitoDTO.getCodigoOrganismo());
            // Validamos si esta activo
            if (organismoTransitoRDTO != null) {
                if (!organismoTransitoRDTO.getActivo()) {
                    // ERROR
                    errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.ORGANISMO_TRANSITO_INACTIVO_LIC_TRAN,
                            respuestaValidacionDTO);
                }
            } else {
                // ERROR
                errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.ORGANISMO_TRANSITO_INEXISTENTE_LIC_COND,
                        respuestaValidacionDTO);
            }
        }

        // Existe fuga del infractor
        // Se validan la fuga de la infracion por instalicion de pais

        // DATOS DEL PROPIETARIO
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas().isEmpty()) {
            validarCamposPropietario(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);

        }

        // DATOS DE LA EMPRESA
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas().isEmpty()) {
            validarCamposEmpresa(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);

        }

        // DATOS AGENTE
        // Se validan los datos del agente por instancia del pais de instalacion

        // DATOS DEL PATIO
        // placa de la grua
        // No hay validaciones de obligatoriedad

        // TESTIGO
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas().isEmpty()) {
            validarCamposTestigo(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);
        }
    }

    /**
     * Metodo que se encarga de validar los campos de alcholemia
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @return
     */
    private void validarCamposAlcoholemia(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarCamposAlcoholemia(ProcesarComparendoDTO)");

        // Fecha alcolemia
        if (procesaComparendoDTO.getFechaPruebaAlcoholemia() != null) {
            if (!UtilFecha.betweenDate(procesaComparendoDTO.getFechaInfraccion(), Calendar.getInstance().getTime(),
                    procesaComparendoDTO.getFechaPruebaAlcoholemia())) {
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.FECHA_PRUEBA_ALCOHOLEMIA,
                        EnumErrorProcesamiento.PRUEBA_ALCOHOLEMIA_INVALIDA, respuestaValidacionDTO);
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean validarFormatoNumeroComparendo(ProcesaComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::validarFormatoNumeroComparendo(ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");
        boolean valido = iLComparendo.validarFormatoNumeroComparendo(procesarComparendoDTO.getNumeroComparendo(),
                configuracionInfraccionDTO.getInfraccion().getTipoComparendo().getId());
        return valido;
    }

    /**
     * valida los campos de los datos del infractor
     * 
     * @param procesaComparendoDTO
     * @return {@link RespuestaValidacionDTO}
     * @throws CirculemosNegocioException
     */
    private void validarCamposInfractor(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarCamposInfractor(ProcesarComparendoDTO)");

        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getValue())) {
                // Tipo de documento del infractor
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.TIPO_DOCUMENTO_DEL_INFRACTOR,
                                EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_INFRACTOR, respuestaValidacionDTO);
                    }
                }

                // Numero del infractor
                boolean existeTipoDocumento = false;
                if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {

                    existeTipoDocumento = true;
                    // Razon Social
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                            .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                        existeTipoDocumento = false;
                        if (procesaComparendoPersonaDTO.getRazonSocial() == null) {
                            // ERROR
                            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.RAZON_SOCIAL_DEL_INFRACTOR,
                                    EnumErrorProcesamiento.FALTA_RAZON_SOCIAL_INFRACTOR, respuestaValidacionDTO);
                        }
                    }

                    if (procesaComparendoPersonaDTO.getNumeroIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_DOCUMENTO_DEL_INFRACTOR,
                                EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_INFRACTOR, respuestaValidacionDTO);
                    } else {

                        boolean esValido = iFachadaAdminGeneral.validarNumeroDocumento(
                                procesaComparendoPersonaDTO.getNumeroIdentificacion(),
                                procesaComparendoPersonaDTO.getIdTipoIdentificacion(),
                                procesaComparendoDTO.getFechaInfraccion());
                        if (!esValido) {
                            // ERROR
                            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_DOCUMENTO_DEL_INFRACTOR,
                                    EnumErrorProcesamiento.FORMATO_DOCUMENTO_INFRACTOR_INVALIDO,
                                    respuestaValidacionDTO);
                        }
                    }
                }

                // Fecha de expedición de la licencia de conducción del Infractor
                if (procesaComparendoPersonaDTO.getFechaExpedicionLicenCondu() != null) {
                    if (procesaComparendoPersonaDTO.getFechaExpedicionLicenCondu()
                            .after(Calendar.getInstance().getTime())) {
                        // ERROR
                        errorRechazoRespuestaValidacion(
                                EnumCamposDetalleComparendo.FECHA_EXPEDICION_DE_LA_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR,
                                EnumErrorProcesamiento.FECHA_EXPED_LICENCIA_CONDUCCION_INVALIDA,
                                respuestaValidacionDTO);
                    }
                }

                // Fecha Vencimiento de la Licencia de Conducción del Infractor
                if (procesaComparendoPersonaDTO.getFechaExpedicionLicenCondu() != null
                        && procesaComparendoPersonaDTO.getFechaVencimientoLicenCondu() != null) {
                    if (procesaComparendoPersonaDTO.getFechaExpedicionLicenCondu()
                            .after(procesaComparendoPersonaDTO.getFechaVencimientoLicenCondu())) {
                        // ERROR
                        errorRechazoRespuestaValidacion(
                                EnumCamposDetalleComparendo.FECHA_VENCIMIENTO_DE_LA_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR,
                                EnumErrorProcesamiento.FECHA_VENCI_LICEN_CONDUCCION_INVALIDA, respuestaValidacionDTO);
                    }
                }

                // Apellido 1 del infractor
                if (procesaComparendoPersonaDTO.getApellido1() == null) {
                    if (existeTipoDocumento) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_APELLIDO_INFRACTOR,
                                EnumErrorProcesamiento.FALTA_PRIMER_APELLIDO_INFRACTOR, respuestaValidacionDTO);
                    }
                }

                // Nombre 1 del infractor
                if (procesaComparendoPersonaDTO.getNombre1() == null) {
                    if (existeTipoDocumento) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_NOMBRE_INFRACTOR,
                                EnumErrorProcesamiento.FALTA_PRIMER_NOMBRE_INFRACTOR, respuestaValidacionDTO);
                    }
                }

                // Organismo de transito de la licencia de conduccion
                if (procesaComparendoPersonaDTO.getCodigoOrganismoLicencia() != null) {
                    OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
                    organismoTransitoDTO.setCodigoOrganismo(procesaComparendoDTO.getCodigoOrganismoLicenciaTransito());
                    OrganismoTransitoDTO organismoTransitoRDTO = consultarOrganismoTransito(
                            organismoTransitoDTO.getCodigoOrganismo());
                    // Validamos si esta activo
                    if (organismoTransitoRDTO != null) {
                        if (!organismoTransitoRDTO.getActivo()) {
                            // ERROR
                            errorRechazoRespuestaValidacion(null,
                                    EnumErrorProcesamiento.ORGANISMO_TRANSITO_INACTIVO_LIC_TRAN,
                                    respuestaValidacionDTO);
                        }
                    } else {
                        // ERROR
                        errorRechazoRespuestaValidacion(null,
                                EnumErrorProcesamiento.ORGANISMO_TRANSITO_INEXISTENTE_LIC_TRAN, respuestaValidacionDTO);
                    }
                }
                break;
            }
        }
    }

    @Override
    public OrganismoTransitoDTO consultarOrganismoTransito(Integer codigoOrganismoTransito) {
        OrganismoTransitoDTO organismoTransitoDTO = null;
        OrganismoTransitoDTO organismoTransitoFiltroDTO = new OrganismoTransitoDTO();
        organismoTransitoFiltroDTO.setCodigoOrganismo(codigoOrganismoTransito);
        List<OrganismoTransitoDTO> organismoTransitoDTOs = iFachadaAdminNegocio
                .consultarOrganismoTransito(organismoTransitoFiltroDTO);
        if (organismoTransitoDTOs != null && !organismoTransitoDTOs.isEmpty()) {
            organismoTransitoDTO = organismoTransitoDTOs.get(0);
        }
        return organismoTransitoDTO;
    }

    /**
     * Valida los campos de los datos del testigo
     * 
     * @param procesaComparendoDTO
     * @return {@link RespuestaValidacionDTO}
     * @throws CirculemosNegocioException
     */
    private void validarCamposTestigo(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarCamposTestigo(ProcesarComparendoDTO)");

        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.TESTIGO.getValue())) {
                // Tipo de documento del testigo
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.TIPO_DE_DOCUMENTO_DEL_TESTIGO,
                                EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_TESTIGO, respuestaValidacionDTO);
                    }
                }

                // Numero del testigo
                boolean existeTipoDocumento = false;
                if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {
                    if (procesaComparendoPersonaDTO.getNumeroIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_TESTIGO,
                                EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_TESTIGO, respuestaValidacionDTO);
                    }
                    existeTipoDocumento = true;
                }

                // Apellido 1 del testigo
                if (procesaComparendoPersonaDTO.getApellido1() == null) {
                    if (existeTipoDocumento) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_APELLIDO_TESTIGO,
                                EnumErrorProcesamiento.FALTA_PRIMER_APELLIDO_TESTIGO, respuestaValidacionDTO);
                    }
                }

                // Nombre 1 del testigo
                if (procesaComparendoPersonaDTO.getNombre1() == null) {
                    if (existeTipoDocumento) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_NOMBRE_TESTIGO,
                                EnumErrorProcesamiento.FALTA_PRIMER_NOMBRE_TESTIGO, respuestaValidacionDTO);
                    }
                }
                break;
            }
        }
    }

    /**
     * valida los campos de las evidencias
     * 
     * @param procesaComparendoDTO
     * @return {@link RespuestaValidacionDTO}
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    private RespuestaRecibirComparendoDTO validarCamposEvidencias(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarCamposEvidencias(ProcesaComparendoDTO)");

        for (ProcesaEvidenciaDTO procesaEvidenciaDTO : procesaComparendoDTO.getProcesaEvidencias()) {

            // Validacion de la extencion
            try {
                EnumExtensionArchivoEvidencia
                        .valueOf(procesaEvidenciaDTO.getArchivoTransportable().getExtension().toLowerCase());
            } catch (IllegalArgumentException e) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.EVIDENCIA,
                        EnumErrorProcesamiento.EXTENSION_INVALIDA, respuestaValidacionDTO);
            }

            if (procesaEvidenciaDTO.getCodigoTipoEvidencia() == null) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.EVIDENCIA,
                        EnumErrorProcesamiento.TIPO_EVIDENCIA_INVALIDO, respuestaValidacionDTO);
            }

            // Consultar el tamaño maximo permitido para un documento
            final ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                    procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo(), true);

            if (procesaEvidenciaDTO.getArchivoTransportable().getContenido().length > valorParametroDTO
                    .getValorParamInt()) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.EVIDENCIA,
                        EnumErrorProcesamiento.LONGITUD_DOCUMENTO_AGENTE_INVALIDA, respuestaValidacionDTO);
            }
        }
        return null;
    }

    /**
     * valida los campos de los datos del propietario
     * 
     * @param procesaComparendoDTO
     * @return {@link RespuestaValidacionDTO}
     * @throws CirculemosNegocioException
     */
    private void validarCamposPropietario(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarCamposPropietario(ProcesaComparendoDTO,RespuestaValidacionDTO)");

        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.PROPIETARIO.getValue())) {
                // Tipo de documento del propietraio
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {

                    // validar lo siguiente: Para Colombia, se valida como numérico, excepto cuando es Pasaporte.
                    if (!procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                            .equals(EnumTipoIdentificacion.PASAPORTE.getValor())) {

                        boolean valido = procesaComparendoPersonaDTO.getNumeroIdentificacion()
                                .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO);
                        if (!valido) {
                            // ERROR
                            errorRechazoRespuestaValidacion(
                                    EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_PROPIETARIO,
                                    EnumErrorProcesamiento.FORMATO_DOCUMENTO_PROPIETARIO_INVALIDO,
                                    respuestaValidacionDTO);
                        }
                    }

                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_PROPIETARIO,
                                respuestaValidacionDTO);
                    }
                }

                // Numero del propietraio
                boolean existeTipoDocumento = false;
                if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {

                    existeTipoDocumento = true;
                    // Razon Social
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                            .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                        existeTipoDocumento = false;
                        if (procesaComparendoPersonaDTO.getRazonSocial() == null) {
                            // ERROR
                            errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FALTA_RAZON_SOCIAL_PROPIETARIO,
                                    respuestaValidacionDTO);
                        }
                    }

                    if (procesaComparendoPersonaDTO.getNumeroIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_PROPIETARIO,
                                EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_PROPIETARIO, respuestaValidacionDTO);
                    }

                    boolean esValido = iFachadaAdminGeneral.validarNumeroDocumento(
                            procesaComparendoPersonaDTO.getNumeroIdentificacion(),
                            procesaComparendoPersonaDTO.getIdTipoIdentificacion(),
                            procesaComparendoDTO.getFechaInfraccion());
                    if (!esValido) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_PROPIETARIO,
                                EnumErrorProcesamiento.FORMATO_DOCUMENTO_PROPIETARIO_INVALIDO, respuestaValidacionDTO);
                    }
                }

                // Apellido 1 del propietraio
                if (procesaComparendoPersonaDTO.getApellido1() == null) {
                    if (existeTipoDocumento) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_APELLIDO_PROPIETARIO,
                                EnumErrorProcesamiento.FALTA_PRIMER_APELLIDO_PROPIETARIO, respuestaValidacionDTO);
                    }
                }

                // Nombre 1 del propietraio
                if (procesaComparendoPersonaDTO.getNombre1() == null) {
                    if (existeTipoDocumento) {
                        // ERROR
                        errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_NOMBRE_PROPIETARIO,
                                EnumErrorProcesamiento.FALTA_PRIMER_NOMBRE_PROPIETARIO, respuestaValidacionDTO);
                    }
                }
                break;
            }
        }
    }

    /**
     * valida los campos de los datos de la empresa
     * 
     * @param procesaComparendoDTO
     * @return {@link RespuestaValidacionDTO}
     * @throws CirculemosNegocioException
     */
    private void validarCamposEmpresa(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarCamposEmpresa(ProcesarComparendoDTO,RespuestaValidacionDTO)");
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue())) {
                if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {

                    // Verificamos el tipo de identificacion
                    ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                            EnumParametro.PAIS_INSTALACION,
                            procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo(), true);

                    TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = iFachadaAdminNegocio
                            .consultarTipoIdentificacionPersonaJuridica(valorParametroDTO.getValorParamInt());

                    if (!tipoIdentificacionPersonaDTO.getId()
                            .equals(procesaComparendoPersonaDTO.getIdTipoIdentificacion())) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.TIPO_DOCUMENTO_EMPRESA_INVALIDO,
                                respuestaValidacionDTO);
                    }

                    // Razon social
                    if (procesaComparendoPersonaDTO.getRazonSocial() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FALTA_RAZON_SOCIAL_EMPRESA,
                                respuestaValidacionDTO);
                    }

                    // Numero de documento empresa
                    if (procesaComparendoPersonaDTO.getNumeroIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_EMPRESA,
                                respuestaValidacionDTO);
                    } else {

                        boolean esValido = iFachadaAdminGeneral.validarNumeroDocumento(
                                procesaComparendoPersonaDTO.getNumeroIdentificacion(),
                                procesaComparendoPersonaDTO.getIdTipoIdentificacion(),
                                procesaComparendoDTO.getFechaInfraccion());
                        if (!esValido) {
                            // ERROR
                            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_DOCUMENTO_EMPRESA,
                                    EnumErrorProcesamiento.FORMATO_DOCUMENTO_EMPRESA_INVALIDO, respuestaValidacionDTO);
                        }
                    }
                }

                // Tipo de documento empresa
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() == null) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_EMPRESA,
                                respuestaValidacionDTO);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void validarCamposAgente(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, RespuestaValidacionDTO respuestaValidacionDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarCamposEmpresa(ProcesarComparendoDTO,RespuestaValidacionDTO)");

        // Primer apellido del agente
        if (procesaComparendoDTO.getApellido1Agente() == null || procesaComparendoDTO.getApellido1Agente().isEmpty()) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_APELLIDO_AGENTE,
                    EnumErrorProcesamiento.FALTA_PRIMER_APELLIDO_AGENTE, respuestaValidacionDTO);
        } else {
            boolean valido = procesaComparendoDTO.getApellido1Agente()
                    .matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES);
            if (!valido) {
                // ERROR
                errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.PRIMER_APELLIDO_AGENTE_INVALIDO_FORMATO,
                        respuestaValidacionDTO);
            }
        }

        // Primer Nombre del agente
        if (procesaComparendoDTO.getNombre1Agente() == null || procesaComparendoDTO.getNombre1Agente().isEmpty()) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PRIMER_NOMBRE_AGENTE,
                    EnumErrorProcesamiento.FALTA_PRIMER_NOMBRE_AGENTE, respuestaValidacionDTO);
        } else {
            boolean valido = procesaComparendoDTO.getNombre1Agente()
                    .matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES);
            if (!valido) {
                // ERROR
                errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.PRIMER_NOMBRE_AGENTE_INVALIDO_FORMATO,
                        respuestaValidacionDTO);
            }
        }

        // Placa agente
        if (procesaComparendoDTO.getNumeroIdentificacionAgente() == null) {
            if (procesaComparendoDTO.getPlacaAgente() == null) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PLACA_DE_AGENTE,
                        EnumErrorProcesamiento.FALTA_PLACA_AGENTE, respuestaValidacionDTO);
            } else {
                boolean valido = procesaComparendoDTO.getPlacaAgente()
                        .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO);
                if (!valido) {
                    // ERROR
                    errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.PLACA_DE_AGENTE,
                            EnumErrorProcesamiento.FORMATO_PLACA_AGENTE_INVALIDO, respuestaValidacionDTO);
                }
            }
        }

        // Numero documento del agente
        if (procesaComparendoDTO.getPlacaAgente() == null) {
            if (procesaComparendoDTO.getNumeroIdentificacionAgente() == null
                    || procesaComparendoDTO.getNumeroIdentificacionAgente().isEmpty()) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_IDENTIFICACION_AGENTE,
                        EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_AGENTE, respuestaValidacionDTO);
            } else {

                // Validamos que solo sea numerico
                if (!procesaComparendoDTO.getIdTipoIdentificacionAgente().equals(EnumTipoIdentificacion.PASAPORTE)) {
                    boolean esNUmerico = StringUtils.isNumeric(procesaComparendoDTO.getNumeroIdentificacionAgente());
                    if (!esNUmerico) {
                        // ERROR
                        errorRechazoRespuestaValidacion(null, EnumErrorProcesamiento.FORMATO_DOCUMENTO_AGENTE_INVALIDO,
                                respuestaValidacionDTO);
                    }
                }
            }
        }

        // Tipo documento del agente
        if (procesaComparendoDTO.getNumeroIdentificacionAgente() != null) {
            if (procesaComparendoDTO.getIdTipoIdentificacionAgente() == null) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.TIPO_IDENTIFICACION_AGENTE,
                        EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_AGENTE, respuestaValidacionDTO);
            }
        }

        // Numero de documento
        if (procesaComparendoDTO.getIdTipoIdentificacionAgente() != null) {
            if (procesaComparendoDTO.getNumeroIdentificacionAgente() == null) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.TIPO_IDENTIFICACION_AGENTE,
                        EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_AGENTE, respuestaValidacionDTO);
            }
        }

        // Entidad agente de transito
        if (!procesaComparendoDTO.getIdTipoAgenteImpositor().equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
            if (procesaComparendoDTO.getIdUnificacionResponsable() == null) {
                // ERROR
                errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.UNIFICACION_RESPONSABLE,
                        EnumErrorProcesamiento.FALTA_ENTIDAD_AGENTE, respuestaValidacionDTO);
            }
        }
    }

    @Override
    public RespuestaRecibirComparendoDTO validarReglasComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarReglasComparendo(ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");
        RespuestaRecibirComparendoDTO respuestaValidacionDTO = new RespuestaRecibirComparendoDTO();
        respuestaValidacionDTO.setDetalleProcesamientoDTOs(new ArrayList<DetalleProcesamientoDTO>());

        // validacion radio accion
        // Debe traer un valor cuando la información del vehículo para el campo “Clase de servicio” es IGUAL a “Público” , de lo contrario
        // deberá
        // genera una INCONSISTENCIA
        if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoServicio() != null) {
            if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoServicio()
                    .equals(EnumTipoServicio.PUBLICO.getValue())) {
                if (procesarComparendoDTO.getProcesaComparendoDTO().getIdRadioAccion() == null) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs().add(crearDetalleProcesamiento(
                            EnumCamposDetalleComparendo.RADIO_ACCION, EnumErrorProcesamiento.FALTA_RADIO_DE_ACCION));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
            }
        }

        // PASO 6: Infraccion vigente
        if (configuracionInfraccionDTO == null) {
            respuestaValidacionDTO.getDetalleProcesamientoDTOs().add(crearDetalleProcesamiento(
                    EnumCamposDetalleComparendo.INFRACCION, EnumErrorProcesamiento.INFRACCION_NO_SE_ENCUENTRA_VIGENTE));
            respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
        }

        // Valida que no sea una validaciond e origen rectificacion de comparendo
        if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
            // **** **** PASO 4 FLUJO BASICO **** ****
            // Preguntamos si es Polca y verificamos que no se encuentre asignado como un formulario a un responsable, de lo contrario si no es polca
            // Verificamos que efectivamente este asignado algun organismo
            // Verificamos que le rango del numero de comparendo exista
            // Estas validaciones que dan solo para el pais de instalacion

            // Validacion entidad agente
            validarEntidadAgenteTransito(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO,
                    configuracionInfraccionDTO);

            if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.CORREGIR_INCONSISTENCIA)) {
                // **** **** PASO 5 FLUJO BASICO **** ****
                // Validamos la fecha de imposicion de comparendo sea antes de la fecha del sistema si es asi validamos que cumpla con la
                // configuracionde
                // la
                // cantidad de dias para el ingreso del comparendo
                if (procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion()
                        .after(Calendar.getInstance().getTime())) {
                    // ERROR
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.FECHA_INFRACCION,
                                    EnumErrorProcesamiento.FECHA_INFRACCION_INVALIDA));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                } else {

                    // Verificamos los dias para el ingreso del comparendo
                    Date fechalimited = iFachadaAdminGeneral.sumarDias(
                            procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo(),
                            procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion(),
                            consultarConfiguracion011(procesarComparendoDTO.getProcesaComparendoDTO()).getDiasLimite()
                                    .intValue(),
                            true);

                    if (fechalimited.before(Calendar.getInstance().getTime())) {
                        // ERROR
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.FECHA_INFRACCION,

                                        EnumErrorProcesamiento.FECHA_INFRACCION_VENCIDA));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                    }
                }
            }
        }
        // **** **** PASO 6 FLUJO BASICO **** ****
        // Se encuentra al inicio del medoto es una validacion de la infraccion la cual se requiere para mas validaciones
        if (configuracionInfraccionDTO != null) {

            // **** **** PASO 7 FLUJO BASICO **** ****
            // Infraccion requiere placa vehiculo
            if (configuracionInfraccionDTO.getRequierePlaca()) {
                // **** **** PASO 8 FLUJO BASICO **** ****
                // Placa/identificador vehiculo
                if (procesarComparendoDTO.getProcesaComparendoDTO().getPlacaVehiculo() == null
                        && procesarComparendoDTO.getProcesaComparendoDTO().getIdentificacionVehiculo() == null) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs().add(crearDetalleProcesamiento(
                            EnumCamposDetalleComparendo.PLACA_VEHICULO, EnumErrorProcesamiento.FALTA_PLACA_VEHICULO));
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.IDENTIFICACION_VEHICULO,
                                    EnumErrorProcesamiento.FALTA_IDENTIFICACION_VEHICULO));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
            }

            // **** **** PASO 9 FLUJO BASICO **** ****
            // Infraccion requiere infractor
            if (configuracionInfraccionDTO.getInfractorObligatorio()) {
                // **** **** PASO 10 FLUJO BASICO **** ****
                // Existe fuga infractor
                if (!procesarComparendoDTO.getProcesaComparendoDTO().getExisteFugaInfractor()) {
                    // **** **** PASO 11 FLUJO BASICO **** ****
                    // Informacion de infractor
                    validarInformacionInfractor(procesarComparendoDTO.getProcesaComparendoDTO(),
                            respuestaValidacionDTO);
                } else {
                    // El valor registrado para el campo “Existe fuga del infractor” es IGUAL a SI
                    // **** **** PASO 1 FLUJO ALTERNO 8 **** ****
                    comportamientoInfractorCartera(procesarComparendoDTO, respuestaValidacionDTO);
                }
            } else {
                // El valor registrado para el campo “Existe fuga del infractor” es IGUAL a SI
                // **** **** PASO 1 FLUJO ALTERNO 7 **** ****

                // Validar informacion infractor
                boolean existeInformacion = true;
                for (ProcesaComparendoPersonaDTO tipoPersonaComparendoDTO : procesarComparendoDTO
                        .getProcesaComparendoDTO().getProcesaComparendoPersonas()) {
                    existeInformacion = true;
                    if (tipoPersonaComparendoDTO.getCodigoTipoPersonaComparendo()
                            .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                        // Tipo de identificacion
                        if (tipoPersonaComparendoDTO.getIdTipoIdentificacion() == null) {
                            existeInformacion = false;
                        }
                        // Numero de identificacion
                        if (tipoPersonaComparendoDTO.getNumeroIdentificacion() == null) {
                            existeInformacion = false;
                        }
                    }
                }

                // los campos validados no existen
                if (!existeInformacion) {
                    comportamientoInfractorCartera(procesarComparendoDTO, respuestaValidacionDTO);
                }
            }

            // **** **** PASO 12 FLUJO BASICO **** ****
            // Infraccion aplica validar embriaguez
            boolean errorReincidencias = false;
            if (configuracionInfraccionDTO.getAplicaEmbriaguez()) {

                // Numero de reincidencias
                if (procesarComparendoDTO.getProcesaComparendoDTO().getNumeroReincidencia() != null) {
                    if (procesarComparendoDTO.getProcesaComparendoDTO().getNumeroReincidencia() < 1) {
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.NUMERO_REINCIDENCIA,
                                        EnumErrorProcesamiento.NUMERO_REINCIDENCIAS_INVALIDO));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                    }
                }

                // Clase de servicio
                if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoServicio() == null) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs().add(crearDetalleProcesamiento(
                            EnumCamposDetalleComparendo.TIPO_SERVICIO, EnumErrorProcesamiento.CLASE_DE_SERVICIO));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }

                // **** **** PASO 13 FLUJO BASICO **** ****
                // Infractor presenta prueba alcoholemia
                if (!procesarComparendoDTO.getProcesaComparendoDTO().getNiegaPruebaAlcoholemia()) {
                    // **** **** PASO 14 FLUJO BASICO **** ****
                    consultarMiligramosEtanol(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);

                    // Numero de veces reincidencias
                    if (procesarComparendoDTO.getProcesaComparendoDTO().getNumeroReincidencia() == null) {
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.NUMERO_REINCIDENCIA,
                                        EnumErrorProcesamiento.FALTA_NUMERO_REINCIDENCIAS));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                        errorReincidencias = true;
                    }
                }
            }

            // **** **** PASO 16 FLUJO BASICO **** ****
            // Infraccion tipo sancion inmovilizacion vehiculo y numero de patio
            boolean sancionInmoviliza = false;
            for (TipoSancionDTO tipoSancionDTO : configuracionInfraccionDTO.getTipoSancionList()) {
                if (tipoSancionDTO.getCodigo().equals(EnumTipoSancion.INMOVILIZACION_VEHICULO.getValue().toString())) {
                    sancionInmoviliza = true;
                    break;
                }
            }

            // **** **** PASO 17 FLUJO BASICO **** ****
            // Numero de patio
            if (sancionInmoviliza && procesarComparendoDTO.getProcesaComparendoDTO().getNumeroPatio() == null
                    && !procesarComparendoDTO.getProcesaComparendoDTO().getExisteFugaInfractor()) {
                respuestaValidacionDTO.getDetalleProcesamientoDTOs().add(crearDetalleProcesamiento(
                        EnumCamposDetalleComparendo.PATIO, EnumErrorProcesamiento.FALTA_CODIGO_PATIO));
                respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
            }

            // **** **** PASO 18 FLUJO BASICO **** ****
            // Validar existe agente si no es polca
            // Esta validacion queda para cada pais

            // **** **** PASO 19 FLUJO BASICO **** ****
            // Consultar presonas infractor/propietario
            validacionPersonaComparendo(procesarComparendoDTO.getProcesaComparendoDTO());

            // **** **** PASO 20 FLUJO BASICO **** ****
            // Consultar tarifa infraccion
            if (!errorReincidencias) {
                validarTarifaInfraccion(respuestaValidacionDTO, procesarComparendoDTO.getProcesaComparendoDTO(),
                        configuracionInfraccionDTO);
            }
        }

        // Validacion de las direcciones
        validarDirecciones(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);

        return respuestaValidacionDTO;
    }

    /**
     * Se encarga de validar si existen las personas(Infractor, Propietario) en la base de datos de lo contrario las crea
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    private void validacionPersonaComparendo(ProcesaComparendoDTO procesaComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validacionPersonaComparendo(ProcesaComparendoDTO)");
        PersonaDTO infractorDTO = null;
        PersonaDTO propietarioDTO = null;

        boolean esInfractor;
        boolean esPropietario;
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            esInfractor = false;
            esPropietario = false;
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                    infractorDTO = consultarPersona(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo(),
                            procesaComparendoPersonaDTO.getIdTipoIdentificacion(),
                            procesaComparendoPersonaDTO.getNumeroIdentificacion());
                    esInfractor = true;
                    esPropietario = false;
                }
            } else if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo())) {
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                    propietarioDTO = consultarPersona(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo(),
                            procesaComparendoPersonaDTO.getIdTipoIdentificacion(),
                            procesaComparendoPersonaDTO.getNumeroIdentificacion());
                    esPropietario = true;
                    esInfractor = false;
                }
            }

            // FA 12 PASO 1: Si no existe crear persona
            if (infractorDTO == null) {
                if (esInfractor) {
                    registroPersonaComparendo(procesaComparendoDTO, procesaComparendoPersonaDTO);
                }
            } else {
                procesaComparendoPersonaDTO.setPersonaDTO(infractorDTO);
            }
            if (propietarioDTO == null) {
                if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                    if (esPropietario) {

                    }
                }
            } else {
                procesaComparendoPersonaDTO.setPersonaDTO(propietarioDTO);
            }
        }
    }

    @Override
    public PersonaDTO consultarPersona(int organismoTransito, int tipoDocumento, String numeroDocumento)
            throws CirculemosNegocioException {
        return iFachadaAdminNegocio.consultarPersona(organismoTransito, tipoDocumento, numeroDocumento);
    }

    @Override
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion) {
        return iFachadaAdminNegocio.consultarPersona(tipoIdentificacion, numeroIdentificacion);
    }

    /**
     * Se encarga del registro de una persona en el sistema que la ingresaron en el comparendo y aun no existe en el sistema
     * 
     * @param procesaComparendoDTO
     * @param procesaComparendoPersonaDTO
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    private void registroPersonaComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO) throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::registroPersonaComparendo(ProcesaComparendoDTO,ProcesaComparendoPersonaDTO)");
        PersonaDTO personaDTO = RecibirComparendoHelper
                .convertirProcesaComparendoPersonaDTO(procesaComparendoPersonaDTO);

        // Organismo de transito
        personaDTO.setOrganismoTransito(procesaComparendoDTO.getOrganismoTransito());
        // Tipo fuente de informacion
        TipoFuenteInformacionDTO fuenteInformacionDTO = new TipoFuenteInformacionDTO();
        fuenteInformacionDTO.setId(EnumTipoFuenteInformacion.COMPARENDO.getValue());
        personaDTO.setFuenteInformacion(fuenteInformacionDTO);
        personaDTO.setId(iFachadaAdminNegocio.registrarPersona(personaDTO));
        procesaComparendoPersonaDTO.setPersonaDTO(personaDTO);
    }

    @Override
    public void validarAgentePolca(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarAgentePolca(ProcesaComparendoDTO)");

        AgenteDTO agenteDTO = new AgenteDTO();
        if (procesaComparendoDTO.getPlacaAgente() != null && !procesaComparendoDTO.getPlacaAgente().isEmpty()) {
            agenteDTO.setPlaca(procesaComparendoDTO.getPlacaAgente());
        }
        agenteDTO.setOrganismoTransito(procesaComparendoDTO.getOrganismoTransito());
        if (procesaComparendoDTO.getNumeroIdentificacionAgente() != null
                && !procesaComparendoDTO.getNumeroIdentificacionAgente().isEmpty()) {
            PersonaDTO personaDTO = new PersonaDTO();
            TipoIdentificacionPersonaDTO identificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            identificacionPersonaDTO.setId(procesaComparendoDTO.getIdTipoIdentificacionAgente());
            personaDTO.setTipoIdentificacion(identificacionPersonaDTO);
            personaDTO.setNumeroIdentificacion(procesaComparendoDTO.getNumeroIdentificacionAgente());
            agenteDTO.setPersona(personaDTO);
        }
        AgenteDTO agenteConsultaDTO = consultarAgente(agenteDTO);

        if (agenteConsultaDTO == null) {
            respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                    .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.NUMERO_IDENTIFICACION_AGENTE,
                            EnumErrorProcesamiento.AGENTE_TRANSITO_INEXISTENTE));
            respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
        } else {
            if (agenteConsultaDTO.getFechaFinVigencia() != null) {
                if (!UtilFecha.betweenDate(agenteConsultaDTO.getFechaInicioVigencia(),
                        agenteConsultaDTO.getFechaFinVigencia(), UtilFecha.currentZeroTimeDate())) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.NUMERO_IDENTIFICACION_AGENTE,
                                    EnumErrorProcesamiento.AGENTE_NO_VIGENTE));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
            }
        }
    }

    @Override
    public AgenteDTO consultarAgente(AgenteDTO agenteDTO) {

        List<AgenteDTO> agenteDTOs = iLAgente.consultarAgente(agenteDTO);
        if (agenteDTOs != null && !agenteDTOs.isEmpty()) {
            return agenteDTOs.get(0);
        }
        return null;
    }

    /**
     * Consultar configuracion rango milimetros etanol asociado a grado alcoholemia
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    private void consultarMiligramosEtanol(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::consultarMiligramosEtanol(ProcesaComparendoDTO)");

        if (procesaComparendoDTO.getGradoAlcoholemia() == null) {
            respuestaValidacionDTO.getDetalleProcesamientoDTOs().add(crearDetalleProcesamiento(
                    EnumCamposDetalleComparendo.GRADO_ALCOHOLEMIA, EnumErrorProcesamiento.FALTA_GRADOS_ALCOHOLEMIA));
            respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
        } else {
            if (procesaComparendoDTO.getValorGradoAlcoholemia() == null) {
                respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                        .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.VALOR_GRADO_ALCOHOLEMIA,
                                EnumErrorProcesamiento.FALTA_MILIGRAMOS_ETANOL));
                respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
            } else {
                // **** **** PASO 15 FLUJO BASICO **** ****
                // Validar existe grado alcoholemia enviado
                List<GradoAlcoholemiaDTO> gradoAlcoholDTOList = adminComparendoEjb
                        .consultarGradoAlcoholemia(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());

                boolean existeGradoAlcohol = false;
                if (gradoAlcoholDTOList != null && !gradoAlcoholDTOList.isEmpty()) {
                    for (GradoAlcoholemiaDTO gradoAlcoholemiaDTO : gradoAlcoholDTOList) {
                        if (gradoAlcoholemiaDTO.getValor().equals(procesaComparendoDTO.getGradoAlcoholemia())) {
                            existeGradoAlcohol = true;
                            break;
                        }
                    }
                }

                if (!existeGradoAlcohol) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.GRADO_ALCOHOLEMIA,

                                    EnumErrorProcesamiento.GRADO_ALCOHOLEMIA_INVALIDO));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                } else {
                    ConfiguracionGradosAlcoholDTO cfgGradosAlcoholDTO = consultarConfiguracion006(procesaComparendoDTO);

                    if (!(procesaComparendoDTO.getValorGradoAlcoholemia()
                            .compareTo(cfgGradosAlcoholDTO.getMinimo().intValue()) >= 0
                            && (cfgGradosAlcoholDTO.getMaximo() == null
                                    || procesaComparendoDTO.getValorGradoAlcoholemia()
                                            .compareTo(cfgGradosAlcoholDTO.getMaximo().intValue()) <= 0))) {
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.VALOR_GRADO_ALCOHOLEMIA,
                                        EnumErrorProcesamiento.MILIGRAMOS_ETANOL_INVALIDOS));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                    }

                }
            }
        }
    }

    /**
     * Metodo que se encarga de registrar las procesa evidencias
     * 
     * @author giovanni.velandia
     * @param procesarComparendoDTO
     * @throws CirculemosAlertaException
     */
    private void registrarProcesaEvidencias(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosAlertaException {
        logger.debug("RecibirComparendoEJB::registrarProcesaEvidencias(ProcesarComparendoDTO)");
        // Validamos que las evidencias llege diligenciadas
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaEvidencias() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaEvidencias().isEmpty()) {

            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECIBIR_COMPARENDO)) {
                // Consultar ruta para almacenar el comparendo
                final ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                        EnumParametro.RUTA_ALMACEN_PROCESA_EVIDENCIA,
                        procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo(),
                        true);

                for (ProcesaEvidenciaDTO procesaEvidenciaDTO : procesarComparendoDTO.getProcesaComparendoDTO()
                        .getProcesaEvidencias()) {

                    ProcesaEvidencia procesaEvidencia = ProcesaEvidenciaHelper.toLevel1Entity(procesaEvidenciaDTO,
                            new ProcesaEvidencia());
                    procesaEvidencia.setFechaEvidencia(new Date());

                    TipoEvidencia tipoEvidencia = em.find(TipoEvidencia.class,
                            procesaEvidenciaDTO.getCodigoTipoEvidencia());

                    // Documento
                    String idDocumento = iRRepositorioArchivo.registrarDocumento(
                            EnumCategoriaDocumento.PROCESA_EVIDENCIA,
                            RecibirComparendoHelper.procesarArchivoTransportable(
                                    procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo(),
                                    tipoEvidencia.getCodigo(), procesaEvidenciaDTO.getArchivoTransportable(),
                                    valorParametroDTO.getValorParam()));
                    procesaEvidencia.setIdDocumento(idDocumento);

                    // Procesa comparendo
                    procesaEvidencia.setProcesaComparendo(ProcesaComparendoHelper
                            .toLevel0Entity(procesarComparendoDTO.getProcesaComparendoDTO(), new ProcesaComparendo()));
                    em.persist(procesaEvidencia);
                }
            }
        }
    }

    /**
     * Registar un procesa comparendo persona
     * 
     * @param procesarComparendoDTO
     * @author giovanni.velandia
     */
    private void registrarProcesaComparendoPersona(ProcesarComparendoDTO procesarComparendoDTO) {
        logger.debug("RecibirComparendoEJB::registrarProcesaComparendoPersona(ProcesarComparendoDTO)");
        if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas() != null
                && !procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas().isEmpty()) {
            for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesarComparendoDTO
                    .getProcesaComparendoDTO().getProcesaComparendoPersonas()) {
                ProcesaComparendoPersona procesaComparendoPersona = ProcesaComparendoPersonaHelper
                        .toLevel1Entity(procesaComparendoPersonaDTO, new ProcesaComparendoPersona());
                procesaComparendoPersona.setProcesaComparendo(ProcesaComparendoHelper
                        .toLevel1Entity(procesarComparendoDTO.getProcesaComparendoDTO(), new ProcesaComparendo()));
                em.persist(procesaComparendoPersona);
            }
        }
    }

    @Override
    public boolean comparendoIngresado(String numeroComparendo, Integer codigoOrganismo) {
        logger.debug("RecibirComparendoEJB::comparendoIngresado(String,Integer)");

        /*
         * Verificamos el procesa comparendo
         */
        boolean existe = false;
        existe = iLProcesaComparendo.existeProcesaComparendo(numeroComparendo, codigoOrganismo);
        if (existe) {
            return existe;
        }

        /*
         * Verificamos si esta como comparendo
         */
        existe = iLComparendo.existeComparendo(numeroComparendo, codigoOrganismo);

        return existe;
    }

    @Override
    public void registroCambioEstadoformulario(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::registroCambioEstadoformulario(ProcesaComparendoDTO,ConfiguracionInfraccionDTO)");

        /*
         * Solo notifica el cambio de estado del formulario si no es polca
         */
        if (!procesaComparendoDTO.getIdTipoAgenteImpositor().equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
            CambioEstadoDTO cambioEstadoDTO = new CambioEstadoDTO();
            cambioEstadoDTO.setEnumCausalCambioEstado(EnumCausalCambioEstado.IMPOSICION);
            cambioEstadoDTO.setNumeroFormulario(procesaComparendoDTO.getNumeroComparendo());
            // Tipo formulario
            cambioEstadoDTO.setTipoFormulario(
                    Integer.parseInt(consultarConfiguracion008(configuracionInfraccionDTO).getTipoFormulario().get(0)));
            cambioEstadoDTO.setFechaCambio(procesaComparendoDTO.getFechaInfraccion());
            iLFormulario.cambiarEstadoFormulario(cambioEstadoDTO);
        }
    }

    @Override
    public void actualizarProcesaComparendo(ProcesaComparendoDTO procesaComparendoDTO) {
        logger.debug("RecibirComparendoEJB::actualizarProcesaComparendo(ProcesaComparendoDTO)");
        iLProcesaComparendo.actualizarProcesaComparendo(procesaComparendoDTO);
    }

    /**
     * Consulta un error de procesamiento por su codigo y pais de instalacion
     * 
     * @return error de procesamiento correspondiente a la enumeracion seleccionada
     * @author giovanni.velandia(2015-11-17)
     */
    private ErrorProcesamientoDTO consultarErrorProcesamiento(EnumErrorProcesamiento errorProcesamiento) {
        logger.debug("RecibirComparendoEJB::consultarErrorProcesamiento(EnumErrorProcesamiento)");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT ep FROM ErrorProcesamiento ep");
        jpql.append(" WHERE ep.codigo = :pCodErrProc");
        jpql.append(" AND ep.pais.id = :idPais");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("pCodErrProc", errorProcesamiento.getCodigo());
        query.setParameter("idPais", iRSeguridadCirculemos.obtenerPais().getId());

        @SuppressWarnings("unchecked")
        List<ErrorProcesamiento> resultado = query.getResultList();
        if (resultado != null && !resultado.isEmpty()) {
            return ErrorProcesamientoHelper.toLevel1DTO(resultado.get(0));
        }
        return null;
    }

    /**
     * Se encarga de validar los datos del infractor
     * 
     * @author giovanni.velandia
     * @param procesarComparendoDTO
     */
    private void validarInformacionInfractor(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarInformacionInfractor(ProcesaComparendoDTO, RespuestaVaidacionDTO)");
        for (ProcesaComparendoPersonaDTO tipoPersonaComparendoDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (tipoPersonaComparendoDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                if (tipoPersonaComparendoDTO.getIdTipoIdentificacion() == null) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.TIPO_DOCUMENTO_DEL_INFRACTOR,
                                    EnumErrorProcesamiento.FALTA_TIPO_DOCUMENTO_INFRACTOR));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
                if (tipoPersonaComparendoDTO.getNumeroIdentificacion() == null) {
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.NUMERO_DOCUMENTO_DEL_INFRACTOR,
                                    EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_INFRACTOR));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
                // Validacion razon social
                if (tipoPersonaComparendoDTO.getIdTipoIdentificacion() != null)
                    if (tipoPersonaComparendoDTO.getIdTipoIdentificacion()
                            .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                        if (tipoPersonaComparendoDTO.getRazonSocial() == null) {
                            respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                    .add(crearDetalleProcesamiento(
                                            EnumCamposDetalleComparendo.RAZON_SOCIAL_DEL_INFRACTOR,
                                            EnumErrorProcesamiento.FALTA_RAZON_SOCIAL_INFRACTOR));
                            respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                        }
                    } else {
                        if (tipoPersonaComparendoDTO.getApellido1() == null) {
                            respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                    .add(crearDetalleProcesamiento(
                                            EnumCamposDetalleComparendo.PRIMER_APELLIDO_INFRACTOR,
                                            EnumErrorProcesamiento.FALTA_PRIMER_APELLIDO_INFRACTOR));
                            respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                        }
                        if (tipoPersonaComparendoDTO.getNombre1() == null) {
                            respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                    .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.PRIMER_NOMBRE_INFRACTOR,
                                            EnumErrorProcesamiento.FALTA_PRIMER_NOMBRE_INFRACTOR));
                            respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                        }
                    }
            }
        }
    }

    /**
     * Consulta un procesa comparendo por id y retorna el resultado en una entidad
     * 
     * @param idProcesaComparendo
     * @return
     * @author giovanni.velandia
     */
    public ProcesaComparendoDTO consultarProcesaComparendo(Long idProcesaComparendo) {
        logger.debug("RecibirComparendoEJB::consultarProcesaComparendo(Long)");
        return iLProcesaComparendo.consultarProcesaComparendo(idProcesaComparendo);
    }

    /**
     * Se encarga de verificar el comportamiento de la persona en cartera por medio de un paramtetro Flujo alterno 27
     * 
     * @param procesarComparendoDTO
     * @return
     * @author giovanni.velandia
     */
    private void comportamientoInfractorCartera(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug(
                "RecibirComparendoEJB::comportamientoInfractorCartera(ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");

        ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.DEUDOR_SIN_INFRACTOR,
                procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo(), true);
        if (valorParametroDTO.getValorParamInt() == EnumComportamientoCartera.PERMITE_CARTERA_PROPIETARIO.getValue()) {

            // Informacion del propietario
            boolean error = false;
            for (ProcesaComparendoPersonaDTO procesaComparendoPersonaPropietarioDTO : procesarComparendoDTO
                    .getProcesaComparendoDTO().getProcesaComparendoPersonas()) {
                if (procesaComparendoPersonaPropietarioDTO.getCodigoTipoPersonaComparendo()
                        .equals(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo())) {
                    if (procesaComparendoPersonaPropietarioDTO.getIdTipoIdentificacion() == null) {
                        error = true;
                    }
                    if (procesaComparendoPersonaPropietarioDTO.getNumeroIdentificacion() == null) {
                        error = true;
                    }
                    if (error) {
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(crearDetalleProcesamiento(
                                        EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_PROPIETARIO,
                                        EnumErrorProcesamiento.FALTA_INFORMACION_PROPIETARIO));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                    }

                    /*
                     * Actualizamos la informacion del infractor con la misma informacion del propietario
                     */
                    if (!error) {
                        int posicionInfractor = 0;

                        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaInfractorDTO : procesarComparendoDTO
                                .getProcesaComparendoDTO().getProcesaComparendoPersonas()) {
                            if (procesaComparendoPersonaInfractorDTO.getCodigoTipoPersonaComparendo()
                                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                                posicionInfractor = procesarComparendoDTO.getProcesaComparendoDTO()
                                        .getProcesaComparendoPersonas().indexOf(procesaComparendoPersonaInfractorDTO);
                                break;
                            }
                        }

                        // Infractor
                        procesarComparendoDTO.getProcesaComparendoDTO().getProcesaComparendoPersonas().set(
                                posicionInfractor,
                                RecibirComparendoHelper.toInfractor(procesaComparendoPersonaPropietarioDTO));
                    }
                    break;
                }
            }
        } else {
            // En este caso el infractor sera nulo y el metodo de cartera se ancargara de asignarle la persona con cedula 0
        }
    }

    @Override
    public RespuestaValidacionDTO rectificarComparendo(ProcesaComparendoRectificadoDTO comparendoRectificadoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::rectificarComparendo(ProcesaComparendoRectificadoDTO)");
        // Valida toda la informacion ingresada mediante la inclusion del caso de uso "Validar reglas de negocio y procesar comparendo" enviando
        // adicional a la informvaliraracion ingresada el "Nombre de usuario" y el "Origen validacion" IGUAL A "Rectificacion de comparendo".
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECTIFICAR_COMPARENDO);
        procesarComparendoDTO.setProcesaComparendoDTO(comparendoRectificadoDTO);
        RespuestaValidacionDTO respuestaValidacionDTO = ilRecibirComparendo.recibirComparendo(procesarComparendoDTO);

        // El caso de uso "Validar reglas de negocio del comparendo" si retorna al menos una inconsistencia
        if (respuestaValidacionDTO.getCodigoResultado() != null
                && (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getCodigo())
                        || respuestaValidacionDTO.getCodigoResultado()
                                .equals(EnumErrorProcesamiento.INCONSISTENTE.getCodigo()))) {
            return respuestaValidacionDTO;
        }

        // El "Numero de comparendo" NO tiene generada resolucion de "Tipo de Resolucion" IGUAL a "Resolucion de sancion" y con un "Estado de la
        // resolucion" IGUAL a VIGENTE
        // generarRectificacion(comparendoRectificadoDTO);

        // Se asigna en la respuesta de validacion su respectivo codigo satisfactorio
        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.REGISTRADO.getCodigo());

        return respuestaValidacionDTO;
    }

    /**
     * Genera la resolucion de rectificacion. Si el comparendo posee una resolucion de sancion vigente, la anula y genera una resolucion de
     * rectificacion anulando la resolucion de sancion.
     * 
     * @param rectificacion
     *            datos de la resolucion de rectificacion
     * @author luis.forero(2016-02-01)
     */
    private void generarRectificacion(final ProcesaComparendoRectificadoDTO rectificacion) {
        logger.debug("RecibirComparendoEJB::generarRectificacion(ProcesaComparendoRectificadoDTO)");
        // El "Numero de comparendo" NO tiene generada resolucion de Tipo de Resolucion IGUAL a Resolucion de sancion y con un
        // "Estado de la resolucion" IGUAL a VIGENTE
        ComparendoResolucionDTO filResolucion = new ComparendoResolucionDTO();
        filResolucion.setTipoResolucion(new TipoResolucionDTO(EnumTipoResolucion.SANCION.getValue()));
        filResolucion.setEstadoResolucion(new EstadoResolucionDTO(EnumEstadoResolucion.VIGENTE.getValue()));
        filResolucion.setComparendo(new ComparendoDTO(rectificacion.getComparendo().getCicomparendo()));
        List<ComparendoResolucionDTO> resoluciones = adminComparendoEjb.consultarResoluciones(filResolucion);
        Resoluble resolucion = null;
        if (resoluciones.isEmpty()) {
            // Se genera la resolucion de rectificacion bajo la inclusion del caso de uso "Generar Documento circulemos 2.0", enviando los siguiente
            // datos:
            // * Nombre del documento Circulemos 2 = "Resolucion de Rectificacion"
            // * Organismo de Transito
            // * Usuario que genera el documento = Usuario en sesion
            // * Fecha de generacion del documento = Fecha actual del sistema
            // * Numero de comparendo
            final GeneraDocumentoDTO generaDocumentoDTO = RecibirComparendoHelper
                    .newInstanceGeneraDocumentoDTO(rectificacion, EnumTipoDocumentoGenerado.RESOLUCION_RECTIFICACION);
            resolucion = new ResolucionRectificacionResoluble(generaDocumentoDTO, rectificacion.getComparendo()
                    .getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());

        } else {
            // El "numero de comparendo" SI tiene resolucion de sancion generada VIGENTE
            // Cambia el "Estado de la resolucion" de sancion asociada al comparendo que se rectifica a ANULADA
            ResolucionDTO resolucionSancion = resoluciones.get(0);
            resolucionSancion.setEstadoResolucion(new EstadoResolucionDTO(EnumEstadoResolucion.ANULADA.getValue()));
            fachadaAdminResolucionEJB.actualizarResolucion(resolucionSancion);

            // Se genera la resolucion de rectificacion bajo la inclusion del caso de uso "Generar Documento circulemos 2.0", enviando los siguiente
            // datos:
            // * Nombre del documento Circulemos 2 = "Resolucion de Rectificacion anulando la de sancion"
            // * Organismo de Transito
            // * Usuario que genera el documento = Usuario en sesion
            // * Fecha de generacion del documento = Fecha actual del sistema
            // * Numero de comparendo
            final GeneraDocumentoDTO generaDocumentoDTO = RecibirComparendoHelper.newInstanceGeneraDocumentoDTO(
                    rectificacion, EnumTipoDocumentoGenerado.RESOLUCION_RECTIFICACION_ANULANDO_SANCION);
            resolucion = new ResolucionRectificacionResoluble(generaDocumentoDTO, rectificacion.getComparendo()
                    .getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
        }
        // Se lleva a cabo la generacion de la resolucion de rectificacion enviando:
        // enviando los siguiente datos:
        // * Nombre del documento Circulemos 2 = "Resolucion de Rectificacion"
        // * Organismo de Transito
        // * Usuario que genera el documento = Usuario en sesion
        // * Fecha de generacion del documento = Fecha actual del sistema
        // * Numero de comparendo
        long idResolucionComparendo = fachadaAdminResolucionEJB.registrarResolucion(resolucion);
        ComparendoResolucion comparendoResolucion = new ComparendoResolucion();
        comparendoResolucion.setIdResolucion(idResolucionComparendo);
        Comparendo comparendo = em.find(Comparendo.class, rectificacion.getComparendo().getCicomparendo());
        comparendoResolucion.setComparendo(comparendo);
        em.persist(comparendoResolucion);

        // Actualizacion de la fecha de modificacion del comparendo.
        actualizarComparendo(ComparendoHelper.toLevel1DTO(comparendo));
    }

    @Override
    public ComparendoDTO rectificacionesComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            EnumActividad enumActividadReg, EnumActividad enumActividadImpo) {
        logger.debug("RecibirComparendoEJB::rectificacionesComparendo(ProcesarComparendoDTO)");

        // Convertimos el procesa comparendo en u procesa comparendo rectificacion paa poder acceder a la lista de cambios de los cmapos del
        // comparendo
        ProcesaComparendoRectificadoDTO procesaComparendoRectificadoDTO = (ProcesaComparendoRectificadoDTO) procesaComparendoDTO;

        if (procesaComparendoRectificadoDTO.getCampoRectificaComparendoDTOs() != null
                && !procesaComparendoRectificadoDTO.getCampoRectificaComparendoDTOs().isEmpty()) {
            // consultar comparendo
            Comparendo comparendo = em.find(Comparendo.class, procesaComparendoDTO.getComparendo().getCicomparendo());
            // Grado de alcholemia
            consultarGradoAlcoholemia(procesaComparendoDTO);

            ComparendoDTO comparendoDTO = ProcesaComparendoHelperExtend.ToComparendoDto.convert(procesaComparendoDTO);

            /*
             * Se ingresan datos de negocio del comparendo que no trae el procesa comparendo y se pierden en la transformacion
             */
            comparendoDTO.setCarteraGenerada(comparendo.getCarteraGenerada());

            comparendo = ProcesaComparendoHelperExtend.toComparendoRectificado(comparendo,
                    procesaComparendoRectificadoDTO, comparendoDTO);

            if (comparendo.getPersonaList() != null && !comparendo.getPersonaList().isEmpty()) {
                for (ComparendoPersona comparendoPersona : comparendo.getPersonaList()) {
                    if (comparendoPersona.getDireccion() != null) {

                        RespuestaIngresarDireccionDTO respuestaIngresarDireccionDTO = iFachadaAdminGeneral
                                .registrarDireccion(DireccionHelper.toLevel1DTO(comparendoPersona.getDireccion()),
                                        EnumTipoValidacionDireccion.INFRACCION);

                        comparendoPersona.getDireccion().setId(respuestaIngresarDireccionDTO.getIdDireccion());
                    }
                }
            }

            /*
             * Ingresamos la informacionj de la rectificacion del comparendo
             */
            registrarRectificacionComparendo(procesaComparendoRectificadoDTO, comparendo, enumActividadReg,
                    enumActividadImpo);
            return ComparendoHelperExtend.toComparendo(comparendo);
        } else {
            return null;
        }
    }

    /**
     * Se encarga de registrar todos los cambios que se modificaron al momento de rectificar el comparendo
     * 
     * @author giovanni.velandia
     */
    private void registrarRectificacionComparendo(ProcesaComparendoRectificadoDTO procesaComparendoRectificadoDTO,
            Comparendo comparendo, EnumActividad enumActividadReg, EnumActividad enumActividadImpo) {
        logger.debug("RecibirComparendoEJB::comportamientoInfractorCartera(ProcesarComparendoRectificacionDTO)");
        comparendo.setRectificaComparendoList(new ArrayList<RectificaComparendo>());

        // Ingresamos los datos de la rectificacion del comparendo
        RectificaComparendo rectificaComparendo = new RectificaComparendo();
        rectificaComparendo.setFecha(Calendar.getInstance().getTime());
        // Comparendo
        Comparendo comparendoRect = new Comparendo();
        comparendoRect.setCicomparendo(comparendo.getCicomparendo());
        rectificaComparendo.setComparendo(comparendoRect);

        // Ingreso de los campos rectificacion del comparendo
        rectificaComparendo.setCampoRectificaComparendos(new ArrayList<CampoRectificaComparendo>());
        for (CampoRectificaComparendoDTO campoRectificaComparendoDTO : procesaComparendoRectificadoDTO
                .getCampoRectificaComparendoDTOs()) {
            CampoRectificaComparendo campoRectificaComparendo = CampoRectificaComparendoHelper
                    .toLevel1Entity(campoRectificaComparendoDTO, new CampoRectificaComparendo());
            campoRectificaComparendo.setRectificaComparendo(rectificaComparendo);
            rectificaComparendo.getCampoRectificaComparendos().add(campoRectificaComparendo);
        }

        // Trazabiliad
        ComparendoDTO comparendoDTO = ComparendoHelperExtend.toComparendo(comparendo);
        TrazabilidadComparendoDTO trazabilidadDTO = RecibirComparendoHelper.generarTrazabilidadComparendoDTO(
                comparendoDTO, enumActividadImpo,
                UtilFecha.setHoraFecha(comparendo.getFechaInfraccion(), comparendo.getHoraInfraccion()),
                iRSeguridadCirculemos.obtenerUsuarioDto());

        TrazabilidadComparendo trazabilidadComparendo = TrazabilidadComparendoHelper.toLevel1Entity(trazabilidadDTO,
                null);

        /*
         * Se requiere consultar el tamaño de la lisata para que se realize la consulta en la base de datos y luego agregamos la nueva trazabilidad
         */
        comparendo.getTrazabilidadComparendoList().size();
        comparendo.getTrazabilidadComparendoList().add(trazabilidadComparendo);

        // Trazabilidad registro
        trazabilidadDTO = RecibirComparendoHelper.generarTrazabilidadComparendoDTO(comparendoDTO, enumActividadReg,
                Calendar.getInstance().getTime(), iRSeguridadCirculemos.obtenerUsuarioDto());

        trazabilidadComparendo = TrazabilidadComparendoHelper.toLevel1Entity(trazabilidadDTO, null);
        comparendo.getTrazabilidadComparendoList().add(trazabilidadComparendo);

        comparendo.getRectificaComparendoList().add(rectificaComparendo);
    }

    /**
     * Se encarga de consultar el grado de alcoholemia
     * 
     * @param procesaComparendoDTO
     * @author giovanni.velandia
     */
    private void consultarGradoAlcoholemia(ProcesaComparendoDTO procesaComparendoDTO) {
        logger.debug("RecibirComparendoEJB::consultarGradoAlcoholemia(ProcesarComparendoDTO)");
        // Colocar id del grado de alcoholemia en caso de necesitar
        if (procesaComparendoDTO.getGradoAlcoholemia() != null) {
            List<GradoAlcoholemiaDTO> gradosAlcoholemia = adminComparendoEjb
                    .consultarGradoAlcoholemia(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());
            for (GradoAlcoholemiaDTO gradoAlcoholemiaDTO : gradosAlcoholemia) {
                if (gradoAlcoholemiaDTO.getValor().equals(procesaComparendoDTO.getGradoAlcoholemia())) {
                    procesaComparendoDTO.setIdGradoAlcoholemia(gradoAlcoholemiaDTO.getId());
                    break;
                }
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UsuarioPersonaDTO obtenerUsuario() {
        return iRSeguridadCirculemos.obtenerUsuarioDto();
    }

    @Override
    public void crearLogC2(EnumErrorProcesamiento enumErrorProcesamiento, ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug(
                "RecibirComparendoEJB::crearLogC2(EnumErrorProcesamiento, ProcesarComparendoDTO,RespuestaValidacionDTO)");
        RecibirComparendoLogDTO recibirComparendoLogDTO = new RecibirComparendoLogDTO();
        recibirComparendoLogDTO.setEstadoTransaccion(enumErrorProcesamiento.name());
        recibirComparendoLogDTO.setFechaHoraProcesamiento(Calendar.getInstance().getTime());
        recibirComparendoLogDTO.setNombreUsuario(iRSeguridadCirculemos.obtenerUsuarioDto().getUsuario().getLogin());
        recibirComparendoLogDTO
                .setNumeroComparendo(procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo());
        recibirComparendoLogDTO.setOrigenValidacion(procesarComparendoDTO.getEnumProcesamiento().name());
        // Listado de errores
        if (respuestaValidacionDTO.getDetalleProcesamientoDTOs() != null
                && !respuestaValidacionDTO.getDetalleProcesamientoDTOs().isEmpty()) {
            recibirComparendoLogDTO.setListadoErrores(new ArrayList<String>());
            for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacionDTO
                    .getDetalleProcesamientoDTOs()) {
                recibirComparendoLogDTO.getListadoErrores()
                        .add(detalleProcesamientoDTO.getErrorProcesamiento().getCodigo()
                                + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
            }
        }
        logC2.escribir(iRSeguridadCirculemos.obtenerUsuarioDto().getUsuario().getLogin(), recibirComparendoLogDTO);
    }

    @Override
    public void registroNotificaciones(ComparendoDTO comparendoDTO, ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::registroNotificaciones(Comparendo, ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");

        // Llamamos a la configuracion para la fecha de notificacion si es igual a la de infraccion
        if (consultarConfiguracion010(configuracionInfraccionDTO, procesaComparendoDTO).isEsFechaImposicion()) {
            comparendoDTO.setFechaNotificacion(procesaComparendoDTO.getFechaInfraccion());
        }

        // Ingresamos a actualizar el tipo de notificacion para el comparendo
        TipoNotificacionComparendoDTO tipoNotificacionComparendoDTO = new TipoNotificacionComparendoDTO();
        tipoNotificacionComparendoDTO.setId(procesaComparendoDTO.getIdTipoNotificacionComparendo());
        comparendoDTO.setTipoNotificacion(tipoNotificacionComparendoDTO);

        // actualizar el estado del comparendo PASO 32 flujo basico
        EstadoComparendoDTO estadoComparendoDTO = new EstadoComparendoDTO();
        estadoComparendoDTO.setId(EnumEstadoComparendo.VIGENTE.getCodigo());
        comparendoDTO.setEstadoComparendo(estadoComparendoDTO);

        /*
         * Registramos la trazabilidad del comparendo PASO 3 flujo 17
         */
        TrazabilidadComparendoDTO trazabilidadDTO = RecibirComparendoHelper.generarTrazabilidadComparendoDTO(
                comparendoDTO, EnumActividad.COMPARENDOS_NOTIFICACION,
                UtilFecha.setHoraFecha(comparendoDTO.getFechaInfraccion(), comparendoDTO.getHoraInfraccion()),
                iRSeguridadCirculemos.obtenerUsuarioDto());
        if (comparendoDTO.getTrazabilidadComparendoList() == null) {
            comparendoDTO.setTrazabilidadComparendoList(new ArrayList<TrazabilidadComparendoDTO>());
        }
        comparendoDTO.getTrazabilidadComparendoList().add(trazabilidadDTO);
    }

    @Override
    public void registroNotificacionesConfiguracion(ComparendoDTO comparendoDTO,
            ProcesarComparendoDTO procesarComparendoDTO, ConfiguracionInfraccionDTO configuracionInfraccionDTO)
            throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::registroNotificacionesConfiguracion(Comparendo, ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");

        // Llamamos a la configuracion para la fecha de notificacion si es igual a la de infraccion
        if (consultarConfiguracion010(configuracionInfraccionDTO, procesarComparendoDTO.getProcesaComparendoDTO())
                .isEsFechaImposicion()) {
            comparendoDTO.setFechaNotificacion(procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion());
        }

        // Ingresamos a actualizar el tipo de notificacion para el comparendo
        TipoNotificacionComparendoDTO tipoNotificacionComparendoDTO = new TipoNotificacionComparendoDTO();
        tipoNotificacionComparendoDTO
                .setId(Integer.valueOf(consultarConfiguracion007(procesarComparendoDTO.getProcesaComparendoDTO())
                        .getTipoNotificacion().get(0)));
        comparendoDTO.setTipoNotificacion(tipoNotificacionComparendoDTO);

        // actualizar el estado del comparendo PASO 32 flujo basico
        EstadoComparendoDTO estadoComparendoDTO = new EstadoComparendoDTO();
        estadoComparendoDTO.setId(EnumEstadoComparendo.VIGENTE.getCodigo());
        comparendoDTO.setEstadoComparendo(estadoComparendoDTO);

        /*
         * Registramos la trazabilidad del comparendo PASO 3 flujo 17
         */
        TrazabilidadComparendoDTO trazabilidadDTO = RecibirComparendoHelper.generarTrazabilidadComparendoDTO(
                comparendoDTO, EnumActividad.COMPARENDOS_NOTIFICACION,
                UtilFecha.setHoraFecha(comparendoDTO.getFechaInfraccion(), comparendoDTO.getHoraInfraccion()),
                iRSeguridadCirculemos.obtenerUsuarioDto());
        if (comparendoDTO.getTrazabilidadComparendoList() == null) {
            comparendoDTO.setTrazabilidadComparendoList(new ArrayList<TrazabilidadComparendoDTO>());
        }
        comparendoDTO.getTrazabilidadComparendoList().add(trazabilidadDTO);
    }

    /**
     * Metodo que se encarga de generar cartera
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    public void generarCartera(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, ComparendoDTO comparendoDTO)
            throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::generarCartera(ProcesarComparendoDTO,ConfiguracionInfraccionDTO,ComparendoDTO)");
        boolean registroNotificacionesConfiguracion = false;
        boolean registroNotificaciones = false;

        /*
         * **** **** PASO 25 FLUJO BASICO **** **** Verificamos campos para la generacion de cartera
         */

        // Se calcula la fecha en la cual se debe generar cartera

        Date fechaGenerarCartera = iFachadaAdminGeneral.sumarDias(
                procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo(),
                procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion(),
                configuracionInfraccionDTO.getDiasGeneraCartera(),
                configuracionInfraccionDTO.getDiaHabilGeneraCartera());

        // Ingresamos la fecha de la generacion de la cartera
        // Sergio Torres (13/ene/2015) Campo t�cnico utilizado para no recalcular en el caso de la generaci�n de la cartera autom�tica
        comparendoDTO.setFechaGeneraCartera(fechaGenerarCartera);

        Date ahora = UtilFecha.buildCalendar().getTime();
        // Se compara que la Fecha actual sea MAYOR O IGUAL a la Fecha para generar cartera
        if (UtilFecha.resetTime(ahora).getTime().compareTo(UtilFecha.resetTime(fechaGenerarCartera).getTime()) >= 0) {

            RegistroCarteraComparendoDTO registroCarteraComparendoDTO = new RegistroCarteraComparendoDTO();
            TarifaLiquidacionDTO tarifaLiquidacionDTO = null;

            // Tarifa liquidacion
            try {

                // Consultamos el parametro para liquidar cartera
                ValorParametroDTO valorParametroDTO = consultarValorParametro(EnumParametro.ORGANISMO_LIQUIDA,
                        procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
                boolean liquidaTarifa = valorParametroDTO.getValorParamBoolean();
                if (liquidaTarifa) {
                    tarifaLiquidacionDTO = iProcesarComparendo.liquidarTarifaInfraccion(
                            procesarComparendoDTO.getProcesaComparendoDTO(), configuracionInfraccionDTO);
                } else {
                    // Valor enviado por el cliente para la liquidacion de la tarifa
                    tarifaLiquidacionDTO = new TarifaLiquidacionDTO();
                    tarifaLiquidacionDTO
                            .setValorLiquidado(procesarComparendoDTO.getProcesaComparendoDTO().getValorConcepto());
                }
                registroCarteraComparendoDTO.setTarifaLiquidacion(tarifaLiquidacionDTO);
            } catch (CirculemosNegocioException e) {
                if (!e.getErrorInfo().getCodigoError()
                        .equals(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048008.getCodigoError())) {
                    throw e;
                }
            }

            // **** **** PASO 26 FLUJO BASICO **** ****
            // Codigo organismo de transito
            registroCarteraComparendoDTO.setCodigoOrganismoTransito(
                    procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
            // fecha Imposicion
            registroCarteraComparendoDTO
                    .setFechaImposicion(procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion());
            // Id comparendo
            registroCarteraComparendoDTO.setIdComparendo(comparendoDTO.getCicomparendo());

            // Buscar en la lista de personas el infractor para saber el tipo de documento
            for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesarComparendoDTO
                    .getProcesaComparendoDTO().getProcesaComparendoPersonas()) {
                if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                        .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                    if (procesaComparendoPersonaDTO.getNumeroIdentificacion() != null
                            && procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {
                        // Tipo documento
                        registroCarteraComparendoDTO
                                .setIdTipoDocumento(procesaComparendoPersonaDTO.getIdTipoIdentificacion());
                        // Numero documento
                        registroCarteraComparendoDTO
                                .setNumeroDocumento(procesaComparendoPersonaDTO.getNumeroIdentificacion());
                    } else {
                        // Tipo documento
                        registroCarteraComparendoDTO
                                .setIdTipoDocumento(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.getValor());
                        // Numero documento
                        registroCarteraComparendoDTO.setNumeroDocumento(CEDULA_CERO);
                    }
                    break;
                }
            }

            // Numero comparendo
            registroCarteraComparendoDTO
                    .setNumeroComparendo(procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo());

            // Origen de la obligacion
            registroCarteraComparendoDTO.setOrigenObligacion(
                    procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
            // **** **** PASO 27 FLUJO BASICO **** ****
            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.CORREGIR_INCONSISTENCIA)
                    || procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECIBIR_COMPARENDO)) {
                // **** **** PASO 28 FLUJO BASICO **** ****
                long idCartera = iCarteraComparendo.registrarCarteraComparendo(registroCarteraComparendoDTO);
                // Confirmamos que se genero la cartera del comparendo
                comparendoDTO.setCarteraGenerada(true);

                // **** **** PASO 29 FLUJO BASICO **** ****
                if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion()
                        .equals(EnumMedioImposicion.ELECTRONICO_DEAP.getPk())
                        || procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion()
                                .equals(EnumMedioImposicion.MANUAL.getPk())) {

                    // **** **** PASO 30 FLUJO BASICO **** ****
                    // Registramos la notificacion actualizando el comparendo
                    registroNotificacionesConfiguracion = true;

                    // **** **** PASO 31 FLUJO BASICO **** ****
                    iCarteraComparendo.activarCarteraComparendo(idCartera,
                            procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion());
                } else {

                    // **** **** FLUJO BASICO 18 **** ****
                    // Medio de Imposición” del comparendo recibido es DIFERENTE a MANUAL o DEAP
                    if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoNotificacionComparendo() != null
                            && procesarComparendoDTO.getProcesaComparendoDTO().getFechaNotificacion() != null) {
                        // Los campos “Tipo de Notificación del Comparendo” y “Fecha Notificación” traen información asociada
                        registroNotificaciones = true;
                    } else {
                        registroNotificacionesConfiguracion = false;
                    }
                }

            } else {
                // Se afecta la liquidacion de la cartera dependiendo de la clase de rectificacion que se ingrese
                if (procesarComparendoDTO.getProcesaComparendoDTO().getIdActividad()
                        .equals(EnumActividad.SUSTITUCION.getValue())) {
                    // TODO por no funcionan
                    Long idInfractor = null;
                    for (ComparendoPersonaDTO comparendoPersonaDTO : comparendoDTO.getPersonaList()) {
                        if (comparendoPersonaDTO.getTipoPersonaComparendo().getCodigo()
                                .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                            idInfractor = comparendoPersonaDTO.getId();
                        }
                    }
                    iCarteraComparendo.actualizarDeudorCartera(comparendoDTO.getCicomparendo(), idInfractor);
                } else {
                    iCarteraComparendo.registrarNotaCartera(RecibirComparendoHelper
                            .toRegistroNotaCarteraDTO(comparendoDTO, procesarComparendoDTO.getProcesaComparendoDTO()));
                }
            }
        } else {

            // Flujo alterno FA16 - PASO 2
            if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion()
                    .equals(EnumMedioImposicion.ELECTRONICO_DEAP.getPk())
                    || procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion()
                            .equals(EnumMedioImposicion.MANUAL.getPk())) {

                // Registramos la notificacion actualizando el comparendo PASO 3
                registroNotificacionesConfiguracion = true;
            } else {
                // **** **** FLUJO BASICO 18 **** ****
                // Medio de Imposición” del comparendo recibido es DIFERENTE a MANUAL o DEAP
                if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoNotificacionComparendo() != null
                        && procesarComparendoDTO.getProcesaComparendoDTO().getFechaNotificacion() != null) {
                    // Los campos “Tipo de Notificación del Comparendo” y “Fecha Notificación” traen información asociada
                    registroNotificaciones = true;
                } else {
                    registroNotificacionesConfiguracion = false;
                }
            }
        }

        /*
         * **** Registro notificaciones ****
         */
        if (registroNotificacionesConfiguracion) {
            registroNotificacionesConfiguracion(comparendoDTO, procesarComparendoDTO, configuracionInfraccionDTO);
        }

        if (registroNotificaciones) {
            registroNotificaciones(comparendoDTO, procesarComparendoDTO.getProcesaComparendoDTO(),
                    configuracionInfraccionDTO);
        }
    }

    /**
     * Datos de la configuracion 006
     * 
     * @author giovanni.velandia
     * @param configuracionInfraccionDTO
     * @throws CirculemosNegocioException
     */
    private ConfiguracionGradosAlcoholDTO consultarConfiguracion006(ProcesaComparendoDTO procesaComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::consultarConfiguracion006(ConfiguracionInfraccionDTO)");

        ConfiguracionGradosAlcoholDTO cfgGradosAlcoholDTO = new ConfiguracionGradosAlcoholDTO();
        cfgGradosAlcoholDTO.setCodigoOrganismo(new ArrayList<String>());
        cfgGradosAlcoholDTO.getCodigoOrganismo()
                .add(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo().toString());
        cfgGradosAlcoholDTO.setFinPeriodoFecha(null);
        cfgGradosAlcoholDTO.setGradoAlcohol(new BigDecimal(procesaComparendoDTO.getGradoAlcoholemia()));
        cfgGradosAlcoholDTO.setInicioPeriodoFecha(procesaComparendoDTO.getFechaInfraccion());

        // COnfiguracion 006
        cfgGradosAlcoholDTO = iRFachadaConfiguracion
                .consultarValorConfiguracion(EnumConfiguracion.CONFIG_GRADOS_ALCOHOL.getCodigo(), cfgGradosAlcoholDTO);

        return cfgGradosAlcoholDTO;
    }

    @Override
    public AsociaComparendoFormularioDTO consultarConfiguracion008(
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::consultarConfiguracion008(ConfiguracionInfraccionDTO)");
        AsociaComparendoFormularioDTO asociaComparendoFormularioDTO = new AsociaComparendoFormularioDTO();
        // Ingreso el tipo comparendo
        List<String> tipoComparendo = new ArrayList<String>();
        tipoComparendo.add(String.valueOf(configuracionInfraccionDTO.getInfraccion().getTipoComparendo().getId()));
        asociaComparendoFormularioDTO.setTipoComparendo(tipoComparendo);

        // Me devuelve el tipo de formulario
        asociaComparendoFormularioDTO = iRFachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO.getCodigo(), asociaComparendoFormularioDTO);
        return asociaComparendoFormularioDTO;
    }

    /**
     * Medio de notificación= Personal 007
     * 
     * @throws CirculemosNegocioException
     */
    private ConfiguraMediosImposicionNotificacionDTO consultarConfiguracion007(
            ProcesaComparendoDTO procesaComparendoDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::consultarConfiguracion007(ProcesarComparendoDTO)");
        ConfiguraMediosImposicionNotificacionDTO configuraMediosImposicionNotificacionDTO = new ConfiguraMediosImposicionNotificacionDTO();

        // Ingreso tipo de comparendo list correspondiente a medios de imposicion
        List<String> mediosImposicion = new ArrayList<String>();
        mediosImposicion.add(String.valueOf(procesaComparendoDTO.getCodigoMedioImposicion()));

        // Ingresamos el organismo de transito
        List<String> codigoOrganismo = new ArrayList<String>();
        codigoOrganismo.add(String.valueOf(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo()));

        // Ingresamos los medios de imposicion
        configuraMediosImposicionNotificacionDTO.setMedioImposicion(mediosImposicion);
        // Ingresamos el organismo de transito
        configuraMediosImposicionNotificacionDTO.setCodigoOrganismo(codigoOrganismo);

        // Nos devuelve el tipo de notificacion
        configuraMediosImposicionNotificacionDTO = iRFachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.CONFIGURA_MEDIOS_IMPOSICION_NOTIFICACION.getCodigo(),
                configuraMediosImposicionNotificacionDTO);
        return configuraMediosImposicionNotificacionDTO;
    }

    /**
     * Fecha de notificación=Fecha de Imposición 010
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    private AsociaMedioImposicionTiposFechaDTO consultarConfiguracion010(
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, ProcesaComparendoDTO procesaComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::consultarConfiguracion010(OnfiguracionInfraccionDTO,ProcesarComparendoDTO)");
        AsociaMedioImposicionTiposFechaDTO asociaMedioImposicionTiposFechaDTO = new AsociaMedioImposicionTiposFechaDTO();

        List<String> tipoComparendo = new ArrayList<String>();
        tipoComparendo.add(configuracionInfraccionDTO.getInfraccion().getTipoComparendo().getCodigo());

        // Ingreso tipo de comparendo list correspondiente a medios de imposicion
        List<String> mediosImposicion = new ArrayList<String>();
        mediosImposicion.add(String.valueOf(procesaComparendoDTO.getCodigoMedioImposicion()));
        asociaMedioImposicionTiposFechaDTO.setMedioImposicion(mediosImposicion);
        // Ingresamos el organismo de transito
        List<String> codigoOrganismo = new ArrayList<String>();
        codigoOrganismo.add(String.valueOf(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo()));
        asociaMedioImposicionTiposFechaDTO.setCodigoOrganismo(codigoOrganismo);

        asociaMedioImposicionTiposFechaDTO = iRFachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.ASOCIA_MEDIO_IMPOSICION_TIPOS_DE_FECHA.getCodigo(),
                asociaMedioImposicionTiposFechaDTO);

        return asociaMedioImposicionTiposFechaDTO;
    }

    /**
     * Asocia días para ingresar comparendo 011
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    private AsociaDiasIngresoComparendoDTO consultarConfiguracion011(ProcesaComparendoDTO procesaComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::consultarConfiguracion01(ProcesarComparendoDTO)");

        AsociaDiasIngresoComparendoDTO asociaDiasIngresoComparendoDTO = new AsociaDiasIngresoComparendoDTO();
        // Ingreso tipo de comparendo list correspondiente a medios de imposicion
        List<String> mediosImposicion = new ArrayList<String>();
        mediosImposicion.add(String.valueOf(procesaComparendoDTO.getCodigoMedioImposicion()));
        asociaDiasIngresoComparendoDTO.setMedioImposicion(mediosImposicion);
        // Ingresamos el organismo de transito
        List<String> codigoOrganismo = new ArrayList<String>();
        codigoOrganismo.add(String.valueOf(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo()));
        asociaDiasIngresoComparendoDTO.setCodigoOrganismo(codigoOrganismo);

        // Me devuelve la cantidad de dias limites
        asociaDiasIngresoComparendoDTO = iRFachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.ASOCIA_DIAS_INGRESAR_COMPARENDO.getCodigo(), asociaDiasIngresoComparendoDTO);

        return asociaDiasIngresoComparendoDTO;
    }

    @Override
    public void validarExistenciaComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarExistenciaComparendo(ProcesarComparendoDTO,RespuestaValidacionDTO)");
        // Verificamos la existencia del comparendo
        boolean existe = existeComparendo(procesaComparendoDTO);

        if (existe) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_YA_EXISTE, respuestaValidacionDTO);
        }

        boolean existeComparendo = iLProcesaComparendo.existeProcesaComparendo(
                procesaComparendoDTO.getNumeroComparendo(),
                procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());

        if (existeComparendo) {
            // ERROR
            errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.EXISTE_COMPARENDO_INCOSISTENTE, respuestaValidacionDTO);
        }
    }

    @Override
    public void errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo enumCamposDetalleComparendo,
            EnumErrorProcesamiento enumErrorProcesamiento, RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug(
                "RecibirComparendoEJB::errorRechazoRespuestaValidacio(EnumCamposDetalleComparendo,EnumErrorProcesamiento,respuestaValidacionDTO)");

        if (respuestaValidacionDTO == null) {
            respuestaValidacionDTO = new RespuestaValidacionDTO();
            respuestaValidacionDTO.setDetalleProcesamientoDTOs(new ArrayList<DetalleProcesamientoDTO>());
        }

        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                .add(crearDetalleProcesamiento(enumCamposDetalleComparendo, enumErrorProcesamiento));
        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.RECHAZADO.getCodigo());
    }

    @Override
    public void actualizarComparendo(ComparendoDTO comparendoDTO) {
        logger.debug("RecibirComparendoEJB::actualizarComparendo(ComparendoDTO)");
        Comparendo comparendo = ComparendoHelperExtend.toComparendo(comparendoDTO, null);
        // Fecha de modificacion
        comparendo.setFechaModificacion(Calendar.getInstance().getTime());
        em.merge(comparendo);
    }

    /**
     * Se encarga de hacer el registro de orden comparendo nacional
     * 
     * @author giovanni.velandia
     * @return idLong
     */
    private Long registrarOrdenComparendoNaciona(ProcesaComparendoDTO procesaComparendoDTO) {
        logger.debug("RecibirComparendoEJB::registrarOrdenComparendoNaciona(ProcesaComparendoDTO)");
        OrdenComparendoNacionalDTO ordenCompNac = new OrdenComparendoNacionalDTO();
        ordenCompNac.setNumeroComparendo(procesaComparendoDTO.getNumeroComparendo());
        ordenCompNac.setOrganismoTransito(procesaComparendoDTO.getOrganismoTransito());
        OrdenComparendoNacional orden = OrdenComparendoNacionalHelper.toLevel1Entity(ordenCompNac, null);
        em.persist(orden);
        return orden.getCicomparendo();
    }

    /**
     * Se encarga de validar los datos de la Entidad agente de transito
     * 
     * @author giovanni.velandia
     * @param procesarComparendoDTO
     * @throws CirculemosNegocioException
     * @throws NumberFormatException
     */
    private void validarEntidadAgenteTransito(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO, ConfiguracionInfraccionDTO configuracionInfraccionDTO)
            throws NumberFormatException, CirculemosNegocioException {
        logger.debug(
                "RecibirComparendoEJB::validarEntidadAgenteTransito(ProcesaComparendoDTO, RespuestaVaidacionDTO, ConfiguracionInfraccionDTO)");

        // Entidad agente de transito
        if (!procesaComparendoDTO.getIdTipoAgenteImpositor().equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
            if (procesaComparendoDTO.getIdUnificacionResponsable() != null) {
                UnificacionResponsableDTO unificacionResponsableDTO = new UnificacionResponsableDTO();
                unificacionResponsableDTO = iLAdministracionFormularios
                        .consultarResponsableFormulario(procesaComparendoDTO.getNumeroComparendo(), Integer.parseInt(
                                consultarConfiguracion008(configuracionInfraccionDTO).getTipoFormulario().get(0)));
                if (unificacionResponsableDTO != null) {
                    if (!unificacionResponsableDTO.getId().equals(procesaComparendoDTO.getIdUnificacionResponsable())) {
                        // ERROR
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.UNIFICACION_RESPONSABLE,
                                        EnumErrorProcesamiento.ENTIDAD_AGENTE_INVALIADA));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());

                    }
                } else {
                    // ERROR
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(crearDetalleProcesamiento(EnumCamposDetalleComparendo.UNIFICACION_RESPONSABLE,
                                    EnumErrorProcesamiento.ENTIDAD_AGENTE_INVALIADA));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
            }
        }
    }

    @Override
    public void validarDirecciones(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarDirecciones(ProcesaComparendoDTO,RespuestaValidacionDTO)");

        // Direccion comparendo
        validarDireccion(procesaComparendoDTO.getProcesaDireccionComparendo(), respuestaValidacionDTO,
                EnumCamposDetalleComparendo.DIRECCION_COMPARENDO, EnumErrorProcesamiento.DIRECCION_INFRACCION_INVALIDA);

        // Direccion Infractor - Testigo
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            // Infractor
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                validarDireccion(procesaComparendoPersonaDTO.getProcesaDireccion(), respuestaValidacionDTO,
                        EnumCamposDetalleComparendo.DIRECCION_DEL_INFRACTOR,
                        EnumErrorProcesamiento.DIRECCION_INFRACTOR_INVALIDA);
            }
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.TESTIGO.getCodigo())) {
                // Testigo
                validarDireccion(procesaComparendoPersonaDTO.getProcesaDireccion(), respuestaValidacionDTO,
                        EnumCamposDetalleComparendo.DIRECCION_DEL_TESTIGO,
                        EnumErrorProcesamiento.DIRECCION_TESTIGO_INVALIDA);
            }
        }

        // Direccion Patio
        validarDireccion(procesaComparendoDTO.getProcesaDireccionPatio(), respuestaValidacionDTO,
                EnumCamposDetalleComparendo.DIRECCION_PATIO, EnumErrorProcesamiento.DIRECCION_PATIO_INVALIDA);
    }

    /**
     * 
     * {@link IRFachadaAdminGeneral#validarDireccion(co.com.datatools.c2.dto.comun.DireccionDTO)}. La direccion no es valida se agrega un resultado de
     * {@link EnumErrorProcesamiento#INCONSISTENTE}.
     * 
     * @param procesaDireccionDTO
     * @param respuestaValidacionDTO
     * @param campoDetalleComparendo
     * @param errorProcesamiento
     * @author rodrigo.cruz - giovanni.velandia(mod 08/04/2016)
     */
    @SuppressWarnings("rawtypes")
    private void validarDireccion(ProcesaDireccionDTO procesaDireccionDTO,
            RespuestaValidacionDTO respuestaValidacionDTO, EnumCamposDetalleComparendo campoDetalleComparendo,
            EnumErrorProcesamiento errorProcesamiento) {
        logger.debug(
                "RecibirComparendoEJB::validarDireccion(ProcesaDireccionDTO,RespuestaValidacionDTO,EnumCamposDetalleComparendo,EnumErrorProcesamiento)");

        if (procesaDireccionDTO != null) {
            RespuestaDTO respuestaDir = iFachadaAdminGeneral.validarDireccion(
                    ProcesaComparendoHelperExtend.ToComparendoDto.procesarDireccion(procesaDireccionDTO),
                    EnumTipoValidacionDireccion.INFRACCION);
            if (respuestaDir.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                        .add(crearDetalleProcesamiento(campoDetalleComparendo, errorProcesamiento));
                respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
            }
        }
    }

    @Override
    public void notificarComparendoTerceros(Long cicomparendo, Integer codigoOrganismo,
            EnumOrigenNotificacionTercero origenNotificacion) {
        NotificacionComparendoTerceroDTO comparendoTerceroDTO = new NotificacionComparendoTerceroDTO();
        comparendoTerceroDTO.setCicomparendo(cicomparendo);
        comparendoTerceroDTO.setCodigoOrganismo(codigoOrganismo);
        comparendoTerceroDTO.setOrigenNotificacionTercero(origenNotificacion);
        irNotificacionComparendoTercero.notificarComparendo(comparendoTerceroDTO);
    }

    @Override
    public ValorParametroDTO consultarValorParametro(EnumParametro enumParametro, Integer codOrganismoTransito) {
        return iFachadaAdminGeneral.consultarValorParametro(enumParametro, codOrganismoTransito, true);
    }

    @Override
    public ConfiguracionInfraccionDTO consultarConfiguracionInfraccion(ProcesaComparendoDTO procesaComparendoDTO) {
        return iLInfraccion.consultarInfraccion(procesaComparendoDTO.getCodigoInfraccion(),
                procesaComparendoDTO.getFechaInfraccion());
    }

    @Override
    public void consumirReservaOCN(ProcesaComparendoDTO procesaComparendoDTO) throws CirculemosNegocioException {
        iLComparendo.consumirReservaOCN(procesaComparendoDTO.getNumeroComparendo(), UtilFecha
                .setHoraFecha(procesaComparendoDTO.getFechaInfraccion(), procesaComparendoDTO.getHoraInfraccion()));
    }

    /**
     * Se encarga de validar la tarifa infraccion
     * 
     * @param respuestaValidacionDTO
     * @param procesaComparendoDTO
     * @param configuracionInfraccionDTO
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     * 
     */
    private void validarTarifaInfraccion(RespuestaValidacionDTO respuestaValidacionDTO,
            ProcesaComparendoDTO procesaComparendoDTO, ConfiguracionInfraccionDTO configuracionInfraccionDTO)
            throws CirculemosNegocioException {

        // Consultamos el parametro para liquidar cartera
        ValorParametroDTO valorParametroDTO = consultarValorParametro(EnumParametro.ORGANISMO_LIQUIDA,
                procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());
        boolean liquidaTarifa = valorParametroDTO.getValorParamBoolean();
        if (liquidaTarifa) {
            /*
             * VALIDACION DATOS COMPARENDO DE NEGOCIO
             */
            boolean erroTipoServico = false;
            for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacionDTO
                    .getDetalleProcesamientoDTOs()) {
                if (detalleProcesamientoDTO.getErrorProcesamiento().getCodigo()
                        .equals(EnumErrorProcesamiento.CLASE_DE_SERVICIO.getCodigo())) {
                    erroTipoServico = true;
                    break;
                }
            }

            // Tarifa liquidacion
            if (!erroTipoServico) {
                try {
                    iProcesarComparendo.liquidarTarifaInfraccion(procesaComparendoDTO, configuracionInfraccionDTO);
                } catch (CirculemosNegocioException e) {
                    if (!e.getErrorInfo().getCodigoError()
                            .equals(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048008.getCodigoError())) {
                        throw e;
                    }
                    // tratamiento
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(iProcesarComparendo.crearDetalleProcesamiento(EnumCamposDetalleComparendo.INFRACCION,
                                    EnumErrorProcesamiento.INFRACCION_NO_TIENE_TARIFA));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());

                }
            }
        } else {

            if (procesaComparendoDTO.getIdActividad() != null) {
                if (!procesaComparendoDTO.getIdActividad().equals(EnumActividad.SUSTITUCION.getValue())) {

                    /*
                     * Validamos la informacion del valor concepto
                     */
                    if (procesaComparendoDTO.getValorConcepto() == null) {
                        // tratamiento
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(iProcesarComparendo.crearDetalleProcesamiento(
                                        EnumCamposDetalleComparendo.INFRACCION,
                                        EnumErrorProcesamiento.VALOR_CONCERPTO));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                    }
                }
            }
        }

    }

    @Override
    public boolean existeComparendo(ProcesaComparendoDTO procesaComparendoDTO) {
        boolean existe = iLComparendo.existeComparendo(procesaComparendoDTO.getNumeroComparendo(),
                procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());
        return existe;
    }

    @Override
    public boolean existeFormulario(String numeroFormulario, int idTipoFormulario) {
        return iLFormulario.existeFormulario(numeroFormulario, idTipoFormulario);
    }

    @Override
    public boolean existeEstadoFormularioOrganismo(String numeroFormulario, Integer idTipoFormulario,
            Integer idCodigoOrganismo, EnumEstadoFomulario... enumEstadoFomulario) {
        return iLFormulario.existeEstadoFormularioOrganismo(numeroFormulario, idTipoFormulario, idCodigoOrganismo,
                enumEstadoFomulario);
    }

    @Override
    public boolean existeInfraccion(String codigoInfraccion) {
        boolean existeInfraccion = false;
        InfraccionDTO infraccionDTO = iLInfraccion.consultarInfraccion(codigoInfraccion);
        if (infraccionDTO != null) {
            existeInfraccion = true;
        }
        return existeInfraccion;
    }
}