package co.com.datatools.seguridad.mb.usuarios;

import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de modificacion de Usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class ModificarUsuarioFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ModificarUsuarioFL.class.getName());

    public static final String NOMBRE_BEAN = "modificarUsuarioFL";

    private boolean primerIngreso = true;
    private UsuarioDto usuarioSeleccionado;
    private UsuarioDetalleDto usuarioModificar;
    private boolean permiteModificar;

    private DualListModel<RolDto> rolesUsuario;
    private DualListModel<GrupoDto> gruposUsuario;

    private boolean pwBloqueado;

    public ModificarUsuarioFL() {
        logger.debug("ModificarUsuarioFL::ModificarUsuarioFL");
    }

    public void reset() {

    }

    public UsuarioDto getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioDto usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public UsuarioDetalleDto getUsuarioModificar() {
        return usuarioModificar;
    }

    public void setUsuarioModificar(UsuarioDetalleDto usuarioModificar) {
        this.usuarioModificar = usuarioModificar;
    }

    public boolean isPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(boolean primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public boolean isPermiteModificar() {
        return permiteModificar;
    }

    public void setPermiteModificar(boolean permiteModificar) {
        this.permiteModificar = permiteModificar;
    }

    public DualListModel<RolDto> getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(DualListModel<RolDto> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }

    public DualListModel<GrupoDto> getGruposUsuario() {
        return gruposUsuario;
    }

    public void setGruposUsuario(DualListModel<GrupoDto> gruposUsuario) {
        this.gruposUsuario = gruposUsuario;
    }

    public boolean isPwBloqueado() {
        return pwBloqueado;
    }

    public void setPwBloqueado(boolean pwBloqueado) {
        this.pwBloqueado = pwBloqueado;
    }

}
