package co.com.datatools.c2.dto.common;

import java.io.Serializable;

import co.com.datatools.c2.enumeracion.EnumRespuestaWS;

/**
 * Contiene el resultado al ser cliente de un WebService
 * 
 * @author luis.forero(2016-03-29)
 * 
 */
public class RespuestaNotificacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Contiene el codigo de respuesta recibido
     */
    private String codigoRespuesta;
    /**
     * Contiene el mensaje de respuesta recibido
     */
    private String mensaje;
    /**
     * Detalle de la respuesta.
     */
    private String detalle;
    /**
     * Indica el tipo de resultado obtenido por el WS.
     */
    private EnumRespuestaWS resultado;

    public RespuestaNotificacionDTO() {
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
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

    public EnumRespuestaWS getResultado() {
        return resultado;
    }

    public void setResultado(EnumRespuestaWS resultado) {
        this.resultado = resultado;
    }

}
