package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.TipoSociedadDTO;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoSociedadHelper {
    // --- to DTO
    public static TipoSociedadDTO toLevel0DTO(TipoSociedad entidad) {
        TipoSociedadDTO dto = new TipoSociedadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setAbreviatura(entidad.getAbreviatura());

        return dto;
    }

    public static TipoSociedadDTO toLevel1DTO(TipoSociedad entidad) {
        TipoSociedadDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<TipoSociedadDTO> toListLevel0DTO(List<TipoSociedad> listEntidad) {
        List<TipoSociedadDTO> listDto = new ArrayList<TipoSociedadDTO>();
        for (TipoSociedad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoSociedadDTO> toListLevel1DTO(List<TipoSociedad> listEntidad) {
        List<TipoSociedadDTO> listDto = new ArrayList<TipoSociedadDTO>();
        for (TipoSociedad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoSociedad toLevel0Entity(TipoSociedadDTO dto, TipoSociedad entidad) {
        if (null == entidad) {
            entidad = new TipoSociedad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setAbreviatura(dto.getAbreviatura());

        return entidad;
    }

    public static TipoSociedad toLevel1Entity(TipoSociedadDTO dto, TipoSociedad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<TipoSociedad> toListLevel0Entity(List<TipoSociedadDTO> listDto) {
        List<TipoSociedad> listEntidad = new ArrayList<TipoSociedad>();
        for (TipoSociedadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoSociedad> toListLevel1Entity(List<TipoSociedadDTO> listDto) {
        List<TipoSociedad> listEntidad = new ArrayList<TipoSociedad>();
        for (TipoSociedadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
