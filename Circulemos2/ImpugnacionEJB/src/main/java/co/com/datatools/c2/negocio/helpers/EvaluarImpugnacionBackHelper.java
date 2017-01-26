package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EvaluarImpugnacionBackDTO;
import co.com.datatools.c2.entidades.EvaluarImpugnacionBack;
import co.com.datatools.c2.entidades.JustificacionImpugnacionBack;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:08:40 COT 2016
 */
public class EvaluarImpugnacionBackHelper {
    // --- to DTO
    public static EvaluarImpugnacionBackDTO toLevel0DTO(EvaluarImpugnacionBack entidad) {
        EvaluarImpugnacionBackDTO dto = new EvaluarImpugnacionBackDTO();
        dto.setId(entidad.getId());
        dto.setEnviarEspecialista(entidad.getEnviarEspecialista());
        dto.setTienePruebas(entidad.getTienePruebas());

        return dto;
    }

    public static EvaluarImpugnacionBackDTO toLevel1DTO(EvaluarImpugnacionBack entidad) {
        EvaluarImpugnacionBackDTO dto = toLevel0DTO(entidad);
        if (entidad.getJustificacionImpugnacionBack() != null)
            dto.setJustificacionImpugnacionBack(
                    JustificacionImpugnacionBackHelper.toLevel0DTO(entidad.getJustificacionImpugnacionBack()));
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));

        return dto;
    }

    public static List<EvaluarImpugnacionBackDTO> toListLevel0DTO(List<EvaluarImpugnacionBack> listEntidad) {
        List<EvaluarImpugnacionBackDTO> listDto = new ArrayList<EvaluarImpugnacionBackDTO>();
        for (EvaluarImpugnacionBack entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EvaluarImpugnacionBackDTO> toListLevel1DTO(List<EvaluarImpugnacionBack> listEntidad) {
        List<EvaluarImpugnacionBackDTO> listDto = new ArrayList<EvaluarImpugnacionBackDTO>();
        for (EvaluarImpugnacionBack entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EvaluarImpugnacionBack toLevel0Entity(EvaluarImpugnacionBackDTO dto, EvaluarImpugnacionBack entidad) {
        if (null == entidad) {
            entidad = new EvaluarImpugnacionBack();
        }
        entidad.setId(dto.getId());
        entidad.setEnviarEspecialista(dto.getEnviarEspecialista());
        entidad.setTienePruebas(dto.getTienePruebas());

        return entidad;
    }

    public static EvaluarImpugnacionBack toLevel1Entity(EvaluarImpugnacionBackDTO dto, EvaluarImpugnacionBack entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getJustificacionImpugnacionBack() != null) {
            entidad.setJustificacionImpugnacionBack(new JustificacionImpugnacionBack());
            entidad.getJustificacionImpugnacionBack().setId(dto.getJustificacionImpugnacionBack().getId());
        }
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }

        return entidad;
    }

    public static List<EvaluarImpugnacionBack> toListLevel0Entity(List<EvaluarImpugnacionBackDTO> listDto) {
        List<EvaluarImpugnacionBack> listEntidad = new ArrayList<EvaluarImpugnacionBack>();
        for (EvaluarImpugnacionBackDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EvaluarImpugnacionBack> toListLevel1Entity(List<EvaluarImpugnacionBackDTO> listDto) {
        List<EvaluarImpugnacionBack> listEntidad = new ArrayList<EvaluarImpugnacionBack>();
        for (EvaluarImpugnacionBackDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
