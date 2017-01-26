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
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "tipo_infractor")
@NamedQueries({ @NamedQuery(name = "TipoInfractor.findAll", query = "SELECT t FROM TipoInfractor t"), @NamedQuery(
        name = "TipoInfractor.findOrderByNombre",
        query = "SELECT ti FROM TipoInfractor AS ti ORDER BY ti.nombre") })
public class TipoInfractor implements EntidadC2, EntidadCatalogoC2 {

    private static final long serialVersionUID = 1L;
    public static final String SQ_TIPOS_ORDER_BY_NOMBRE = "TipoInfractor.findOrderByNombre";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_tipo_infractor")
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

    public TipoInfractor() {
    }

    public TipoInfractor(Integer id) {
        this.id = id;
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

    public Boolean getActivo() {
        return this.estado;
    }

    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}