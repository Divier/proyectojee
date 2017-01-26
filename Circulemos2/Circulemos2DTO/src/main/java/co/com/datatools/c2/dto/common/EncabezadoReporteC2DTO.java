package co.com.datatools.c2.dto.common;

import java.io.Serializable;

/**
 * Dto utilizado para el caso de uso CU_CIR20_DAT_ADM_006 - Contenido encabezado general para reportes
 * 
 * @author dixon.alvarez
 * 
 */
public class EncabezadoReporteC2DTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreOrganismo;
    private String loginUsuario;
    private String nombreUsuario;

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
