package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CoactivoOficioBienDTO;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.CoactivoOficioBien;
import co.com.datatools.c2.entidades.OficioBien;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class CoactivoOficioBienHelper {
    // --- to DTO
    public static CoactivoOficioBienDTO toLevel0DTO(CoactivoOficioBien entidad) {
        CoactivoOficioBienDTO dto = new CoactivoOficioBienDTO();
        dto.setId(entidad.getId());
        dto.setFechaRespuesta(entidad.getFechaRespuesta());
        dto.setNumeroDocRespuesta(entidad.getNumeroDocRespuesta());
        dto.setTieneBien(entidad.getTieneBien());

        return dto;
    }

    public static CoactivoOficioBienDTO toLevel1DTO(CoactivoOficioBien entidad) {
        CoactivoOficioBienDTO dto = toLevel0DTO(entidad);
        if (entidad.getCoactivo() != null)
            dto.setCoactivoDTO(CoactivoHelper.toLevel0DTO(entidad.getCoactivo()));
        if (entidad.getOficioBien() != null)
            dto.setOficioBienDTO(OficioBienHelper.toLevel0DTO(entidad.getOficioBien()));

        return dto;
    }

    public static List<CoactivoOficioBienDTO> toListLevel0DTO(List<CoactivoOficioBien> listEntidad) {
        List<CoactivoOficioBienDTO> listDto = new ArrayList<CoactivoOficioBienDTO>();
        for (CoactivoOficioBien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CoactivoOficioBienDTO> toListLevel1DTO(List<CoactivoOficioBien> listEntidad) {
        List<CoactivoOficioBienDTO> listDto = new ArrayList<CoactivoOficioBienDTO>();
        for (CoactivoOficioBien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CoactivoOficioBien toLevel0Entity(CoactivoOficioBienDTO dto, CoactivoOficioBien entidad) {
        if (null == entidad) {
            entidad = new CoactivoOficioBien();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRespuesta(dto.getFechaRespuesta());
        entidad.setNumeroDocRespuesta(dto.getNumeroDocRespuesta());
        entidad.setTieneBien(dto.getTieneBien());

        return entidad;
    }

    public static CoactivoOficioBien toLevel1Entity(CoactivoOficioBienDTO dto, CoactivoOficioBien entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCoactivoDTO() != null) {
            entidad.setCoactivo(new Coactivo());
            entidad.getCoactivo().setId(dto.getCoactivoDTO().getId());
        }
        if (dto.getOficioBienDTO() != null) {
            entidad.setOficioBien(new OficioBien());
            entidad.getOficioBien().setId(dto.getOficioBienDTO().getId());
        }

        return entidad;
    }

    public static List<CoactivoOficioBien> toListLevel0Entity(List<CoactivoOficioBienDTO> listDto) {
        List<CoactivoOficioBien> listEntidad = new ArrayList<CoactivoOficioBien>();
        for (CoactivoOficioBienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CoactivoOficioBien> toListLevel1Entity(List<CoactivoOficioBienDTO> listDto) {
        List<CoactivoOficioBien> listEntidad = new ArrayList<CoactivoOficioBien>();
        for (CoactivoOficioBienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
