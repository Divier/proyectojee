package co.com.datatools.c2.entidades.financiacion;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the it_detalle_financiacion database table.
 * 
 */
@Entity
@Table(name = "it_detalle_financiacion")
@NamedQuery(name = "ItDetalleFinanciacion.findAll", query = "SELECT i FROM ItDetalleFinanciacion i")
public class ItDetalleFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_financiacion")
    private Long idDetalleFinanciacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_obligacion")
    private Date fechaObligacion;

    @Column(name = "obligacion")
    private Long obligacion;

    @Column(name = "tipo_obligacion")
    private String tipoObligacion;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    // bi-directional many-to-one association to ItFinanciacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_financiacion")
    private ItFinanciacion itFinanciacion;

    public ItDetalleFinanciacion() {
    }

    public Long getIdDetalleFinanciacion() {
        return idDetalleFinanciacion;
    }

    public void setIdDetalleFinanciacion(Long idDetalleFinanciacion) {
        this.idDetalleFinanciacion = idDetalleFinanciacion;
    }

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public Long getObligacion() {
        return obligacion;
    }

    public void setObligacion(Long obligacion) {
        this.obligacion = obligacion;
    }

    public String getTipoObligacion() {
        return tipoObligacion;
    }

    public void setTipoObligacion(String tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public ItFinanciacion getItFinanciacion() {
        return itFinanciacion;
    }

    public void setItFinanciacion(ItFinanciacion itFinanciacion) {
        this.itFinanciacion = itFinanciacion;
    }

}