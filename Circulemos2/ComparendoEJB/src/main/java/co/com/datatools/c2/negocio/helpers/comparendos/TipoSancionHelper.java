package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.entidades.TipoSancion;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 06 17:02:38 COT 2015
 */
public class TipoSancionHelper {
    // --- to DTO
    public static TipoSancionDTO toLevel0DTO(TipoSancion entidad) {
        TipoSancionDTO dto = new TipoSancionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    public static TipoSancionDTO toLevel1DTO(TipoSancion entidad) {
        TipoSancionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoSancionDTO> toListLevel0DTO(List<TipoSancion> listEntidad) {
        List<TipoSancionDTO> listDto = new ArrayList<TipoSancionDTO>();
        for (TipoSancion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoSancionDTO> toListLevel1DTO(List<TipoSancion> listEntidad) {
        List<TipoSancionDTO> listDto = new ArrayList<TipoSancionDTO>();
        for (TipoSancion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoSancion toLevel0Entity(TipoSancionDTO dto, TipoSancion entidad) {
        if (null == entidad) {
            entidad = new TipoSancion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    public static TipoSancion toLevel1Entity(TipoSancionDTO dto, TipoSancion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoSancion> toListLevel0Entity(List<TipoSancionDTO> listDto) {
        List<TipoSancion> listEntidad = new ArrayList<TipoSancion>();
        for (TipoSancionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoSancion> toListLevel1Entity(List<TipoSancionDTO> listDto) {
        List<TipoSancion> listEntidad = new ArrayList<TipoSancion>();
        for (TipoSancionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}