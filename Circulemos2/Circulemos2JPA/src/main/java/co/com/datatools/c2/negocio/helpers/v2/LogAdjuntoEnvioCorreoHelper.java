package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.LogAdjuntoEnvioCorreoDTO;
import co.com.datatools.c2.entidades.LogAdjuntoEnvioCorreo;
import co.com.datatools.c2.entidades.LogEnvioCorreo;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class LogAdjuntoEnvioCorreoHelper {
    // --- to DTO
    public static LogAdjuntoEnvioCorreoDTO toLevel0DTO(LogAdjuntoEnvioCorreo entidad) {
        LogAdjuntoEnvioCorreoDTO dto = new LogAdjuntoEnvioCorreoDTO();

        dto.setId(entidad.getId());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());

        return dto;
    }

    public static LogAdjuntoEnvioCorreoDTO toLevel1DTO(LogAdjuntoEnvioCorreo entidad) {
        LogAdjuntoEnvioCorreoDTO dto = toLevel0DTO(entidad);
        if (entidad.getLogEnvioCorreo() != null)
            dto.setLogEnvioCorreo(LogEnvioCorreoHelper.toLevel0DTO(entidad.getLogEnvioCorreo()));

        if (entidad.getArchivo() != null)
            dto.setArchivo(entidad.getArchivo());

        return dto;
    }

    public static List<LogAdjuntoEnvioCorreoDTO> toListLevel0DTO(List<LogAdjuntoEnvioCorreo> listEntidad) {
        List<LogAdjuntoEnvioCorreoDTO> listDto = new ArrayList<LogAdjuntoEnvioCorreoDTO>();
        for (LogAdjuntoEnvioCorreo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LogAdjuntoEnvioCorreoDTO> toListLevel1DTO(List<LogAdjuntoEnvioCorreo> listEntidad) {
        List<LogAdjuntoEnvioCorreoDTO> listDto = new ArrayList<LogAdjuntoEnvioCorreoDTO>();
        for (LogAdjuntoEnvioCorreo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LogAdjuntoEnvioCorreo toLevel0Entity(LogAdjuntoEnvioCorreoDTO dto, LogAdjuntoEnvioCorreo entidad) {
        if (null == entidad) {
            entidad = new LogAdjuntoEnvioCorreo();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());

        return entidad;
    }

    public static LogAdjuntoEnvioCorreo toLevel1Entity(LogAdjuntoEnvioCorreoDTO dto, LogAdjuntoEnvioCorreo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getLogEnvioCorreo() != null) {
            entidad.setLogEnvioCorreo(new LogEnvioCorreo());
            entidad.getLogEnvioCorreo().setId(dto.getLogEnvioCorreo().getId());
        }
        if (dto.getArchivo() != null)
            entidad.setArchivo(dto.getArchivo());

        return entidad;
    }

    public static List<LogAdjuntoEnvioCorreo> toListLevel0Entity(List<LogAdjuntoEnvioCorreoDTO> listDto) {
        List<LogAdjuntoEnvioCorreo> listEntidad = new ArrayList<LogAdjuntoEnvioCorreo>();
        for (LogAdjuntoEnvioCorreoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LogAdjuntoEnvioCorreo> toListLevel1Entity(List<LogAdjuntoEnvioCorreoDTO> listDto) {
        List<LogAdjuntoEnvioCorreo> listEntidad = new ArrayList<LogAdjuntoEnvioCorreo>();
        for (LogAdjuntoEnvioCorreoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
