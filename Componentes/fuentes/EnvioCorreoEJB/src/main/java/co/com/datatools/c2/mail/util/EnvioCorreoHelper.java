package co.com.datatools.c2.mail.util;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.mail.dto.ParametrosCorreoDTO;
import co.com.datatools.c2.mail.excepcion.EnvioCorreoException;

/**
 * Helper para validaciones al momento de enviar un correo
 */
public class EnvioCorreoHelper {

    /**
     * Expresión regular para validar cuentas de correo electrónico
     */
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Este metodo realiza la validacion de los parametros de la cuenta de Correo electronico
     * 
     * @param mensajeCorreo
     *            Objeto a validar
     * @return Lista errores de parametros de correo
     */
    public static List<EnumErrorParametrosCorreo> erroresParametrosCorreo(ParametrosCorreoDTO mensajeCorreo) {

        final List<EnumErrorParametrosCorreo> erroresCorreo = new ArrayList<EnumErrorParametrosCorreo>();

        if (mensajeCorreo.getEmailAccount() == null || mensajeCorreo.getEmailAccount().equals("")) {
            erroresCorreo.add(EnumErrorParametrosCorreo.CUENTA_CORREO_VACIO);
        } else {
            boolean bValidarCorreo = esCorreoValido(mensajeCorreo.getEmailAccount());
            if (!bValidarCorreo) {
                erroresCorreo.add(EnumErrorParametrosCorreo.CUENTA_CORREO_NO_VALIDA);
            }
        }

        if (mensajeCorreo.getEmailPassword() == null || mensajeCorreo.getEmailPassword().equals("")) {
            erroresCorreo.add(EnumErrorParametrosCorreo.PASSWORD_CORREO_VACIO);
        }

        if (mensajeCorreo.getSmtpHostName() == null || mensajeCorreo.getSmtpHostName().equals("")) {
            erroresCorreo.add(EnumErrorParametrosCorreo.SMTP_HOST_NAME_VACIO);
        }

        if (mensajeCorreo.getSmtpPort() == null || mensajeCorreo.getSmtpPort().equals("")) {
            erroresCorreo.add(EnumErrorParametrosCorreo.SMTP_PORT_VACIO);
        } else {
            if (!isNumeric(mensajeCorreo.getSmtpPort())) {
                erroresCorreo.add(EnumErrorParametrosCorreo.SMTP_PORT_NO_VALIDO);
            }
        }
        return erroresCorreo;
    }

    /**
     * Este metodo realiza la validacion de los parametros de correo ingresados
     * 
     * @param mensajeCorreo
     *            Objeto que contiene los parametros de correo
     * @return booleano que indica si la validacion fue exitosa de lo contario lanza una excepcion con el mensaje asociado al error
     * @throws EnvioCorreoException
     */
    public static boolean validacionParametrosCorreo(ParametrosCorreoDTO mensajeCorreo) throws EnvioCorreoException {

        boolean bValidacionExitosa = true;

        if (mensajeCorreo.getEmailAccount() == null || mensajeCorreo.getEmailAccount().equals("")) {
            throw new EnvioCorreoException(EnumErrorParametrosCorreo.CUENTA_CORREO_VACIO.getDescripcion());
        } else {
            boolean bValidarCorreo = esCorreoValido(mensajeCorreo.getEmailAccount());
            if (!bValidarCorreo) {
                throw new EnvioCorreoException(EnumErrorParametrosCorreo.CUENTA_CORREO_NO_VALIDA.getDescripcion());
            }
        }

        if (mensajeCorreo.getEmailPassword() == null || mensajeCorreo.getEmailPassword().equals("")) {
            throw new EnvioCorreoException(EnumErrorParametrosCorreo.PASSWORD_CORREO_VACIO.getDescripcion());
        }

        if (mensajeCorreo.getSmtpHostName() == null || mensajeCorreo.getSmtpHostName().equals("")) {
            throw new EnvioCorreoException(EnumErrorParametrosCorreo.SMTP_HOST_NAME_VACIO.getDescripcion());
        }

        if (mensajeCorreo.getSmtpPort() == null || mensajeCorreo.getSmtpPort().equals("")) {
            throw new EnvioCorreoException(EnumErrorParametrosCorreo.SMTP_PORT_VACIO.getDescripcion());
        } else {
            if (!isNumeric(mensajeCorreo.getSmtpPort())) {
                throw new EnvioCorreoException(EnumErrorParametrosCorreo.SMTP_PORT_NO_VALIDO.getDescripcion());
            }
        }
        return bValidacionExitosa;
    }

    /**
     * valida si la direccion de correo electrónica es valida
     * 
     * @param correoElectronico
     * @return booleano que indica si la direccion es valida
     */
    public static boolean esCorreoValido(String correoElectronico) {
        return correoElectronico.matches(EnvioCorreoHelper.EMAIL_REGEX);
    }

    /**
     * Valida si el valor ingresado es un numero
     * 
     * @param cadena
     * @return booleano que indica si es un numero o no
     */
    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
