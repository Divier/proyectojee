package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.entidades.EstadoProceso;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.TipoProceso;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:25:49 COT 2016
 */
public class ProcesoHelper {
    // --- to DTO
    public static ProcesoDTO toLevel0DTO(Proceso entidad) {
        ProcesoDTO dto = new ProcesoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroProceso(entidad.getNumeroProceso());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        dto.setObservacion(entidad.getObservacion());

        return dto;
    }

    public static ProcesoDTO toLevel1DTO(Proceso entidad) {
        ProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoProceso() != null)
            dto.setTipoProceso(TipoProcesoHelper.toLevel0DTO(entidad.getTipoProceso()));
        if (entidad.getEstadoProceso() != null)
            dto.setEstadoProceso(EstadoProcesoHelper.toLevel0DTO(entidad.getEstadoProceso()));

        return dto;
    }

    public static List<ProcesoDTO> toListLevel0DTO(List<Proceso> listEntidad) {
        List<ProcesoDTO> listDto = new ArrayList<ProcesoDTO>();
        for (Proceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProcesoDTO> toListLevel1DTO(List<Proceso> listEntidad) {
        List<ProcesoDTO> listDto = new ArrayList<ProcesoDTO>();
        for (Proceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Proceso toLevel0Entity(ProcesoDTO dto, Proceso entidad) {
        if (null == entidad) {
            entidad = new Proceso();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroProceso(dto.getNumeroProceso());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());
        entidad.setObservacion(dto.getObservacion());

        return entidad;
    }

    public static Proceso toLevel1Entity(ProcesoDTO dto, Proceso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoProceso() != null) {
            entidad.setTipoProceso(new TipoProceso());
            entidad.getTipoProceso().setId(dto.getTipoProceso().getId());
        }
        if (dto.getEstadoProceso() != null) {
            entidad.setEstadoProceso(new EstadoProceso());
            entidad.getEstadoProceso().setId(dto.getEstadoProceso().getId());
        }

        return entidad;
    }

    public static List<Proceso> toListLevel0Entity(List<ProcesoDTO> listDto) {
        List<Proceso> listEntidad = new ArrayList<Proceso>();
        for (ProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Proceso> toListLevel1Entity(List<ProcesoDTO> listDto) {
        List<Proceso> listEntidad = new ArrayList<Proceso>();
        for (ProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
