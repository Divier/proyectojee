package co.com.datatools.c2.managed_bean.administracion.usuarios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class UsuariosHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "usuariosHolderFL";

    private List<RolDetalleDto> rolesFiltro = new ArrayList<RolDetalleDto>();
    private List<RolDetalleDto> rolesSeleccionados = new ArrayList<RolDetalleDto>();

    private String idTipoIdentificacionPersona;
    private String numeroIdentificacionPersona;
    private String login;
    private String nombres;
    private String apellidos;
    private String estadoSeleccionado;

    private List<UsuarioPersonaDTO> resultadoConsulta;

    private UsuarioPersonaDTO usuarioSeleccionado;

    private UsuarioDetalleDto detalleUsuario;

    public UsuariosHolderFL() {
        resultadoConsulta = new ArrayList<UsuarioPersonaDTO>();
    }

    public List<RolDetalleDto> getRolesFiltro() {
        return rolesFiltro;
    }

    public void setRolesFiltro(List<RolDetalleDto> rolesFiltro) {
        this.rolesFiltro = rolesFiltro;
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

    public List<RolDetalleDto> getRolesSeleccionados() {
        return rolesSeleccionados;
    }

    public void setRolesSeleccionados(List<RolDetalleDto> rolesSeleccionados) {
        this.rolesSeleccionados = rolesSeleccionados;
    }

    public List<UsuarioPersonaDTO> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<UsuarioPersonaDTO> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public UsuarioPersonaDTO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioPersonaDTO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public UsuarioDetalleDto getDetalleUsuario() {
        return detalleUsuario;
    }

    public void setDetalleUsuario(UsuarioDetalleDto detalleUsuario) {
        this.detalleUsuario = detalleUsuario;
    }

}
