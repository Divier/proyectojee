package co.com.datatools.seguridad.ejb;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.mail.dto.DocAdjuntoDTO;
import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;
import co.com.datatools.c2.mail.util.PublicarMensajeCorreo;
import co.com.datatools.seguridad.interfaces.IRSeguridadMailSender;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;

/**
 * EJB que ofrece servicios de correo para el modulo de seguridad
 * 
 * @author rodrigo.cruz
 * 
 */
@Stateless(name = "SeguridadMailSenderEJB")
@LocalBean
public class SeguridadMailSenderEJB implements IRSeguridadMailSender {

    private final static Logger logger = Logger.getLogger(SeguridadMailSenderEJB.class.getName());
    public final static String HTML_MEDIA_TYPE = "text/html";

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    private PublicarMensajeCorreo publicador;

    @PostConstruct
    public void init() {
        publicador = new PublicarMensajeCorreo();
    }

    @PreDestroy
    public void destroy() {
        publicador.close();
    }

    @Override
    public void publicarCorreo(String[] direccionDestino, String asunto, String contenido, DocAdjuntoDTO adjunto) {
        MensajeCorreoDTO contenidoMensaje = new MensajeCorreoDTO(direccionDestino, asunto, contenido, HTML_MEDIA_TYPE);

        if (adjunto != null)
            contenidoMensaje.addDocAdjunto(adjunto);

        publicador.publicarMensaje(contenidoMensaje);

        logger.infov("Peticion de correo publicada correctamente [direccionDestino:{0}, asunto:{1}]",
                Arrays.toString(direccionDestino), asunto);
    }

}
