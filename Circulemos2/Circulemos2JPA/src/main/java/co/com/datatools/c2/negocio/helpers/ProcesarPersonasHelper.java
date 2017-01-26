package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.DivisionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.GrupoActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.personas.SeccionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.entidades.personas.RepresentanteLegal;
import co.com.datatools.c2.negocio.helpers.comun.DepartamentoHelper;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.EstadoCivilHelper;
import co.com.datatools.c2.negocio.helpers.personas.NivelEducativoHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoSociedadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoViviendaHelper;

/**
 * Clase encargada de controlar la conversion de entidades relacionadas con persona en DTO
 * <ul>
 * <li>{@link Persona}</li>
 * <li>{@link PersonaJuridica}</li>
 * <li>{@link RepresentanteLegal}</li>
 * <li>{@link Funcionario}</li>
 * </ul>
 * El objetivo de esta clase es evitar el uso de Helpers Auto generados q generan multiples consultas a la BD
 * 
 * @author felipe.martinez
 */
public class ProcesarPersonasHelper {

    public static PersonaDTO toPersonaLevel0DTO(Persona entidad) {
        PersonaDTO dto = null;
        if (entidad.getPersonaJuridica() != null) {
            dto = toLevel0DTO(entidad.getPersonaJuridica(), new PersonaJuridicaDTO());
        } else {
            dto = new PersonaDTO();
        }
        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setFechaUltimaActualizacion(entidad.getFechaUltimaActualizacion());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // dto.setNumeroTelefonico(entidad.getNumeroTelefonico());

        dto.setFechaExpedicionDocumento(entidad.getFechaExpedicionDocumento());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        dto.setFechaFallecimiento(entidad.getFechaFallecimiento());
        dto.setGenero(entidad.getGenero());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // dto.setCorreoElectronico(entidad.getCorreoElectronico());
        // dto.setNumeroCelular(entidad.getNumeroCelular());
        // dto.setTelefonoEmpresaLabora(entidad.getTelefonoEmpresaLabora());
        // dto.setFaxEmpresaLabora(entidad.getFaxEmpresaLabora());
        // dto.setHuellaDigital(entidad.getHuellaDigital());
        dto.setNombreEmpresaLabora(entidad.getNombreEmpresaLabora());
        dto.setNotificaDirEmpresa(entidad.getNotificaDirEmpresa());
        dto.setCargoEmpresaLabora(entidad.getCargoEmpresaLabora());
        dto.setRutaFoto(entidad.getRutaFoto());
        dto.setFechaFoto(entidad.getFechaFoto());

        return dto;
    }

    private static PersonaJuridicaDTO toLevel0DTO(PersonaJuridica entidad, PersonaJuridicaDTO dto) {
        dto.setDigitoVerificacion(entidad.getDigitoVerificacion());
        dto.setNombreComercial(entidad.getNombreComercial());
        dto.setSigla(entidad.getSigla());
        return dto;
    }

