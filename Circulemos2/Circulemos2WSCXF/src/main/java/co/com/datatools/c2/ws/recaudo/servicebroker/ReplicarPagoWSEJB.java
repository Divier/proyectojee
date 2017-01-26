package co.com.datatools.c2.ws.recaudo.servicebroker;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ErrorInconsistenciaPagoDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.RespuestaHomologarCatalogoRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaProcesarRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.dto.ws.DetalleRespuestaWSDTO;
import co.com.datatools.c2.dto.ws.PagoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaWSDTO;
import co.com.datatools.c2.enumeracion.EnumCatalogosHomologacion;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.enumeracion.EnumWebService;
import co.com.datatools.c2.enumeraciones.EnumErrorInconsistenciaPago;
import co.com.datatools.c2.enumeraciones.EnumTipoRechazoRecaudo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.ErrorRecaudo.ValidarReglaNegocioRecaudo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaCartera;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRRecaudo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.util.RecaudoUtil;
import co.com.datatools.c2.util.log.LogReplicarPago;
import co.com.datatools.c2.ws.recaudo.utilidades.enumeracion.EnumProcesamientoReplicarPagoWS;
import co.com.datatools.c2.ws.recaudo.utilidades.helper.ReplicarPagoHelper;

/**
 * EJB encargado de procesar la peticion del WS de replicar pago
 * 
 * 
 */
@Stateless(name = "ReplicarPagoWSEJB")
@LocalBean
public class ReplicarPagoWSEJB implements ILReplicarPagoWS {

