package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EstadoCondicionCoactivoDTO;
import co.com.datatools.c2.entidades.CondicionCoactivo;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.EstadoCondicionCoactivo;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:27:14 COT 2016
 */
public class EstadoCondicionCoactivoHelper {
    // --- to DTO
    public static EstadoCondicionCoactivoDTO toLevel0DTO(EstadoCondicionCoactivo entidad) {
        EstadoCondicionCoactivoDTO dto = new EstadoCondicionCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static EstadoCondicionCoactivoDTO toLevel1DTO(EstadoCondicionCoactivo entidad) {
        EstadoCondicionCoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getCondicionCoactivo() != null)
            dto.setCondicionCoactivo(CondicionCoactivoHelper.toLevel0DTO(entidad.getCondicionCoactivo()));
        if (entidad.getConfiguracionCoactivo() != null)
            dto.setConfiguracionCoactivo(ConfiguracionCoactivoHelper.toLevel0DTO(entidad.getConfiguracionCoactivo()));

        return dto;
    }

    public static List<EstadoCondicionCoactivoDTO> toListLevel0DTO(List<EstadoCondicionCoactivo> listEntidad) {
        List<EstadoCondicionCoactivoDTO> listDto = new ArrayList<EstadoCondicionCoactivoDTO>();
        for (EstadoCondicionCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoCondicionCoactivoDTO> toListLevel1DTO(List<EstadoCondicionCoactivo> listEntidad) {
        List<EstadoCondicionCoactivoDTO> listDto = new ArrayList<EstadoCondicionCoactivoDTO>();
        for (EstadoCondicionCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoCondicionCoactivo toLevel0Entity(EstadoCondicionCoactivoDTO dto,
            EstadoCondicionCoactivo entidad) {
        if (null == entidad) {
            entidad = new EstadoCondicionCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static EstadoCondicionCoactivo toLevel1Entity(EstadoCondicionCoactivoDTO dto,
            EstadoCondicionCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCondicionCoactivo() != null) {
            entidad.setCondicionCoactivo(new CondicionCoactivo());
            entidad.getCondicionCoactivo().setCodigo(dto.getCondicionCoactivo().getCodigo());
        }
        if (dto.getConfiguracionCoactivo() != null) {
            entidad.setConfiguracionCoactivo(new ConfiguracionCoactivo());
            entidad.getConfiguracionCoactivo().setId(dto.getConfiguracionCoactivo().getId());
        }

        return entidad;
    }

    public static List<EstadoCondicionCoactivo> toListLevel0Entity(List<EstadoCondicionCoactivoDTO> listDto) {
        List<EstadoCondicionCoactivo> listEntidad = new ArrayList<EstadoCondicionCoactivo>();
        for (EstadoCondicionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoCondicionCoactivo> toListLevel1Entity(List<EstadoCondicionCoactivoDTO> listDto) {
        List<EstadoCondicionCoactivo> listEntidad = new ArrayList<EstadoCondicionCoactivo>();
        for (EstadoCondicionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
