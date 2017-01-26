package co.com.datatools.c2.negocio.ejb;

import java.text.MessageFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.dto.LoggerComparendoTercero;
import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudComparendosDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
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
import co.com.datatools.c2.entidades.TipoOrigenComparendo;
import co.com.datatools.c2.entidades.TipoServicio;
import co.com.datatools.c2.entidades.TipoTransportePasajero;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.helpers.enumeracion.EnumTipoEntidades;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoMultaHelper;
import co.com.datatools.c2.negocio.interfaces.ILComparendoTercero;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRComparendoTercero;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.negocio.local.interfaces.IProcesarRecibirComparendo;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.dto.EntidadC2;

/**
 * Implementacion de logica de la interface IComparendoTercero que permite definir el comportamiento particular
 * 
 * @author Luis Forero(2016-06-06)
 * 
 */
@Stateless(name = "ComparendoTerceroEJB")
@LocalBean
public class ComparendoTerceroEJB implements IRComparendoTercero, ILComparendoTercero {

    /**
     * logger del servidor jboss
     */
    private Logger logger = Logger.getLogger(ComparendoTerceroEJB.class.getName());

    /**
     * 
     */
    private static final String NOMBRE_PARAMETRO_CODIGO = "pCod";

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaIntegracionTerceros fachadaIntegracionTercerosEJB;
    @EJB
    private IRFachadaConfiguracion fachadaConfiguracionEJB;
    @EJB
    private IRSeguridadCirculemos seguridadCirculemosEJB;
    @EJB
    private IProcesarRecibirComparendo procesarRecibirComparendoEJB;
    @EJB
    private IRAdministracion administracionEJB;
    @EJB
    private IRRecibirComparendo recibirComparendoEJB;

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaSolicitudComparendosDTO solicitarComparendosTerceros() {
        logger.debug(
                ComparendoTerceroEJB.class.getName().concat("actualizarEstadoComparendo(CambioEstadoComparendoDTO)"));
        RespuestaSolicitudComparendosDTO respuestaSolicitudComparendosDTO = new RespuestaSolicitudComparendosDTO();

        Integer codigoOrganismo = seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo();
        List<ItMultaDTO> multas = fachadaIntegracionTercerosEJB.consultarMultas(codigoOrganismo,
                EnumEstadoLectura.NUEVO);

        int totalLeidos = 0;
        int totalRegistrados = 0;
        int totalNoRegistrados = 0;
        String login = seguridadCirculemosEJB.obtenerUsuarioDto().getLogin();
        RespuestaValidacionDTO respuestaWSDTO = new RespuestaValidacionDTO();
        // Se construye error para casos inesperados
        procesarErrorCatalogo(respuestaWSDTO, EnumCamposDetalleComparendo.NO_ES_CAMPO_PCC,
                EnumErrorProcesamiento.NO_SE_PUDO_REALIZA_LA_TRANSACCION);
        ILog log = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_COMPARENDOS_TERCEROS);
        for (ItMultaDTO itMultaDTO : multas) {
            ProcesarComparendoDTO procesarComparendoDTO = ComparendoMultaHelper.ToProcesaComparendoDto
                    .convertComparendoM(itMultaDTO);

            if (homologarCatalogos(procesarComparendoDTO, itMultaDTO)) {
                try {
                    RespuestaValidacionDTO recibirComparendo = recibirComparendoEJB
                            .recibirComparendo(procesarComparendoDTO);
                    if (recibirComparendo.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getCodigo())) {
                        fachadaIntegracionTercerosEJB.actualizarEstadoMulta(itMultaDTO.getIdMulta(),
                                EnumEstadoLectura.RECIBIDO);
                        totalRegistrados++;
                    } else {
                        fachadaIntegracionTercerosEJB.actualizarEstadoMulta(itMultaDTO.getIdMulta(),
                                EnumEstadoLectura.NO_RECIBIDO);
                        totalNoRegistrados++;
                    }
                } catch (CirculemosNegocioException e) {
                    fachadaIntegracionTercerosEJB.actualizarEstadoMulta(itMultaDTO.getIdMulta(),
                            EnumEstadoLectura.NO_RECIBIDO);
                    procesarComparendoDTO.getProcesaComparendoDTO().setNumeroComparendo(
                            String.valueOf(procesarComparendoDTO.getProcesaComparendoDTO().getIdFacturaAxis()));
                    procesarRecibirComparendoEJB.registroDetalleBloqueo(respuestaWSDTO,
                            procesarComparendoDTO.getProcesaComparendoDTO());
                    LoggerComparendoTercero datos = new LoggerComparendoTercero();
                    datos.setCodigoError(e.getErrorInfo().getCodigoError());
                    datos.setMensaje(e.getErrorInfo().getDescError());
                    datos.setNumeroComparendo(
                            String.valueOf(procesarComparendoDTO.getProcesaComparendoDTO().getIdFacturaAxis()));
                    log.escribir(login, datos);
                    totalNoRegistrados++;
                } catch (CirculemosIllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else {
                fachadaIntegracionTercerosEJB.actualizarEstadoMulta(itMultaDTO.getIdMulta(),
                        EnumEstadoLectura.NO_RECIBIDO);
                totalNoRegistrados++;
            }
            totalLeidos++;
        }
        respuestaSolicitudComparendosDTO.setTotalComparendosLeidos(totalLeidos);
        respuestaSolicitudComparendosDTO.setTotalComparendosNoRecibidos(totalNoRegistrados);
        respuestaSolicitudComparendosDTO.setTotalComparendosRecibidos(totalRegistrados);

        return respuestaSolicitudComparendosDTO;
    }

