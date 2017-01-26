package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import co.com.datatools.c2.adaptador.cartera.IRCarteraFinanciacion.EnumCarteraFinanciacion;

/**
 * Representa una obligación de cartera en el módulo de financiacion.
 * 
 * @author julio.pinzon
 * 
 */
public class CarteraFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private EnumCarteraFinanciacion estado;
    private BigDecimal valorSaldoCapital;
    private BigDecimal valorSaldoInteres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumCarteraFinanciacion getEstado() {
        return estado;
    }

    public void setEstado(EnumCarteraFinanciacion estado) {
        this.estado = estado;
    }

    public BigDecimal getValorSaldoCapital() {
        return valorSaldoCapital;
    }

    public void setValorSaldoCapital(BigDecimal valorSaldoCapital) {
        this.valorSaldoCapital = valorSaldoCapital;
    }

    public BigDecimal getValorSaldoInteres() {
        return valorSaldoInteres;
    }

    public void setValorSaldoInteres(BigDecimal valorSaldoInteres) {
        this.valorSaldoInteres = valorSaldoInteres;
    }

}
