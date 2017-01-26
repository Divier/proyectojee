package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.entidades.comun.TipoUbicabilidad;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoUbicabilidadHelper {
    // --- to DTO
    public static TipoUbicabilidadDTO toLevel0DTO(TipoUbicabilidad entidad) {
        TipoUbicabilidadDTO dto = new TipoUbicabilidadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoUbicabilidadDTO toLevel1DTO(TipoUbicabilidad entidad) {
        TipoUbicabilidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoUbicabilidadDTO> toListLevel0DTO(List<TipoUbicabilidad> listEntidad) {
        List<TipoUbicabilidadDTO> listDto = new ArrayList<TipoUbicabilidadDTO>();
        for (TipoUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoUbicabilidadDTO> toListLevel1DTO(List<TipoUbicabilidad> listEntidad) {
        List<TipoUbicabilidadDTO> listDto = new ArrayList<TipoUbicabilidadDTO>();
        for (TipoUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoUbicabilidad toLevel0Entity(TipoUbicabilidadDTO dto, TipoUbicabilidad entidad) {
        if (null == entidad) {
            entidad = new TipoUbicabilidad();
        }

        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoUbicabilidad toLevel1Entity(TipoUbicabilidadDTO dto, TipoUbicabilidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoUbicabilidad> toListLevel0Entity(List<TipoUbicabilidadDTO> listDto) {
        List<TipoUbicabilidad> listEntidad = new ArrayList<TipoUbicabilidad>();
        for (TipoUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoUbicabilidad> toListLevel1Entity(List<TipoUbicabilidadDTO> listDto) {
        List<TipoUbicabilidad> listEntidad = new ArrayList<TipoUbicabilidad>();
        for (TipoUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
