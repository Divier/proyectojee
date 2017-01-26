package co.com.datatools.c2.dto.personas;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Se crea herencia de PersonaJuridicaDTO para mantener la informacion relativa a la entidad Persona
 * 
 * @author felipe.martinez
 * @version 3.0 - Tue May 27 18:03:44 COT 2014 - editado
 */
public class RepresentanteLegalDTO extends PersonaJuridicaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer idRepresentante;
    private String correoElectronico;
    private Date fechaInicio;
    private Date fechaFin;
    private PersonaJuridicaDTO personaJuridica;
    private Date fechaRegistro;
    private UsuarioPersonaDTO usuarioRegistro;
    private Date fechaActualizacion;
    private UsuarioPersonaDTO usuarioActualiza;

    // --- Constructor
    public RepresentanteLegalDTO() {
    }

    public RepresentanteLegalDTO(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    // --- Getters-Setters

    public Integer getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public PersonaJuridicaDTO getPersonaJuridica() {
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridicaDTO personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public UsuarioPersonaDTO getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersonaDTO usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersonaDTO getUsuarioActualiza() {
        return usuarioActualiza;
    }

    public void setUsuarioActualiza(UsuarioPersonaDTO usuarioActualiza) {
        this.usuarioActualiza = usuarioActualiza;
    }

}