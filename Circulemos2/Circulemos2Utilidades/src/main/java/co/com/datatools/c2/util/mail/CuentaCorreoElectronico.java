package co.com.datatools.c2.util.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.util.mail.EmailValidator;

/**
 * Método que se conecta al correo dado busca los archivos adjuntos y los almacena el la ruta especificada.
 * 
 * @author camilo.sierra
 * @since 02-Sep-2013
 */
public class CuentaCorreoElectronico {

    /**
     * Expresión regular para validar cuentas de correo electrónico
     */
    private static final String EMAIL_REGEX = "(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)";

    // private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /**
     * Descarga los archivos adjuntos de todos los mensajes nuevos encontrados en la carpeta INBOX del correo parametrizado en el archivo de
     * propiedades.
     * 
     * @param usuario
     *            usuario de la cuenta de correo
     * @param clave
     *            clave de la cuenta de correo
     * @param rutaDescarga
     *            ruta donde se descargarán los archivos adjuntos
     * @param popHost
     *            host de la cuenta de correo
     * @param protocolo
     *            protocolo a utilizar
     * @throws MessagingException
     *             si hay problemas con la descarga
     * @throws IOException
     *             si hay problemas con la descarga
     */
    public static void descargueAdjuntos(String usuario, String clave, String rutaDescarga, String popHost,
            String protocolo) throws MessagingException, IOException {

        Properties props = System.getProperties();
        // Obtener una sesión con las propiedades anteriormente definidas
        Session sesion = Session.getDefaultInstance(props, null);
        // Capturar las excepciones

        // Crear un Store indicando el protocolo de acceso y nos conectamos a él
        Store store = sesion.getStore(protocolo);
        // store.connect(popHost, 995, usuario, clave);
        store.connect(popHost, usuario, clave);

        // TODO Se podría parametrizar el folder??!
        // Crear un Folder y abrimos la carpeta INBOX en modo SOLO LECTURA
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);

        // mensajes.copyMessages(mensaje,trashFolder);
        Message[] mensajes = folder.getMessages();
        for (int e = 1; e <= mensajes.length; e++) {
            // System.out.println("lee emensaje No: " + e);
            // Obtener el mensaje número 1 de los almacenados en el Folder
            Message mensaje = folder.getMessage(e);
            // Obtener el contenido del mensaje y vemos de que tipo es
            Object o = mensaje.getContent();

            // Si el mensaje es de texto sin adjuntos, ya sea texto HTML o
            // texto plano, el contenido será de tipo String
            if (o instanceof String) {
                // Si entra por aquí se trata de texto HTML
                if (mensaje.getContentType().indexOf("text/html") != -1) {
                    DataHandler dh = mensaje.getDataHandler();
                    File temp = new File(rutaDescarga, "contenido.html");
                    try (OutputStream os = new FileOutputStream(temp.getPath())) {
                        dh.writeTo(os);
                    }

                }
                // else {
                // Si entra por aquí se trata de texto plano
                // System.out.println(o);
                // }
            }
            // Si entra por aquí es que se trata de un mensaje con adjunto/s
            // o HTML con imágenes embebidas en el mensaje
            else if (o instanceof Multipart) {
                // Se sabe que el contenido del mensaje es de tipo Multipart,
                // así que se le hace un casting para obtener un objeto de este tipo
                Multipart mp = (Multipart) o;
                // Recorrer todos los Part que componen el mensaje
                int numPart = mp.getCount();
                for (int i = 0; i < numPart; i++) {
                    // Con cada parte del mensaje hay que ver la disposición que tiene
                    Part part = mp.getBodyPart(i);
                    String disposition = part.getDisposition();
                    // Si la disposición es null probablemente se trate del contenido en sí del mensaje
                    if (disposition == null) {
                        if (part.isMimeType("multipart/alternative") || part.isMimeType("text/plain")) {
                            // String cuerpoMensaje;
                            if (part.isMimeType("multipart/alternative")) {
                                // Multipart mp2 = (Multipart) part.getContent();
                                // Part part2 = mp2.getBodyPart(0);
                                // cuerpoMensaje = (String) part2.getContent();
                            } else {
                                // cuerpoMensaje = (String) part.getContent();
                            }

                        } else {
                            // Si entra por aquí se trataría de la parte del texto de un correo HTML con imágenes embebidas
                            if (part.getContentType().indexOf("text/html") != -1) {
                                MimeBodyPart mbp = (MimeBodyPart) part;
                                File temp = new File(rutaDescarga, "contenido.html");
                                mbp.saveFile(temp.getPath());
                            }
                        }
                    }
                    // Si entra por aquí es que se trata de un adjunto o de una imagen embebida en el mensaje
                    else if (StringUtils.isNotBlank(disposition)
                            && (disposition.equalsIgnoreCase(Part.ATTACHMENT) || (disposition
                                    .equalsIgnoreCase(Part.INLINE)))) {
                        String nombrePart = part.getFileName();
                        if (nombrePart == null)
                            nombrePart = "adjunto" + i;
                        // Procesar el adjunto o imagen
                        MimeBodyPart mbp = (MimeBodyPart) part;
                        File temp = new File(rutaDescarga, nombrePart);
                        mbp.saveFile(temp.getPath());
                    }

                }

            }

            mensaje.setFlag(Flags.Flag.DELETED, true);
        }

        folder.close(true);
        store.close();

    }

    /**
     * Retorna true si la cuenta de correo electrónico indicada es válida acorde a la expresión regular manejada para las cuentas de correo
     * electrónico de circulemos.
     * 
     * @param correoElectronico
     *            cuenta de correo electrónico a validar
     * @return true si es válida
     * @deprecated usar {@link EmailValidator}
     */
    @Deprecated
    public static boolean esCorreoValido(String correoElectronico) {
        return correoElectronico.matches(CuentaCorreoElectronico.EMAIL_REGEX);
    }

}