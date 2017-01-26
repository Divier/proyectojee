package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoAccidenteDTO;
import co.com.datatools.c2.entidades.TipoAccidente;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:00:52 COT 2016
 */
public class TipoAccidenteHelper {
    // --- to DTO
    public static TipoAccidenteDTO toLevel0DTO(TipoAccidente entidad) {
        TipoAccidenteDTO dto = new TipoAccidenteDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoAccidenteDTO toLevel1DTO(TipoAccidente entidad) {
        TipoAccidenteDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoAccidenteDTO> toListLevel0DTO(List<TipoAccidente> listEntidad) {
        List<TipoAccidenteDTO> listDto = new ArrayList<TipoAccidenteDTO>();
        for (TipoAccidente entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoAccidenteDTO> toListLevel1DTO(List<TipoAccidente> listEntidad) {
        List<TipoAccidenteDTO> listDto = new ArrayList<TipoAccidenteDTO>();
        for (TipoAccidente entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoAccidente toLevel0Entity(TipoAccidenteDTO dto, TipoAccidente entidad) {
        if (null == entidad) {
            entidad = new TipoAccidente();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoAccidente toLevel1Entity(TipoAccidenteDTO dto, TipoAccidente entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoAccidente> toListLevel0Entity(List<TipoAccidenteDTO> listDto) {
        List<TipoAccidente> listEntidad = new ArrayList<TipoAccidente>();
        for (TipoAccidenteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoAccidente> toListLevel1Entity(List<TipoAccidenteDTO> listDto) {
        List<TipoAccidente> listEntidad = new ArrayList<TipoAccidente>();
        for (TipoAccidenteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
