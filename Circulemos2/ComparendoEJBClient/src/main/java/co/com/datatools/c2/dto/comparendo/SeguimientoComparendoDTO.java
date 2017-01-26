package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO Contiene informacion basica del comparendo junto con su trazabilidad y saldos de cartera.
 * 
 * @author luis.forero(2016-02-26)
 */
public class SeguimientoComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ComparendoDTO comparendoDTO;
    private SaldoComparendoDTO saldoComparendoDTO;
    private String descripcionInfraccion;
    private BigDecimal valorInfraccion;

    public ComparendoDTO getComparendoDTO() {
        return comparendoDTO;
    }

    public void setComparendoDTO(ComparendoDTO comparendoDTO) {
        this.comparendoDTO = comparendoDTO;
    }

    public SaldoComparendoDTO getSaldoComparendoDTO() {
        return saldoComparendoDTO;
    }

    public void setSaldoComparendoDTO(SaldoComparendoDTO saldoComparendoDTO) {
        this.saldoComparendoDTO = saldoComparendoDTO;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public BigDecimal getValorInfraccion() {
        return valorInfraccion;
    }

    public void setValorInfraccion(BigDecimal valorInfraccion) {
        this.valorInfraccion = valorInfraccion;
    }
}
