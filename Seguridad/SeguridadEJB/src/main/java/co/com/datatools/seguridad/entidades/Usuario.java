package co.com.datatools.seguridad.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Definicion de un usuario
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "Usuario.findByLogin", query = "SELECT us FROM Usuario us WHERE us.login = :login"),
        @NamedQuery(
                name = "Usuario.nombresRolesUsuario",
                query = "Select rl.nombre FROM Usuario us LEFT JOIN us.rolList rl WHERE us.login = :login"),
        @NamedQuery(
                name = "Usuario.rolesUsuario",
                query = "Select rl FROM Usuario us JOIN us.rolList rl WHERE us.login = :login"),
        @NamedQuery(
                name = "Usuario.gruposUsuario",
                query = "Select gr FROM Usuario us JOIN us.grupoList gr WHERE us.login = :login"),
        @NamedQuery(
                name = "Usuario.countByLogin",
                query = "SELECT COUNT(us.idUsuario) FROM Usuario us WHERE us.login = :login"),
        @NamedQuery(
                name = "Usuario.findByGrupo",
                query = "SELECT u FROM Usuario u JOIN u.grupoList gr WHERE gr.idGrupo=:idGrupo"),
        @NamedQuery(name = "Usuario.findByEmail", query = "SELECT us FROM Usuario us WHERE us.email = UPPER(:email)") })
public class Usuario implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_USUARIO_ALL = "Usuario.findAll";
    public static final String SQ_USUARIO_BY_LOGIN = "Usuario.findByLogin";
    public static final String SQ_COUNT_USUARIO_BY_LOGIN = "Usuario.countByLogin";
    public static final String SQ_ROL_BY_USUARIO = "Usuario.nombresRolesUsuario";
    public static final String SQ_ROLES_USUARIO = "Usuario.rolesUsuario";
    public static final String SQ_GRUPOS_USUARIO = "Usuario.gruposUsuario";
    public static final String SQ_USUARIO_BY_GRUPO = "Usuario.findByGrupo";
    public static final String SQ_USUARIO_BY_EMAIL = "Usuario.findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "login", updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "ldap")
    // @Type(type = "numeric_boolean")
    private boolean ldap;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio_usuario")
    private Date fechaInicioUsuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin_usuario")
    private Date fechaFinUsuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifica_usuario")
    private Date fechaModificaUsuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifica_password")
    private Date fechaModificaPassword;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_bloqueo_password")
    private Date fechaBloqueoPassword;

    @Lob
    // @Type(type = "org.hibernate.type.TextType")
    @Column(name = "huella")
    private String huella;

    @JoinColumn(name = "id_estado_usuario", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoUsuario estadoUsuario;

    @JoinColumn(name = "id_estado_password", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoPassword estadoPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "grupo_usuario",
            joinColumns = { @JoinColumn(name = "id_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_grupo") })
    private List<Grupo> grupoList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rol_usuario",
            joinColumns = { @JoinColumn(name = "id_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_rol") })
    private List<Rol> rolList;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<HistoricoUsuario> historicoUsuarioList;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<IngresoUsuario> ingresoUsuarioList;

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public Date getFechaFinUsuario() {
        return fechaFinUsuario;
    }

    public void setFechaFinUsuario(Date fechaFinUsuario) {
        this.fechaFinUsuario = fechaFinUsuario;
    }

    public Date getFechaInicioUsuario() {
        return fechaInicioUsuario;
    }

    public void setFechaInicioUsuario(Date fechaInicioUsuario) {
        this.fechaInicioUsuario = fechaInicioUsuario;
    }

    public Date getFechaModificaPassword() {
        return fechaModificaPassword;
    }

    public void setFechaModificaPassword(Date fechaModificaPassword) {
        this.fechaModificaPassword = fechaModificaPassword;
    }

    public Date getFechaModificaUsuario() {
        return fechaModificaUsuario;
    }

    public void setFechaModificaUsuario(Date fechaModificaUsuario) {
        this.fechaModificaUsuario = fechaModificaUsuario;
    }

    public String getHuella() {
        return huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public EstadoPassword getEstadoPassword() {
        return estadoPassword;
    }

    public void setEstadoPassword(EstadoPassword estadoPassword) {
        this.estadoPassword = estadoPassword;
    }

    public List<HistoricoUsuario> getHistoricoUsuarioList() {
        return historicoUsuarioList;
    }

    public void setHistoricoUsuarioList(List<HistoricoUsuario> historicoUsuarioList) {
        this.historicoUsuarioList = historicoUsuarioList;
    }

    public List<IngresoUsuario> getIngresoUsuarioList() {
        return ingresoUsuarioList;
    }

    public void setIngresoUsuarioList(List<IngresoUsuario> ingresoUsuarioList) {
        this.ingresoUsuarioList = ingresoUsuarioList;
    }

    public Date getFechaBloqueoPassword() {
        return fechaBloqueoPassword;
    }

    public void setFechaBloqueoPassword(Date fechaBloqueoPassword) {
        this.fechaBloqueoPassword = fechaBloqueoPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null)
                || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario {idUsuario: " + getIdUsuario() + ", login: " + getLogin() + "}";
    }

}
