package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 10 16:41:51 COT 2016
 */
public class DetallePagoConciliacionErrorDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private DetallePagoDTO detallePago;
    private ErrorConciliacionPagoDTO errorConciliacionPago;
    private Date fechaRegistro;

    // --- Constructor
    public DetallePagoConciliacionErrorDTO() {
    }

    public DetallePagoConciliacionErrorDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetallePagoDTO getDetallePago() {
        return this.detallePago;
    }

    public void setDetallePago(DetallePagoDTO detallePago) {
        this.detallePago = detallePago;
    }

    public ErrorConciliacionPagoDTO getErrorConciliacionPago() {
        return this.errorConciliacionPago;
    }

    public void setErrorConciliacionPago(ErrorConciliacionPagoDTO errorConciliacionPago) {
        this.errorConciliacionPago = errorConciliacionPago;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
