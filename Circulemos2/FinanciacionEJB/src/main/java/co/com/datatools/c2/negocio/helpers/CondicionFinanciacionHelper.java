package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.entidades.CondicionFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:44:16 COT 2016
 */
public class CondicionFinanciacionHelper {
    // --- to DTO
    public static CondicionFinanciacionDTO toLevel0DTO(CondicionFinanciacion entidad) {
        CondicionFinanciacionDTO dto = new CondicionFinanciacionDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setOrden(entidad.getOrden());

        return dto;
    }

    public static CondicionFinanciacionDTO toLevel1DTO(CondicionFinanciacion entidad) {
        CondicionFinanciacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<CondicionFinanciacionDTO> toListLevel0DTO(List<CondicionFinanciacion> listEntidad) {
        List<CondicionFinanciacionDTO> listDto = new ArrayList<CondicionFinanciacionDTO>();
        for (CondicionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CondicionFinanciacionDTO> toListLevel1DTO(List<CondicionFinanciacion> listEntidad) {
        List<CondicionFinanciacionDTO> listDto = new ArrayList<CondicionFinanciacionDTO>();
        for (CondicionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CondicionFinanciacion toLevel0Entity(CondicionFinanciacionDTO dto, CondicionFinanciacion entidad) {
        if (null == entidad) {
            entidad = new CondicionFinanciacion();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setOrden(dto.getOrden());

        return entidad;
    }

    public static CondicionFinanciacion toLevel1Entity(CondicionFinanciacionDTO dto, CondicionFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<CondicionFinanciacion> toListLevel0Entity(List<CondicionFinanciacionDTO> listDto) {
        List<CondicionFinanciacion> listEntidad = new ArrayList<CondicionFinanciacion>();
        for (CondicionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CondicionFinanciacion> toListLevel1Entity(List<CondicionFinanciacionDTO> listDto) {
        List<CondicionFinanciacion> listEntidad = new ArrayList<CondicionFinanciacion>();
        for (CondicionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
