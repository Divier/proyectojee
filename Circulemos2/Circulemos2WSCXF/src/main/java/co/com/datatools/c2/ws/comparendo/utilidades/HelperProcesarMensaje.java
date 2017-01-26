package co.com.datatools.c2.ws.comparendo.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.policy.PolicyException;
import org.jboss.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.util.log.LogReplicarPago;
import co.com.datatools.c2.ws.comparendo.utilidades.enumeracion.EnumProcesamientoWS;
import co.com.datatools.c2.ws.recaudo.utilidades.enumeracion.EnumProcesamientoReplicarPagoWS;
import co.com.datatools.c2.ws.utilidades.enumeracion.EnumServicioWS;

/**
 * @author luis.forero
 * 
 */
public class HelperProcesarMensaje {
    /**
     * CARACTER DOS PUNTOS
     */
    private static final String CH_DOS_PUNTOS = ": ";
    /**
     * CARACTERES PARA SEPARADOR
     */
    private static final String CH_SEPARADOR = " - ";

    private static final Logger logger = Logger.getLogger(HelperProcesarMensaje.class.getName());

    public static void obtenerInformacionLogMensaje(SoapMessage message, String faseInterceptor) {
        // Se lleva a cabo la extraccion del nombre del servicio y su respectiva enumeracion para llevar a cabo el regitro del log del servidor
        Exchange exc = message.getExchange();
        EnumServicioWS enumServicioWS = Utilidades.buscarElemEnum(EnumServicioWS.class,
                exc.getService().getName().getLocalPart());
        switch (enumServicioWS) {
        case COMPARENDOS_COLOMBIA:
            ILog logger = LoggerC2.getLogger(EnumLogSistema.RECIBIR_COMPARENDO_WS);
            LogRecibirComparendo logRecibirComparendo = null;
            if (message.containsKey(enumServicioWS.getClaseLogServicio().getName())) {
                logRecibirComparendo = (LogRecibirComparendo) message
                        .get(enumServicioWS.getClaseLogServicio().getName());
            } else {
                logRecibirComparendo = (LogRecibirComparendo) crearInstancia(enumServicioWS);

            }
            if (faseInterceptor.equals(Phase.RECEIVE)) {
                InputStream inputStream = message.getContent(InputStream.class);

                CachedOutputStream cachedInputStream = null;
                if (inputStream != null) {
                    cachedInputStream = new CachedOutputStream();
                    try {
                        IOUtils.copy(inputStream, cachedInputStream);
                        inputStream.close();

                        cachedInputStream.close();
                        // LOGICA DE OBTENCION DEL NUMERO DE COMPARENDO
                        InputStream tmpInputStream = cachedInputStream.getInputStream();

                        DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
                        DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
                        Document documento = creadorDocumento.parse(tmpInputStream);
                        // Obtener el elemento raíz del documento
                        Element raiz = documento.getDocumentElement();
                        Node nComparendo = raiz.getElementsByTagName("comparendo").item(0);
                        NodeList atributosComp = nComparendo.getChildNodes();
                        for (int i = 0; i < atributosComp.getLength(); i++) {
                            Node item = atributosComp.item(i);
                            if (item.getNodeName().equals("numeroComparendo")) {
                                logRecibirComparendo.setNumeroComparendo(item.getTextContent());
                                break;
                            }
                        }
                        message.setContent(InputStream.class, cachedInputStream.getInputStream());
                    } catch (IOException | ParserConfigurationException | SAXException e) {
                        logger.escribir(null, new LogRecibirComparendo(null, e.getMessage()));
                    }
                }
            }
            message.put(enumServicioWS.getClaseLogServicio().getName(), logRecibirComparendo);
            break;
        case SOLICITAR_NUMERO_COMPARENDO:
            // ILog loggerOCN = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_OCN);
            // LogSolicitarNumeroComparendo logSolicitarNumeroComparendo = null;
            // if (message.containsKey(enumServicioWS.getClaseLogServicio().getName())) {
            // logSolicitarNumeroComparendo = (LogSolicitarNumeroComparendo) message.get(enumServicioWS
            // .getClaseLogServicio().getName());
            // } else {
            // logSolicitarNumeroComparendo = (LogSolicitarNumeroComparendo) crearInstancia(enumServicioWS);
            // }
            //
            // if (faseInterceptor.equals(Phase.RECEIVE)) {
            // InputStream inputStream = message.getContent(InputStream.class);
            //
            // CachedOutputStream cachedInputStream = null;
            // if (inputStream != null) {
            // cachedInputStream = new CachedOutputStream();
            // try {
            // IOUtils.copy(inputStream, cachedInputStream);
            // inputStream.close();
            //
            // cachedInputStream.close();
            // // LOGICA DE OBTENCION DEL NUMERO DE COMPARENDO
            // InputStream tmpInputStream = cachedInputStream.getInputStream();
            //
            // DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            // DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
            // Document documento = creadorDocumento.parse(tmpInputStream);
            // // Obtener el elemento raíz del documento
            // Element raiz = documento.getDocumentElement();
            // Node nComparendo = raiz.getElementsByTagName("solicitudNumeroComparendo").item(0);
            // NodeList atributosComp = nComparendo.getChildNodes();
            // for (int i = 0; i < atributosComp.getLength(); i++) {
            // Node item = atributosComp.item(i);
            // if (item.getNodeName().equals("codigoOrganismo")) {
            // logSolicitarNumeroComparendo.setOrganismoTransito(new Integer(item.getTextContent()));
            // break;
            // }
            // }
            // message.setContent(InputStream.class, cachedInputStream.getInputStream());
            // } catch (IOException | ParserConfigurationException | SAXException e) {
            // loggerOCN.escribir(null, new LogRecibirComparendo(null, e.getMessage()));
            // }
            // }
            // }
            // message.put(enumServicioWS.getClaseLogServicio().getName(), logSolicitarNumeroComparendo);
            break;
        case REPLICAR_PAGO:
            obtenerInformacionReplicarPagoLog(message, faseInterceptor);
            break;
        case SERVICIOS_SOGIT:

            break;
        default:
            break;
        }

    }

