package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;

/**
 * 
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class PersonaJuridicaHelper {
    // --- to DTO
    public static PersonaJuridicaDTO toLevel0DTO(PersonaJuridica entidad) {
        PersonaJuridicaDTO dto = new PersonaJuridicaDTO();
        dto.setDigitoVerificacion(entidad.getDigitoVerificacion());
        dto.setNombreComercial(entidad.getNombreComercial());
        dto.setSigla(entidad.getSigla());
        dto.setCodigoEmpresa(entidad.getCodigoEmpresa());

        return dto;
    }

    public static PersonaJuridicaDTO toLevel1DTO(PersonaJuridica entidad) {
        PersonaJuridicaDTO dto = toLevel0DTO(entidad);

        if (entidad.getTipoSociedad() != null)
            dto.setTipoSociedad(TipoSociedadHelper.toLevel0DTO(entidad.getTipoSociedad()));
        if (entidad.getClaseActividadEconomica() != null)
            dto.setClaseActividadEconomica(
                    ClaseActividadEconomicaHelper.toLevel0DTO(entidad.getClaseActividadEconomica()));
        if (entidad.getMunicipio() != null)
            dto.setMunicipio(MunicipioHelper.toLevel0DTO(entidad.getMunicipio()));

        return dto;
    }

    public static List<PersonaJuridicaDTO> toListLevel0DTO(List<PersonaJuridica> listEntidad) {
        List<PersonaJuridicaDTO> listDto = new ArrayList<PersonaJuridicaDTO>();
        for (PersonaJuridica entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PersonaJuridicaDTO> toListLevel1DTO(List<PersonaJuridica> listEntidad) {
        List<PersonaJuridicaDTO> listDto = new ArrayList<PersonaJuridicaDTO>();
        for (PersonaJuridica entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PersonaJuridica toLevel0Entity(PersonaJuridicaDTO dto, PersonaJuridica entidad) {
        if (null == entidad) {
            entidad = new PersonaJuridica();
        }
        entidad.setDigitoVerificacion(dto.getDigitoVerificacion());
        entidad.setNombreComercial(dto.getNombreComercial());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigoEmpresa(dto.getCodigoEmpresa());
        return entidad;
    }

    public static PersonaJuridica toLevel1Entity(PersonaJuridicaDTO dto, PersonaJuridica entidad) {
        entidad = toLevel0Entity(dto, entidad);
        // if (dto.getPersona() != null) {
        // entidad.setPersona(new Persona());
        // entidad.getPersona().setId(dto.getPersona().getId());
        // }
        if (dto.getTipoSociedad() != null) {
            entidad.setTipoSociedad(new TipoSociedad());
            entidad.getTipoSociedad().setId(dto.getTipoSociedad().getId());
        }
        if (dto.getClaseActividadEconomica() != null) {
            entidad.setClaseActividadEconomica(new ClaseActividadEconomica());
            entidad.getClaseActividadEconomica().setId(dto.getClaseActividadEconomica().getId());
        }
        if (dto.getMunicipio() != null) {
            entidad.setMunicipio(new Municipio());
            entidad.getMunicipio().setId(dto.getMunicipio().getId());
        }

        return entidad;
    }

    public static List<PersonaJuridica> toListLevel0Entity(List<PersonaJuridicaDTO> listDto) {
        List<PersonaJuridica> listEntidad = new ArrayList<PersonaJuridica>();
        for (PersonaJuridicaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PersonaJuridica> toListLevel1Entity(List<PersonaJuridicaDTO> listDto) {
        List<PersonaJuridica> listEntidad = new ArrayList<PersonaJuridica>();
        for (PersonaJuridicaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
