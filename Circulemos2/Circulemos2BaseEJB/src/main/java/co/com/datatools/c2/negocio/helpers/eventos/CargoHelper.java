package co.com.datatools.c2.negocio.helpers.eventos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.eventos.CargoDTO;
import co.com.datatools.c2.entidades.eventos.Cargo;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:25:49 COT 2016
 */
public class CargoHelper {
    // --- to DTO
    public static CargoDTO toLevel0DTO(Cargo entidad) {
        CargoDTO dto = new CargoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        return dto;
    }

    public static CargoDTO toLevel1DTO(Cargo entidad) {
        CargoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<CargoDTO> toListLevel0DTO(List<Cargo> listEntidad) {
        List<CargoDTO> listDto = new ArrayList<CargoDTO>();
        for (Cargo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CargoDTO> toListLevel1DTO(List<Cargo> listEntidad) {
        List<CargoDTO> listDto = new ArrayList<CargoDTO>();
        for (Cargo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Cargo toLevel0Entity(CargoDTO dto, Cargo entidad) {
        if (null == entidad) {
            entidad = new Cargo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static Cargo toLevel1Entity(CargoDTO dto, Cargo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Cargo> toListLevel0Entity(List<CargoDTO> listDto) {
        List<Cargo> listEntidad = new ArrayList<Cargo>();
        for (CargoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Cargo> toListLevel1Entity(List<CargoDTO> listDto) {
        List<Cargo> listEntidad = new ArrayList<Cargo>();
        for (CargoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
