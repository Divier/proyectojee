package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author felipe.martinez
 * @version 3.0 - Wed May 28 11:42:54 COT 2014 - editado
 */
public class PersonaHelper {
    // --- to DTO
    public static PersonaDTO toLevel0DTO(Persona entidad) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setFechaUltimaActualizacion(entidad.getFechaUltimaActualizacion());
        dto.setFechaExpedicionDocumento(entidad.getFechaExpedicionDocumento());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        dto.setFechaFallecimiento(entidad.getFechaFallecimiento());
        dto.setGenero(entidad.getGenero());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setNombreEmpresaLabora(entidad.getNombreEmpresaLabora());
        dto.setNotificaDirEmpresa(entidad.getNotificaDirEmpresa());
        dto.setCargoEmpresaLabora(entidad.getCargoEmpresaLabora());
        dto.setRutaFoto(entidad.getRutaFoto());
        dto.setFechaFoto(entidad.getFechaFoto());

        return dto;
    }

    public static PersonaDTO toLevel1DTO(Persona entidad) {
        PersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        if (entidad.getFuenteInformacion() != null)
            dto.setFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getFuenteInformacion()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getMunicipioExpedicionDocumento() != null)
            dto.setMunicipioExpedicionDocumento(MunicipioHelper.toLevel0DTO(entidad.getMunicipioExpedicionDocumento()));
        if (entidad.getEstadoCivil() != null)
            dto.setEstadoCivil(EstadoCivilHelper.toLevel0DTO(entidad.getEstadoCivil()));
        if (entidad.getTipoVivienda() != null)
            dto.setTipoVivienda(TipoViviendaHelper.toLevel0DTO(entidad.getTipoVivienda()));
        if (entidad.getNivelEducativo() != null)
            dto.setNivelEducativo(NivelEducativoHelper.toLevel0DTO(entidad.getNivelEducativo()));
        return dto;
    }

    public static List<PersonaDTO> toListLevel0DTO(List<Persona> listEntidad) {
        List<PersonaDTO> listDto = new ArrayList<PersonaDTO>();
        for (Persona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PersonaDTO> toListLevel1DTO(List<Persona> listEntidad) {
        List<PersonaDTO> listDto = new ArrayList<PersonaDTO>();
        for (Persona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Persona toLevel0Entity(PersonaDTO dto, Persona entidad) {
        if (null == entidad) {
            entidad = new Persona();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setFechaUltimaActualizacion(dto.getFechaUltimaActualizacion());
        entidad.setFechaExpedicionDocumento(dto.getFechaExpedicionDocumento());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        entidad.setFechaFallecimiento(dto.getFechaFallecimiento());
        entidad.setGenero(dto.getGenero());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setNombreEmpresaLabora(dto.getNombreEmpresaLabora());
        entidad.setNotificaDirEmpresa(dto.getNotificaDirEmpresa());
        entidad.setCargoEmpresaLabora(dto.getCargoEmpresaLabora());
        entidad.setHuellaDigital(dto.getHuellaDigital());
        entidad.setRutaFoto(dto.getRutaFoto());
        entidad.setFechaFoto(dto.getFechaFoto());

        return entidad;
    }

    public static Persona toLevel1Entity(PersonaDTO dto, Persona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoIdentificacion() != null) {
            entidad.setTipoIdentificacion(new TipoIdentificacionPersona());
            entidad.getTipoIdentificacion().setId(dto.getTipoIdentificacion().getId());
        }
        if (dto.getFuenteInformacion() != null) {
            entidad.setFuenteInformacion(new TipoFuenteInformacion());
            entidad.getFuenteInformacion().setCodigo(dto.getFuenteInformacion().getCodigo());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getMunicipioExpedicionDocumento() != null) {
            entidad.setMunicipioExpedicionDocumento(new Municipio());
            entidad.getMunicipioExpedicionDocumento().setId(dto.getMunicipioExpedicionDocumento().getId());
        }
        if (dto.getEstadoCivil() != null) {
            entidad.setEstadoCivil(new EstadoCivil());
            entidad.getEstadoCivil().setId(dto.getEstadoCivil().getId());
        }
        if (dto.getTipoVivienda() != null) {
            entidad.setTipoVivienda(new TipoVivienda());
            entidad.getTipoVivienda().setCodigo(dto.getTipoVivienda().getCodigo());
        }
        if (dto.getNivelEducativo() != null) {
            entidad.setNivelEducativo(new NivelEducativo());
            entidad.getNivelEducativo().setCodigo(dto.getNivelEducativo().getCodigo());
        }
        return entidad;
    }

    public static List<Persona> toListLevel0Entity(List<PersonaDTO> listDto) {
        List<Persona> listEntidad = new ArrayList<Persona>();
        for (PersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Persona> toListLevel1Entity(List<PersonaDTO> listDto) {
        List<Persona> listEntidad = new ArrayList<Persona>();
        for (PersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
