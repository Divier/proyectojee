package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.RadicarExcepcion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class RadicarExcepcionHelper {
    // --- to DTO
    public static RadicarExcepcionDTO toLevel0DTO(RadicarExcepcion entidad) {
        RadicarExcepcionDTO dto = new RadicarExcepcionDTO();
        dto.setIdRadicarExcepcion(entidad.getIdRadicarExcepcion());
        dto.setObservaciones(entidad.getObservaciones());
        dto.setFechaExcepcion(entidad.getFechaExcepcion());
        dto.setFechaFalloExcepcion(entidad.getFechaFalloExcepcion());
        dto.setObservacionesFallo(entidad.getObservacionesFallo());
        dto.setFalloAFavor(entidad.getFalloAFavor());
        return dto;
    }

    public static RadicarExcepcionDTO toLevel1DTO(RadicarExcepcion entidad) {
        RadicarExcepcionDTO dto = toLevel0DTO(entidad);
        if (entidad.getCoactivo() != null)
            dto.setCoactivoDTO(CoactivoHelper.toLevel0DTO(entidad.getCoactivo()));

        return dto;
    }

    public static List<RadicarExcepcionDTO> toListLevel0DTO(List<RadicarExcepcion> listEntidad) {
        List<RadicarExcepcionDTO> listDto = new ArrayList<RadicarExcepcionDTO>();
        for (RadicarExcepcion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RadicarExcepcionDTO> toListLevel1DTO(List<RadicarExcepcion> listEntidad) {
        List<RadicarExcepcionDTO> listDto = new ArrayList<RadicarExcepcionDTO>();
        for (RadicarExcepcion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RadicarExcepcion toLevel0Entity(RadicarExcepcionDTO dto, RadicarExcepcion entidad) {
        if (null == entidad) {
            entidad = new RadicarExcepcion();
        }
        entidad.setIdRadicarExcepcion(dto.getIdRadicarExcepcion());
        entidad.setObservaciones(dto.getObservaciones());
        entidad.setFechaExcepcion(dto.getFechaExcepcion());
        entidad.setFechaFalloExcepcion(dto.getFechaFalloExcepcion());
        entidad.setObservacionesFallo(dto.getObservacionesFallo());
        entidad.setFalloAFavor(dto.getFalloAFavor());
        return entidad;
    }

    public static RadicarExcepcion toLevel1Entity(RadicarExcepcionDTO dto, RadicarExcepcion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCoactivoDTO() != null) {
            entidad.setCoactivo(new Coactivo());
            entidad.getCoactivo().setId(dto.getCoactivoDTO().getId());
        }

        return entidad;
    }

    public static List<RadicarExcepcion> toListLevel0Entity(List<RadicarExcepcionDTO> listDto) {
        List<RadicarExcepcion> listEntidad = new ArrayList<RadicarExcepcion>();
        for (RadicarExcepcionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RadicarExcepcion> toListLevel1Entity(List<RadicarExcepcionDTO> listDto) {
        List<RadicarExcepcion> listEntidad = new ArrayList<RadicarExcepcion>();
        for (RadicarExcepcionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
