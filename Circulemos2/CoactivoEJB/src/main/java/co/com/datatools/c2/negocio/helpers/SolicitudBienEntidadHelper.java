package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.SolicitudBienEntidadDTO;
import co.com.datatools.c2.entidades.ConfiguracionEntidad;
import co.com.datatools.c2.entidades.SolicitudBienEntidad;
import co.com.datatools.c2.entidades.SolicitudOficioBien;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class SolicitudBienEntidadHelper {
    // --- to DTO
    public static SolicitudBienEntidadDTO toLevel0DTO(SolicitudBienEntidad entidad) {
        SolicitudBienEntidadDTO dto = new SolicitudBienEntidadDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static SolicitudBienEntidadDTO toLevel1DTO(SolicitudBienEntidad entidad) {
        SolicitudBienEntidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getSolicitudOficioBien() != null)
            dto.setSolicitudOficioBienDTO(SolicitudOficioBienHelper.toLevel0DTO(entidad.getSolicitudOficioBien()));
        if (entidad.getConfiguracionEntidad() != null)
            dto.setConfiguracionEntidadDTO(ConfiguracionEntidadHelper.toLevel0DTO(entidad.getConfiguracionEntidad()));

        return dto;
    }

    public static List<SolicitudBienEntidadDTO> toListLevel0DTO(List<SolicitudBienEntidad> listEntidad) {
        List<SolicitudBienEntidadDTO> listDto = new ArrayList<SolicitudBienEntidadDTO>();
        for (SolicitudBienEntidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SolicitudBienEntidadDTO> toListLevel1DTO(List<SolicitudBienEntidad> listEntidad) {
        List<SolicitudBienEntidadDTO> listDto = new ArrayList<SolicitudBienEntidadDTO>();
        for (SolicitudBienEntidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SolicitudBienEntidad toLevel0Entity(SolicitudBienEntidadDTO dto, SolicitudBienEntidad entidad) {
        if (null == entidad) {
            entidad = new SolicitudBienEntidad();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static SolicitudBienEntidad toLevel1Entity(SolicitudBienEntidadDTO dto, SolicitudBienEntidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfiguracionEntidadDTO() != null) {
            entidad.setConfiguracionEntidad(new ConfiguracionEntidad());
            entidad.getConfiguracionEntidad().setId(dto.getConfiguracionEntidadDTO().getId());
        }
        if (dto.getSolicitudOficioBienDTO() != null) {
            entidad.setSolicitudOficioBien(new SolicitudOficioBien());
            entidad.getSolicitudOficioBien().setId(dto.getSolicitudOficioBienDTO().getId());
        }

        return entidad;
    }

    public static List<SolicitudBienEntidad> toListLevel0Entity(List<SolicitudBienEntidadDTO> listDto) {
        List<SolicitudBienEntidad> listEntidad = new ArrayList<SolicitudBienEntidad>();
        for (SolicitudBienEntidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SolicitudBienEntidad> toListLevel1Entity(List<SolicitudBienEntidadDTO> listDto) {
        List<SolicitudBienEntidad> listEntidad = new ArrayList<SolicitudBienEntidad>();
        for (SolicitudBienEntidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
