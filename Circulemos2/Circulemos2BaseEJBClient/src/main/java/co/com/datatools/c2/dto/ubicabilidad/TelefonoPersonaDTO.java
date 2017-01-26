package co.com.datatools.c2.dto.ubicabilidad;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.ScoreUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 11:58:13 COT 2016
 */
public class TelefonoPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Boolean estado;
    private Boolean autorizadoNotificacion;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private String numeroTelefono;
    private TipoFuenteInformacionDTO tipoFuenteValidacion;
    private Integer prioridad;
    private UsuarioPersonaDTO usuarioValida;
    private Date fechaValidacion;
    private List<PersonaHistoricoDTO> personaHistoricos;
    private ScoreUbicabilidadDTO scoreUbicabilidad;
    private TipoTelefonoDTO tipoTelefono;
    private TipoFuenteInformacionDTO tipoFuenteInformacion;
    private PersonaDTO persona;
    private UsuarioPersonaDTO usuarioRegistro;
    private UsuarioPersonaDTO usuarioActualiza;

    // --- Constructor
    public TelefonoPersonaDTO() {
    }

    public TelefonoPersonaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNumeroTelefono() {
        return this.numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<PersonaHistoricoDTO> getPersonaHistoricos() {
        if (this.personaHistoricos == null) {
            this.personaHistoricos = new java.util.ArrayList<>(1);
        }
        return this.personaHistoricos;
    }

    public void setPersonaHistoricos(List<PersonaHistoricoDTO> personaHistoricos) {
        this.personaHistoricos = personaHistoricos;
    }

    public ScoreUbicabilidadDTO getScoreUbicabilidad() {
        return this.scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidadDTO scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

    public TipoTelefonoDTO getTipoTelefono() {
        return this.tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefonoDTO tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public TipoFuenteInformacionDTO getTipoFuenteInformacion() {
        return this.tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacionDTO tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public TipoFuenteInformacionDTO getTipoFuenteValidacion() {
        return tipoFuenteValidacion;
    }

    public void setTipoFuenteValidacion(TipoFuenteInformacionDTO tipoFuenteValidacion) {
        this.tipoFuenteValidacion = tipoFuenteValidacion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public UsuarioPersonaDTO getUsuarioValida() {
        return usuarioValida;
    }

    public void setUsuarioValida(UsuarioPersonaDTO usuarioValida) {
        this.usuarioValida = usuarioValida;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public UsuarioPersonaDTO getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersonaDTO usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public UsuarioPersonaDTO getUsuarioActualiza() {
        return usuarioActualiza;
    }

    public void setUsuarioActualiza(UsuarioPersonaDTO usuarioActualiza) {
        this.usuarioActualiza = usuarioActualiza;
    }

    public Boolean getAutorizadoNotificacion() {
        return autorizadoNotificacion;
    }

    public void setAutorizadoNotificacion(Boolean autorizadoNotificacion) {
        this.autorizadoNotificacion = autorizadoNotificacion;
    }

}
