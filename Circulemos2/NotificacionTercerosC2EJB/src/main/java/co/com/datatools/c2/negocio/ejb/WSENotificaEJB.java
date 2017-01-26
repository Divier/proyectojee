package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.axis.AxisFault;
import org.jboss.logging.Logger;

import co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument;
import co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse;
import co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationService;
import co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceLocator;
import co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap;
import co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoapStub;
import co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver;
import co.com.datatools.c2.clientes.ws.enotifica.Co_operations_query_bean;
import co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse;
import co.com.datatools.c2.clientes.ws.enotifica.DocumentType;
import co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse;
import co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean;
import co.com.datatools.c2.clientes.ws.enotifica.MetadataBean;
import co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean;
import co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse;
import co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm;
import co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile;
import co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType;
import co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsSignatureTemplate;
import co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsXadesReferenceTransform;
import co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams;
import co.com.datatools.c2.clientes.ws.enotifica.tempuri.BasicHttpBinding_IServerSignatureServiceStub;
import co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService;
import co.com.datatools.c2.clientes.ws.enotifica.tempuri.ServerSignatureService;
import co.com.datatools.c2.clientes.ws.enotifica.tempuri.ServerSignatureServiceLocator;
import co.com.datatools.c2.dto.AutenticacionWebServiceDTO;
import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.CreacionCircuitoDTO;
import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaConsultaCircuitoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaEvidenciaDTO;
import co.com.datatools.c2.dto.RespuestaCreacionCircuitoDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.enumeracion.EnumWebService;
import co.com.datatools.c2.excepciones.NotificacionException;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.interfaces.ILWSENotifica;
import co.com.datatools.c2.negocio.interfaces.IRWSENotifica;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.date.UtilFecha;

@Stateless(mappedName = "WSENotificaEJB")
@LocalBean
public class WSENotificaEJB implements IRWSENotifica, ILWSENotifica {

