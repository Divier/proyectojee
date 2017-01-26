package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoDestinatarioDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoTipoDestinatario;
    private String nombre;

    // Constructors Declaration

    public TipoDestinatarioDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoTipoDestinatario() {
        return codigoTipoDestinatario;
    }

    public void setCodigoTipoDestinatario(Integer codigoTipoDestinatario) {
        this.codigoTipoDestinatario = codigoTipoDestinatario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Finish the class
}