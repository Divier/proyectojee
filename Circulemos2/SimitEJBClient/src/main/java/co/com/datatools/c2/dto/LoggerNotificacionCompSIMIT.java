package co.com.datatools.c2.dto;

import co.com.datatools.c2.log.ILogueable;

/**
 * Logger de notificaciones de comparendo SIMIT
 * 
 * @author luis.forero(2016-03-30)
 * 
 */
public class LoggerNotificacionCompSIMIT implements ILogueable {

    public enum EstadoTransaccion {
        REGISTRADO, INCONSISTENTE, RECHAZADO;
    }

    private static final long serialVersionUID = 1L;

    private String numeroComparendo;
    private String estadoTransaccion;
    private String descripcion;
    private String detalle;

    public LoggerNotificacionCompSIMIT() {
    }

    public LoggerNotificacionCompSIMIT(String numeroComparendo, String estadoTransaccion, String descripcion) {
        this.numeroComparendo = numeroComparendo;
        this.estadoTransaccion = estadoTransaccion;
        this.descripcion = descripcion;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
