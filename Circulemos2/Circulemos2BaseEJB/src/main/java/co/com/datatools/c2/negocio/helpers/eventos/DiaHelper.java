package co.com.datatools.c2.negocio.helpers.eventos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.eventos.DiaDTO;
import co.com.datatools.c2.entidades.eventos.Dia;



/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:25:49 COT 2016
 */
public class DiaHelper {
    // --- to DTO
    public static DiaDTO toLevel0DTO(Dia entidad) {
        DiaDTO dto = new DiaDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static DiaDTO toLevel1DTO(Dia entidad) {
        DiaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<DiaDTO> toListLevel0DTO(List<Dia> listEntidad) {
        List<DiaDTO> listDto = new ArrayList<DiaDTO>();
        for (Dia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DiaDTO> toListLevel1DTO(List<Dia> listEntidad) {
        List<DiaDTO> listDto = new ArrayList<DiaDTO>();
        for (Dia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Dia toLevel0Entity(DiaDTO dto, Dia entidad) {
        if (null == entidad) {
            entidad = new Dia();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        return entidad;
    }

    public static Dia toLevel1Entity(DiaDTO dto, Dia entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Dia> toListLevel0Entity(List<DiaDTO> listDto) {
        List<Dia> listEntidad = new ArrayList<Dia>();
        for (DiaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Dia> toListLevel1Entity(List<DiaDTO> listDto) {
        List<Dia> listEntidad = new ArrayList<Dia>();
        for (DiaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
