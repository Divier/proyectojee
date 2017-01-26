package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 10:56:26 COT 2016
 */
public class InconsistenciaDetalleFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idDetalleFinanciacion;
    private Date fechaObligacion;
    private Long obligacion;
    private String tipoObligacion;
    private BigDecimal valorObligacion;
    private InconsistenciaFinanciacionDTO inconsistenciaFinanciacion;

    // --- Constructor
    public InconsistenciaDetalleFinanciacionDTO() {
    }

    public InconsistenciaDetalleFinanciacionDTO(Long idDetalleFinanciacion) {
        this.idDetalleFinanciacion = idDetalleFinanciacion;

    }

    // --- Getters-Setters
    public Long getIdDetalleFinanciacion() {
        return this.idDetalleFinanciacion;
    }

    public void setIdDetalleFinanciacion(Long idDetalleFinanciacion) {
        this.idDetalleFinanciacion = idDetalleFinanciacion;
    }

    public Date getFechaObligacion() {
        return this.fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public Long getObligacion() {
        return this.obligacion;
    }

    public void setObligacion(Long obligacion) {
        this.obligacion = obligacion;
    }

    public String getTipoObligacion() {
        return this.tipoObligacion;
    }

    public void setTipoObligacion(String tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    public BigDecimal getValorObligacion() {
        return this.valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public InconsistenciaFinanciacionDTO getInconsistenciaFinanciacion() {
        return this.inconsistenciaFinanciacion;
    }

    public void setInconsistenciaFinanciacion(InconsistenciaFinanciacionDTO inconsistenciaFinanciacion) {
        this.inconsistenciaFinanciacion = inconsistenciaFinanciacion;
    }

}
