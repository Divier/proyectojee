package co.com.datatools.c2.ws.interceptor;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.jboss.logging.Logger;

import co.com.datatools.c2.ws.comparendo.utilidades.HelperProcesarMensaje;
import co.com.datatools.c2.ws.utilidades.enumeracion.EnumErrorWS;
import co.com.datatools.util.xss.XSSValidator;

/**
 * Clase que permite interceptar y llevar a cabo validaciones XSS para los campos de tipo cadenas de caracteres
 * 
 * @author luis.forero(2016-01-20)
 * 
 */
public class XSSValidationInterceptor extends AbstractPhaseInterceptor<Message> {

    /**
     * Nombre del atributo en las variables del mensaje entrante a extraer para el usuario autenticado.
     */
    private static final String NOMBRE_USUARIO = "nombreUsuario";
    /**
     * Siglas que identifican un tipo de dato complejo utilizado para web services WS DTO.
     */
    private static final String REGEX_WSDTO = "WSDTO";
    private static final Logger logger = Logger.getLogger(XSSValidationInterceptor.class.getName());
    /**
     * Bundle donde carga los mensajes
     */
    private static final String BUNDLE_MENSAJES = "co.com.datatools.c2.ws.bundle.Mensajes";

    public XSSValidationInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        MessageContentsList objs = MessageContentsList.getContentsList(message);

        Object object = objs.get(0);

        validarValor(object, message);
    }

    /**
     * Validacion xss de los valores encontrados
     * 
     * @param object
     *            objeto con los datos de entrada
     * @param message
     *            mensaje soap entrante
     * @throws Fault
     * @author luis.forero(2016-01-20)
     */
    public void validarValor(Object object, Message message) throws Fault {
        Class<? extends Object> class1 = object.getClass();
        Field[] declaredFields = class1.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (type.equals(String.class)) {
                try {
                    Object value = field.get(object);
                    if (value != null) {
                        if (XSSValidator.isUnsafe((String) value)) {
                            Object object2 = message.get(NOMBRE_USUARIO);
                            String errMsg = HelperProcesarMensaje.getMensaje(
                                    ResourceBundle.getBundle(BUNDLE_MENSAJES, Locale.getDefault()),
                                    EnumErrorWS.VALIDACION_XSS_ERR.getLlave());
                            HelperProcesarMensaje.escribirLogServidor((String) object2, message, errMsg);
                            throw new SecurityException(new Throwable(errMsg));
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            } else if (field.getType().getName().contains(REGEX_WSDTO)) {
                try {
                    Object value = field.get(object);
                    if (value != null) {
                        validarValor(value, message);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
            field.setAccessible(false);
        }
    }
}
