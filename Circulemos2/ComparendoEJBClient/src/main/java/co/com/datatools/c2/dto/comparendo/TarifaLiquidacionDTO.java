package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TarifaLiquidacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal valorLiquidado;

    public BigDecimal getValorLiquidado() {
        return valorLiquidado;
    }

    public void setValorLiquidado(BigDecimal valorLiquidado) {
        this.valorLiquidado = valorLiquidado;
    }
}
