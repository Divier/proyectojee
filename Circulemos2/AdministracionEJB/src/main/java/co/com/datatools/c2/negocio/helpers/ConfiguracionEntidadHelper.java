package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.entidades.ConfiguracionEntidad;
import co.com.datatools.c2.entidades.TipoEntidad;
import co.com.datatools.c2.entidades.comun.EntidadOficio;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.negocio.helpers.comun.EntidadOficioHelper;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:25:51 COT 2016
 */
public class ConfiguracionEntidadHelper {
    // --- to DTO
    public static ConfiguracionEntidadDTO toLevel0DTO(ConfiguracionEntidad entidad) {
        ConfiguracionEntidadDTO dto = new ConfiguracionEntidadDTO();
        dto.setId(entidad.getId());
        dto.setContacto(entidad.getContacto());
        dto.setDireccion(entidad.getDireccion());
        dto.setSiglaOficio(entidad.getSiglaOficio());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static ConfiguracionEntidadDTO toLevel1DTO(ConfiguracionEntidad entidad) {
        ConfiguracionEntidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getMunicipio() != null)
            dto.setMunicipioDTO(MunicipioHelper.toLevel0DTO(entidad.getMunicipio()));
        if (entidad.getTipoEntidad() != null)
            dto.setTipoEntidadDTO(TipoEntidadHelper.toLevel0DTO(entidad.getTipoEntidad()));
        if (entidad.getEntidadOficio() != null)
            dto.setEntidadOficioDTO(EntidadOficioHelper.toLevel0DTO(entidad.getEntidadOficio()));
        return dto;
    }

    public static List<ConfiguracionEntidadDTO> toListLevel0DTO(List<ConfiguracionEntidad> listEntidad) {
        List<ConfiguracionEntidadDTO> listDto = new ArrayList<ConfiguracionEntidadDTO>();
        for (ConfiguracionEntidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionEntidadDTO> toListLevel1DTO(List<ConfiguracionEntidad> listEntidad) {
        List<ConfiguracionEntidadDTO> listDto = new ArrayList<ConfiguracionEntidadDTO>();
        for (ConfiguracionEntidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionEntidad toLevel0Entity(ConfiguracionEntidadDTO dto, ConfiguracionEntidad entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionEntidad();
        }
        entidad.setId(dto.getId());
        entidad.setContacto(dto.getContacto());
        entidad.setDireccion(dto.getDireccion());
        entidad.setSiglaOficio(dto.getSiglaOficio());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static ConfiguracionEntidad toLevel1Entity(ConfiguracionEntidadDTO dto, ConfiguracionEntidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getMunicipioDTO() != null) {
            entidad.setMunicipio(new Municipio());
            entidad.getMunicipio().setId(dto.getMunicipioDTO().getId());
        }
        if (dto.getTipoEntidadDTO() != null) {
            entidad.setTipoEntidad(new TipoEntidad());
            entidad.getTipoEntidad().setId(dto.getTipoEntidadDTO().getId());
        }
        if (dto.getEntidadOficioDTO() != null) {
            entidad.setEntidadOficio(new EntidadOficio());
            entidad.getEntidadOficio().setId(dto.getEntidadOficioDTO().getId());
        }
        return entidad;
    }

    public static List<ConfiguracionEntidad> toListLevel0Entity(List<ConfiguracionEntidadDTO> listDto) {
        List<ConfiguracionEntidad> listEntidad = new ArrayList<ConfiguracionEntidad>();
        for (ConfiguracionEntidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionEntidad> toListLevel1Entity(List<ConfiguracionEntidadDTO> listDto) {
        List<ConfiguracionEntidad> listEntidad = new ArrayList<ConfiguracionEntidad>();
        for (ConfiguracionEntidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
