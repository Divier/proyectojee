package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.OrigenPruebaDTO;
import co.com.datatools.c2.entidades.OrigenPrueba;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:07:57 COT 2016
 */
public class OrigenPruebaHelper {
    // --- to DTO
    public static OrigenPruebaDTO toLevel0DTO(OrigenPrueba entidad) {
        OrigenPruebaDTO dto = new OrigenPruebaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static OrigenPruebaDTO toLevel1DTO(OrigenPrueba entidad) {
        OrigenPruebaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<OrigenPruebaDTO> toListLevel0DTO(List<OrigenPrueba> listEntidad) {
        List<OrigenPruebaDTO> listDto = new ArrayList<OrigenPruebaDTO>();
        for (OrigenPrueba entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<OrigenPruebaDTO> toListLevel1DTO(List<OrigenPrueba> listEntidad) {
        List<OrigenPruebaDTO> listDto = new ArrayList<OrigenPruebaDTO>();
        for (OrigenPrueba entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static OrigenPrueba toLevel0Entity(OrigenPruebaDTO dto, OrigenPrueba entidad) {
        if (null == entidad) {
            entidad = new OrigenPrueba();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static OrigenPrueba toLevel1Entity(OrigenPruebaDTO dto, OrigenPrueba entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<OrigenPrueba> toListLevel0Entity(List<OrigenPruebaDTO> listDto) {
        List<OrigenPrueba> listEntidad = new ArrayList<OrigenPrueba>();
        for (OrigenPruebaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<OrigenPrueba> toListLevel1Entity(List<OrigenPruebaDTO> listDto) {
        List<OrigenPrueba> listEntidad = new ArrayList<OrigenPrueba>();
        for (OrigenPruebaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
