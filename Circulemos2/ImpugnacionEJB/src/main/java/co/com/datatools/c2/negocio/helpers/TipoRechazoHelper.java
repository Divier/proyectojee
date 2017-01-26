package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoRechazoDTO;
import co.com.datatools.c2.entidades.TipoRechazo;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 13:51:57 COT 2016
 */
public class TipoRechazoHelper {
    // --- to DTO
    public static TipoRechazoDTO toLevel0DTO(TipoRechazo entidad) {
        TipoRechazoDTO dto = new TipoRechazoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getActivo());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoRechazoDTO toLevel1DTO(TipoRechazo entidad) {
        TipoRechazoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoRechazoDTO> toListLevel0DTO(List<TipoRechazo> listEntidad) {
        List<TipoRechazoDTO> listDto = new ArrayList<TipoRechazoDTO>();
        for (TipoRechazo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoRechazoDTO> toListLevel1DTO(List<TipoRechazo> listEntidad) {
        List<TipoRechazoDTO> listDto = new ArrayList<TipoRechazoDTO>();
        for (TipoRechazo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoRechazo toLevel0Entity(TipoRechazoDTO dto, TipoRechazo entidad) {
        if (null == entidad) {
            entidad = new TipoRechazo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoRechazo toLevel1Entity(TipoRechazoDTO dto, TipoRechazo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoRechazo> toListLevel0Entity(List<TipoRechazoDTO> listDto) {
        List<TipoRechazo> listEntidad = new ArrayList<TipoRechazo>();
        for (TipoRechazoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoRechazo> toListLevel1Entity(List<TipoRechazoDTO> listDto) {
        List<TipoRechazo> listEntidad = new ArrayList<TipoRechazo>();
        for (TipoRechazoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
