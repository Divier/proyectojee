package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.entidades.CampoEntidad;
import co.com.datatools.c2.entidades.Entidad;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class CampoEntidadHelper {
    // --- to DTO
    public static CampoEntidadDTO toLevel0DTO(CampoEntidad entidad) {
        CampoEntidadDTO dto = new CampoEntidadDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static CampoEntidadDTO toLevel1DTO(CampoEntidad entidad) {
        CampoEntidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getEntidad() != null)
            dto.setEntidad(EntidadHelper.toLevel0DTO(entidad.getEntidad()));

        return dto;
    }

    public static List<CampoEntidadDTO> toListLevel0DTO(List<CampoEntidad> listEntidad) {
        List<CampoEntidadDTO> listDto = new ArrayList<CampoEntidadDTO>();
        for (CampoEntidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CampoEntidadDTO> toListLevel1DTO(List<CampoEntidad> listEntidad) {
        List<CampoEntidadDTO> listDto = new ArrayList<CampoEntidadDTO>();
        for (CampoEntidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CampoEntidad toLevel0Entity(CampoEntidadDTO dto, CampoEntidad entidad) {
        if (null == entidad) {
            entidad = new CampoEntidad();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static CampoEntidad toLevel1Entity(CampoEntidadDTO dto, CampoEntidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEntidad() != null) {
            entidad.setEntidad(new Entidad());
            entidad.getEntidad().setCodigo(dto.getEntidad().getCodigo());
        }

        return entidad;
    }

    public static List<CampoEntidad> toListLevel0Entity(List<CampoEntidadDTO> listDto) {
        List<CampoEntidad> listEntidad = new ArrayList<CampoEntidad>();
        for (CampoEntidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CampoEntidad> toListLevel1Entity(List<CampoEntidadDTO> listDto) {
        List<CampoEntidad> listEntidad = new ArrayList<CampoEntidad>();
        for (CampoEntidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
