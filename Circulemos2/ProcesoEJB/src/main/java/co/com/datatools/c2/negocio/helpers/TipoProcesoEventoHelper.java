package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoProcesoEventoDTO;
import co.com.datatools.c2.entidades.TipoProcesoEvento;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class TipoProcesoEventoHelper {
    // --- to DTO
    public static TipoProcesoEventoDTO toLevel0DTO(TipoProcesoEvento entidad) {
        TipoProcesoEventoDTO dto = new TipoProcesoEventoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoProcesoEventoDTO toLevel1DTO(TipoProcesoEvento entidad) {
        TipoProcesoEventoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoProcesoEventoDTO> toListLevel0DTO(List<TipoProcesoEvento> listEntidad) {
        List<TipoProcesoEventoDTO> listDto = new ArrayList<TipoProcesoEventoDTO>();
        for (TipoProcesoEvento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoProcesoEventoDTO> toListLevel1DTO(List<TipoProcesoEvento> listEntidad) {
        List<TipoProcesoEventoDTO> listDto = new ArrayList<TipoProcesoEventoDTO>();
        for (TipoProcesoEvento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoProcesoEvento toLevel0Entity(TipoProcesoEventoDTO dto, TipoProcesoEvento entidad) {
        if (null == entidad) {
            entidad = new TipoProcesoEvento();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoProcesoEvento toLevel1Entity(TipoProcesoEventoDTO dto, TipoProcesoEvento entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoProcesoEvento> toListLevel0Entity(List<TipoProcesoEventoDTO> listDto) {
        List<TipoProcesoEvento> listEntidad = new ArrayList<TipoProcesoEvento>();
        for (TipoProcesoEventoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoProcesoEvento> toListLevel1Entity(List<TipoProcesoEventoDTO> listDto) {
        List<TipoProcesoEvento> listEntidad = new ArrayList<TipoProcesoEvento>();
        for (TipoProcesoEventoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
