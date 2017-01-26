package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 14:18:51 COT 2016
 */
public class DetalleNotificacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private LogEnvioCorreoDTO logEnvioCorreo;
    private LogEjecucionWSDTO logEjecucionWS;
    private NotificacionDTO notificacion;
    private String codigoSeguimientoExt;
    private String codigoSeguimientoInt;
    private Integer enviadoExt;
    private String estadoExt;
    private Date fechaEnvioTercero;
    private Date fechaNotificacion;
    private Date fechaModificacion;
    private UsuarioPersonaDTO usuario;

    // --- Constructor
    public DetalleNotificacionDTO() {
    }

    public DetalleNotificacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogEnvioCorreoDTO getLogEnvioCorreo() {
        return this.logEnvioCorreo;
    }

    public void setLogEnvioCorreo(LogEnvioCorreoDTO logEnvioCorreo) {
        this.logEnvioCorreo = logEnvioCorreo;
    }

    public LogEjecucionWSDTO getLogEjecucionWS() {
        return this.logEjecucionWS;
    }

    public void setLogEjecucionWS(LogEjecucionWSDTO logEjecucionWS) {
        this.logEjecucionWS = logEjecucionWS;
    }

    public NotificacionDTO getNotificacion() {
        return this.notificacion;
    }

    public void setNotificacion(NotificacionDTO notificacion) {
        this.notificacion = notificacion;
    }

    public String getCodigoSeguimientoExt() {
        return codigoSeguimientoExt;
    }

    public void setCodigoSeguimientoExt(String codigoSeguimientoExt) {
        this.codigoSeguimientoExt = codigoSeguimientoExt;
    }

    public String getCodigoSeguimientoInt() {
        return codigoSeguimientoInt;
    }

    public void setCodigoSeguimientoInt(String codigoSeguimientoInt) {
        this.codigoSeguimientoInt = codigoSeguimientoInt;
    }

    public Integer getEnviadoExt() {
        return enviadoExt;
    }

    public void setEnviadoExt(Integer enviadoExt) {
        this.enviadoExt = enviadoExt;
    }

    public String getEstadoExt() {
        return estadoExt;
    }

    public void setEstadoExt(String estadoExt) {
        this.estadoExt = estadoExt;
    }

    public Date getFechaEnvioTercero() {
        return fechaEnvioTercero;
    }

    public void setFechaEnvioTercero(Date fechaEnvioTercero) {
        this.fechaEnvioTercero = fechaEnvioTercero;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }
}