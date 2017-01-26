package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 15:09:46 COT 2015
 */
public class ValorMapeoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String valorFinal;
    private String valorOrigen;
    private TipoMapeoDTO tipoMapeo;

    // --- Constructor
    public ValorMapeoDTO() {
    }

    public ValorMapeoDTO(Long id) {
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

    public TipoMapeoDTO getTipoMapeo() {
        return this.tipoMapeo;
    }

    public void setTipoMapeo(TipoMapeoDTO tipoMapeo) {
        this.tipoMapeo = tipoMapeo;
    }

}
