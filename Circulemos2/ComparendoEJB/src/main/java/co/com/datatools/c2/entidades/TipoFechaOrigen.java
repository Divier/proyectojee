package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the tipo_fecha_origen database table.
 * 
 */
@Entity
@Table(name = "tipo_fecha_origen")
@NamedQuery(name = "TipoFechaOrigen.findAll", query = "SELECT t FROM TipoFechaOrigen t")
public class TipoFechaOrigen implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo_fecha_origen")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sigla")
    private String sigla;

    public TipoFechaOrigen() {
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;

    }

    @Override
    public Boolean getActivo() {
        return this.estado;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.estado = activo;

    }

}