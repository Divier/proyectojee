package co.com.datatools.seguridad.dto.autorizacion;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;

/**
 * Informacion detallada del rol
 * 
 * @author Felipe Martinez
 */
public class RolDetalleDto extends RolDto {

    private static final long serialVersionUID = -489327034679545857L;

    /**
     * Lista los recursos asociados al rol por aplicacion<br>
     * K: identificador de aplicacion<br>
     * V: listado de recursos para la aplicacion
     */
    private Map<String, List<RecursoDetalleDto>> recursosAplicacion;

    /**
     * Lista los usuarios q tienen asociado el rol
     */
    private List<UsuarioDto> usuarios;

    /**
     * Rol padre
     */
    private RolDetalleDto rolPadre;

    /**
     * Grupos del Rol
     */
    private List<GrupoDto> grupos;

    private List<HistoricoRolDto> historico;

    public RolDetalleDto() {
        super();
        recursosAplicacion = new Hashtable<>();
    }

    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }

    public Map<String, List<RecursoDetalleDto>> getRecursosAplicacion() {
        return recursosAplicacion;
    }

    public void setRecursosAplicacion(Map<String, List<RecursoDetalleDto>> recursosAplicacion) {
        this.recursosAplicacion = recursosAplicacion;
    }

    public RolDetalleDto getRolPadre() {
        return rolPadre;
    }

    public void setRolPadre(RolDetalleDto rolPadre) {
        this.rolPadre = rolPadre;
    }

    @Override
    public String toString() {
        return "RolDetalleDto {nombre=" + getNombre() + ", recursos=" + recursosAplicacion + "}";
    }

    public List<GrupoDto> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoDto> grupos) {
        this.grupos = grupos;
    }

    public List<HistoricoRolDto> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HistoricoRolDto> historico) {
        this.historico = historico;
    }

}
