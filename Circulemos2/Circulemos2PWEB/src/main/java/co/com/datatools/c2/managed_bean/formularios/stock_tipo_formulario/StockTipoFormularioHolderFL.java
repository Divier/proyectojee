/**
 * 
 */
package co.com.datatools.c2.managed_bean.formularios.stock_tipo_formulario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;

/**
 * Objeto de flujo que permite manejar el flujo de datos desde la interfaz. Utilizado en <b>CU_CIR20_DAT_NUM_013 'Administrar Stock por Tipo de
 * Formulario y Organismo de Transito'</b>
 * 
 * @author luis.forero
 * 
 */
public class StockTipoFormularioHolderFL implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer fIdTipoFormulario;

    private List<StockTipoFormularioDTO> lstStockTipoFormularioDTOs;

    private StockTipoFormularioDTO stockTipoFormSeleccionado;

    private StockTipoFormularioDTO stockTipoFormularioDTO;

    public StockTipoFormularioHolderFL() {
        lstStockTipoFormularioDTOs = new ArrayList<>(1);
    }

    public void initStockTipoFormularioDTO() {
        stockTipoFormularioDTO = new StockTipoFormularioDTO();
        stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO());
        stockTipoFormularioDTO.setOrganismoTransitoDTO(new OrganismoTransitoDTO());
    }

    public Integer getfIdTipoFormulario() {
        return fIdTipoFormulario;
    }

    public void setfIdTipoFormulario(Integer fIdTipoFormulario) {
        this.fIdTipoFormulario = fIdTipoFormulario;
    }

    public List<StockTipoFormularioDTO> getLstStockTipoFormularioDTOs() {
        return lstStockTipoFormularioDTOs;
    }

    public void setLstStockTipoFormularioDTOs(List<StockTipoFormularioDTO> lstStockTipoFormularioDTOs) {
        this.lstStockTipoFormularioDTOs = lstStockTipoFormularioDTOs;
    }

    public StockTipoFormularioDTO getStockTipoFormSeleccionado() {
        return stockTipoFormSeleccionado;
    }

    public void setStockTipoFormSeleccionado(StockTipoFormularioDTO stockTipoFormSeleccionado) {
        this.stockTipoFormSeleccionado = stockTipoFormSeleccionado;
    }

    public StockTipoFormularioDTO getStockTipoFormularioDTO() {
        return stockTipoFormularioDTO;
    }

    public void setStockTipoFormularioDTO(StockTipoFormularioDTO stockTipoFormularioDTO) {
        this.stockTipoFormularioDTO = stockTipoFormularioDTO;
    }

}
