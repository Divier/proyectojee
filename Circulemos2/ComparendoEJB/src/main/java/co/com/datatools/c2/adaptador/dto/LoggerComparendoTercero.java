package co.com.datatools.c2.adaptador.dto;

import co.com.datatools.c2.log.ILogueable;

/**
 * Log para registrar posibles errores presentados en la lectura de comparendos de terceros
 * 
 * @author luis.forero(2016-05-24)
 * 
 */
public class LoggerComparendoTercero implements ILogueable {

    private static final long serialVersionUID = 1L;
    private String numeroComparendo;
    private String codigoError;
    private String mensaje;
    private String detalle;

    /**
     * 
     */
    public LoggerComparendoTercero() {
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
