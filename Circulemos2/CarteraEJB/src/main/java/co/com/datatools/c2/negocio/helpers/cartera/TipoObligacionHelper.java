package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.TipoObligacionDTO;
import co.com.datatools.c2.entidades.TipoObligacion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:28:11 COT 2015
 */
public class TipoObligacionHelper {
    // --- to DTO
    public static TipoObligacionDTO toLevel0DTO(TipoObligacion entidad) {
        TipoObligacionDTO dto = new TipoObligacionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoObligacionDTO toLevel1DTO(TipoObligacion entidad) {
        TipoObligacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoObligacionDTO> toListLevel0DTO(List<TipoObligacion> listEntidad) {
        List<TipoObligacionDTO> listDto = new ArrayList<TipoObligacionDTO>();
        for (TipoObligacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoObligacionDTO> toListLevel1DTO(List<TipoObligacion> listEntidad) {
        List<TipoObligacionDTO> listDto = new ArrayList<TipoObligacionDTO>();
        for (TipoObligacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoObligacion toLevel0Entity(TipoObligacionDTO dto, TipoObligacion entidad) {
        if (null == entidad) {
            entidad = new TipoObligacion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoObligacion toLevel1Entity(TipoObligacionDTO dto, TipoObligacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoObligacion> toListLevel0Entity(List<TipoObligacionDTO> listDto) {
        List<TipoObligacion> listEntidad = new ArrayList<TipoObligacion>();
        for (TipoObligacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoObligacion> toListLevel1Entity(List<TipoObligacionDTO> listDto) {
        List<TipoObligacion> listEntidad = new ArrayList<TipoObligacion>();
        for (TipoObligacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
