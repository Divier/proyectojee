package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * The persistent class for the tarifa_infraccion database table.
 * 
 * @author robert.bautista
 */
@Entity
@Table(name = "tarifa_infraccion")
@NamedQuery(name = "TarifaInfraccion.findAll", query = "SELECT t FROM TarifaInfraccion t")
public class TarifaInfraccion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa_infraccion")
    private Integer idTarifaInfraccion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_confirmacion")
    private Date fechaConfirmacion;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_final_vigencia")
    private Date fechaFinalVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio_vigencia")
    private Date fechaInicioVigencia;

    @Basic(optional = false)
    @Column(name = "porcentaje_descuento")
    private BigDecimal porcentajeDescuento;

    @Column(name = "tarifa_confirmada")
    private Boolean tarifaConfirmada;

    @Basic(optional = false)
    @Column(name = "valor_cia")
    private BigDecimal valorCia;

    @Basic(optional = false)
    @Column(name = "valor_comparendo")
    private BigDecimal valorComparendo;

    @Basic(optional = false)
    @Column(name = "valor_descuento")
    private BigDecimal valorDescuento;

    @Basic(optional = false)
    @Column(name = "valor_infraccion")
    private BigDecimal valorInfraccion;

    // bi-directional many-to-one association to Infraccion
    @ManyToOne
    @JoinColumn(name = "id_infraccion")
    private Infraccion infraccion;

    public TarifaInfraccion() {
    }

    public Integer getIdTarifaInfraccion() {
        return this.idTarifaInfraccion;
    }

    public void setIdTarifaInfraccion(Integer idTarifaInfraccion) {
        this.idTarifaInfraccion = idTarifaInfraccion;
    }

    public Date getFechaConfirmacion() {
        return this.fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalVigencia() {
        return this.fechaFinalVigencia;
    }

    public void setFechaFinalVigencia(Date fechaFinalVigencia) {
        this.fechaFinalVigencia = fechaFinalVigencia;
    }

    public Date getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public BigDecimal getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Boolean getTarifaConfirmada() {
        return this.tarifaConfirmada;
    }

    public void setTarifaConfirmada(Boolean tarifaConfirmada) {
        this.tarifaConfirmada = tarifaConfirmada;
    }

    public BigDecimal getValorCia() {
        return this.valorCia;
    }

    public void setValorCia(BigDecimal valorCia) {
        this.valorCia = valorCia;
    }

    public BigDecimal getValorComparendo() {
        return this.valorComparendo;
    }

    public void setValorComparendo(BigDecimal valorComparendo) {
        this.valorComparendo = valorComparendo;
    }

    public BigDecimal getValorDescuento() {
        return this.valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public BigDecimal getValorInfraccion() {
        return this.valorInfraccion;
    }

    public void setValorInfraccion(BigDecimal valorInfraccion) {
        this.valorInfraccion = valorInfraccion;
    }

    public Infraccion getInfraccion() {
        return this.infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

}