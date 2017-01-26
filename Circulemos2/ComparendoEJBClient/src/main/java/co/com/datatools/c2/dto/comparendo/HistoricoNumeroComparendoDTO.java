package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 14 11:49:31 COT 2016
 */
public class HistoricoNumeroComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ComparendoDTO comparendo;
    private String numeroComparendoAntiguo;
    private String numeroComparendoNuevo;
    private Date fechaCambio;

    // --- Constructor
    public HistoricoNumeroComparendoDTO() {
    }

    public HistoricoNumeroComparendoDTO(Long id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparendoDTO getComparendo() {
        return comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public String getNumeroComparendoAntiguo() {
        return numeroComparendoAntiguo;
    }

    public void setNumeroComparendoAntiguo(String numeroComparendoAntiguo) {
        this.numeroComparendoAntiguo = numeroComparendoAntiguo;
    }

    public String getNumeroComparendoNuevo() {
        return numeroComparendoNuevo;
    }

    public void setNumeroComparendoNuevo(String numeroComparendoNuevo) {
        this.numeroComparendoNuevo = numeroComparendoNuevo;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    // --- Getters-Setters

}
