package co.com.datatools.c2.mail;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.datatools.c2.mail.dto.DocAdjuntoDTO;
import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;
import co.com.datatools.c2.mail.dto.ParametrosCorreoDTO;
import co.com.datatools.c2.mail.ejb.EnvioCorreoEJB;
import co.com.datatools.c2.mail.excepcion.EnvioCorreoException;
import co.com.datatools.c2.mail.interfaces.IEnvioCorreo;
import co.com.datatools.c2.mail.util.UtilidadesCorreo;

public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {
        // testRemoteEJB();
        // testEnvioCorreoSubsistemaMailAdjunto();
        // testEnvioCorreoParametrosAdjunto();
        testEnvioCorreoSubsistemaMailAdjunto();

    }

    @SuppressWarnings("unused")
    private static void testRemoteEJB() throws NamingException, EnvioCorreoException {

        final IEnvioCorreo ejb = lookupRemoteEJB();
        String asunto = "Prueba de Envio Correo Subsistema Email";
        String contenido = "shshshshshshs";

        String[] direccionDestino = new String[1];
        direccionDestino[0] = "luis.martinez@datatools.com.co";
        String HTML_MEDIA_TYPE = "text/plain";

        MensajeCorreoDTO mc = new MensajeCorreoDTO(direccionDestino, asunto, contenido, HTML_MEDIA_TYPE);

        ejb.enviarCorreo(mc);
    }

    @SuppressWarnings("unused")
    private static void testEnvioCorreoParametros() throws NamingException, EnvioCorreoException {

        final IEnvioCorreo ejb = lookupRemoteEJB();
        String asunto = "Prueba de Envio Correo por Parametros";
        String contenido = "shshshshshshs";
        String direccionOrigen = "luisgamartinez@gmail.com";

        String[] direccionDestino = new String[1];
        direccionDestino[0] = "luis.martinez@datatools.com.co";
        direccionDestino[1] = "ing_gaby18@hotmail.com";
        String HTML_MEDIA_TYPE = "text/plain";

        ParametrosCorreoDTO pDTO = parametrosCorreo();

        MensajeCorreoDTO mc = new MensajeCorreoDTO(direccionOrigen, direccionDestino, asunto, contenido,
                HTML_MEDIA_TYPE, pDTO);

        ejb.enviarCorreo(mc);

    }

    @SuppressWarnings("unused")
    private static void testEnvioCorreoParametrosAdjunto() throws NamingException, EnvioCorreoException, IOException {

        final IEnvioCorreo ejb = lookupRemoteEJB();
        String asunto = "Prueba de Envio Correo por Parametros";
        String contenido = "Archivos Adjuntos";
        String direccionOrigen = "luisgamartinez@gmail.com";

        String[] direccionDestino = new String[2];
        direccionDestino[0] = "luis.martinez@datatools.com.co";
        direccionDestino[1] = "ing_gaby18@hotmail.com";
        String HTML_MEDIA_TYPE = "text/plain";

        ParametrosCorreoDTO pDTO = parametrosCorreo();

        MensajeCorreoDTO mc = new MensajeCorreoDTO(direccionOrigen, direccionDestino, asunto, contenido,
                HTML_MEDIA_TYPE, pDTO);

        DocAdjuntoDTO doc = new DocAdjuntoDTO();
        String path = "D:\\resolucion.PDF";
        File ff = new File(path);
        doc.setByteArray(UtilidadesCorreo.getBytesFromFile(ff));
        doc.setNombre(ff.getName());
        mc.addDocAdjunto(doc);

        DocAdjuntoDTO doc1 = new DocAdjuntoDTO();
        String path1 = "D:\\autenticacion.png";
        File ff1 = new File(path1);
        doc1.setByteArray(UtilidadesCorreo.getBytesFromFile(ff1));
        doc1.setNombre(ff1.getName());
        mc.addDocAdjunto(doc1);

        ejb.enviarCorreo(mc);

    }

    @SuppressWarnings("unused")
    private static void testEnvioCorreoSubsistemaMailAdjunto() throws NamingException, EnvioCorreoException,
            IOException {

        final IEnvioCorreo ejb = lookupRemoteEJB();
        String asunto = "Prueba de Envio Correo Subsistema Email";
        String contenido = "shshshshshshs";

        String[] direccionDestino = new String[2];
        direccionDestino[0] = "luis.martinez@datatools.com.co";
        direccionDestino[1] = "ing_gaby18@hotmail.com";
        String HTML_MEDIA_TYPE = "text/plain";

        MensajeCorreoDTO mc = new MensajeCorreoDTO(direccionDestino, asunto, contenido, HTML_MEDIA_TYPE);

        DocAdjuntoDTO doc = new DocAdjuntoDTO();
        String path = "D:\\resolucion.PDF";
        File ff = new File(path);
        doc.setByteArray(UtilidadesCorreo.getBytesFromFile(ff));
        doc.setNombre(ff.getName());
        mc.addDocAdjunto(doc);

        DocAdjuntoDTO doc1 = new DocAdjuntoDTO();
        String path1 = "D:\\autenticacion.png";
        File ff1 = new File(path1);
        doc1.setByteArray(UtilidadesCorreo.getBytesFromFile(ff1));
        doc1.setNombre(ff1.getName());
        mc.addDocAdjunto(doc1);

        ejb.enviarCorreo(mc);

    }

    private static IEnvioCorreo lookupRemoteEJB() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        final Context context = new InitialContext(jndiProperties);

        final String appName = "Circulemo2EAR-0.2.0-SNAPSHOT";
        final String moduleName = "Circulemos2PWEB-0.2.0-SNAPSHOT";
        final String distinctName = "";
        final String beanName = EnvioCorreoEJB.class.getSimpleName();

        final String viewClassName = IEnvioCorreo.class.getName();
        System.out.println("Looking EJB via JNDI ");
        System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!"
                + viewClassName);

        return (IEnvioCorreo) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName
                + "!" + viewClassName);
    }

    public static ParametrosCorreoDTO parametrosCorreo() {
        ParametrosCorreoDTO pDTO = new ParametrosCorreoDTO();
        pDTO.setEmailAccount("recaudosimit@gmail.com");
        pDTO.setEmailPassword("Datat66ls");
        pDTO.setSmtpHostName("smtp.gmail.com");
        pDTO.setSmtpPort("587");

        return pDTO;
    }

}
