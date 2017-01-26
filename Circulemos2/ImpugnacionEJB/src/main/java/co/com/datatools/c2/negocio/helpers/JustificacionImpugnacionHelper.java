package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.JustificacionImpugnacionDTO;
import co.com.datatools.c2.entidades.JustificacionImpugnacion;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 13:51:44 COT 2016
 */
public class JustificacionImpugnacionHelper {
    // --- to DTO
    public static JustificacionImpugnacionDTO toLevel0DTO(JustificacionImpugnacion entidad) {
        JustificacionImpugnacionDTO dto = new JustificacionImpugnacionDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getActivo());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static JustificacionImpugnacionDTO toLevel1DTO(JustificacionImpugnacion entidad) {
        JustificacionImpugnacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<JustificacionImpugnacionDTO> toListLevel0DTO(List<JustificacionImpugnacion> listEntidad) {
        List<JustificacionImpugnacionDTO> listDto = new ArrayList<JustificacionImpugnacionDTO>();
        for (JustificacionImpugnacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<JustificacionImpugnacionDTO> toListLevel1DTO(List<JustificacionImpugnacion> listEntidad) {
        List<JustificacionImpugnacionDTO> listDto = new ArrayList<JustificacionImpugnacionDTO>();
        for (JustificacionImpugnacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static JustificacionImpugnacion toLevel0Entity(JustificacionImpugnacionDTO dto,
            JustificacionImpugnacion entidad) {
        if (null == entidad) {
            entidad = new JustificacionImpugnacion();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static JustificacionImpugnacion toLevel1Entity(JustificacionImpugnacionDTO dto,
            JustificacionImpugnacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<JustificacionImpugnacion> toListLevel0Entity(List<JustificacionImpugnacionDTO> listDto) {
        List<JustificacionImpugnacion> listEntidad = new ArrayList<JustificacionImpugnacion>();
        for (JustificacionImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<JustificacionImpugnacion> toListLevel1Entity(List<JustificacionImpugnacionDTO> listDto) {
        List<JustificacionImpugnacion> listEntidad = new ArrayList<JustificacionImpugnacion>();
        for (JustificacionImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
