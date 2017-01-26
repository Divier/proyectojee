package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ModuloDTO;
import co.com.datatools.c2.entidades.Modulo;

/**
 * @author Generated
 * @version 3.0 - Mon Mar 14 17:55:03 COT 2016
 */
public class ModuloHelper {
    // --- to DTO
    public static ModuloDTO toLevel0DTO(Modulo entidad) {
        ModuloDTO dto = new ModuloDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static ModuloDTO toLevel1DTO(Modulo entidad) {
        ModuloDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ModuloDTO> toListLevel0DTO(List<Modulo> listEntidad) {
        List<ModuloDTO> listDto = new ArrayList<ModuloDTO>();
        for (Modulo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ModuloDTO> toListLevel1DTO(List<Modulo> listEntidad) {
        List<ModuloDTO> listDto = new ArrayList<ModuloDTO>();
        for (Modulo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Modulo toLevel0Entity(ModuloDTO dto, Modulo entidad) {
        if (null == entidad) {
            entidad = new Modulo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static Modulo toLevel1Entity(ModuloDTO dto, Modulo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Modulo> toListLevel0Entity(List<ModuloDTO> listDto) {
        List<Modulo> listEntidad = new ArrayList<Modulo>();
        for (ModuloDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Modulo> toListLevel1Entity(List<ModuloDTO> listDto) {
        List<Modulo> listEntidad = new ArrayList<Modulo>();
        for (ModuloDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
