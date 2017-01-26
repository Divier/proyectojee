package co.com.datatools.seguridad.util;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.c2.mail.dto.DocAdjuntoDTO;
import co.com.datatools.c2.reporte.jsf.EmailProcessor;
import co.com.datatools.seguridad.interfaces.IRSeguridadMailSender;

/**
 * Para el llamado del correo desde el componente de generar reporte
 * 
 * @author felipe.martinez
 */
@ManagedBean
@SessionScoped
public class MailExportarMB implements EmailProcessor, Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private IRSeguridadMailSender mailSender;

    @Override
    public void enviarCorreoReporte(String[] direccionDestino, String asunto, String contenido, String nombreAdjunto,
            byte[] contenidoAdjunto) {
        DocAdjuntoDTO adjunto = new DocAdjuntoDTO();
        adjunto.setNombre(nombreAdjunto);
        adjunto.setByteArray(contenidoAdjunto);

        mailSender.publicarCorreo(direccionDestino, asunto, contenido, adjunto);
    }
}
