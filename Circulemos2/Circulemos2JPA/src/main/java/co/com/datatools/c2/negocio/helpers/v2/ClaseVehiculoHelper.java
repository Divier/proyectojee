package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.entidades.ClaseVehiculo;

/**
 * @author Generated
 * @version 3.0 - Fri Oct 09 16:00:23 COT 2015
 */
public class ClaseVehiculoHelper {
    // --- to DTO
    public static ClaseVehiculoDTO toLevel0DTO(ClaseVehiculo entidad) {
        ClaseVehiculoDTO dto = new ClaseVehiculoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setActivo(entidad.getActivo());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ClaseVehiculoDTO toLevel1DTO(ClaseVehiculo entidad) {
        ClaseVehiculoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ClaseVehiculoDTO> toListLevel0DTO(List<ClaseVehiculo> listEntidad) {
        List<ClaseVehiculoDTO> listDto = new ArrayList<ClaseVehiculoDTO>();
        for (ClaseVehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ClaseVehiculoDTO> toListLevel1DTO(List<ClaseVehiculo> listEntidad) {
        List<ClaseVehiculoDTO> listDto = new ArrayList<ClaseVehiculoDTO>();
        for (ClaseVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ClaseVehiculo toLevel0Entity(ClaseVehiculoDTO dto, ClaseVehiculo entidad) {
        if (null == entidad) {
            entidad = new ClaseVehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getActivo());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ClaseVehiculo toLevel1Entity(ClaseVehiculoDTO dto, ClaseVehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ClaseVehiculo> toListLevel0Entity(List<ClaseVehiculoDTO> listDto) {
        List<ClaseVehiculo> listEntidad = new ArrayList<ClaseVehiculo>();
        for (ClaseVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ClaseVehiculo> toListLevel1Entity(List<ClaseVehiculoDTO> listDto) {
        List<ClaseVehiculo> listEntidad = new ArrayList<ClaseVehiculo>();
        for (ClaseVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}