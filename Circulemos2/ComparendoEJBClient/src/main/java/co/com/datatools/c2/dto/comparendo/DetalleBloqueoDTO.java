package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 07:47:48 COT 2016
 */
public class DetalleBloqueoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private String id;
    private BloqueoComparendoDTO bloqueoComparendo;
    private ErrorProcesamientoDTO errorProcesamiento;
    private CampoEntidadDTO campoEntidad;

    // --- Constructor
    public DetalleBloqueoDTO() {
    }

    public DetalleBloqueoDTO(String id) {
        this.id = id;

    }

    // --- Getters-Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BloqueoComparendoDTO getBloqueoComparendo() {
        return this.bloqueoComparendo;
    }

    public void setBloqueoComparendo(BloqueoComparendoDTO bloqueoComparendo) {
        this.bloqueoComparendo = bloqueoComparendo;
    }

    public ErrorProcesamientoDTO getErrorProcesamiento() {
        return this.errorProcesamiento;
    }

    public void setErrorProcesamiento(ErrorProcesamientoDTO errorProcesamiento) {
        this.errorProcesamiento = errorProcesamiento;
    }

    public CampoEntidadDTO getCampoEntidad() {
        return this.campoEntidad;
    }

    public void setCampoEntidad(CampoEntidadDTO campoEntidad) {
        this.campoEntidad = campoEntidad;
    }
}