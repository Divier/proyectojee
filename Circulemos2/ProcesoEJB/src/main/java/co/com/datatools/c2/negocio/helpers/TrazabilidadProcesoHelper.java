package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.entidades.EstadoProceso;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:52:52 COT 2016
 */
public class TrazabilidadProcesoHelper {
    // --- to DTO
    public static TrazabilidadProcesoDTO toLevel0DTO(TrazabilidadProceso entidad) {
        TrazabilidadProcesoDTO dto = new TrazabilidadProcesoDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static TrazabilidadProcesoDTO toLevel1DTO(TrazabilidadProceso entidad) {
        TrazabilidadProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoHelper.toLevel0DTO(entidad.getProceso()));
        if (entidad.getEstadoProceso() != null)
            dto.setEstadoProceso(EstadoProcesoHelper.toLevel0DTO(entidad.getEstadoProceso()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<TrazabilidadProcesoDTO> toListLevel0DTO(List<TrazabilidadProceso> listEntidad) {
        List<TrazabilidadProcesoDTO> listDto = new ArrayList<TrazabilidadProcesoDTO>();
        for (TrazabilidadProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TrazabilidadProcesoDTO> toListLevel1DTO(List<TrazabilidadProceso> listEntidad) {
        List<TrazabilidadProcesoDTO> listDto = new ArrayList<TrazabilidadProcesoDTO>();
        for (TrazabilidadProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TrazabilidadProceso toLevel0Entity(TrazabilidadProcesoDTO dto, TrazabilidadProceso entidad) {
        if (null == entidad) {
            entidad = new TrazabilidadProceso();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static TrazabilidadProceso toLevel1Entity(TrazabilidadProcesoDTO dto, TrazabilidadProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getProceso() != null) {
            entidad.setProceso(new Proceso());
            entidad.getProceso().setId(dto.getProceso().getId());
        }
        if (dto.getEstadoProceso() != null) {
            entidad.setEstadoProceso(new EstadoProceso());
            entidad.getEstadoProceso().setId(dto.getEstadoProceso().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }

        return entidad;
    }

    public static List<TrazabilidadProceso> toListLevel0Entity(List<TrazabilidadProcesoDTO> listDto) {
        List<TrazabilidadProceso> listEntidad = new ArrayList<TrazabilidadProceso>();
        for (TrazabilidadProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TrazabilidadProceso> toListLevel1Entity(List<TrazabilidadProcesoDTO> listDto) {
        List<TrazabilidadProceso> listEntidad = new ArrayList<TrazabilidadProceso>();
        for (TrazabilidadProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
