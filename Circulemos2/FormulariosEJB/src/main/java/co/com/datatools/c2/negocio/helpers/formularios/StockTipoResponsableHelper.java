package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.entidades.StockTipoResponsable;
import co.com.datatools.c2.entidades.TipoFormulario;
import co.com.datatools.c2.entidades.TipoResponsableFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 15:24:18 COT 2015
 */
public class StockTipoResponsableHelper {
    // --- to DTO
    public static StockTipoResponsableDTO toLevel0DTO(StockTipoResponsable entidad) {
        StockTipoResponsableDTO dto = new StockTipoResponsableDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static StockTipoResponsableDTO toLevel1DTO(StockTipoResponsable entidad) {
        StockTipoResponsableDTO dto = toLevel0DTO(entidad);
        dto.setEstadoStock(entidad.getEstadoStock());
        if (entidad.getStockMinimo() != 0)
            dto.setStockMinimo(entidad.getStockMinimo());
        if (entidad.getStockMaximo() != 0)
            dto.setStockMaximo(entidad.getStockMaximo());
        if (entidad.getCodigoOrganismo() != null)
            dto.setCodigoOrganismo(OrganismoTransitoHelper.toLevel0DTO(entidad.getCodigoOrganismo()));
        if (entidad.getTipoResponsable() != null)
            dto.setTipoResponsable(TipoResponsableFormularioHelper.toLevel0DTO(entidad.getTipoResponsable()));
        if (entidad.getTipoFormulario() != null)
            dto.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(entidad.getTipoFormulario()));

        return dto;
    }

    public static List<StockTipoResponsableDTO> toListLevel0DTO(List<StockTipoResponsable> listEntidad) {
        List<StockTipoResponsableDTO> listDto = new ArrayList<StockTipoResponsableDTO>();
        for (StockTipoResponsable entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<StockTipoResponsableDTO> toListLevel1DTO(List<StockTipoResponsable> listEntidad) {
        List<StockTipoResponsableDTO> listDto = new ArrayList<StockTipoResponsableDTO>();
        for (StockTipoResponsable entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static StockTipoResponsable toLevel0Entity(StockTipoResponsableDTO dto, StockTipoResponsable entidad) {
        if (null == entidad) {
            entidad = new StockTipoResponsable();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static StockTipoResponsable toLevel1Entity(StockTipoResponsableDTO dto, StockTipoResponsable entidad) {
        entidad = toLevel0Entity(dto, entidad);
        entidad.setEstadoStock(dto.getEstadoStock());
        if (dto.getStockMinimo() != 0) {
            entidad.setStockMinimo(dto.getStockMinimo());
        }
        if (dto.getStockMaximo() != 0) {
            entidad.setStockMaximo(dto.getStockMaximo());

        }
        if (dto.getCodigoOrganismo() != null) {
            entidad.setCodigoOrganismo(new OrganismoTransito());
            entidad.getCodigoOrganismo().setCodigoOrganismo(dto.getCodigoOrganismo().getCodigoOrganismo());
        }
        if (dto.getTipoResponsable() != null) {
            entidad.setTipoResponsable(new TipoResponsableFormulario());
            entidad.getTipoResponsable().setId(dto.getTipoResponsable().getId());
        }
        if (dto.getTipoFormulario() != null) {
            entidad.setTipoFormulario(new TipoFormulario());
            entidad.getTipoFormulario().setId(dto.getTipoFormulario().getId());
        }

        return entidad;
    }

    public static List<StockTipoResponsable> toListLevel0Entity(List<StockTipoResponsableDTO> listDto) {
        List<StockTipoResponsable> listEntidad = new ArrayList<StockTipoResponsable>();
        for (StockTipoResponsableDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<StockTipoResponsable> toListLevel1Entity(List<StockTipoResponsableDTO> listDto) {
        List<StockTipoResponsable> listEntidad = new ArrayList<StockTipoResponsable>();
        for (StockTipoResponsableDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
