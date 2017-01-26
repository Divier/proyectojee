package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ParametroOrganismoDTO;
import co.com.datatools.c2.entidades.Parametro;
import co.com.datatools.c2.entidades.ParametroOrganismo;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class ParametroOrganismoHelper {
    // --- to DTO
    public static ParametroOrganismoDTO toLevel0DTO(ParametroOrganismo entidad) {
        ParametroOrganismoDTO dto = new ParametroOrganismoDTO();
        dto.setId(entidad.getId());
        dto.setValor(entidad.getValor());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static ParametroOrganismoDTO toLevel1DTO(ParametroOrganismo entidad) {
        ParametroOrganismoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getParametro() != null)
            dto.setParametro(ParametroHelper.toLevel0DTO(entidad.getParametro()));

        return dto;
    }

    public static List<ParametroOrganismoDTO> toListLevel0DTO(List<ParametroOrganismo> listEntidad) {
        List<ParametroOrganismoDTO> listDto = new ArrayList<ParametroOrganismoDTO>();
        for (ParametroOrganismo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ParametroOrganismoDTO> toListLevel1DTO(List<ParametroOrganismo> listEntidad) {
        List<ParametroOrganismoDTO> listDto = new ArrayList<ParametroOrganismoDTO>();
        for (ParametroOrganismo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ParametroOrganismo toLevel0Entity(ParametroOrganismoDTO dto, ParametroOrganismo entidad) {
        if (null == entidad) {
            entidad = new ParametroOrganismo();
        }
        entidad.setId(dto.getId());
        entidad.setValor(dto.getValor());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static ParametroOrganismo toLevel1Entity(ParametroOrganismoDTO dto, ParametroOrganismo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getParametro() != null) {
            entidad.setParametro(new Parametro());
            entidad.getParametro().setCodigo(dto.getParametro().getCodigo());
        }

        return entidad;
    }

    public static List<ParametroOrganismo> toListLevel0Entity(List<ParametroOrganismoDTO> listDto) {
        List<ParametroOrganismo> listEntidad = new ArrayList<ParametroOrganismo>();
        for (ParametroOrganismoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ParametroOrganismo> toListLevel1Entity(List<ParametroOrganismoDTO> listDto) {
        List<ParametroOrganismo> listEntidad = new ArrayList<ParametroOrganismo>();
        for (ParametroOrganismoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
