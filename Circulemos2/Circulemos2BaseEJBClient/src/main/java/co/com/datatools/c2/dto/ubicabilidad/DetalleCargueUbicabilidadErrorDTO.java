package co.com.datatools.c2.dto.ubicabilidad;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 11:58:06 COT 2016
 */
public class DetalleCargueUbicabilidadErrorDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaRegistro;
    private DetalleCargueUbicabilidadDTO detalleCargueUbicabilidad;
    private ErrorCargueUbicabilidadDTO errorCargueUbicabilidad;

    // --- Constructor
    public DetalleCargueUbicabilidadErrorDTO() {
    }

    public DetalleCargueUbicabilidadErrorDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleCargueUbicabilidadDTO getDetalleCargueUbicabilidad() {
        return this.detalleCargueUbicabilidad;
    }

    public void setDetalleCargueUbicabilidad(DetalleCargueUbicabilidadDTO detalleCargueUbicabilidad) {
        this.detalleCargueUbicabilidad = detalleCargueUbicabilidad;
    }

    public ErrorCargueUbicabilidadDTO getErrorCargueUbicabilidad() {
        return this.errorCargueUbicabilidad;
    }

    public void setErrorCargueUbicabilidad(ErrorCargueUbicabilidadDTO errorCargueUbicabilidad) {
        this.errorCargueUbicabilidad = errorCargueUbicabilidad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
