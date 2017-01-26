package co.com.datatools.c2.mail.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;
import co.com.datatools.c2.mail.excepcion.EnvioCorreoException;

@Remote
public interface IEnvioCorreo {

    /**
     * Este metodo se encarga de realizar el envio de correo de acuerdo a los parametros de configuracion dados por el cliente
     * 
     * @param mensajeCorreo
     *            Objeto que contiene los parametros de configuracion que conlleva el mensaje de correo
     */
    public void enviarCorreo(MensajeCorreoDTO mensajeCorreo) throws EnvioCorreoException;

}
