package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "estado_formulario")
@NamedQueries({ @NamedQuery(name = "EstadoFormulario.findAll", query = "SELECT e FROM EstadoFormulario e") })
public class EstadoFormulario implements EntidadC2, EntidadCatalogoC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_formulario")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    public EstadoFormulario() {
    }

    public EstadoFormulario(Integer idEstadoFormulario) {
        this.id = idEstadoFormulario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idEstadoFormulario) {
        this.id = idEstadoFormulario;
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
