package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad que permite mantener la referencia de las tablas que representan catalogos en el sistema
 * 
 * @author felipe.martinez
 * @version Iteracion 4 - valida
 */
@Entity
@Table(name = "catalogo")
public class Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id_catalogo")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "sigla")
    private String sigla;

    @Basic(optional = false)
    @Column(name = "editable")
    private Boolean editable;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre_entidad")
    private String nombreEntidad;

    @Column(name = "paquete_entidad")
    private String paqueteEntidad;

    @OneToMany(mappedBy = "idCatalogoDependencia", fetch = FetchType.LAZY)
    private List<Catalogo> catalogoList;

    @JoinColumn(name = "id_catalogo_dependencia", referencedColumnName = "id_catalogo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalogo idCatalogoDependencia;

    public Catalogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getPaqueteEntidad() {
        return paqueteEntidad;
    }

    public void setPaqueteEntidad(String paqueteEntidad) {
        this.paqueteEntidad = paqueteEntidad;
    }

    public Catalogo getIdCatalogoDependencia() {
        return idCatalogoDependencia;
    }

    public void setIdCatalogoDependencia(Catalogo idCatalogoDependencia) {
        this.idCatalogoDependencia = idCatalogoDependencia;
    }

}
