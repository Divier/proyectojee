package co.com.datatools.c2.negocio.helpers.personas;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaHistorico;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.entidades.personas.TipoVivienda;

/**
 * @author robert.bautista
 * @since 2013-08-11
 */
public class PersonaHistoricoHelperExtend extends PersonaHistoricoHelper {

    public static PersonaHistorico toLevel1Entity(PersonaHistoricoDTO personaHistoricoDTO,
            PersonaHistorico personaHistorico) {
        PersonaHistorico personaHistoricoNivel1 = PersonaHistoricoHelperExtend.toLevel0Entity(personaHistoricoDTO,
                personaHistorico);

        Persona persona = new Persona();
        persona.setId(personaHistoricoDTO.getPersona().getId());
        personaHistoricoNivel1.setPersona(persona);

        TipoFuenteInformacion tipoFuenteInformacion = new TipoFuenteInformacion();
        tipoFuenteInformacion.setId(personaHistoricoDTO.getFuenteInformacion().getId());
        personaHistoricoNivel1.setFuenteInformacion(tipoFuenteInformacion);

        OrganismoTransito organismoTransito = new OrganismoTransito();
        organismoTransito.setCodigoOrganismo(personaHistoricoDTO.getOrganismoTransito().getCodigoOrganismo());
        personaHistoricoNivel1.setOrganismoTransito(organismoTransito);

        TipoIdentificacionPersona tipoIdentificacionPersona = new TipoIdentificacionPersona();
        tipoIdentificacionPersona.setId(personaHistoricoDTO.getTipoIdentificacion().getId());
        personaHistoricoNivel1.setTipoIdentificacion(tipoIdentificacionPersona);

        // TODO FIX Helper
        // if (personaHistoricoDTO.getPersonaConyugeDTO() != null) {
        // if (personaHistoricoDTO.getPersonaConyugeDTO().getIdPersona() != null) {
        // Persona conyugue = new Persona();
        // conyugue.setIdPersona(personaHistoricoDTO.getPersonaConyugeDTO().getIdPersona());
        // personaHistoricoNivel1.setPersonaConyuge(conyugue);
        // }
        //
        // }

        if (personaHistoricoDTO.getMunicipioExpedicionDocumento() != null) {
            if (personaHistoricoDTO.getMunicipioExpedicionDocumento().getId() != null) {
                Municipio municipio = new Municipio();
                municipio.setId(personaHistoricoDTO.getMunicipioExpedicionDocumento().getId());
                personaHistoricoNivel1.setMunicipioExpedicionDocumento(municipio);
            }

        }

        if (personaHistoricoDTO.getEstadoCivil() != null) {
            if (personaHistoricoDTO.getEstadoCivil().getId() != null) {
                EstadoCivil estadoCivil = new EstadoCivil();
                estadoCivil.setId(personaHistoricoDTO.getEstadoCivil().getId());
                personaHistoricoNivel1.setEstadoCivil(estadoCivil);
            }

        }

        if (personaHistoricoDTO.getNivelEducativo() != null) {
            if (personaHistoricoDTO.getNivelEducativo().getCodigo() != null) {
                NivelEducativo nivelEducativo = new NivelEducativo();
                nivelEducativo.setCodigo(personaHistoricoDTO.getNivelEducativo().getCodigo());
                personaHistoricoNivel1.setNivelEducativo(nivelEducativo);
            }

        }

        if (personaHistoricoDTO.getTipoVivienda() != null) {
            if (personaHistoricoDTO.getTipoVivienda().getCodigo() != null) {
                TipoVivienda tipoVivienda = new TipoVivienda();
                tipoVivienda.setCodigo(personaHistoricoDTO.getTipoVivienda().getCodigo());
                personaHistoricoNivel1.setTipoVivienda(tipoVivienda);
            }

        }

        // Persona juridica
        if (personaHistoricoDTO.getTipoSociedad() != null) {
            if (personaHistoricoDTO.getTipoSociedad().getId() != null) {
                TipoSociedad tipoSociedad = new TipoSociedad();
                tipoSociedad.setId(personaHistoricoDTO.getTipoSociedad().getId());
                personaHistoricoNivel1.setTipoSociedad(tipoSociedad);
            }

        }

        if (personaHistoricoDTO.getClaseActividadEconomica() != null) {
            if (personaHistoricoDTO.getClaseActividadEconomica().getId() != null) {
                ClaseActividadEconomica claseActividadEconomica = new ClaseActividadEconomica();
                claseActividadEconomica.setId(personaHistoricoDTO.getClaseActividadEconomica().getId());
                personaHistoricoNivel1.setClaseActividadEconomica(claseActividadEconomica);
            }

        }

        return personaHistoricoNivel1;
    }

