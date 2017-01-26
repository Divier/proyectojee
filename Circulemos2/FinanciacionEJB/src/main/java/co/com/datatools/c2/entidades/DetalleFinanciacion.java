package co.com.datatools.c2.entidades;

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

@Entity
@Table(name = "detalle_financiacion")
@NamedQuery(name = "DetalleFinanciacion.findAll", query = "SELECT d FROM DetalleFinanciacion d")
public class DetalleFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_financiacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_financiacion")
    private Financiacion financiacion;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pago_oportuno")
    private Date fechaPagoOportuno;

    @Column(name = "valor_capital")
    private BigDecimal valorCapital;

    @Column(name = "valor_intereses")
    private BigDecimal valorIntereses;

    @Column(name = "id_documento")
    private Long idDocumento;

    @Column(name = "numero_volante")
    private Long numeroVolante;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_pago")
    private Date fechaPago;

    @Column(name = "valor_saldo_obligacion")
    private BigDecimal valorSaldoObligacion;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Financiacion getFinanciacion() {
        return this.financiacion;
    }

    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
    }

    public Date getFechaPagoOportuno() {
        return fechaPagoOportuno;
    }

    public void setFechaPagoOportuno(Date fechaPagoOportuno) {
        this.fechaPagoOportuno = fechaPagoOportuno;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(BigDecimal valorCapital) {
        this.valorCapital = valorCapital;
    }

    public BigDecimal getValorIntereses() {
        return valorIntereses;
    }

    public void setValorIntereses(BigDecimal valorIntereses) {
        this.valorIntereses = valorIntereses;
    }

    public BigDecimal getValorSaldoObligacion() {
        return valorSaldoObligacion;
    }

    public void setValorSaldoObligacion(BigDecimal valorSaldoObligacion) {
        this.valorSaldoObligacion = valorSaldoObligacion;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getNumeroVolante() {
        return numeroVolante;
    }

    public void setNumeroVolante(Long numeroVolante) {
        this.numeroVolante = numeroVolante;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

}