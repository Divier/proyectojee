package co.com.datatools.seguridad.helper;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.entidades.Usuario;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para Usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class UsuarioHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de Usuario
     * @return Dto de Usuario con los correspodientes datos de la entidad
     */
    public static UsuarioDto toDto(Usuario entidad, UsuarioDto dtoBase) {
        UsuarioDto dto = dtoBase;
        if (dto == null) {
            dto = new UsuarioDetalleDto();
        }
        dto.setId(entidad.getIdUsuario());
        dto.setNombres(entidad.getNombre());
        dto.setApellidos(entidad.getApellido());
        dto.setLogin(entidad.getLogin());
        dto.setEmail(entidad.getEmail());
        dto.setAutenticacionConLdap(entidad.isLdap());
        dto.setEstadoPassword(entidad.getEstadoPassword().getNombre());
        dto.setEstadoUsuario(entidad.getEstadoUsuario().getNombre());
        dto.setIdEstadoPassword("" + entidad.getEstadoPassword().getIdEstado());
        dto.setIdEstadoUsuario("" + entidad.getEstadoUsuario().getIdEstado());
        dto.setFechaModUsuario(entidad.getFechaModificaUsuario());
        dto.setFechaModPass(entidad.getFechaModificaPassword());
        dto.setFechaInicioUsuario(entidad.getFechaInicioUsuario());
        dto.setFechaFinUsuario(entidad.getFechaFinUsuario());
        dto.setFechaBloqueoPassword(entidad.getFechaBloqueoPassword());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de Usario
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de Usario con los correspodientes datos del Dto
     */
    public Usuario toEntity(UsuarioDto dto, Usuario entidad) {
        if (entidad == null)
            entidad = new Usuario();
        entidad.setIdUsuario(dto.getId());
        entidad.setApellido(dto.getApellidos());
        entidad.setEmail(dto.getEmail());
        entidad.setFechaModificaPassword(dto.getFechaModPass());
        entidad.setFechaModificaUsuario(dto.getFechaModUsuario());
        entidad.setLdap(dto.isAutenticacionConLdap());
        entidad.setLogin(dto.getLogin());
        entidad.setNombre(dto.getNombres());
        entidad.setFechaFinUsuario(dto.getFechaFinUsuario());
        entidad.setFechaInicioUsuario(dto.getFechaInicioUsuario());
        entidad.setFechaBloqueoPassword(dto.getFechaBloqueoPassword());
        return entidad;
    }

    /**
     * Transforma una lista de entidades de Usuario en otra lista con sus correspondientes Dtos
     * 
     * @param list
     *            Lista de entidades a transformar en Lista de Dtos
     * @return Lista obtenida con los Dtos de Usuario
     */
    public List<UsuarioDto> toListDto(List<Usuario> entidades) {
        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        for (Usuario usuario : entidades) {
            usuarioDtos.add(toDto(usuario, null));
        }
        return usuarioDtos;
    }
}
