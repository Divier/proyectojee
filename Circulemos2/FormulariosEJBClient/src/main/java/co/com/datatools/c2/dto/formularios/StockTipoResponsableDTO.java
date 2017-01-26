package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 15:15:10 COT 2015
 */
public class StockTipoResponsableDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private Boolean estadoStock;
    private OrganismoTransitoDTO codigoOrganismo;
    private TipoResponsableFormularioDTO tipoResponsable;
    private TipoFormularioDTO tipoFormulario;

    // --- Constructor
    public StockTipoResponsableDTO() {
    }

    public StockTipoResponsableDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public OrganismoTransitoDTO getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransitoDTO codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public TipoResponsableFormularioDTO getTipoResponsable() {
        return this.tipoResponsable;
    }

    public void setTipoResponsable(TipoResponsableFormularioDTO tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

    public TipoFormularioDTO getTipoFormulario() {
        return this.tipoFormulario;
    }

    public void setTipoFormulario(TipoFormularioDTO tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public Boolean getEstadoStock() {
        return estadoStock;
    }

    public void setEstadoStock(Boolean estadoStock) {
        this.estadoStock = estadoStock;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

}
