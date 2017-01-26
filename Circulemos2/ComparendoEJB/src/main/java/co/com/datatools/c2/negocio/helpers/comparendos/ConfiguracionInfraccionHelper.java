package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.entidades.CausalInfraccion;
import co.com.datatools.c2.entidades.ConfiguracionInfraccion;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.entidades.Normatividad;
import co.com.datatools.c2.entidades.OrdenamientoPais;
import co.com.datatools.c2.entidades.TipoEntidadAgenteTransito;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:34:35 COT 2015
 */
public class ConfiguracionInfraccionHelper {
    // --- to DTO
    public static ConfiguracionInfraccionDTO toLevel0DTO(ConfiguracionInfraccion entidad) {
        ConfiguracionInfraccionDTO dto = new ConfiguracionInfraccionDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setArticulo(entidad.getArticulo());
        dto.setFechaArticulo(entidad.getFechaArticulo());
        dto.setRequierePlaca(entidad.getRequierePlaca());
        dto.setInfractorObligatorio(entidad.getInfractorObligatorio());
        dto.setDiasGeneraCartera(entidad.getDiasGeneraCartera());
        dto.setGeneraCartera(entidad.getGeneraCartera());
        dto.setDiaHabilGeneraCartera(entidad.getDiaHabilGeneraCartera());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        dto.setAplicaDescuento(entidad.getAplicaDescuento());
        dto.setAplicaEmbriaguez(entidad.getAplicaEmbriaguez());

        return dto;
    }

    public static ConfiguracionInfraccionDTO toLevel1DTO(ConfiguracionInfraccion entidad) {
        ConfiguracionInfraccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInfraccion() != null)
            dto.setInfraccion(InfraccionHelper.toLevel0DTO(entidad.getInfraccion()));
        if (entidad.getNormatividad() != null)
            dto.setNormatividad(NormatividadHelper.toLevel0DTO(entidad.getNormatividad()));
        if (entidad.getEntidadAgenteTransito() != null)
            dto.setEntidadAgenteTransito(TipoEntidadAgenteTransitoHelper.toLevel0DTO(entidad.getEntidadAgenteTransito()));
        if (entidad.getCausalInfraccion() != null)
            dto.setCausalInfraccion(CausalInfraccionHelper.toLevel0DTO(entidad.getCausalInfraccion()));
        if (entidad.getOrdenamientoPais() != null)
            dto.setOrdenamientoPais(OrdenamientoPaisHelper.toLevel0DTO(entidad.getOrdenamientoPais()));

        return dto;
    }

    public static List<ConfiguracionInfraccionDTO> toListLevel0DTO(List<ConfiguracionInfraccion> listEntidad) {
        List<ConfiguracionInfraccionDTO> listDto = new ArrayList<ConfiguracionInfraccionDTO>();
        for (ConfiguracionInfraccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionInfraccionDTO> toListLevel1DTO(List<ConfiguracionInfraccion> listEntidad) {
        List<ConfiguracionInfraccionDTO> listDto = new ArrayList<ConfiguracionInfraccionDTO>();
        for (ConfiguracionInfraccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionInfraccion toLevel0Entity(ConfiguracionInfraccionDTO dto, ConfiguracionInfraccion entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionInfraccion();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setArticulo(dto.getArticulo());
        entidad.setFechaArticulo(dto.getFechaArticulo());
        entidad.setRequierePlaca(dto.getRequierePlaca());
        entidad.setInfractorObligatorio(dto.getInfractorObligatorio());
        entidad.setDiasGeneraCartera(dto.getDiasGeneraCartera());
        entidad.setGeneraCartera(dto.getGeneraCartera());
        entidad.setDiaHabilGeneraCartera(dto.getDiaHabilGeneraCartera());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());
        entidad.setAplicaDescuento(dto.getAplicaDescuento());
        entidad.setAplicaEmbriaguez(dto.getAplicaEmbriaguez());

        return entidad;
    }

    public static ConfiguracionInfraccion toLevel1Entity(ConfiguracionInfraccionDTO dto, ConfiguracionInfraccion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getInfraccion() != null) {
            entidad.setInfraccion(new Infraccion());
            entidad.getInfraccion().setId(dto.getInfraccion().getId());
        }
        if (dto.getNormatividad() != null) {
            entidad.setNormatividad(new Normatividad());
            entidad.getNormatividad().setId(dto.getNormatividad().getId());
        }
        if (dto.getEntidadAgenteTransito() != null) {
            entidad.setEntidadAgenteTransito(new TipoEntidadAgenteTransito());
            entidad.getEntidadAgenteTransito().setId(dto.getEntidadAgenteTransito().getId());
        }
        if (dto.getCausalInfraccion() != null) {
            entidad.setCausalInfraccion(new CausalInfraccion());
            entidad.getCausalInfraccion().setId(dto.getCausalInfraccion().getId());
        }
        if (dto.getOrdenamientoPais() != null) {
            entidad.setOrdenamientoPais(new OrdenamientoPais());
            entidad.getOrdenamientoPais().setId(dto.getOrdenamientoPais().getId());
        }

        return entidad;
    }

    public static List<ConfiguracionInfraccion> toListLevel0Entity(List<ConfiguracionInfraccionDTO> listDto) {
        List<ConfiguracionInfraccion> listEntidad = new ArrayList<ConfiguracionInfraccion>();
        for (ConfiguracionInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionInfraccion> toListLevel1Entity(List<ConfiguracionInfraccionDTO> listDto) {
        List<ConfiguracionInfraccion> listEntidad = new ArrayList<ConfiguracionInfraccion>();
        for (ConfiguracionInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
