package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.entidades.comun.Pais;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class PaisHelper {
    // --- to DTO
    public static PaisDTO toLevel0DTO(Pais entidad) {
        PaisDTO dto = new PaisDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static PaisDTO toLevel1DTO(Pais entidad) {
        PaisDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<PaisDTO> toListLevel0DTO(List<Pais> listEntidad) {
        List<PaisDTO> listDto = new ArrayList<PaisDTO>();
        for (Pais entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PaisDTO> toListLevel1DTO(List<Pais> listEntidad) {
        List<PaisDTO> listDto = new ArrayList<PaisDTO>();
        for (Pais entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Pais toLevel0Entity(PaisDTO dto, Pais entidad) {
        if (null == entidad) {
            entidad = new Pais();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static Pais toLevel1Entity(PaisDTO dto, Pais entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Pais> toListLevel0Entity(List<PaisDTO> listDto) {
        List<Pais> listEntidad = new ArrayList<Pais>();
        for (PaisDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Pais> toListLevel1Entity(List<PaisDTO> listDto) {
        List<Pais> listEntidad = new ArrayList<Pais>();
        for (PaisDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
