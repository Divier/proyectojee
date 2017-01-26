package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:40:38 COT 2016
 */
public class PrecoactivoErrorDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ErrorGeneraCoactivoDTO errorGeneraCoactivo;
    private PrecoactivoDTO precoactivo;

    // --- Constructor
    public PrecoactivoErrorDTO() {
    }

    public PrecoactivoErrorDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ErrorGeneraCoactivoDTO getErrorGeneraCoactivo() {
        return this.errorGeneraCoactivo;
    }

    public void setErrorGeneraCoactivo(ErrorGeneraCoactivoDTO errorGeneraCoactivo) {
        this.errorGeneraCoactivo = errorGeneraCoactivo;
    }

    public PrecoactivoDTO getPrecoactivo() {
        return this.precoactivo;
    }

    public void setPrecoactivo(PrecoactivoDTO precoactivo) {
        this.precoactivo = precoactivo;
    }

}
