package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase encargada de almacenar las fecha de inicio y fin para las consultas
 * 
 * @author luis.forero
 * @version 1.0 (05-11-2014)
 */
public class RangoFechasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date fechaInicio;
    private Date fechaFin;

    public RangoFechasDTO() {
    }

    public RangoFechasDTO(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

}
