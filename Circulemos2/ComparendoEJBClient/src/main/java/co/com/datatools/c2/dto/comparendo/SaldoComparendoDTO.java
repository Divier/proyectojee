package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO Contiene los saldos del comparendo.
 * 
 * @author luis.forero(2016-02-26)
 * 
 */
public class SaldoComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Saldo a capital del comparendo.
     */
    private BigDecimal saldoCapital;
    /**
     * Saldo a interés del comparendo.
     */
    private BigDecimal saldoInteres;

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getSaldoInteres() {
        return saldoInteres;
    }

    public void setSaldoInteres(BigDecimal saldoInteres) {
        this.saldoInteres = saldoInteres;
    }
}