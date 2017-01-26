package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.PersonaProvisionalDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.DireccionPersonaFuenteExterna;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaFuenteExterna;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.negocio.helpers.comun.DepartamentoHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelperExtend;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHelper;

/**
 * Extencion del Helper de persona para construir las relaciones adicionales del dto
 * 
 * @author felipe.martinez <br/>
 *         luis.forero (mod 2014-11-19)
 */
public class PersonaHelperExtend extends PersonaHelper {

    public static PersonaDTO toLevel1DTO(PersonaFuenteExterna entidad) {
        PersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        if (entidad.getFuenteInformacion() != null)
            dto.setFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getFuenteInformacion()));
        if (entidad.getEstadoCivil() != null)
            dto.setEstadoCivil(EstadoCivilHelper.toLevel0DTO(entidad.getEstadoCivil()));
        if (entidad.getTipoVivienda() != null)
            dto.setTipoVivienda(TipoViviendaHelper.toLevel0DTO(entidad.getTipoVivienda()));
        if (entidad.getNivelEducativo() != null)
            dto.setNivelEducativo(NivelEducativoHelper.toLevel0DTO(entidad.getNivelEducativo()));

        if ((entidad.getDireccionFuenteExternaList() != null) && (!entidad.getDireccionFuenteExternaList().isEmpty())) {
            dto.setDireccionPersonaList(new ArrayList<DireccionPersonaDTO>());
            for (DireccionPersonaFuenteExterna direccionPersona : entidad.getDireccionFuenteExternaList()) {
                DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
                direccionPersonaDTO.setId(direccionPersona.getId());
                // TODO: UBICABILIDAD ARREGLAR
                // direccionPersonaDTO.setFechaInicio(direccionPersona.getFechaInicio());
                // direccionPersonaDTO.setFechaFin(direccionPersona.getFechaFin());
                if (direccionPersona.getDireccion() != null)
                    direccionPersonaDTO.setDireccion(DireccionHelper.toLevel0DTO(direccionPersona.getDireccion()));
                if (direccionPersona.getPersonaFuenteExterna() != null)
                    direccionPersonaDTO
                            .setPersona(PersonaHelperExtend.toLevel0DTO(direccionPersona.getPersonaFuenteExterna()));
                if (entidad.getFuenteInformacion() != null)
                    direccionPersonaDTO.setTipoFuenteInformacion(
                            TipoFuenteInformacionHelper.toLevel0DTO(entidad.getFuenteInformacion()));

                DireccionDTO direccionDTO = DireccionHelperExtend.toLevel1DTO(direccionPersona.getDireccion());
                direccionPersonaDTO.setDireccion(direccionDTO);
                dto.getDireccionPersonaList().add(direccionPersonaDTO);
            }

        }

        return dto;
    }

    private static PersonaDTO toLevel0DTO(PersonaFuenteExterna entidad) {
        PersonaDTO dto = new PersonaDTO();

        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setFechaUltimaActualizacion(entidad.getFechaUltimaActualizacion());
        dto.setFechaExpedicionDocumento(entidad.getFechaExpedicionDocumento());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        dto.setFechaFallecimiento(entidad.getFechaFallecimiento());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // dto.setNumeroTelefonico(entidad.getNumeroTelefonico());
        // dto.setCorreoElectronico(entidad.getCorreoElectronico());
        // dto.setNumeroCelular(entidad.getNumeroCelular());
        // dto.setFaxEmpresaLabora(entidad.getFaxEmpresaLabora());
        dto.setGenero(entidad.getGenero());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setNombreEmpresaLabora(entidad.getNombreEmpresaLabora());
        dto.setNotificaDirEmpresa(entidad.getNotificaDirEmpresa());
        dto.setCargoEmpresaLabora(entidad.getCargoEmpresaLabora());
        dto.setHuellaDigital(entidad.getHuellaDigital());
        return dto;
    }

    // TO DTO LEVEL 1 (VALIDADO)
    public static PersonaDTO toLevel1DTO(Persona persona) {
        PersonaDTO personaDTO = PersonaHelper.toLevel1DTO(persona);

        if (persona.getMunicipioExpedicionDocumento() != null) {
            DepartamentoDTO departamentoDTO = new DepartamentoDTO();
            if (persona.getMunicipioExpedicionDocumento().getDepartamento() != null) {
                departamentoDTO = DepartamentoHelper
                        .toLevel0DTO(persona.getMunicipioExpedicionDocumento().getDepartamento());
            }

            personaDTO.getMunicipioExpedicionDocumento().setDepartamento(departamentoDTO);
        }

        if (personaDTO.getNivelEducativo() == null) {
            personaDTO.setNivelEducativo(new NivelEducativoDTO());
        }

        if (personaDTO.getEstadoCivil() == null) {
            personaDTO.setEstadoCivil(new EstadoCivilDTO());
        }

        if (personaDTO.getTipoVivienda() == null) {
            personaDTO.setTipoVivienda(new TipoViviendaDTO());
        }

        if ((persona.getDireccionPersonaList() != null) && (!persona.getDireccionPersonaList().isEmpty())) {
            for (DireccionPersona direccionPersona : persona.getDireccionPersonaList()) {
                DireccionPersonaDTO direccionPersonaDTO = DireccionPersonaHelper.toLevel1DTO(direccionPersona);
                DireccionDTO direccionDTO = DireccionHelperExtend.toLevel1DTO(direccionPersona.getDireccion());
                direccionPersonaDTO.setDireccion(direccionDTO);
                personaDTO.getDireccionPersonaList().add(direccionPersonaDTO);
            }

        }

        return personaDTO;
    }

    // TO ENTITY LEVEL 1
    public static Persona toLevel1Entity(PersonaDTO personaDTO, Persona persona) {
        Persona personaNivel1 = PersonaHelper.toLevel0Entity(personaDTO, persona);

        if ((personaDTO.getEstadoCivil() != null) && (personaDTO.getEstadoCivil().getId() != null)) {
            EstadoCivil estadoCivil = new EstadoCivil();
            estadoCivil.setId(personaDTO.getEstadoCivil().getId());
            personaNivel1.setEstadoCivil(estadoCivil);
        }

        if ((personaDTO.getMunicipioExpedicionDocumento() != null)
                && (personaDTO.getMunicipioExpedicionDocumento().getId() != null)) {
            Municipio municipio = new Municipio();
            municipio.setId(personaDTO.getMunicipioExpedicionDocumento().getId());
            personaNivel1.setMunicipioExpedicionDocumento(municipio);
        }

        if ((personaDTO.getNivelEducativo() != null) && (personaDTO.getNivelEducativo().getCodigo() != null)) {
            NivelEducativo nivelEducativo = new NivelEducativo();
            nivelEducativo.setCodigo(personaDTO.getNivelEducativo().getCodigo());
            personaNivel1.setNivelEducativo(nivelEducativo);
        }

        OrganismoTransito organismoTransito = new OrganismoTransito();
        organismoTransito.setCodigoOrganismo(personaDTO.getOrganismoTransito().getCodigoOrganismo());
        personaNivel1.setOrganismoTransito(organismoTransito);

        TipoFuenteInformacion tipoFuenteInformacion = new TipoFuenteInformacion();
        tipoFuenteInformacion.setId(personaDTO.getFuenteInformacion().getId());
        personaNivel1.setFuenteInformacion(tipoFuenteInformacion);

        TipoIdentificacionPersona tipoIdentificacionPersona = new TipoIdentificacionPersona();
        tipoIdentificacionPersona.setId(personaDTO.getTipoIdentificacion().getId());
        personaNivel1.setTipoIdentificacion(tipoIdentificacionPersona);

        if ((personaDTO.getTipoVivienda() != null) && (personaDTO.getTipoVivienda().getCodigo() != null)) {
            TipoVivienda tipoVivienda = new TipoVivienda();
            tipoVivienda.setCodigo(personaDTO.getTipoVivienda().getCodigo());
            personaNivel1.setTipoVivienda(tipoVivienda);
        }

        return personaNivel1;
    }

    public static List<PersonaDTO> toListLevel1DTO(List<Persona> listEntidad) {
        List<PersonaDTO> listDto = new ArrayList<PersonaDTO>();
        for (Persona entidad : listEntidad) {
            listDto.add(PersonaHelperExtend.toLevel1DTO(entidad));
        }

        return listDto;
    }

    // TO DTO LEVEL 1
    public static PersonaDTO toLevel1DTO(PersonaProvisionalDTO provisionalDTO) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(provisionalDTO.getId());
        personaDTO.setApellido1(provisionalDTO.getApellido1());
        personaDTO.setApellido2(provisionalDTO.getApellido2());
        personaDTO.setCargoEmpresaLabora(provisionalDTO.getCargoEmpresaLabora());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // personaDTO.setCorreoElectronico(provisionalDTO.getCorreoElectronico());
        // personaDTO.setFaxEmpresaLabora(provisionalDTO.getFaxEmpresaLabora());
        // personaDTO.setNumeroTelefonico(provisionalDTO.getNumeroTelefonico());
        // personaDTO.setTelefonoEmpresaLabora(provisionalDTO.getTelefonoEmpresaLabora());
        // personaDTO.setNumeroCelular(provisionalDTO.getNumeroCelular());
        personaDTO.setFechaExpedicionDocumento(provisionalDTO.getFechaExpedicionDocumento());
        personaDTO.setFechaNacimiento(provisionalDTO.getFechaNacimiento());
        personaDTO.setGenero(provisionalDTO.getGenero());
        personaDTO.setNombreEmpresaLabora(provisionalDTO.getNombreEmpresaLabora());
        personaDTO.setNombre1(provisionalDTO.getNombre1());
        personaDTO.setNombre2(provisionalDTO.getNombre2());
        personaDTO.setNotificaDirEmpresa(provisionalDTO.getNotificaDirEmpresa());
        personaDTO.setNumeroIdentificacion(provisionalDTO.getNumeroIdentificacion());
        if (provisionalDTO.getEstadoCivil() != null && provisionalDTO.getEstadoCivil().getId() != null) {
            EstadoCivilDTO estadoCivilDTO = new EstadoCivilDTO();
            estadoCivilDTO.setId(provisionalDTO.getEstadoCivil().getId());
            personaDTO.setEstadoCivil(estadoCivilDTO);
        }
        if (provisionalDTO.getMunicipioExpedicionDocumento() != null
                && provisionalDTO.getMunicipioExpedicionDocumento().getId() != null) {
            MunicipioDTO municipioDTO = new MunicipioDTO();
            municipioDTO.setId(provisionalDTO.getMunicipioExpedicionDocumento().getId());
            personaDTO.setMunicipioExpedicionDocumento(municipioDTO);
        }
        if (provisionalDTO.getNivelEducativo() != null && provisionalDTO.getNivelEducativo().getCodigo() != null) {
            NivelEducativoDTO nivelEducativoDTO = new NivelEducativoDTO();
            nivelEducativoDTO.setCodigo(provisionalDTO.getNivelEducativo().getCodigo());
            personaDTO.setNivelEducativo(nivelEducativoDTO);
        }
        if (provisionalDTO.getOrganismoTransito() != null
                && provisionalDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(provisionalDTO.getOrganismoTransito().getCodigoOrganismo());
            personaDTO.setOrganismoTransito(organismoTransitoDTO);
        }
        if (provisionalDTO.getTipoIdentificacion() != null && provisionalDTO.getTipoIdentificacion().getId() != null) {
            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            tipoIdentificacionPersonaDTO.setId(provisionalDTO.getTipoIdentificacion().getId());
            personaDTO.setTipoIdentificacion(tipoIdentificacionPersonaDTO);
        }
        if (provisionalDTO.getTipoVivienda() != null && provisionalDTO.getTipoVivienda().getCodigo() != null) {
            TipoViviendaDTO tipoViviendaDTO = new TipoViviendaDTO();
            tipoViviendaDTO.setCodigo(provisionalDTO.getTipoVivienda().getCodigo());
            personaDTO.setTipoVivienda(tipoViviendaDTO);
        }

        return personaDTO;
    }

    /**
     * Permite extraer el nombre de la persona ya sea de instancia juridica o natural
     * 
     * @param persona
     *            persona a la cual se concatena nombre completo
     * @return nombre de la persona ya sea o juridica o natural
     * @author luis.forero (2014-12-26)
     */
    public static String construirNombrePersona(PersonaDTO persona) {
        StringBuilder strNombre = new StringBuilder(1);
        if (persona != null) {
            if (persona instanceof PersonaJuridicaDTO) {
                strNombre.append(((PersonaJuridicaDTO) persona).getNombreComercial());
            } else {
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre1()) ? persona.getNombre1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre2()) ? persona.getNombre2() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido1()) ? persona.getApellido1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido2()) ? persona.getApellido2() : "");
            }
        }
        return strNombre.toString();
    }

    /**
     * Permite extraer el nombre completo de la persona natural
     * 
     * @param persona
     *            persona a la cual se concatena nombre completo
     * @return nombre de la persona natural
     * @author luis.forero (2014-12-26)
     */
    public static String construirNombrePersona(Persona persona) {
        StringBuilder strNombre = new StringBuilder(1);
        strNombre.append(StringUtils.isNotEmpty(persona.getNombre1()) ? persona.getNombre1() + " " : "");
        strNombre.append(StringUtils.isNotEmpty(persona.getNombre2()) ? persona.getNombre2() + " " : "");
        strNombre.append(StringUtils.isNotEmpty(persona.getApellido1()) ? persona.getApellido1() + " " : "");
        strNombre.append(StringUtils.isNotEmpty(persona.getApellido2()) ? persona.getApellido2() : "");
        return strNombre.toString();
    }
}
