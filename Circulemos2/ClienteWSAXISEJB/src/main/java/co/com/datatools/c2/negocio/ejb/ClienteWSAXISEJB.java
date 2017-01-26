package co.com.datatools.c2.negocio.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.com.datatools.c2.axis.ws.cliente.error.ErrorWSAxis.ErrorWSAnularFinanciamiento;
import co.com.datatools.c2.axis.ws.cliente.error.ErrorWSAxis.ErrorWSCoactivo;
import co.com.datatools.c2.axis.ws.cliente.error.ErrorWSAxis.ErrorWSImpugnacion;
import co.com.datatools.c2.axis.ws.cliente.error.ErrorWSAxis.ErrorWSRegistrarFinanciamiento;
import co.com.datatools.c2.axis.ws.cliente.helpers.ClienteWSAXISUtil;
import co.com.datatools.c2.axis.ws.cliente.vo.RespuestaRestWSAxisVO;
import co.com.datatools.c2.axis.ws.cliente.vo.coactivo.CoactivoVO;
import co.com.datatools.c2.axis.ws.cliente.vo.financiacion.FinanciacionVO;
import co.com.datatools.c2.clientes.ws.rest.ServicioRest;
import co.com.datatools.c2.clientes.ws.rest.enumeration.EnumMetodoEnvio;
import co.com.datatools.c2.clientes.ws.rest.enumeration.EnumTipoRespuesta;
import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.enumeracion.EnumWebService;
import co.com.datatools.c2.enumeraciones.EnumOpcionImpugnacion;
import co.com.datatools.c2.enumeraciones.EnumTipoFallo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.interfaces.ILClienteWSAXIS;
import co.com.datatools.c2.negocio.interfaces.IRClienteWSAXIS;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;

/**
 * Implementacion de logica de metodos contra las conexiones de WS de AXIS
 * 
 * @author julio.pinzon 2016-08-12
 * 
 */
@Stateless(name = "ClienteWSAXISEJB")
@LocalBean
public class ClienteWSAXISEJB implements IRClienteWSAXIS, ILClienteWSAXIS {

