package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ArchivoExcepcionDTO;
import co.com.datatools.c2.entidades.ArchivoExcepcion;
import co.com.datatools.c2.entidades.RadicarExcepcion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class ArchivoExcepcionHelper {
    // --- to DTO
    public static ArchivoExcepcionDTO toLevel0DTO(ArchivoExcepcion entidad) {
        ArchivoExcepcionDTO dto = new ArchivoExcepcionDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setNombreArchivo(entidad.getNombreArchivo());
        dto.setNumeroArchivo(entidad.getNumeroArchivo());
        dto.setFalloExcepcion(entidad.getFalloExcepcion());

        return dto;
    }

    public static ArchivoExcepcionDTO toLevel1DTO(ArchivoExcepcion entidad) {
        ArchivoExcepcionDTO dto = toLevel0DTO(entidad);
        if (entidad.getRadicarExcepcion() != null)
            dto.setRadicarExcepcionDTO(RadicarExcepcionHelper.toLevel0DTO(entidad.getRadicarExcepcion()));

        return dto;
    }

    public static List<ArchivoExcepcionDTO> toListLevel0DTO(List<ArchivoExcepcion> listEntidad) {
        List<ArchivoExcepcionDTO> listDto = new ArrayList<ArchivoExcepcionDTO>();
        for (ArchivoExcepcion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ArchivoExcepcionDTO> toListLevel1DTO(List<ArchivoExcepcion> listEntidad) {
        List<ArchivoExcepcionDTO> listDto = new ArrayList<ArchivoExcepcionDTO>();
        for (ArchivoExcepcion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ArchivoExcepcion toLevel0Entity(ArchivoExcepcionDTO dto, ArchivoExcepcion entidad) {
        if (null == entidad) {
            entidad = new ArchivoExcepcion();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setNombreArchivo(dto.getNombreArchivo());
        entidad.setNumeroArchivo(dto.getNumeroArchivo());
        entidad.setFalloExcepcion(dto.getFalloExcepcion());

        return entidad;
    }

    public static ArchivoExcepcion toLevel1Entity(ArchivoExcepcionDTO dto, ArchivoExcepcion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getRadicarExcepcionDTO() != null) {
            entidad.setRadicarExcepcion(new RadicarExcepcion());
            entidad.getRadicarExcepcion().setIdRadicarExcepcion(dto.getRadicarExcepcionDTO().getIdRadicarExcepcion());
        }

        return entidad;
    }

    public static List<ArchivoExcepcion> toListLevel0Entity(List<ArchivoExcepcionDTO> listDto) {
        List<ArchivoExcepcion> listEntidad = new ArrayList<ArchivoExcepcion>();
        for (ArchivoExcepcionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ArchivoExcepcion> toListLevel1Entity(List<ArchivoExcepcionDTO> listDto) {
        List<ArchivoExcepcion> listEntidad = new ArrayList<ArchivoExcepcion>();
        for (ArchivoExcepcionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