    private static final Logger logger = Logger.getLogger(ReplicarPagoWSEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRRecaudo recaudoEJB;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneralEJB;

    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;

    @EJB
    private IRFachadaConfiguracion fachadaConfiguracionEJB;

    @EJB
    private IRFachadaCartera iRFachadaCartera;

    /**
     * 
     */
    private static final String IGUAL = " = ";

    /**
     * 
     */
    private static final String SEPARADOR = " - ";

    /**
     * 
     */
    private static final String ERR_CAMPO = ": ";

    /**
     * Campos para errores log
     */
    private static final String TOTAL_RECAUDO = "totalRecaudo";
    private static final String VALOR_EFECTIVO = "totalEfectivo";
    private static final String VALOR_CHEQUE = "totalCheque";
    private static final String ORGANISMO = "codigoOrganismoTransito";
    private static final String NUMERO_RECAUDO = "numeroRecaudo";
    private static final String NUMERO_OBLIGACION = "obligacionPagada";
    private static final String NUMERO_CUENTA = "cuenta";
    private static final String FECHA_RECAUDO = "fechaTransaccion";
    private static final String NUMERO_IDENTIFICACION = "numeroIdentificacion";
    private static final String VALOR_OBLIGACION = "valorObligacion";
    private static final String NUMERO_CUOTA = "numeroCuota";
    private static final String HORA_RECAUDO = "horaTransaccion";

    private static final ILog loggerReplicar = LoggerC2.getLogger(EnumLogSistema.REPLICAR_PAGO_WS);

    // Listado que contiene las respuestas posibles de recibir comparendo.
    private static List<RespuestaWebServiceDTO> lstRespuestasWSReplicar = null;

    @Override
    public RespuestaWSDTO replicarPagoWs(PagoWSDTO pago) {
        logger.debug("ReplicarPagoWSEJB.replicarPagoWs(PagoWSDTO)");
        LogReplicarPago logReplicarPago = new LogReplicarPago(pago.getNumeroRecaudo(), null);
        DetalleRespuestaWSDTO errorInesperado = construirDetalleRespuestaWSDTO(
                EnumRespuestaWebServices.NO_SE_PUDO_REALIZA_TRANSACCION_RECAUDO);

        RespuestaWSDTO respuestaWSDTO = new RespuestaWSDTO();
        respuestaWSDTO.setDetalle(new ArrayList<DetalleRespuestaWSDTO>(0));

        String loginUsuario = seguridadCirculemosEJB.obtenerUsuarioDto().getLogin();
        RecaudoUtil.inicializarCacheHomologacion();
        try {

            PagoDTO pagoDTO = ReplicarPagoHelper.toPagoDTO(pago);
            List<ItRecaudoDTO> recaudos = ReplicarPagoHelper.toItRecaudoDTO(pago);
            for (ItRecaudoDTO itRecaudoDTO : recaudos) {
                // Homologacion de recaudos
                RespuestaHomologarCatalogoRecaudoDTO respuestaHomologacion = recaudoEJB
                        .homologarCatalogosReplicarPago(pagoDTO, itRecaudoDTO);
                List<EnumTipoRechazoRecaudo> tiposRechazoRecaudo = respuestaHomologacion.getErrores();
                if (!tiposRechazoRecaudo.isEmpty()) {
                    logReplicarPago.setDetalle("");
                    for (EnumTipoRechazoRecaudo enumTipoRechazoRecaudo : tiposRechazoRecaudo) {
                        String campo = null;
                        String valor = null;
                        switch (enumTipoRechazoRecaudo) {
                        case BANCO_NO_EXISTE:
                            valor = itRecaudoDTO.getCodigoBanco();
                            campo = EnumCatalogosHomologacion.bancoRecaudo.toString();
                            break;
                        case CODIGO_BANCO_OBLIGATORIO:
                            valor = itRecaudoDTO.getCodigoBanco();
                            campo = EnumCatalogosHomologacion.bancoRecaudo.toString();
                            break;
                        case CODIGO_TIPO_CUENTA_OBLIGATORIO:
                            valor = itRecaudoDTO.getCodigoTipoCuenta();
                            campo = EnumCatalogosHomologacion.tipoCuenta.toString();
                            break;
                        case CODIGO_TIPO_ID_OBLIGATORIO:
                            valor = itRecaudoDTO.getCodigoTipoIdentificacion();
                            campo = EnumCatalogosHomologacion.codigoTipoIdentificacion.toString();
                            break;
                        case CODIGO_TIPO_RECAUDO_OBLIGATORIO:
                            valor = itRecaudoDTO.getCodigoTipoRecaudo();
                            campo = EnumCatalogosHomologacion.tipoRecaudo.toString();
                            break;
                        case FECHA_RECAUDO_SUPERIOR_ACTUAL:
                            valor = itRecaudoDTO.getFechaTransaccion().toString();
                            campo = FECHA_RECAUDO;
                            break;
                        case NUMERO_CUENTA_OBLIGATORIO:
                            valor = itRecaudoDTO.getNumeroCuenta();
                            campo = NUMERO_CUENTA;
                            break;
                        case NUMERO_CUENTA_LONGITUD:
                            valor = itRecaudoDTO.getNumeroCuenta();
                            campo = NUMERO_CUENTA;
                            break;
                        case NUMERO_OBLIGACION_OBLIGATORIO:
                            valor = itRecaudoDTO.getNumeroObligacion();
                            campo = NUMERO_OBLIGACION;
                            break;
                        case NUMERO_RECAUDO_OBLIGATORIO:
                            valor = itRecaudoDTO.getNumeroRecaudo();
                            campo = NUMERO_RECAUDO;
                            break;
                        case ORGANISMO_NO_EXISTE:
                            valor = itRecaudoDTO.getCodigoOrganismo() + "";
                            campo = ORGANISMO;
                            break;
                        case ORGANISMO_INCORRECTO:
                            valor = itRecaudoDTO.getCodigoOrganismo() + "";
                            campo = ORGANISMO;
                            break;
                        case TIPO_CUENTA_NO_EXISTE:
                            valor = itRecaudoDTO.getCodigoTipoCuenta();
                            campo = EnumCatalogosHomologacion.tipoCuenta.toString();
                            break;
                        case TIPO_DOCUMENTO_IDENTIFICACION_NO_EXISTE:
                            valor = itRecaudoDTO.getCodigoTipoIdentificacion();
                            campo = EnumCatalogosHomologacion.codigoTipoIdentificacion.toString();
                            break;
                        case TIPO_RECAUDO_NO_EXISTE:
                            valor = itRecaudoDTO.getCodigoTipoRecaudo();
                            campo = EnumCatalogosHomologacion.tipoRecaudo.toString();
                            break;
                        case TOTAL_RECAUDO_MENOR_IGUAL_CERO:
                            valor = itRecaudoDTO.getTotalRecaudo() + "";
                            campo = TOTAL_RECAUDO;
                            break;
                        case TOTAL_RECAUDO_OBLIGATORIO:
                            valor = itRecaudoDTO.getTotalRecaudo() + "";
                            campo = TOTAL_RECAUDO;
                            break;
                        case VALOR_CHEQUE_MENOR_IGUAL_CERO:
                            valor = itRecaudoDTO.getTotalCheque().toPlainString();
                            campo = VALOR_CHEQUE;
                            break;
                        case VALOR_EFECTIVO_MENOR_IGUAL_CERO:
                            valor = itRecaudoDTO.getTotalEfectivo().toPlainString();
                            campo = VALOR_EFECTIVO;
                            break;
                        case TOTAL_RECAUDO_NO_SUMA_MEDIOS_PAGO:
                            valor = itRecaudoDTO.getTotalRecaudo().toPlainString();
                            campo = TOTAL_RECAUDO;
                            break;
                        case NUMERO_IDENTIFICACION_OBLIGATORIO:
                            valor = itRecaudoDTO.getNumeroIdentificacion();
                            campo = NUMERO_IDENTIFICACION;
                            break;
                        case VALOR_OBLIGACION_MENOR_IGUAL_CERO:
                            valor = itRecaudoDTO.getValorObligacion() + "";
                            campo = VALOR_OBLIGACION;
                            break;
                        case NUMERO_CUOTA_MINIMO:
                            valor = itRecaudoDTO.getNumeroCuota() + "";
                            campo = NUMERO_CUOTA;
                            break;
                        case NUMERO_CUOTA_INCORRECTO:
                            valor = itRecaudoDTO.getNumeroCuota() + "";
                            campo = NUMERO_CUOTA;
                            break;
                        case NUMERO_OBLIGACION_LONGITUD:
                            valor = itRecaudoDTO.getNumeroObligacion();
                            campo = NUMERO_OBLIGACION;
                            break;
                        case FECHA_TRANSACCION_INCORRECTA:
                            valor = itRecaudoDTO.getFechaTransaccion() + "";
                            campo = FECHA_RECAUDO;
                            break;
                        case HORA_TRANSACCION_INCORRECTA:
                            valor = itRecaudoDTO.getHoraTransaccion() + "";
                            campo = HORA_RECAUDO;
                            break;
                        case NUMERO_IDENTIFICACION_LONGITUD:
                            valor = itRecaudoDTO.getNumeroIdentificacion();
                            campo = NUMERO_IDENTIFICACION;
                            break;
                        case NUMERO_RECAUDO_INCORRECTO:
                            valor = itRecaudoDTO.getNumeroRecaudo();
                            campo = NUMERO_RECAUDO;
                            break;
                        }
                        procesarErrorCatalogo(respuestaWSDTO, logReplicarPago, campo, valor);
                    }
                    break;
                }
                pagoDTO = respuestaHomologacion.getPagoDTO();
            }

            if (!respuestaWSDTO.getDetalle().isEmpty()) {
                respuestaWSDTO.setCodigoGeneral(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());
                logReplicarPago.setEstadoTransaccion(respuestaWSDTO.getCodigoGeneral());
                return respuestaWSDTO;
            }

            RespuestaProcesarRecaudoDTO respuesta = recaudoEJB.procesarRecaudo(pagoDTO);
            respuestaWSDTO = convierteRespuestaReplicarPagoWSDTO(respuesta);
            // Registrar respuesta y escribir resultado en logger.
            logReplicarPago.setEstadoTransaccion(respuestaWSDTO.getCodigoGeneral());
        } catch (CirculemosNegocioException e) {
            logReplicarPago.setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());
            respuestaWSDTO.setCodigoGeneral(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());

            ValidarReglaNegocioRecaudo valReglaNegocio = (ValidarReglaNegocioRecaudo) e.getErrorInfo();

            switch (valReglaNegocio) {

            case REC_006_001:
                respuestaWSDTO.getDetalle()
                        .add(construirDetalleRespuestaWSDTO(EnumRespuestaWebServices.NO_CATALOGO_ORGANISMO_TRANSITO));
                break;
            case REC_006_002:
                respuestaWSDTO.getDetalle()
                        .add(construirDetalleRespuestaWSDTO(EnumRespuestaWebServices.FECHA_PAGO_SUPERIOR_ACTUAL));
                break;
            case REC_006_003:
                respuestaWSDTO.getDetalle().add(
                        construirDetalleRespuestaWSDTO(EnumRespuestaWebServices.TOTAL_RECAUDO_NO_SUMA_MEDIOS_PAGO));
                break;
            default:
                respuestaWSDTO.getDetalle().add(errorInesperado);
                break;
            }
            logReplicarPago.setDetalle(e.getMessage());
        } catch (Exception e) {
            // Se define el error correspondiente a algo inesperado.
            logReplicarPago.setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());
            respuestaWSDTO.setCodigoGeneral(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());
            respuestaWSDTO.getDetalle().add(errorInesperado);
            logReplicarPago.setDetalle(e.getMessage());
        } finally {
            RecaudoUtil.limpiarCacheHomologacion();
            logReplicarPago.setEstadoTransaccion(respuestaWSDTO.getCodigoGeneral());
            loggerReplicar.escribir(loginUsuario, logReplicarPago);
        }
        return respuestaWSDTO;
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
        if (lstRespuestasWSReplicar == null) {
            lstRespuestasWSReplicar = fachadaAdminGeneralEJB
                    .consultarRespuestasWebService(EnumWebService.REPLICAR_PAGO.getValue());
        }
        for (RespuestaWebServiceDTO rstWebService : lstRespuestasWSReplicar) {
            if (rstWebService.getId().equals(errorProcesamiento.getValue())) {
                return rstWebService;
            }
        }
        return null;
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
     * Transforma la respuesta recibida a los codigos de respuesta del WS
     * 
     * @param respuesta
     * @return Respuesta del WS
     * @author julio.pinzon
     */
    private RespuestaWSDTO convierteRespuestaReplicarPagoWSDTO(RespuestaProcesarRecaudoDTO respuesta) {
        RespuestaWSDTO respuestaReplicarPagoWSDTO = new RespuestaWSDTO();
        switch (respuesta.getEstadoTransaccion()) {
        case REGISTRADO:
            respuestaReplicarPagoWSDTO
                    .setCodigoGeneral(EnumProcesamientoReplicarPagoWS.REGISTRO_SATISFACTORIO.getCodigo());
            break;
        case INCONSISTENTE:
            respuestaReplicarPagoWSDTO
                    .setCodigoGeneral(EnumProcesamientoReplicarPagoWS.PAGO_RECIBIDO_SIN_PROCESAR.getCodigo());
            break;
        case RECHAZADO:
            respuestaReplicarPagoWSDTO
                    .setCodigoGeneral(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());
            break;
        }
        if (respuesta.getErrores() != null) {
            respuestaReplicarPagoWSDTO
                    .setDetalle(convierteDetalleRespuestaReplicarPagoWSDTOList(respuesta.getErrores()));
        }
        return respuestaReplicarPagoWSDTO;
    }

