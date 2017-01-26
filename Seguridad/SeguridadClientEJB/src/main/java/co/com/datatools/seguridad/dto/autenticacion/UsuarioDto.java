package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;
import java.util.Date;

/**
 * Informacion basica de un usuario
 * 
 * @author Felipe Martinez
 */
public class UsuarioDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // -- Usuario
    private Integer id;
    private String nombres;
    private String apellidos;
    private String email;
    private String idEstadoUsuario;
    private String estadoUsuario;
    private String idEstadoPassword;
    private String estadoPassword;
    private boolean autenticacionConLdap;
    private Date fechaInicioUsuario;
    private Date fechaFinUsuario;
    private Date fechaBloqueoPassword;

    /**
     * Password plano del usuario, solamente de entrada
     */
    private String password;
    private String login;
    private Date fechaModPass;
    private Date fechaModUsuario;

    private String usuarioRealizaCambio;

    /**
     * Constructor sin argumentos, llama a super
     */
    public UsuarioDto() {
        super();
    }

    /**
     * Construtor con los datos basicos del usuario
     * 
     * @param id
     *            Identificador del usuario
     * @param nombres
     *            Nombres del usuario
     * @param apellidos
     *            Apellidos del usuario
     * @param email
     *            Correo electronico del usuario
     * @param login
     *            Login del usuario
     */
    public UsuarioDto(Integer id, String nombres, String apellidos, String email, String login) {
        super();
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.login = login;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEstadoPassword() {
        return estadoPassword;
    }

    public void setEstadoPassword(String estadoPassword) {
        this.estadoPassword = estadoPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAutenticacionConLdap() {
        return autenticacionConLdap;
    }

    public void setAutenticacionConLdap(boolean autenticacionConLdap) {
        this.autenticacionConLdap = autenticacionConLdap;
    }

    public Date getFechaModPass() {
        return fechaModPass;
    }

    public void setFechaModPass(Date fechaModPass) {
        this.fechaModPass = fechaModPass;
    }

    public Date getFechaModUsuario() {
        return fechaModUsuario;
    }

    public void setFechaModUsuario(Date fechaModUsuario) {
        this.fechaModUsuario = fechaModUsuario;
    }

    public Date getFechaInicioUsuario() {
        return fechaInicioUsuario;
    }

    public void setFechaInicioUsuario(Date fechaInicioUsuario) {
        this.fechaInicioUsuario = fechaInicioUsuario;
    }

    public Date getFechaFinUsuario() {
        return fechaFinUsuario;
    }

    public void setFechaFinUsuario(Date fechaFinUsuario) {
        this.fechaFinUsuario = fechaFinUsuario;
    }

    public String getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(String idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public String getIdEstadoPassword() {
        return idEstadoPassword;
    }

    public void setIdEstadoPassword(String idEstadoPassword) {
        this.idEstadoPassword = idEstadoPassword;
    }

    public Date getFechaBloqueoPassword() {
        return fechaBloqueoPassword;
    }

    public void setFechaBloqueoPassword(Date fechaBloqueoPassword) {
        this.fechaBloqueoPassword = fechaBloqueoPassword;
    }

    public String getUsuarioRealizaCambio() {
        return usuarioRealizaCambio;
    }

    public void setUsuarioRealizaCambio(String usuarioRealizaCambio) {
        this.usuarioRealizaCambio = usuarioRealizaCambio;
    }

}