    private final static Logger logger = Logger.getLogger(WSENotificaEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneralEJB;
    @EJB
    private ILWSENotifica iLWSENotifica;

    @EJB
    private IRRepositorioArchivo iReposArchivoEjb;

    private WebServiceDTO webServiceCircuito;
    private WebServiceDTO webServiceFirma;
    private static final String PARAMETRO_APPID = "appid";
    private static final String PARAMETRO_USUARIO = "username";
    private static final String PARAMETRO_PASSWORD = "password";

    private static final String PARAMETRO_TOKEN = "token";
    private static final String PARAMETRO_TIPO_APLICACION = "tipo_aplicacion";
    private static final String PARAMETRO_TITULO_CIRCUITO = "titulo_circuito";
    private static final String PARAMETRO_DOCUMENTOS = "documentos";
    private static final String PARAMETRO_PUBLICACION_CERTIF = "publicacion_certificada";
    private static final String PARAMETRO_DESTINATARIOS = "destinatarios";
    private static final String PARAMETRO_OCUL_CREADOR_CIRCUITO = "ocultar_creador_circuito";
    private static final String PARAMETRO_RECIBIR_AVISO = "recibir_aviso";
    private static final String PARAMETRO_EXTERNAL_APP_ID = "external_app_id";
    private static final String PARAMETRO_EVIDENCIA_ID = "id_evidencia";
    private static final String PARAMETRO_METADATA = "metadata";
    private static final String ERROR_PARAMETRO = "Hace falta ingresar la informacion de uno de los parametros requeridos por el servicio";
    private static final String METADATA_KEY = "METADATA";
    private static final String PARAMETRO_USER_ID_FIRMA = "user_id_firma";
    private static final String PARAMETRO_PASSWORD_FIRMA = "password_firma";
    private static final String PARAMETRO_ID_CERTIFICATE_FIRMA = "id_certificate_firma";
    private static final String PARAMETRO_CERT_PIN_FIRMA = "cert_pin_firma";
    private static final String PARAMETRO_SIGNATURE_PROFILE_FIRMA = "signature_profile_firma";
    private static final String PARAMETRO_SIGNATURE_TYPE_FIRMA = "signature_type_firma";
    private static final String PARAMETRO_HASH_ALGORITM_FIRMA = "hash_algoritm_firma";
    private static final String PARAMETRO_OPTIONS_FIRMA = "options_firma";
    private static final String PARAMETRO_PARAMETERS_FIRMA = "parameters_firma";
    private static final String PARAMETRO_DETACHED_SIGNATURE_FIRMA = "detached_signature_firma";
    private static final String PARAMETRO_SIGNING_DOCUMENT_FIRMA = "signing_document_firma";

    @PostConstruct
    public void init() {
        webServiceCircuito = fachadaAdminGeneralEJB.consultarWebService(EnumWebService.NOTIFICAR_E_NOTIFICA.getValue());
        webServiceFirma = fachadaAdminGeneralEJB.consultarWebService(EnumWebService.FIRMAR_E_NOTIFICA.getValue());
    }

    @Override
    public byte[] firmarDocumento(byte[] archivo) throws NotificacionException {

        logger.debug(WSENotificaEJB.class.getName().concat("::firmarDocumento(byte[])"));
        checkNotNull(archivo, "Debe recibirse el archivo a firmar");
        byte[] archivoFirmado = null;
        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        try {
            log.setFechaEnvio(UtilFecha.currentZeroTimeDate());
            log.setRutaEnvio(webServiceFirma.getUrlPrimaria());
            log.setObservacion(webServiceFirma.getUrlPrimaria());
            log.setRespuestaWebService(getRespuestaGenerica());

            List<AutenticacionWebServiceDTO> lAutenticacion = webServiceFirma.getAutenticacionWebServices();
            AutenticacionWebServiceDTO authDTO = lAutenticacion.get(0);
            String[] parametrosAdic = authDTO.getParametro().split(",");

            ServerSignatureService sss = new ServerSignatureServiceLocator();
            sss.setBasicHttpBinding_IServerSignatureServiceEndpointAddress(webServiceFirma.getUrlPrimaria());
            IServerSignatureService wsFirma = new BasicHttpBinding_IServerSignatureServiceStub(
                    new URL(sss.getBasicHttpBinding_IServerSignatureServiceAddress()), sss);

            IvSignatureEnumsIvSignatureProfile sigProfile = IvSignatureEnumsIvSignatureProfile.PAdESLTV;
            IvSignatureEnumsIvSignatureType sigType = IvSignatureEnumsIvSignatureType.Default_;
            IvSignatureEnumsIvHashAlgorithm algoritm = IvSignatureEnumsIvHashAlgorithm.SHA256;
            String[] options = { parametrosAdic[2].trim() };
            IvSignatureParams params = new IvSignatureParams();
            params.setIsXadesCounterSignature(false);
            params.setTemplate(IvSignatureEnumsSignatureTemplate.None);
            params.setTstampBackupServerId(0);
            params.setTstampServerId(0);
            params.setXadesPrimaryRefTransform(IvSignatureEnumsXadesReferenceTransform.Default_);

            Map<String, String> parametros = new HashMap<>();
            parametros.put(PARAMETRO_USER_ID_FIRMA, authDTO.getUsuario());
            parametros.put(PARAMETRO_PASSWORD_FIRMA, authDTO.getClave());
            parametros.put(PARAMETRO_ID_CERTIFICATE_FIRMA, parametrosAdic[0].trim());
            parametros.put(PARAMETRO_CERT_PIN_FIRMA, parametrosAdic[1].trim());
            parametros.put(PARAMETRO_SIGNATURE_PROFILE_FIRMA, sigProfile.getValue());
            parametros.put(PARAMETRO_SIGNATURE_TYPE_FIRMA, sigType.getValue());
            parametros.put(PARAMETRO_HASH_ALGORITM_FIRMA, algoritm.getValue());
            parametros.put(PARAMETRO_OPTIONS_FIRMA, options[0]);
            parametros.put(PARAMETRO_PARAMETERS_FIRMA, params.toString());
            parametros.put(PARAMETRO_DETACHED_SIGNATURE_FIRMA, null);
            parametros.put(PARAMETRO_SIGNING_DOCUMENT_FIRMA, archivo.toString());
            log.setMensajeEnviado(parametros.toString());

            archivoFirmado = wsFirma.signByToken(authDTO.getUsuario() + authDTO.getClave(), parametrosAdic[0].trim(),
                    parametrosAdic[1].trim(), sigProfile, sigType, algoritm, options, params, null, archivo);
            log.setMensajeRecibido(archivoFirmado.toString());
            if (archivoFirmado != null) {
                RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                        .encontrarPorCodigo(EnumRespuestaWebServices.EJECUCION_CORRECTA.getCodigo(),
                                EnumWebService.NOTIFICAR_E_NOTIFICA)
                        .getValue());
                log.setRespuestaWebService(respuestaWebService);
            }
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } finally {
            log.setFechaRecepcion(UtilFecha.currentZeroTimeDate());
            // Guardar log llamado WS
            iLWSENotifica.guardarLogWS(log);
        }
        return archivoFirmado;
    }

