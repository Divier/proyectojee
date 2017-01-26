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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author luis.forero (2015-02-02)
 */
@Entity
@Table(name = "stock_tipo_formulario")
@NamedQueries({ @NamedQuery(
        name = "StockTipoFormulario.findByTipoFor",
        query = "SELECT stf FROM StockTipoFormulario stf WHERE stf.tipoFormulario.id=:idTipoForm") })
public class StockTipoFormulario implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    public static final String SQ_FIND_BY_TIPOFORM = "StockTipoFormulario.findByTipoFor";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stock_tipo_formulario")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "porcentaje_maximo_consumo")
    private Integer porcentajeMaximoConsumo;

    @JoinColumn(name = "id_tipo_formulario", referencedColumnName = "id_tipo_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFormulario tipoFormulario;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito codigoOrganismo;

    public StockTipoFormulario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPorcentajeMaximoConsumo() {
        return porcentajeMaximoConsumo;
    }

    public void setPorcentajeMaximoConsumo(Integer porcentajeMaximoConsumo) {
        this.porcentajeMaximoConsumo = porcentajeMaximoConsumo;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public OrganismoTransito getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransito codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}
