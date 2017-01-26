package co.com.datatools.c2.mail.dto;

import java.io.Serializable;

/**
 * Esta clase contiene los parametros de configuracion de correo empleados para realizar la autenticacion de la clase de origen usada para cualquier
 * envio de correo
 * 
 * @author luis.martinez
 * 
 */
public class ParametrosCorreoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Nombre del host SMTP
     */
    private String smtpHostName;
    /**
     * Puerto SMTP
     */
    private String smtpPort;
    /**
     * Nombre de la cuenta de correo
     */
    private String emailAccount;
    /**
     * Contraseña de la cuenta de correo
     */
    private String emailPassword;
    /**
     * Parametro que activa el depurador de session mail usado para ver el trafico de informacion cuando se envia un correo
     */
    private boolean showMailDebug;

    public String getSmtpHostName() {
        return smtpHostName;
    }

    public void setSmtpHostName(String smtpHostName) {
        this.smtpHostName = smtpHostName;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public boolean isShowMailDebug() {
        return showMailDebug;
    }

    public void setShowMailDebug(boolean showMailDebug) {
        this.showMailDebug = showMailDebug;
    }

    @Override
    public String toString() {
        return "ParametrosCorreoDTO [smtpHostName=" + smtpHostName + ", smtpPort=" + smtpPort + ", emailAccount="
                + emailAccount + ", emailPassword=" + emailPassword + ", showMailDebug=" + showMailDebug + "]";
    }

}
