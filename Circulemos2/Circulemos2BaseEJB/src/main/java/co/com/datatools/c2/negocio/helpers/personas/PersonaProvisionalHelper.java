package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaProvisionalDTO;
import co.com.datatools.c2.entidades.comun.DireccionProvisional;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.PersonaProvisional;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.negocio.helpers.comun.DireccionProvisionalHelper;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class PersonaProvisionalHelper {
    // --- to DTO
    public static PersonaProvisionalDTO toLevel0DTO(PersonaProvisional entidad) {
        PersonaProvisionalDTO dto = new PersonaProvisionalDTO();
        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaExpedicionDocumento(entidad.getFechaExpedicionDocumento());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        dto.setNumeroTelefonico(entidad.getNumeroTelefonico());
        dto.setGenero(entidad.getGenero());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setNumeroCelular(entidad.getNumeroCelular());
        dto.setNombreEmpresaLabora(entidad.getNombreEmpresaLabora());
        dto.setNotificaDirEmpresa(entidad.getNotificaDirEmpresa());
        dto.setCargoEmpresaLabora(entidad.getCargoEmpresaLabora());
        dto.setTelefonoEmpresaLabora(entidad.getTelefonoEmpresaLabora());
        dto.setFaxEmpresaLabora(entidad.getFaxEmpresaLabora());

        return dto;
    }

    public static PersonaProvisionalDTO toLevel1DTO(PersonaProvisional entidad) {
        PersonaProvisionalDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getMunicipioExpedicionDocumento() != null)
            dto.setMunicipioExpedicionDocumento(MunicipioHelper.toLevel0DTO(entidad.getMunicipioExpedicionDocumento()));
        if (entidad.getEstadoCivil() != null)
            dto.setEstadoCivil(EstadoCivilHelper.toLevel0DTO(entidad.getEstadoCivil()));
        if (entidad.getDireccionSolicitud() != null)
            dto.setDireccionSolicitud(DireccionProvisionalHelper.toLevel0DTO(entidad.getDireccionSolicitud()));
        if (entidad.getNivelEducativo() != null)
            dto.setNivelEducativo(NivelEducativoHelper.toLevel0DTO(entidad.getNivelEducativo()));
        if (entidad.getTipoVivienda() != null)
            dto.setTipoVivienda(TipoViviendaHelper.toLevel0DTO(entidad.getTipoVivienda()));

        return dto;
    }

    public static List<PersonaProvisionalDTO> toListLevel0DTO(List<PersonaProvisional> listEntidad) {
        List<PersonaProvisionalDTO> listDto = new ArrayList<PersonaProvisionalDTO>();
        for (PersonaProvisional entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PersonaProvisionalDTO> toListLevel1DTO(List<PersonaProvisional> listEntidad) {
        List<PersonaProvisionalDTO> listDto = new ArrayList<PersonaProvisionalDTO>();
        for (PersonaProvisional entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PersonaProvisional toLevel0Entity(PersonaProvisionalDTO dto, PersonaProvisional entidad) {
        if (null == entidad) {
            entidad = new PersonaProvisional();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaExpedicionDocumento(dto.getFechaExpedicionDocumento());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        entidad.setNumeroTelefonico(dto.getNumeroTelefonico());
        entidad.setGenero(dto.getGenero());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setNumeroCelular(dto.getNumeroCelular());
        entidad.setNombreEmpresaLabora(dto.getNombreEmpresaLabora());
        entidad.setNotificaDirEmpresa(dto.getNotificaDirEmpresa());
        entidad.setCargoEmpresaLabora(dto.getCargoEmpresaLabora());
        entidad.setTelefonoEmpresaLabora(dto.getTelefonoEmpresaLabora());
        entidad.setFaxEmpresaLabora(dto.getFaxEmpresaLabora());

        return entidad;
    }

    public static PersonaProvisional toLevel1Entity(PersonaProvisionalDTO dto, PersonaProvisional entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoIdentificacion() != null) {
            entidad.setTipoIdentificacion(new TipoIdentificacionPersona());
            entidad.getTipoIdentificacion().setId(dto.getTipoIdentificacion().getId());
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
        if (dto.getDireccionSolicitud() != null) {
            entidad.setDireccionSolicitud(new DireccionProvisional());
            entidad.getDireccionSolicitud().setId(dto.getDireccionSolicitud().getId());
        }
        if (dto.getNivelEducativo() != null) {
            entidad.setNivelEducativo(new NivelEducativo());
            entidad.getNivelEducativo().setCodigo(dto.getNivelEducativo().getCodigo());
        }
        if (dto.getTipoVivienda() != null) {
            entidad.setTipoVivienda(new TipoVivienda());
            entidad.getTipoVivienda().setCodigo(dto.getTipoVivienda().getCodigo());
        }

        return entidad;
    }

    public static List<PersonaProvisional> toListLevel0Entity(List<PersonaProvisionalDTO> listDto) {
        List<PersonaProvisional> listEntidad = new ArrayList<PersonaProvisional>();
        for (PersonaProvisionalDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PersonaProvisional> toListLevel1Entity(List<PersonaProvisionalDTO> listDto) {
        List<PersonaProvisional> listEntidad = new ArrayList<PersonaProvisional>();
        for (PersonaProvisionalDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
