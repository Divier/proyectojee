package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaHistorico;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 11:55:20 COT 2014
 */
public class PersonaHistoricoHelper {
    // --- to DTO
    public static PersonaHistoricoDTO toLevel0DTO(PersonaHistorico entidad) {
        PersonaHistoricoDTO dto = new PersonaHistoricoDTO();
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
        dto.setHuellaDigital(entidad.getHuellaDigital());
        dto.setRutaFoto(entidad.getRutaFoto());
        dto.setDigitoVerificacion(entidad.getDigitoVerificacion());
        dto.setNombreComercial(entidad.getNombreComercial());
        dto.setSigla(entidad.getSigla());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setIdDocumento(entidad.getIdDocumento());

        return dto;
    }

    public static PersonaHistoricoDTO toLevel1DTO(PersonaHistorico entidad) {
        PersonaHistoricoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
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
        if (entidad.getTipoSociedad() != null)
            dto.setTipoSociedad(TipoSociedadHelper.toLevel0DTO(entidad.getTipoSociedad()));
        if (entidad.getClaseActividadEconomica() != null)
            dto.setClaseActividadEconomica(
                    ClaseActividadEconomicaHelper.toLevel0DTO(entidad.getClaseActividadEconomica()));
        if (entidad.getUsuarioRegistro() != null)
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));

        return dto;
    }

    public static List<PersonaHistoricoDTO> toListLevel0DTO(List<PersonaHistorico> listEntidad) {
        List<PersonaHistoricoDTO> listDto = new ArrayList<PersonaHistoricoDTO>();
        for (PersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PersonaHistoricoDTO> toListLevel1DTO(List<PersonaHistorico> listEntidad) {
        List<PersonaHistoricoDTO> listDto = new ArrayList<PersonaHistoricoDTO>();
        for (PersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PersonaHistorico toLevel0Entity(PersonaHistoricoDTO dto, PersonaHistorico entidad) {
        if (null == entidad) {
            entidad = new PersonaHistorico();
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
        entidad.setDigitoVerificacion(dto.getDigitoVerificacion());
        entidad.setNombreComercial(dto.getNombreComercial());
        entidad.setSigla(dto.getSigla());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setIdDocumento(dto.getIdDocumento());

        return entidad;
    }

    public static PersonaHistorico toLevel1Entity(PersonaHistoricoDTO dto, PersonaHistorico entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
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
        if (dto.getTipoSociedad() != null) {
            entidad.setTipoSociedad(new TipoSociedad());
            entidad.getTipoSociedad().setId(dto.getTipoSociedad().getId());
        }
        if (dto.getClaseActividadEconomica() != null) {
            entidad.setClaseActividadEconomica(new ClaseActividadEconomica());
            entidad.getClaseActividadEconomica().setId(dto.getClaseActividadEconomica().getId());
        }
        if (dto.getUsuarioRegistro() != null) {
            entidad.setUsuarioRegistro(new UsuarioPersona());
            entidad.getUsuarioRegistro().setIdUsuario(dto.getUsuarioRegistro().getUsuario().getId());
        }

        return entidad;
    }

    public static List<PersonaHistorico> toListLevel0Entity(List<PersonaHistoricoDTO> listDto) {
        List<PersonaHistorico> listEntidad = new ArrayList<PersonaHistorico>();
        for (PersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PersonaHistorico> toListLevel1Entity(List<PersonaHistoricoDTO> listDto) {
        List<PersonaHistorico> listEntidad = new ArrayList<PersonaHistorico>();
        for (PersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
