package co.com.datatools.c2.mail.ejb;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.jboss.logging.Logger;

import co.com.datatools.c2.mail.dto.DocAdjuntoDTO;
import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;
import co.com.datatools.c2.mail.excepcion.EnvioCorreoException;
import co.com.datatools.c2.mail.interfaces.IEnvioCorreo;
import co.com.datatools.c2.mail.util.EnvioCorreoHelper;

@Stateless(name = "EnvioCorreoEJB")
@LocalBean
public class EnvioCorreoEJB implements IEnvioCorreo {

    @Resource(mappedName = "java:jboss/mail/Default")
    private Session mailSession;

    private final static Logger logger = Logger.getLogger(EnvioCorreoEJB.class.getName());
    public static boolean EMAIL_DEBUG = true;

    /**
     * Este metodo se encarga de enviar un mensaje de correo empleando el subsistema mail del servidor de aplicaciones Jboss
     * 
     * @param mensajeCorreo
     *            Objeto que contiene los parametros de configuracion que conlleva el mensaje de correo
     * @throws MessagingException
     */
    private void enviarMensajeCorreo(MensajeCorreoDTO mensajeCorreo) throws MessagingException {
        final String[] toAddrs = mensajeCorreo.getDireccionesEnvio();
        final String subject = mensajeCorreo.getAsunto();
        final String body = mensajeCorreo.getTextoCorreo();
        final String mimeType = mensajeCorreo.getMimeType();
        final String charset = StandardCharsets.ISO_8859_1.name();

        MimeMessage msg = new MimeMessage(mailSession);

        Address[] addressTo = new InternetAddress[toAddrs.length];
        for (int i = 0; i < toAddrs.length; i++) {
            addressTo[i] = new InternetAddress(toAddrs[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        msg.setSubject(subject, charset);

        /**
         * Adicionar los archivos Adjuntos
         */
        if (mensajeCorreo.getDocAdjuntosList() != null) {
            Multipart multip = new MimeMultipart("related");
            MimeBodyPart mbpCuerpo = new MimeBodyPart();
            mbpCuerpo.setContent(mensajeCorreo.getTextoCorreo(), mensajeCorreo.getMimeType());
            multip.addBodyPart(mbpCuerpo);

            Iterator<DocAdjuntoDTO> iter = mensajeCorreo.getDocAdjuntosList().iterator();
            while (iter.hasNext()) {
                DocAdjuntoDTO doc = (DocAdjuntoDTO) iter.next();
                ByteArrayDataSource bads = new ByteArrayDataSource(doc.getByteArray(), "application/octet-stream");
                MimeBodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(bads));
                if (doc.isImagenEmbebida()) {
                    adjunto.setDisposition("inline");
                } else {
                    adjunto.setFileName(doc.getNombre());
                }
                String alias = doc.getAlias();
                if (alias != null && !"".equals(alias)) {
                    adjunto.setHeader("Content-ID", "<" + alias + ">");
                }
                /**
                 * Se añade el archivo adjunto
                 */
                multip.addBodyPart(adjunto);
            }
            msg.setContent(multip);
        } else {
            msg.setContent(body, mimeType);
        }

        Transport.send(msg);
    }

    /**
     * Este metodo se encarga de enviar un mensaje de correo ingresando dinamicamente los parametros de configuracion empleados para el envio de
     * correo (usuario, contraseña, puerto y nombre del host SMTP)
     * 
     * @param mensajeCorreo
     *            Objeto que contiene los parametros de configuracion que conlleva el mensaje de correo
     * @throws MessagingException
     */
    private void enviarMensajeCorreoParametros(MensajeCorreoDTO mensajeCorreo) throws MessagingException {
        final String[] toAddrs = mensajeCorreo.getDireccionesEnvio();
        final String[] toAddrsCC = mensajeCorreo.getDireccionesEnvio();
        final String fromAddr = mensajeCorreo.getDireccionRemitente();
        final String subject = mensajeCorreo.getAsunto();
        final String body = mensajeCorreo.getTextoCorreo();
        final String mimeType = mensajeCorreo.getMimeType();

        Session session = abrirSesionParametrosCorreo(mensajeCorreo);
        MimeMessage msg = new MimeMessage(session);

        Address addressFrom = new InternetAddress(fromAddr);
        msg.setFrom(addressFrom);

        Address[] addressTo = new InternetAddress[toAddrs.length];
        for (int i = 0; i < toAddrs.length; i++) {
            addressTo[i] = new InternetAddress(toAddrs[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        Address[] addressToCC = new InternetAddress[toAddrsCC.length];
        for (int i = 0; i < toAddrsCC.length; i++) {
            addressToCC[i] = new InternetAddress(toAddrsCC[i]);
        }
        msg.setRecipients(Message.RecipientType.CC, addressToCC);

        msg.setSubject(subject);

        /**
         * Adicionar los archivos Adjuntos
         */
        if (mensajeCorreo.getDocAdjuntosList() != null) {
            Multipart multip = new MimeMultipart("related");
            MimeBodyPart mbpCuerpo = new MimeBodyPart();
            mbpCuerpo.setContent(mensajeCorreo.getTextoCorreo(), mensajeCorreo.getMimeType());
            multip.addBodyPart(mbpCuerpo);

            Iterator<DocAdjuntoDTO> iter = mensajeCorreo.getDocAdjuntosList().iterator();
            while (iter.hasNext()) {
                DocAdjuntoDTO doc = (DocAdjuntoDTO) iter.next();
                ByteArrayDataSource bads = new ByteArrayDataSource(doc.getByteArray(), "application/octet-stream");
                MimeBodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(bads));
                if (doc.isImagenEmbebida()) {
                    adjunto.setDisposition("inline");
                } else {
                    adjunto.setFileName(doc.getNombre());
                }
                String alias = doc.getAlias();
                if (alias != null && !"".equals(alias)) {
                    adjunto.setHeader("Content-ID", "<" + alias + ">");
                }
                /**
                 * Se añade el archivo adjunto
                 */
                multip.addBodyPart(adjunto);
            }
            msg.setContent(multip);
        } else {
            msg.setContent(body, mimeType);
        }

        Transport.send(msg);
    }

    /**
     * Este metodo carga la configuracion de la Session mail usada para el envio de un correo
     * 
     * @param mensajeCorreo
     *            Objeto que contiene los parametros de configuracion que conlleva el mensaje de correo
     * @return Objeto de Tipo Session Mail
     */
    private Session abrirSesionParametrosCorreo(MensajeCorreoDTO mensajeCorreo) {
        Session session = null;

        try {

            final String SMTP_USERNAME_EMAIL = mensajeCorreo.getParametrosCorreo().getEmailAccount();
            final String SMTP_PASSWORD_EMAIL = mensajeCorreo.getParametrosCorreo().getEmailPassword();

            /**
             * Carga los parametros de configuración para el envio del mensaje por SMTPS
             */
            Properties props = new Properties();
            props.put("mail.smtp.host", mensajeCorreo.getParametrosCorreo().getSmtpHostName());
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", mensajeCorreo.getParametrosCorreo().getSmtpPort());
            props.put("mail.smtp.starttls.enable", "true");

            /**
             * Se configura la autenticacion contra el SMTP
             */

            session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTP_USERNAME_EMAIL, SMTP_PASSWORD_EMAIL);
                }
            });

            session.setDebug(EMAIL_DEBUG);
            session.setDebug(true);
        } catch (Exception e) {
            logger.error("EnvioCorreoEJB:abrirSesionParametrosCorreo -> Error abrir session: ", e);
        }
        return session;
    }

    @Override
    public void enviarCorreo(MensajeCorreoDTO mensajeCorreo) {
        logger.debug("EnvioCorreoEJB.enviarCorreo()");
        logger.info("Enviando Correo " + mensajeCorreo.toString());
        try {
            if (mensajeCorreo.getParametrosCorreo() == null) {
                enviarMensajeCorreo(mensajeCorreo);
            } else {
                boolean bValidacion;
                bValidacion = EnvioCorreoHelper.validacionParametrosCorreo(mensajeCorreo.getParametrosCorreo());
                if (bValidacion) {
                    enviarMensajeCorreoParametros(mensajeCorreo);
                }
            }

        } catch (MessagingException e) {
            logger.error("Error - EnvioCorreoEJB.enviarCorreo()", e);
        } catch (EnvioCorreoException e1) {
            logger.error("Error - Parametros Incorrectos Cuenta Correo - EnvioCorreoEJB.enviarCorreo()", e1);
        }
    }
}
