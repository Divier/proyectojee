package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.c2.entidades.util.EntidadCatalogoCompuestoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 10
 * 
 */
@Entity
@Table(name = "estado_proceso")
@NamedQuery(name = "EstadoProceso.findAll", query = "SELECT e FROM EstadoProceso e")
public class EstadoProceso implements EntidadC2, EntidadCatalogoCompuestoC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_proceso")
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

    @JoinColumn(name = "id_tipo_proceso", referencedColumnName = "id_tipo_proceso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoProceso tipoProceso;

    public EstadoProceso() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    @Override
    public Boolean getActivo() {
        return this.estado;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.estado = activo;
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

    @Transient
    public EntidadCatalogoC2 getDependencia() {
        return this.tipoProceso;
    }

    @Transient
    public void setDependencia(EntidadCatalogoC2 entidadCatalogoC2) {
        this.tipoProceso = (TipoProceso) entidadCatalogoC2;

    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

}