package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class LogEnvioCorreoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private OrganismoTransitoDTO organismoTransito;
    private TipoEmailDTO tipoEmail;
    private ConfiguracionEmailDTO configuracionEmail;
    private String asuntoEmail;
    private String cuerpoEmail;
    private Boolean adjunto;
    private UsuarioPersonaDTO usuario;
    private Date fechaEnvio;
    private String tablaSolicitud;
    private Long idTablaSolicitud;

    // --- Constructor
    public LogEnvioCorreoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoEmailDTO getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(TipoEmailDTO tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public ConfiguracionEmailDTO getConfiguracionEmail() {
        return configuracionEmail;
    }

    public void setConfiguracionEmail(ConfiguracionEmailDTO configuracionEmail) {
        this.configuracionEmail = configuracionEmail;
    }

    public String getAsuntoEmail() {
        return asuntoEmail;
    }

    public void setAsuntoEmail(String asuntoEmail) {
        this.asuntoEmail = asuntoEmail;
    }

    public String getCuerpoEmail() {
        return cuerpoEmail;
    }

    public void setCuerpoEmail(String cuerpoEmail) {
        this.cuerpoEmail = cuerpoEmail;
    }

    public Boolean getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Boolean adjunto) {
        this.adjunto = adjunto;
    }

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTablaSolicitud() {
        return tablaSolicitud;
    }

    public void setTablaSolicitud(String tablaSolicitud) {
        this.tablaSolicitud = tablaSolicitud;
    }

    public Long getIdTablaSolicitud() {
        return idTablaSolicitud;
    }

    public void setIdTablaSolicitud(Long idTablaSolicitud) {
        this.idTablaSolicitud = idTablaSolicitud;
    }

}
