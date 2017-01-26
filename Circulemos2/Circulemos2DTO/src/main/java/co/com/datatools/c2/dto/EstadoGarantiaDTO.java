package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class EstadoGarantiaDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoEstadoGarantia;
    private String nombreEstadoGarantia;

    // Constructors Declaration

    public EstadoGarantiaDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoEstadoGarantia() {
        return codigoEstadoGarantia;
    }

    public void setCodigoEstadoGarantia(Integer codigoEstadoGarantia) {
        this.codigoEstadoGarantia = codigoEstadoGarantia;
    }

    public String getNombreEstadoGarantia() {
        return nombreEstadoGarantia;
    }

    public void setNombreEstadoGarantia(String nombreEstadoGarantia) {
        this.nombreEstadoGarantia = nombreEstadoGarantia;
    }

    // Finish the class
}