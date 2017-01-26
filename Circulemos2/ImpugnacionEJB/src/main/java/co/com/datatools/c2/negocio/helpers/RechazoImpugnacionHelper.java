package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.RechazoImpugnacionDTO;
import co.com.datatools.c2.entidades.RechazoImpugnacion;
import co.com.datatools.c2.entidades.TipoRechazo;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:06:16 COT 2016
 */
public class RechazoImpugnacionHelper {
    // --- to DTO
    public static RechazoImpugnacionDTO toLevel0DTO(RechazoImpugnacion entidad) {
        RechazoImpugnacionDTO dto = new RechazoImpugnacionDTO();
        dto.setId(entidad.getId());

        dto.setObservacion(entidad.getObservacion());
        return dto;
    }

    public static RechazoImpugnacionDTO toLevel1DTO(RechazoImpugnacion entidad) {
        RechazoImpugnacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getTipoRechazo() != null)
            dto.setTipoRechazo(TipoRechazoHelper.toLevel0DTO(entidad.getTipoRechazo()));

        return dto;
    }

    public static List<RechazoImpugnacionDTO> toListLevel0DTO(List<RechazoImpugnacion> listEntidad) {
        List<RechazoImpugnacionDTO> listDto = new ArrayList<RechazoImpugnacionDTO>();
        for (RechazoImpugnacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RechazoImpugnacionDTO> toListLevel1DTO(List<RechazoImpugnacion> listEntidad) {
        List<RechazoImpugnacionDTO> listDto = new ArrayList<RechazoImpugnacionDTO>();
        for (RechazoImpugnacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RechazoImpugnacion toLevel0Entity(RechazoImpugnacionDTO dto, RechazoImpugnacion entidad) {
        if (null == entidad) {
            entidad = new RechazoImpugnacion();
        }
        entidad.setId(dto.getId());
        entidad.setObservacion(dto.getObservacion());

        return entidad;
    }

    public static RechazoImpugnacion toLevel1Entity(RechazoImpugnacionDTO dto, RechazoImpugnacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getTipoRechazo() != null) {
            entidad.setTipoRechazo(new TipoRechazo());
            entidad.getTipoRechazo().setId(dto.getTipoRechazo().getId());
        }

        return entidad;
    }

    public static List<RechazoImpugnacion> toListLevel0Entity(List<RechazoImpugnacionDTO> listDto) {
        List<RechazoImpugnacion> listEntidad = new ArrayList<RechazoImpugnacion>();
        for (RechazoImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RechazoImpugnacion> toListLevel1Entity(List<RechazoImpugnacionDTO> listDto) {
        List<RechazoImpugnacion> listEntidad = new ArrayList<RechazoImpugnacion>();
        for (RechazoImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
