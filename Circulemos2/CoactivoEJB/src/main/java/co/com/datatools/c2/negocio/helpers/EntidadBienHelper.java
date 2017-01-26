package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EntidadBienDTO;
import co.com.datatools.c2.entidades.EntidadBien;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class EntidadBienHelper {
    // --- to DTO
    public static EntidadBienDTO toLevel0DTO(EntidadBien entidad) {
        EntidadBienDTO dto = new EntidadBienDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static EntidadBienDTO toLevel1DTO(EntidadBien entidad) {
        EntidadBienDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EntidadBienDTO> toListLevel0DTO(List<EntidadBien> listEntidad) {
        List<EntidadBienDTO> listDto = new ArrayList<EntidadBienDTO>();
        for (EntidadBien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EntidadBienDTO> toListLevel1DTO(List<EntidadBien> listEntidad) {
        List<EntidadBienDTO> listDto = new ArrayList<EntidadBienDTO>();
        for (EntidadBien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EntidadBien toLevel0Entity(EntidadBienDTO dto, EntidadBien entidad) {
        if (null == entidad) {
            entidad = new EntidadBien();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static EntidadBien toLevel1Entity(EntidadBienDTO dto, EntidadBien entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EntidadBien> toListLevel0Entity(List<EntidadBienDTO> listDto) {
        List<EntidadBien> listEntidad = new ArrayList<EntidadBien>();
        for (EntidadBienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EntidadBien> toListLevel1Entity(List<EntidadBienDTO> listDto) {
        List<EntidadBien> listEntidad = new ArrayList<EntidadBien>();
        for (EntidadBienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
