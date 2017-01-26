package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ObligacionFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1434565607684208369L;

    private String tipoObligacion;
    private String nombreObligacion;
    private String obligacion;
    private Date fechaObligacion;
    private BigDecimal valorObligacion;
    private BigDecimal recargoOblicacion;

    public String getTipoObligacion() {
        return tipoObligacion;
    }

    public void setTipoObligacion(String tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public BigDecimal getRecargoOblicacion() {
        return recargoOblicacion;
    }

    public void setRecargoOblicacion(BigDecimal recargoOblicacion) {
        this.recargoOblicacion = recargoOblicacion;
    }

    public String getNombreObligacion() {
        return nombreObligacion;
    }

    public void setNombreObligacion(String nombreObligacion) {
        this.nombreObligacion = nombreObligacion;
    }

    public String getObligacion() {
        return obligacion;
    }

    public void setObligacion(String obligacion) {
        this.obligacion = obligacion;
    }
}
