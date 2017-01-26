package co.com.datatools.c2.dto.common.cartera;

import java.io.Serializable;
import java.math.BigDecimal;

import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;

/**
 * DTO Utilitario para afectar cartera - CU_CIR20_DAT_CAR_010
 * 
 * @author rodrigo.cruz
 * 
 */
public class MovimientoCarteraDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private MovimientosCarteraDTO movimientosCartera;
    private Integer idActividad;
    private BigDecimal saldoCapital;
    private BigDecimal saldoIntereses;
    private BigDecimal saldoCostasProcesales;

    public MovimientosCarteraDTO getMovimientosCartera() {
        return movimientosCartera;
    }

    public void setMovimientosCartera(MovimientosCarteraDTO movimientosCartera) {
        this.movimientosCartera = movimientosCartera;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getSaldoIntereses() {
        return saldoIntereses;
    }

    public void setSaldoIntereses(BigDecimal saldoIntereses) {
        this.saldoIntereses = saldoIntereses;
    }

    public BigDecimal getSaldoCostasProcesales() {
        return saldoCostasProcesales;
    }

    public void setSaldoCostasProcesales(BigDecimal saldoCostasProcesales) {
        this.saldoCostasProcesales = saldoCostasProcesales;
    }

}
