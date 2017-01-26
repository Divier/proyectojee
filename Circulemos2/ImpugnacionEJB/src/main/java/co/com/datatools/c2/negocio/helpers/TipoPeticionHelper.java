package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoPeticionDTO;
import co.com.datatools.c2.entidades.TipoPeticion;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 13:52:10 COT 2016
 */
public class TipoPeticionHelper {
    // --- to DTO
    public static TipoPeticionDTO toLevel0DTO(TipoPeticion entidad) {
        TipoPeticionDTO dto = new TipoPeticionDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getActivo());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoPeticionDTO toLevel1DTO(TipoPeticion entidad) {
        TipoPeticionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoPeticionDTO> toListLevel0DTO(List<TipoPeticion> listEntidad) {
        List<TipoPeticionDTO> listDto = new ArrayList<TipoPeticionDTO>();
        for (TipoPeticion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoPeticionDTO> toListLevel1DTO(List<TipoPeticion> listEntidad) {
        List<TipoPeticionDTO> listDto = new ArrayList<TipoPeticionDTO>();
        for (TipoPeticion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoPeticion toLevel0Entity(TipoPeticionDTO dto, TipoPeticion entidad) {
        if (null == entidad) {
            entidad = new TipoPeticion();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoPeticion toLevel1Entity(TipoPeticionDTO dto, TipoPeticion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoPeticion> toListLevel0Entity(List<TipoPeticionDTO> listDto) {
        List<TipoPeticion> listEntidad = new ArrayList<TipoPeticion>();
        for (TipoPeticionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoPeticion> toListLevel1Entity(List<TipoPeticionDTO> listDto) {
        List<TipoPeticion> listEntidad = new ArrayList<TipoPeticion>();
        for (TipoPeticionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
