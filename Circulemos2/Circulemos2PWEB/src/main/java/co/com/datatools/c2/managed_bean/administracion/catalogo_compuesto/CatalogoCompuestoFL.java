package co.com.datatools.c2.managed_bean.administracion.catalogo_compuesto;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class CatalogoCompuestoFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private String tituloEditar;
    private CatalogoDTO catalogoDTO;
    private ItemCatalogoDTO filtroItemCatalogoDTO;
    private String tipoEstadoItem;
    private String registroTipoEstadoItem;
    private String actTipoEstadoItem;
    private List<ItemCatalogoDTO> itemCatalogoDTOs;
    private ItemCatalogoDTO seleccionItemCatalogoDTO;
    private ItemCatalogoDTO seleccionItemCatalogoBDDTO;
    private ItemCatalogoDTO itemCatalogoDTO;

    private List<CatalogoDTO> catalogoDepenDTOs;
    private List<ItemCatalogoDTO> itemCatalogoPickLisDTOs;
    private List<ItemCatalogoDTO> itemCatalogoPickLisBDDTOs;
    private List<ItemCatalogoDTO> itemCatalogoGrupoDTOs;

    private List<ItemCatalogoDTO> itemCatalogoDepenDTOs;

    private String nombreDependencia;

    public CatalogoCompuestoFL() {
        super();
        filtroItemCatalogoDTO = new ItemCatalogoDTO();
        itemCatalogoDTO = new ItemCatalogoDTO();
        catalogoDepenDTOs = new ArrayList<CatalogoDTO>();
        itemCatalogoPickLisDTOs = new ArrayList<ItemCatalogoDTO>();
        itemCatalogoPickLisBDDTOs = new ArrayList<ItemCatalogoDTO>();
        itemCatalogoGrupoDTOs = new ArrayList<ItemCatalogoDTO>();
        itemCatalogoDepenDTOs = new ArrayList<ItemCatalogoDTO>();
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

    public String getRegistroTipoEstadoItem() {
        return registroTipoEstadoItem;
    }

    public void setRegistroTipoEstadoItem(String registroTipoEstadoItem) {
        this.registroTipoEstadoItem = registroTipoEstadoItem;
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

    public ItemCatalogoDTO getItemCatalogoDTO() {
        return itemCatalogoDTO;
    }

    public void setItemCatalogoDTO(ItemCatalogoDTO itemCatalogoDTO) {
        this.itemCatalogoDTO = itemCatalogoDTO;
    }

    public ItemCatalogoDTO getFiltroItemCatalogoDTO() {
        return filtroItemCatalogoDTO;
    }

    public void setFiltroItemCatalogoDTO(ItemCatalogoDTO filtroItemCatalogoDTO) {
        this.filtroItemCatalogoDTO = filtroItemCatalogoDTO;
    }

    public String getTipoEstadoItem() {
        return tipoEstadoItem;
    }

    public void setTipoEstadoItem(String tipoEstadoItem) {
        this.tipoEstadoItem = tipoEstadoItem;
    }

    public String getActTipoEstadoItem() {
        return actTipoEstadoItem;
    }

    public void setActTipoEstadoItem(String actTipoEstadoItem) {
        this.actTipoEstadoItem = actTipoEstadoItem;
    }

    public List<CatalogoDTO> getCatalogoDepenDTOs() {
        return catalogoDepenDTOs;
    }

    public void setCatalogoDepenDTOs(List<CatalogoDTO> catalogoDepenDTOs) {
        this.catalogoDepenDTOs = catalogoDepenDTOs;
    }

    public List<ItemCatalogoDTO> getItemCatalogoPickLisDTOs() {
        return itemCatalogoPickLisDTOs;
    }

    public void setItemCatalogoPickLisDTOs(List<ItemCatalogoDTO> itemCatalogoPickLisDTOs) {
        this.itemCatalogoPickLisDTOs = itemCatalogoPickLisDTOs;
    }

    public List<ItemCatalogoDTO> getItemCatalogoPickLisBDDTOs() {
        return itemCatalogoPickLisBDDTOs;
    }

    public void setItemCatalogoPickLisBDDTOs(List<ItemCatalogoDTO> itemCatalogoPickLisBDDTOs) {
        this.itemCatalogoPickLisBDDTOs = itemCatalogoPickLisBDDTOs;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public List<ItemCatalogoDTO> getItemCatalogoGrupoDTOs() {
        return itemCatalogoGrupoDTOs;
    }

    public void setItemCatalogoGrupoDTOs(List<ItemCatalogoDTO> itemCatalogoGrupoDTOs) {
        this.itemCatalogoGrupoDTOs = itemCatalogoGrupoDTOs;
    }

    public ItemCatalogoDTO getSeleccionItemCatalogoBDDTO() {
        return seleccionItemCatalogoBDDTO;
    }

    public void setSeleccionItemCatalogoBDDTO(ItemCatalogoDTO seleccionItemCatalogoBDDTO) {
        this.seleccionItemCatalogoBDDTO = seleccionItemCatalogoBDDTO;
    }

    public List<ItemCatalogoDTO> getItemCatalogoDepenDTOs() {
        return itemCatalogoDepenDTOs;
    }

    public void setItemCatalogoDepenDTOs(List<ItemCatalogoDTO> itemCatalogoDepenDTOs) {
        this.itemCatalogoDepenDTOs = itemCatalogoDepenDTOs;
    }

}
