package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.entidades.CondicionCoactivo;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:25:14 COT 2016
 */
public class CondicionCoactivoHelper {
    // --- to DTO
    public static CondicionCoactivoDTO toLevel0DTO(CondicionCoactivo entidad) {
        CondicionCoactivoDTO dto = new CondicionCoactivoDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setOrden(entidad.getOrden());

        return dto;
    }

    public static CondicionCoactivoDTO toLevel1DTO(CondicionCoactivo entidad) {
        CondicionCoactivoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<CondicionCoactivoDTO> toListLevel0DTO(List<CondicionCoactivo> listEntidad) {
        List<CondicionCoactivoDTO> listDto = new ArrayList<CondicionCoactivoDTO>();
        for (CondicionCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CondicionCoactivoDTO> toListLevel1DTO(List<CondicionCoactivo> listEntidad) {
        List<CondicionCoactivoDTO> listDto = new ArrayList<CondicionCoactivoDTO>();
        for (CondicionCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CondicionCoactivo toLevel0Entity(CondicionCoactivoDTO dto, CondicionCoactivo entidad) {
        if (null == entidad) {
            entidad = new CondicionCoactivo();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setOrden(dto.getOrden());

        return entidad;
    }

    public static CondicionCoactivo toLevel1Entity(CondicionCoactivoDTO dto, CondicionCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<CondicionCoactivo> toListLevel0Entity(List<CondicionCoactivoDTO> listDto) {
        List<CondicionCoactivo> listEntidad = new ArrayList<CondicionCoactivo>();
        for (CondicionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CondicionCoactivo> toListLevel1Entity(List<CondicionCoactivoDTO> listDto) {
        List<CondicionCoactivo> listEntidad = new ArrayList<CondicionCoactivo>();
        for (CondicionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
