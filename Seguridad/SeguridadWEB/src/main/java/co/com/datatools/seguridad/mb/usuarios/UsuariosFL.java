package co.com.datatools.seguridad.mb.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de Usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class UsuariosFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(UsuariosFL.class.getName());

    public static final String NOMBRE_BEAN = "usuariosFL";

    private String login;
    private String nombres;
    private String apellidos;
    private String estadoSeleccionado;
    private boolean consultaRealizada;
    private UsuarioDetalleDto detalleUsuario;

    private List<UsuarioDto> resultadoConsulta;
    private UsuarioDto usuarioSeleccionado;

    private List<RolDetalleDto> rolesSeleccionados = new ArrayList<RolDetalleDto>();

    private List<RolDetalleDto> rolesDisponibles = new ArrayList<RolDetalleDto>();

    private Date fechaInicioHistorico;
    private Date fechaFinHistorico;

    public UsuariosFL() {
        logger.debug("UsuariosFL::UsuariosFL");
        resultadoConsulta = new ArrayList<>();
    }

    public List<UsuarioDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<UsuarioDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public UsuarioDto getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioDto usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
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

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public UsuarioDetalleDto getDetalleUsuario() {
        return detalleUsuario;
    }

    public void setDetalleUsuario(UsuarioDetalleDto detalleUsuario) {
        this.detalleUsuario = detalleUsuario;
    }

    public List<RolDetalleDto> getRolesSeleccionados() {
        return rolesSeleccionados;
    }

    public void setRolesSeleccionados(List<RolDetalleDto> rolesSeleccionados) {
        this.rolesSeleccionados = rolesSeleccionados;
    }

    public List<RolDetalleDto> getRolesDisponibles() {
        return rolesDisponibles;
    }

    public void setRolesDisponibles(List<RolDetalleDto> rolesDisponibles) {
        this.rolesDisponibles = rolesDisponibles;
    }

    public Date getFechaInicioHistorico() {
        return fechaInicioHistorico;
    }

    public void setFechaInicioHistorico(Date fechaInicioHistorico) {
        this.fechaInicioHistorico = fechaInicioHistorico;
    }

    public Date getFechaFinHistorico() {
        return fechaFinHistorico;
    }

    public void setFechaFinHistorico(Date fechaFinHistorico) {
        this.fechaFinHistorico = fechaFinHistorico;
    }
}
