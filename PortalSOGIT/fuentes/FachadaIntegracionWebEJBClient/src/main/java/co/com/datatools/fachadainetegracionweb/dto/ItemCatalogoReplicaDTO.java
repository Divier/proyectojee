package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ItemCatalogoReplicaDTO")
public class ItemCatalogoReplicaDTO implements Serializable {

    private static final long serialVersionUID = -7800142172984543129L;

    protected Integer id;
    protected String nombre;
    protected String sigla;
    protected boolean activo;
    protected String codigo;
    protected String descripcion;
    protected ItemCatalogoReplicaDTO itemCatalogoDependenciaDTO;

    public ItemCatalogoReplicaDTO() {

    }

    public ItemCatalogoReplicaDTO(Integer id) {
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

    public ItemCatalogoReplicaDTO getItemCatalogoDependenciaDTO() {
        return itemCatalogoDependenciaDTO;
    }

    public void setItemCatalogoDependenciaDTO(ItemCatalogoReplicaDTO itemCatalogoDependenciaDTO) {
        this.itemCatalogoDependenciaDTO = itemCatalogoDependenciaDTO;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
