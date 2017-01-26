package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO que se encarga de devolver los datos especificos para los obligaciones de financiacion
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaObligacionesFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idObligacion;
    private Integer tipoObligacion;
    private String nombreTipoObligacion;
    private String numeroObligacion;
    private Date fechaObligacion;
    private BigDecimal valorObligacion;
    private BigDecimal valorRecargo;
    private BigDecimal valorCostasProcesales;

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
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

    public BigDecimal getValorRecargo() {
        return valorRecargo;
    }

    public void setValorRecargo(BigDecimal valorRecargo) {
        this.valorRecargo = valorRecargo;
    }

    public BigDecimal getValorCostasProcesales() {
        return valorCostasProcesales;
    }

    public void setValorCostasProcesales(BigDecimal valorCostasProcesales) {
        this.valorCostasProcesales = valorCostasProcesales;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(Long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public Integer getTipoObligacion() {
        return tipoObligacion;
    }

    public void setTipoObligacion(Integer tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    public String getNombreTipoObligacion() {
        return nombreTipoObligacion;
    }

    public void setNombreTipoObligacion(String nombreTipoObligacion) {
        this.nombreTipoObligacion = nombreTipoObligacion;
    }
}
