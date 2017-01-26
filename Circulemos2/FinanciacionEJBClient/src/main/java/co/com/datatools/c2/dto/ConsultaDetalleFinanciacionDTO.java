package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO que se encarga de devolver los datos especificos para los detalles de financiacion
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaDetalleFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDetalleFinanciacion;
    private int numeroCuota;
    private Date fechaPagoOportuno;
    private BigDecimal valorCapital;
    private BigDecimal valorInteres;
    private BigDecimal valorTotalCuota;
    private BigDecimal saldoObligacion;
    private Date fechaRealPago;

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaPagoOportuno() {
        return fechaPagoOportuno;
    }

    public void setFechaPagoOportuno(Date fechaPagoOportuno) {
        this.fechaPagoOportuno = fechaPagoOportuno;
    }

    public BigDecimal getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(BigDecimal valorCapital) {
        this.valorCapital = valorCapital;
    }

    public BigDecimal getValorInteres() {
        return valorInteres;
    }

    public void setValorInteres(BigDecimal valorInteres) {
        this.valorInteres = valorInteres;
    }

    public BigDecimal getValorTotalCuota() {
        return valorTotalCuota;
    }

    public void setValorTotalCuota(BigDecimal valorTotalCuota) {
        this.valorTotalCuota = valorTotalCuota;
    }

    public BigDecimal getSaldoObligacion() {
        return saldoObligacion;
    }

    public void setSaldoObligacion(BigDecimal saldoObligacion) {
        this.saldoObligacion = saldoObligacion;
    }

    public Date getFechaRealPago() {
        return fechaRealPago;
    }

    public void setFechaRealPago(Date fechaRealPago) {
        this.fechaRealPago = fechaRealPago;
    }

    public Long getIdDetalleFinanciacion() {
        return idDetalleFinanciacion;
    }

    public void setIdDetalleFinanciacion(Long idDetalleFinanciacion) {
        this.idDetalleFinanciacion = idDetalleFinanciacion;
    }

}
