package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:02:38 COT 2016
 */
public class EvaluarImpugnacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private Boolean solucionInmediata;
    private JustificacionImpugnacionDTO justificacionImpugnacion;
    private String consideracionJuridica;
    private String codigoPlantilla;

    // --- Constructor
    public EvaluarImpugnacionDTO() {
    }

    public EvaluarImpugnacionDTO(Long id) {
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

    public Boolean getSolucionInmediata() {
        return this.solucionInmediata;
    }

    public void setSolucionInmediata(Boolean solucionInmediata) {
        this.solucionInmediata = solucionInmediata;
    }

    public JustificacionImpugnacionDTO getJustificacionImpugnacion() {
        return this.justificacionImpugnacion;
    }

    public void setJustificacionImpugnacion(JustificacionImpugnacionDTO justificacionImpugnacion) {
        this.justificacionImpugnacion = justificacionImpugnacion;
    }

    public String getConsideracionJuridica() {
        return consideracionJuridica;
    }

    public void setConsideracionJuridica(String consideracionJuridica) {
        this.consideracionJuridica = consideracionJuridica;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

}
