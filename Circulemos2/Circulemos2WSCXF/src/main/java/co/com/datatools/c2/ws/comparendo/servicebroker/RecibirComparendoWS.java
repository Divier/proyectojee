package co.com.datatools.c2.ws.comparendo.servicebroker;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.configuracion.AsociaComparendoFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.DetalleRespuestaWSDTO;
import co.com.datatools.c2.dto.ws.DireccionWSDTO;
import co.com.datatools.c2.dto.ws.EvidenciaWSDTO;
import co.com.datatools.c2.dto.ws.PersonaWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaRecibirComparendoWSDTO;
import co.com.datatools.c2.entidades.ClaseVehiculo;
import co.com.datatools.c2.entidades.EstadoComparendo;
import co.com.datatools.c2.entidades.MedioImposicionComparendo;
import co.com.datatools.c2.entidades.Modalidad;
import co.com.datatools.c2.entidades.RadioAccion;
import co.com.datatools.c2.entidades.TipoAgenteImpositor;
import co.com.datatools.c2.entidades.TipoCategLicenciaConduccion;
import co.com.datatools.c2.entidades.TipoEvidencia;
import co.com.datatools.c2.entidades.TipoInfractor;
import co.com.datatools.c2.entidades.TipoNotificacionComparendo;
import co.com.datatools.c2.entidades.TipoServicio;
import co.com.datatools.c2.entidades.TipoTransportePasajero;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.c2.enumeracion.EnumCatalogosHomologacion;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumPais;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.administracion.ILInfraccion;
import co.com.datatools.c2.negocio.interfaces.formularios.ILAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.ws.comparendo.helper.RecibirComparendoHelper;
import co.com.datatools.c2.ws.comparendo.utilidades.LogRecibirComparendo;
import co.com.datatools.c2.ws.comparendo.utilidades.enumeracion.EnumProcesamientoWS;
import co.com.datatools.util.dto.EntidadC2;

/**
 * EJB encargado de procesar la peticion del WS de recibir comparendo <b>COM_001</b>
 * 
 * @author luis.forero(2015-11-12)
 * 
 */
@Stateless(name = "RecibirComparendoWS")
@LocalBean
public class RecibirComparendoWS implements ILRecibirComparendoWS {

    /**
     * 
     */
    private static final String IGUAL = "=";

    /**
     * 
     */
    private static final String SEPARADOR = " - ";

    /**
     * 
     */
    private static final String ERR_CAMPO = ": ";

