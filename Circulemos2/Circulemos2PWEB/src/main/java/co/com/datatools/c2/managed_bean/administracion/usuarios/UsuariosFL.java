package co.com.datatools.c2.managed_bean.administracion.usuarios;

import org.primefaces.model.DualListModel;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;

/**
 * Clase que actua como Dto que provee los datos para las paginas de creacion y actualizacion de usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class UsuariosFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "usuariosFL";

    // ****************************************NUEVO*************************************************************
    private boolean usuarioPersona;
    private boolean validoUsuario;
    private UsuarioPersonaDTO usuarioPersonaDTO;
    private boolean personaNatural;
    private String login;
    private boolean guardarUsuario;

    // ****************************************ANTIGUO*************************************************************

    private UsuarioDetalleDto usuarioModificar;

    private DualListModel<RolDto> rolesUsuario;

    private boolean pwBloqueado;
    private String nombres;
    private String apellidos;
    private String estadoSeleccionado;
    private PersonaDTO personaDTO;
    private boolean permiteRegistrarPersona;
    private boolean mostrarFormulario;

    private DireccionDTO direccion;
    private boolean ldap;
    private boolean permiteLdap;
    private String valorCampoBusquedaLdap;
    private boolean ldapValidado;
    private boolean mostrarDatosUsuario;
    private boolean adicionarUsuario;

    private boolean pedirDatosUsuario = true;
    private boolean mostrarDatosPersona;

    private String idTipoIdentificacionPersona;
    private String numeroIdentificacionPersona;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String telefono;
    private Short digitoVerificacion;
    private String nombreComercial;
    private String sigla;
    private String emailUsuario;

    public UsuariosFL() {
        personaDTO = new PersonaDTO();
        personaDTO.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        usuarioPersonaDTO = new UsuarioPersonaDTO();
        usuarioPersonaDTO.setUsuario(new UsuarioDetalleDto());
        usuarioPersonaDTO.setPersona(new PersonaDTO());
        usuarioPersonaDTO.getPersona().setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
    }

    public UsuarioDetalleDto getUsuarioModificar() {
        return usuarioModificar;
    }

    public void setUsuarioModificar(UsuarioDetalleDto usuarioModificar) {
        this.usuarioModificar = usuarioModificar;
    }

    public boolean isPwBloqueado() {
        return pwBloqueado;
    }

    public void setPwBloqueado(boolean pwBloqueado) {
        this.pwBloqueado = pwBloqueado;
    }

    public DualListModel<RolDto> getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(DualListModel<RolDto> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
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

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public boolean isPermiteRegistrarPersona() {
        return permiteRegistrarPersona;
    }

    public void setPermiteRegistrarPersona(boolean permiteRegistrarPersona) {
        this.permiteRegistrarPersona = permiteRegistrarPersona;
    }

    public boolean isPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(boolean personaNatural) {
        this.personaNatural = personaNatural;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public boolean isLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public boolean isPermiteLdap() {
        return permiteLdap;
    }

    public void setPermiteLdap(boolean permiteLdap) {
        this.permiteLdap = permiteLdap;
    }

    public String getValorCampoBusquedaLdap() {
        return valorCampoBusquedaLdap;
    }

    public void setValorCampoBusquedaLdap(String valorCampoBusquedaLdap) {
        this.valorCampoBusquedaLdap = valorCampoBusquedaLdap;
    }

    public boolean isLdapValidado() {
        return ldapValidado;
    }

    public void setLdapValidado(boolean ldapValidado) {
        this.ldapValidado = ldapValidado;
    }

    public boolean isMostrarDatosUsuario() {
        return mostrarDatosUsuario;
    }

    public void setMostrarDatosUsuario(boolean mostrarDatosUsuario) {
        this.mostrarDatosUsuario = mostrarDatosUsuario;
    }

    public boolean isPedirDatosUsuario() {
        return pedirDatosUsuario;
    }

    public void setPedirDatosUsuario(boolean pedirDatosUsuario) {
        this.pedirDatosUsuario = pedirDatosUsuario;
    }

    public boolean isMostrarDatosPersona() {
        return mostrarDatosPersona;
    }

    public void setMostrarDatosPersona(boolean mostrarDatosPersona) {
        this.mostrarDatosPersona = mostrarDatosPersona;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public UsuarioPersonaDTO getUsuarioPersonaDTO() {
        return usuarioPersonaDTO;
    }

    public void setUsuarioPersonaDTO(UsuarioPersonaDTO usuarioPersonaDTO) {
        this.usuarioPersonaDTO = usuarioPersonaDTO;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public boolean isAdicionarUsuario() {
        return adicionarUsuario;
    }

    public void setAdicionarUsuario(boolean adicionarUsuario) {
        this.adicionarUsuario = adicionarUsuario;
    }

    public boolean isUsuarioPersona() {
        return usuarioPersona;
    }

    public void setUsuarioPersona(boolean usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public String getIdTipoIdentificacionPersona() {
        return idTipoIdentificacionPersona;
    }

    public void setIdTipoIdentificacionPersona(String idTipoIdentificacionPersona) {
        this.idTipoIdentificacionPersona = idTipoIdentificacionPersona;
    }

    public String getNumeroIdentificacionPersona() {
        return numeroIdentificacionPersona;
    }

    public void setNumeroIdentificacionPersona(String numeroIdentificacionPersona) {
        this.numeroIdentificacionPersona = numeroIdentificacionPersona;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Short getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getNombreBean() {
        return NOMBRE_BEAN;
    }

    public boolean isValidoUsuario() {
        return validoUsuario;
    }

    public void setValidoUsuario(boolean validoUsuario) {
        this.validoUsuario = validoUsuario;
    }

    public boolean isGuardarUsuario() {
        return guardarUsuario;
    }

    public void setGuardarUsuario(boolean guardarUsuario) {
        this.guardarUsuario = guardarUsuario;
    }
}
