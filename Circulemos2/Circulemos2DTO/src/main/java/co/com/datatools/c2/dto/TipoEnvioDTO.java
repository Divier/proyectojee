package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoEnvioDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoTipoEnvio;
    private String nombre;

    // Constructors Declaration

    public TipoEnvioDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoTipoEnvio() {
        return codigoTipoEnvio;
    }

    public void setCodigoTipoEnvio(Integer codigoTipoEnvio) {
        this.codigoTipoEnvio = codigoTipoEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Finish the class
}