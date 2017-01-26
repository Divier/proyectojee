package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.OficioBienDTO;
import co.com.datatools.c2.entidades.OficioBien;
import co.com.datatools.c2.entidades.SolicitudBienEntidad;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class OficioBienHelper {
    // --- to DTO
    public static OficioBienDTO toLevel0DTO(OficioBien entidad) {
        OficioBienDTO dto = new OficioBienDTO();
        dto.setId(entidad.getId());
        dto.setNumeroOficio(entidad.getNumeroOficio());
        dto.setFechaGeneracion(entidad.getFechaGeneracion());

        return dto;
    }

    public static OficioBienDTO toLevel1DTO(OficioBien entidad) {
        OficioBienDTO dto = toLevel0DTO(entidad);
        if (entidad.getSolicitudBienEntidad() != null)
            dto.setSolicitudBienEntidadDTO(SolicitudBienEntidadHelper.toLevel0DTO(entidad.getSolicitudBienEntidad()));

        return dto;
    }

    public static List<OficioBienDTO> toListLevel0DTO(List<OficioBien> listEntidad) {
        List<OficioBienDTO> listDto = new ArrayList<OficioBienDTO>();
        for (OficioBien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<OficioBienDTO> toListLevel1DTO(List<OficioBien> listEntidad) {
        List<OficioBienDTO> listDto = new ArrayList<OficioBienDTO>();
        for (OficioBien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static OficioBien toLevel0Entity(OficioBienDTO dto, OficioBien entidad) {
        if (null == entidad) {
            entidad = new OficioBien();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroOficio(dto.getNumeroOficio());
        entidad.setFechaGeneracion(dto.getFechaGeneracion());

        return entidad;
    }

    public static OficioBien toLevel1Entity(OficioBienDTO dto, OficioBien entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getSolicitudBienEntidadDTO() != null) {
            entidad.setSolicitudBienEntidad(new SolicitudBienEntidad());
            entidad.getSolicitudBienEntidad().setId(dto.getSolicitudBienEntidadDTO().getId());
        }

        return entidad;
    }

    public static List<OficioBien> toListLevel0Entity(List<OficioBienDTO> listDto) {
        List<OficioBien> listEntidad = new ArrayList<OficioBien>();
        for (OficioBienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<OficioBien> toListLevel1Entity(List<OficioBienDTO> listDto) {
        List<OficioBien> listEntidad = new ArrayList<OficioBien>();
        for (OficioBienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
