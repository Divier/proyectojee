package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the evaluar_impugnacion_back database table.
 * 
 */
@Entity
@Table(name = "evaluar_impugnacion_back")
@NamedQuery(name = "EvaluarImpugnacionBack.findAll", query = "SELECT e FROM EvaluarImpugnacionBack e")
public class EvaluarImpugnacionBack implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluar_impugnacion_back")
    private Long id;

    @Column(name = "enviar_especialista")
    private Boolean enviarEspecialista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_justificacion_impugnacion_back")
    private JustificacionImpugnacionBack justificacionImpugnacionBack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    @Column(name = "tiene_pruebas")
    private Boolean tienePruebas;

    public EvaluarImpugnacionBack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnviarEspecialista() {
        return enviarEspecialista;
    }

    public void setEnviarEspecialista(Boolean enviarEspecialista) {
        this.enviarEspecialista = enviarEspecialista;
    }

    public JustificacionImpugnacionBack getJustificacionImpugnacionBack() {
        return justificacionImpugnacionBack;
    }

    public void setJustificacionImpugnacionBack(JustificacionImpugnacionBack justificacionImpugnacionBack) {
        this.justificacionImpugnacionBack = justificacionImpugnacionBack;
    }

    public TrazabilidadProceso getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProceso trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public Boolean getTienePruebas() {
        return tienePruebas;
    }

    public void setTienePruebas(Boolean tienePruebas) {
        this.tienePruebas = tienePruebas;
    }

}