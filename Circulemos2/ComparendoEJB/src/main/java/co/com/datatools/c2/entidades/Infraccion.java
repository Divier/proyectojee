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

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "infraccion")
@NamedQueries({ @NamedQuery(name = "Infraccion.findAll", query = "SELECT i FROM Infraccion i"), @NamedQuery(
        name = "Infraccion.findByCodInf",
        query = "SELECT i FROM Infraccion i LEFT JOIN FETCH i.tipoInfraccion ti LEFT JOIN FETCH i.tipoComparendo tc WHERE i.codigo = :pCodInf") })
public class Infraccion implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    /**
     * Consulta una infraccion por su codigo, retorna el tipo de infraccion de existir junto con el tipo de comparendo <br/>
     * <br>
     * SELECT i FROM Infraccion i LEFT JOIN FETCH i.tipoInfraccion ti LEFT JOIN FETCH ti.tipoComparendo tc WHERE i.codigo = :<b>pCodInf<b>
     * 
     * @author luis.forero(2015-11-19)
     */
    public static final String SQ_FIND_BY_CODIGO_INFRACCION = "Infraccion.findByCodInf";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_infraccion")
    private Integer id;

    @Column(name = "numeral_infraccion")
    private String numeral;

    @Basic(optional = false)
    @Column(name = "codigo_infraccion")
    private String codigo;

    @JoinColumn(name = "id_tipo_infraccion", referencedColumnName = "id_tipo_infraccion")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoInfraccion tipoInfraccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_comparendo")
    private TipoComparendo tipoComparendo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infraccion", fetch = FetchType.LAZY)
    private List<ConfiguracionInfraccion> configuracionInfraccionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infraccion", fetch = FetchType.LAZY)
    private List<TarifaInfraccion> tarifaInfraccionList;

    public Infraccion() {
    }

    public Infraccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeral() {
        return numeral;
    }

    public void setNumeral(String numeral) {
        this.numeral = numeral;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoInfraccion getTipoInfraccion() {
        return tipoInfraccion;
    }

    public void setTipoInfraccion(TipoInfraccion tipoInfraccion) {
        this.tipoInfraccion = tipoInfraccion;
    }

    public TipoComparendo getTipoComparendo() {
        return this.tipoComparendo;
    }

    public void setTipoComparendo(TipoComparendo tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public List<ConfiguracionInfraccion> getConfiguracionInfraccionList() {
        return configuracionInfraccionList;
    }

    public void setConfiguracionInfraccionList(List<ConfiguracionInfraccion> configuracionInfraccionList) {
        this.configuracionInfraccionList = configuracionInfraccionList;
    }

    public List<TarifaInfraccion> getTarifaInfraccionList() {
        return tarifaInfraccionList;
    }

    public void setTarifaInfraccionList(List<TarifaInfraccion> tarifaInfraccionList) {
        this.tarifaInfraccionList = tarifaInfraccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Infraccion)) {
            return false;
        }
        Infraccion other = (Infraccion) object;
        if ((this.getId() == null && other.getId() != null)
                || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.datatools.c2.entidades.Infraccion[ id=" + getId() + " ]";
    }

}
