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
 * @version Sprint 4
 */
@Entity
@Table(name = "tipo_sancion")
@NamedQueries({ @NamedQuery(name = "TipoSancion.findAll", query = "SELECT t FROM TipoSancion t"),
        /*
         * @NamedQuery( name = "TipoSancion.findTiposSancionByIdPais", query =
         * "SELECT ts FROM TipoSancion AS ts WHERE ts.pais.id = :pIdPais ORDER BY ts.nombre")
         */ })
public class TipoSancion implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_TIPOS_SANCION_X_PAIS = "TipoSancion.findTiposSancionByIdPais";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_sancion")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "sigla")
    private String sigla;

    public TipoSancion() {
    }

    public TipoSancion(Integer idTipoSancion) {
        this.setId(idTipoSancion);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
