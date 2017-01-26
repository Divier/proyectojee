package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EmbargoBienDTO;
import co.com.datatools.c2.entidades.Bien;
import co.com.datatools.c2.entidades.Embargo;
import co.com.datatools.c2.entidades.EmbargoBien;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class EmbargoBienHelper {
    // --- to DTO
    public static EmbargoBienDTO toLevel0DTO(EmbargoBien entidad) {
        EmbargoBienDTO dto = new EmbargoBienDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static EmbargoBienDTO toLevel1DTO(EmbargoBien entidad) {
        EmbargoBienDTO dto = toLevel0DTO(entidad);
        if (entidad.getEmbargo() != null)
            dto.setEmbargoDTO(EmbargoHelper.toLevel0DTO(entidad.getEmbargo()));
        if (entidad.getBien() != null)
            dto.setBienDTO(BienHelper.toLevel0DTO(entidad.getBien()));

        return dto;
    }

    public static List<EmbargoBienDTO> toListLevel0DTO(List<EmbargoBien> listEntidad) {
        List<EmbargoBienDTO> listDto = new ArrayList<EmbargoBienDTO>();
        for (EmbargoBien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EmbargoBienDTO> toListLevel1DTO(List<EmbargoBien> listEntidad) {
        List<EmbargoBienDTO> listDto = new ArrayList<EmbargoBienDTO>();
        for (EmbargoBien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EmbargoBien toLevel0Entity(EmbargoBienDTO dto, EmbargoBien entidad) {
        if (null == entidad) {
            entidad = new EmbargoBien();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static EmbargoBien toLevel1Entity(EmbargoBienDTO dto, EmbargoBien entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEmbargoDTO() != null) {
            entidad.setEmbargo(new Embargo());
            entidad.getEmbargo().setId(dto.getEmbargoDTO().getId());
        }
        if (dto.getBienDTO() != null) {
            entidad.setBien(new Bien());
            entidad.getBien().setId(dto.getBienDTO().getId());
        }

        return entidad;
    }

    public static List<EmbargoBien> toListLevel0Entity(List<EmbargoBienDTO> listDto) {
        List<EmbargoBien> listEntidad = new ArrayList<EmbargoBien>();
        for (EmbargoBienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EmbargoBien> toListLevel1Entity(List<EmbargoBienDTO> listDto) {
        List<EmbargoBien> listEntidad = new ArrayList<EmbargoBien>();
        for (EmbargoBienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
