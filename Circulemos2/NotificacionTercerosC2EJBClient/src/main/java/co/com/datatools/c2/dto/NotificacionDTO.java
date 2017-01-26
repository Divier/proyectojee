package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 11:23:17 COT 2016
 */
public class NotificacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TipoNotificacionDTO tipoNotificacion;
    private MedioNotificacionDTO medioNotificacion;
    private Date fechaSolicitud;
    private UsuarioPersonaDTO usuario;

    // --- Constructor
    public NotificacionDTO() {
    }

    public NotificacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoNotificacionDTO getTipoNotificacion() {
        return this.tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacionDTO tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public MedioNotificacionDTO getMedioNotificacion() {
        return medioNotificacion;
    }

    public void setMedioNotificacion(MedioNotificacionDTO medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }
}