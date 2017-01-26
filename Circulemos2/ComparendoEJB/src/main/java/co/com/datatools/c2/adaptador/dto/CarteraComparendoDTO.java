package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import co.com.datatools.c2.adaptador.cartera.ILCarteraComparendo.EnumCarteraComparendo;

/**
 * Representa una obligación de cartera en el módulo de comparendos.
 * 
 * @author julio.pinzon
 * 
 */
public class CarteraComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer idTipoObligacion;
    private String nomTipoObligacion;
    private EnumCarteraComparendo estado;
    private BigDecimal valorSaldoCapital;
    private BigDecimal valorSaldoInteres;
    private BigDecimal valorSaldoCostasProcesales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumCarteraComparendo getEstado() {
        return estado;
    }

    public void setEstado(EnumCarteraComparendo estado) {
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

    public Integer getIdTipoObligacion() {
        return idTipoObligacion;
    }

    public void setIdTipoObligacion(Integer idTipoObligacion) {
        this.idTipoObligacion = idTipoObligacion;
    }

    public String getNomTipoObligacion() {
        return nomTipoObligacion;
    }

    public void setNomTipoObligacion(String nomTipoObligacion) {
        this.nomTipoObligacion = nomTipoObligacion;
    }

    public BigDecimal getValorSaldoCostasProcesales() {
        return valorSaldoCostasProcesales;
    }

    public void setValorSaldoCostasProcesales(BigDecimal valorSaldoCostasProcesales) {
        this.valorSaldoCostasProcesales = valorSaldoCostasProcesales;
    }

}
