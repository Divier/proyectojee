package co.com.datatools.c2.dto.ubicabilidad;

import java.util.Date;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.ScoreUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DireccionPersonaDTO implements EntidadDtoC2, Comparable<DireccionPersonaDTO> {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private TipoFuenteInformacionDTO tipoFuenteValidacion;
    private Integer prioridad;
    private UsuarioPersonaDTO usuarioValida;
    private Date fechaValidacion;
    private DireccionDTO direccion;
    private PersonaDTO persona;
    private TipoFuenteInformacionDTO tipoFuenteInformacion;
    private ScoreUbicabilidadDTO scoreUbicabilidad;
    private Boolean estado;
    private Boolean autorizadoNotificacion;
    private UsuarioPersonaDTO usuarioRegistro;
    private UsuarioPersonaDTO usuarioActualiza;

    // --- Constructor
    public DireccionPersonaDTO() {
    }

    public DireccionPersonaDTO(Long id) {
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

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public TipoFuenteInformacionDTO getTipoFuenteInformacion() {
        return this.tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacionDTO tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

    @Override
    public int compareTo(DireccionPersonaDTO direccionPersonaDTO) {
        return this.getFechaRegistro().compareTo(direccionPersonaDTO.getFechaRegistro());
    }

    public ScoreUbicabilidadDTO getScoreUbicabilidad() {
        return scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidadDTO scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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