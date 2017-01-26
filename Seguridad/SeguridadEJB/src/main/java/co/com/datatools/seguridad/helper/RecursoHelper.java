package co.com.datatools.seguridad.helper;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.entidades.Recurso;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para Recursos
 * 
 * @author claudia.rodriguez
 * 
 */
public class RecursoHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de Recurso
     * @param dtoBase
     *            dto que usa como base para asignar los campos de la entidad, opcional
     * @return Dto de Recurso con los correspodientes datos de la entidad
     */
    public RecursoDto toDto(Recurso entidad, RecursoDto dtoBase) {
        RecursoDto dto = dtoBase;
        if (dto == null) {
            dto = new RecursoDto();
        }
        dto.setIdRecurso(entidad.getIdRecurso());
        dto.setNombreRecurso(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setIdAplicacion(entidad.getAplicacion().getIdAplicacion() + "");
        dto.setNombreAplicacion(entidad.getAplicacion().getNombreAplicacion());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de Recurso
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de Recurso con los correspodientes datos del Dto
     */
    public Recurso toEntity(RecursoDto dto, Recurso entidad) {
        if (entidad == null) {
            entidad = new Recurso();
        }
        entidad.setIdRecurso(dto.getIdRecurso());
        entidad.setNombre(dto.getNombreRecurso());
        entidad.setDescripcion(dto.getDescripcion());
        return entidad;
    }

    /**
     * Transforma una lista de entidades de Recurso en otra lista con sus correspondientes Dtos
     * 
     * @param list
     *            Lista de entidades a transformar en Lista de Dtos
     * @return Lista obtenida con los Dtos
     */
    public List<RecursoDto> toListDto(List<Recurso> entidades) {
        List<RecursoDto> recursosDtos = new ArrayList<>();
        for (Recurso recurso : entidades) {
            recursosDtos.add(toDto(recurso, null));
        }
        return recursosDtos;
    }

}
