package co.com.datatools.c2.dto.ubicabilidad;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.ScoreUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 15 11:03:42 COT 2016
 */
public class DireccionPersonaHistoricoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaRegistro;
    private DireccionDTO direccion;
    private TipoFuenteInformacionDTO tipoFuenteInformacion;
    private ScoreUbicabilidadDTO scoreUbicabilidad;
    private Boolean estado;
    private Boolean autorizadoNotificacion;
    private TipoFuenteInformacionDTO tipoFuenteValidacion;
    private Integer prioridad;
    private UsuarioPersonaDTO usuarioValida;
    private Date fechaValidacion;
    private UsuarioPersonaDTO usuarioRegistro;
    private DireccionPersonaDTO direccionPersona;
    private List<PersonaHistoricoDTO> personaHistoricos;

    // --- Constructor
    public DireccionPersonaHistoricoDTO() {
    }

    public DireccionPersonaHistoricoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public TipoFuenteInformacionDTO getTipoFuenteInformacion() {
        return this.tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacionDTO tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

    public ScoreUbicabilidadDTO getScoreUbicabilidad() {
        return this.scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidadDTO scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoFuenteInformacionDTO getTipoFuenteValidacion() {
        return this.tipoFuenteValidacion;
    }

    public void setTipoFuenteValidacion(TipoFuenteInformacionDTO tipoFuenteValidacion) {
        this.tipoFuenteValidacion = tipoFuenteValidacion;
    }

    public Integer getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public UsuarioPersonaDTO getUsuarioValida() {
        return this.usuarioValida;
    }

    public void setUsuarioValida(UsuarioPersonaDTO usuarioValida) {
        this.usuarioValida = usuarioValida;
    }

    public Date getFechaValidacion() {
        return this.fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public UsuarioPersonaDTO getUsuarioRegistro() {
        return this.usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersonaDTO usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
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

    public Boolean getAutorizadoNotificacion() {
        return autorizadoNotificacion;
    }

    public void setAutorizadoNotificacion(Boolean autorizadoNotificacion) {
        this.autorizadoNotificacion = autorizadoNotificacion;
    }

    public DireccionPersonaDTO getDireccionPersona() {
        return direccionPersona;
    }

    public void setDireccionPersona(DireccionPersonaDTO direccionPersona) {
        this.direccionPersona = direccionPersona;
    }

}
