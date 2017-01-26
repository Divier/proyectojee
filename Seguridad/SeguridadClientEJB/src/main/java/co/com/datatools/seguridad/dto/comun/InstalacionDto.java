package co.com.datatools.seguridad.dto.comun;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;

/**
 * Encapsula los datos relacionados con el cambio de los parametros de configuracion de la aplicacion
 */
public class InstalacionDto implements Serializable {

    private static final long serialVersionUID = 7735345045590103641L;

    /**
     * Correo electronico del usuario SUPER-ADMIN
     */
    private String emailSuperAdmin;
    /**
     * Nombres del usuario SUPER-ADMIN
     */
    private String nombresSuperAdmin;
    /**
     * Apellidos del usuario SUPER-ADMIN
     */
    private String apellidosSuperAdmin;
    /**
     * Contraseña ingresada por el usuario SUPER-ADMIN
     */
    private String pwSuperAdmin;
    
    /**
     * Listado con los parametros de seguridad que deben ser actualizados
     */
    List<ParametroSeguridadDto> parametrosConfiguracion;

    public String getEmailSuperAdmin() {
        return emailSuperAdmin;
    }

    public void setEmailSuperAdmin(String emailSuperAdmin) {
        this.emailSuperAdmin = emailSuperAdmin;
    }

    public String getNombresSuperAdmin() {
        return nombresSuperAdmin;
    }

    public void setNombresSuperAdmin(String nombresSuperAdmin) {
        this.nombresSuperAdmin = nombresSuperAdmin;
    }

    public String getApellidosSuperAdmin() {
        return apellidosSuperAdmin;
    }

    public void setApellidosSuperAdmin(String apellidosSuperAdmin) {
        this.apellidosSuperAdmin = apellidosSuperAdmin;
    }

    public String getPwSuperAdmin() {
        return pwSuperAdmin;
    }

    public void setPwSuperAdmin(String pwSuperAdmin) {
        this.pwSuperAdmin = pwSuperAdmin;
    }   

    public List<ParametroSeguridadDto> getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    public void setParametrosConfiguracion(List<ParametroSeguridadDto> parametrosConfiguracion) {
        this.parametrosConfiguracion = parametrosConfiguracion;
    }

}
