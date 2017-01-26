package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoInfraccionDTO;
import co.com.datatools.c2.entidades.TipoInfraccion;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 08:31:06 COT 2016
 */
public class TipoInfraccionHelper {
    // --- to DTO
    public static TipoInfraccionDTO toLevel0DTO(TipoInfraccion entidad) {
        TipoInfraccionDTO dto = new TipoInfraccionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    public static TipoInfraccionDTO toLevel1DTO(TipoInfraccion entidad) {
        TipoInfraccionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoInfraccionDTO> toListLevel0DTO(List<TipoInfraccion> listEntidad) {
        List<TipoInfraccionDTO> listDto = new ArrayList<TipoInfraccionDTO>();
        for (TipoInfraccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoInfraccionDTO> toListLevel1DTO(List<TipoInfraccion> listEntidad) {
        List<TipoInfraccionDTO> listDto = new ArrayList<TipoInfraccionDTO>();
        for (TipoInfraccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoInfraccion toLevel0Entity(TipoInfraccionDTO dto, TipoInfraccion entidad) {
        if (null == entidad) {
            entidad = new TipoInfraccion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    public static TipoInfraccion toLevel1Entity(TipoInfraccionDTO dto, TipoInfraccion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoInfraccion> toListLevel0Entity(List<TipoInfraccionDTO> listDto) {
        List<TipoInfraccion> listEntidad = new ArrayList<TipoInfraccion>();
        for (TipoInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoInfraccion> toListLevel1Entity(List<TipoInfraccionDTO> listDto) {
        List<TipoInfraccion> listEntidad = new ArrayList<TipoInfraccion>();
        for (TipoInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}