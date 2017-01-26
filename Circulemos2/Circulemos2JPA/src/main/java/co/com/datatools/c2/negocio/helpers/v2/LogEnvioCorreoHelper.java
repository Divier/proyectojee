package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.entidades.LogEnvioCorreo;
import co.com.datatools.c2.entidades.TipoEmail;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class LogEnvioCorreoHelper {
    // --- to DTO
    public static LogEnvioCorreoDTO toLevel0DTO(LogEnvioCorreo entidad) {
        LogEnvioCorreoDTO dto = new LogEnvioCorreoDTO();

        dto.setId(entidad.getId());
        dto.setAsuntoEmail(entidad.getAsuntoEmail());
        dto.setCuerpoEmail(entidad.getCuerpoEmail());
        dto.setAdjunto(entidad.getAdjunto());
        dto.setFechaEnvio(entidad.getFechaEnvio());
        dto.setTablaSolicitud(entidad.getTablaSolicitud());
        dto.setIdTablaSolicitud(entidad.getIdTablaSolicitud());

        return dto;
    }

    public static LogEnvioCorreoDTO toLevel1DTO(LogEnvioCorreo entidad) {
        LogEnvioCorreoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getTipoEmail() != null)
            dto.setTipoEmail(TipoEmailHelper.toLevel0DTO(entidad.getTipoEmail()));
        if (entidad.getConfiguracionEmail() != null)
            dto.setConfiguracionEmail(ConfiguracionEmailHelper.toLevel0DTO(entidad.getConfiguracionEmail()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<LogEnvioCorreoDTO> toListLevel0DTO(List<LogEnvioCorreo> listEntidad) {
        List<LogEnvioCorreoDTO> listDto = new ArrayList<LogEnvioCorreoDTO>();
        for (LogEnvioCorreo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LogEnvioCorreoDTO> toListLevel1DTO(List<LogEnvioCorreo> listEntidad) {
        List<LogEnvioCorreoDTO> listDto = new ArrayList<LogEnvioCorreoDTO>();
        for (LogEnvioCorreo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LogEnvioCorreo toLevel0Entity(LogEnvioCorreoDTO dto, LogEnvioCorreo entidad) {
        if (null == entidad) {
            entidad = new LogEnvioCorreo();
        }
        entidad.setId(dto.getId());
        entidad.setAsuntoEmail(dto.getAsuntoEmail());
        entidad.setCuerpoEmail(dto.getCuerpoEmail());
        entidad.setAdjunto(dto.getAdjunto());
        entidad.setFechaEnvio(dto.getFechaEnvio());
        entidad.setTablaSolicitud(dto.getTablaSolicitud());
        entidad.setIdTablaSolicitud(dto.getIdTablaSolicitud());

        return entidad;
    }

    public static LogEnvioCorreo toLevel1Entity(LogEnvioCorreoDTO dto, LogEnvioCorreo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getTipoEmail() != null) {
            entidad.setTipoEmail(new TipoEmail());
            entidad.getTipoEmail().setCodigo(dto.getTipoEmail().getCodigo());
        }
        if (dto.getConfiguracionEmail() != null) {
            entidad.setConfiguracionEmail(new ConfiguracionEmail());
            entidad.getConfiguracionEmail().setId(dto.getConfiguracionEmail().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }

        return entidad;
    }

    public static List<LogEnvioCorreo> toListLevel0Entity(List<LogEnvioCorreoDTO> listDto) {
        List<LogEnvioCorreo> listEntidad = new ArrayList<LogEnvioCorreo>();
        for (LogEnvioCorreoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LogEnvioCorreo> toListLevel1Entity(List<LogEnvioCorreoDTO> listDto) {
        List<LogEnvioCorreo> listEntidad = new ArrayList<LogEnvioCorreo>();
        for (LogEnvioCorreoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
