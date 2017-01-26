package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class ClaseGarantiaDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoClaseGarantia;
    private String nombreClaseGarantia;

    // Constructors Declaration

    public ClaseGarantiaDTO() {

    }

    // Start Methods Declaration

    public String getNombreClaseGarantia() {
        return nombreClaseGarantia;
    }

    public void setNombreClaseGarantia(String nombreClaseGarantia) {
        this.nombreClaseGarantia = nombreClaseGarantia;
    }

    public Integer getCodigoClaseGarantia() {
        return codigoClaseGarantia;
    }

    public void setCodigoClaseGarantia(Integer codigoClaseGarantia) {
        this.codigoClaseGarantia = codigoClaseGarantia;
    }

    // Finish the class
}