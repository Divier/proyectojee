package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoVariableDTO;
import co.com.datatools.c2.entidades.TipoVariable;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 11:34:49 COT 2014
 */
public class TipoVariableHelper {
    // --- to DTO
    public static TipoVariableDTO toLevel0DTO(TipoVariable entidad) {
        TipoVariableDTO dto = new TipoVariableDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoVariableDTO toLevel1DTO(TipoVariable entidad) {
        TipoVariableDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoVariableDTO> toListLevel0DTO(List<TipoVariable> listEntidad) {
        List<TipoVariableDTO> listDto = new ArrayList<TipoVariableDTO>();
        for (TipoVariable entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoVariableDTO> toListLevel1DTO(List<TipoVariable> listEntidad) {
        List<TipoVariableDTO> listDto = new ArrayList<TipoVariableDTO>();
        for (TipoVariable entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoVariable toLevel0Entity(TipoVariableDTO dto, TipoVariable entidad) {
        if (null == entidad) {
            entidad = new TipoVariable();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoVariable toLevel1Entity(TipoVariableDTO dto, TipoVariable entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoVariable> toListLevel0Entity(List<TipoVariableDTO> listDto) {
        List<TipoVariable> listEntidad = new ArrayList<TipoVariable>();
        for (TipoVariableDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoVariable> toListLevel1Entity(List<TipoVariableDTO> listDto) {
        List<TipoVariable> listEntidad = new ArrayList<TipoVariable>();
        for (TipoVariableDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
