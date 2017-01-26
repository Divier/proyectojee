package co.com.datatools.c2.dto.comparendo;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu May 29 10:11:18 COT 2014
 */
public class TarifaInfraccionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer idTarifaInfraccion;
    private BigDecimal porcentajeDescuento;
    private BigDecimal valorInfraccion;
    private Date fechaInicioVigencia;
    private Date fechaFinalVigencia;
    private Boolean confirmada;
    private Date fechaCreacion;
    private Date fechaConfirmacion;
    private InfraccionDTO infraccion;
    private Boolean tarifaConfirmada;
    private BigDecimal valorCia;
    private BigDecimal valorComparendo;
    private BigDecimal valorDescuento;

    // --- Constructor
    public TarifaInfraccionDTO() {
    }

    // --- Getters-Setters
    public Integer getIdTarifaInfraccion() {
        return idTarifaInfraccion;
    }

    public void setIdTarifaInfraccion(Integer idTarifaInfraccion) {
        this.idTarifaInfraccion = idTarifaInfraccion;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getValorInfraccion() {
        return valorInfraccion;
    }

    public void setValorInfraccion(BigDecimal valorInfraccion) {
        this.valorInfraccion = valorInfraccion;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinalVigencia() {
        return fechaFinalVigencia;
    }

    public void setFechaFinalVigencia(Date fechaFinalVigencia) {
        this.fechaFinalVigencia = fechaFinalVigencia;
    }

    public Boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Boolean confirmada) {
        this.confirmada = confirmada;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public InfraccionDTO getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(InfraccionDTO infraccion) {
        this.infraccion = infraccion;
    }

    public Boolean getTarifaConfirmada() {
        return tarifaConfirmada;
    }

    public void setTarifaConfirmada(Boolean tarifaConfirmada) {
        this.tarifaConfirmada = tarifaConfirmada;
    }

    public BigDecimal getValorCia() {
        return valorCia;
    }

    public void setValorCia(BigDecimal valorCia) {
        this.valorCia = valorCia;
    }

    public BigDecimal getValorComparendo() {
        return valorComparendo;
    }

    public void setValorComparendo(BigDecimal valorComparendo) {
        this.valorComparendo = valorComparendo;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
