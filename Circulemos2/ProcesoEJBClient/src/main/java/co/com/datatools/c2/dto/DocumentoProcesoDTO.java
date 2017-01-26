package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 15:56:32 COT 2016
 */
public class DocumentoProcesoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long numeroDocumento;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private Integer idComparendoProceso;
    private TipoDocumentoProcesoDTO tipoDocumento;
    private FirmaPersonaDTO firma;
    private String responsableGeneracion;

    // --- Constructor
    public DocumentoProcesoDTO() {
    }

    public DocumentoProcesoDTO(Long id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public Integer getIdComparendoProceso() {
        return idComparendoProceso;
    }

    public void setIdComparendoProceso(Integer idComparendoProceso) {
        this.idComparendoProceso = idComparendoProceso;
    }

    public TipoDocumentoProcesoDTO getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoProcesoDTO tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public FirmaPersonaDTO getFirma() {
        return firma;
    }

    public void setFirma(FirmaPersonaDTO firma) {
        this.firma = firma;
    }

    public String getResponsableGeneracion() {
        return responsableGeneracion;
    }

    public void setResponsableGeneracion(String responsableGeneracion) {
        this.responsableGeneracion = responsableGeneracion;
    }

    // --- Getters-Setters

}
