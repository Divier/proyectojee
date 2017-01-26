/**
 * 
 */
package co.com.datatools.c2.axis.ws.cliente.vo.financiacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * Vo utilizado para enviar las cuotas de una financiacion
 * 
 * @author julio.pinzon 2016-08-16
 *
 */
public class CuotaVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @SerializedName("nro_cuota")
    private Integer nroCuota;
    private BigDecimal monto;
    private BigDecimal interes;

    @SerializedName("fecha_inicio")
    private Date fechaInicio;

    @SerializedName("fecha_vencimiento")
    private Date fechaVencimiento;

    public Integer getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(Integer nroCuota) {
        this.nroCuota = nroCuota;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
