package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.DivisionActividadEconomicaDTO;
import co.com.datatools.c2.entidades.personas.DivisionActividadEconomica;
import co.com.datatools.c2.entidades.personas.SeccionActividadEconomica;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DivisionActividadEconomicaHelper {
    // --- to DTO
    public static DivisionActividadEconomicaDTO toLevel0DTO(DivisionActividadEconomica entidad) {
        DivisionActividadEconomicaDTO dto = new DivisionActividadEconomicaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static DivisionActividadEconomicaDTO toLevel1DTO(DivisionActividadEconomica entidad) {
        DivisionActividadEconomicaDTO dto = toLevel0DTO(entidad);
        if (entidad.getSeccionActividadEconomica() != null)
            dto.setSeccionActividadEconomica(SeccionActividadEconomicaHelper.toLevel0DTO(entidad
                    .getSeccionActividadEconomica()));

        return dto;
    }

    public static List<DivisionActividadEconomicaDTO> toListLevel0DTO(List<DivisionActividadEconomica> listEntidad) {
        List<DivisionActividadEconomicaDTO> listDto = new ArrayList<DivisionActividadEconomicaDTO>();
        for (DivisionActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DivisionActividadEconomicaDTO> toListLevel1DTO(List<DivisionActividadEconomica> listEntidad) {
        List<DivisionActividadEconomicaDTO> listDto = new ArrayList<DivisionActividadEconomicaDTO>();
        for (DivisionActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DivisionActividadEconomica toLevel0Entity(DivisionActividadEconomicaDTO dto,
            DivisionActividadEconomica entidad) {
        if (null == entidad) {
            entidad = new DivisionActividadEconomica();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static DivisionActividadEconomica toLevel1Entity(DivisionActividadEconomicaDTO dto,
            DivisionActividadEconomica entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getSeccionActividadEconomica() != null) {
            entidad.setSeccionActividadEconomica(new SeccionActividadEconomica());
            entidad.getSeccionActividadEconomica().setId(dto.getSeccionActividadEconomica().getId());
        }

        return entidad;
    }

    public static List<DivisionActividadEconomica> toListLevel0Entity(List<DivisionActividadEconomicaDTO> listDto) {
        List<DivisionActividadEconomica> listEntidad = new ArrayList<DivisionActividadEconomica>();
        for (DivisionActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DivisionActividadEconomica> toListLevel1Entity(List<DivisionActividadEconomicaDTO> listDto) {
        List<DivisionActividadEconomica> listEntidad = new ArrayList<DivisionActividadEconomica>();
        for (DivisionActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
