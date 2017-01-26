package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.DivisionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.GrupoActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.PersonaProvisionalDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.personas.SeccionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.TipoSociedadDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.negocio.helpers.comun.DepartamentoHelper;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHelper;

public class PersonaJuridicaHelperExtend extends PersonaJuridicaHelper {

    private static void copiePersonaAPersonaJuridica(PersonaDTO personaDTO, PersonaJuridicaDTO personaJuridicaDTO) {
        personaJuridicaDTO.setId(personaDTO.getId());
        personaJuridicaDTO.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
        personaJuridicaDTO.setFechaUltimaActualizacion(personaDTO.getFechaUltimaActualizacion());
        personaJuridicaDTO.setFechaFallecimiento(personaDTO.getFechaFallecimiento());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // personaJuridicaDTO.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
        // personaJuridicaDTO.setNumeroCelular(personaDTO.getNumeroCelular());
        personaJuridicaDTO.setGenero(personaDTO.getGenero());
        personaJuridicaDTO.setNotificaDirEmpresa(personaDTO.getNotificaDirEmpresa());
        personaJuridicaDTO.setHuellaDigital(personaDTO.getHuellaDigital());
        personaJuridicaDTO.setRutaFoto(personaDTO.getRutaFoto());
        personaJuridicaDTO.setFechaFoto(personaDTO.getFechaFoto());

        if (personaDTO.getTipoIdentificacion() != null) {
            personaJuridicaDTO.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
        }

        if (personaDTO.getFuenteInformacion() != null) {
            personaJuridicaDTO.setFuenteInformacion(personaDTO.getFuenteInformacion());
        }

        if (personaDTO.getOrganismoTransito() != null) {
            personaJuridicaDTO.setOrganismoTransito(personaDTO.getOrganismoTransito());
        }

        if (!personaDTO.getDireccionPersonaList().isEmpty()) {
            personaJuridicaDTO.setDireccionPersonaList(personaDTO.getDireccionPersonaList());
        }

    }

