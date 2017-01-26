package co.com.datatools.seguridad.mb.roles;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de modificacion de Roles
 * 
 * @author claudia.rodriguez
 * 
 */
public class ModificarRolFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "modificarRolFL";

    private String idRolPadre;
    private String nombreRolPadre;

    private RolDetalleDto rolModificar;
    private DualListModel<GrupoDto> gruposRol;

    private List<WrapperAplicacion> recursosAppRol = new ArrayList<>();

    private boolean primerIngreso = true;

    private String nombreAplicacion;

    public String getIdRolPadre() {
        return idRolPadre;
    }

    public void setIdRolPadre(String idRolPadre) {
        this.idRolPadre = idRolPadre;
    }

    public String getNombreRolPadre() {
        return nombreRolPadre;
    }

    public void setNombreRolPadre(String nombreRolPadre) {
        this.nombreRolPadre = nombreRolPadre;
    }

    public RolDetalleDto getRolModificar() {
        return rolModificar;
    }

    public void setRolModificar(RolDetalleDto rolModificar) {
        this.rolModificar = rolModificar;
    }

    public DualListModel<GrupoDto> getGruposRol() {
        return gruposRol;
    }

    public void setGruposRol(DualListModel<GrupoDto> gruposRol) {
        this.gruposRol = gruposRol;
    }

    public List<WrapperAplicacion> getRecursosAppRol() {
        return recursosAppRol;
    }

    public void setRecursosAppRol(List<WrapperAplicacion> recursosAppRol) {
        this.recursosAppRol = recursosAppRol;
    }

    public boolean isPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(boolean primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

}