    private final static Logger logger = Logger.getLogger(ClienteWSAXISEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneralEJB;

    @EJB
    private IRSeguridadCirculemos seguridadCirculemosEJB;

    @EJB
    private IRFachadaProceso fachadaProcesoEJB;

    @EJB
    private ILClienteWSAXIS clienteWSAxis;

    /**
     * Constantes de parametros de envio a ws
     */
    private static final String PARAMETRO_FINANCIAMIENTO = "financiamiento";
    private static final String PARAMETRO_CONVENIO = "idconvenio";
    private static final String PARAMETRO_USUARIO = "usuario";
    private static final String PARAMETRO_OBSERVACION = "observacion";
    private static final String PARAMETRO_OPCION = "opcion";
    private static final String PARAMETRO_FACTURA = "factura";
    private static final String PARAMETRO_MULTA = "multa";
    private static final String PARAMETRO_PUNTOS = "puntos";
    private static final String PARAMETRO_RUBRO = "rubro";
    private static final String PARAMETRO_EMPRESA = "empresa";
    private static final String PARAMETRO_IND_PORC = "ind_porc";
    private static final String PARAMETRO_PORCENTAJE = "porcentaje";
    private static final String PARAMETRO_FECHA_ACTA = "fecha_acta";
    private static final String PARAMETRO_DOCUMENTO_REFERENCIA = "documento_referencia";
    private static final String IND_PORC_S = "S";
    private static final String IND_PORC_N = "N";
    private static final String OBSERVACION_ANULACION_FINANCIACION = "Limite de pago primera cuota vencido";
    private static final String PARAMETRO_COACTIVO = "coactiva";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public FinanciacionDTO registrarFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug(
                ClienteWSAXISEJB.class.getName().concat("::notificarComparendoSIMIT(HomologacionComparendoSIMITDTO)"));
        // Se lleva a cabo la consulta de los datos del web service para llevar a cabo la conexion
        WebServiceDTO webService = fachadaAdminGeneralEJB
                .consultarWebService(EnumWebService.REGISTRAR_FINANCIAMIENTO.getValue());
        if (webService == null) {
            throw new CirculemosNegocioException(ErrorWSRegistrarFinanciamiento.REG_FIN_001);
        }

        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        log.setFechaEnvio(new Date());
        log.setRutaEnvio(webService.getUrlPrimaria());
        log.setObservacion(webService.getUrlPrimaria());

        try {
            // Objeto para realizar la conversion del objeto JSon guardado en la bd
            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
            Map<String, String> parametros = new HashMap<>();

            // Obtiene objeto de financiacion que será enviado como parametro
            FinanciacionVO financiacion = ClienteWSAXISUtil.homologarFinanciamiento(financiacionDTO);
            parametros.put(PARAMETRO_FINANCIAMIENTO, gson.toJson(financiacion));

            // Pone en el log los parametros enviados
            log.setMensajeEnviado(parametros.toString());

            // Obtiene instancia de la clase que hace la peticion del servicio rest
            ServicioRest registro = new ServicioRest(parametros, webService.getUrlPrimaria(), EnumTipoRespuesta.JSON,
                    EnumMetodoEnvio.POST);
            String respuesta = registro.enviar();

            // Pone en el log lo que recibio
            log.setMensajeRecibido(respuesta);

            RespuestaRestWSAxisVO respuestaAxis = gson.fromJson(respuesta, RespuestaRestWSAxisVO.class);

            // Obtiene el id de la respuesta dependiendo del codigo de respuesta
            RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                    .encontrarPorCodigo(respuestaAxis.getCodMensaje(), EnumWebService.REGISTRAR_FINANCIAMIENTO)
                    .getValue());
            // Coloca la respuesta del ws
            log.setRespuestaWebService(respuestaWebService);

            if (respuestaAxis.getCodMensaje().equals(EnumRespuestaWebServices.TRANSACCION_EXITOSA.getCodigo())
                    || respuestaAxis.getCodMensaje()
                            .equals(EnumRespuestaWebServices.DATO_REGISTRADO_ANTERIORMENTE.getCodigo())) {
                financiacionDTO.setNumeroFinanciacion(respuestaAxis.getData().getIdConvenio());
                financiacionDTO.setIdTramite(respuestaAxis.getData().getIdTramite());
            } else {
                logger.error("Mensaje de error al registrar financiacion en AXIS : " + respuestaAxis);
                throw new CirculemosNegocioException(ErrorWSRegistrarFinanciamiento.REG_FIN_003);
            }
        } catch (Exception e) {
            if (e instanceof CirculemosNegocioException) {
                throw (CirculemosNegocioException) e;
            } else {
                // Pone en el log lo que recibio
                log.setMensajeRecibido(e.getMessage());
                log.setRespuestaWebService(
                        new RespuestaWebServiceDTO(EnumRespuestaWebServices.ERROR_INESPERADO.getValue()));
                logger.error("Error al registrar financiacion en AXIS", e);
                throw new CirculemosNegocioException(ErrorWSRegistrarFinanciamiento.REG_FIN_002);
            }
        } finally {
            try {
                log.setFechaRecepcion(new Date());
                // Guardar log llamado WS
                clienteWSAxis.guardarLogWS(log);
            } catch (Exception e) {
                logger.error("Error al guardar el log de conexión a AXIS", e);
                throw new CirculemosNegocioException(ErrorWSRegistrarFinanciamiento.REG_FIN_004);
            }
        }

