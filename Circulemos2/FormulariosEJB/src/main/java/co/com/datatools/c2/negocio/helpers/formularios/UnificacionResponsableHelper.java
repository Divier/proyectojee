package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.entidades.ResponsableFormulario;
import co.com.datatools.c2.entidades.UnificacionResponsable;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 17:18:38 COT 2015
 */
public class UnificacionResponsableHelper {
    // --- to DTO
    public static UnificacionResponsableDTO toLevel0DTO(UnificacionResponsable entidad) {
        UnificacionResponsableDTO dto = new UnificacionResponsableDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static UnificacionResponsableDTO toLevel1DTO(UnificacionResponsable entidad) {
        UnificacionResponsableDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getResponsableFormulario() != null)
            dto.setResponsableFormulario(ResponsableFormularioHelper.toLevel0DTO(entidad.getResponsableFormulario()));

        return dto;
    }

    public static List<UnificacionResponsableDTO> toListLevel0DTO(List<UnificacionResponsable> listEntidad) {
        List<UnificacionResponsableDTO> listDto = new ArrayList<UnificacionResponsableDTO>();
        for (UnificacionResponsable entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<UnificacionResponsableDTO> toListLevel1DTO(List<UnificacionResponsable> listEntidad) {
        List<UnificacionResponsableDTO> listDto = new ArrayList<UnificacionResponsableDTO>();
        for (UnificacionResponsable entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static UnificacionResponsable toLevel0Entity(UnificacionResponsableDTO dto, UnificacionResponsable entidad) {
        if (null == entidad) {
            entidad = new UnificacionResponsable();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static UnificacionResponsable toLevel1Entity(UnificacionResponsableDTO dto, UnificacionResponsable entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getResponsableFormulario() != null) {
            entidad.setResponsableFormulario(new ResponsableFormulario());
            entidad.getResponsableFormulario().setId(dto.getResponsableFormulario().getId());
        }

        return entidad;
    }

    public static List<UnificacionResponsable> toListLevel0Entity(List<UnificacionResponsableDTO> listDto) {
        List<UnificacionResponsable> listEntidad = new ArrayList<UnificacionResponsable>();
        for (UnificacionResponsableDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<UnificacionResponsable> toListLevel1Entity(List<UnificacionResponsableDTO> listDto) {
        List<UnificacionResponsable> listEntidad = new ArrayList<UnificacionResponsable>();
        for (UnificacionResponsableDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
