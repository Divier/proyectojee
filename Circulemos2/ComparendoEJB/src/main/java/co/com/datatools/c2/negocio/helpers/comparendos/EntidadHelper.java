package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EntidadDTO;
import co.com.datatools.c2.entidades.Entidad;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class EntidadHelper {
    // --- to DTO
    public static EntidadDTO toLevel0DTO(Entidad entidad) {
        EntidadDTO dto = new EntidadDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static EntidadDTO toLevel1DTO(Entidad entidad) {
        EntidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EntidadDTO> toListLevel0DTO(List<Entidad> listEntidad) {
        List<EntidadDTO> listDto = new ArrayList<EntidadDTO>();
        for (Entidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EntidadDTO> toListLevel1DTO(List<Entidad> listEntidad) {
        List<EntidadDTO> listDto = new ArrayList<EntidadDTO>();
        for (Entidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Entidad toLevel0Entity(EntidadDTO dto, Entidad entidad) {
        if (null == entidad) {
            entidad = new Entidad();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static Entidad toLevel1Entity(EntidadDTO dto, Entidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Entidad> toListLevel0Entity(List<EntidadDTO> listDto) {
        List<Entidad> listEntidad = new ArrayList<Entidad>();
        for (EntidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Entidad> toListLevel1Entity(List<EntidadDTO> listDto) {
        List<Entidad> listEntidad = new ArrayList<Entidad>();
        for (EntidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
