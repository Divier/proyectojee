package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CarrilDTO;
import co.com.datatools.c2.entidades.Carril;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:58:37 COT 2016
 */
public class CarrilHelper {
    // --- to DTO
    public static CarrilDTO toLevel0DTO(Carril entidad) {
        CarrilDTO dto = new CarrilDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static CarrilDTO toLevel1DTO(Carril entidad) {
        CarrilDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<CarrilDTO> toListLevel0DTO(List<Carril> listEntidad) {
        List<CarrilDTO> listDto = new ArrayList<CarrilDTO>();
        for (Carril entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CarrilDTO> toListLevel1DTO(List<Carril> listEntidad) {
        List<CarrilDTO> listDto = new ArrayList<CarrilDTO>();
        for (Carril entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Carril toLevel0Entity(CarrilDTO dto, Carril entidad) {
        if (null == entidad) {
            entidad = new Carril();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static Carril toLevel1Entity(CarrilDTO dto, Carril entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Carril> toListLevel0Entity(List<CarrilDTO> listDto) {
        List<Carril> listEntidad = new ArrayList<Carril>();
        for (CarrilDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Carril> toListLevel1Entity(List<CarrilDTO> listDto) {
        List<Carril> listEntidad = new ArrayList<Carril>();
        for (CarrilDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
