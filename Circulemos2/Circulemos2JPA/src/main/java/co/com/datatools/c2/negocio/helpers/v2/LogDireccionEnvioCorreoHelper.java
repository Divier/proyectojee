package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.LogDireccionEnvioCorreoDTO;
import co.com.datatools.c2.entidades.LogDireccionEnvioCorreo;
import co.com.datatools.c2.entidades.LogEnvioCorreo;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class LogDireccionEnvioCorreoHelper {
    // --- to DTO
    public static LogDireccionEnvioCorreoDTO toLevel0DTO(LogDireccionEnvioCorreo entidad) {
        LogDireccionEnvioCorreoDTO dto = new LogDireccionEnvioCorreoDTO();

        dto.setId(entidad.getId());
        dto.setDireccionDestino(entidad.getDireccionDestino());
        dto.setTipoDestinatario(entidad.getTipoDestinatario());

        return dto;
    }

    public static LogDireccionEnvioCorreoDTO toLevel1DTO(LogDireccionEnvioCorreo entidad) {
        LogDireccionEnvioCorreoDTO dto = toLevel0DTO(entidad);
        if (entidad.getLogEnvioCorreo() != null)
            dto.setLogEnvioCorreo(LogEnvioCorreoHelper.toLevel0DTO(entidad.getLogEnvioCorreo()));

        return dto;
    }

    public static List<LogDireccionEnvioCorreoDTO> toListLevel0DTO(List<LogDireccionEnvioCorreo> listEntidad) {
        List<LogDireccionEnvioCorreoDTO> listDto = new ArrayList<LogDireccionEnvioCorreoDTO>();
        for (LogDireccionEnvioCorreo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LogDireccionEnvioCorreoDTO> toListLevel1DTO(List<LogDireccionEnvioCorreo> listEntidad) {
        List<LogDireccionEnvioCorreoDTO> listDto = new ArrayList<LogDireccionEnvioCorreoDTO>();
        for (LogDireccionEnvioCorreo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LogDireccionEnvioCorreo toLevel0Entity(LogDireccionEnvioCorreoDTO dto,
            LogDireccionEnvioCorreo entidad) {
        if (null == entidad) {
            entidad = new LogDireccionEnvioCorreo();
        }
        entidad.setId(dto.getId());
        entidad.setDireccionDestino(dto.getDireccionDestino());
        entidad.setTipoDestinatario(dto.getTipoDestinatario());

        return entidad;
    }

    public static LogDireccionEnvioCorreo toLevel1Entity(LogDireccionEnvioCorreoDTO dto,
            LogDireccionEnvioCorreo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getLogEnvioCorreo() != null) {
            entidad.setLogEnvioCorreo(new LogEnvioCorreo());
            entidad.getLogEnvioCorreo().setId(dto.getLogEnvioCorreo().getId());
        }

        return entidad;
    }

    public static List<LogDireccionEnvioCorreo> toListLevel0Entity(List<LogDireccionEnvioCorreoDTO> listDto) {
        List<LogDireccionEnvioCorreo> listEntidad = new ArrayList<LogDireccionEnvioCorreo>();
        for (LogDireccionEnvioCorreoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LogDireccionEnvioCorreo> toListLevel1Entity(List<LogDireccionEnvioCorreoDTO> listDto) {
        List<LogDireccionEnvioCorreo> listEntidad = new ArrayList<LogDireccionEnvioCorreo>();
        for (LogDireccionEnvioCorreoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
