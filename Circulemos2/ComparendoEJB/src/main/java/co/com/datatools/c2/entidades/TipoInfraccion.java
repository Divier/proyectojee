package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "tipo_infraccion")
@NamedQueries({ @NamedQuery(name = "TipoInfraccion.findAll", query = "SELECT t FROM TipoInfraccion t"),
        @NamedQuery(
                name = "TipoInfraccion.consultarTiposInfraccion",
                query = "SELECT DISTINCT ti FROM TipoInfraccion ti LEFT JOIN FETCH ti.infraccionList i"
                        + " ORDER BY ti.nombre"),
        @NamedQuery(
                name = "TipoInfraccion.countTipInfrByNombre",
                query = "SELECT COUNT(tpi) FROM TipoInfraccion AS tpi WHERE tpi.nombre =:pNombreTI") })
public class TipoInfraccion implements EntidadC2, EntidadCatalogoC2 {

    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * SELECT DISTINCT ti FROM TipoInfraccion ti LEFT JOIN FETCH ti.infraccionList i ORDER BY ti.nombre
     * </pre>
     */
    public static String CONSULTAR_TIPOS_INFRACCION = "TipoInfraccion.consultarTiposInfraccion";

    /**
     * SELECT COUNT(tpi) FROM TipoInfraccion AS tpi WHERE tpi.nombre =:pNombreTI
     */
    public static String SQ_COUNT_TIPO_INFR_BY_NOMBRE = "TipoInfraccion.countTipInfrByNombre";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_infraccion")
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

    @OneToMany(mappedBy = "tipoInfraccion", fetch = FetchType.LAZY)
    private List<Infraccion> infraccionList;

    public TipoInfraccion() {
    }

    public TipoInfraccion(Integer id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Infraccion> getInfraccionList() {
        return infraccionList;
    }

    public void setInfraccionList(List<Infraccion> infraccionList) {
        this.infraccionList = infraccionList;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
