package co.com.datatools.c2.mail.ejb;

import java.io.Serializable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;
import co.com.datatools.c2.mail.util.PublicarMensajeCorreo;

/**
 * Esta clase se encarga de realizar el envio de mensajes de correo a traves de la cola configurada en el subsistema de messaging
 * 
 * @author luis.martinez
 */
@MessageDriven(name = "EnvioCorreoMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = PublicarMensajeCorreo.ENVIO_CORREO_QUEUE_JNDI_NAME),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class EnvioCorreoMDB implements MessageListener {

    private final static Logger logger = Logger.getLogger(EnvioCorreoMDB.class.getName());

    @EJB
    private EnvioCorreoEJB envioCorreo;

    public void onMessage(Message message) {

        // Ensure the message is specified
        if (message == null) {
            throw new IllegalArgumentException("Mensaje debe ser especificado");
        }
        // Ensure the message is in expected form
        final ObjectMessage objMessage;
        if (message instanceof ObjectMessage) {
            objMessage = (ObjectMessage) message;
        } else {
            throw new IllegalArgumentException("Mensaje debe ser del tipo " + ObjectMessage.class.getName());
        }
        // Extract out the embedded status update
        final Serializable obj;
        try {
            obj = objMessage.getObject();
        } catch (final JMSException jmse) {
            throw new IllegalArgumentException("No es posible obtener el contenido del mensaje " + objMessage);
        }

        final MensajeCorreoDTO mensajeCorreo;
        if (obj instanceof MensajeCorreoDTO) {
            mensajeCorreo = (MensajeCorreoDTO) obj;
            logger.info("Se recive objeto para procesamiento de correo");
            envioCorreo.enviarCorreo(mensajeCorreo);
        } else {
            throw new IllegalArgumentException("El tipo del contenido debe ser " + MensajeCorreoDTO.class.getName()
                    + "; en lugar de " + obj.getClass().getName());
        }
    }

}
