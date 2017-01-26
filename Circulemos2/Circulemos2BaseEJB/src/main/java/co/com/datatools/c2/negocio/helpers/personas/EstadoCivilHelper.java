package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class EstadoCivilHelper {
    // --- to DTO
    public static EstadoCivilDTO toLevel0DTO(EstadoCivil entidad) {
        EstadoCivilDTO dto = new EstadoCivilDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setAbreviatura(entidad.getAbreviatura());

        return dto;
    }

    public static EstadoCivilDTO toLevel1DTO(EstadoCivil entidad) {
        EstadoCivilDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<EstadoCivilDTO> toListLevel0DTO(List<EstadoCivil> listEntidad) {
        List<EstadoCivilDTO> listDto = new ArrayList<EstadoCivilDTO>();
        for (EstadoCivil entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoCivilDTO> toListLevel1DTO(List<EstadoCivil> listEntidad) {
        List<EstadoCivilDTO> listDto = new ArrayList<EstadoCivilDTO>();
        for (EstadoCivil entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoCivil toLevel0Entity(EstadoCivilDTO dto, EstadoCivil entidad) {
        if (null == entidad) {
            entidad = new EstadoCivil();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setAbreviatura(dto.getAbreviatura());

        return entidad;
    }

    public static EstadoCivil toLevel1Entity(EstadoCivilDTO dto, EstadoCivil entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<EstadoCivil> toListLevel0Entity(List<EstadoCivilDTO> listDto) {
        List<EstadoCivil> listEntidad = new ArrayList<EstadoCivil>();
        for (EstadoCivilDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoCivil> toListLevel1Entity(List<EstadoCivilDTO> listDto) {
        List<EstadoCivil> listEntidad = new ArrayList<EstadoCivil>();
        for (EstadoCivilDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
