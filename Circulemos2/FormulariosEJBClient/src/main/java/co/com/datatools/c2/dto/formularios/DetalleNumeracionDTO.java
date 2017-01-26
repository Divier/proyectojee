package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 17:38:33 COT 2015
 */
public class DetalleNumeracionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long digito;
    private Character caracterInicio;
    private Character caracterFin;
    private Boolean incremental;
    private Boolean numerico;
    private NumeracionFormularioDTO numeracion;

    // --- Constructor
    public DetalleNumeracionDTO() {
    }

    public DetalleNumeracionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDigito() {
        return this.digito;
    }

    public void setDigito(Long digito) {
        this.digito = digito;
    }

    public Character getCaracterInicio() {
        return this.caracterInicio;
    }

    public void setCaracterInicio(Character caracterInicio) {
        this.caracterInicio = caracterInicio;
    }

    public Character getCaracterFin() {
        return this.caracterFin;
    }

    public void setCaracterFin(Character caracterFin) {
        this.caracterFin = caracterFin;
    }

    public Boolean getIncremental() {
        return this.incremental;
    }

    public void setIncremental(Boolean incremental) {
        this.incremental = incremental;
    }

    public Boolean getNumerico() {
        return this.numerico;
    }

    public void setNumerico(Boolean numerico) {
        this.numerico = numerico;
    }

    public NumeracionFormularioDTO getNumeracion() {
        return this.numeracion;
    }

    public void setNumeracion(NumeracionFormularioDTO numeracion) {
        this.numeracion = numeracion;
    }

}
