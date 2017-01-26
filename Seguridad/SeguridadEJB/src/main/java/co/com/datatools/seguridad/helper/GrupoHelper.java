package co.com.datatools.seguridad.helper;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.entidades.Grupo;
import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para Grupos
 * 
 * @author claudia.rodriguez
 * 
 */
public class GrupoHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de Grupo
     * @param dtoBase
     *            dto que usa como base para asignar los campos de la entidad, opcional
     * @return Dto de Grupo con los correspodientes datos de la entidad
     */
    public GrupoDto toDto(Grupo entidad, GrupoDto dtoBase) {
        GrupoDto dto = dtoBase;
        if (dto == null) {
            dto = new GrupoDto();
        }
        dto.setIdGrupo(entidad.getIdGrupo());
        dto.setNombre(entidad.getNombre());
        dto.setActivo(entidad.getActivo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setClase(entidad.getClase().name());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de Grupo
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de Grupo con los correspodientes datos del Dto
     */
    public Grupo toEntity(GrupoDto dto, Grupo entidad) {
        if (entidad == null)
            entidad = new Grupo();
        entidad.setIdGrupo(dto.getIdGrupo());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getActivo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setClase(EnumClaseGrupo.valueOf(dto.getClase()));
        return entidad;
    }

    /**
     * Transforma una lista de entidades de Grupo en otra lista con sus correspondientes Dtos
     * 
     * @param list
     *            Lista de entidades a transformar en Lista de Dtos
     * @return Lista obtenida con los Dtos
     */
    public List<GrupoDto> toListDto(List<Grupo> list) {
        List<GrupoDto> listGruposDto = new ArrayList<>();
        if (list != null) {
            for (Grupo grupo : list) {
                listGruposDto.add(toDto(grupo, null));
            }
        }
        return listGruposDto;
    }

}
