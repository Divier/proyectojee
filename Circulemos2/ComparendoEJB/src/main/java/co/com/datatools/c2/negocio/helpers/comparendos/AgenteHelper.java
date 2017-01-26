package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.entidades.Agente;
import co.com.datatools.c2.entidades.TipoEntidadAgenteTransito;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 13 14:53:53 COT 2015
 */
public class AgenteHelper {
    // --- to DTO
    public static AgenteDTO toLevel0DTO(Agente entidad) {
        AgenteDTO dto = new AgenteDTO();
        dto.setId(entidad.getId());
        dto.setFechaFinVigencia(entidad.getFechaFinVigencia());
        dto.setFechaInicioVigencia(entidad.getFechaInicioVigencia());
        dto.setPlaca(entidad.getPlaca());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setNumeroFirma(entidad.getNumeroFirma());

        return dto;
    }

    public static AgenteDTO toLevel1DTO(Agente entidad) {
        AgenteDTO dto = toLevel0DTO(entidad);
        if (entidad.getEntidadAgenteTransito() != null)
            dto.setEntidadAgenteTransito(
                    TipoEntidadAgenteTransitoHelper.toLevel0DTO(entidad.getEntidadAgenteTransito()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getUsuarioActualizacion() != null)
            dto.setUsuarioActualizacion(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioActualizacion()));
        if (entidad.getMotivoVigenciaAgente() != null)
            dto.setMotivoVigenciaAgente(MotivoVigenciaAgenteHelper.toLevel0DTO(entidad.getMotivoVigenciaAgente()));

        return dto;
    }

    public static List<AgenteDTO> toListLevel0DTO(List<Agente> listEntidad) {
        List<AgenteDTO> listDto = new ArrayList<AgenteDTO>();
        for (Agente entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<AgenteDTO> toListLevel1DTO(List<Agente> listEntidad) {
        List<AgenteDTO> listDto = new ArrayList<AgenteDTO>();
        for (Agente entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Agente toLevel0Entity(AgenteDTO dto, Agente entidad) {
        if (null == entidad) {
            entidad = new Agente();
        }
        entidad.setId(dto.getId());
        entidad.setFechaFinVigencia(dto.getFechaFinVigencia());
        entidad.setFechaInicioVigencia(dto.getFechaInicioVigencia());
        entidad.setPlaca(dto.getPlaca());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setNumeroFirma(dto.getNumeroFirma());

        return entidad;
    }

    public static Agente toLevel1Entity(AgenteDTO dto, Agente entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEntidadAgenteTransito() != null) {
            entidad.setEntidadAgenteTransito(new TipoEntidadAgenteTransito());
            entidad.getEntidadAgenteTransito().setId(dto.getEntidadAgenteTransito().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getUsuarioActualizacion() != null) {
            entidad.setUsuarioActualizacion(UsuarioPersonaHelper.toLevel1Entity(dto.getUsuarioActualizacion(), null));
        }
        if (dto.getMotivoVigenciaAgente() != null) {
            entidad.setMotivoVigenciaAgente(
                    MotivoVigenciaAgenteHelper.toLevel0Entity(dto.getMotivoVigenciaAgente(), null));
        }

        return entidad;
    }

    public static List<Agente> toListLevel0Entity(List<AgenteDTO> listDto) {
        List<Agente> listEntidad = new ArrayList<Agente>();
        for (AgenteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Agente> toListLevel1Entity(List<AgenteDTO> listDto) {
        List<Agente> listEntidad = new ArrayList<Agente>();
        for (AgenteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
