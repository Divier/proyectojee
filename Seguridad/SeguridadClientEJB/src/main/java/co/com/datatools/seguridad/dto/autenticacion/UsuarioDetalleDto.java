package co.com.datatools.seguridad.dto.autenticacion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;

/**
 * Informacion del perfil de un usuario: roles, grupos, ingresos
 * 
 * @author Felipe Martinez
 */
public class UsuarioDetalleDto extends UsuarioDto {

    private static final long serialVersionUID = 1L;

    private List<RolDto> roles;
    private List<GrupoDto> grupos;
    private List<IngresoDto> ingresos;
    private List<HistoricoUsuarioDto> historico;

    public UsuarioDetalleDto() {
        roles = new ArrayList<RolDto>();
    }

    public List<RolDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDto> roles) {
        this.roles = roles;
    }

    public List<GrupoDto> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoDto> grupos) {
        this.grupos = grupos;
    }

    public List<IngresoDto> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<IngresoDto> ingresos) {
        this.ingresos = ingresos;
    }

    public List<HistoricoUsuarioDto> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HistoricoUsuarioDto> historico) {
        this.historico = historico;
    }

}
