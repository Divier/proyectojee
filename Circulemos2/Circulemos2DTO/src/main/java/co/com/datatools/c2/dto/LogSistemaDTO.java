package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class LogSistemaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaGeneracion;
    private String mensajeLog;
    private UsuarioPersonaDTO usuario;
    private TipoLogDTO tipoLog;
    private OrganismoTransitoDTO organismoTransito;

    // --- Constructor
    public LogSistemaDTO() {
    }

    public LogSistemaDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getMensajeLog() {
        return this.mensajeLog;
    }

    public void setMensajeLog(String mensajeLog) {
        this.mensajeLog = mensajeLog;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public TipoLogDTO getTipoLog() {
        return this.tipoLog;
    }

    public void setTipoLog(TipoLogDTO tipoLog) {
        this.tipoLog = tipoLog;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}