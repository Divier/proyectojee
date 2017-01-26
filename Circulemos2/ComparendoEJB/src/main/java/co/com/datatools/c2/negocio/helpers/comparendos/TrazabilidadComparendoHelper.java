package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TrazabilidadComparendoDTO;
import co.com.datatools.c2.entidades.Actividad;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.EstadoComparendo;
import co.com.datatools.c2.entidades.TrazabilidadComparendo;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Mar 01 11:57:59 COT 2016
 */
public class TrazabilidadComparendoHelper {
    // --- to DTO
    public static TrazabilidadComparendoDTO toLevel0DTO(TrazabilidadComparendo entidad) {
        TrazabilidadComparendoDTO dto = new TrazabilidadComparendoDTO();
        dto.setId(entidad.getId());
        dto.setFechaEjecucion(entidad.getFechaEjecucion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setUsuarioExterno(entidad.getUsuarioExterno());
        dto.setNumeroActoAdministrativo(entidad.getNumeroActoAdministrativo());

        return dto;
    }

    public static TrazabilidadComparendoDTO toLevel1DTO(TrazabilidadComparendo entidad) {
        TrazabilidadComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));
        if (entidad.getActividad() != null)
            dto.setActividad(ActividadHelper.toLevel0DTO(entidad.getActividad()));
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getEstadoComparendo() != null)
            dto.setEstadoComparendo(EstadoComparendoHelper.toLevel0DTO(entidad.getEstadoComparendo()));

        return dto;
    }

    public static List<TrazabilidadComparendoDTO> toListLevel0DTO(List<TrazabilidadComparendo> listEntidad) {
        List<TrazabilidadComparendoDTO> listDto = new ArrayList<TrazabilidadComparendoDTO>();
        for (TrazabilidadComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TrazabilidadComparendoDTO> toListLevel1DTO(List<TrazabilidadComparendo> listEntidad) {
        List<TrazabilidadComparendoDTO> listDto = new ArrayList<TrazabilidadComparendoDTO>();
        for (TrazabilidadComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TrazabilidadComparendo toLevel0Entity(TrazabilidadComparendoDTO dto, TrazabilidadComparendo entidad) {
        if (null == entidad) {
            entidad = new TrazabilidadComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaEjecucion(dto.getFechaEjecucion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setUsuarioExterno(dto.getUsuarioExterno());
        entidad.setNumeroActoAdministrativo(dto.getNumeroActoAdministrativo());

        return entidad;
    }

    public static TrazabilidadComparendo toLevel1Entity(TrazabilidadComparendoDTO dto, TrazabilidadComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        if (dto.getActividad() != null) {
            entidad.setActividad(new Actividad());
            entidad.getActividad().setId(dto.getActividad().getId());
        }
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getEstadoComparendo() != null) {
            entidad.setEstadoComparendo(new EstadoComparendo());
            entidad.getEstadoComparendo().setId(dto.getEstadoComparendo().getId());
        }

        return entidad;
    }

    public static List<TrazabilidadComparendo> toListLevel0Entity(List<TrazabilidadComparendoDTO> listDto) {
        List<TrazabilidadComparendo> listEntidad = new ArrayList<TrazabilidadComparendo>();
        for (TrazabilidadComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TrazabilidadComparendo> toListLevel1Entity(List<TrazabilidadComparendoDTO> listDto) {
        List<TrazabilidadComparendo> listEntidad = new ArrayList<TrazabilidadComparendo>();
        for (TrazabilidadComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
