package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 08:48:47 COT 2016
 */
public class ConfiguracionFinanciacionHelper {
    // --- to DTO
    public static ConfiguracionFinanciacionDTO toLevel0DTO(ConfiguracionFinanciacion entidad) {
        ConfiguracionFinanciacionDTO dto = new ConfiguracionFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setFechaInicial(entidad.getFechaInicial());
        dto.setFechaFinal(entidad.getFechaFinal());

        return dto;
    }

    public static ConfiguracionFinanciacionDTO toLevel1DTO(ConfiguracionFinanciacion entidad) {
        ConfiguracionFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<ConfiguracionFinanciacionDTO> toListLevel0DTO(List<ConfiguracionFinanciacion> listEntidad) {
        List<ConfiguracionFinanciacionDTO> listDto = new ArrayList<ConfiguracionFinanciacionDTO>();
        for (ConfiguracionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionFinanciacionDTO> toListLevel1DTO(List<ConfiguracionFinanciacion> listEntidad) {
        List<ConfiguracionFinanciacionDTO> listDto = new ArrayList<ConfiguracionFinanciacionDTO>();
        for (ConfiguracionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionFinanciacion toLevel0Entity(ConfiguracionFinanciacionDTO dto,
            ConfiguracionFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setFechaInicial(dto.getFechaInicial());
        entidad.setFechaFinal(dto.getFechaFinal());

        return entidad;
    }

    public static ConfiguracionFinanciacion toLevel1Entity(ConfiguracionFinanciacionDTO dto,
            ConfiguracionFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<ConfiguracionFinanciacion> toListLevel0Entity(List<ConfiguracionFinanciacionDTO> listDto) {
        List<ConfiguracionFinanciacion> listEntidad = new ArrayList<ConfiguracionFinanciacion>();
        for (ConfiguracionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionFinanciacion> toListLevel1Entity(List<ConfiguracionFinanciacionDTO> listDto) {
        List<ConfiguracionFinanciacion> listEntidad = new ArrayList<ConfiguracionFinanciacion>();
        for (ConfiguracionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
