/**
 * 
 */
package co.com.datatools.c2.axis.ws.cliente.vo.financiacion;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * vo para enviar las obligaciones asociadas a una financiacion
 * 
 * @author julio.pinzon 2016-08-16
 *
 */
public class InfraccionVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idfactura;
    private BigDecimal monto;
    private BigDecimal interes;

    public Long getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Long idfactura) {
        this.idfactura = idfactura;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

}
