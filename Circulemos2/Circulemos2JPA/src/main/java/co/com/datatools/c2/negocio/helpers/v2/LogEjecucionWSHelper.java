package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.entidades.LogEjecucionWS;
import co.com.datatools.c2.entidades.RespuestaWebService;

/**
 * @author Generated
 * @version 3.0 - Fri Aug 12 17:50:51 COT 2016
 */
public class LogEjecucionWSHelper {
    // --- to DTO
    public static LogEjecucionWSDTO toLevel0DTO(LogEjecucionWS entidad) {
        LogEjecucionWSDTO dto = new LogEjecucionWSDTO();
        dto.setId(entidad.getId());
        dto.setMensajeEnviado(entidad.getMensajeEnviado());
        dto.setFechaEnvio(entidad.getFechaEnvio());
        dto.setRutaEnvio(entidad.getRutaEnvio());
        dto.setMensajeRecibido(entidad.getMensajeRecibido());
        dto.setFechaRecepcion(entidad.getFechaRecepcion());
        dto.setObservacion(entidad.getObservacion());

        return dto;
    }

    public static LogEjecucionWSDTO toLevel1DTO(LogEjecucionWS entidad) {
        LogEjecucionWSDTO dto = toLevel0DTO(entidad);
        if (entidad.getRespuestaWebService() != null)
            dto.setRespuestaWebService(RespuestaWebServiceHelper.toLevel0DTO(entidad.getRespuestaWebService()));

        return dto;
    }

    public static List<LogEjecucionWSDTO> toListLevel0DTO(List<LogEjecucionWS> listEntidad) {
        List<LogEjecucionWSDTO> listDto = new ArrayList<LogEjecucionWSDTO>();
        for (LogEjecucionWS entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LogEjecucionWSDTO> toListLevel1DTO(List<LogEjecucionWS> listEntidad) {
        List<LogEjecucionWSDTO> listDto = new ArrayList<LogEjecucionWSDTO>();
        for (LogEjecucionWS entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LogEjecucionWS toLevel0Entity(LogEjecucionWSDTO dto, LogEjecucionWS entidad) {
        if (null == entidad) {
            entidad = new LogEjecucionWS();
        }
        entidad.setId(dto.getId());
        entidad.setMensajeEnviado(dto.getMensajeEnviado());
        entidad.setFechaEnvio(dto.getFechaEnvio());
        entidad.setRutaEnvio(dto.getRutaEnvio());
        entidad.setMensajeRecibido(dto.getMensajeRecibido());
        entidad.setFechaRecepcion(dto.getFechaRecepcion());
        entidad.setObservacion(dto.getObservacion());

        return entidad;
    }

    public static LogEjecucionWS toLevel1Entity(LogEjecucionWSDTO dto, LogEjecucionWS entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getRespuestaWebService() != null) {
            entidad.setRespuestaWebService(new RespuestaWebService());
            entidad.getRespuestaWebService().setId(dto.getRespuestaWebService().getId());
        }

        return entidad;
    }

    public static List<LogEjecucionWS> toListLevel0Entity(List<LogEjecucionWSDTO> listDto) {
        List<LogEjecucionWS> listEntidad = new ArrayList<LogEjecucionWS>();
        for (LogEjecucionWSDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LogEjecucionWS> toListLevel1Entity(List<LogEjecucionWSDTO> listDto) {
        List<LogEjecucionWS> listEntidad = new ArrayList<LogEjecucionWS>();
        for (LogEjecucionWSDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
