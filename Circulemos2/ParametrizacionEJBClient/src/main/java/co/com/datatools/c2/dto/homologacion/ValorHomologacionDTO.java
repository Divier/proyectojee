package co.com.datatools.c2.dto.homologacion;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:43:19 COT 2015
 */
public class ValorHomologacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String valorFinal;
    private String valorOrigen;
    private TipoHomologacionDTO tipoHomologacion;

    // --- Constructor
    public ValorHomologacionDTO() {
    }

    public ValorHomologacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValorFinal() {
        return this.valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getValorOrigen() {
        return this.valorOrigen;
    }

    public void setValorOrigen(String valorOrigen) {
        this.valorOrigen = valorOrigen;
    }

    public TipoHomologacionDTO getTipoHomologacion() {
        return this.tipoHomologacion;
    }

    public void setTipoHomologacion(TipoHomologacionDTO tipoHomologacion) {
        this.tipoHomologacion = tipoHomologacion;
    }

}
