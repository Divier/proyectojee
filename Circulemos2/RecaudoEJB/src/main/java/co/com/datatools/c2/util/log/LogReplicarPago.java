/**
 * 
 */
package co.com.datatools.c2.util.log;

import co.com.datatools.c2.log.ILogueable;

/**
 * Estructura del log para la replicar pago
 * 
 * @author julio.pinzon(2016-04-21)
 * 
 */
public class LogReplicarPago implements ILogueable {

    private static final long serialVersionUID = 1L;

    private String numeroRecaudo;
    /**
     * CONCATENACION DEL CODIGO DE ERROR JUNTO CON UNA DESCRIPCION SI EXISTE
     */
    private String estadoTransaccion;
    /**
     * Mensaje detallado
     */
    private String detalle;

    public LogReplicarPago() {

    }

    public LogReplicarPago(String numeroRecaudo, String estadoTransaccion) {
        this.numeroRecaudo = numeroRecaudo;
        this.estadoTransaccion = estadoTransaccion;
    }

    public String getNumeroRecaudo() {
        return numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
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
