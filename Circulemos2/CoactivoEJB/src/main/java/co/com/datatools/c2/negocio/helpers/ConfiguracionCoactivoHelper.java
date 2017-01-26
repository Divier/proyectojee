package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:25:51 COT 2016
 */
public class ConfiguracionCoactivoHelper {
    // --- to DTO
    public static ConfiguracionCoactivoDTO toLevel0DTO(ConfiguracionCoactivo entidad) {
        ConfiguracionCoactivoDTO dto = new ConfiguracionCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setFechaInicial(entidad.getFechaInicial());
        dto.setFechaFinal(entidad.getFechaFinal());

        return dto;
    }

    public static ConfiguracionCoactivoDTO toLevel1DTO(ConfiguracionCoactivo entidad) {
        ConfiguracionCoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<ConfiguracionCoactivoDTO> toListLevel0DTO(List<ConfiguracionCoactivo> listEntidad) {
        List<ConfiguracionCoactivoDTO> listDto = new ArrayList<ConfiguracionCoactivoDTO>();
        for (ConfiguracionCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionCoactivoDTO> toListLevel1DTO(List<ConfiguracionCoactivo> listEntidad) {
        List<ConfiguracionCoactivoDTO> listDto = new ArrayList<ConfiguracionCoactivoDTO>();
        for (ConfiguracionCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionCoactivo toLevel0Entity(ConfiguracionCoactivoDTO dto, ConfiguracionCoactivo entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setFechaInicial(dto.getFechaInicial());
        entidad.setFechaFinal(dto.getFechaFinal());

        return entidad;
    }

    public static ConfiguracionCoactivo toLevel1Entity(ConfiguracionCoactivoDTO dto, ConfiguracionCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<ConfiguracionCoactivo> toListLevel0Entity(List<ConfiguracionCoactivoDTO> listDto) {
        List<ConfiguracionCoactivo> listEntidad = new ArrayList<ConfiguracionCoactivo>();
        for (ConfiguracionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionCoactivo> toListLevel1Entity(List<ConfiguracionCoactivoDTO> listDto) {
        List<ConfiguracionCoactivo> listEntidad = new ArrayList<ConfiguracionCoactivo>();
        for (ConfiguracionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
