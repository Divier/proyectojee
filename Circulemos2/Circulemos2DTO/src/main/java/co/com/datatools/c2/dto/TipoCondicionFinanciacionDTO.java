package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoCondicionFinanciacionDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoCondicion;
    private String nombreCondicion;

    // Constructors Declaration

    public TipoCondicionFinanciacionDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoCondicion() {
        return codigoCondicion;
    }

    public void setCodigoCondicion(Integer codigoCondicion) {
        this.codigoCondicion = codigoCondicion;
    }

    public String getNombreCondicion() {
        return nombreCondicion;
    }

    public void setNombreCondicion(String nombreCondicion) {
        this.nombreCondicion = nombreCondicion;
    }

    // Finish the class
}