package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EvaluarImpugnacionDTO;
import co.com.datatools.c2.entidades.EvaluarImpugnacion;
import co.com.datatools.c2.entidades.JustificacionImpugnacion;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:05:35 COT 2016
 */
public class EvaluarImpugnacionHelper {
    // --- to DTO
    public static EvaluarImpugnacionDTO toLevel0DTO(EvaluarImpugnacion entidad) {
        EvaluarImpugnacionDTO dto = new EvaluarImpugnacionDTO();
        dto.setId(entidad.getId());
        dto.setSolucionInmediata(entidad.getSolucionInmediata());
        dto.setConsideracionJuridica(entidad.getConsideracionJuridica());

        return dto;
    }

    public static EvaluarImpugnacionDTO toLevel1DTO(EvaluarImpugnacion entidad) {
        EvaluarImpugnacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getJustificacionImpugnacion() != null)
            dto.setJustificacionImpugnacion(
                    JustificacionImpugnacionHelper.toLevel0DTO(entidad.getJustificacionImpugnacion()));

        return dto;
    }

    public static List<EvaluarImpugnacionDTO> toListLevel0DTO(List<EvaluarImpugnacion> listEntidad) {
        List<EvaluarImpugnacionDTO> listDto = new ArrayList<EvaluarImpugnacionDTO>();
        for (EvaluarImpugnacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EvaluarImpugnacionDTO> toListLevel1DTO(List<EvaluarImpugnacion> listEntidad) {
        List<EvaluarImpugnacionDTO> listDto = new ArrayList<EvaluarImpugnacionDTO>();
        for (EvaluarImpugnacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EvaluarImpugnacion toLevel0Entity(EvaluarImpugnacionDTO dto, EvaluarImpugnacion entidad) {
        if (null == entidad) {
            entidad = new EvaluarImpugnacion();
        }
        entidad.setId(dto.getId());
        entidad.setSolucionInmediata(dto.getSolucionInmediata());
        entidad.setConsideracionJuridica(dto.getConsideracionJuridica());

        return entidad;
    }

    public static EvaluarImpugnacion toLevel1Entity(EvaluarImpugnacionDTO dto, EvaluarImpugnacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getJustificacionImpugnacion() != null) {
            entidad.setJustificacionImpugnacion(new JustificacionImpugnacion());
            entidad.getJustificacionImpugnacion().setId(dto.getJustificacionImpugnacion().getId());
        }

        return entidad;
    }

    public static List<EvaluarImpugnacion> toListLevel0Entity(List<EvaluarImpugnacionDTO> listDto) {
        List<EvaluarImpugnacion> listEntidad = new ArrayList<EvaluarImpugnacion>();
        for (EvaluarImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EvaluarImpugnacion> toListLevel1Entity(List<EvaluarImpugnacionDTO> listDto) {
        List<EvaluarImpugnacion> listEntidad = new ArrayList<EvaluarImpugnacion>();
        for (EvaluarImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
