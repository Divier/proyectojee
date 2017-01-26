package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.entidades.DetalleNotificacion;
import co.com.datatools.c2.entidades.LogEjecucionWS;
import co.com.datatools.c2.entidades.LogEnvioCorreo;
import co.com.datatools.c2.entidades.Notificacion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEjecucionWSHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 14:25:05 COT 2016
 */
public class DetalleNotificacionHelper {
    // --- to DTO
    public static DetalleNotificacionDTO toLevel0DTO(DetalleNotificacion entidad) {
        DetalleNotificacionDTO dto = new DetalleNotificacionDTO();
        dto.setId(entidad.getId());
        dto.setCodigoSeguimientoExt(entidad.getCodigoSeguimientoExt());
        dto.setCodigoSeguimientoInt(entidad.getCodigoSeguimientoInt());
        dto.setEnviadoExt(entidad.getEnviadoExt());
        dto.setEstadoExt(entidad.getEstadoExt());
        dto.setFechaEnvioTercero(entidad.getFechaEnvioTercero());
        dto.setFechaModificacion(entidad.getFechaModificacion());
        dto.setFechaNotificacion(entidad.getFechaNotificacion());

        return dto;
    }

    public static DetalleNotificacionDTO toLevel1DTO(DetalleNotificacion entidad) {
        DetalleNotificacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getLogEnvioCorreo() != null)
            dto.setLogEnvioCorreo(LogEnvioCorreoHelper.toLevel0DTO(entidad.getLogEnvioCorreo()));
        if (entidad.getLogEjecucionWS() != null)
            dto.setLogEjecucionWS(LogEjecucionWSHelper.toLevel0DTO(entidad.getLogEjecucionWS()));
        if (entidad.getNotificacion() != null)
            dto.setNotificacion(NotificacionHelper.toLevel0DTO(entidad.getNotificacion()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<DetalleNotificacionDTO> toListLevel0DTO(List<DetalleNotificacion> listEntidad) {
        List<DetalleNotificacionDTO> listDto = new ArrayList<DetalleNotificacionDTO>();
        for (DetalleNotificacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleNotificacionDTO> toListLevel1DTO(List<DetalleNotificacion> listEntidad) {
        List<DetalleNotificacionDTO> listDto = new ArrayList<DetalleNotificacionDTO>();
        for (DetalleNotificacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleNotificacion toLevel0Entity(DetalleNotificacionDTO dto, DetalleNotificacion entidad) {
        if (null == entidad) {
            entidad = new DetalleNotificacion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoSeguimientoExt(dto.getCodigoSeguimientoExt());
        entidad.setCodigoSeguimientoInt(dto.getCodigoSeguimientoInt());
        entidad.setEnviadoExt(dto.getEnviadoExt());
        entidad.setEstadoExt(dto.getEstadoExt());
        entidad.setFechaEnvioTercero(dto.getFechaEnvioTercero());
        entidad.setFechaModificacion(dto.getFechaModificacion());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());

        return entidad;
    }

    public static DetalleNotificacion toLevel1Entity(DetalleNotificacionDTO dto, DetalleNotificacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getLogEnvioCorreo() != null) {
            entidad.setLogEnvioCorreo(new LogEnvioCorreo());
            entidad.getLogEnvioCorreo().setId(dto.getLogEnvioCorreo().getId());
        }
        if (dto.getLogEjecucionWS() != null) {
            entidad.setLogEjecucionWS(new LogEjecucionWS());
            entidad.getLogEjecucionWS().setId(dto.getLogEjecucionWS().getId());
        }
        if (dto.getNotificacion() != null) {
            entidad.setNotificacion(new Notificacion());
            entidad.getNotificacion().setId(dto.getNotificacion().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            // entidad.getUsuario().setIdUsuario(dto.getUsuario().getIdUsuario());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        return entidad;
    }

    public static List<DetalleNotificacion> toListLevel0Entity(List<DetalleNotificacionDTO> listDto) {
        List<DetalleNotificacion> listEntidad = new ArrayList<DetalleNotificacion>();
        for (DetalleNotificacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleNotificacion> toListLevel1Entity(List<DetalleNotificacionDTO> listDto) {
        List<DetalleNotificacion> listEntidad = new ArrayList<DetalleNotificacion>();
        for (DetalleNotificacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
