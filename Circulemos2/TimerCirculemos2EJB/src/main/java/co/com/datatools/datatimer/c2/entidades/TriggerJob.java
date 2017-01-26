package co.com.datatools.datatimer.c2.entidades;

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
 * The persistent class for the job database table.
 * 
 */
@Entity
@Table(name = "trigger_job")
@NamedQuery(name = "TriggerJob.findAll", query = "SELECT tj FROM TriggerJob tj")
public class TriggerJob implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trigger")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre_trigger")
    private String nombreTrigger;

    @Basic(optional = false)
    @Column(name = "grupo_trigger")
    private String grupoTrigger;

    @Basic(optional = false)
    @Column(name = "es_automatico")
    private Boolean esAutomatico;

    @Column(name = "expresion_cron")
    private String expresionCron;

    @JoinColumn(name = "id_job", referencedColumnName = "id_job")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Job job;

    @JoinColumn(name = "codigo_estado_trigger", referencedColumnName = "codigo_estado_trigger")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoTrigger estadoTrigger;

    public TriggerJob() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTrigger() {
        return nombreTrigger;
    }

    public void setNombreTrigger(String nombreTrigger) {
        this.nombreTrigger = nombreTrigger;
    }

    public String getGrupoTrigger() {
        return grupoTrigger;
    }

    public void setGrupoTrigger(String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }

    public Boolean getEsAutomatico() {
        return esAutomatico;
    }

    public void setEsAutomatico(Boolean esAutomatico) {
        this.esAutomatico = esAutomatico;
    }

    public String getExpresionCron() {
        return expresionCron;
    }

    public void setExpresionCron(String expresionCron) {
        this.expresionCron = expresionCron;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public EstadoTrigger getEstadoTrigger() {
        return estadoTrigger;
    }

    public void setEstadoTrigger(EstadoTrigger estadoTrigger) {
        this.estadoTrigger = estadoTrigger;
    }
}