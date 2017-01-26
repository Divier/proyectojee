package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Registro cartera financiacion
 * 
 * @author julio.pinzon 2016-05-18
 * 
 */
public class RegistroCarteraFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaFinanciacion;
    private String numeroFinanciacion;
    private int origenObligacion;
    private Long idFinanciacion;
    private BigDecimal valorFinanciacion;
    private Long idDeudor;

    public String getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(String numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public int getOrigenObligacion() {
        return origenObligacion;
    }

    public void setOrigenObligacion(int origenObligacion) {
        this.origenObligacion = origenObligacion;
    }

    public Long getIdFinanciacion() {
        return idFinanciacion;
    }

    public void setIdFinanciacion(Long idFinanciacion) {
        this.idFinanciacion = idFinanciacion;
    }

    public Date getFechaFinanciacion() {
        return fechaFinanciacion;
    }

    public void setFechaFinanciacion(Date fechaFinanciacion) {
        this.fechaFinanciacion = fechaFinanciacion;
    }

    public BigDecimal getValorFinanciacion() {
        return valorFinanciacion;
    }

    public void setValorFinanciacion(BigDecimal valorFinanciacion) {
        this.valorFinanciacion = valorFinanciacion;
    }

    public Long getIdDeudor() {
        return idDeudor;
    }

    public void setIdDeudor(Long idDeudor) {
        this.idDeudor = idDeudor;
    }

}
