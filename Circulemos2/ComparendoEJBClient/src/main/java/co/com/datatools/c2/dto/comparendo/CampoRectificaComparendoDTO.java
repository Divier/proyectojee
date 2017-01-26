package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 16:00:25 COT 2016
 */
public class CampoRectificaComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String valorNuevo;
    private String valorOriginal;
    private RectificaComparendoDTO rectificaComparendo;
    private CampoEntidadDTO campoEntidad;

    // --- Constructor
    public CampoRectificaComparendoDTO() {
    }

    public CampoRectificaComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValorNuevo() {
        return this.valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getValorOriginal() {
        return this.valorOriginal;
    }

    public void setValorOriginal(String valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public RectificaComparendoDTO getRectificaComparendo() {
        return this.rectificaComparendo;
    }

    public void setRectificaComparendo(RectificaComparendoDTO rectificaComparendo) {
        this.rectificaComparendo = rectificaComparendo;
    }

    public CampoEntidadDTO getCampoEntidad() {
        return this.campoEntidad;
    }

    public void setCampoEntidad(CampoEntidadDTO campoEntidad) {
        this.campoEntidad = campoEntidad;
    }

}
