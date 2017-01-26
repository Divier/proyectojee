package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.entidades.StockTipoFormulario;
import co.com.datatools.c2.entidades.TipoFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * 
 * @author luis.forero
 * 
 */
public class StockTipoFormularioHelper {

    // --- to DTO
    public static StockTipoFormularioDTO toLevel0DTO(StockTipoFormulario entidad) {
        StockTipoFormularioDTO dto = new StockTipoFormularioDTO();
        dto.setId(entidad.getId());
        dto.setPorcentajeMaximoConsumo(entidad.getPorcentajeMaximoConsumo());

        return dto;
    }

    public static StockTipoFormularioDTO toLevel1DTO(StockTipoFormulario entidad) {
        StockTipoFormularioDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoFormulario() != null) {
            dto.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(entidad.getTipoFormulario()));
        }
        if (entidad.getCodigoOrganismo() != null) {
            dto.setOrganismoTransitoDTO(OrganismoTransitoHelper.toLevel0DTO(entidad.getCodigoOrganismo()));
        }
        return dto;
    }

    public static List<StockTipoFormularioDTO> toListLevel0DTO(List<StockTipoFormulario> listEntidad) {
        List<StockTipoFormularioDTO> listDto = new ArrayList<StockTipoFormularioDTO>();
        for (StockTipoFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<StockTipoFormularioDTO> toListLevel1DTO(List<StockTipoFormulario> listEntidad) {
        List<StockTipoFormularioDTO> listDto = new ArrayList<StockTipoFormularioDTO>();
        for (StockTipoFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static StockTipoFormulario toLevel0Entity(StockTipoFormularioDTO dto, StockTipoFormulario entidad) {
        if (null == entidad) {
            entidad = new StockTipoFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setPorcentajeMaximoConsumo(dto.getPorcentajeMaximoConsumo());

        return entidad;
    }

    public static StockTipoFormulario toLevel1Entity(StockTipoFormularioDTO dto, StockTipoFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoFormulario() != null) {
            entidad.setTipoFormulario(TipoFormularioHelper.toLevel0Entity(dto.getTipoFormulario(), new TipoFormulario()));
        }
        if (dto.getOrganismoTransitoDTO() != null) {
            entidad.setCodigoOrganismo(OrganismoTransitoHelper.toLevel0Entity(dto.getOrganismoTransitoDTO(),
                    new OrganismoTransito()));
        }
        return entidad;
    }

    public static List<StockTipoFormulario> toListLevel0Entity(List<StockTipoFormularioDTO> listDto) {
        List<StockTipoFormulario> listEntidad = new ArrayList<StockTipoFormulario>();
        for (StockTipoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<StockTipoFormulario> toListLevel1Entity(List<StockTipoFormularioDTO> listDto) {
        List<StockTipoFormulario> listEntidad = new ArrayList<StockTipoFormulario>();
        for (StockTipoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad

}
