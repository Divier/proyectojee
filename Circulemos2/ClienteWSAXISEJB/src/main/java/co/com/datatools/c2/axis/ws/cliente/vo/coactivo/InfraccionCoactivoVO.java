/**
 * 
 */
package co.com.datatools.c2.axis.ws.cliente.vo.coactivo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Objeto que se enviará como parametro en registro de las infracciones del coactivo
 * 
 * @author Jeison.Rodriguez (2016-09-21)
 *
 */
public class InfraccionCoactivoVO implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    private String idTipoDocumento;
    private Integer idDocumento;
    private BigDecimal monto;
    private BigDecimal interes;

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
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
