package co.com.datatools.c2.entidades.comun;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "usuario_persona")
@NamedQueries({ @NamedQuery(name = "UsuarioPersona.findAll", query = "SELECT u FROM UsuarioPersona u"),
        @NamedQuery(
                name = "UsuarioPersona.findUsuarioPersonaByUsuario",
                query = "SELECT up FROM UsuarioPersona up WHERE up.idUsuario=:idUsuario"),
        @NamedQuery(
                name = "UsuarioPersona.findUsuarioPersonaBylogin",
                query = "SELECT up FROM UsuarioPersona up WHERE up.login=:login") })
public class UsuarioPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_USUARIO_PERSONA_BY_USUARIO = "UsuarioPersona.findUsuarioPersonaByUsuario";
    public static final String SQ_USUARIO_PERSONA_BY_LOGIN_USUARIO = "UsuarioPersona.findUsuarioPersonaBylogin";

    @Id
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    @Column(name = "login", updatable = false)
    private String login;

    public UsuarioPersona() {
    }

    public UsuarioPersona(Integer idUsuario) {
        super();
        this.idUsuario = idUsuario;
    }

    public UsuarioPersona(UsuarioPersonaDTO usuarioDto) {
        super();
        this.idUsuario = usuarioDto.getUsuario().getId();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
