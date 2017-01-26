package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class EstadoSolicitudDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoEstadoSolicitud;
    private String nombreEstadoSolicitud;

    // Constructors Declaration

    public EstadoSolicitudDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoEstadoSolicitud() {
        return codigoEstadoSolicitud;
    }

    public void setCodigoEstadoSolicitud(Integer codigoEstadoSolicitud) {
        this.codigoEstadoSolicitud = codigoEstadoSolicitud;
    }

    public String getNombreEstadoSolicitud() {
        return nombreEstadoSolicitud;
    }

    public void setNombreEstadoSolicitud(String nombreEstadoSolicitud) {
        this.nombreEstadoSolicitud = nombreEstadoSolicitud;
    }

    // Finish the class
}