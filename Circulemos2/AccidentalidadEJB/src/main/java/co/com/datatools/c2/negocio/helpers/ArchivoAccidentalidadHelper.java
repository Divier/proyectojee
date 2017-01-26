package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ArchivoAccidentalidadDTO;
import co.com.datatools.c2.entidades.Accidentalidad;
import co.com.datatools.c2.entidades.ArchivoAccidentalidad;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:02:58 COT 2016
 */
public class ArchivoAccidentalidadHelper {
    // --- to DTO
    public static ArchivoAccidentalidadDTO toLevel0DTO(ArchivoAccidentalidad entidad) {
        ArchivoAccidentalidadDTO dto = new ArchivoAccidentalidadDTO();
        dto.setId(entidad.getId());
        dto.setNumeroArchivo(entidad.getNumeroArchivo());
        dto.setNombreArchivo(entidad.getNombreArchivo());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        return dto;

    }

    public static ArchivoAccidentalidadDTO toLevel1DTO(ArchivoAccidentalidad entidad) {
        ArchivoAccidentalidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getAccidentalidad() != null)
            dto.setAccidentalidad(AccidentalidadHelper.toLevel0DTO(entidad.getAccidentalidad()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<ArchivoAccidentalidadDTO> toListLevel0DTO(List<ArchivoAccidentalidad> listEntidad) {
        List<ArchivoAccidentalidadDTO> listDto = new ArrayList<ArchivoAccidentalidadDTO>();
        for (ArchivoAccidentalidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ArchivoAccidentalidadDTO> toListLevel1DTO(List<ArchivoAccidentalidad> listEntidad) {
        List<ArchivoAccidentalidadDTO> listDto = new ArrayList<ArchivoAccidentalidadDTO>();
        for (ArchivoAccidentalidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ArchivoAccidentalidad toLevel0Entity(ArchivoAccidentalidadDTO dto, ArchivoAccidentalidad entidad) {
        if (null == entidad) {
            entidad = new ArchivoAccidentalidad();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroArchivo(dto.getNumeroArchivo());
        entidad.setNombreArchivo(dto.getNombreArchivo());
        entidad.setFechaRegistro(dto.getFechaRegistro());

        return entidad;
    }

    public static ArchivoAccidentalidad toLevel1Entity(ArchivoAccidentalidadDTO dto, ArchivoAccidentalidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getAccidentalidad() != null) {
            entidad.setAccidentalidad(new Accidentalidad());
            entidad.getAccidentalidad().setId(dto.getAccidentalidad().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            // entidad.getUsuario().setIdUsuario(dto.getUsuario().getIdUsuario());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }

        return entidad;
    }

    public static List<ArchivoAccidentalidad> toListLevel0Entity(List<ArchivoAccidentalidadDTO> listDto) {
        List<ArchivoAccidentalidad> listEntidad = new ArrayList<ArchivoAccidentalidad>();
        for (ArchivoAccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ArchivoAccidentalidad> toListLevel1Entity(List<ArchivoAccidentalidadDTO> listDto) {
        List<ArchivoAccidentalidad> listEntidad = new ArrayList<ArchivoAccidentalidad>();
        for (ArchivoAccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
