package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.entidades.comun.Localidad;
import co.com.datatools.c2.entidades.comun.Municipio;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class LocalidadHelper {
    // --- to DTO
    public static LocalidadDTO toLevel0DTO(Localidad entidad) {
        LocalidadDTO dto = new LocalidadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static LocalidadDTO toLevel1DTO(Localidad entidad) {
        LocalidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getMunicipio() != null)
            dto.setMunicipio(MunicipioHelper.toLevel0DTO(entidad.getMunicipio()));

        return dto;
    }

    public static List<LocalidadDTO> toListLevel0DTO(List<Localidad> listEntidad) {
        List<LocalidadDTO> listDto = new ArrayList<LocalidadDTO>();
        for (Localidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LocalidadDTO> toListLevel1DTO(List<Localidad> listEntidad) {
        List<LocalidadDTO> listDto = new ArrayList<LocalidadDTO>();
        for (Localidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Localidad toLevel0Entity(LocalidadDTO dto, Localidad entidad) {
        if (null == entidad) {
            entidad = new Localidad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static Localidad toLevel1Entity(LocalidadDTO dto, Localidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getMunicipio() != null) {
            entidad.setMunicipio(new Municipio());
            entidad.getMunicipio().setId(dto.getMunicipio().getId());
        }

        return entidad;
    }

    public static List<Localidad> toListLevel0Entity(List<LocalidadDTO> listDto) {
        List<Localidad> listEntidad = new ArrayList<Localidad>();
        for (LocalidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Localidad> toListLevel1Entity(List<LocalidadDTO> listDto) {
        List<Localidad> listEntidad = new ArrayList<Localidad>();
        for (LocalidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
