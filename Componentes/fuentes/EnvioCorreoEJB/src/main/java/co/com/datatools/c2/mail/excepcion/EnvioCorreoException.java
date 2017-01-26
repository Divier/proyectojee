package co.com.datatools.c2.mail.excepcion;

import javax.ejb.ApplicationException;

/**
 * Excepcion para manejar errores de Envio de Correo
 * 
 * @author luis.martinez
 * 
 */
@ApplicationException(rollback = true)
public class EnvioCorreoException extends Exception {
    private static final long serialVersionUID = 1L;

    public EnvioCorreoException(String message) {
        super(message);
    }
}
