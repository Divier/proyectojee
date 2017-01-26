package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Fri Aug 12 17:48:56 COT 2016
 */
public class LogEjecucionWSDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private RespuestaWebServiceDTO respuestaWebService;
    private String mensajeEnviado;
    private Date fechaEnvio;
    private String rutaEnvio;
    private String mensajeRecibido;
    private Date fechaRecepcion;
    private String observacion;

    // --- Constructor
    public LogEjecucionWSDTO() {
    }

    public LogEjecucionWSDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RespuestaWebServiceDTO getRespuestaWebService() {
        return this.respuestaWebService;
    }

    public void setRespuestaWebService(RespuestaWebServiceDTO respuestaWebService) {
        this.respuestaWebService = respuestaWebService;
    }

    public String getMensajeEnviado() {
        return this.mensajeEnviado;
    }

    public void setMensajeEnviado(String mensajeEnviado) {
        this.mensajeEnviado = mensajeEnviado;
    }

    public Date getFechaEnvio() {
        return this.fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getRutaEnvio() {
        return this.rutaEnvio;
    }

    public void setRutaEnvio(String rutaEnvio) {
        this.rutaEnvio = rutaEnvio;
    }

    public String getMensajeRecibido() {
        return this.mensajeRecibido;
    }

    public void setMensajeRecibido(String mensajeRecibido) {
        this.mensajeRecibido = mensajeRecibido;
    }

    public Date getFechaRecepcion() {
        return this.fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
