package co.com.datatools.c2.ws.comparendo.utilidades;

import co.com.datatools.c2.log.ILogueable;

/**
 * Estructura del log para la recepcion de un comparendo
 * 
 * @author luis.forero(2015-11-18)
 * 
 */
public class LogRecibirComparendo implements ILogueable {

    private static final long serialVersionUID = 1L;

    private String numeroComparendo;
    /**
     * CONCATENACION DEL CODIGO DE ERROR JUNTO CON UNA DESCRIPCION SI EXISTE
     */
    private String estadoTransaccion;
    /**
     * Mensaje detallado
     */
    private String detalle;

    public LogRecibirComparendo() {

    }

    public LogRecibirComparendo(String numeroComparendo, String estadoTransaccion) {
        this.numeroComparendo = numeroComparendo;
        this.estadoTransaccion = estadoTransaccion;
    }

    public LogRecibirComparendo(String numeroComparendo, String estadoTransaccion, String detalle) {
        this.numeroComparendo = numeroComparendo;
        this.estadoTransaccion = estadoTransaccion;
        this.detalle = detalle;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