    public static PersonaDTO toPersonaLevel1DTO(Persona entidad) {
        PersonaDTO dto = toPersonaLevel0DTO(entidad);

        if (dto instanceof PersonaJuridicaDTO)
            dto = toLevel1DTO(entidad.getPersonaJuridica(), (PersonaJuridicaDTO) dto);

        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));

        if (entidad.getFuenteInformacion() != null)
            dto.setFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getFuenteInformacion()));

        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        MunicipioDTO municipioDTO;
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        if (entidad.getMunicipioExpedicionDocumento() != null) {
            municipioDTO = MunicipioHelper.toLevel0DTO(entidad.getMunicipioExpedicionDocumento());
            if (entidad.getMunicipioExpedicionDocumento().getDepartamento() != null) {
                departamentoDTO = DepartamentoHelper
                        .toLevel0DTO(entidad.getMunicipioExpedicionDocumento().getDepartamento());
            }

        } else {
            municipioDTO = new MunicipioDTO();
        }

        dto.setMunicipioExpedicionDocumento(municipioDTO);
        dto.getMunicipioExpedicionDocumento().setDepartamento(departamentoDTO);

        if (!(dto instanceof PersonaJuridicaDTO)) {
            if (entidad.getEstadoCivil() != null) {
                dto.setEstadoCivil(EstadoCivilHelper.toLevel0DTO(entidad.getEstadoCivil()));
            } else {
                dto.setEstadoCivil(new EstadoCivilDTO());
            }

            if (entidad.getTipoVivienda() != null) {
                dto.setTipoVivienda(TipoViviendaHelper.toLevel0DTO(entidad.getTipoVivienda()));
            } else {
                dto.setTipoVivienda(new TipoViviendaDTO());
            }

            if (entidad.getNivelEducativo() != null) {
                dto.setNivelEducativo(NivelEducativoHelper.toLevel0DTO(entidad.getNivelEducativo()));
            } else {
                dto.setNivelEducativo(new NivelEducativoDTO());
            }

        }

        return dto;
    }

    /**
     * Se encarga de transformar la persona a sus datos basicos y resolviendo si es una persona juridica
     * 
     * @author giovanni.velandia
     * @param entidad
     * @return
     */
    public static PersonaDTO toPersonaBasicaDTO(Persona entidad) {
        PersonaDTO dto = toPersonaLevel0DTO(entidad);

        if (dto instanceof PersonaJuridicaDTO)
            dto = (PersonaJuridicaDTO) dto;

        if (entidad.getTipoIdentificacion() != null) {
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        }
        return dto;
    }

    private static PersonaJuridicaDTO toLevel1DTO(PersonaJuridica entidad, PersonaJuridicaDTO dto) {

        if (entidad.getTipoSociedad() != null)
            dto.setTipoSociedad(TipoSociedadHelper.toLevel0DTO(entidad.getTipoSociedad()));

        if (entidad.getClaseActividadEconomica() != null) {
            ClaseActividadEconomicaDTO claseActividadEconomicaDTO = new ClaseActividadEconomicaDTO();
            claseActividadEconomicaDTO.setId(entidad.getClaseActividadEconomica().getId());
            claseActividadEconomicaDTO.setNombre(entidad.getClaseActividadEconomica().getNombre());
            dto.setClaseActividadEconomica(claseActividadEconomicaDTO);

            GrupoActividadEconomicaDTO grupoActividadEconomicaDTO = new GrupoActividadEconomicaDTO();
            grupoActividadEconomicaDTO.setId(entidad.getClaseActividadEconomica().getGrupoActividadEconomica().getId());
            grupoActividadEconomicaDTO
                    .setNombre(entidad.getClaseActividadEconomica().getGrupoActividadEconomica().getNombre());
            claseActividadEconomicaDTO.setGrupoActividadEconomica(grupoActividadEconomicaDTO);

            DivisionActividadEconomicaDTO divisionActividadEconomicaDTO = new DivisionActividadEconomicaDTO();
            divisionActividadEconomicaDTO.setId(entidad.getClaseActividadEconomica().getGrupoActividadEconomica()
                    .getDivisionActividadEconomica().getId());
            divisionActividadEconomicaDTO.setNombre(entidad.getClaseActividadEconomica().getGrupoActividadEconomica()
                    .getDivisionActividadEconomica().getNombre());
            grupoActividadEconomicaDTO.setDivisionActividadEconomica(divisionActividadEconomicaDTO);

            SeccionActividadEconomicaDTO seccionActividadEconomicaDTO = new SeccionActividadEconomicaDTO();
            seccionActividadEconomicaDTO.setId(entidad.getClaseActividadEconomica().getGrupoActividadEconomica()
                    .getDivisionActividadEconomica().getSeccionActividadEconomica().getId());
            seccionActividadEconomicaDTO.setNombre(entidad.getClaseActividadEconomica().getGrupoActividadEconomica()
                    .getDivisionActividadEconomica().getSeccionActividadEconomica().getNombre());
            divisionActividadEconomicaDTO.setSeccionActividadEconomica(seccionActividadEconomicaDTO);
        }

        MunicipioDTO municipioDTO;
        DepartamentoDTO departamentoDTO;
        if (entidad.getMunicipio() != null) {
            municipioDTO = MunicipioHelper.toLevel0DTO(entidad.getMunicipio());
            departamentoDTO = DepartamentoHelper.toLevel1DTO(entidad.getMunicipio().getDepartamento());
        } else {
            municipioDTO = new MunicipioDTO();
            departamentoDTO = new DepartamentoDTO();
            PaisDTO paisDTO = new PaisDTO();
            departamentoDTO.setPais(paisDTO);
        }

        municipioDTO.setDepartamento(departamentoDTO);
        dto.setMunicipio(municipioDTO);

        return dto;
    }

    public static List<PersonaDTO> toPersonaListLevel0DTO(List<Persona> listEntidad) {
        List<PersonaDTO> listDto = new ArrayList<PersonaDTO>(listEntidad.size());
        for (Persona entidad : listEntidad) {
            listDto.add(toPersonaLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PersonaDTO> toPersonaListLevel1DTO(List<Persona> listEntidad) {
        List<PersonaDTO> listDto = new ArrayList<PersonaDTO>(listEntidad.size());
        for (Persona entidad : listEntidad) {
            listDto.add(toPersonaLevel1DTO(entidad));
        }
        return listDto;
    }

    public static RepresentanteLegalDTO toRepresentanteLegalLevel1DTO(RepresentanteLegal entidad,
            PersonaDTO dtoPersona) {
        RepresentanteLegalDTO dto = new RepresentanteLegalDTO();

        dto = (RepresentanteLegalDTO) copiarPersona(dtoPersona, dto);
        // datos de representante legal
        dto.setIdRepresentante(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        // Adicion de campos para traza de representante
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        if (entidad.getUsuarioRegistro() != null) {
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));
        }
        if (entidad.getUsuarioActualiza() != null) {
            dto.setUsuarioActualiza(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioActualiza()));
        }

        return dto;
    }

    public static PersonaDTO copiarPersona(PersonaDTO origen, PersonaDTO destino) {
        destino.setId(origen.getId());
        destino.setNumeroIdentificacion(origen.getNumeroIdentificacion());
        destino.setFechaUltimaActualizacion(origen.getFechaUltimaActualizacion());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // destino.setNumeroTelefonico(origen.getNumeroTelefonico());
        destino.setFechaExpedicionDocumento(origen.getFechaExpedicionDocumento());
        destino.setFechaNacimiento(origen.getFechaNacimiento());
        destino.setFechaFallecimiento(origen.getFechaFallecimiento());
        destino.setGenero(origen.getGenero());
        destino.setApellido1(origen.getApellido1());
        destino.setApellido2(origen.getApellido2());
        destino.setNombre1(origen.getNombre1());
        destino.setNombre2(origen.getNombre2());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // destino.setCorreoElectronico(origen.getCorreoElectronico());
        // destino.setNumeroCelular(origen.getNumeroCelular());
        destino.setNombreEmpresaLabora(origen.getNombreEmpresaLabora());
        destino.setNotificaDirEmpresa(origen.getNotificaDirEmpresa());
        destino.setCargoEmpresaLabora(origen.getCargoEmpresaLabora());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // destino.setTelefonoEmpresaLabora(origen.getTelefonoEmpresaLabora());
        // destino.setFaxEmpresaLabora(origen.getFaxEmpresaLabora());
        destino.setHuellaDigital(origen.getHuellaDigital());
        destino.setRutaFoto(origen.getRutaFoto());
        destino.setFechaFoto(origen.getFechaFoto());
        destino.setDireccionPersonaList(origen.getDireccionPersonaList());
        destino.setEstadoCivil(origen.getEstadoCivil());
        destino.setFuenteInformacion(origen.getFuenteInformacion());
        destino.setMunicipioExpedicionDocumento(origen.getMunicipioExpedicionDocumento());
        destino.setNivelEducativo(origen.getNivelEducativo());
        destino.setOrganismoTransito(origen.getOrganismoTransito());
        destino.setParentescoPersonaList(origen.getParentescoPersonaList());
        destino.setTipoIdentificacion(origen.getTipoIdentificacion());
        destino.setTipoVivienda(origen.getTipoVivienda());

        if (origen instanceof PersonaJuridicaDTO && destino instanceof PersonaJuridicaDTO) {
            PersonaJuridicaDTO personaJuridicaOrigen = (PersonaJuridicaDTO) origen;
            PersonaJuridicaDTO personaJuridicaDestino = (PersonaJuridicaDTO) destino;
            personaJuridicaDestino.setClaseActividadEconomica(personaJuridicaOrigen.getClaseActividadEconomica());
            personaJuridicaDestino.setDigitoVerificacion(personaJuridicaOrigen.getDigitoVerificacion());
            personaJuridicaDestino.setMunicipio(personaJuridicaOrigen.getMunicipio());
            personaJuridicaDestino.setNombreComercial(personaJuridicaOrigen.getNombreComercial());
            personaJuridicaDestino.setRepresentanteLegalList(personaJuridicaOrigen.getRepresentanteLegalList());
            personaJuridicaDestino.setSigla(personaJuridicaOrigen.getSigla());
            personaJuridicaDestino.setTipoSociedad(personaJuridicaOrigen.getTipoSociedad());
        }

        if (origen instanceof RepresentanteLegalDTO && destino instanceof RepresentanteLegalDTO) {
            RepresentanteLegalDTO representanteLegalOrigen = (RepresentanteLegalDTO) origen;
            RepresentanteLegalDTO representanteLegalDestino = (RepresentanteLegalDTO) destino;
            representanteLegalDestino.setCorreoElectronico(representanteLegalOrigen.getCorreoElectronico());
            representanteLegalDestino.setFechaFin(representanteLegalOrigen.getFechaFin());
            representanteLegalDestino.setFechaInicio(representanteLegalOrigen.getFechaInicio());
            representanteLegalDestino.setIdRepresentante(representanteLegalOrigen.getIdRepresentante());
            representanteLegalDestino.setPersonaJuridica(representanteLegalOrigen.getPersonaJuridica());
            // Adicion de campos para traza de representante
            representanteLegalDestino.setFechaRegistro(representanteLegalOrigen.getFechaRegistro());
            representanteLegalDestino.setFechaActualizacion(representanteLegalOrigen.getFechaActualizacion());
            representanteLegalDestino.setUsuarioRegistro(representanteLegalOrigen.getUsuarioRegistro());
            representanteLegalDestino.setUsuarioActualiza(representanteLegalOrigen.getUsuarioActualiza());
        }

        return destino;
    }
}
