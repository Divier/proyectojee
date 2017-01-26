package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:40:44 COT 2016
 */
public class DetalleCargueCourierErrorDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private DetalleCargueCourierDTO detalleCargueCourier;
    private ErrorCargueCourierDTO errorCargueCourier;

    // --- Constructor
    public DetalleCargueCourierErrorDTO() {
    }

    public DetalleCargueCourierErrorDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleCargueCourierDTO getDetalleCargueCourier() {
        return this.detalleCargueCourier;
    }

    public void setDetalleCargueCourier(DetalleCargueCourierDTO detalleCargueCourier) {
        this.detalleCargueCourier = detalleCargueCourier;
    }

    public ErrorCargueCourierDTO getErrorCargueCourier() {
        return this.errorCargueCourier;
    }

    public void setErrorCargueCourier(ErrorCargueCourierDTO errorCargueCourier) {
        this.errorCargueCourier = errorCargueCourier;
    }

}
