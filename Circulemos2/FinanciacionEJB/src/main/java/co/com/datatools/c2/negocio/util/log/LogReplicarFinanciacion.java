/**
 * 
 */
package co.com.datatools.c2.negocio.util.log;

import co.com.datatools.c2.log.ILogueable;

/**
 * Estructura del log para la replicar financiacion
 * 
 * @author julio.pinzon(2016-05-17)
 * 
 */
public class LogReplicarFinanciacion implements ILogueable {

    private static final long serialVersionUID = 1L;

    private String numeroFinanciacion;
    /**
     * CONCATENACION DEL CODIGO DE ERROR JUNTO CON UNA DESCRIPCION SI EXISTE
     */
    private String estadoTransaccion;
    /**
     * Mensaje detallado
     */
    private String detalle;

    public LogReplicarFinanciacion() {

    }

    public LogReplicarFinanciacion(String numeroFinanciacion, String estadoTransaccion) {
        this.numeroFinanciacion = numeroFinanciacion;
        this.estadoTransaccion = estadoTransaccion;
    }

    public String getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(String numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
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
