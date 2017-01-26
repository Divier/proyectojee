package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class ContextoAplicacionVariableDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoContextoAplicacion;
    private String nombreContextoAplicacion;

    // Constructors Declaration

    public ContextoAplicacionVariableDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoContextoAplicacion() {
        return codigoContextoAplicacion;
    }

    public void setCodigoContextoAplicacion(Integer codigoContextoAplicacion) {
        this.codigoContextoAplicacion = codigoContextoAplicacion;
    }

    public String getNombreContextoAplicacion() {
        return nombreContextoAplicacion;
    }

    public void setNombreContextoAplicacion(String nombreContextoAplicacion) {
        this.nombreContextoAplicacion = nombreContextoAplicacion;
    }

    // Finish the class
}