    /**
     * Transforma la respuesta recibida a los codigos de respuesta del WS
     * 
     * @param list
     * @return Respuesta del WS
     * @author julio.pinzon
     */
    private List<DetalleRespuestaWSDTO> convierteDetalleRespuestaReplicarPagoWSDTOList(
            List<ErrorInconsistenciaPagoDTO> list) {
        List<DetalleRespuestaWSDTO> detalles = new ArrayList<DetalleRespuestaWSDTO>();

        for (ErrorInconsistenciaPagoDTO errorInconsistenciaPagoDTO : list) {
            DetalleRespuestaWSDTO detalleRespuestaReplicarPagoDTO = null;

            if (errorInconsistenciaPagoDTO.getCodigo()
                    .equals(EnumErrorInconsistenciaPago.PAGO_YA_APLICADO.getCodigo())) {
                detalleRespuestaReplicarPagoDTO = construirDetalleRespuestaWSDTO(
                        EnumRespuestaWebServices.PAGO_YA_APLICADO);
            }
            if (errorInconsistenciaPagoDTO.getCodigo()
                    .equals(EnumErrorInconsistenciaPago.FALTA_NUMERO_CUOTA.getCodigo())) {
                detalleRespuestaReplicarPagoDTO = construirDetalleRespuestaWSDTO(
                        EnumRespuestaWebServices.FALTA_NUMERO_CUOTA);
            }
            if (errorInconsistenciaPagoDTO.getCodigo()
                    .equals(EnumErrorInconsistenciaPago.PAGO_YA_APLICADO.getCodigo())) {
                detalleRespuestaReplicarPagoDTO = construirDetalleRespuestaWSDTO(
                        EnumRespuestaWebServices.PAGO_YA_REGISTRADO);
            }
            detalles.add(detalleRespuestaReplicarPagoDTO);
        }
        return detalles;
    }

