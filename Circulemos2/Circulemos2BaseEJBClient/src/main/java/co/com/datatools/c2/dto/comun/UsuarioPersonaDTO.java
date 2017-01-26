package co.com.datatools.c2.dto.comun;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 11:24:14 COT 2014
 */
public class UsuarioPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private UsuarioDetalleDto usuario;
    private PersonaDTO persona;
    private String login;

    // --- Constructor
    public UsuarioPersonaDTO() {
    }

    public UsuarioPersonaDTO(Integer idUsuario) {
        usuario = new UsuarioDetalleDto();
        usuario.setId(idUsuario);

    }

    // --- Getters-Setters

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public UsuarioDetalleDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDetalleDto usuario) {
        this.usuario = usuario;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
