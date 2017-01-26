package co.com.datatools.c2.dto.parametrizacion;

import java.io.Serializable;

/**
 * Definicion general de los campos que tiene un registro de un catalogo
 * 
 * @author felipe.martinez
 */
public class ItemCatalogoDTO implements Serializable {

    private static final long serialVersionUID = -7800142172984543129L;

    protected Integer id;
    protected String nombre;
    protected String sigla;
    protected Boolean activo;
    protected String codigo;
    protected String descripcion;
    protected ItemCatalogoDTO itemCatalogoDependenciaDTO;
    protected CatalogoDTO catalogoDTO;

    public ItemCatalogoDTO() {

    }

    public ItemCatalogoDTO(Integer id) {
        super();
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Boolean isActivo() {
        return getActivo();
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ItemCatalogoDTO getItemCatalogoDependenciaDTO() {
        return itemCatalogoDependenciaDTO;
    }

    public void setItemCatalogoDependenciaDTO(ItemCatalogoDTO itemCatalogoDependenciaDTO) {
        this.itemCatalogoDependenciaDTO = itemCatalogoDependenciaDTO;
    }

    public CatalogoDTO getCatalogoDTO() {
        return catalogoDTO;
    }

    public void setCatalogoDTO(CatalogoDTO catalogoDTO) {
        this.catalogoDTO = catalogoDTO;
    }

}