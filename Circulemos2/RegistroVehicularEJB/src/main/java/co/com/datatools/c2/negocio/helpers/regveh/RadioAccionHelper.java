package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.RadioAccionDTO;
import co.com.datatools.c2.entidades.RadioAccion;

/**
 * @author Generated
 * @version 3.0 - Fri Oct 09 16:22:35 COT 2015
 */
public class RadioAccionHelper {
    // --- to DTO
    public static RadioAccionDTO toLevel0DTO(RadioAccion entidad) {
        RadioAccionDTO dto = new RadioAccionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static RadioAccionDTO toLevel1DTO(RadioAccion entidad) {
        RadioAccionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<RadioAccionDTO> toListLevel0DTO(List<RadioAccion> listEntidad) {
        List<RadioAccionDTO> listDto = new ArrayList<RadioAccionDTO>();
        for (RadioAccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RadioAccionDTO> toListLevel1DTO(List<RadioAccion> listEntidad) {
        List<RadioAccionDTO> listDto = new ArrayList<RadioAccionDTO>();
        for (RadioAccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RadioAccion toLevel0Entity(RadioAccionDTO dto, RadioAccion entidad) {
        if (null == entidad) {
            entidad = new RadioAccion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static RadioAccion toLevel1Entity(RadioAccionDTO dto, RadioAccion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<RadioAccion> toListLevel0Entity(List<RadioAccionDTO> listDto) {
        List<RadioAccion> listEntidad = new ArrayList<RadioAccion>();
        for (RadioAccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RadioAccion> toListLevel1Entity(List<RadioAccionDTO> listDto) {
        List<RadioAccion> listEntidad = new ArrayList<RadioAccion>();
        for (RadioAccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}