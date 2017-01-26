package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.entidades.TipoEmail;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class ConfiguracionEmailHelper {
    // --- to DTO
    public static ConfiguracionEmailDTO toLevel0DTO(ConfiguracionEmail entidad) {
        ConfiguracionEmailDTO dto = new ConfiguracionEmailDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setFechaCambio(entidad.getFechaCambio());
        dto.setAsuntoEmail(entidad.getAsuntoEmail());
        dto.setCuerpoEmail(entidad.getCuerpoEmail());
        dto.setPieEmail(entidad.getPieEmail());

        return dto;
    }

    public static ConfiguracionEmailDTO toLevel1DTO(ConfiguracionEmail entidad) {
        ConfiguracionEmailDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoEmail() != null)
            dto.setTipoEmail(TipoEmailHelper.toLevel0DTO(entidad.getTipoEmail()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<ConfiguracionEmailDTO> toListLevel0DTO(List<ConfiguracionEmail> listEntidad) {
        List<ConfiguracionEmailDTO> listDto = new ArrayList<ConfiguracionEmailDTO>();
        for (ConfiguracionEmail entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionEmailDTO> toListLevel1DTO(List<ConfiguracionEmail> listEntidad) {
        List<ConfiguracionEmailDTO> listDto = new ArrayList<ConfiguracionEmailDTO>();
        for (ConfiguracionEmail entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionEmail toLevel0Entity(ConfiguracionEmailDTO dto, ConfiguracionEmail entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionEmail();
        }
        entidad.setId(dto.getId());
        entidad.setEstado(dto.getEstado());
        entidad.setFechaCambio(dto.getFechaCambio());
        entidad.setAsuntoEmail(dto.getAsuntoEmail());
        entidad.setCuerpoEmail(dto.getCuerpoEmail());
        entidad.setPieEmail(dto.getPieEmail());

        return entidad;
    }

    public static ConfiguracionEmail toLevel1Entity(ConfiguracionEmailDTO dto, ConfiguracionEmail entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoEmail() != null) {
            entidad.setTipoEmail(new TipoEmail());
            entidad.getTipoEmail().setCodigo(dto.getTipoEmail().getCodigo());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<ConfiguracionEmail> toListLevel0Entity(List<ConfiguracionEmailDTO> listDto) {
        List<ConfiguracionEmail> listEntidad = new ArrayList<ConfiguracionEmail>();
        for (ConfiguracionEmailDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionEmail> toListLevel1Entity(List<ConfiguracionEmailDTO> listDto) {
        List<ConfiguracionEmail> listEntidad = new ArrayList<ConfiguracionEmail>();
        for (ConfiguracionEmailDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