    public static PersonaJuridicaDTO toPersonaJuridicaDTOSegundoNivel(PersonaJuridica personaJuridica) {
        PersonaJuridicaDTO personaJuridicaDTO = PersonaJuridicaHelper.toLevel0DTO(personaJuridica);
        PersonaJuridicaHelperExtend.copiePersonaAPersonaJuridica(
                PersonaHelperExtend.toLevel1DTO(personaJuridica.getPersona()), personaJuridicaDTO);

        if (personaJuridica.getTipoSociedad() != null) {
            TipoSociedadDTO tipoSociedadDTO = new TipoSociedadDTO();
            tipoSociedadDTO.setId(personaJuridica.getTipoSociedad().getId());
            tipoSociedadDTO.setNombre(personaJuridica.getTipoSociedad().getNombre());
            personaJuridicaDTO.setTipoSociedad(tipoSociedadDTO);
        }

        if (personaJuridica.getMunicipio() != null) {
            MunicipioDTO municipioDTO = MunicipioHelper.toLevel0DTO(personaJuridica.getMunicipio());
            DepartamentoDTO departamentoDTO = DepartamentoHelper
                    .toLevel1DTO(personaJuridica.getMunicipio().getDepartamento());
            municipioDTO.setDepartamento(departamentoDTO);
            personaJuridicaDTO.setMunicipio(municipioDTO);

        }

        if (personaJuridica.getClaseActividadEconomica() != null) {
            ClaseActividadEconomicaDTO claseActividadEconomicaDTO = new ClaseActividadEconomicaDTO();
            claseActividadEconomicaDTO.setId(personaJuridica.getClaseActividadEconomica().getId());
            claseActividadEconomicaDTO.setNombre(personaJuridica.getClaseActividadEconomica().getNombre());
            personaJuridicaDTO.setClaseActividadEconomica(claseActividadEconomicaDTO);

            GrupoActividadEconomicaDTO grupoActividadEconomicaDTO = new GrupoActividadEconomicaDTO();
            grupoActividadEconomicaDTO
                    .setId(personaJuridica.getClaseActividadEconomica().getGrupoActividadEconomica().getId());
            grupoActividadEconomicaDTO
                    .setNombre(personaJuridica.getClaseActividadEconomica().getGrupoActividadEconomica().getNombre());
            claseActividadEconomicaDTO.setGrupoActividadEconomica(grupoActividadEconomicaDTO);

            DivisionActividadEconomicaDTO divisionActividadEconomicaDTO = new DivisionActividadEconomicaDTO();
            divisionActividadEconomicaDTO.setId(personaJuridica.getClaseActividadEconomica()
                    .getGrupoActividadEconomica().getDivisionActividadEconomica().getId());
            divisionActividadEconomicaDTO.setNombre(personaJuridica.getClaseActividadEconomica()
                    .getGrupoActividadEconomica().getDivisionActividadEconomica().getNombre());
            grupoActividadEconomicaDTO.setDivisionActividadEconomica(divisionActividadEconomicaDTO);

            SeccionActividadEconomicaDTO seccionActividadEconomicaDTO = new SeccionActividadEconomicaDTO();
            seccionActividadEconomicaDTO.setId(personaJuridica.getClaseActividadEconomica().getGrupoActividadEconomica()
                    .getDivisionActividadEconomica().getSeccionActividadEconomica().getId());
            seccionActividadEconomicaDTO
                    .setNombre(personaJuridica.getClaseActividadEconomica().getGrupoActividadEconomica()
                            .getDivisionActividadEconomica().getSeccionActividadEconomica().getNombre());
            divisionActividadEconomicaDTO.setSeccionActividadEconomica(seccionActividadEconomicaDTO);
        }

        if (personaJuridica.getRepresentanteLegalList() != null) {
            List<RepresentanteLegalDTO> lRepresentanteLegal = RepresentanteLegalHelperExtend
                    .toListLevel1DTO(personaJuridica.getRepresentanteLegalList());
            personaJuridicaDTO.setRepresentanteLegalList(lRepresentanteLegal);
            for (RepresentanteLegalDTO representanteLegalDTO : lRepresentanteLegal) {
                representanteLegalDTO.setPersonaJuridica(personaJuridicaDTO);
            }

        }

        return personaJuridicaDTO;
    }

    public static List<PersonaJuridicaDTO> toListLevelSegundoNivelDTO(List<PersonaJuridica> listEntidad) {
        List<PersonaJuridicaDTO> listDto = new ArrayList<PersonaJuridicaDTO>();
        for (PersonaJuridica entidad : listEntidad) {
            listDto.add(PersonaJuridicaHelperExtend.toPersonaJuridicaDTOSegundoNivel(entidad));
        }

        return listDto;
    }

