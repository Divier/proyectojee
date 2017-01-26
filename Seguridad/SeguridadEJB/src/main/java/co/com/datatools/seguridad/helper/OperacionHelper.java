package co.com.datatools.seguridad.helper;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.entidades.Operacion;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para Operaciones
 * 
 * @author claudia.rodriguez
 * 
 */
public class OperacionHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de Operacion
     * @param dtoBase
     *            dto que usa como base para asignar los campos de la entidad, opcional
     * @return Dto de Operacion con los correspodientes datos de la entidad
     */

    public OperacionDto toDto(Operacion entidad, OperacionDto dtoBase) {
        OperacionDto dto = dtoBase;
        if (dto == null)
            dto = new OperacionDto();
        dto.setIdOperacion(entidad.getIdOperacion());
        dto.setNombreOperacion(entidad.getNombre());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de Operacion
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de Operacion con los correspodientes datos del Dto
     */
    public Operacion toEntity(OperacionDto dto, Operacion entidadBase) {
        Operacion entidad = entidadBase;
        if (entidad == null)
            entidad = new Operacion();
        entidad.setIdOperacion(dto.getIdOperacion());
        entidad.setNombre(dto.getNombreOperacion());
        return entidad;
    }

    /**
     * Transforma una lista de entidades de Operacion en otra lista con sus correspondientes Dtos
     * 
     * @param list
     *            Lista de entidades a transformar en Lista de Dtos
     * @return Lista obtenida con los Dtos
     */
    public List<OperacionDto> toListDto(List<Operacion> entidades) {
        List<OperacionDto> operacionDtos = new ArrayList<>();
        for (Operacion operacion : entidades) {
            operacionDtos.add(toDto(operacion, null));
        }
        return operacionDtos;
    }

}
