package co.com.datatools.seguridad.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

/**
 * Clase utilitaria para envio de correos
 * 
 * @author claudia.rodriguez
 * 
 */
public class EnvioCorreo {

    private Properties properties;

    /**
     * Carga las propiedades para el envio de correos
     */
    public void cargarPropiedades() {
        properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("user", "c2pruebas");
        properties.setProperty("password", "circulemos2");
    }

    public EnvioCorreo() {
    }

    /**
     * Envia un correo electronico al destinatario señalado con el asunto y mensaje indicados
     * 
     * @param destinatario
     * @param asunto
     *            Asunto del correo
     * @param mensaje
     *            Mensaje o cuerpo del correo en formato HTML
     * @throws NamingException
     * @throws MessagingException
     */
    public void enviarCorreoHTML(String destinatario, String asunto, String mensaje) throws MessagingException {

        cargarPropiedades();
        final String usuario = properties.getProperty("user");
        final String password = properties.getProperty("password");

        Session mailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(asunto);
        message.addRecipients(javax.mail.Message.RecipientType.TO,
                javax.mail.internet.InternetAddress.parse(destinatario, false));

        // Agregar como HTML el cuerpo del mensaje
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(mensaje, "text/html");
        MimeMultipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        message.saveChanges();

        Transport.send(message);
    }

}
