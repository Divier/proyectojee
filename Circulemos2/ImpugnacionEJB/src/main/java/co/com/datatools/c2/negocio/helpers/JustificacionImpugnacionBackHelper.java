package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.JustificacionImpugnacionBackDTO;
import co.com.datatools.c2.entidades.JustificacionImpugnacionBack;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:08:12 COT 2016
 */
public class JustificacionImpugnacionBackHelper {
    // --- to DTO
    public static JustificacionImpugnacionBackDTO toLevel0DTO(JustificacionImpugnacionBack entidad) {
        JustificacionImpugnacionBackDTO dto = new JustificacionImpugnacionBackDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static JustificacionImpugnacionBackDTO toLevel1DTO(JustificacionImpugnacionBack entidad) {
        JustificacionImpugnacionBackDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<JustificacionImpugnacionBackDTO> toListLevel0DTO(
            List<JustificacionImpugnacionBack> listEntidad) {
        List<JustificacionImpugnacionBackDTO> listDto = new ArrayList<JustificacionImpugnacionBackDTO>();
        for (JustificacionImpugnacionBack entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<JustificacionImpugnacionBackDTO> toListLevel1DTO(
            List<JustificacionImpugnacionBack> listEntidad) {
        List<JustificacionImpugnacionBackDTO> listDto = new ArrayList<JustificacionImpugnacionBackDTO>();
        for (JustificacionImpugnacionBack entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static JustificacionImpugnacionBack toLevel0Entity(JustificacionImpugnacionBackDTO dto,
            JustificacionImpugnacionBack entidad) {
        if (null == entidad) {
            entidad = new JustificacionImpugnacionBack();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static JustificacionImpugnacionBack toLevel1Entity(JustificacionImpugnacionBackDTO dto,
            JustificacionImpugnacionBack entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<JustificacionImpugnacionBack> toListLevel0Entity(List<JustificacionImpugnacionBackDTO> listDto) {
        List<JustificacionImpugnacionBack> listEntidad = new ArrayList<JustificacionImpugnacionBack>();
        for (JustificacionImpugnacionBackDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<JustificacionImpugnacionBack> toListLevel1Entity(List<JustificacionImpugnacionBackDTO> listDto) {
        List<JustificacionImpugnacionBack> listEntidad = new ArrayList<JustificacionImpugnacionBack>();
        for (JustificacionImpugnacionBackDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