    @Override
    public String autenticar() throws NotificacionException {

        logger.debug(WSENotificaEJB.class.getName().concat("::autenticar()"));
        String token = null;
        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        try {
            log.setFechaEnvio(UtilFecha.currentZeroTimeDate());
            log.setRutaEnvio(webServiceCircuito.getUrlPrimaria());
            log.setObservacion(webServiceCircuito.getUrlPrimaria());

            CircuitIntegrationService cis = new CircuitIntegrationServiceLocator();
            cis.setCircuitIntegrationServiceSoapEndpointAddress(webServiceCircuito.getUrlPrimaria());
            CircuitIntegrationServiceSoap ciss = new CircuitIntegrationServiceSoapStub(
                    new URL(cis.getCircuitIntegrationServiceSoapAddress()), cis);
            List<AutenticacionWebServiceDTO> lAutenticacion = webServiceCircuito.getAutenticacionWebServices();
            Map<String, String> parametros = new HashMap<>();
            if (lAutenticacion != null && !lAutenticacion.isEmpty()) {
                AutenticacionWebServiceDTO authDTO = lAutenticacion.get(0);
                parametros.put(PARAMETRO_APPID, authDTO.getParametro());
                parametros.put(PARAMETRO_USUARIO, authDTO.getUsuario());
                parametros.put(PARAMETRO_PASSWORD, authDTO.getClave());
                log.setMensajeEnviado(parametros.toString());
                token = ciss.logonbyUserName(authDTO.getParametro(), authDTO.getUsuario(), authDTO.getClave(), null);
                log.setMensajeRecibido(token);
                if (token == null || token.isEmpty()) {
                    log.setRespuestaWebService(getRespuestaGenerica());
                } else {
                    // Obtiene el id de la respuesta dependiendo del codigo de respuesta
                    RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                            .encontrarPorCodigo(EnumRespuestaWebServices.EJECUCION_CORRECTA.getCodigo(),
                                    EnumWebService.NOTIFICAR_E_NOTIFICA)
                            .getValue());
                    log.setRespuestaWebService(respuestaWebService);
                }
            }
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } finally {
            log.setFechaRecepcion(UtilFecha.currentZeroTimeDate());
            // Guardar log llamado WS
            iLWSENotifica.guardarLogWS(log);
        }
        return token;
    }

    @Override
    public RespuestaCreacionCircuitoDTO crearCircuito(CreacionCircuitoDTO cCircuitoDTO) throws NotificacionException {

        logger.debug(WSENotificaEJB.class.getName().concat("::crearCircuito(CreacionCircuitoDTO)"));
        checkNotNull(cCircuitoDTO, "Debe recibirse el circuito a crear");
        String token = null;
        CreationCircuitResponse ccr = null;
        RespuestaCreacionCircuitoDTO respuesta = null;
        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        try {
            log.setFechaEnvio(UtilFecha.currentZeroTimeDate());
            log.setRutaEnvio(webServiceCircuito.getUrlPrimaria());
            log.setObservacion(webServiceCircuito.getUrlPrimaria());

            token = autenticar();
            if (token != null && !token.isEmpty()) {
                CircuitIntegrationService cis = new CircuitIntegrationServiceLocator();
                cis.setCircuitIntegrationServiceSoapEndpointAddress(webServiceCircuito.getUrlPrimaria());
                CircuitIntegrationServiceSoap ciss = new CircuitIntegrationServiceSoapStub(
                        new URL(cis.getCircuitIntegrationServiceSoapAddress()), cis);

                String tipoAplicacion = cCircuitoDTO.getTipoCircuito().getCodigo();

                StringBuilder sbDocumentos = new StringBuilder();
                CircuitDocument[] documentos = null;
                if (cCircuitoDTO.getlDocumentos() != null && !cCircuitoDTO.getlDocumentos().isEmpty()) {
                    documentos = new CircuitDocument[cCircuitoDTO.getlDocumentos().size()];
                    CircuitDocument cd = null;
                    int indice = 0;
                    for (ArchivoTransportableDTO documento : cCircuitoDTO.getlDocumentos()) {
                        cd = new CircuitDocument();
                        cd.setFileNameWithExtension(documento.getNombre());
                        cd.setType(DocumentType.MAIN);
                        cd.setContent(documento.getContenido());
                        cd.setTitle(documento.getNombre());
                        cd.setExternalId(cCircuitoDTO.getExternalId());
                        documentos[indice] = cd;
                        sbDocumentos.append(cd.toString());
                        ++indice;
                    }
                }

                StringBuilder sbDestinatarios = new StringBuilder();
                CircuitReceiver[] destinatarios = null;
                if (cCircuitoDTO.getlDestinatarios() != null && !cCircuitoDTO.getlDestinatarios().isEmpty()) {
                    CircuitReceiver cr = null;
                    for (PersonaDTO persona : cCircuitoDTO.getlDestinatarios()) {
                        int indice = 0;
                        destinatarios = new CircuitReceiver[persona.getCorreoPersonaList().size()];
                        for (CorreoPersonaDTO correoPersona : persona.getCorreoPersonaList()) {
                            cr = new CircuitReceiver();
                            cr.setEmail(correoPersona.getCorreoElectronico());
                            cr.setOperationType(cCircuitoDTO.getTipoOperacion().getCodigo());
                            cr.setBody_email(cCircuitoDTO.getConfigMail().getCuerpoEmail());
                            cr.setSubject_email(cCircuitoDTO.getConfigMail().getAsuntoEmail());
                            cr.setExternalId(cCircuitoDTO.getExternalId());
                            destinatarios[indice] = cr;
                            sbDestinatarios.append(cr.toString());
                            ++indice;
                        }
                    }
                }

                boolean publicacionCetif = cCircuitoDTO.isPublicacionCerti();
                boolean oculCreadorCircuito = cCircuitoDTO.isOculCreadorCircuito();
                boolean recibirAviso = cCircuitoDTO.isRecibirAviso();

                StringBuilder sbMetadataCircuit = new StringBuilder();
                MetadataBean[] metadataCircuit = null;
                if (cCircuitoDTO.getlMetadata() != null && !cCircuitoDTO.getlMetadata().isEmpty()) {
                    metadataCircuit = new MetadataBean[cCircuitoDTO.getlMetadata().size()];
                    MetadataBean md = null;
                    int indice = 0;
                    for (ConsultarNotificacionesDTO conNotificacion : cCircuitoDTO.getlMetadata()) {
                        md = new MetadataBean();
                        md.setKey(METADATA_KEY);
                        md.setValue(conNotificacion.toString());
                        metadataCircuit[indice] = md;
                        sbMetadataCircuit.append(md.toString());
                        ++indice;
                    }
                }

                Map<String, String> parametros = new HashMap<>();
                parametros.put(PARAMETRO_TOKEN, token);
                parametros.put(PARAMETRO_TIPO_APLICACION, tipoAplicacion);
                parametros.put(PARAMETRO_TITULO_CIRCUITO, cCircuitoDTO.getTituloCircuito());
                parametros.put(PARAMETRO_DOCUMENTOS, sbDocumentos.toString());
                parametros.put(PARAMETRO_PUBLICACION_CERTIF, String.valueOf(publicacionCetif));
                parametros.put(PARAMETRO_DESTINATARIOS, sbDestinatarios.toString());
                parametros.put(PARAMETRO_OCUL_CREADOR_CIRCUITO, String.valueOf(oculCreadorCircuito));
                parametros.put(PARAMETRO_RECIBIR_AVISO, String.valueOf(recibirAviso));
                parametros.put(PARAMETRO_EXTERNAL_APP_ID, cCircuitoDTO.getExternalAppId());
                parametros.put(PARAMETRO_METADATA, sbMetadataCircuit.toString());
                log.setMensajeEnviado(parametros.toString());

                if (tipoAplicacion == null || cCircuitoDTO.getTituloCircuito() == null || documentos == null
                        || destinatarios == null || metadataCircuit == null) {
                    throw new NotificacionException(ERROR_PARAMETRO);
                }

                ccr = ciss.createCircuitFullDocument(token, tipoAplicacion, cCircuitoDTO.getTituloCircuito(),
                        documentos, publicacionCetif, destinatarios, oculCreadorCircuito, recibirAviso, false, false,
                        false, cCircuitoDTO.getExternalAppId(), false, null, metadataCircuit);
                log.setMensajeRecibido(ccr.toString());
                if (ccr != null) {
                    RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                            .encontrarPorCodigo(ccr.getCreationCode(), EnumWebService.NOTIFICAR_E_NOTIFICA).getValue());
                    log.setRespuestaWebService(respuestaWebService);
                }
            }
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } finally {
            if (token != null) {
                log.setFechaRecepcion(UtilFecha.currentZeroTimeDate());
                // Guardar log llamado WS
                LogEjecucionWSDTO logDTO = iLWSENotifica.guardarLogWS(log);
                respuesta = new RespuestaCreacionCircuitoDTO();
                respuesta.setIdCircuito(ccr.getIdcircuit());
                respuesta.setIdLogEjecucionWS(logDTO.getId());
            }
        }
        return respuesta;
    }

    @Override
    public RespuestaConsultaCircuitoDTO consultarCircuito(String idExternal) throws NotificacionException {

        logger.debug(WSENotificaEJB.class.getName().concat("::consultarCircuito(String)"));
        String token = null;
        OperationsBatchIdQueryResultResponse ocr = null;
        RespuestaConsultaCircuitoDTO respuesta = null;
        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        try {
            log.setFechaEnvio(UtilFecha.currentZeroTimeDate());
            log.setRutaEnvio(webServiceCircuito.getUrlPrimaria());
            log.setObservacion(webServiceCircuito.getUrlPrimaria());

            token = autenticar();
            if (token != null && !token.isEmpty()) {
                CircuitIntegrationService cis = new CircuitIntegrationServiceLocator();
                cis.setCircuitIntegrationServiceSoapEndpointAddress(webServiceCircuito.getUrlPrimaria());
                CircuitIntegrationServiceSoap ciss = new CircuitIntegrationServiceSoapStub(
                        new URL(cis.getCircuitIntegrationServiceSoapAddress()), cis);

                Map<String, String> parametros = new HashMap<>();
                parametros.put(PARAMETRO_TOKEN, token);
                parametros.put(PARAMETRO_EXTERNAL_APP_ID, idExternal);
                log.setMensajeEnviado(parametros.toString());

                ocr = ciss.getAllOperationsByExternalCircuitUsertoken(token, idExternal);
                log.setMensajeRecibido(ocr.toString());
                if (ocr != null) {
                    Co_operations_query_bean[] aOperacion = ocr.getOperations();
                    if (aOperacion != null && aOperacion.length > 0) {
                        respuesta = new RespuestaConsultaCircuitoDTO();
                        for (Co_operations_query_bean operacion : aOperacion) {
                            respuesta.setCodigoEstado(operacion.getStateCode());
                            respuesta.setSmtpCodigo(operacion.getSmtpCode());
                            respuesta.setSmtpDate(
                                    operacion.getSmtpDate() != null ? operacion.getSmtpDate().getTime() : null);
                            respuesta.setCloseDate(
                                    operacion.getCloseDate() != null ? operacion.getCloseDate().getTime() : null);
                            respuesta.setCreateDate(
                                    operacion.getCreateDate() != null ? operacion.getCreateDate().getTime() : null);
                        }
                        RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(
                                EnumRespuestaWebServices.encontrarPorCodigo(String.valueOf(ocr.getErrorCode()),
                                        EnumWebService.NOTIFICAR_E_NOTIFICA).getValue());
                        log.setRespuestaWebService(respuestaWebService);
                    }
                }
            }
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } finally {
            if (token != null) {
                log.setFechaRecepcion(UtilFecha.currentZeroTimeDate());
                // Guardar log llamado WS
                iLWSENotifica.guardarLogWS(log);
            }
        }
        return respuesta;
    }

    @Override
    public List<RespuestaConsultaEvidenciaDTO> consultarEvidencias(DetalleNotificacionDTO detNotifDTO)
            throws NotificacionException {

        logger.debug(WSENotificaEJB.class.getName().concat("::consultarEvidencias(DetalleNotificacionDTO)"));
        checkNotNull(detNotifDTO, "Debe recibirse el detalle de la notificacion");
        String token = null;
        List<RespuestaConsultaEvidenciaDTO> lRConsultaEv = null;
        CircuitEvidenceResponse cer = null;
        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        try {
            log.setFechaEnvio(UtilFecha.currentZeroTimeDate());
            log.setRutaEnvio(webServiceCircuito.getUrlPrimaria());
            log.setObservacion(webServiceCircuito.getUrlPrimaria());

            token = autenticar();
            if (token != null && !token.isEmpty()) {
                CircuitIntegrationService cis = new CircuitIntegrationServiceLocator();
                cis.setCircuitIntegrationServiceSoapEndpointAddress(webServiceCircuito.getUrlPrimaria());
                CircuitIntegrationServiceSoap ciss = new CircuitIntegrationServiceSoapStub(
                        new URL(cis.getCircuitIntegrationServiceSoapAddress()), cis);

                Map<String, String> parametros = new HashMap<>();
                parametros.put(PARAMETRO_TOKEN, token);
                parametros.put(PARAMETRO_EXTERNAL_APP_ID, detNotifDTO.getId().toString());
                log.setMensajeEnviado(parametros.toString());

                cer = ciss.getCircuitEvidence(token, detNotifDTO.getId().toString());
                log.setMensajeRecibido(cer.toString());
                if (cer != null) {
                    Operation_evidence_bean[] aOperacionEv = cer.getOperations();
                    if (aOperacionEv != null && aOperacionEv.length > 0) {
                        for (Operation_evidence_bean oEvidence : aOperacionEv) {
                            Evidence_operation_bean[] aEOperacion = oEvidence.getEvidencias();
                            lRConsultaEv = new ArrayList<>();
                            for (Evidence_operation_bean eOperacion : aEOperacion) {
                                RespuestaConsultaEvidenciaDTO rConsultaEv = new RespuestaConsultaEvidenciaDTO();
                                rConsultaEv.setIdEvidencia(eOperacion.getIdEvidencia());
                                rConsultaEv.setTipoEvidencia(eOperacion.getTipoEvidencia());
                                lRConsultaEv.add(rConsultaEv);
                            }
                        }
                        RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(
                                EnumRespuestaWebServices.encontrarPorCodigo(String.valueOf(cer.getErrorCode()),
                                        EnumWebService.NOTIFICAR_E_NOTIFICA).getValue());
                        log.setRespuestaWebService(respuestaWebService);
                    }
                }
            }
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } finally {
            if (token != null) {
                log.setFechaRecepcion(UtilFecha.currentZeroTimeDate());
                // Guardar log llamado WS
                iLWSENotifica.guardarLogWS(log);
            }
        }
        return lRConsultaEv;
    }

    @Override
    public byte[] obtenerEvidencias(RespuestaConsultaEvidenciaDTO rConsultaEv) throws NotificacionException {

        logger.debug(WSENotificaEJB.class.getName().concat("::obtenerEvidencias(RespuestaConsultaEvidenciaDTO)"));
        checkNotNull(rConsultaEv, "Debe recibirse la evidencia consultada");
        String token = null;
        byte[] evidencia = null;
        EvidenceResultResponse err = null;
        // Inicializa el log a guardar
        LogEjecucionWSDTO log = new LogEjecucionWSDTO();
        try {
            log.setFechaEnvio(UtilFecha.currentZeroTimeDate());
            log.setRutaEnvio(webServiceCircuito.getUrlPrimaria());
            log.setObservacion(webServiceCircuito.getUrlPrimaria());

            token = autenticar();
            if (token != null && !token.isEmpty()) {
                CircuitIntegrationService cis = new CircuitIntegrationServiceLocator();
                cis.setCircuitIntegrationServiceSoapEndpointAddress(webServiceCircuito.getUrlPrimaria());
                CircuitIntegrationServiceSoap ciss = new CircuitIntegrationServiceSoapStub(
                        new URL(cis.getCircuitIntegrationServiceSoapAddress()), cis);

                Map<String, String> parametros = new HashMap<>();
                parametros.put(PARAMETRO_TOKEN, token);
                parametros.put(PARAMETRO_EVIDENCIA_ID, rConsultaEv.getIdEvidencia());
                log.setMensajeEnviado(parametros.toString());

                err = ciss.getPDFFromEvidence(token, rConsultaEv.getIdEvidencia());
                log.setMensajeRecibido(err.toString());
                if (err != null) {
                    evidencia = err.getContent();
                    RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(EnumRespuestaWebServices
                            .encontrarPorCodigo(String.valueOf(err.getErrorCode()), EnumWebService.NOTIFICAR_E_NOTIFICA)
                            .getValue());
                    log.setRespuestaWebService(respuestaWebService);
                }
            }
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
            log.setRespuestaWebService(getRespuestaGenerica());
            throw new NotificacionException(e.getMessage());
        } finally {
            if (token != null) {
                log.setFechaRecepcion(UtilFecha.currentZeroTimeDate());
                // Guardar log llamado WS
                iLWSENotifica.guardarLogWS(log);
            }
        }
        return evidencia;
    }

    private RespuestaWebServiceDTO getRespuestaGenerica() {
        RespuestaWebServiceDTO respuestaWebService = new RespuestaWebServiceDTO(
                EnumRespuestaWebServices.encontrarPorCodigo(EnumRespuestaWebServices.ERROR_GENERICO.getCodigo(),
                        EnumWebService.NOTIFICAR_E_NOTIFICA).getValue());
        return respuestaWebService;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public LogEjecucionWSDTO guardarLogWS(LogEjecucionWSDTO log) {
        return fachadaAdminGeneralEJB.registrarLogWS(log);
    }
}