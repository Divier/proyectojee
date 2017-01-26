package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoVariableFinanciacionDTO;
import co.com.datatools.c2.entidades.TipoVariableFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:08:16 COT 2016
 */
public class TipoVariableFinanciacionHelper {
    // --- to DTO
    public static TipoVariableFinanciacionDTO toLevel0DTO(TipoVariableFinanciacion entidad) {
        TipoVariableFinanciacionDTO dto = new TipoVariableFinanciacionDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoVariableFinanciacionDTO toLevel1DTO(TipoVariableFinanciacion entidad) {
        TipoVariableFinanciacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoVariableFinanciacionDTO> toListLevel0DTO(List<TipoVariableFinanciacion> listEntidad) {
        List<TipoVariableFinanciacionDTO> listDto = new ArrayList<TipoVariableFinanciacionDTO>();
        for (TipoVariableFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoVariableFinanciacionDTO> toListLevel1DTO(List<TipoVariableFinanciacion> listEntidad) {
        List<TipoVariableFinanciacionDTO> listDto = new ArrayList<TipoVariableFinanciacionDTO>();
        for (TipoVariableFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoVariableFinanciacion toLevel0Entity(TipoVariableFinanciacionDTO dto,
            TipoVariableFinanciacion entidad) {
        if (null == entidad) {
            entidad = new TipoVariableFinanciacion();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoVariableFinanciacion toLevel1Entity(TipoVariableFinanciacionDTO dto,
            TipoVariableFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoVariableFinanciacion> toListLevel0Entity(List<TipoVariableFinanciacionDTO> listDto) {
        List<TipoVariableFinanciacion> listEntidad = new ArrayList<TipoVariableFinanciacion>();
        for (TipoVariableFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoVariableFinanciacion> toListLevel1Entity(List<TipoVariableFinanciacionDTO> listDto) {
        List<TipoVariableFinanciacion> listEntidad = new ArrayList<TipoVariableFinanciacion>();
        for (TipoVariableFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