    /**
     * Crea una instancia del objeto logger de la enumeracion.
     * 
     * @param enumServicioWS
     *            enumeracion del servicio
     * @return instancia del logger correspondiente
     * @author luis.forero(2015-11-27)
     */
    private static Object crearInstancia(EnumServicioWS enumServicioWS, Object... argumentosConstructor) {
        Class<?> clazz;
        Object instance = null;
        try {
            clazz = Class.forName(enumServicioWS.getClaseLogServicio().getName());
            if (argumentosConstructor != null && argumentosConstructor.length > 0) {
                Class<?>[] clArg = null;
                clArg = new Class<?>[argumentosConstructor.length];
                for (int i = 0; i < argumentosConstructor.length; i++) {
                    clArg[i] = argumentosConstructor[i].getClass();
                }
                Constructor<?> constructor = clazz.getConstructor(clArg);

                instance = constructor.newInstance(argumentosConstructor);

            } else {
                Constructor<?> constructor = clazz.getConstructor();

                instance = constructor.newInstance();
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.debug(e.getMessage());
        }
        return instance;
    }

    /**
     * Escribe un log de servidor identificando y/o creando su respectivo objeto log a ser registrado para cada web service entrante.
     * 
     * @param nombreUsuario
     *            nombre del usuario con el cual se escribe el log
     * @param message
     *            mensaje con el cual se determina el origen del servicio y se obtienen los objetos y atributos guardados para el registro del Log.
     * @author luis.forero(2015-11-26)
     */
    public static void escribirLogServidor(String nombreUsuario, SoapMessage message) {
        // Se obtiene en nombre y se identifica el servicio al cual corresponde el webservice el cual contiene la clase de log con la que se crea
        Exchange exc = message.getExchange();
        EnumServicioWS enumServicioWS = Utilidades.buscarElemEnum(EnumServicioWS.class,
                exc.getService().getName().getLocalPart());
        // Se determinan los datos dependiendo del web service al cual hace referencia para la escritura del log
        switch (enumServicioWS) {
        case COMPARENDOS_COLOMBIA:
            ILog logger = LoggerC2.getLogger(EnumLogSistema.RECIBIR_COMPARENDO_WS);
            LogRecibirComparendo logRecibirComparendo = null;
            // Creacion del log correspondiente
            if (message.containsKey(enumServicioWS.getClaseLogServicio().getName())) {
                logRecibirComparendo = (LogRecibirComparendo) message
                        .get(enumServicioWS.getClaseLogServicio().getName());
            } else {
                logRecibirComparendo = (LogRecibirComparendo) crearInstancia(enumServicioWS);
            }

            RespuestaWebServiceDTO errorRechazado = consultarErrorProcesamiento(
                    EnumRespuestaWebServices.ERROR_BLOQUEANTE);

            // Se lleva a cabo la identificacion del error correspondiente para ingresar el error
            Exception content = message.getContent(Exception.class);
            if (content instanceof Fault) {
                Fault fault = (Fault) content;
                Throwable cause = fault.getCause();

                Set<String> hS = message.keySet();
                System.out.println(hS.toString());
                while (cause != null) {
                    if (cause instanceof SAXParseException) {
                        // Escritura en logger
                        logRecibirComparendo.setEstadoTransaccion(errorRechazado.getCodigo() + CH_SEPARADOR
                                + errorRechazado.getNombre() + CH_DOS_PUNTOS + cause.getMessage());
                        break;
                    }

                    cause = cause.getCause();
                }
            } else if (content instanceof SecurityException) {
                SecurityException fault = (SecurityException) content;
                Throwable cause = fault.getCause();
                logRecibirComparendo
                        .setEstadoTransaccion(errorRechazado.getCodigo() + CH_SEPARADOR + errorRechazado.getNombre()
                                + CH_DOS_PUNTOS + (cause != null ? cause.getMessage() : fault.getMessage()));
            } else if (content instanceof PolicyException) {
                PolicyException fault = (PolicyException) content;
                Throwable cause = fault.getCause();
                logRecibirComparendo
                        .setEstadoTransaccion(errorRechazado.getCodigo() + CH_SEPARADOR + errorRechazado.getNombre()
                                + CH_DOS_PUNTOS + (cause != null ? cause.getMessage() : fault.getMessage()));
            } else if (content != null) {
                logRecibirComparendo.setEstadoTransaccion(errorRechazado.getCodigo() + CH_SEPARADOR
                        + errorRechazado.getNombre() + CH_DOS_PUNTOS
                        + (content.getCause() == null ? content.getMessage() : content.getCause().getMessage()));
            }

            // se lleva a cabo la escritura del error
            logger.escribir(nombreUsuario, logRecibirComparendo);
            break;
        case SOLICITAR_NUMERO_COMPARENDO:
            // ILog loggerOCN = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_OCN);
            // LogSolicitarNumeroComparendo logSolicitarNumeroComparendo = null;
            // // Creacion del log correspondiente
            // if (message.containsKey(enumServicioWS.getClaseLogServicio().getName())) {
            // logSolicitarNumeroComparendo = (LogSolicitarNumeroComparendo) message.get(enumServicioWS
            // .getClaseLogServicio().getName());
            // } else {
            // logSolicitarNumeroComparendo = (LogSolicitarNumeroComparendo) crearInstancia(enumServicioWS);
            // }
            //
            // Exception contentOCN = message.getContent(Exception.class);
            // if (contentOCN instanceof Fault) {
            // Fault fault = (Fault) contentOCN;
            // Throwable cause = fault.getCause();
            //
            // Set<String> hS = message.keySet();
            // System.out.println(hS.toString());
            // while (cause != null) {
            // if (cause instanceof SAXParseException) {
            // // Escritura en logger
            // logSolicitarNumeroComparendo
            // .setEstadoTransaccion(EnumProcesamientoWS.ERROR_COMPARENDO_RECHAZADO.getCodigo()
            // + CH_SEPARADOR + EnumProcesamientoWS.ERROR_COMPARENDO_RECHAZADO.toString()
            // + CH_DOS_PUNTOS + cause.getMessage());
            // break;
            // }
            //
            // cause = cause.getCause();
            // }
            // } else if (contentOCN instanceof SecurityException) {
            // SecurityException fault = (SecurityException) contentOCN;
            // Throwable cause = fault.getCause();
            // logSolicitarNumeroComparendo.setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString()
            // + CH_DOS_PUNTOS + (cause != null ? cause.getMessage() : fault.getMessage()));
            // } else if (contentOCN instanceof PolicyException) {
            // PolicyException fault = (PolicyException) contentOCN;
            // Throwable cause = fault.getCause();
            // logSolicitarNumeroComparendo.setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString()
            // + CH_DOS_PUNTOS + (cause != null ? cause.getMessage() : fault.getMessage()));
            // } else if (contentOCN != null) {
            // logSolicitarNumeroComparendo
            // .setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString()
            // + CH_DOS_PUNTOS
            // + (contentOCN.getCause() == null ? contentOCN.getMessage() : contentOCN.getCause()
            // .getMessage()));
            // }
            //
            // // se lleva a cabo la escritura del error
            // loggerOCN.escribir(nombreUsuario, logSolicitarNumeroComparendo);
            break;
        case REPLICAR_PAGO:
            escribirReplicarPagoLog(message, nombreUsuario);
            break;
        default:
            break;
        }

    }

    /**
     * Escribe un log de servidor identificando y/o creando su respectivo objeto log a ser registrado para cada web service entrante.
     * 
     * @param nombreUsuario
     *            nombre del usuario con el cual se escribe el log
     * @param message
     *            mensaje con el cual se determina el origen del servicio y se obtienen los objetos y atributos guardados para el registro del Log.
     * @param errMsg
     *            Mensaje de error a mostrar en el log correspondiente.
     * @author luis.forero (2015-11-30)<br>
     *         luis.forero (mod 2016-01-20)
     */
    public static void escribirLogServidor(String nombreUsuario, Message message, String errMsg) {
        Exchange exc = message.getExchange();
        EnumServicioWS enumServicioWS = Utilidades.buscarElemEnum(EnumServicioWS.class,
                exc.getService().getName().getLocalPart());
        switch (enumServicioWS) {
        case COMPARENDOS_COLOMBIA:
            ILog logger = LoggerC2.getLogger(EnumLogSistema.RECIBIR_COMPARENDO_WS);
            LogRecibirComparendo logRecibirComparendo = null;
            if (message.containsKey(enumServicioWS.getClaseLogServicio().getName())) {
                logRecibirComparendo = (LogRecibirComparendo) message
                        .get(enumServicioWS.getClaseLogServicio().getName());
            } else {
                logRecibirComparendo = (LogRecibirComparendo) crearInstancia(enumServicioWS);
            }

            logRecibirComparendo
                    .setEstadoTransaccion(EnumProcesamientoWS.ERROR_COMPARENDO_RECHAZADO.getCodigo() + CH_SEPARADOR
                            + EnumProcesamientoWS.ERROR_COMPARENDO_RECHAZADO.toString() + CH_DOS_PUNTOS + errMsg);

            logger.escribir(nombreUsuario, logRecibirComparendo);

            break;
        case SOLICITAR_NUMERO_COMPARENDO:
            // ILog loggerOCN = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_OCN);
            // LogSolicitarNumeroComparendo logSolicitarNumeroComparendo = null;
            // if (message.containsKey(enumServicioWS.getClaseLogServicio().getName())) {
            // logSolicitarNumeroComparendo = (LogSolicitarNumeroComparendo) message.get(enumServicioWS
            // .getClaseLogServicio().getName());
            // } else {
            // logSolicitarNumeroComparendo = (LogSolicitarNumeroComparendo) crearInstancia(enumServicioWS);
            // }
            //
            // logSolicitarNumeroComparendo.setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString()
            // + CH_DOS_PUNTOS + errMsg);
            // loggerOCN.escribir(nombreUsuario, logSolicitarNumeroComparendo);
            break;
        case REPLICAR_PAGO:
            escribirReplicarPagoLog(message, nombreUsuario, errMsg);
            break;
        default:
            break;
        }

    }

    /**
     * Lleva a cabo la carga de un mensaje de bundle
     * 
     * @param bundle
     *            bundle donde se consulta el mensaje
     * @param llave
     *            llave del mensaje
     * @param args
     *            argumentos del mensaje de tenerlos.
     * @return mensaje junto con los argumentos ingresados
     * @author luis.forero(2015-12-01)
     */
    public static String getMensaje(ResourceBundle bundle, String llave, Object... args) {
        String mensaje = null;
        String msg = bundle.getString(llave);
        if (args != null && args.length > 0) {
            mensaje = MessageFormat.format(msg, args);
        } else {
            mensaje = msg;
        }
        return mensaje;
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
    private static RespuestaWebServiceDTO consultarErrorProcesamiento(EnumRespuestaWebServices errorProcesamiento) {
        IRFachadaAdminGeneral fachadaAdminGeneralEJB = BeanLocatorC2.locate(IRFachadaAdminGeneral.class,
                BeanLocatorC2.Beans.IRFachadaAdminGeneral.toString());
        List<RespuestaWebServiceDTO> lstRespuestasWSRecibir = fachadaAdminGeneralEJB
                .consultarRespuestasWebService(errorProcesamiento.getEnumWebService().getValue());
        for (RespuestaWebServiceDTO rstWebService : lstRespuestasWSRecibir) {
            if (rstWebService.getId().equals(errorProcesamiento.getValue())) {
                return rstWebService;
            }
        }
        return null;
    }

    /**
     * Obtiene informacion del log para el servicio de replicar pago
     * 
     * @param message
     * @param enumServicioWS
     * @param faseInterceptor
     * @author julio.pinzon (2016-04-25)
     */
    public static void obtenerInformacionReplicarPagoLog(SoapMessage message, String faseInterceptor) {
        ILog loggerRP = LoggerC2.getLogger(EnumLogSistema.REPLICAR_PAGO_WS);
        LogReplicarPago logReplicarPago = null;
        if (message.containsKey(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName())) {
            logReplicarPago = (LogReplicarPago) message
                    .get(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName());
        } else {
            logReplicarPago = (LogReplicarPago) crearInstancia(EnumServicioWS.REPLICAR_PAGO);

        }
        if (faseInterceptor.equals(Phase.RECEIVE)) {
            InputStream inputStream = message.getContent(InputStream.class);

            CachedOutputStream cachedInputStream = null;
            if (inputStream != null) {
                cachedInputStream = new CachedOutputStream();
                try {
                    IOUtils.copy(inputStream, cachedInputStream);
                    inputStream.close();

                    cachedInputStream.close();
                    // LOGICA DE OBTENCION DEL NUMERO DE RECAUDO
                    InputStream tmpInputStream = cachedInputStream.getInputStream();

                    DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
                    DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
                    Document documento = creadorDocumento.parse(tmpInputStream);
                    // Obtener el elemento raíz del documento
                    Element raiz = documento.getDocumentElement();
                    Node nComparendo = raiz.getElementsByTagName("pago").item(0);
                    NodeList atributosComp = nComparendo.getChildNodes();
                    for (int i = 0; i < atributosComp.getLength(); i++) {
                        Node item = atributosComp.item(i);
                        if (item.getNodeName().equals("numeroRecaudo")) {
                            logReplicarPago.setNumeroRecaudo(item.getTextContent());
                            break;
                        }
                    }
                    message.setContent(InputStream.class, cachedInputStream.getInputStream());
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    loggerRP.escribir(null, new LogRecibirComparendo(null, e.getMessage()));
                }
            }
        }
        message.put(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName(), logReplicarPago);
    }

    /**
     * Escribir en el log del servicio de replicar pago
     * 
     * @param message
     * @param enumServicioWS
     * @param faseInterceptor
     * @author julio.pinzon (2016-04-25)
     * @param errMsg
     */
    public static void escribirReplicarPagoLog(SoapMessage message, String nombreUsuario) {

        ILog loggerRP = LoggerC2.getLogger(EnumLogSistema.REPLICAR_PAGO_WS);
        LogReplicarPago logReplicarPago = null;
        if (message.containsKey(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName())) {
            logReplicarPago = (LogReplicarPago) message
                    .get(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName());
        } else {
            logReplicarPago = (LogReplicarPago) crearInstancia(EnumServicioWS.REPLICAR_PAGO);

        }

        // Se lleva a cabo la identificacion del error correspondiente para ingresar el error
        Exception content = message.getContent(Exception.class);
        if (content instanceof Fault) {
            Fault fault = (Fault) content;
            Throwable cause = fault.getCause();

            Set<String> hS = message.keySet();
            System.out.println(hS.toString());
            while (cause != null) {
                if (cause instanceof SAXParseException) {
                    // Escritura en logger
                    logReplicarPago
                            .setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo()
                                    + CH_SEPARADOR + EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.toString()
                                    + CH_DOS_PUNTOS + cause.getMessage());
                    break;
                }

                cause = cause.getCause();
            }
        } else if (content instanceof SecurityException) {
            SecurityException fault = (SecurityException) content;
            Throwable cause = fault.getCause();
            logReplicarPago.setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo()
                    + CH_SEPARADOR + EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.toString() + CH_DOS_PUNTOS
                    + (cause != null ? cause.getMessage() : fault.getMessage()));
        } else if (content instanceof PolicyException) {
            PolicyException fault = (PolicyException) content;
            Throwable cause = fault.getCause();
            logReplicarPago.setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo()
                    + CH_SEPARADOR + EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.toString() + CH_DOS_PUNTOS
                    + (cause != null ? cause.getMessage() : fault.getMessage()));
        } else if (content != null) {
            logReplicarPago.setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo()
                    + CH_SEPARADOR + EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.toString() + CH_DOS_PUNTOS
                    + (content.getCause() == null ? content.getMessage() : content.getCause().getMessage()));
        }

        // se lleva a cabo la escritura del error
        loggerRP.escribir(nombreUsuario, logReplicarPago);
    }

    /**
     * Escribir en el log del servicio de replicar pago
     * 
     * @param message
     * @param enumServicioWS
     * @param faseInterceptor
     * @param errMsg
     * @author julio.pinzon (2016-04-25)
     */
    public static void escribirReplicarPagoLog(Message message, String nombreUsuario, String errMsg) {
        ILog loggerRP = LoggerC2.getLogger(EnumLogSistema.REPLICAR_PAGO_WS);
        LogReplicarPago logReplicarPago = null;
        if (message.containsKey(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName())) {
            logReplicarPago = (LogReplicarPago) message
                    .get(EnumServicioWS.REPLICAR_PAGO.getClaseLogServicio().getName());
        } else {
            logReplicarPago = (LogReplicarPago) crearInstancia(EnumServicioWS.REPLICAR_PAGO);

        }

        logReplicarPago
                .setEstadoTransaccion(EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.getCodigo() + CH_SEPARADOR
                        + EnumProcesamientoReplicarPagoWS.ERROR_PAGO_RECHAZADO.toString() + CH_DOS_PUNTOS + errMsg);

        // se lleva a cabo la escritura del error
        loggerRP.escribir(nombreUsuario, logReplicarPago);

    }
}