    /**
     * Construye error de homologacion correspondiente al catalogo o campo
     * 
     * @param respuestaWSDTO
     *            respuesta al que se agrega error
     * @param logReplicarPago
     *            log con los datos a ser escritos sobre el servidor
     * @param campo
     *            campo de catalogo de homologacion {@link EnumCatalogosHomologacion}
     * @param valor
     *            valor entrante del campo
     * @author luis.forero (2015-12-04)
     */
    private void procesarErrorCatalogo(RespuestaWSDTO respuestaWSDTO, LogReplicarPago logReplicarPago, String campo,
            Object valor) {
        respuestaWSDTO.setCodigoGeneral(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo());
        DetalleRespuestaWSDTO errMapeoInexistente = construirDetalleRespuestaWSDTO(
                EnumRespuestaWebServices.VALOR_NO_MAPEADO_RECAUDO);
        errMapeoInexistente.setDescripcion(errMapeoInexistente.getDescripcion() + ERR_CAMPO + campo);
        respuestaWSDTO.getDetalle().add(errMapeoInexistente);
        logReplicarPago.setDetalle(logReplicarPago.getDetalle() + SEPARADOR + errMapeoInexistente.getCodigo()
                + SEPARADOR + errMapeoInexistente.getDescripcion() + IGUAL + String.valueOf(valor));
        // loggerReplicar.escribir(seguridadCirculemosEJB.obtenerUsuarioDto().getLogin(), logReplicarPago);
    }
}
