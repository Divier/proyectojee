package co.com.datatools.c2.managed_bean.administracion.catalogo_simple;

import java.util.List;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class CatalogoSimpleFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private String tituloEditar;
    private CatalogoDTO catalogoDTO;
    private ItemCatalogoDTO filtroItemCatalogoDTO;
    private String tipoEstadoItem;
    private String registroTipoEstadoItem;
    private String actTipoEstadoItem;
    private List<ItemCatalogoDTO> itemCatalogoDTOs;
    private ItemCatalogoDTO seleccionItemCatalogoDTO;
    private ItemCatalogoDTO itemCatalogoDTO;

    public CatalogoSimpleFL() {
        super();
        filtroItemCatalogoDTO = new ItemCatalogoDTO();
        itemCatalogoDTO = new ItemCatalogoDTO();
    }

    public String getTituloEditar() {
        return tituloEditar;
    }

    public void setTituloEditar(String tituloEditar) {
        this.tituloEditar = tituloEditar;
    }

    public CatalogoDTO getCatalogoDTO() {
        return catalogoDTO;
    }

    public void setCatalogoDTO(CatalogoDTO catalogoDTO) {
        this.catalogoDTO = catalogoDTO;
    }

    public List<ItemCatalogoDTO> getItemCatalogoDTOs() {
        return itemCatalogoDTOs;
    }

    public void setItemCatalogoDTOs(List<ItemCatalogoDTO> itemCatalogoDTOs) {
        this.itemCatalogoDTOs = itemCatalogoDTOs;
    }

    public ItemCatalogoDTO getSeleccionItemCatalogoDTO() {
        return seleccionItemCatalogoDTO;
    }

    public void setSeleccionItemCatalogoDTO(ItemCatalogoDTO seleccionItemCatalogoDTO) {
        this.seleccionItemCatalogoDTO = seleccionItemCatalogoDTO;
    }

    public ItemCatalogoDTO getFiltroItemCatalogoDTO() {
        return filtroItemCatalogoDTO;
    }

    public void setFiltroItemCatalogoDTO(ItemCatalogoDTO filtroItemCatalogoDTO) {
        this.filtroItemCatalogoDTO = filtroItemCatalogoDTO;
    }

    public ItemCatalogoDTO getItemCatalogoDTO() {
        return itemCatalogoDTO;
    }

    public void setItemCatalogoDTO(ItemCatalogoDTO itemCatalogoDTO) {
        this.itemCatalogoDTO = itemCatalogoDTO;
    }

    public String getTipoEstadoItem() {
        return tipoEstadoItem;
    }

    public void setTipoEstadoItem(String tipoEstadoItem) {
        this.tipoEstadoItem = tipoEstadoItem;
    }

    public String getRegistroTipoEstadoItem() {
        return registroTipoEstadoItem;
    }

    public void setRegistroTipoEstadoItem(String registroTipoEstadoItem) {
        this.registroTipoEstadoItem = registroTipoEstadoItem;
    }

    public String getActTipoEstadoItem() {
        return actTipoEstadoItem;
    }

    public void setActTipoEstadoItem(String actTipoEstadoItem) {
        this.actTipoEstadoItem = actTipoEstadoItem;
    }

}
