package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.SolicitudOficioBienDTO;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.SolicitudOficioBien;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class SolicitudOficioBienHelper {
    // --- to DTO
    public static SolicitudOficioBienDTO toLevel0DTO(SolicitudOficioBien entidad) {
        SolicitudOficioBienDTO dto = new SolicitudOficioBienDTO();
        dto.setId(entidad.getId());
        dto.setFechaSolicitud(entidad.getFechaSolicitud());
        dto.setGeneraOficio(entidad.getGeneraOficio());

        return dto;
    }

    public static SolicitudOficioBienDTO toLevel1DTO(SolicitudOficioBien entidad) {
        SolicitudOficioBienDTO dto = toLevel0DTO(entidad);
        if (entidad.getCoactivo() != null)
            dto.setCoactivoDTO(CoactivoHelper.toLevel0DTO(entidad.getCoactivo()));

        return dto;
    }

    public static List<SolicitudOficioBienDTO> toListLevel0DTO(List<SolicitudOficioBien> listEntidad) {
        List<SolicitudOficioBienDTO> listDto = new ArrayList<SolicitudOficioBienDTO>();
        for (SolicitudOficioBien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SolicitudOficioBienDTO> toListLevel1DTO(List<SolicitudOficioBien> listEntidad) {
        List<SolicitudOficioBienDTO> listDto = new ArrayList<SolicitudOficioBienDTO>();
        for (SolicitudOficioBien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SolicitudOficioBien toLevel0Entity(SolicitudOficioBienDTO dto, SolicitudOficioBien entidad) {
        if (null == entidad) {
            entidad = new SolicitudOficioBien();
        }
        entidad.setId(dto.getId());
        entidad.setFechaSolicitud(dto.getFechaSolicitud());
        entidad.setGeneraOficio(dto.getGeneraOficio());

        return entidad;
    }

    public static SolicitudOficioBien toLevel1Entity(SolicitudOficioBienDTO dto, SolicitudOficioBien entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCoactivoDTO() != null) {
            entidad.setCoactivo(new Coactivo());
            entidad.getCoactivo().setId(dto.getCoactivoDTO().getId());
        }

        return entidad;
    }

    public static List<SolicitudOficioBien> toListLevel0Entity(List<SolicitudOficioBienDTO> listDto) {
        List<SolicitudOficioBien> listEntidad = new ArrayList<SolicitudOficioBien>();
        for (SolicitudOficioBienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SolicitudOficioBien> toListLevel1Entity(List<SolicitudOficioBienDTO> listDto) {
        List<SolicitudOficioBien> listEntidad = new ArrayList<SolicitudOficioBien>();
        for (SolicitudOficioBienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
