package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * No regenerar datos agregados
 * 
 * @author Generated
 * @version 3.0 - Fri May 06 12:00:49 COT 2016
 */
public class ObligacionFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoTipoObligacion;
    private String numeroObligacion;
    private FinanciacionDTO financiacion;
    private Date fechaObligacion;
    private BigDecimal valorCostasProcesales;
    private Long idCartera;
    private BigDecimal valorObligacion;
    private BigDecimal valorInteresMoratorios;

    // --- Constructor
    public ObligacionFinanciacionDTO() {
    }

    public ObligacionFinanciacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoObligacion() {
        return this.codigoTipoObligacion;
    }

    public void setCodigoTipoObligacion(Integer codigoTipoObligacion) {
        this.codigoTipoObligacion = codigoTipoObligacion;
    }

    public String getNumeroObligacion() {
        return this.numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public FinanciacionDTO getFinanciacion() {
        return this.financiacion;
    }

    public void setFinanciacion(FinanciacionDTO financiacion) {
        this.financiacion = financiacion;
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

    public BigDecimal getValorCostasProcesales() {
        return valorCostasProcesales;
    }

    public void setValorCostasProcesales(BigDecimal valorCostasProcesales) {
        this.valorCostasProcesales = valorCostasProcesales;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public BigDecimal getValorInteresMoratorios() {
        return valorInteresMoratorios;
    }

    public void setValorInteresMoratorios(BigDecimal valorInteresMoratorios) {
        this.valorInteresMoratorios = valorInteresMoratorios;
    }

}
