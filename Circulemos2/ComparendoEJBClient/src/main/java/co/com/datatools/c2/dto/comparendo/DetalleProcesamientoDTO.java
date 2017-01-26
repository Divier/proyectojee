package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:35:31 COT 2015
 */
public class DetalleProcesamientoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private CampoEntidadDTO campoEntidad;
    private ErrorProcesamientoDTO errorProcesamiento;
    private ProcesaComparendoDTO procesaComparendo;

    // --- Constructor
    public DetalleProcesamientoDTO() {
    }

    public DetalleProcesamientoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CampoEntidadDTO getCampoEntidad() {
        return this.campoEntidad;
    }

    public void setCampoEntidad(CampoEntidadDTO campoEntidad) {
        this.campoEntidad = campoEntidad;
    }

    public ErrorProcesamientoDTO getErrorProcesamiento() {
        return this.errorProcesamiento;
    }

    public void setErrorProcesamiento(ErrorProcesamientoDTO errorProcesamiento) {
        this.errorProcesamiento = errorProcesamiento;
    }

    public ProcesaComparendoDTO getProcesaComparendo() {
        return this.procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendoDTO procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

}
