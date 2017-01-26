package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.NotificacionAvisoDTO;
import co.com.datatools.c2.entidades.NotificacionAviso;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Thu Feb 25 11:52:56 COT 2016
 */
public class NotificacionAvisoHelper {
    // --- to DTO
    public static NotificacionAvisoDTO toLevel0DTO(NotificacionAviso entidad) {
        NotificacionAvisoDTO dto = new NotificacionAvisoDTO();
        dto.setId(entidad.getId());
        dto.setFechaEjecucionNotificacion(entidad.getFechaEjecucionNotificacion());
        dto.setFechaGeneracion(entidad.getFechaGeneracion());
        dto.setFechaNotificacion(entidad.getFechaNotificacion());
        dto.setNotificado(entidad.getNotificado());
        dto.setIdArchivoGenerado(entidad.getIdArchivoGenerado());
        dto.setCantidadComparendos(entidad.getCantidadComparendos());
        dto.setConsecutivo(entidad.getConsecutivo());

        return dto;
    }

    public static NotificacionAvisoDTO toLevel1DTO(NotificacionAviso entidad) {
        NotificacionAvisoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<NotificacionAvisoDTO> toListLevel0DTO(List<NotificacionAviso> listEntidad) {
        List<NotificacionAvisoDTO> listDto = new ArrayList<NotificacionAvisoDTO>();
        for (NotificacionAviso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NotificacionAvisoDTO> toListLevel1DTO(List<NotificacionAviso> listEntidad) {
        List<NotificacionAvisoDTO> listDto = new ArrayList<NotificacionAvisoDTO>();
        for (NotificacionAviso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NotificacionAviso toLevel0Entity(NotificacionAvisoDTO dto, NotificacionAviso entidad) {
        if (null == entidad) {
            entidad = new NotificacionAviso();
        }
        entidad.setId(dto.getId());
        entidad.setFechaEjecucionNotificacion(dto.getFechaEjecucionNotificacion());
        entidad.setFechaGeneracion(dto.getFechaGeneracion());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());
        entidad.setNotificado(dto.getNotificado());
        entidad.setIdArchivoGenerado(dto.getIdArchivoGenerado());
        entidad.setCantidadComparendos(dto.getCantidadComparendos());
        entidad.setConsecutivo(dto.getConsecutivo());

        return entidad;
    }

    public static NotificacionAviso toLevel1Entity(NotificacionAvisoDTO dto, NotificacionAviso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<NotificacionAviso> toListLevel0Entity(List<NotificacionAvisoDTO> listDto) {
        List<NotificacionAviso> listEntidad = new ArrayList<NotificacionAviso>();
        for (NotificacionAvisoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NotificacionAviso> toListLevel1Entity(List<NotificacionAvisoDTO> listDto) {
        List<NotificacionAviso> listEntidad = new ArrayList<NotificacionAviso>();
        for (NotificacionAvisoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
