package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EstadoCondicionFinanciacionDTO;
import co.com.datatools.c2.entidades.CondicionFinanciacion;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.EstadoCondicionFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:07:26 COT 2016
 */
public class EstadoCondicionFinanciacionHelper {
    // --- to DTO
    public static EstadoCondicionFinanciacionDTO toLevel0DTO(EstadoCondicionFinanciacion entidad) {
        EstadoCondicionFinanciacionDTO dto = new EstadoCondicionFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static EstadoCondicionFinanciacionDTO toLevel1DTO(EstadoCondicionFinanciacion entidad) {
        EstadoCondicionFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getCondicionFinanciacion() != null)
            dto.setCondicionFinanciacion(CondicionFinanciacionHelper.toLevel0DTO(entidad.getCondicionFinanciacion()));
        if (entidad.getConfiguracionFinanciacion() != null)
            dto.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper.toLevel0DTO(entidad
                    .getConfiguracionFinanciacion()));

        return dto;
    }

    public static List<EstadoCondicionFinanciacionDTO> toListLevel0DTO(List<EstadoCondicionFinanciacion> listEntidad) {
        List<EstadoCondicionFinanciacionDTO> listDto = new ArrayList<EstadoCondicionFinanciacionDTO>();
        for (EstadoCondicionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoCondicionFinanciacionDTO> toListLevel1DTO(List<EstadoCondicionFinanciacion> listEntidad) {
        List<EstadoCondicionFinanciacionDTO> listDto = new ArrayList<EstadoCondicionFinanciacionDTO>();
        for (EstadoCondicionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoCondicionFinanciacion toLevel0Entity(EstadoCondicionFinanciacionDTO dto,
            EstadoCondicionFinanciacion entidad) {
        if (null == entidad) {
            entidad = new EstadoCondicionFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static EstadoCondicionFinanciacion toLevel1Entity(EstadoCondicionFinanciacionDTO dto,
            EstadoCondicionFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCondicionFinanciacion() != null) {
            entidad.setCondicionFinanciacion(new CondicionFinanciacion());
            entidad.getCondicionFinanciacion().setCodigo(dto.getCondicionFinanciacion().getCodigo());
        }
        if (dto.getConfiguracionFinanciacion() != null) {
            entidad.setConfiguracionFinanciacion(new ConfiguracionFinanciacion());
            entidad.getConfiguracionFinanciacion().setId(dto.getConfiguracionFinanciacion().getId());
        }

        return entidad;
    }

    public static List<EstadoCondicionFinanciacion> toListLevel0Entity(List<EstadoCondicionFinanciacionDTO> listDto) {
        List<EstadoCondicionFinanciacion> listEntidad = new ArrayList<EstadoCondicionFinanciacion>();
        for (EstadoCondicionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoCondicionFinanciacion> toListLevel1Entity(List<EstadoCondicionFinanciacionDTO> listDto) {
        List<EstadoCondicionFinanciacion> listEntidad = new ArrayList<EstadoCondicionFinanciacion>();
        for (EstadoCondicionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
