package co.com.datatools.seguridad.helper;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.entidades.Rol;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para Roles
 * 
 * @author claudia.rodriguez
 * 
 */
public class RolesHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param rol
     *            Contiene los datos de la entidad de Rol
     * @return Dto del Rol con los correspodientes datos de la entidad
     */
    public RolDto toDto(Rol rol, RolDto dtoBase) {
        RolDto dto = dtoBase;
        if (dto == null)
            dto = new RolDto();
        dto.setIdRol(rol.getIdRol());
        dto.setNombre(rol.getNombre());
        dto.setActivo(rol.isActivo());
        dto.setDescripcion(rol.getDescripcion());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de Rol
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de Rol con los correspodientes datos del Dto
     */
    public Rol toEntity(RolDto dto, Rol entidad) {
        if (entidad == null) {
            entidad = new Rol();
        }
        entidad.setIdRol(dto.getIdRol());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getActivo());
        entidad.setDescripcion(dto.getDescripcion());
        return entidad;

    }

    /**
     * Transforma una lista de entidades de Rol en otra lista con sus correspondientes Dtos
     * 
     * @param list
     *            Lista de entidades a transformar en Lista de Dtos
     * @return Lista obtenida con los Dtos de Rol
     */
    public List<RolDto> toListDto(List<Rol> roles) {
        List<RolDto> lDto = new ArrayList<>();
        if (roles != null) {
            for (Rol rol : roles) {
                if (rol != null)
                    lDto.add(toDto(rol, null));
            }
        }
        return lDto;
    }
}