    /**
     * Retorna la PersonaHistoricoDTO de la personaDTO indicada
     * 
     * @param personaDTO
     *            contiene los datos de la persona historico DTO a retornar
     * 
     * @return PersonaHistoricoDTO con la información de la persona indicada
     */
    public static PersonaHistoricoDTO toPersonaHistoricoDTO(PersonaDTO personaDTO) {
        PersonaHistoricoDTO personaHistoricoDTO = new PersonaHistoricoDTO();

        personaHistoricoDTO.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
        personaHistoricoDTO.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
        personaHistoricoDTO.setFechaUltimaActualizacion(personaDTO.getFechaUltimaActualizacion());
        personaHistoricoDTO.setOrganismoTransito(personaDTO.getOrganismoTransito());
        personaHistoricoDTO.setFuenteInformacion(personaDTO.getFuenteInformacion());

        personaHistoricoDTO.setIdDocumento(personaDTO.getIdDocumento());

        // REFERENCIA A PERSONA
        personaHistoricoDTO.setPersona(personaDTO);

        if (personaDTO instanceof PersonaJuridicaDTO) {
            // Persona juridica
            PersonaJuridicaDTO personaJuridicaDTO = (PersonaJuridicaDTO) personaDTO;

            personaHistoricoDTO.setDigitoVerificacion(personaJuridicaDTO.getDigitoVerificacion());
            personaHistoricoDTO.setNombreComercial(personaJuridicaDTO.getNombreComercial());
            personaHistoricoDTO.setSigla(personaJuridicaDTO.getSigla());
            personaHistoricoDTO.setTipoSociedad(personaJuridicaDTO.getTipoSociedad());
            personaHistoricoDTO.setClaseActividadEconomica(personaJuridicaDTO.getClaseActividadEconomica());
        } else {
            // Persona natural
            personaHistoricoDTO.setApellido1(personaDTO.getApellido1());
            personaHistoricoDTO.setApellido2(personaDTO.getApellido2());
            personaHistoricoDTO.setNombre1(personaDTO.getNombre1());
            personaHistoricoDTO.setNombre2(personaDTO.getNombre2());
            personaHistoricoDTO.setGenero(personaDTO.getGenero());

            personaHistoricoDTO.setNombreEmpresaLabora(personaDTO.getNombreEmpresaLabora());
            personaHistoricoDTO.setNotificaDirEmpresa(personaDTO.getNotificaDirEmpresa());
            personaHistoricoDTO.setCargoEmpresaLabora(personaDTO.getCargoEmpresaLabora());
            if (personaDTO.getNivelEducativo() != null) {
                if (personaDTO.getNivelEducativo().getCodigo() != null) {
                    personaHistoricoDTO.setNivelEducativo(personaDTO.getNivelEducativo());
                }

            }

            if (personaDTO.getTipoVivienda() != null) {
                personaHistoricoDTO.setTipoVivienda(personaDTO.getTipoVivienda());
            }

            if (personaDTO.getMunicipioExpedicionDocumento() != null) {
                personaHistoricoDTO.setMunicipioExpedicionDocumento(personaDTO.getMunicipioExpedicionDocumento());
            }

            personaHistoricoDTO.setFechaExpedicionDocumento(personaDTO.getFechaExpedicionDocumento());
            personaHistoricoDTO.setFechaNacimiento(personaDTO.getFechaNacimiento());
            personaHistoricoDTO.setFechaFallecimiento(personaDTO.getFechaFallecimiento());

            if (personaDTO.getEstadoCivil() != null) {
                personaHistoricoDTO.setEstadoCivil(personaDTO.getEstadoCivil());
            }

        }

        return personaHistoricoDTO;
    }
}
