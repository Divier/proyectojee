package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.entidades.EstadoResolucion;
import co.com.datatools.c2.entidades.Resolucion;
import co.com.datatools.c2.entidades.TipoResolucion;

/**
 * @author Generated
 * @version 3.0 - Fri Jan 29 10:54:25 COT 2016
 */
public class ResolucionHelper {
    // --- to DTO
    public static ResolucionDTO toLevel0DTO(Resolucion entidad) {
        ResolucionDTO dto = new ResolucionDTO();
        dto.setId(entidad.getId());
        dto.setAnoResolucion(entidad.getAnoResolucion());
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setFechaResolucion(entidad.getFechaResolucion());
        dto.setIdDocumento(entidad.getIdDocumento());
        dto.setNumeroResolucion(entidad.getNumeroResolucion());
        dto.setResolucionExitosa(entidad.getResolucionExitosa());

        return dto;
    }

    public static ResolucionDTO toLevel1DTO(Resolucion entidad) {
        ResolucionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoResolucion() != null)
            dto.setTipoResolucion(TipoResolucionHelper.toLevel0DTO(entidad.getTipoResolucion()));
        if (entidad.getEstadoResolucion() != null)
            dto.setEstadoResolucion(EstadoResolucionHelper.toLevel0DTO(entidad.getEstadoResolucion()));
        if (entidad.getResolucion() != null)
            dto.setResolucion(ResolucionHelper.toLevel0DTO(entidad.getResolucion()));

        return dto;
    }

    public static List<ResolucionDTO> toListLevel0DTO(List<Resolucion> listEntidad) {
        List<ResolucionDTO> listDto = new ArrayList<ResolucionDTO>();
        for (Resolucion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ResolucionDTO> toListLevel1DTO(List<Resolucion> listEntidad) {
        List<ResolucionDTO> listDto = new ArrayList<ResolucionDTO>();
        for (Resolucion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Resolucion toLevel0Entity(ResolucionDTO dto, Resolucion entidad) {
        if (null == entidad) {
            entidad = new Resolucion();
        }
        entidad.setId(dto.getId());
        entidad.setAnoResolucion(dto.getAnoResolucion());
        entidad.setCodigoOrganismo(dto.getCodigoOrganismo());
        entidad.setFechaResolucion(dto.getFechaResolucion());
        entidad.setIdDocumento(dto.getIdDocumento());
        entidad.setNumeroResolucion(dto.getNumeroResolucion());
        entidad.setResolucionExitosa(dto.getResolucionExitosa());

        return entidad;
    }

    public static Resolucion toLevel1Entity(ResolucionDTO dto, Resolucion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoResolucion() != null) {
            entidad.setTipoResolucion(new TipoResolucion());
            entidad.getTipoResolucion().setId(dto.getTipoResolucion().getId());
        }
        if (dto.getEstadoResolucion() != null) {
            entidad.setEstadoResolucion(new EstadoResolucion());
            entidad.getEstadoResolucion().setId(dto.getEstadoResolucion().getId());
        }
        if (dto.getResolucion() != null) {
            entidad.setResolucion(new Resolucion());
            entidad.getResolucion().setId(dto.getResolucion().getId());
        }

        return entidad;
    }

    public static List<Resolucion> toListLevel0Entity(List<ResolucionDTO> listDto) {
        List<Resolucion> listEntidad = new ArrayList<Resolucion>();
        for (ResolucionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Resolucion> toListLevel1Entity(List<ResolucionDTO> listDto) {
        List<Resolucion> listEntidad = new ArrayList<Resolucion>();
        for (ResolucionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
