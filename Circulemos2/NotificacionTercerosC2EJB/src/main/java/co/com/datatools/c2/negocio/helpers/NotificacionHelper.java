package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.NotificacionDTO;
import co.com.datatools.c2.entidades.MedioNotificacion;
import co.com.datatools.c2.entidades.Notificacion;
import co.com.datatools.c2.entidades.TipoNotificacion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 14:23:02 COT 2016
 */
public class NotificacionHelper {
    // --- to DTO
    public static NotificacionDTO toLevel0DTO(Notificacion entidad) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(entidad.getId());
        dto.setFechaSolicitud(entidad.getFechaSolicitud());

        return dto;
    }

    public static NotificacionDTO toLevel1DTO(Notificacion entidad) {
        NotificacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoNotificacion() != null)
            dto.setTipoNotificacion(TipoNotificacionHelper.toLevel0DTO(entidad.getTipoNotificacion()));
        if (entidad.getMedioNotificacion() != null)
            dto.setMedioNotificacion(MedioNotificacionHelper.toLevel0DTO(entidad.getMedioNotificacion()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));
        return dto;
    }

    public static List<NotificacionDTO> toListLevel0DTO(List<Notificacion> listEntidad) {
        List<NotificacionDTO> listDto = new ArrayList<NotificacionDTO>();
        for (Notificacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NotificacionDTO> toListLevel1DTO(List<Notificacion> listEntidad) {
        List<NotificacionDTO> listDto = new ArrayList<NotificacionDTO>();
        for (Notificacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Notificacion toLevel0Entity(NotificacionDTO dto, Notificacion entidad) {
        if (null == entidad) {
            entidad = new Notificacion();
        }
        entidad.setId(dto.getId());
        entidad.setFechaSolicitud(dto.getFechaSolicitud());

        return entidad;
    }

    public static Notificacion toLevel1Entity(NotificacionDTO dto, Notificacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoNotificacion() != null) {
            entidad.setTipoNotificacion(new TipoNotificacion());
            entidad.getTipoNotificacion().setId(dto.getTipoNotificacion().getId());
        }
        if (dto.getMedioNotificacion() != null) {
            entidad.setMedioNotificacion(new MedioNotificacion());
            entidad.getMedioNotificacion().setId(dto.getMedioNotificacion().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            // entidad.getUsuario().setIdUsuario(dto.getUsuario().getIdUsuario());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        return entidad;
    }

    public static List<Notificacion> toListLevel0Entity(List<NotificacionDTO> listDto) {
        List<Notificacion> listEntidad = new ArrayList<Notificacion>();
        for (NotificacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Notificacion> toListLevel1Entity(List<NotificacionDTO> listDto) {
        List<Notificacion> listEntidad = new ArrayList<Notificacion>();
        for (NotificacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
