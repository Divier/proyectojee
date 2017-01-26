package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
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
 * The persistent class for the documento_proceso database table.
 * 
 */
@Entity
@Table(name = "evaluar_impugnacion")
@NamedQuery(name = "EvaluarImpugnacion.findAll", query = "SELECT d FROM EvaluarImpugnacion d")
public class EvaluarImpugnacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluar_impugnacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    @Basic(optional = false)
    @Column(name = "solucion_inmediata")
    private Boolean solucionInmediata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_justificacion_impugnacion")
    private JustificacionImpugnacion justificacionImpugnacion;

    @Column(name = "consideracion_juridica")
    private String consideracionJuridica;

    public EvaluarImpugnacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProceso getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProceso trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public Boolean getSolucionInmediata() {
        return solucionInmediata;
    }

    public void setSolucionInmediata(Boolean solucionInmediata) {
        this.solucionInmediata = solucionInmediata;
    }

    public JustificacionImpugnacion getJustificacionImpugnacion() {
        return justificacionImpugnacion;
    }

    public void setJustificacionImpugnacion(JustificacionImpugnacion justificacionImpugnacion) {
        this.justificacionImpugnacion = justificacionImpugnacion;
    }

    public String getConsideracionJuridica() {
        return consideracionJuridica;
    }

    public void setConsideracionJuridica(String consideracionJuridica) {
        this.consideracionJuridica = consideracionJuridica;
    }

}