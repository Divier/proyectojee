package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoNotificacionDTO;
import co.com.datatools.c2.entidades.TipoNotificacion;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 14:21:33 COT 2016
 */
public class TipoNotificacionHelper {
    // --- to DTO
    public static TipoNotificacionDTO toLevel0DTO(TipoNotificacion entidad) {
        TipoNotificacionDTO dto = new TipoNotificacionDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoNotificacionDTO toLevel1DTO(TipoNotificacion entidad) {
        TipoNotificacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoNotificacionDTO> toListLevel0DTO(List<TipoNotificacion> listEntidad) {
        List<TipoNotificacionDTO> listDto = new ArrayList<TipoNotificacionDTO>();
        for (TipoNotificacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoNotificacionDTO> toListLevel1DTO(List<TipoNotificacion> listEntidad) {
        List<TipoNotificacionDTO> listDto = new ArrayList<TipoNotificacionDTO>();
        for (TipoNotificacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoNotificacion toLevel0Entity(TipoNotificacionDTO dto, TipoNotificacion entidad) {
        if (null == entidad) {
            entidad = new TipoNotificacion();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoNotificacion toLevel1Entity(TipoNotificacionDTO dto, TipoNotificacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoNotificacion> toListLevel0Entity(List<TipoNotificacionDTO> listDto) {
        List<TipoNotificacion> listEntidad = new ArrayList<TipoNotificacion>();
        for (TipoNotificacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoNotificacion> toListLevel1Entity(List<TipoNotificacionDTO> listDto) {
        List<TipoNotificacion> listEntidad = new ArrayList<TipoNotificacion>();
        for (TipoNotificacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
