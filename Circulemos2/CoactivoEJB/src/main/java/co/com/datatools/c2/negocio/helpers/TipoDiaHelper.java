package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoDiaDTO;
import co.com.datatools.c2.entidades.TipoDia;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 03 18:59:19 COT 2016
 */
public class TipoDiaHelper {
    // --- to DTO
    public static TipoDiaDTO toLevel0DTO(TipoDia entidad) {
        TipoDiaDTO dto = new TipoDiaDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoDiaDTO toLevel1DTO(TipoDia entidad) {
        TipoDiaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoDiaDTO> toListLevel0DTO(List<TipoDia> listEntidad) {
        List<TipoDiaDTO> listDto = new ArrayList<TipoDiaDTO>();
        for (TipoDia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoDiaDTO> toListLevel1DTO(List<TipoDia> listEntidad) {
        List<TipoDiaDTO> listDto = new ArrayList<TipoDiaDTO>();
        for (TipoDia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoDia toLevel0Entity(TipoDiaDTO dto, TipoDia entidad) {
        if (null == entidad) {
            entidad = new TipoDia();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoDia toLevel1Entity(TipoDiaDTO dto, TipoDia entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoDia> toListLevel0Entity(List<TipoDiaDTO> listDto) {
        List<TipoDia> listEntidad = new ArrayList<TipoDia>();
        for (TipoDiaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoDia> toListLevel1Entity(List<TipoDiaDTO> listDto) {
        List<TipoDia> listEntidad = new ArrayList<TipoDia>();
        for (TipoDiaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
