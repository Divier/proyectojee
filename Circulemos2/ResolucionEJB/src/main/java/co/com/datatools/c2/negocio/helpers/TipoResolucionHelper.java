package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoResolucionDTO;
import co.com.datatools.c2.entidades.TipoResolucion;

/**
 * @author Generated
 * @version 3.0 - Fri Jan 29 11:04:25 COT 2016
 */
public class TipoResolucionHelper {
    // --- to DTO
    public static TipoResolucionDTO toLevel0DTO(TipoResolucion entidad) {
        TipoResolucionDTO dto = new TipoResolucionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoResolucionDTO toLevel1DTO(TipoResolucion entidad) {
        TipoResolucionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoResolucionDTO> toListLevel0DTO(List<TipoResolucion> listEntidad) {
        List<TipoResolucionDTO> listDto = new ArrayList<TipoResolucionDTO>();
        for (TipoResolucion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoResolucionDTO> toListLevel1DTO(List<TipoResolucion> listEntidad) {
        List<TipoResolucionDTO> listDto = new ArrayList<TipoResolucionDTO>();
        for (TipoResolucion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoResolucion toLevel0Entity(TipoResolucionDTO dto, TipoResolucion entidad) {
        if (null == entidad) {
            entidad = new TipoResolucion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoResolucion toLevel1Entity(TipoResolucionDTO dto, TipoResolucion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoResolucion> toListLevel0Entity(List<TipoResolucionDTO> listDto) {
        List<TipoResolucion> listEntidad = new ArrayList<TipoResolucion>();
        for (TipoResolucionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoResolucion> toListLevel1Entity(List<TipoResolucionDTO> listDto) {
        List<TipoResolucion> listEntidad = new ArrayList<TipoResolucion>();
        for (TipoResolucionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
