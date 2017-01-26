package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * @author luis.forero
 * 
 */
public class StockTipoFormularioDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer porcentajeMaximoConsumo;

    private TipoFormularioDTO tipoFormulario;

    private OrganismoTransitoDTO organismoTransitoDTO;

    public StockTipoFormularioDTO() {

    }

    public StockTipoFormularioDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPorcentajeMaximoConsumo() {
        return porcentajeMaximoConsumo;
    }

    public void setPorcentajeMaximoConsumo(Integer porcentajeMaximoConsumo) {
        this.porcentajeMaximoConsumo = porcentajeMaximoConsumo;
    }

    public TipoFormularioDTO getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormularioDTO tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

}
