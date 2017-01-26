package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TrazaEjecucionSoporteDTO;
import co.com.datatools.c2.entidades.ConfiguracionSoporte;
import co.com.datatools.c2.entidades.TrazaEjecucionSoporte;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 11 17:27:45 COT 2016
 */
public class TrazaEjecucionSoporteHelper {
    // --- to DTO
    public static TrazaEjecucionSoporteDTO toLevel0DTO(TrazaEjecucionSoporte entidad) {
        TrazaEjecucionSoporteDTO dto = new TrazaEjecucionSoporteDTO();
        dto.setId(entidad.getId());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static TrazaEjecucionSoporteDTO toLevel1DTO(TrazaEjecucionSoporte entidad) {
        TrazaEjecucionSoporteDTO dto = toLevel0DTO(entidad);
        if (entidad.getConfigSoporte() != null)
            dto.setConfigSoporte(ConfiguracionSoporteHelper.toLevel0DTO(entidad.getConfigSoporte()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<TrazaEjecucionSoporteDTO> toListLevel0DTO(List<TrazaEjecucionSoporte> listEntidad) {
        List<TrazaEjecucionSoporteDTO> listDto = new ArrayList<TrazaEjecucionSoporteDTO>();
        for (TrazaEjecucionSoporte entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TrazaEjecucionSoporteDTO> toListLevel1DTO(List<TrazaEjecucionSoporte> listEntidad) {
        List<TrazaEjecucionSoporteDTO> listDto = new ArrayList<TrazaEjecucionSoporteDTO>();
        for (TrazaEjecucionSoporte entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TrazaEjecucionSoporte toLevel0Entity(TrazaEjecucionSoporteDTO dto, TrazaEjecucionSoporte entidad) {
        if (null == entidad) {
            entidad = new TrazaEjecucionSoporte();
        }
        entidad.setId(dto.getId());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static TrazaEjecucionSoporte toLevel1Entity(TrazaEjecucionSoporteDTO dto, TrazaEjecucionSoporte entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfigSoporte() != null) {
            entidad.setConfigSoporte(new ConfiguracionSoporte());
            entidad.getConfigSoporte().setId(dto.getConfigSoporte().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }

        return entidad;
    }

    public static List<TrazaEjecucionSoporte> toListLevel0Entity(List<TrazaEjecucionSoporteDTO> listDto) {
        List<TrazaEjecucionSoporte> listEntidad = new ArrayList<TrazaEjecucionSoporte>();
        for (TrazaEjecucionSoporteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TrazaEjecucionSoporte> toListLevel1Entity(List<TrazaEjecucionSoporteDTO> listDto) {
        List<TrazaEjecucionSoporte> listEntidad = new ArrayList<TrazaEjecucionSoporte>();
        for (TrazaEjecucionSoporteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}