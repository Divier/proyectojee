package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Javier.Fajardo
 * 
 */
public class CalculoFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int numeroCuotas;
    private BigDecimal tasaInteresFinanciacion;
    private BigDecimal valorTotalCitaciones;
    private BigDecimal valorTotalRecargos;
    private BigDecimal valorTotalCostas;
    private BigDecimal valorTotal;
    private BigDecimal valorTotalFinanciarIntereses;

    public CalculoFinanciacionDTO() {
    }

    public BigDecimal getTasaInteresFinanciacion() {
        return tasaInteresFinanciacion;
    }

    public void setTasaInteresFinanciacion(BigDecimal tasaInteresFinanciacion) {
        this.tasaInteresFinanciacion = tasaInteresFinanciacion;
    }

    public BigDecimal getValorTotalCitaciones() {
        return valorTotalCitaciones;
    }

    public void setValorTotalCitaciones(BigDecimal valorTotalCitaciones) {
        this.valorTotalCitaciones = valorTotalCitaciones;
    }

    public BigDecimal getValorTotalRecargos() {
        return valorTotalRecargos;
    }

    public void setValorTotalRecargos(BigDecimal valorTotalRecargos) {
        this.valorTotalRecargos = valorTotalRecargos;
    }

    public BigDecimal getValorTotalCostas() {
        return valorTotalCostas;
    }

    public void setValorTotalCostas(BigDecimal valorTotalCostas) {
        this.valorTotalCostas = valorTotalCostas;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotalFinanciarIntereses() {
        return valorTotalFinanciarIntereses;
    }

    public void setValorTotalFinanciarIntereses(BigDecimal valorTotalFinanciarIntereses) {
        this.valorTotalFinanciarIntereses = valorTotalFinanciarIntereses;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }
}
