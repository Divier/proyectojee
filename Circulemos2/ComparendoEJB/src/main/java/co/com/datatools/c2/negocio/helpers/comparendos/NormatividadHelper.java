package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.NormatividadDTO;
import co.com.datatools.c2.entidades.Normatividad;
import co.com.datatools.c2.entidades.OrdenamientoPais;

/**
 * @author Generated
 * @version 3.0 - Thu May 29 11:29:15 COT 2014
 */
public class NormatividadHelper {
    // --- to DTO
    public static NormatividadDTO toLevel0DTO(Normatividad entidad) {
        NormatividadDTO dto = new NormatividadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static NormatividadDTO toLevel1DTO(Normatividad entidad) {
        NormatividadDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrdenamientoPais() != null)
            dto.setOrdenamientoPais(OrdenamientoPaisHelper.toLevel0DTO(entidad.getOrdenamientoPais()));

        return dto;
    }

    public static List<NormatividadDTO> toListLevel0DTO(List<Normatividad> listEntidad) {
        List<NormatividadDTO> listDto = new ArrayList<NormatividadDTO>();
        for (Normatividad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NormatividadDTO> toListLevel1DTO(List<Normatividad> listEntidad) {
        List<NormatividadDTO> listDto = new ArrayList<NormatividadDTO>();
        for (Normatividad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Normatividad toLevel0Entity(NormatividadDTO dto, Normatividad entidad) {
        if (null == entidad) {
            entidad = new Normatividad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static Normatividad toLevel1Entity(NormatividadDTO dto, Normatividad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrdenamientoPais() != null) {
            entidad.setOrdenamientoPais(new OrdenamientoPais());
            entidad.getOrdenamientoPais().setId(dto.getOrdenamientoPais().getId());
        }

        return entidad;
    }

    public static List<Normatividad> toListLevel0Entity(List<NormatividadDTO> listDto) {
        List<Normatividad> listEntidad = new ArrayList<Normatividad>();
        for (NormatividadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Normatividad> toListLevel1Entity(List<NormatividadDTO> listDto) {
        List<Normatividad> listEntidad = new ArrayList<Normatividad>();
        for (NormatividadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
