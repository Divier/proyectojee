package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 17:53:44 COT 2016
 */
public class EvaluarImpugnacionBackDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Boolean enviarEspecialista;
    private JustificacionImpugnacionBackDTO justificacionImpugnacionBack;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private Boolean tienePruebas;

    // --- Constructor
    public EvaluarImpugnacionBackDTO() {
    }

    public EvaluarImpugnacionBackDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnviarEspecialista() {
        return this.enviarEspecialista;
    }

    public void setEnviarEspecialista(Boolean enviarEspecialista) {
        this.enviarEspecialista = enviarEspecialista;
    }

    public JustificacionImpugnacionBackDTO getJustificacionImpugnacionBack() {
        return this.justificacionImpugnacionBack;
    }

    public void setJustificacionImpugnacionBack(JustificacionImpugnacionBackDTO justificacionImpugnacionBack) {
        this.justificacionImpugnacionBack = justificacionImpugnacionBack;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return this.trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public Boolean getTienePruebas() {
        return this.tienePruebas;
    }

    public void setTienePruebas(Boolean tienePruebas) {
        this.tienePruebas = tienePruebas;
    }

}
