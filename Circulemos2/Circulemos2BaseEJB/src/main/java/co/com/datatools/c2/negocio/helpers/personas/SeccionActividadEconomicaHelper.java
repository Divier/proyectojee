package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.SeccionActividadEconomicaDTO;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.personas.SeccionActividadEconomica;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class SeccionActividadEconomicaHelper {
    // --- to DTO
    public static SeccionActividadEconomicaDTO toLevel0DTO(SeccionActividadEconomica entidad) {
        SeccionActividadEconomicaDTO dto = new SeccionActividadEconomicaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static SeccionActividadEconomicaDTO toLevel1DTO(SeccionActividadEconomica entidad) {
        SeccionActividadEconomicaDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<SeccionActividadEconomicaDTO> toListLevel0DTO(List<SeccionActividadEconomica> listEntidad) {
        List<SeccionActividadEconomicaDTO> listDto = new ArrayList<SeccionActividadEconomicaDTO>();
        for (SeccionActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SeccionActividadEconomicaDTO> toListLevel1DTO(List<SeccionActividadEconomica> listEntidad) {
        List<SeccionActividadEconomicaDTO> listDto = new ArrayList<SeccionActividadEconomicaDTO>();
        for (SeccionActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SeccionActividadEconomica toLevel0Entity(SeccionActividadEconomicaDTO dto,
            SeccionActividadEconomica entidad) {
        if (null == entidad) {
            entidad = new SeccionActividadEconomica();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static SeccionActividadEconomica toLevel1Entity(SeccionActividadEconomicaDTO dto,
            SeccionActividadEconomica entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<SeccionActividadEconomica> toListLevel0Entity(List<SeccionActividadEconomicaDTO> listDto) {
        List<SeccionActividadEconomica> listEntidad = new ArrayList<SeccionActividadEconomica>();
        for (SeccionActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SeccionActividadEconomica> toListLevel1Entity(List<SeccionActividadEconomicaDTO> listDto) {
        List<SeccionActividadEconomica> listEntidad = new ArrayList<SeccionActividadEconomica>();
        for (SeccionActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
