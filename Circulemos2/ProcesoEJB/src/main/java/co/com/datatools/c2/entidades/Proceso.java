package co.com.datatools.c2.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the proceso database table.
 * 
 */
@Entity
@NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
public class Proceso implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proceso")
    private Long id;

    // bi-directional many-to-one association to TipoProceso
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_proceso")
    private TipoProceso tipoProceso;

    @Column(name = "numero_proceso")
    private String numeroProceso;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "observacion")
    private String observacion;

    @OneToMany(mappedBy = "proceso", fetch = FetchType.LAZY)
    private List<TrazabilidadProceso> trazabilidadProceso;

    @OneToMany(mappedBy = "proceso", fetch = FetchType.LAZY)
    private List<ParticipanteProceso> participantesProceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_proceso")
    private EstadoProceso estadoProceso;

    public Proceso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<TrazabilidadProceso> getTrazabilidadProceso() {
        return trazabilidadProceso == null ? new ArrayList<TrazabilidadProceso>() : trazabilidadProceso;
    }

    public void setTrazabilidadProceso(List<TrazabilidadProceso> trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public List<ParticipanteProceso> getParticipantesProceso() {
        return participantesProceso;
    }

    public void setParticipantesProceso(List<ParticipanteProceso> participantesProceso) {
        this.participantesProceso = participantesProceso;
    }

    public EstadoProceso getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EstadoProceso estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

}