        return financiacionDTO;
    }

    @Override
    public void anularFinanciacion(String numeroFinanciacion) throws CirculemosNegocioException {
        logger.debug(ClienteWSAXISEJB.class.getName().concat("::anularFinanciacion(String)"));

        // Se lleva a cabo la consulta de los datos del web service para llevar a cabo la conexion
        WebServiceDTO webService = fachadaAdminGeneralEJB
                .consultarWebService(EnumWebService.ANULAR_FINANCIAMIENTO.getValue());
        if (webService == null) {
            throw new CirculemosNegocioException(ErrorWSAnularFinanciamiento.ANU_FIN_001);
        }

        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        log.setFechaEnvio(new Date());
        log.setRutaEnvio(webService.getUrlPrimaria());
        log.setObservacion(webService.getUrlPrimaria());

        try {
            // Objeto para realizar la conversion del objeto JSon guardado en la bd
            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
            Map<String, String> parametros = new HashMap<>();
            parametros.put(PARAMETRO_CONVENIO, numeroFinanciacion);
            parametros.put(PARAMETRO_USUARIO, ClienteWSAXISUtil.USUARIO);
            parametros.put(PARAMETRO_OBSERVACION, OBSERVACION_ANULACION_FINANCIACION);

            // Pone en el log los parametros enviados
            log.setMensajeEnviado(parametros.toString());

            // Obtiene instancia de la clase que hace la peticion del servicio rest
            ServicioRest anula = new ServicioRest(parametros, webService.getUrlPrimaria(), EnumTipoRespuesta.JSON,
                    EnumMetodoEnvio.POST);
            String respuesta = anula.enviar();
            // Pone en el log lo que recibio
            log.setMensajeRecibido(respuesta);

            RespuestaRestWSAxisVO respuestaAxis = gson.fromJson(respuesta, RespuestaRestWSAxisVO.class);

            // Obtiene el id de la respuesta dependiendo del codigo de respuesta
            RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                    .encontrarPorCodigo(respuestaAxis.getCodMensaje(), EnumWebService.ANULAR_FINANCIAMIENTO)
                    .getValue());
            // Coloca la respuesta del ws
            log.setRespuestaWebService(respuestaWebService);
            if (!respuestaAxis.getCodMensaje().equals(EnumRespuestaWebServices.TRANSACCION_EXITOSA_ANULAR.getCodigo())
                    && !respuestaAxis.getCodMensaje()
                            .equals(EnumRespuestaWebServices.DATO_REGISTRADO_ANTERIORMENTE_ANULAR.getCodigo())) {
                logger.error("Mensaje de error al anular financiacion en AXIS : " + respuestaAxis);
                throw new CirculemosNegocioException(ErrorWSAnularFinanciamiento.ANU_FIN_003);
            }
        } catch (Exception e) {
            if (e instanceof CirculemosNegocioException) {
                throw (CirculemosNegocioException) e;
            } else {
                // Pone en el log lo que recibio
                log.setMensajeRecibido(e.getMessage());
                log.setRespuestaWebService(
                        new RespuestaWebServiceDTO(EnumRespuestaWebServices.ERROR_INESPERADO_ANULAR.getValue()));
                logger.error("Error al registrar financiacion en AXIS", e);
                throw new CirculemosNegocioException(ErrorWSAnularFinanciamiento.ANU_FIN_002);
            }
        } finally {
            try {
                log.setFechaRecepcion(new Date());
                // Guardar log llamado WS
                clienteWSAxis.guardarLogWS(log);
            } catch (Exception e) {
                logger.error("Error al guardar el log de conexión a AXIS", e);
                throw new CirculemosNegocioException(ErrorWSAnularFinanciamiento.ANU_FIN_004);
            }
        }
    }

    @Override
    public void impugnarComparendo(ComparendoDTO comparendo, ProcesoDTO impugnacion) throws CirculemosNegocioException {
        logger.debug(ClienteWSAXISEJB.class.getName().concat("::impugnarComparendo(ProcesoDTO)"));

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Map<String, String> parametros = new HashMap<>();
        // poner valores en parametros
        parametros.put(PARAMETRO_OPCION, EnumOpcionImpugnacion.IMP.toString());
        parametros.put(PARAMETRO_FACTURA, comparendo.getIdFacturaAxis().toString());
        parametros.put(PARAMETRO_OBSERVACION, "-");
        parametros.put(PARAMETRO_EMPRESA, ClienteWSAXISUtil.EMPRESA);
        parametros.put(PARAMETRO_IND_PORC, IND_PORC_N);
        parametros.put(PARAMETRO_FECHA_ACTA, sdf.format(impugnacion.getFechaInicio()));
        parametros.put(PARAMETRO_DOCUMENTO_REFERENCIA, impugnacion.getNumeroProceso());

        llamarServicioImpugnacion(parametros, EnumWebService.IMPUGNAR_COMPARENDO_AXIS);

    }

    @Override
    public Long registrarFalloImpugnacion(FalloImpugnacionDTO fallo, ComparendoDTO comparendo, Long idProceso)
            throws CirculemosNegocioException {
        logger.debug(ClienteWSAXISEJB.class.getName().concat("::registrarFalloImpugnacion(FalloImpugnacionDTO)"));

        Long idFacturaNueva = null;

        // Consulta el proceso
        ProcesoDTO proceso = fachadaProcesoEJB.consultarProceso(idProceso);

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        Map<String, String> parametros = new HashMap<>();
        // poner valores en parametros
        if (fallo.getTipoFallo().getId().equals(EnumTipoFallo.ABSOLUTORIO.getValue())) {
            parametros.put(PARAMETRO_OPCION, EnumOpcionImpugnacion.ABS.toString());
            parametros.put(PARAMETRO_OBSERVACION, EnumTipoFallo.ABSOLUTORIO.toString());
            parametros.put(PARAMETRO_IND_PORC, IND_PORC_N);
        } else if (fallo.getTipoFallo().getId().equals(EnumTipoFallo.CONDENATORIO.getValue())
                || fallo.getTipoFallo().getId().equals(EnumTipoFallo.CONDENATORIO_PARCIAL.getValue())) {
            parametros.put(PARAMETRO_OPCION, EnumOpcionImpugnacion.CON.toString());
            parametros.put(PARAMETRO_OBSERVACION, EnumTipoFallo.CONDENATORIO.toString());
            if (fallo.getValorObligacion() != null) {
                parametros.put(PARAMETRO_MULTA, fallo.getValorObligacion().toPlainString());
            }
            if (fallo.getPuntos() != null) {
                parametros.put(PARAMETRO_PUNTOS, String.valueOf(fallo.getPuntos()));
            }
            parametros.put(PARAMETRO_RUBRO, comparendo.getInfraccion().getCodigo());// Codigo infraccion
            parametros.put(PARAMETRO_IND_PORC, IND_PORC_S);
            parametros.put(PARAMETRO_PORCENTAJE, fallo.getPorcentaje().toString());
        }
        parametros.put(PARAMETRO_FACTURA, comparendo.getIdFacturaAxis().toString());
        parametros.put(PARAMETRO_EMPRESA, ClienteWSAXISUtil.EMPRESA);
        parametros.put(PARAMETRO_FECHA_ACTA, sdf.format(new Date()));
        parametros.put(PARAMETRO_DOCUMENTO_REFERENCIA, proceso.getNumeroProceso());

        RespuestaRestWSAxisVO respuestaAxis = llamarServicioImpugnacion(parametros,
                EnumWebService.IMPUGNAR_COMPARENDO_AXIS);
        idFacturaNueva = respuestaAxis.getData().getNuevaFactura();

        return idFacturaNueva;
    }

    /**
     * Realiza el llamado del ws de impugnacion
     * 
     * @param parametros
     * @param enumWebService
     * @return Respuesta del ws
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-08-29
     */
    private RespuestaRestWSAxisVO llamarServicioImpugnacion(Map<String, String> parametros,
            EnumWebService enumWebService) throws CirculemosNegocioException {

        logger.debug(ClienteWSAXISEJB.class.getName().concat("::registrarFalloImpugnacion(FalloImpugnacionDTO)"));
        RespuestaRestWSAxisVO respuestaAxis = null;
        // Se lleva a cabo la consulta de los datos del web service para llevar a cabo la conexion
        WebServiceDTO webService = fachadaAdminGeneralEJB.consultarWebService(enumWebService.getValue());
        if (webService == null) {
            throw new CirculemosNegocioException(ErrorWSImpugnacion.REG_IMP_001);
        }

        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        log.setFechaEnvio(new Date());
        log.setRutaEnvio(webService.getUrlPrimaria());
        log.setObservacion(webService.getUrlPrimaria());

        try {
            // Objeto para realizar la conversion del objeto JSon guardado en la bd
            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

            // Pone en el log los parametros enviados
            log.setMensajeEnviado(parametros.toString());

            // Obtiene instancia de la clase que hace la peticion del servicio rest
            ServicioRest anula = new ServicioRest(parametros, webService.getUrlPrimaria(), EnumTipoRespuesta.JSON,
                    EnumMetodoEnvio.POST);
            String respuesta = anula.enviar();
            // Pone en el log lo que recibio
            log.setMensajeRecibido(respuesta);

            respuestaAxis = gson.fromJson(respuesta, RespuestaRestWSAxisVO.class);

            // Obtiene el id de la respuesta dependiendo del codigo de respuesta
            RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                    .encontrarPorCodigo(respuestaAxis.getCodMensaje(), EnumWebService.IMPUGNAR_COMPARENDO_AXIS)
                    .getValue());
            // Coloca la respuesta del ws
            log.setRespuestaWebService(respuestaWebService);
            if (!respuestaAxis.getCodMensaje().equals(EnumRespuestaWebServices.TRANSACCION_EXITOSA_IMPUGNAR.getCodigo())
                    && !respuestaAxis.getCodMensaje()
                            .equals(EnumRespuestaWebServices.DATO_REGISTRADO_ANTERIORMENTE_IMPUGNAR.getCodigo())) {
                logger.error("Mensaje de error al impugnar comparendo en AXIS : " + respuestaAxis);
                throw new CirculemosNegocioException(ErrorWSImpugnacion.REG_IMP_003);
            } else {
                return respuestaAxis;
            }
        } catch (Exception e) {
            if (e instanceof CirculemosNegocioException) {
                throw (CirculemosNegocioException) e;
            } else {
                // Pone en el log lo que recibio
                log.setMensajeRecibido(e.getMessage());
                log.setRespuestaWebService(
                        new RespuestaWebServiceDTO(EnumRespuestaWebServices.ERROR_INESPERADO_IMPUGNAR.getValue()));
                logger.error("Error al impugnar comparendo en AXIS", e);
                throw new CirculemosNegocioException(ErrorWSImpugnacion.REG_IMP_002);
            }
        } finally {

            try {
                log.setFechaRecepcion(new Date());
                // Guardar log llamado WS
                clienteWSAxis.guardarLogWS(log);
            } catch (Exception e) {
                logger.error("Error al guardar el log de conexión a AXIS", e);
                throw new CirculemosNegocioException(ErrorWSImpugnacion.REG_IMP_004);
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void guardarLogWS(LogEjecucionWSDTO log) {
        fachadaAdminGeneralEJB.registrarLogWS(log);
    }

    @Override
    public CoactivoDTO registarCoactivo(CoactivoDTO coactivoDTO) throws CirculemosNegocioException {
        // Se lleva a cabo la consulta de los datos del web service para llevar a cabo la conexion
        WebServiceDTO webService = fachadaAdminGeneralEJB
                .consultarWebService(EnumWebService.COACTIVO_COMPARENDO_AXIS.getValue());
        if (webService == null) {
            throw new CirculemosNegocioException(ErrorWSCoactivo.REG_COAC_001);
        }

        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        log.setFechaEnvio(new Date());
        log.setRutaEnvio(webService.getUrlPrimaria());
        log.setObservacion(webService.getUrlPrimaria());

        try {
            // Objeto para realizar la conversion del objeto JSon guardado en la bd
            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
            Map<String, String> parametros = new HashMap<>();

            // Obtiene objeto de coactivo que será enviado como parametro
            CoactivoVO coactivo = ClienteWSAXISUtil.homologarCoactivo(coactivoDTO);
            parametros.put(PARAMETRO_COACTIVO, gson.toJson(coactivo));

            // Pone en el log los parametros enviados
            log.setMensajeEnviado(parametros.toString());

            // Obtiene instancia de la clase que hace la peticion del servicio rest
            ServicioRest registro = new ServicioRest(parametros, webService.getUrlPrimaria(), EnumTipoRespuesta.JSON,
                    EnumMetodoEnvio.POST);
            String respuesta = registro.enviar();

            // Pone en el log lo que recibio
            log.setMensajeRecibido(respuesta);

            RespuestaRestWSAxisVO respuestaAxis = gson.fromJson(respuesta, RespuestaRestWSAxisVO.class);

            // Obtiene el id de la respuesta dependiendo del codigo de respuesta
            RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                    .encontrarPorCodigo(respuestaAxis.getCodMensaje(), EnumWebService.COACTIVO_COMPARENDO_AXIS)
                    .getValue());
            // Coloca la respuesta del ws
            log.setRespuestaWebService(respuestaWebService);

            if (respuestaAxis.getCodMensaje().equals(EnumRespuestaWebServices.TRANSACCION_EXITOSA_COACTIVO.getCodigo())
                    || respuestaAxis.getCodMensaje()
                            .equals(EnumRespuestaWebServices.DATO_REGISTRADO_ANTERIORMENTE_COACTIVO.getCodigo())) {
                coactivoDTO.setNumeroCoactivo(String.valueOf(respuestaAxis.getData().getNuevaFactura()));
                coactivoDTO.setIdTramite(respuestaAxis.getData().getIdTramite());
            } else {
                logger.error("Mensaje de error al registrar coactivo en AXIS : " + respuestaAxis);
                throw new CirculemosNegocioException(ErrorWSCoactivo.REG_COAC_003);
            }
        } catch (Exception e) {
            if (e instanceof CirculemosNegocioException) {
                throw (CirculemosNegocioException) e;
            } else {
                // Pone en el log lo que recibio
                log.setMensajeRecibido(e.getMessage());
                log.setRespuestaWebService(
                        new RespuestaWebServiceDTO(EnumRespuestaWebServices.ERROR_INESPERADO_COACTIVO.getValue()));
                logger.error("Error al registrar coactivo en AXIS", e);
                throw new CirculemosNegocioException(ErrorWSCoactivo.REG_COAC_002);
            }
        } finally {
            try {
                log.setFechaRecepcion(new Date());
                // Guardar log llamado WS
                clienteWSAxis.guardarLogWS(log);
            } catch (Exception e) {
                logger.error("Error al guardar el log de conexión a AXIS", e);
                throw new CirculemosNegocioException(ErrorWSCoactivo.REG_COAC_004);
            }
        }

        return coactivoDTO;

    }

}
