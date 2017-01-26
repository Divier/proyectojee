package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the web_services database table.
 * 
 */
@Entity
@Table(name = "log_ejecucion_ws")
@NamedQueries({ @NamedQuery(name = "LogEjecucionWS.findAll", query = "SELECT l FROM LogEjecucionWS l") })
public class LogEjecucionWS implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_ejecucion_ws")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_respuesta_web_services")
    private RespuestaWebService respuestaWebService;

    @Column(name = "mensaje_enviado")
    private String mensajeEnviado;

    @Basic(optional = false)
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Column(name = "ruta_envio")
    private String rutaEnvio;

    @Column(name = "mensaje_recibido")
    private String mensajeRecibido;

    @Basic(optional = false)
    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;

    @Column(name = "observacion")
    private String observacion;

    public LogEjecucionWS() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RespuestaWebService getRespuestaWebService() {
        return respuestaWebService;
    }

    public void setRespuestaWebService(RespuestaWebService respuestaWebService) {
        this.respuestaWebService = respuestaWebService;
    }

    public String getMensajeEnviado() {
        return mensajeEnviado;
    }

    public void setMensajeEnviado(String mensajeEnviado) {
        this.mensajeEnviado = mensajeEnviado;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getRutaEnvio() {
        return rutaEnvio;
    }

    public void setRutaEnvio(String rutaEnvio) {
        this.rutaEnvio = rutaEnvio;
    }

    public String getMensajeRecibido() {
        return mensajeRecibido;
    }

    public void setMensajeRecibido(String mensajeRecibido) {
        this.mensajeRecibido = mensajeRecibido;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}