package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:03:33 COT 2016
 */
public class RechazoImpugnacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private TipoRechazoDTO tipoRechazo;
    private String observacion;

    // --- Constructor
    public RechazoImpugnacionDTO() {
    }

    public RechazoImpugnacionDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return this.trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public TipoRechazoDTO getTipoRechazo() {
        return tipoRechazo;
    }

    public void setTipoRechazo(TipoRechazoDTO tipoRechazo) {
        this.tipoRechazo = tipoRechazo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}