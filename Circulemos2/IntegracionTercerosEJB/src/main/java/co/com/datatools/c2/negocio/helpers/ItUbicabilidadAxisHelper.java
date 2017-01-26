package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.ItUbicabilidadAxisDTO;
import co.com.datatools.c2.entidades.ItUbicabilidadAxis;

/**
 * @author Generated
 * @version 3.0 - Mon May 16 17:12:18 COT 2016
 */
public class ItUbicabilidadAxisHelper {
    // --- to DTO
    public static ItUbicabilidadAxisDTO toLevel0DTO(ItUbicabilidadAxis entidad) {
        ItUbicabilidadAxisDTO dto = new ItUbicabilidadAxisDTO();
        dto.setId(entidad.getId());
        dto.setIdTipoDocumentoAxis(entidad.getIdTipoDocumentoAxis());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        dto.setPrimerNombre(entidad.getPrimerNombre());
        dto.setSegundoNombre(entidad.getSegundoNombre());
        dto.setPrimerApellido(entidad.getPrimerApellido());
        dto.setSegundoApellido(entidad.getSegundoApellido());
        dto.setRazonSocial(entidad.getRazonSocial());
        dto.setDireccion(entidad.getDireccion());
        dto.setLatitud(entidad.getLatitud());
        dto.setLongitud(entidad.getLongitud());
        dto.setTelefonoFijo(entidad.getTelefonoFijo());
        dto.setTelefonoMovil(entidad.getTelefonoMovil());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setIdFuenteInformacion(entidad.getIdFuenteInformacion());
        dto.setIdEstadoLecturaAxis(entidad.getIdEstadoLecturaAxis());
        dto.setFechaReporte(entidad.getFechaReporte());

        return dto;
    }

    public static ItUbicabilidadAxisDTO toLevel1DTO(ItUbicabilidadAxis entidad) {
        ItUbicabilidadAxisDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ItUbicabilidadAxisDTO> toListLevel0DTO(List<ItUbicabilidadAxis> listEntidad) {
        List<ItUbicabilidadAxisDTO> listDto = new ArrayList<ItUbicabilidadAxisDTO>();
        for (ItUbicabilidadAxis entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItUbicabilidadAxisDTO> toListLevel1DTO(List<ItUbicabilidadAxis> listEntidad) {
        List<ItUbicabilidadAxisDTO> listDto = new ArrayList<ItUbicabilidadAxisDTO>();
        for (ItUbicabilidadAxis entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ItUbicabilidadAxis toLevel0Entity(ItUbicabilidadAxisDTO dto, ItUbicabilidadAxis entidad) {
        if (null == entidad) {
            entidad = new ItUbicabilidadAxis();
        }
        entidad.setId(dto.getId());
        entidad.setIdTipoDocumentoAxis(dto.getIdTipoDocumentoAxis());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());
        entidad.setPrimerNombre(dto.getPrimerNombre());
        entidad.setSegundoNombre(dto.getSegundoNombre());
        entidad.setPrimerApellido(dto.getPrimerApellido());
        entidad.setSegundoApellido(dto.getSegundoApellido());
        entidad.setRazonSocial(dto.getRazonSocial());
        entidad.setDireccion(dto.getDireccion());
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());
        entidad.setTelefonoFijo(dto.getTelefonoFijo());
        entidad.setTelefonoMovil(dto.getTelefonoMovil());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setIdFuenteInformacion(dto.getIdFuenteInformacion());
        entidad.setIdEstadoLecturaAxis(dto.getIdEstadoLecturaAxis());
        entidad.setFechaReporte(dto.getFechaReporte());

        return entidad;
    }

    public static ItUbicabilidadAxis toLevel1Entity(ItUbicabilidadAxisDTO dto, ItUbicabilidadAxis entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ItUbicabilidadAxis> toListLevel0Entity(List<ItUbicabilidadAxisDTO> listDto) {
        List<ItUbicabilidadAxis> listEntidad = new ArrayList<ItUbicabilidadAxis>();
        for (ItUbicabilidadAxisDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ItUbicabilidadAxis> toListLevel1Entity(List<ItUbicabilidadAxisDTO> listDto) {
        List<ItUbicabilidadAxis> listEntidad = new ArrayList<ItUbicabilidadAxis>();
        for (ItUbicabilidadAxisDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}