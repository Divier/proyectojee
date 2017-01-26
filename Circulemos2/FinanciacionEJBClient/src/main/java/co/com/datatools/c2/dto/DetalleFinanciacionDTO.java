package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Fri May 06 12:00:36 COT 2016
 */
public class DetalleFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private FinanciacionDTO financiacion;
    private Integer numeroCuota;
    private BigDecimal valorTotal;
    private Date fechaPagoOportuno;
    private BigDecimal valorCapital;
    private BigDecimal valorIntereses;
    private Long idDocumento;
    private Long numeroVolante;
    private Date fechaPago;
    private BigDecimal valorSaldoObligacion;

    /**
     * Attribute to Identify the registry on graphic interface
     */
    private int index;
    /**
     * Attribute to manipule the style of text
     */
    private String styleText;

    // --- Constructor
    public DetalleFinanciacionDTO() {
    }

    public DetalleFinanciacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
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

    public FinanciacionDTO getFinanciacion() {
        return this.financiacion;
    }

    public void setFinanciacion(FinanciacionDTO financiacion) {
        this.financiacion = financiacion;
    }

    public Date getFechaPagoOportuno() {
        return fechaPagoOportuno;
    }

    public void setFechaPagoOportuno(Date fechaPagoOportuno) {
        this.fechaPagoOportuno = fechaPagoOportuno;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStyleText() {
        return styleText;
    }

    public void setStyleText(String styleText) {
        this.styleText = styleText;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
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

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

}
