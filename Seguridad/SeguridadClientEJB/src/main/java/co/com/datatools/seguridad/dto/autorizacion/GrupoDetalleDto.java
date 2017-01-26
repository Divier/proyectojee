package co.com.datatools.seguridad.dto.autorizacion;

import java.util.List;

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;

/**
 * Informacion detallada de las relaciones de un grupo
 * 
 * @author Felipe Martinez
 */
public class GrupoDetalleDto extends GrupoDto {

    private static final long serialVersionUID = 1L;
    /**
     * Lista de usuarios asociados al grupo
     */
    private List<UsuarioDto> usuarios;

    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }
}
