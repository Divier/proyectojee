package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;

/**
 * AplicacionDTO
 * 
 * @author giovanni.velandia
 * 
 */
public class AplicacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idAplicacion;
    private String nombreAplicacion;

    public Integer getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

}
