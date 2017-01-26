package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConfiguracionDescuentoDTO;
import co.com.datatools.c2.entidades.ConfiguracionDescuento;
import co.com.datatools.c2.entidades.TipoDescuento;
import co.com.datatools.c2.entidades.TipoFechaOrigen;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 27 10:10:00 COT 2016
 */
public class ConfiguracionDescuentoHelper {
    // --- to DTO
    public static ConfiguracionDescuentoDTO toLevel0DTO(ConfiguracionDescuento entidad) {
        ConfiguracionDescuentoDTO dto = new ConfiguracionDescuentoDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFechaVigenciaFin(entidad.getFechaVigenciaFin());
        dto.setFechaVigenciaInicio(entidad.getFechaVigenciaInicio());
        dto.setLimite(entidad.getLimite());
        dto.setResolucionEspecial(entidad.getResolucionEspecial());
        dto.setCantidadDiasCalendario(entidad.getCantidadDiasCalendario());
        dto.setCantidadDiasHabiles(entidad.getCantidadDiasHabiles());
        dto.setDiasDesdeFechaImposicion(entidad.getDiasDesdeFechaImposicion());
        dto.setInteresComparendo(entidad.getInteresComparendo());

        return dto;
    }

    public static ConfiguracionDescuentoDTO toLevel1DTO(ConfiguracionDescuento entidad) {
        ConfiguracionDescuentoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getTipoDescuento() != null)
            dto.setTipoDescuento(TipoDescuentoHelper.toLevel0DTO(entidad.getTipoDescuento()));
        if (entidad.getTipoFechaOrigen() != null)
            dto.setTipoFechaOrigen(TipoFechaOrigenHelper.toLevel0DTO(entidad.getTipoFechaOrigen()));

        return dto;
    }

    public static List<ConfiguracionDescuentoDTO> toListLevel0DTO(List<ConfiguracionDescuento> listEntidad) {
        List<ConfiguracionDescuentoDTO> listDto = new ArrayList<ConfiguracionDescuentoDTO>();
        for (ConfiguracionDescuento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionDescuentoDTO> toListLevel1DTO(List<ConfiguracionDescuento> listEntidad) {
        List<ConfiguracionDescuentoDTO> listDto = new ArrayList<ConfiguracionDescuentoDTO>();
        for (ConfiguracionDescuento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionDescuento toLevel0Entity(ConfiguracionDescuentoDTO dto, ConfiguracionDescuento entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionDescuento();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setFechaVigenciaFin(dto.getFechaVigenciaFin());
        entidad.setFechaVigenciaInicio(dto.getFechaVigenciaInicio());
        entidad.setLimite(dto.getLimite());
        entidad.setResolucionEspecial(dto.getResolucionEspecial());
        entidad.setCantidadDiasCalendario(dto.getCantidadDiasCalendario());
        entidad.setCantidadDiasHabiles(dto.getCantidadDiasHabiles());
        entidad.setDiasDesdeFechaImposicion(dto.getDiasDesdeFechaImposicion());
        entidad.setInteresComparendo(dto.getInteresComparendo());

        return entidad;
    }

    public static ConfiguracionDescuento toLevel1Entity(ConfiguracionDescuentoDTO dto, ConfiguracionDescuento entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getTipoDescuento() != null) {
            entidad.setTipoDescuento(new TipoDescuento());
            entidad.getTipoDescuento().setId(dto.getTipoDescuento().getId());
        }
        if (dto.getTipoFechaOrigen() != null) {
            entidad.setTipoFechaOrigen(new TipoFechaOrigen());
            entidad.getTipoFechaOrigen().setId(dto.getTipoFechaOrigen().getId());
        }

        return entidad;
    }

    public static List<ConfiguracionDescuento> toListLevel0Entity(List<ConfiguracionDescuentoDTO> listDto) {
        List<ConfiguracionDescuento> listEntidad = new ArrayList<ConfiguracionDescuento>();
        for (ConfiguracionDescuentoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionDescuento> toListLevel1Entity(List<ConfiguracionDescuentoDTO> listDto) {
        List<ConfiguracionDescuento> listEntidad = new ArrayList<ConfiguracionDescuento>();
        for (ConfiguracionDescuentoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
