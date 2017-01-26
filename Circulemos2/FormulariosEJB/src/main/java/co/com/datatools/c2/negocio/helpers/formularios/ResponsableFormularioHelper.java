package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.entidades.ResponsableFormulario;
import co.com.datatools.c2.entidades.TipoResponsableFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 17:19:44 COT 2015
 */
public class ResponsableFormularioHelper {
    // --- to DTO
    public static ResponsableFormularioDTO toLevel0DTO(ResponsableFormulario entidad) {
        ResponsableFormularioDTO dto = new ResponsableFormularioDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicioVincula(entidad.getFechaInicioVincula());
        dto.setFechaFinVincula(entidad.getFechaFinVincula());
        dto.setCorreoResponsableFormulario(entidad.getCorreoResponsableFormulario());

        return dto;
    }

    public static ResponsableFormularioDTO toLevel1DTO(ResponsableFormulario entidad) {
        ResponsableFormularioDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoResponsable() != null)
            dto.setTipoResponsable(TipoResponsableFormularioHelper.toLevel0DTO(entidad.getTipoResponsable()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<ResponsableFormularioDTO> toListLevel0DTO(List<ResponsableFormulario> listEntidad) {
        List<ResponsableFormularioDTO> listDto = new ArrayList<ResponsableFormularioDTO>();
        for (ResponsableFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ResponsableFormularioDTO> toListLevel1DTO(List<ResponsableFormulario> listEntidad) {
        List<ResponsableFormularioDTO> listDto = new ArrayList<ResponsableFormularioDTO>();
        for (ResponsableFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ResponsableFormulario toLevel0Entity(ResponsableFormularioDTO dto, ResponsableFormulario entidad) {
        if (null == entidad) {
            entidad = new ResponsableFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicioVincula(dto.getFechaInicioVincula());
        entidad.setFechaFinVincula(dto.getFechaFinVincula());
        entidad.setCorreoResponsableFormulario(dto.getCorreoResponsableFormulario());

        return entidad;
    }

    public static ResponsableFormulario toLevel1Entity(ResponsableFormularioDTO dto, ResponsableFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoResponsable() != null) {
            entidad.setTipoResponsable(new TipoResponsableFormulario());
            entidad.getTipoResponsable().setId(dto.getTipoResponsable().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<ResponsableFormulario> toListLevel0Entity(List<ResponsableFormularioDTO> listDto) {
        List<ResponsableFormulario> listEntidad = new ArrayList<ResponsableFormulario>();
        for (ResponsableFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ResponsableFormulario> toListLevel1Entity(List<ResponsableFormularioDTO> listDto) {
        List<ResponsableFormulario> listEntidad = new ArrayList<ResponsableFormulario>();
        for (ResponsableFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
