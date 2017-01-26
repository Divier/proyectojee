package co.com.datatools.c2.ws.comparendo.utilidades;

import co.com.datatools.c2.log.ILogueable;

/**
 * Estructura del log para la recepcion de un comparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class LogServiciosSogit implements ILogueable {

    private static final long serialVersionUID = 1L;

    /**
     * CONCATENACION DEL CODIGO DE ERROR JUNTO CON UNA DESCRIPCION SI EXISTE
     */
    private String estadoTransaccion;
    /**
     * Mensaje detallado
     */
    private String detalle;

    public LogServiciosSogit() {

    }

    public LogServiciosSogit(String numeroComparendo, String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    public LogServiciosSogit(String numeroComparendo, String estadoTransaccion, String detalle) {
        this.estadoTransaccion = estadoTransaccion;
        this.detalle = detalle;
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
