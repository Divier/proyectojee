package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.c2.entidades.util.EntidadCatalogoCompuestoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "causal_cambio_estado")
@NamedQueries({ @NamedQuery(name = "CausalCambioEstado.findAll", query = "SELECT c FROM CausalCambioEstado c") })
public class CausalCambioEstado implements EntidadC2, EntidadCatalogoCompuestoC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_causal_cambio_estado")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "causalCambioEstado", fetch = FetchType.LAZY)
    private List<DetalleCambioEstado> detalleCambioEstadoList;

    @JoinColumn(name = "id_estado_formulario", referencedColumnName = "id_estado_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormulario estadoFormulario;

    public CausalCambioEstado() {
    }

    public CausalCambioEstado(Integer idCausalCambioEstado) {
        this.id = idCausalCambioEstado;
    }

    public CausalCambioEstado(Integer idCausalCambioEstado, String nombre, Boolean estado) {
        this.id = idCausalCambioEstado;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetalleCambioEstado> getDetalleCambioEstadoList() {
        return detalleCambioEstadoList;
    }

    public void setDetalleCambioEstadoList(List<DetalleCambioEstado> detalleCambioEstadoList) {
        this.detalleCambioEstadoList = detalleCambioEstadoList;
    }

    public EstadoFormulario getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormulario estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    @Transient
    public EntidadCatalogoC2 getDependencia() {
        return this.estadoFormulario;
    }

    @Transient
    public void setDependencia(EntidadCatalogoC2 entidadCatalogoC2) {
        this.estadoFormulario = (EstadoFormulario) entidadCatalogoC2;
    }

    public Boolean getActivo() {
        return estado;
    }

    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
