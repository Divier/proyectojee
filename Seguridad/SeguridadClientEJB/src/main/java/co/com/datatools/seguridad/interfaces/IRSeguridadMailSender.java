package co.com.datatools.seguridad.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.mail.dto.DocAdjuntoDTO;

/**
 * @author rodrigo.cruz
 * 
 */
@Remote
public interface IRSeguridadMailSender {

    /**
     * @param direccionDestino
     * @param asunto
     * @param contenido
     * @param adjunto
     */
    public void publicarCorreo(String[] direccionDestino, String asunto, String contenido, DocAdjuntoDTO adjunto);
}
