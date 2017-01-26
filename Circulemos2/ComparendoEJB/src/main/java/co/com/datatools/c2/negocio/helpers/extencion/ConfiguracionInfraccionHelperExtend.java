package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.entidades.ConfiguracionInfraccion;
import co.com.datatools.c2.negocio.helpers.comparendos.CausalInfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ConfiguracionInfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.InfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.NormatividadHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.OrdenamientoPaisHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoEntidadAgenteTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoInfractorHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoSancionHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoResponsableSolidarioHelper;

/**
 * @author luis.forero
 * 
 */
public class ConfiguracionInfraccionHelperExtend extends ConfiguracionInfraccionHelper {

    public static ConfiguracionInfraccionDTO toLevel2DTO(ConfiguracionInfraccion entidad) {
        ConfiguracionInfraccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInfraccion() != null)
            dto.setInfraccion(InfraccionHelper.toLevel1DTO(entidad.getInfraccion()));
        if (entidad.getNormatividad() != null)
            dto.setNormatividad(NormatividadHelper.toLevel1DTO(entidad.getNormatividad()));
        if (entidad.getEntidadAgenteTransito() != null)
            dto.setEntidadAgenteTransito(TipoEntidadAgenteTransitoHelper.toLevel1DTO(entidad.getEntidadAgenteTransito()));
        if (entidad.getCausalInfraccion() != null)
            dto.setCausalInfraccion(CausalInfraccionHelper.toLevel0DTO(entidad.getCausalInfraccion()));
        if (entidad.getOrdenamientoPais() != null)
            dto.setOrdenamientoPais(OrdenamientoPaisHelper.toLevel1DTO(entidad.getOrdenamientoPais()));

        dto.setTipoInfractorList(TipoInfractorHelper.toListLevel0DTO(entidad.getTipoInfractorList()));
        dto.setTipoResponsableSolidarioList(TipoResponsableSolidarioHelper.toListLevel1DTO(entidad
                .getTipoResponsableSolidarioList()));
        dto.setTipoSancionList(TipoSancionHelper.toListLevel0DTO(entidad.getTipoSancionList()));

        return dto;
    }

    public static List<ConfiguracionInfraccionDTO> toListLevel2DTO(List<ConfiguracionInfraccion> listEntidad) {
        List<ConfiguracionInfraccionDTO> listDto = new ArrayList<ConfiguracionInfraccionDTO>();
        for (ConfiguracionInfraccion entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }

    public static ConfiguracionInfraccionDTO toLevel1DTOExtend(ConfiguracionInfraccion entidad) {
        ConfiguracionInfraccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInfraccion() != null) {
            dto.setInfraccion(InfraccionHelperExtend.toLevel1DTOExtend(entidad.getInfraccion()));
        }
        if (entidad.getNormatividad() != null) {
            dto.setNormatividad(NormatividadHelper.toLevel1DTO(entidad.getNormatividad()));
        }
        if (entidad.getEntidadAgenteTransito() != null) {
            dto.setEntidadAgenteTransito(TipoEntidadAgenteTransitoHelper.toLevel1DTO(entidad.getEntidadAgenteTransito()));
        }
        if (entidad.getCausalInfraccion() != null) {
            dto.setCausalInfraccion(CausalInfraccionHelper.toLevel0DTO(entidad.getCausalInfraccion()));
        }
        if (entidad.getOrdenamientoPais() != null) {
            dto.setOrdenamientoPais(OrdenamientoPaisHelper.toLevel1DTO(entidad.getOrdenamientoPais()));
        }

        dto.setTipoInfractorList(TipoInfractorHelper.toListLevel0DTO(entidad.getTipoInfractorList()));
        dto.setTipoResponsableSolidarioList(TipoResponsableSolidarioHelper.toListLevel1DTO(entidad
                .getTipoResponsableSolidarioList()));
        dto.setTipoSancionList(TipoSancionHelper.toListLevel0DTO(entidad.getTipoSancionList()));

        return dto;
    }
}
