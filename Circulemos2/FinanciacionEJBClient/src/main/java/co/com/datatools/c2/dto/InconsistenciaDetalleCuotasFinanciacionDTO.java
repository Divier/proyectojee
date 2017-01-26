package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 10:56:54 COT 2016
 */
public class InconsistenciaDetalleCuotasFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idDetalleCuotasFinanciacion;
    private Integer numeroCuota;
    private Date fechaPagoOportuno;
    private BigDecimal valorCuota;
    private BigDecimal recargoCuota;
    private InconsistenciaFinanciacionDTO inconsistenciaFinanciacion;

    // --- Constructor
    public InconsistenciaDetalleCuotasFinanciacionDTO() {
    }

    public InconsistenciaDetalleCuotasFinanciacionDTO(Long idDetalleCuotasFinanciacion) {
        this.idDetalleCuotasFinanciacion = idDetalleCuotasFinanciacion;

    }

    // --- Getters-Setters
    public Long getIdDetalleCuotasFinanciacion() {
        return this.idDetalleCuotasFinanciacion;
    }

    public void setIdDetalleCuotasFinanciacion(Long idDetalleCuotasFinanciacion) {
        this.idDetalleCuotasFinanciacion = idDetalleCuotasFinanciacion;
    }

    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaPagoOportuno() {
        return this.fechaPagoOportuno;
    }

    public void setFechaPagoOportuno(Date fechaPagoOportuno) {
        this.fechaPagoOportuno = fechaPagoOportuno;
    }

    public BigDecimal getValorCuota() {
        return this.valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    public BigDecimal getRecargoCuota() {
        return this.recargoCuota;
    }

    public void setRecargoCuota(BigDecimal recargoCuota) {
        this.recargoCuota = recargoCuota;
    }

    public InconsistenciaFinanciacionDTO getInconsistenciaFinanciacion() {
        return this.inconsistenciaFinanciacion;
    }

    public void setInconsistenciaFinanciacion(InconsistenciaFinanciacionDTO inconsistenciaFinanciacion) {
        this.inconsistenciaFinanciacion = inconsistenciaFinanciacion;
    }

}
