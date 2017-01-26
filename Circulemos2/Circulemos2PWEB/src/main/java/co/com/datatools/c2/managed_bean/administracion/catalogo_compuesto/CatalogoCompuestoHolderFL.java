package co.com.datatools.c2.managed_bean.administracion.catalogo_compuesto;

import java.util.List;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class CatalogoCompuestoHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private CatalogoDTO filtroCatalogoDTO;

    private CatalogoDTO seleccionCatalogoDTO;

    private List<CatalogoDTO> catalogoDTOs;

    public CatalogoCompuestoHolderFL() {
        super();
        filtroCatalogoDTO = new CatalogoDTO();
    }

    public CatalogoDTO getFiltroCatalogoDTO() {
        return filtroCatalogoDTO;
    }

    public void setFiltroCatalogoDTO(CatalogoDTO filtroCatalogoDTO) {
        this.filtroCatalogoDTO = filtroCatalogoDTO;
    }

    public List<CatalogoDTO> getCatalogoDTOs() {
        return catalogoDTOs;
    }

    public void setCatalogoDTOs(List<CatalogoDTO> catalogoDTOs) {
        this.catalogoDTOs = catalogoDTOs;
    }

    public CatalogoDTO getSeleccionCatalogoDTO() {
        return seleccionCatalogoDTO;
    }

    public void setSeleccionCatalogoDTO(CatalogoDTO seleccionCatalogoDTO) {
        this.seleccionCatalogoDTO = seleccionCatalogoDTO;
    }

}