    /**
     * Homologa y asigna el valor correspondiente de cada uno de los catalogos entrantes
     * 
     * @param procesarComparendoDTO
     * @param comparendo
     *            ItMulta con los datos de entrada a homologar
     * @return true si fue exitosa la homologacion, false de lo contrario dejando registrados los rechazos
     * @author luis.forero(2016-05-06)
     */
    private boolean homologarCatalogos(ProcesarComparendoDTO procesarComparendoDTO, ItMultaDTO comparendo) {
        ProcesaComparendoDTO procesaComparendoDTO = procesarComparendoDTO.getProcesaComparendoDTO();
        final RespuestaValidacionDTO respuestaValidacionDTO = new RespuestaValidacionDTO();
        final EnumErrorProcesamiento errorMapeo = EnumErrorProcesamiento.VALOR_NO_MAPEADO;
        // **************************************
        // Homologacion de catalogos del encabezado
        // **************************************
        // TIPO COMPARENDO-> MedioImposicionComparendo
        if (StringUtils.isNotBlank(comparendo.getCodigoMedioImposicion())) {
            Integer id = consultarItemCatalogo(MedioImposicionComparendo.class, comparendo.getCodigoMedioImposicion());
            if (id != null) {
                procesaComparendoDTO.setCodigoMedioImposicion(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.MEDIO_IMPOSICION, errorMapeo);
            }
        }

        // CODIGO ORIGEN COMPARENDO
        if (StringUtils.isNotBlank(comparendo.getCodigoOrigenComparendo())) {
            Integer id = consultarItemCatalogo(TipoOrigenComparendo.class, comparendo.getCodigoOrigenComparendo());
            if (id != null) {
                procesaComparendoDTO.setCodigoOrigen(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO,
                        EnumCamposDetalleComparendo.CODIGO_DE_ORIGEN_DEL_COMPARENDO, errorMapeo);
            }
        }

        // TIPO AGENTE IMPOSITOR
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoAgenteImpositor())) {
            Integer id = consultarItemCatalogo(TipoAgenteImpositor.class, comparendo.getCodigoTipoAgenteImpositor());
            if (id != null) {
                procesaComparendoDTO.setIdTipoAgenteImpositor(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.TIPO_AGENTE_IMPOSITOR,
                        errorMapeo);
            }
        }

        // ESTADO COMPARENDO
        if (StringUtils.isNotBlank(comparendo.getCodigoEstadoComparendo())) {
            Integer id = consultarItemCatalogo(EstadoComparendo.class, comparendo.getCodigoEstadoComparendo());
            if (id != null) {
                procesaComparendoDTO.setIdEstadoComparendo(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.ESTADO_COMPARENDO,
                        errorMapeo);
            }
        }

        // TIPO NOTIFICACION COMPARENDO
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoNotifCompa())) {
            Integer id = consultarItemCatalogo(TipoNotificacionComparendo.class, comparendo.getCodigoTipoNotifCompa());
            if (id != null) {
                procesaComparendoDTO.setIdTipoNotificacionComparendo(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.TIPO_NOTIFICACION,
                        errorMapeo);
            }
        }

        // Homologa datos de catalogos de la direccion para la infraccion
        homologaCatalogosDireccion(procesaComparendoDTO.getProcesaDireccionComparendo(), comparendo,
                respuestaValidacionDTO, EnumTipoEntidades.INFRACCION);

        // **************************************
        // Homologacion de catalogos del vehiculo
        // **************************************
        // TIPO DE SERVICIO
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoServicio())) {
            Integer idTipoServicio = consultarIdCatalogo(TipoServicio.class, comparendo.getCodigoTipoServicio());
            if (idTipoServicio != null) {
                procesaComparendoDTO.setIdTipoServicio(idTipoServicio);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.TIPO_SERVICIO, errorMapeo);
            }
        }

        // CLASE DE VEHICULO o tipo de vehiculo
        if (StringUtils.isNotBlank(comparendo.getCodigoClaseVehiculo())) {
            Integer id = consultarIdCatalogo(ClaseVehiculo.class, comparendo.getCodigoClaseVehiculo());
            if (id != null) {
                procesaComparendoDTO.setIdClaseVehiculo(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.CLASE_VEHICULO, errorMapeo);
            }
        }

        // RADIO DE ACCION
        if (StringUtils.isNotBlank(comparendo.getCodigoRadioAccion())) {
            Integer id = consultarIdCatalogo(RadioAccion.class, comparendo.getCodigoRadioAccion());
            if (id != null) {
                procesaComparendoDTO.setIdRadioAccion(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.RADIO_ACCION, errorMapeo);
            }
        }

        // MODALIDAD TRANSPORTE
        if (StringUtils.isNotBlank(comparendo.getCodigoModalidad())) {
            Integer id = consultarIdCatalogo(Modalidad.class, comparendo.getCodigoModalidad());
            if (id != null) {
                procesaComparendoDTO.setIdModalidad(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.MODALIDAD, errorMapeo);
            }
        }

        // TIPO TRANSPORTE DE PASAJEROS
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoTransPasajero())) {
            Integer id = consultarIdCatalogo(TipoTransportePasajero.class, comparendo.getCodigoTipoTransPasajero());
            if (id != null) {
                procesaComparendoDTO.setIdTipoServicio(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.TIPO_TRANSPORTE_PASAJERO,
                        errorMapeo);
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
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.TIPO_INFRACTOR, errorMapeo);
            }
        }
        // **************************************
        // AGENTE
        // **************************************
        // CODIGO TIPO DE IDENTIFICACION DEL AGENTE
        if (StringUtils.isNotBlank(comparendo.getCodigoTipoIdentAgente())) {
            Integer id = consultarItemCatalogo(TipoIdentificacionPersona.class, comparendo.getCodigoTipoIdentAgente());
            if (id != null) {
                procesaComparendoDTO.setIdTipoIdentificacionAgente(id);
            } else {
                procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.TIPO_IDENTIFICACION_AGENTE,
                        errorMapeo);
            }
        }

        // **************************************
        // INMOVILIZACION
        // **************************************
        // VALIDACION DIRECCION PATIO
        homologaCatalogosDireccion(procesaComparendoDTO.getProcesaDireccionPatio(), comparendo, respuestaValidacionDTO,
                EnumTipoEntidades.PATIO);

        // **************************************
        // HOMOLOGACION DE PERSONAS RECIBIDAS
        // **************************************
        homologarPersonas(procesaComparendoDTO.getProcesaComparendoPersonas(), comparendo, respuestaValidacionDTO);
        // **************************************
        // HOMOLOGACION DE EVIDENCIAS
        // **************************************
        homologarEvidencias(procesaComparendoDTO.getProcesaEvidencias(), comparendo, respuestaValidacionDTO);

        if (respuestaValidacionDTO.getCodigoResultado() != null
                && respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getCodigo())) {
            procesaComparendoDTO.setNumeroComparendo(String.valueOf(comparendo.getIdFacturaAxis()));
            procesarRecibirComparendoEJB.registroDetalleBloqueo(respuestaValidacionDTO, procesaComparendoDTO);
            return false;
        }

        return true;
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
            logger.error(e.getMessage());
            throw e;
        }
        return id;
    }

    /**
     * Homologa los catalogos de las evidencias recibidas al objeto destino
     * 
     * @param procesaEvidencias
     * @param comparendo
     * @param respuestaValidacion
     * @author luis.forero(2016-05-06)
     */
    private void homologarEvidencias(List<ProcesaEvidenciaDTO> procesaEvidencias, ItMultaDTO comparendo,
            RespuestaValidacionDTO respuestaValidacion) {
        EnumErrorProcesamiento errorMapeo = EnumErrorProcesamiento.VALOR_NO_MAPEADO;
        // para cada uno se homologan sus catalogos
        for (ProcesaEvidenciaDTO procesaEvidenciaDTO : procesaEvidencias) {

            if ((StringUtils.isNotBlank(comparendo.getEvidencia1Url())
                    && comparendo.getEvidencia1Nombre().equals(procesaEvidenciaDTO.getNombreEvidencia()))) {
                // HOMOLOGA SU TIPO DE EVIDENCIA
                if (StringUtils.isNotBlank(comparendo.getEvidencia1CodigoTipo())) {
                    Integer idTipoEvidencia = consultarItemCatalogo(TipoEvidencia.class,
                            comparendo.getEvidencia1CodigoTipo());
                    if (idTipoEvidencia == null) {
                        procesarErrorCatalogo(respuestaValidacion, EnumCamposDetalleComparendo.TIPO_EVIDENCIA,
                                errorMapeo);
                        return;
                    }
                    procesaEvidenciaDTO.setCodigoTipoEvidencia(idTipoEvidencia);
                }
            }

            if ((StringUtils.isNotBlank(comparendo.getEvidencia2Url())
                    && comparendo.getEvidencia1Nombre().equals(procesaEvidenciaDTO.getNombreEvidencia()))) {
                // HOMOLOGA SU TIPO DE EVIDENCIA
                if (StringUtils.isNotBlank(comparendo.getEvidencia2CodigoTipo())) {
                    Integer idTipoEvidencia = consultarItemCatalogo(TipoEvidencia.class,
                            comparendo.getEvidencia1CodigoTipo());
                    if (idTipoEvidencia == null) {
                        procesarErrorCatalogo(respuestaValidacion, EnumCamposDetalleComparendo.TIPO_EVIDENCIA,
                                errorMapeo);
                        return;
                    }
                    procesaEvidenciaDTO.setCodigoTipoEvidencia(idTipoEvidencia);
                }
            }

        }

    }

    /**
     * Homologa todos los catalogos correspondientes a las personas recibidas
     * 
     * @param procesaComparendoPersonas
     * @param comparendo
     * @param respuestaValidacion
     */
    private void homologarPersonas(List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas, ItMultaDTO comparendo,
            RespuestaValidacionDTO respuestaValidacion) {
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoPersonas) {
            EnumTipoPersonaComparendo tipoPersonaComp = Utilidades.buscarElemEnum(EnumTipoPersonaComparendo.class,
                    procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo());
            EnumTipoEntidades tipoEntidad = null;
            switch (tipoPersonaComp) {
            case EMPRESA_TRANSPORTE:
                tipoEntidad = EnumTipoEntidades.EMPRESA;
                break;
            case INFRACTOR:
                tipoEntidad = EnumTipoEntidades.INFRACTOR;
                break;
            case PROPIETARIO:
                tipoEntidad = EnumTipoEntidades.PROPIETARIO;
                break;
            case TESTIGO:
                tipoEntidad = EnumTipoEntidades.TESTIGO;
                break;
            default:
                break;
            }
            if (tipoEntidad != null) {
                homologarCatalogosPersonaWS(respuestaValidacion, procesaComparendoPersonaDTO, comparendo, tipoEntidad);
            }
        }

    }

    /**
     * Homologa los catalogos correspondentes a las personas
     * 
     * @param respuestaValidacion
     * @param procesaComparendoPersonaDTO
     * @param personaM
     * @param tipoEntidad
     */
    private void homologarCatalogosPersonaWS(RespuestaValidacionDTO respuestaValidacion,
            ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO, ItMultaDTO personaM,
            EnumTipoEntidades tipoEntidad) {
        String codigoTipoIdentificacion = null;
        String codigoCategoriaLicenciaCondu = null;
        String direccionBasica = null;
        EnumCamposDetalleComparendo camposDetalleComparendo = null;
        switch (tipoEntidad) {
        case INFRACTOR:
            codigoTipoIdentificacion = personaM.getCodigoTipoIdentInfractor();
            camposDetalleComparendo = EnumCamposDetalleComparendo.TIPO_DOCUMENTO_DEL_INFRACTOR;
            codigoCategoriaLicenciaCondu = personaM.getCodigoCategLicenConduInfr();
            direccionBasica = personaM.getDireccionBasicaInfractor();
            break;
        case PROPIETARIO:
            codigoTipoIdentificacion = personaM.getCodigoTipoIdentPropietario();
            camposDetalleComparendo = EnumCamposDetalleComparendo.TIPO_DOCUMENTO_DEL_PROPIETARIO;
            break;
        case TESTIGO:
            codigoTipoIdentificacion = personaM.getCodigoTipoIdentTestigo();
            camposDetalleComparendo = EnumCamposDetalleComparendo.TIPO_DE_DOCUMENTO_DEL_TESTIGO;
            direccionBasica = personaM.getDireccionBasicaTestigo();
            break;
        case EMPRESA:
            codigoTipoIdentificacion = personaM.getCodigoTipoIdentEmpresa();
            camposDetalleComparendo = EnumCamposDetalleComparendo.TIPO_DOCUMENTO_EMPRESA;
            break;
        default:
            break;
        }
        EnumErrorProcesamiento errorMapeo = EnumErrorProcesamiento.VALOR_NO_MAPEADO;
        if (personaM != null) {
            // HOMOLOGA TIPO DE IDENTIFICACION DE LA PERSONA
            if (StringUtils.isNotBlank(codigoTipoIdentificacion)) {
                Integer id = consultarIdCatalogo(TipoIdentificacionPersona.class, codigoTipoIdentificacion);
                if (id != null) {
                    procesaComparendoPersonaDTO.setIdTipoIdentificacion(id);
                } else {
                    procesarErrorCatalogo(respuestaValidacion, camposDetalleComparendo, errorMapeo);
                }
            }

            // Homologar Direccion de la persona:
            if (direccionBasica != null) {
                homologaCatalogosDireccion(procesaComparendoPersonaDTO.getProcesaDireccion(), personaM,
                        respuestaValidacion, tipoEntidad);
            }

            // HOMOLOGAR CATEGORIA DE LICENCIA
            if (StringUtils.isNotBlank(codigoCategoriaLicenciaCondu)) {
                Integer id = consultarIdCatalogo(TipoCategLicenciaConduccion.class, codigoCategoriaLicenciaCondu);
                if (id != null) {
                    procesaComparendoPersonaDTO.setIdCategoriaLicenciaCondu(id);
                } else {
                    procesarErrorCatalogo(respuestaValidacion,
                            EnumCamposDetalleComparendo.CATEGORIA_DE_LA_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR,
                            errorMapeo);
                }
            }
        }

    }

    /**
     * Homologa de la direcciones del comparendo los catalogos respectivos
     * 
     * @param procesaDireccionComparendo
     * @param direccionM
     * @param respuestaValidacionDTO
     * @author luis.forero(2016-05-06)
     */
    private void homologaCatalogosDireccion(ProcesaDireccionDTO procesaDireccionComparendo, ItMultaDTO direccionM,
            RespuestaValidacionDTO respuestaValidacionDTO, EnumTipoEntidades tipoEntidad) {
        String direccionBasica = null;
        String codigoPais = null;
        String codigoDepartamento = null;
        String codigoMunicipio = null;
        String codigoLocalidad = null;
        switch (tipoEntidad) {
        case INFRACCION:
            direccionBasica = direccionM.getDireccionBasicaInfraccion();
            codigoPais = direccionM.getCodigoPaisInfraccion();
            codigoDepartamento = direccionM.getCodigoDepartamentoInfraccion();
            codigoMunicipio = direccionM.getCodigoMunicipioInfraccion();
            codigoLocalidad = direccionM.getCodigoLocalidadInfraccion();
            break;
        case PATIO:
            direccionBasica = direccionM.getDireccionBasicaPatio();
            codigoPais = direccionM.getCodigoPaisDirecPatio();
            codigoDepartamento = direccionM.getCodigoDepartamentoPatio();
            codigoMunicipio = direccionM.getCodigoMunicipioPatio();
            codigoLocalidad = direccionM.getCodigoLocalidadPatio();
        case INFRACTOR:
            direccionBasica = direccionM.getDireccionBasicaInfractor();
            codigoPais = direccionM.getCodigoPaisDirecInfractor();
            codigoDepartamento = direccionM.getCodigoDepartamentoInfractor();
            codigoMunicipio = direccionM.getCodigoMunicipioInfractor();
            codigoLocalidad = direccionM.getCodigoLocalidadInfractor();
        case TESTIGO:
            direccionBasica = direccionM.getDireccionBasicaTestigo();
            codigoPais = direccionM.getCodigoPaisDirecTestigo();
            codigoDepartamento = direccionM.getCodigoDepartamentoTestigo();
            codigoMunicipio = direccionM.getCodigoMunicipioTestigo();
            codigoLocalidad = direccionM.getCodigoLocalidadTestigo();
        default:
            break;
        }
        direccionM.getDireccionBasicaInfraccion();
        EnumErrorProcesamiento errorMapeo = EnumErrorProcesamiento.VALOR_NO_MAPEADO;
        if (StringUtils.isNotBlank(direccionBasica) && procesaDireccionComparendo != null) {
            // HOMOLOGACION CODIGO PAIS DE LA DIRECCION
            if (StringUtils.isNotBlank(codigoPais)) {
                PaisDTO paisDTO = new PaisDTO();
                paisDTO.setCodigo(codigoPais);
                List<PaisDTO> consultarPais = administracionEJB.consultarPais(paisDTO);
                Integer idCatalogo = consultarPais.isEmpty() ? null : consultarPais.get(0).getId();
                if (null != idCatalogo) {
                    procesaDireccionComparendo.setIdPais(idCatalogo);
                } else {

                    procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.PAIS_DIRECCION,
                            errorMapeo);
                }
            }

            // HOMOLOGACION CODIGO DE DEPARTAMENTO
            if (StringUtils.isNotBlank(codigoDepartamento)) {
                DepartamentoDTO departamentoDTO = new DepartamentoDTO();
                departamentoDTO.setCodigo(codigoDepartamento);
                List<DepartamentoDTO> consultarDepartamento = administracionEJB.consultarDepartamento(departamentoDTO);
                Integer idCatalogo = consultarDepartamento.isEmpty() ? null : consultarDepartamento.get(0).getId();
                if (null != idCatalogo) {
                    procesaDireccionComparendo.setIdDepartamento(idCatalogo);
                } else {
                    procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.DEPARTAMENTO_DIRECCION,
                            errorMapeo);
                }
            }

            // HOMOLOGACION CODIGO DE MUNCIPIO
            if (StringUtils.isNotBlank(codigoMunicipio)) {
                MunicipioDTO municipioDTO = new MunicipioDTO();
                municipioDTO.setCodigo(codigoMunicipio);
                List<MunicipioDTO> consultarMunicipio = administracionEJB.consultarMunicipio(municipioDTO);
                Integer id = consultarMunicipio.isEmpty() ? null : consultarMunicipio.get(0).getId();
                if (null != id) {
                    procesaDireccionComparendo.setIdMunicipio(id);
                } else {
                    procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.MUNICIPIO_DIRECCION,
                            errorMapeo);
                }
            }

            // HOMOLOGACION CODIGO DE LOCALIDAD <b>No se hacen por falta de implementacion de consultas</b>
            if (StringUtils.isNotBlank(codigoLocalidad)) {
                LocalidadDTO localidadDTO = new LocalidadDTO();
                localidadDTO.setCodigo(codigoLocalidad);
                List<LocalidadDTO> consultarLocalidad = administracionEJB.consultarLocalidad(localidadDTO);
                Integer id = consultarLocalidad.isEmpty() ? null : consultarLocalidad.get(0).getId();
                if (null != id) {
                    procesaDireccionComparendo.setIdLocalidad(id);
                } else {
                    procesarErrorCatalogo(respuestaValidacionDTO, EnumCamposDetalleComparendo.LOCALIDAD_DIRECCION,
                            errorMapeo);
                }
            }
        }

    }

    /**
     * Construye error de homologacion correspondiente al catalogo del campo
     * 
     * @param respuestaWSDTO
     * @param campo
     */
    private void procesarErrorCatalogo(RespuestaValidacionDTO respuestaWSDTO, EnumCamposDetalleComparendo campo,
            EnumErrorProcesamiento errorProcesamiento) {
        procesarRecibirComparendoEJB.errorRechazoRespuestaValidacion(campo, errorProcesamiento, respuestaWSDTO);
    }

}