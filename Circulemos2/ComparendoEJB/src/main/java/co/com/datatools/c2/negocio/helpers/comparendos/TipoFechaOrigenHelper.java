package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoFechaOrigenDTO;
import co.com.datatools.c2.entidades.TipoFechaOrigen;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 27 10:28:16 COT 2016
 */
public class TipoFechaOrigenHelper {
    // --- to DTO
    public static TipoFechaOrigenDTO toLevel0DTO(TipoFechaOrigen entidad) {
        TipoFechaOrigenDTO dto = new TipoFechaOrigenDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoFechaOrigenDTO toLevel1DTO(TipoFechaOrigen entidad) {
        TipoFechaOrigenDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoFechaOrigenDTO> toListLevel0DTO(List<TipoFechaOrigen> listEntidad) {
        List<TipoFechaOrigenDTO> listDto = new ArrayList<TipoFechaOrigenDTO>();
        for (TipoFechaOrigen entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoFechaOrigenDTO> toListLevel1DTO(List<TipoFechaOrigen> listEntidad) {
        List<TipoFechaOrigenDTO> listDto = new ArrayList<TipoFechaOrigenDTO>();
        for (TipoFechaOrigen entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoFechaOrigen toLevel0Entity(TipoFechaOrigenDTO dto, TipoFechaOrigen entidad) {
        if (null == entidad) {
            entidad = new TipoFechaOrigen();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoFechaOrigen toLevel1Entity(TipoFechaOrigenDTO dto, TipoFechaOrigen entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoFechaOrigen> toListLevel0Entity(List<TipoFechaOrigenDTO> listDto) {
        List<TipoFechaOrigen> listEntidad = new ArrayList<TipoFechaOrigen>();
        for (TipoFechaOrigenDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoFechaOrigen> toListLevel1Entity(List<TipoFechaOrigenDTO> listDto) {
        List<TipoFechaOrigen> listEntidad = new ArrayList<TipoFechaOrigen>();
        for (TipoFechaOrigenDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
