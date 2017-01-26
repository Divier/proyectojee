package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 10:57:26 COT 2016
 */
public class InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private InconsistenciaDetalleCuotasFinanciacionDTO inconsistenciaDetalleCuotasFinanciacion;
    private InconsistenciaFinanciacionDTO inconsistenciaFinanciacion;
    private InconsistenciaDetalleFinanciacionDTO inconsistenciaDetalleFinanciacion;
    private ErrorInconsistenciaFinanciacionDTO errorInconsistenciaFinanciacion;

    // --- Constructor
    public InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO() {
    }

    public InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InconsistenciaDetalleCuotasFinanciacionDTO getInconsistenciaDetalleCuotasFinanciacion() {
        return this.inconsistenciaDetalleCuotasFinanciacion;
    }

    public void setInconsistenciaDetalleCuotasFinanciacion(
            InconsistenciaDetalleCuotasFinanciacionDTO inconsistenciaDetalleCuotasFinanciacion) {
        this.inconsistenciaDetalleCuotasFinanciacion = inconsistenciaDetalleCuotasFinanciacion;
    }

    public InconsistenciaFinanciacionDTO getInconsistenciaFinanciacion() {
        return this.inconsistenciaFinanciacion;
    }

    public void setInconsistenciaFinanciacion(InconsistenciaFinanciacionDTO inconsistenciaFinanciacion) {
        this.inconsistenciaFinanciacion = inconsistenciaFinanciacion;
    }

    public InconsistenciaDetalleFinanciacionDTO getInconsistenciaDetalleFinanciacion() {
        return this.inconsistenciaDetalleFinanciacion;
    }

    public void setInconsistenciaDetalleFinanciacion(
            InconsistenciaDetalleFinanciacionDTO inconsistenciaDetalleFinanciacion) {
        this.inconsistenciaDetalleFinanciacion = inconsistenciaDetalleFinanciacion;
    }

    public ErrorInconsistenciaFinanciacionDTO getErrorInconsistenciaFinanciacion() {
        return this.errorInconsistenciaFinanciacion;
    }

    public void setErrorInconsistenciaFinanciacion(ErrorInconsistenciaFinanciacionDTO errorInconsistenciaFinanciacion) {
        this.errorInconsistenciaFinanciacion = errorInconsistenciaFinanciacion;
    }

}
