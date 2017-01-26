package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoVariableCoactivoDTO;
import co.com.datatools.c2.entidades.TipoVariableCoactivo;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:26:10 COT 2016
 */
public class TipoVariableCoactivoHelper {
    // --- to DTO
    public static TipoVariableCoactivoDTO toLevel0DTO(TipoVariableCoactivo entidad) {
        TipoVariableCoactivoDTO dto = new TipoVariableCoactivoDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoVariableCoactivoDTO toLevel1DTO(TipoVariableCoactivo entidad) {
        TipoVariableCoactivoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoVariableCoactivoDTO> toListLevel0DTO(List<TipoVariableCoactivo> listEntidad) {
        List<TipoVariableCoactivoDTO> listDto = new ArrayList<TipoVariableCoactivoDTO>();
        for (TipoVariableCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoVariableCoactivoDTO> toListLevel1DTO(List<TipoVariableCoactivo> listEntidad) {
        List<TipoVariableCoactivoDTO> listDto = new ArrayList<TipoVariableCoactivoDTO>();
        for (TipoVariableCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoVariableCoactivo toLevel0Entity(TipoVariableCoactivoDTO dto, TipoVariableCoactivo entidad) {
        if (null == entidad) {
            entidad = new TipoVariableCoactivo();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoVariableCoactivo toLevel1Entity(TipoVariableCoactivoDTO dto, TipoVariableCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoVariableCoactivo> toListLevel0Entity(List<TipoVariableCoactivoDTO> listDto) {
        List<TipoVariableCoactivo> listEntidad = new ArrayList<TipoVariableCoactivo>();
        for (TipoVariableCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoVariableCoactivo> toListLevel1Entity(List<TipoVariableCoactivoDTO> listDto) {
        List<TipoVariableCoactivo> listEntidad = new ArrayList<TipoVariableCoactivo>();
        for (TipoVariableCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
