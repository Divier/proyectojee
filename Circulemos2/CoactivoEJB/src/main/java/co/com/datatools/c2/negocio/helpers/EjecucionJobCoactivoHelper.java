package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EjecucionJobCoactivoDTO;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.EjecucionJobCoactivo;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 03 19:00:13 COT 2016
 */
public class EjecucionJobCoactivoHelper {
    // --- to DTO
    public static EjecucionJobCoactivoDTO toLevel0DTO(EjecucionJobCoactivo entidad) {
        EjecucionJobCoactivoDTO dto = new EjecucionJobCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setFechaEjecucion(entidad.getFechaEjecucion());

        return dto;
    }

    public static EjecucionJobCoactivoDTO toLevel1DTO(EjecucionJobCoactivo entidad) {
        EjecucionJobCoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getConfiguracionCoactivo() != null)
            dto.setConfiguracionCoactivo(ConfiguracionCoactivoHelper.toLevel0DTO(entidad.getConfiguracionCoactivo()));

        return dto;
    }

    public static List<EjecucionJobCoactivoDTO> toListLevel0DTO(List<EjecucionJobCoactivo> listEntidad) {
        List<EjecucionJobCoactivoDTO> listDto = new ArrayList<EjecucionJobCoactivoDTO>();
        for (EjecucionJobCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EjecucionJobCoactivoDTO> toListLevel1DTO(List<EjecucionJobCoactivo> listEntidad) {
        List<EjecucionJobCoactivoDTO> listDto = new ArrayList<EjecucionJobCoactivoDTO>();
        for (EjecucionJobCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EjecucionJobCoactivo toLevel0Entity(EjecucionJobCoactivoDTO dto, EjecucionJobCoactivo entidad) {
        if (null == entidad) {
            entidad = new EjecucionJobCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaEjecucion(dto.getFechaEjecucion());

        return entidad;
    }

    public static EjecucionJobCoactivo toLevel1Entity(EjecucionJobCoactivoDTO dto, EjecucionJobCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfiguracionCoactivo() != null) {
            entidad.setConfiguracionCoactivo(new ConfiguracionCoactivo());
            entidad.getConfiguracionCoactivo().setId(dto.getConfiguracionCoactivo().getId());
        }

        return entidad;
    }

    public static List<EjecucionJobCoactivo> toListLevel0Entity(List<EjecucionJobCoactivoDTO> listDto) {
        List<EjecucionJobCoactivo> listEntidad = new ArrayList<EjecucionJobCoactivo>();
        for (EjecucionJobCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EjecucionJobCoactivo> toListLevel1Entity(List<EjecucionJobCoactivoDTO> listDto) {
        List<EjecucionJobCoactivo> listEntidad = new ArrayList<EjecucionJobCoactivo>();
        for (EjecucionJobCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
