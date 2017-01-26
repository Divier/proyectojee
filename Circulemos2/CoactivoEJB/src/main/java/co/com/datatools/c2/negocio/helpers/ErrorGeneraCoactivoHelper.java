package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ErrorGeneraCoactivoDTO;
import co.com.datatools.c2.entidades.ErrorGeneraCoactivo;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:45 COT 2016
 */
public class ErrorGeneraCoactivoHelper {
    // --- to DTO
    public static ErrorGeneraCoactivoDTO toLevel0DTO(ErrorGeneraCoactivo entidad) {
        ErrorGeneraCoactivoDTO dto = new ErrorGeneraCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorGeneraCoactivoDTO toLevel1DTO(ErrorGeneraCoactivo entidad) {
        ErrorGeneraCoactivoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorGeneraCoactivoDTO> toListLevel0DTO(List<ErrorGeneraCoactivo> listEntidad) {
        List<ErrorGeneraCoactivoDTO> listDto = new ArrayList<ErrorGeneraCoactivoDTO>();
        for (ErrorGeneraCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorGeneraCoactivoDTO> toListLevel1DTO(List<ErrorGeneraCoactivo> listEntidad) {
        List<ErrorGeneraCoactivoDTO> listDto = new ArrayList<ErrorGeneraCoactivoDTO>();
        for (ErrorGeneraCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorGeneraCoactivo toLevel0Entity(ErrorGeneraCoactivoDTO dto, ErrorGeneraCoactivo entidad) {
        if (null == entidad) {
            entidad = new ErrorGeneraCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorGeneraCoactivo toLevel1Entity(ErrorGeneraCoactivoDTO dto, ErrorGeneraCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorGeneraCoactivo> toListLevel0Entity(List<ErrorGeneraCoactivoDTO> listDto) {
        List<ErrorGeneraCoactivo> listEntidad = new ArrayList<ErrorGeneraCoactivo>();
        for (ErrorGeneraCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorGeneraCoactivo> toListLevel1Entity(List<ErrorGeneraCoactivoDTO> listDto) {
        List<ErrorGeneraCoactivo> listEntidad = new ArrayList<ErrorGeneraCoactivo>();
        for (ErrorGeneraCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
