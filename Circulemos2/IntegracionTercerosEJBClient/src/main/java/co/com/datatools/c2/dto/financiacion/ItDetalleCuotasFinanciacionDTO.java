package co.com.datatools.c2.dto.financiacion;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 17 16:41:25 COT 2016
 */
public class ItDetalleCuotasFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idDetalleCuotasFinanciacion;
    private Integer numeroCuota;
    private Date fechaPagoOportuno;
    private BigDecimal valorCuota;
    private BigDecimal recargoCuota;
    private ItFinanciacionDTO itFinanciacion;

    // --- Constructor
    public ItDetalleCuotasFinanciacionDTO() {
    }

    public ItDetalleCuotasFinanciacionDTO(Long idDetalleCuotasFinanciacion) {
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

    public ItFinanciacionDTO getItFinanciacion() {
        return this.itFinanciacion;
    }

    public void setItFinanciacion(ItFinanciacionDTO itFinanciacion) {
        this.itFinanciacion = itFinanciacion;
    }

}
