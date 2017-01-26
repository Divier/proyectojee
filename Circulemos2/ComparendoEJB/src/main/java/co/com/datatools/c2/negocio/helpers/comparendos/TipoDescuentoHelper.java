package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoDescuentoDTO;
import co.com.datatools.c2.entidades.TipoDescuento;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 27 10:28:51 COT 2016
 */
public class TipoDescuentoHelper {
    // --- to DTO
    public static TipoDescuentoDTO toLevel0DTO(TipoDescuento entidad) {
        TipoDescuentoDTO dto = new TipoDescuentoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoDescuentoDTO toLevel1DTO(TipoDescuento entidad) {
        TipoDescuentoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoDescuentoDTO> toListLevel0DTO(List<TipoDescuento> listEntidad) {
        List<TipoDescuentoDTO> listDto = new ArrayList<TipoDescuentoDTO>();
        for (TipoDescuento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoDescuentoDTO> toListLevel1DTO(List<TipoDescuento> listEntidad) {
        List<TipoDescuentoDTO> listDto = new ArrayList<TipoDescuentoDTO>();
        for (TipoDescuento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoDescuento toLevel0Entity(TipoDescuentoDTO dto, TipoDescuento entidad) {
        if (null == entidad) {
            entidad = new TipoDescuento();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoDescuento toLevel1Entity(TipoDescuentoDTO dto, TipoDescuento entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoDescuento> toListLevel0Entity(List<TipoDescuentoDTO> listDto) {
        List<TipoDescuento> listEntidad = new ArrayList<TipoDescuento>();
        for (TipoDescuentoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoDescuento> toListLevel1Entity(List<TipoDescuentoDTO> listDto) {
        List<TipoDescuento> listEntidad = new ArrayList<TipoDescuento>();
        for (TipoDescuentoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
