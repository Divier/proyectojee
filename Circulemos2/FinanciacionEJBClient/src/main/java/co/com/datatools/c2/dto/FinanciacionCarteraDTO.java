package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 16 11:02:08 COT 2015
 */
public class FinanciacionCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idFinanciacionCartera;
    private long idCartera;
    private FinanciacionDTO financiacion;

    // --- Constructor
    public FinanciacionCarteraDTO() {
    }

    public FinanciacionCarteraDTO(Long idFinanciacionCartera) {
        this.idFinanciacionCartera = idFinanciacionCartera;

    }

    // --- Getters-Setters
    public Long getIdFinanciacionCartera() {
        return this.idFinanciacionCartera;
    }

    public void setIdFinanciacionCartera(Long idFinanciacionCartera) {
        this.idFinanciacionCartera = idFinanciacionCartera;
    }

    public long getIdCartera() {
        return this.idCartera;
    }

    public void setIdCartera(long idCartera) {
        this.idCartera = idCartera;
    }

    public FinanciacionDTO getFinanciacion() {
        return this.financiacion;
    }

    public void setFinanciacion(FinanciacionDTO financiacion) {
        this.financiacion = financiacion;
    }

}
