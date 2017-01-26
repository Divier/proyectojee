package co.com.datatools.c2.managed_bean.comun;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.reporte.jsf.EmailProcessor;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Para el llamado del correo desde el componente de generar reporte
 * 
 * @author felipe.martinez
 */
@ManagedBean
@SessionScoped
public class MailExportarMB extends AbstractC2ManagedBean implements EmailProcessor {

    private static final long serialVersionUID = 1L;

    @EJB
    private IRCirculemosMailSender mailSender;

    @Override
    public void enviarCorreoReporte(String[] direccionDestino, String asunto, String contenido, String nombreAdjunto,
            byte[] contenidoAdjunto) {

        ArrayList<ArchivoTransportableDTO> archivos = new ArrayList<ArchivoTransportableDTO>();
        archivos.add(new ArchivoTransportableDTO(nombreAdjunto, contenidoAdjunto));

        mailSender.publicarCorreo(direccionDestino, asunto, contenido, archivos);
    }
}
