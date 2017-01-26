package co.com.datatools.c2.mail.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;

/**
 * Clase utilitaria para publicar un
 * 
 * @author felipe.martinez
 * 
 */
public final class PublicarMensajeCorreo {

    private final static Logger logger = Logger.getLogger(PublicarMensajeCorreo.class.getName());

    /**
     * Nombre JNDI de la cola para publicar mensajes de correo electronico de salida
     */
    public static final String ENVIO_CORREO_QUEUE_JNDI_NAME = "java:jboss/queue/EnvioCorreoQueue";

    private Connection connection = null;
    private Session session = null;
    private Queue queue = null;

    /**
     * Constructor que inicializa un session con la cola de correos
     */
    public PublicarMensajeCorreo() {

        try {
            Context ic = new InitialContext();

            ConnectionFactory cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
            queue = (Queue) ic.lookup(ENVIO_CORREO_QUEUE_JNDI_NAME);

            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (NamingException | JMSException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crea la session de la cola de mensajes y publica el objeto recibido
     * 
     * @param contenidoMensaje
     *            objeto con los parametros de envio de un correo
     */
    public void publicarMensaje(MensajeCorreoDTO contenidoMensaje) {
        try {

            MessageProducer publisher = session.createProducer(queue);
            connection.start();
            ObjectMessage message = session.createObjectMessage();

            message.setObject(contenidoMensaje);
            publisher.send(message);

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra la conexiones iniciadas con la cola de correos
     */
    public void close() {
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            logger.error("Error cerrando conexiones cola de correo", e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