    // TO LEVEL1 MANEJADO CON LA HERENCIA
    public static PersonaJuridica toLevel1Entity(PersonaJuridicaDTO personaJuridicaDTO,
            PersonaJuridica personaJuridica) {
        PersonaJuridica personaJuridicaNivel1 = PersonaJuridicaHelper.toLevel0Entity(personaJuridicaDTO,
                personaJuridica);
        Persona persona = null;
        if (personaJuridica != null) {
            persona = personaJuridica.getPersona();
        }

        if (persona == null) {
            persona = new Persona();
            personaJuridicaNivel1.setPersona(persona);
        }

        persona.setId(personaJuridicaDTO.getId());
        persona.setNotificaDirEmpresa(personaJuridicaDTO.getNotificaDirEmpresa());
        OrganismoTransito organismoTransito = new OrganismoTransito();
        organismoTransito.setCodigoOrganismo(personaJuridicaDTO.getOrganismoTransito().getCodigoOrganismo());
        persona.setOrganismoTransito(organismoTransito);

        TipoIdentificacionPersona tipoIdentificacionPersona = new TipoIdentificacionPersona();
        tipoIdentificacionPersona.setId(personaJuridicaDTO.getTipoIdentificacion().getId());
        persona.setTipoIdentificacion(tipoIdentificacionPersona);
        persona.setNumeroIdentificacion(personaJuridicaDTO.getNumeroIdentificacion());
        persona.setFechaUltimaActualizacion(personaJuridicaDTO.getFechaUltimaActualizacion());

        TipoFuenteInformacion tipoFuenteInformacion = new TipoFuenteInformacion();
        tipoFuenteInformacion.setId(personaJuridicaDTO.getFuenteInformacion().getId());
        persona.setFuenteInformacion(tipoFuenteInformacion);
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // persona.setNumeroTelefonico(personaJuridicaDTO.getNumeroTelefonico());
        // persona.setNumeroCelular(personaJuridicaDTO.getNumeroCelular());
        // persona.setCorreoElectronico(personaJuridicaDTO.getCorreoElectronico());

        if ((personaJuridicaDTO.getMunicipio() != null) && (personaJuridicaDTO.getMunicipio().getId() != null)) {
            Municipio municipio = new Municipio();
            municipio.setId(personaJuridicaDTO.getMunicipio().getId());
            personaJuridicaNivel1.setMunicipio(municipio);
        }

        if ((personaJuridicaDTO.getTipoSociedad() != null) && (personaJuridicaDTO.getTipoSociedad().getId() != null)) {
            TipoSociedad tipoSociedad = new TipoSociedad();
            tipoSociedad.setId((personaJuridicaDTO.getTipoSociedad().getId()));
            personaJuridicaNivel1.setTipoSociedad(tipoSociedad);
        }

        if ((personaJuridicaDTO.getClaseActividadEconomica() != null)
                && (personaJuridicaDTO.getClaseActividadEconomica().getId() != null)) {
            ClaseActividadEconomica claseActividadEconomica = new ClaseActividadEconomica();
            claseActividadEconomica.setId(personaJuridicaDTO.getClaseActividadEconomica().getId());
            personaJuridicaNivel1.setClaseActividadEconomica(claseActividadEconomica);
        }

        if (personaJuridicaNivel1.getPersona().getDireccionPersonaList() == null) {
            personaJuridicaNivel1.getPersona().setDireccionPersonaList(new ArrayList<DireccionPersona>());
        }

        for (DireccionPersonaDTO dirPersonaDTO : personaJuridicaDTO.getDireccionPersonaList()) {
            DireccionPersona dirPersona = DireccionPersonaHelper.toLevel1Entity(dirPersonaDTO, null);
            dirPersona.setPersona(personaJuridicaNivel1.getPersona());
            personaJuridicaNivel1.getPersona().getDireccionPersonaList().add(dirPersona);
        }

        return personaJuridicaNivel1;
    }

    // LEVEL 1 DTO
    public static PersonaJuridicaDTO toLevel1DTO(PersonaProvisionalDTO personaSolicitudDTO) {
        PersonaJuridicaDTO personaJuridicaDTO = new PersonaJuridicaDTO();
        personaJuridicaDTO.setId(personaSolicitudDTO.getId());
        personaJuridicaDTO.setNumeroIdentificacion(personaSolicitudDTO.getNumeroIdentificacion());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // personaJuridicaDTO.setNumeroTelefonico(personaSolicitudDTO.getNumeroTelefonico());

        return personaJuridicaDTO;

    }

    public static PersonaJuridica toLevel0EntityExtend(PersonaJuridicaDTO dto, PersonaJuridica entidad) {
        if (null == entidad) {
            entidad = new PersonaJuridica();
        }
        entidad.setDigitoVerificacion(dto.getDigitoVerificacion());
        entidad.setNombreComercial(dto.getNombreComercial());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigoEmpresa(dto.getCodigoEmpresa());
        return entidad;
    }
}
