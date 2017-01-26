package co.com.datatools.c2.managed_bean.formularios.stock_tipo_responsable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;

/**
 * Estructura utilizada para manejo de los datos de la funcionalidad de <b>CU_CIR20_DAT_NUM_017
 * "Administrar Stock por tipo formulario y tipo de asignacion"</b>
 * 
 * @author luis.forero giovanni.velandia(mod 2015-08-19)
 * 
 */
public class StockTipoResponsableHolderFL implements Serializable {

    // Filtros
    private StockTipoResponsableDTO stockTipoResponsableDTO;
    // Control de datos consultados

    private List<StockTipoResponsableDTO> stockTipoResponsableDTOs;
    private StockTipoResponsableDTO stockTipoResponsableDTOSeleccionado;
    // Atributo de control de flujo tanto para registro como para actualizacion de un stock
    private StockTipoResponsableDTO stockTipoResponsableCrearDTO;

    private static final long serialVersionUID = 1L;

    public StockTipoResponsableHolderFL() {
        stockTipoResponsableDTO = new StockTipoResponsableDTO();
        stockTipoResponsableDTO.setTipoFormulario(new TipoFormularioDTO());
        stockTipoResponsableDTO.setTipoResponsable(new TipoResponsableFormularioDTO());
        stockTipoResponsableDTOs = new ArrayList<StockTipoResponsableDTO>();
    }

    public void initStockTipoResponsableDTO() {
        stockTipoResponsableCrearDTO = new StockTipoResponsableDTO();
        stockTipoResponsableCrearDTO.setTipoFormulario(new TipoFormularioDTO());
        stockTipoResponsableCrearDTO.setTipoResponsable(new TipoResponsableFormularioDTO());
        stockTipoResponsableCrearDTO.setCodigoOrganismo(new OrganismoTransitoDTO());
    }

    public List<StockTipoResponsableDTO> getStockTipoResponsableDTOs() {
        return stockTipoResponsableDTOs;
    }

    public void setStockTipoResponsableDTOs(List<StockTipoResponsableDTO> stockTipoResponsableDTOs) {
        this.stockTipoResponsableDTOs = stockTipoResponsableDTOs;
    }

    public StockTipoResponsableDTO getStockTipoResponsableDTO() {
        return stockTipoResponsableDTO;
    }

    public void setStockTipoResponsableDTO(StockTipoResponsableDTO stockTipoResponsableDTO) {
        this.stockTipoResponsableDTO = stockTipoResponsableDTO;
    }

    public StockTipoResponsableDTO getStockTipoResponsableDTOSeleccionado() {
        return stockTipoResponsableDTOSeleccionado;
    }

    public void setStockTipoResponsableDTOSeleccionado(StockTipoResponsableDTO stockTipoResponsableDTOSeleccionado) {
        this.stockTipoResponsableDTOSeleccionado = stockTipoResponsableDTOSeleccionado;
    }

    public StockTipoResponsableDTO getStockTipoResponsableCrearDTO() {
        return stockTipoResponsableCrearDTO;
    }

    public void setStockTipoResponsableCrearDTO(StockTipoResponsableDTO stockTipoResponsableCrearDTO) {
        this.stockTipoResponsableCrearDTO = stockTipoResponsableCrearDTO;
    }

}