    /**
     * 
     */
    private static final String NOMBRE_PARAMETRO_CODIGO = "pCod";

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.RECIBIR_COMPARENDO_WS);
    private static final Logger loggerServer = Logger.getLogger(RecibirComparendoWS.class.getName());

    // Listado que contiene las respuestas posibles de recibir comparendo.
    private static List<RespuestaWebServiceDTO> lstRespuestasWSRecibir = null;

    private static final Integer ORGANISMO_TRANSITO = 11001;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILRecibirComparendo recibirComparendoEJB;

    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;

    @EJB
    private IRFachadaConfiguracion fachadaConfiguracionEJB;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneralEJB;

    @EJB
    private ILInfraccion infraccionEJB;

    @EJB
    private ILAdministracionFormularios administracionFormulariosEJB;

    @EJB
    private ILAdministracion administracionEJB;

    @Override
    public RespuestaRecibirComparendoWSDTO procesarComparendoWs(ComparendoWSDTO comparendo) {
        String login = seguridadCirculemosEJB.obtenerUsuarioDto().getLogin();
        LogRecibirComparendo logRecibirComparendo = null;
        DetalleRespuestaWSDTO errorInesperado = construirDetalleRespuestaWSDTO(
                EnumRespuestaWebServices.NO_SE_PUDO_REALIZA_TRANSACCION);

        RespuestaRecibirComparendoWSDTO respuestaWSDTO = new RespuestaRecibirComparendoWSDTO();
        respuestaWSDTO.setDetalle(new ArrayList<DetalleRespuestaWSDTO>(0));

        if (comparendo.getNumeroFactura() == null) {
            logRecibirComparendo = new LogRecibirComparendo(null, null);
            // Se define el error correspondiente a el numero de factura ya que es necesario para la recepcion del log
            RespuestaWebServiceDTO errorBloqueante = consultarErrorProcesamiento(
                    EnumRespuestaWebServices.FALTA_NUMERO_FACTURA);
            DetalleRespuestaWSDTO errorFactura = construirDetalleRespuestaWSDTO(
                    EnumRespuestaWebServices.FALTA_NUMERO_FACTURA);
            logRecibirComparendo.setEstadoTransaccion(errorBloqueante.getCodigo());
            respuestaWSDTO.setCodigoGeneral(errorBloqueante.getCodigo());
            respuestaWSDTO.getDetalle().add(errorFactura);
            logRecibirComparendo.setDetalle(errorBloqueante.getDescripcion());
            logger.escribir(login, logRecibirComparendo);
            return respuestaWSDTO;
        } else {
            logRecibirComparendo = new LogRecibirComparendo(comparendo.getNumeroFactura().toString(), null);
        }

        try {

            comparendo.setCodigoOrigenValidacion(EnumProcesamiento.RECIBIR_COMPARENDO.getValue());

            ProcesarComparendoDTO procesarComparendoDTO = RecibirComparendoHelper.ToProcesaComparendoDto
                    .convertComparendoWs(comparendo);
            homologarCatalogos(procesarComparendoDTO, comparendo, respuestaWSDTO, logRecibirComparendo);

            // Organismo de transito
            // Se establece en la reglas de negocio que este valor debe ser siempre el mismo valor "11001"
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(ORGANISMO_TRANSITO);
            procesarComparendoDTO.getProcesaComparendoDTO().setOrganismoTransito(organismoTransitoDTO);

            // Pais infraccion
            // Se establece en la reglas de negocio que este valor debe ser siempre el mismo valor "ECU"
            if (procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionComparendo() != null
                    && procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionComparendo()
                            .getComplemento() != null) {
                procesarComparendoDTO.getProcesaComparendoDTO().getProcesaDireccionComparendo()
                        .setIdPais(EnumPais.ECUADOR.getValue());
            }

            // Pais infractor
            // Se establece en la reglas de negocio que este valor debe ser siempre el mismo valor "ECU"
            for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesarComparendoDTO
                    .getProcesaComparendoDTO().getProcesaComparendoPersonas()) {
                if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                        .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                    if (procesaComparendoPersonaDTO.getProcesaDireccion() != null
                            && procesaComparendoPersonaDTO.getProcesaDireccion().getComplemento() != null) {
                        procesaComparendoPersonaDTO.getProcesaDireccion().setIdPais(EnumPais.ECUADOR.getValue());
                    }
                    break;
                }

            }

            // LLeva a cabo la asignacion del campo de salida de verificacion del responsable
            verificarResponsable(procesarComparendoDTO, respuestaWSDTO);

            if (!respuestaWSDTO.getDetalle().isEmpty()) {
                respuestaWSDTO.setCodigoGeneral(
                        consultarErrorProcesamiento(EnumRespuestaWebServices.ERROR_BLOQUEANTE).getCodigo());
                logRecibirComparendo = new LogRecibirComparendo(
                        procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo(),
                        respuestaWSDTO.getCodigoGeneral());
                return respuestaWSDTO;
            }

            RespuestaValidacionDTO recibirComparendo = recibirComparendoEJB.recibirComparendo(procesarComparendoDTO);

            procesarRespuestaRecibirComparendo(recibirComparendo, respuestaWSDTO);
            // Registrar respuesta y escribir resultado en logger.
            logRecibirComparendo = new LogRecibirComparendo(
                    procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo(),
                    respuestaWSDTO.getCodigoGeneral());
        } catch (CirculemosNegocioException e) {
            // Se define el error correspondiente a algo inesperado.
            RespuestaWebServiceDTO errorBloqueante = consultarErrorProcesamiento(
                    EnumRespuestaWebServices.ERROR_BLOQUEANTE);
            logRecibirComparendo.setEstadoTransaccion(errorBloqueante.getCodigo());
            respuestaWSDTO.setCodigoGeneral(errorBloqueante.getCodigo());
            respuestaWSDTO.getDetalle().add(errorInesperado);
            respuestaWSDTO.setErrorExepcion(e.getErrorInfo().getDescError());
            logRecibirComparendo.setDetalle(e.getMessage());
        } finally {
            logRecibirComparendo.setEstadoTransaccion(respuestaWSDTO.getCodigoGeneral());
            logger.escribir(login, logRecibirComparendo);
        }
        return respuestaWSDTO;
    }

    /**
     * Verifica el responsable del formulario y lo asigna al comparendo procesado dependiendo de las validaciones efectuadas
     * 
     * @param procesarComparendoDTO
     *            objeto al cual se le agrega la informacion del responsable
     * @param respuestaWSDTO
     *            respuesta a la cual se agregan los posibles errores o validaciones efectuadas sobre el responsable de formulario
     * @author luis.forero(2015-11-19)
     */
    private void verificarResponsable(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaRecibirComparendoWSDTO respuestaWSDTO) throws CirculemosNegocioException {
        ProcesaComparendoDTO procesaComparendoDTO = procesarComparendoDTO.getProcesaComparendoDTO();

        if (procesaComparendoDTO.getIdTipoAgenteImpositor() != null) {
            EnumTipoAgenteImpositor tipoAgenteImpositor = Utilidades.buscarElemEnum(EnumTipoAgenteImpositor.class,
                    procesaComparendoDTO.getIdTipoAgenteImpositor());
            switch (tipoAgenteImpositor) {
            // Si es polca se consulta el nombre del responsable para POLCA de los parametros del sistema
            case POLCA:
                ValorParametroDTO nombrePolca = fachadaAdminGeneralEJB.consultarValorParametro(
                        EnumParametro.NOMBRE_POLCA,
                        seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);
                procesaComparendoDTO.setNombreResponsable(nombrePolca.getValorParam());
                break;

            // Si no es polca se lleva a cabo la consulta del responsable desde el modulo de formularios.
            default:
                if (procesaComparendoDTO.getCodigoInfraccion() == null) {
                    break;
                }

                // Se lleva a cabo la consulta de la infraccion para obtener el tipo de comparendo
                // TODO LWF Validar que sucede cuando la infraccion no contiene un tipo de infraccion.
                InfraccionDTO infraccion = infraccionEJB
                        .consultarInfraccion(procesaComparendoDTO.getCodigoInfraccion());

                // Se obtiene con el tipo de comparendo el tipo de formulario de las configuraciones existentes ADM_066
                AsociaComparendoFormularioDTO dto = new AsociaComparendoFormularioDTO();
                dto.setTipoComparendo(
                        Arrays.asList(new String[] { String.valueOf(infraccion.getTipoComparendo().getId()) }));
                dto = fachadaConfiguracionEJB.consultarValorConfiguracion(
                        EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO.getCodigo(), dto);
                List<String> tipoFormulario = dto.getTipoFormulario();

                // Si no encuentra un tipo de formulario arroja un ERROR INESPERADO
                if (tipoFormulario == null || tipoFormulario.isEmpty()) {
                    respuestaWSDTO.setCodigoGeneral(
                            consultarErrorProcesamiento(EnumRespuestaWebServices.ERROR_BLOQUEANTE).getCodigo());
                    respuestaWSDTO.getDetalle().add(
                            construirDetalleRespuestaWSDTO(EnumRespuestaWebServices.NO_SE_PUDO_REALIZA_TRANSACCION));
                    return;
                }

                // Se lleva a cabo la consulta del responsable de formulario y lo asigna al comparendo a procesar
                UnificacionResponsableDTO unificacionResponsable = administracionFormulariosEJB
                        .consultarResponsableFormulario(procesaComparendoDTO.getNumeroComparendo(),
                                Integer.valueOf(tipoFormulario.get(0)));
                if (unificacionResponsable != null) {
                    procesaComparendoDTO.setIdUnificacionResponsable(unificacionResponsable.getId());
                }
                break;
            }
        }

    }

    /**
     * Respuesta de validacion efectuada del comparendo.
     * 
     * @param recibirComparendo
     *            respuesta de valdiaciones de comparendo.
     * @param respuestaWSDTO
     *            respuesta a ser insertados la respuesta del comparendo.
     * @author luis.forero(2015-11-13)
     */
    private void procesarRespuestaRecibirComparendo(RespuestaValidacionDTO recibirComparendo,
            final RespuestaRecibirComparendoWSDTO respuestaWSDTO) {
        // Se consulta el codigo correspondiente al codigo general de respuesta del web service respondido
        String codigoGeneral = consultarErrorProcesamiento(Utilidades
                .buscarElemEnum(EnumProcesamientoWS.class,
                        Utilidades.buscarElemEnum(EnumErrorProcesamiento.class, recibirComparendo.getCodigoResultado()))
                .getCodigo()).getCodigo();

        // Se lleva a cabo asignacion del codigo de respuesta del ws junto con cada uno de sus detalles.
        if (codigoGeneral != null) {
            respuestaWSDTO.setCodigoGeneral(codigoGeneral);

            // Recorre los datos de procesamiento del comparendo y los agrega a la lista detallada de respuesta del web service
            if (recibirComparendo.getDetalleProcesamientoDTOs() != null
                    || !recibirComparendo.getDetalleProcesamientoDTOs().isEmpty()) {
                for (DetalleProcesamientoDTO detalleProcesamiento : recibirComparendo.getDetalleProcesamientoDTOs()) {
                    DetalleRespuestaWSDTO detalleRespuestaWSDTO = new DetalleRespuestaWSDTO();
                    if (detalleProcesamiento.getErrorProcesamiento() != null) {
                        detalleRespuestaWSDTO.setCodigo(detalleProcesamiento.getErrorProcesamiento().getCodigo());
                        detalleRespuestaWSDTO
                                .setDescripcion(detalleProcesamiento.getErrorProcesamiento().getDescripcion());
                    }
                    respuestaWSDTO.getDetalle().add(detalleRespuestaWSDTO);
                }
            }

        } else {
            // Si no encuentra ningun codigo mapeado correspondiente al de respuesta responde error
            respuestaWSDTO.setCodigoGeneral(
                    consultarErrorProcesamiento(EnumProcesamientoWS.ERROR_COMPARENDO_RECHAZADO.getCodigo())
                            .getCodigo());
            respuestaWSDTO.getDetalle().add(construirDetalleRespuestaWSDTO(EnumRespuestaWebServices.VALOR_NO_MAPEADO));
        }
    }

    /**
     * Construye un detalle de respuesta con un error determinado
     * 
     * @param errorProcesamiento
     *            Enumeracion del error de procesamiento definido para el determinado suceso.
     * @return detalle de respuesta con el error junto con su codigo y descripcion correspondientes
     * @author luis.forero(2015-11-18)
     */
    private DetalleRespuestaWSDTO construirDetalleRespuestaWSDTO(EnumRespuestaWebServices errorProcesamiento) {
        DetalleRespuestaWSDTO detalleRespuestaWSDTO = new DetalleRespuestaWSDTO();
        RespuestaWebServiceDTO error = consultarErrorProcesamiento(errorProcesamiento);
        detalleRespuestaWSDTO.setCodigo(error.getCodigo());
        detalleRespuestaWSDTO.setDescripcion(error.getDescripcion());
        return detalleRespuestaWSDTO;
    }

    /**
     * Homologa y asigna el valor correspondiente de cada uno de los catalogos entrantes
     * 
     * @param procesarComparendoDTO
     *            objeto convertido con los datos de entrada y los catalogos
     * @param comparendo
     *            datos del comparendo entrante
     * @param respuestaWSDTO
     *            para agregar al listado de detalles un posible error sobre una determinada homologacion de uno de los datos de entrada
     * @param logRecibirComparendo
     */
    private void homologarCatalogos(ProcesarComparendoDTO procesarComparendoDTO, ComparendoWSDTO comparendo,
            RespuestaRecibirComparendoWSDTO respuestaWSDTO, LogRecibirComparendo logRecibirComparendo) {
        ProcesaComparendoDTO procesaComparendoDTO = procesarComparendoDTO.getProcesaComparendoDTO();
        // **************************************
        // Homologacion de catalogos del encabezado
        // **************************************
        // TIPO COMPARENDO-> MedioImposicionComparendo
        if (StringUtils.isNotBlank(comparendo.getCodigoMedioImposicion())) {
            Integer id = consultarItemCatalogo(MedioImposicionComparendo.class, comparendo.getCodigoMedioImposicion());
            if (id != null) {
                procesaComparendoDTO.setCodigoMedioImposicion(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoMedioImposicion, comparendo.getCodigoMedioImposicion());
            }
        }

        // TIPO AGENTE IMPOSITOR
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoAgenteImpositor())) {
            Integer id = consultarItemCatalogo(TipoAgenteImpositor.class, comparendo.getCodigoTipoAgenteImpositor());
            if (id != null) {
                procesaComparendoDTO.setIdTipoAgenteImpositor(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoTipoAgenteImpositor, comparendo.getCodigoTipoAgenteImpositor());
            }
        }

        // ESTADO COMPARENDO
        if (StringUtils.isNotBlank(comparendo.getCodigoEstado())) {
            Integer id = consultarItemCatalogo(EstadoComparendo.class, comparendo.getCodigoEstado());
            if (id != null) {
                procesaComparendoDTO.setIdEstadoComparendo(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo, EnumCatalogosHomologacion.codigoEstado,
                        comparendo.getCodigoEstado());
            }
        }

        // TIPO NOTIFICACION COMPARENDO
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoNotificacion())) {
            Integer id = consultarItemCatalogo(TipoNotificacionComparendo.class,
                    comparendo.getCodigoTipoNotificacion());
            if (id != null) {
                procesaComparendoDTO.setIdTipoNotificacionComparendo(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoTipoNotificacion, comparendo.getCodigoTipoNotificacion());
            }
        }

        // Homologa datos de catalogos de la direccion para la infraccion
        homologaCatalogosDireccion(procesaComparendoDTO.getProcesaDireccionComparendo(),
                comparendo.getDireccionInfraccion(), respuestaWSDTO, logRecibirComparendo);

        // **************************************
        // Homologacion de catalogos del vehiculo
        // **************************************
        // TIPO DE SERVICIO
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoServicio())) {
            Integer idTipoServicio = consultarIdCatalogo(TipoServicio.class, comparendo.getCodigoTipoServicio());
            if (idTipoServicio != null) {
                procesaComparendoDTO.setIdTipoServicio(idTipoServicio);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoTipoServicio, comparendo.getCodigoTipoServicio());
            }
        }

        // CLASE DE VEHICULO o tipo de vehiculo
        if (StringUtils.isNotBlank(comparendo.getCodigoClaseVehiculo())) {
            Integer id = consultarIdCatalogo(ClaseVehiculo.class, comparendo.getCodigoClaseVehiculo());
            if (id != null) {
                procesaComparendoDTO.setIdClaseVehiculo(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoClaseVehiculo, comparendo.getCodigoClaseVehiculo());
            }
        }

        // RADIO DE ACCION
        if (StringUtils.isNotBlank(comparendo.getCodigoRadioAccion())) {
            Integer id = consultarIdCatalogo(RadioAccion.class, comparendo.getCodigoRadioAccion());
            if (id != null) {
                procesaComparendoDTO.setIdRadioAccion(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo, EnumCatalogosHomologacion.codigoRadioAccion,
                        comparendo.getCodigoRadioAccion());
            }
        }

        // MODALIDAD TRANSPORTE
        if (StringUtils.isNotBlank(comparendo.getCodigoModalidad())) {
            Integer id = consultarIdCatalogo(Modalidad.class, comparendo.getCodigoModalidad());
            if (id != null) {
                procesaComparendoDTO.setIdModalidad(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo, EnumCatalogosHomologacion.codigoModalidad,
                        comparendo.getCodigoModalidad());
            }
        }

        // TIPO TRANSPORTE DE PASAJEROS
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoTransportePasajero())) {
            Integer id = consultarIdCatalogo(TipoTransportePasajero.class,
                    comparendo.getCodigoTipoTransportePasajero());
            if (id != null) {
                procesaComparendoDTO.setIdTipoServicio(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoTipoTransportePasajero,
                        comparendo.getCodigoTipoTransportePasajero());
            }
        }

        // **************************************
        // Infractor
        // **************************************
        // TIPO INFRACTOR
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoInfractor())) {
            Integer id = consultarItemCatalogo(TipoInfractor.class, comparendo.getCodigoTipoInfractor());
            if (id != null) {
                procesaComparendoDTO.setCodigoTipoInfractor(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoTipoInfractor, comparendo.getCodigoTipoInfractor());
            }
        }
        // **************************************
        // AGENTE
        // **************************************
        // CODIGO TIPO DE IDENTIFICACION DEL AGENTE
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoIdentificacionAgente())) {
            Integer id = consultarItemCatalogo(TipoIdentificacionPersona.class,
                    comparendo.getCodigoTipoIdentificacionAgente());
            if (id != null) {
                procesaComparendoDTO.setIdTipoIdentificacionAgente(id);
            } else {
                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                        EnumCatalogosHomologacion.codigoTipoIdentificacionAgente,
                        comparendo.getCodigoTipoIdentificacionAgente());
            }
        }

        // **************************************
        // INMOVILIZACION
        // **************************************
        // VALIDACION DIRECCION PATIO
        homologaCatalogosDireccion(procesaComparendoDTO.getProcesaDireccionPatio(), comparendo.getDireccionPatio(),
                respuestaWSDTO, logRecibirComparendo);

        // **************************************
        // HOMOLOGACION DE PERSONAS RECIBIDAS
        // **************************************
        homologarPersonas(procesaComparendoDTO.getProcesaComparendoPersonas(), comparendo, respuestaWSDTO,
                logRecibirComparendo);
        // **************************************
        // HOMOLOGACION DE EVIDENCIAS
        // **************************************
        homologarEvidencias(procesaComparendoDTO.getProcesaEvidencias(), comparendo, respuestaWSDTO,
                logRecibirComparendo);

    }

    /**
     * Homologa los catalogos de las evidencias recibidas al objeto destino
     * 
     * @param procesaEvidencias
     *            listado de evidencias procesadas a homologar
     * @param comparendo
     *            comparendo con el listado de las evidencias sin procesar para homologar los catalogos
     * @param respuestaWSDTO
     *            respuesta a la cual se registrara problemas de existir en la homologacion
     * @param logRecibirComparendo
     *            log del caso de uso
     * @author luis.forero(2015-12-10) giovanni.velandia(mod 2016-11-25)
     */
    private void homologarEvidencias(List<ProcesaEvidenciaDTO> procesaEvidencias, ComparendoWSDTO comparendo,
            RespuestaRecibirComparendoWSDTO respuestaWSDTO, LogRecibirComparendo logRecibirComparendo) {
        // para cada uno se homologan sus catalogos
        for (ProcesaEvidenciaDTO procesaEvidenciaDTO : procesaEvidencias) {
            for (EvidenciaWSDTO evidenciaWSDTO : comparendo.getEvidencias()) {
                if (evidenciaWSDTO != null) {
                    if (evidenciaWSDTO.getNombre().equals(procesaEvidenciaDTO.getArchivoTransportable().getNombre())
                            && evidenciaWSDTO.getArchivo()
                                    .equals(procesaEvidenciaDTO.getArchivoTransportable().getContenido())) {
                        // HOMOLOGA SU TIPO DE EVIDENCIA
                        if (StringUtils.isNotBlank(evidenciaWSDTO.getCodigoTipoEvidencia())) {
                            Integer idTipoEvidencia = consultarItemCatalogo(TipoEvidencia.class,
                                    evidenciaWSDTO.getCodigoTipoEvidencia());
                            if (idTipoEvidencia == null) {
                                procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                                        EnumCatalogosHomologacion.codigoTipoEvidencia,
                                        evidenciaWSDTO.getCodigoTipoEvidencia());
                                return;
                            }
                            procesaEvidenciaDTO.setCodigoTipoEvidencia(idTipoEvidencia);
                        }
                        break;
                    }
                }
            }
        }

    }

    /**
     * Homologa todos los catalogos correspondientes a las personas recibidas
     * 
     * @param procesaComparendoPersonas
     * @param comparendo
     * @param respuestaWSDTO
     * @param logRecibirComparendo
     */
    private void homologarPersonas(List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas,
            ComparendoWSDTO comparendo, RespuestaRecibirComparendoWSDTO respuestaWSDTO,
            LogRecibirComparendo logRecibirComparendo) {
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoPersonas) {
            EnumTipoPersonaComparendo tipoPersonaComp = Utilidades.buscarElemEnum(EnumTipoPersonaComparendo.class,
                    procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo());
            PersonaWSDTO personaWSDTO = null;
            switch (tipoPersonaComp) {
            case EMPRESA_TRANSPORTE:
                personaWSDTO = comparendo.getEmpresa();
                break;
            case INFRACTOR:
                personaWSDTO = comparendo.getInfractor();
                break;
            case PROPIETARIO:
                personaWSDTO = comparendo.getPropietario();
                break;
            case TESTIGO:
                personaWSDTO = comparendo.getTestigo();
            default:
                break;
            }
            if (personaWSDTO != null) {
                homologarCatalogosPersonaWS(respuestaWSDTO, logRecibirComparendo, procesaComparendoPersonaDTO,
                        personaWSDTO);
            }
        }

    }

    /**
     * Homologa los catalogos correspondentes a las personas
     * 
     * @param respuestaWSDTO
     *            respuesta a la cual se ingresa el problema de homologacion existente.
     * @param logRecibirComparendo
     *            log de recibir comparendo a ser escrito en caso de falla
     * @param procesaComparendoPersonaDTO
     *            persona procesada a ingresar homologacion
     * @param personaWSDTO
     *            persona con el valor a homologar del catalogo
     */
    private void homologarCatalogosPersonaWS(RespuestaRecibirComparendoWSDTO respuestaWSDTO,
            LogRecibirComparendo logRecibirComparendo, ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO,
            PersonaWSDTO personaWSDTO) {
        if (personaWSDTO != null) {
            // HOMOLOGA TIPO DE IDENTIFICACION DE LA PERSONA
            if (StringUtils.isNotBlank(personaWSDTO.getCodigoTipoIdentificacion())) {
                Integer id = consultarIdCatalogo(TipoIdentificacionPersona.class,
                        personaWSDTO.getCodigoTipoIdentificacion());
                if (id != null) {
                    procesaComparendoPersonaDTO.setIdTipoIdentificacion(id);
                } else {
                    procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                            EnumCatalogosHomologacion.codigoTipoIdentificacion,
                            personaWSDTO.getCodigoTipoIdentificacion());
                }
            }

            // Homologar Direccion de la persona:
            if (personaWSDTO.getDireccion() != null) {
                homologaCatalogosDireccion(procesaComparendoPersonaDTO.getProcesaDireccion(),
                        personaWSDTO.getDireccion(), respuestaWSDTO, logRecibirComparendo);
            }

            // HOMOLOGAR CATEGORIA DE LICENCIA
            if (StringUtils.isNotBlank(personaWSDTO.getCodigoCategoriaLicenciaCondu())) {
                Integer id = consultarIdCatalogo(TipoCategLicenciaConduccion.class,
                        personaWSDTO.getCodigoCategoriaLicenciaCondu());
                if (id != null) {
                    procesaComparendoPersonaDTO.setIdCategoriaLicenciaCondu(id);
                } else {
                    procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                            EnumCatalogosHomologacion.codigoCategoriaLicenciaCondu,
                            personaWSDTO.getCodigoCategoriaLicenciaCondu());
                }
            }
        }

    }

    /**
     * Construye error de homologacion correspondiente al catalogo o campo
     * 
     * @param respuestaWSDTO
     *            respuesta al que se agrega error
     * @param logRecibirComparendo
     *            log con los datos a ser escritos sobre el servidor
     * @param campo
     *            campo de catalogo de homologacion {@link EnumCatalogosHomologacion}
     * @param valor
     *            valor entrante del campo
     * @author luis.forero (2015-12-04)
     */
    private void procesarErrorCatalogo(RespuestaRecibirComparendoWSDTO respuestaWSDTO,
            LogRecibirComparendo logRecibirComparendo, EnumCatalogosHomologacion campo, Object valor) {
        RespuestaWebServiceDTO errorCompRechazado = consultarErrorProcesamiento(
                EnumProcesamientoWS.ERROR_COMPARENDO_RECHAZADO.getCodigo());
        respuestaWSDTO.setCodigoGeneral(errorCompRechazado.getCodigo());
        DetalleRespuestaWSDTO errMapeoInexistente = construirDetalleRespuestaWSDTO(
                EnumRespuestaWebServices.VALOR_NO_MAPEADO);
        errMapeoInexistente.setDescripcion(errMapeoInexistente.getDescripcion() + ERR_CAMPO + campo);
        respuestaWSDTO.getDetalle().add(errMapeoInexistente);
        logRecibirComparendo.setEstadoTransaccion(errMapeoInexistente.getCodigo() + SEPARADOR
                + errMapeoInexistente.getDescripcion() + IGUAL + String.valueOf(valor));
        logger.escribir(seguridadCirculemosEJB.obtenerUsuarioDto().getLogin(), logRecibirComparendo);
    }

    /**
     * Homologa de la direcciones del comparendo los catalogos respectivos
     * 
     * @param procesaDireccionComparendo
     *            direccion procesada a la cual se homologan los catalogos
     * @param direccion
     *            direccion en la cual vienen los datos de catalogos a homologar
     * @param respuestaWSDTO
     *            respuesta a la cual se agrega una validacion si no existe el item en el catalogo
     * @param logRecibirComparendo
     *            para registro de posibles errores
     * @author luis.forero(2015-12-10)
     */
    private void homologaCatalogosDireccion(ProcesaDireccionDTO procesaDireccionComparendo, DireccionWSDTO direccion,
            RespuestaRecibirComparendoWSDTO respuestaWSDTO, LogRecibirComparendo logRecibirComparendo) {
        if (direccion != null && procesaDireccionComparendo != null) {
            // HOMOLOGACION CODIGO PAIS DE LA DIRECCION
            if (StringUtils.isNotBlank(direccion.getCodigoPais())) {
                PaisDTO paisDTO = new PaisDTO();
                paisDTO.setCodigo(direccion.getCodigoPais());
                List<PaisDTO> consultarPais = administracionEJB.consultarPais(paisDTO);
                Integer idCatalogo = consultarPais.isEmpty() ? null : consultarPais.get(0).getId();
                if (null != idCatalogo) {
                    procesaDireccionComparendo.setIdPais(idCatalogo);
                } else {
                    procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo, EnumCatalogosHomologacion.codigoPais,
                            direccion.getCodigoPais());
                }
            }

            // HOMOLOGACION CODIGO DE DEPARTAMENTO
            if (StringUtils.isNotBlank(direccion.getCodigoDepartamento())) {
                DepartamentoDTO departamentoDTO = new DepartamentoDTO();
                departamentoDTO.setCodigo(direccion.getCodigoDepartamento());
                List<DepartamentoDTO> consultarDepartamento = administracionEJB.consultarDepartamento(departamentoDTO);
                Integer idCatalogo = consultarDepartamento.isEmpty() ? null : consultarDepartamento.get(0).getId();
                if (null != idCatalogo) {
                    procesaDireccionComparendo.setIdDepartamento(idCatalogo);
                } else {
                    procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                            EnumCatalogosHomologacion.codigoDepartamento, direccion.getCodigoDepartamento());
                }
            }

            // HOMOLOGACION CODIGO DE MUNCIPIO
            if (StringUtils.isNotBlank(direccion.getCodigoMunicipio())) {
                MunicipioDTO municipioDTO = new MunicipioDTO();
                municipioDTO.setCodigo(direccion.getCodigoMunicipio());
                List<MunicipioDTO> consultarMunicipio = administracionEJB.consultarMunicipio(municipioDTO);
                Integer id = consultarMunicipio.isEmpty() ? null : consultarMunicipio.get(0).getId();
                if (null != id) {
                    procesaDireccionComparendo.setIdMunicipio(id);
                } else {
                    procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                            EnumCatalogosHomologacion.codigoMunicipio, direccion.getCodigoMunicipio());
                }
            }

            // HOMOLOGACION CODIGO DE LOCALIDAD <b>No se hacen por falta de implementacion de consultas</b>
            if (StringUtils.isNotBlank(direccion.getCodigoLocalidad())) {
                LocalidadDTO localidadDTO = new LocalidadDTO();
                localidadDTO.setCodigo(direccion.getCodigoLocalidad());
                List<LocalidadDTO> consultarLocalidad = administracionEJB.consultarLocalidad(localidadDTO);
                Integer id = consultarLocalidad.isEmpty() ? null : consultarLocalidad.get(0).getId();
                if (null != id) {
                    procesaDireccionComparendo.setIdLocalidad(id);
                } else {
                    procesarErrorCatalogo(respuestaWSDTO, logRecibirComparendo,
                            EnumCatalogosHomologacion.codigoLocalidad, direccion.getCodigoLocalidad());
                }
            }
        }

    }

    /**
     * Consulta el id de un determinado item de catalogos desde modulos externos.
     * 
     * @param clazz
     *            Clase de la entidad del catalogo a consultar
     * @param codigo
     *            el codigo del item del catalogo a consultar
     * @return id o identificador del catalogo ingresado.
     * @author luis.forero(2015-12-04)
     */
    private <T extends EntidadC2> Integer consultarIdCatalogo(Class<T> clazz, String codigo) {
        ItemCatalogoDTO item = new ItemCatalogoDTO();
        item.setCodigo(codigo);
        List<ItemCatalogoDTO> result = fachadaConfiguracionEJB.consultarItemsCatalogo(clazz.getName(), item);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0).getId();
    }

    /**
     * Consulta un item de un determinado catalogo por su codigo.<br>
     * <b> Debe cumplir con el estandar:<br>
     * Name of Query = < T >.findByCodigo<br>
     * Query = SELECT T FROM < T > T where T.codigo= :pCod</b>
     * 
     * @param catalogo
     *            referencia de la entidad a consultar
     * @param codigoCatalogo
     *            codigo del catalogo
     * @param log
     *            log de escritura de los sucesos ocurridos
     * @return item de la entidad o catalogo encontrado, NULL en caso contrario
     * @author luis.forero(2015-12-03)
     */
    private <T extends EntidadCatalogoC2> Integer consultarItemCatalogo(Class<T> catalogo, String codigoCatalogo) {
        Integer id = null;
        try {
            String jpqlTxt = "SELECT T FROM {0} AS T where T.codigo = :pCod";
            String format = MessageFormat.format(jpqlTxt, catalogo.getSimpleName());
            TypedQuery<T> query = em.createQuery(format, catalogo);
            query.setParameter(NOMBRE_PARAMETRO_CODIGO, codigoCatalogo);

            List<T> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                id = resultList.get(0).getId();
            }
        } catch (IllegalArgumentException e) {
            loggerServer.error(e.getMessage());
            throw e;
        }
        return id;
    }

    /**
     * Consulta un error de procesamiento por su codigo
     * 
     * @param errorProcesamiento
     *            error o respuesta que se retorna en el webservice
     * @return error de procesamiento correspondiente a la enumeracion seleccionada
     * @author luis.forero(2015-11-17)<br>
     *         luis.forero(mod 2016-04-25)
     */
    private RespuestaWebServiceDTO consultarErrorProcesamiento(EnumRespuestaWebServices errorProcesamiento) {
        if (lstRespuestasWSRecibir == null) {
            lstRespuestasWSRecibir = fachadaAdminGeneralEJB
                    .consultarRespuestasWebService(errorProcesamiento.getEnumWebService().getValue());
        }
        for (RespuestaWebServiceDTO rstWebService : lstRespuestasWSRecibir) {
            if (rstWebService.getId().equals(errorProcesamiento.getValue())) {
                return rstWebService;
            }
        }
        return null;
    }
}
