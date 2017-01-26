package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoRechazoRecaudoDTO;
import co.com.datatools.c2.entidades.TipoRechazoRecaudo;

/**
 * @author Generated
 * @version 3.0 - Thu May 05 19:55:30 COT 2016
 */
public class TipoRechazoRecaudoHelper {
    // --- to DTO
    public static TipoRechazoRecaudoDTO toLevel0DTO(TipoRechazoRecaudo entidad) {
        TipoRechazoRecaudoDTO dto = new TipoRechazoRecaudoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoRechazoRecaudoDTO toLevel1DTO(TipoRechazoRecaudo entidad) {
        TipoRechazoRecaudoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoRechazoRecaudoDTO> toListLevel0DTO(List<TipoRechazoRecaudo> listEntidad) {
        List<TipoRechazoRecaudoDTO> listDto = new ArrayList<TipoRechazoRecaudoDTO>();
        for (TipoRechazoRecaudo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoRechazoRecaudoDTO> toListLevel1DTO(List<TipoRechazoRecaudo> listEntidad) {
        List<TipoRechazoRecaudoDTO> listDto = new ArrayList<TipoRechazoRecaudoDTO>();
        for (TipoRechazoRecaudo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoRechazoRecaudo toLevel0Entity(TipoRechazoRecaudoDTO dto, TipoRechazoRecaudo entidad) {
        if (null == entidad) {
            entidad = new TipoRechazoRecaudo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoRechazoRecaudo toLevel1Entity(TipoRechazoRecaudoDTO dto, TipoRechazoRecaudo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoRechazoRecaudo> toListLevel0Entity(List<TipoRechazoRecaudoDTO> listDto) {
        List<TipoRechazoRecaudo> listEntidad = new ArrayList<TipoRechazoRecaudo>();
        for (TipoRechazoRecaudoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoRechazoRecaudo> toListLevel1Entity(List<TipoRechazoRecaudoDTO> listDto) {
        List<TipoRechazoRecaudo> listEntidad = new ArrayList<TipoRechazoRecaudo>();
        for (TipoRechazoRecaudoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
