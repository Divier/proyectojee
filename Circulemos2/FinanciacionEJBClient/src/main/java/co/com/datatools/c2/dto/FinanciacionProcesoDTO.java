package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Jun 14 10:12:47 COT 2016
 */
public class FinanciacionProcesoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Long idProceso;
    private FinanciacionDTO financiacion;

    // --- Constructor
    public FinanciacionProcesoDTO() {
    }

    public FinanciacionProcesoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdProceso() {
        return this.idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public FinanciacionDTO getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(FinanciacionDTO financiacion) {
        this.financiacion = financiacion;
    }

}
