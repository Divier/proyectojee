package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.ModalidadDTO;
import co.com.datatools.c2.entidades.Modalidad;

/**
 * @author Generated
 * @version 3.0 - Fri Oct 09 16:34:55 COT 2015
 */
public class ModalidadHelper {
    // --- to DTO
    public static ModalidadDTO toLevel0DTO(Modalidad entidad) {
        ModalidadDTO dto = new ModalidadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static ModalidadDTO toLevel1DTO(Modalidad entidad) {
        ModalidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ModalidadDTO> toListLevel0DTO(List<Modalidad> listEntidad) {
        List<ModalidadDTO> listDto = new ArrayList<ModalidadDTO>();
        for (Modalidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ModalidadDTO> toListLevel1DTO(List<Modalidad> listEntidad) {
        List<ModalidadDTO> listDto = new ArrayList<ModalidadDTO>();
        for (Modalidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Modalidad toLevel0Entity(ModalidadDTO dto, Modalidad entidad) {
        if (null == entidad) {
            entidad = new Modalidad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static Modalidad toLevel1Entity(ModalidadDTO dto, Modalidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Modalidad> toListLevel0Entity(List<ModalidadDTO> listDto) {
        List<Modalidad> listEntidad = new ArrayList<Modalidad>();
        for (ModalidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Modalidad> toListLevel1Entity(List<ModalidadDTO> listDto) {
        List<Modalidad> listEntidad = new ArrayList<Modalidad>();
        for (ModalidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}