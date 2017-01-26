package co.com.datatools.datatimer.c2.entidades;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the job database table.
 * 
 */
@Entity
@Table(name = "job")
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "grupo")
    private String grupo;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private List<TriggerJob> triggerJobList;

    @JoinColumn(name = "codigo_estado_job", referencedColumnName = "codigo_estado_job")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoJob estadoJob;

    public Job() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public EstadoJob getEstadoJob() {
        return estadoJob;
    }

    public void setEstadoJob(EstadoJob estadoJob) {
        this.estadoJob = estadoJob;
    }

    public List<TriggerJob> getTriggerJobList() {
        return triggerJobList;
    }

    public void setTriggerJobList(List<TriggerJob> triggerJobList) {
        this.triggerJobList = triggerJobList;
    }
}