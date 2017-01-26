package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the tipo_consecutivo database table.
 * 
 */
@Entity
@Table(name = "tipo_consecutivo")
@NamedQuery(name = "TipoConsecutivo.findAll", query = "SELECT t FROM TipoConsecutivo t")
public class TipoConsecutivo implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_tipo_consecutivo")
    private Integer id;

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

    @Basic(optional = false)
    @Column(name = "usa_anio")
    private Boolean aplicaAnio;

    // bi-directional many-to-one association to Consecutivo
    @OneToMany(mappedBy = "tipoConsecutivo")
    private List<Consecutivo> consecutivos;

    public TipoConsecutivo() {
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public List<Consecutivo> getConsecutivos() {
        return this.consecutivos;
    }

    public void setConsecutivos(List<Consecutivo> consecutivos) {
        this.consecutivos = consecutivos;
    }

    public Consecutivo addConsecutivo(Consecutivo consecutivo) {
        getConsecutivos().add(consecutivo);
        consecutivo.setTipoConsecutivo(this);

        return consecutivo;
    }

    public Consecutivo removeConsecutivo(Consecutivo consecutivo) {
        getConsecutivos().remove(consecutivo);
        consecutivo.setTipoConsecutivo(null);

        return consecutivo;
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

    public Boolean getAplicaAnio() {
        return aplicaAnio;
    }

    public void setAplicaAnio(Boolean aplicaAnio) {
        this.aplicaAnio = aplicaAnio;
    }

}