package co.com.datatools.fachadaintegracionweb.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @version Sprint 7
 * @author luis.forero(mod 2015-12-03)
 */
@Entity
@Table(name = "medio_imposicion_comparendo")
@NamedQueries({
        @NamedQuery(name = "MedioImposicionComparendo.findAll", query = "SELECT m FROM MedioImposicionComparendo m"),
        @NamedQuery(
                name = "MedioImposicionComparendo.findByCodigo",
                query = "SELECT m FROM MedioImposicionComparendo m WHERE m.codigo = :pCod") })
public class MedioImposicionComparendo implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta un medio de imposicion por su codigo<br>
     * 
     * SELECT m FROM MedioImposicionComparendo m WHERE m.codigo = :pCod
     * 
     * @author luis.forero(2015-12-03)
     */
    public static final String SQ_FIND_BY_CODIGO = "MedioImposicionComparendo.findByCodigo";

    @Id
    @Basic(optional = false)
    @Column(name = "codigo_medio_imposicion")
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

    public MedioImposicionComparendo() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getActivo() {
        return estado;
    }

    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}