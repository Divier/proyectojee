package co.com.datatools.c2.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
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
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "stock_tipo_responsable")
@NamedQueries({ @NamedQuery(name = "StockTipoResponsable.findAll", query = "SELECT s FROM StockTipoResponsable s"),
        @NamedQuery(
                name = "StockTipoResponsable.findByIdStockTipoResponsable",
                query = "SELECT s FROM StockTipoResponsable s WHERE s.id = :idStockTipoResponsable"),
        @NamedQuery(
                name = "StockTipoResponsable.findByStockMinimo",
                query = "SELECT s FROM StockTipoResponsable s WHERE s.stockMinimo = :stockMinimo"),
        @NamedQuery(
                name = "StockTipoResponsable.findByStockMaximo",
                query = "SELECT s FROM StockTipoResponsable s WHERE s.stockMaximo = :stockMaximo"),
        @NamedQuery(
                name = "StockTipoResponsable.findByEstadoStock",
                query = "SELECT s FROM StockTipoResponsable s WHERE s.estadoStock = :estadoStock"),
        @NamedQuery(
                name = "StockTipoResponsable.countByTipFormTipResp",
                query = "SELECT COUNT(s) FROM StockTipoResponsable AS s WHERE s.tipoFormulario.id = :pIdTipForm AND s.tipoResponsable.id = :pIdTipResp") })
public class StockTipoResponsable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SQ_COUNT_BY_TIP_FORM_TIP_RESP = "StockTipoResponsable.countByTipFormTipResp";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stock_tipo_responsable")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    @Basic(optional = false)
    @Column(name = "stock_maximo")
    private Integer stockMaximo;

    @Basic(optional = false)
    @Column(name = "estado_stock")
    private Boolean estadoStock;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito codigoOrganismo;

    @JoinColumn(name = "id_tipo_responsable", referencedColumnName = "id_tipo_responsable")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoResponsableFormulario tipoResponsable;

    @JoinColumn(name = "id_tipo_formulario", referencedColumnName = "id_tipo_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFormulario tipoFormulario;

    public StockTipoResponsable() {
    }

    public StockTipoResponsable(Integer id) {
        this.id = id;
    }

    public StockTipoResponsable(Integer id, int stockMinimo, int stockMaximo, Boolean estadoStock) {
        this.id = id;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.estadoStock = estadoStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganismoTransito getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransito codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public TipoResponsableFormulario getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(TipoResponsableFormulario idTipoResponsable) {
        this.tipoResponsable = idTipoResponsable;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario idTipoFormulario) {
        this.tipoFormulario = idTipoFormulario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockTipoResponsable)) {
            return false;
        }
        StockTipoResponsable other = (StockTipoResponsable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication2.StockTipoResponsable[ idStockTipoResponsable=" + id + " ]";
    }

    public Boolean getEstadoStock() {
        return estadoStock;
    }

    public void setEstadoStock(Boolean estadoStock) {
        this.estadoStock = estadoStock;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

}
