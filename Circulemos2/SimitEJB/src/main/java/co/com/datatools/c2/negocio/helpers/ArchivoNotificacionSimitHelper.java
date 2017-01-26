package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ArchivoNotificacionSimitDTO;
import co.com.datatools.c2.entidades.ArchivoNotificacionSimit;
import co.com.datatools.c2.entidades.NotificacionSimit;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 02 16:04:14 COT 2015
 */
public class ArchivoNotificacionSimitHelper {
    // --- to DTO
    public static ArchivoNotificacionSimitDTO toLevel0DTO(ArchivoNotificacionSimit entidad) {
        ArchivoNotificacionSimitDTO dto = new ArchivoNotificacionSimitDTO();
        dto.setId(entidad.getId());
        dto.setIdDocumento(entidad.getIdDocumento());
        dto.setRutaDocumento(entidad.getRutaDocumento());
        dto.setNumeroOficio(entidad.getNumeroOficio());
        dto.setCantidadRegistros(entidad.getCantidadRegistros());

        return dto;
    }

    public static ArchivoNotificacionSimitDTO toLevel1DTO(ArchivoNotificacionSimit entidad) {
        ArchivoNotificacionSimitDTO dto = toLevel0DTO(entidad);
        if (entidad.getNotificacionSimit() != null)
            dto.setNotificacionSimit(NotificacionSimitHelper.toLevel0DTO(entidad.getNotificacionSimit()));

        return dto;
    }

    public static List<ArchivoNotificacionSimitDTO> toListLevel0DTO(List<ArchivoNotificacionSimit> listEntidad) {
        List<ArchivoNotificacionSimitDTO> listDto = new ArrayList<ArchivoNotificacionSimitDTO>();
        for (ArchivoNotificacionSimit entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ArchivoNotificacionSimitDTO> toListLevel1DTO(List<ArchivoNotificacionSimit> listEntidad) {
        List<ArchivoNotificacionSimitDTO> listDto = new ArrayList<ArchivoNotificacionSimitDTO>();
        for (ArchivoNotificacionSimit entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ArchivoNotificacionSimit toLevel0Entity(ArchivoNotificacionSimitDTO dto,
            ArchivoNotificacionSimit entidad) {
        if (null == entidad) {
            entidad = new ArchivoNotificacionSimit();
        }
        entidad.setId(dto.getId());
        entidad.setIdDocumento(dto.getIdDocumento());
        entidad.setRutaDocumento(dto.getRutaDocumento());
        entidad.setNumeroOficio(dto.getNumeroOficio());

        return entidad;
    }

    public static ArchivoNotificacionSimit toLevel1Entity(ArchivoNotificacionSimitDTO dto,
            ArchivoNotificacionSimit entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getNotificacionSimit() != null) {
            entidad.setNotificacionSimit(new NotificacionSimit());
            entidad.getNotificacionSimit().setId(dto.getNotificacionSimit().getId());
        }

        return entidad;
    }

    public static List<ArchivoNotificacionSimit> toListLevel0Entity(List<ArchivoNotificacionSimitDTO> listDto) {
        List<ArchivoNotificacionSimit> listEntidad = new ArrayList<ArchivoNotificacionSimit>();
        for (ArchivoNotificacionSimitDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ArchivoNotificacionSimit> toListLevel1Entity(List<ArchivoNotificacionSimitDTO> listDto) {
        List<ArchivoNotificacionSimit> listEntidad = new ArrayList<ArchivoNotificacionSimit>();
        for (ArchivoNotificacionSimitDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
