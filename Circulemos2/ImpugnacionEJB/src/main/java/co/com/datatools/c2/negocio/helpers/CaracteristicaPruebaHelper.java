package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CaracteristicaPruebaDTO;
import co.com.datatools.c2.entidades.CaracteristicaPrueba;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:08:57 COT 2016
 */
public class CaracteristicaPruebaHelper {
    // --- to DTO
    public static CaracteristicaPruebaDTO toLevel0DTO(CaracteristicaPrueba entidad) {
        CaracteristicaPruebaDTO dto = new CaracteristicaPruebaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static CaracteristicaPruebaDTO toLevel1DTO(CaracteristicaPrueba entidad) {
        CaracteristicaPruebaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<CaracteristicaPruebaDTO> toListLevel0DTO(List<CaracteristicaPrueba> listEntidad) {
        List<CaracteristicaPruebaDTO> listDto = new ArrayList<CaracteristicaPruebaDTO>();
        for (CaracteristicaPrueba entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CaracteristicaPruebaDTO> toListLevel1DTO(List<CaracteristicaPrueba> listEntidad) {
        List<CaracteristicaPruebaDTO> listDto = new ArrayList<CaracteristicaPruebaDTO>();
        for (CaracteristicaPrueba entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CaracteristicaPrueba toLevel0Entity(CaracteristicaPruebaDTO dto, CaracteristicaPrueba entidad) {
        if (null == entidad) {
            entidad = new CaracteristicaPrueba();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static CaracteristicaPrueba toLevel1Entity(CaracteristicaPruebaDTO dto, CaracteristicaPrueba entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<CaracteristicaPrueba> toListLevel0Entity(List<CaracteristicaPruebaDTO> listDto) {
        List<CaracteristicaPrueba> listEntidad = new ArrayList<CaracteristicaPrueba>();
        for (CaracteristicaPruebaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CaracteristicaPrueba> toListLevel1Entity(List<CaracteristicaPruebaDTO> listDto) {
        List<CaracteristicaPrueba> listEntidad = new ArrayList<CaracteristicaPrueba>();
        for (CaracteristicaPruebaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
