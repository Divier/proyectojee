package co.com.datatools.datatimer.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the estado_job database table.
 * 
 */
@Entity
@Table(name = "estado_job")
@NamedQuery(name = "EstadoJob.findAll", query = "SELECT ej FROM EstadoJob ej")
public class EstadoJob implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_estado_job")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre_estado_job")
    private String nombreEstadoJob;

    public EstadoJob() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEstadoJob() {
        return nombreEstadoJob;
    }

    public void setNombreEstadoJob(String nombreEstadoJob) {
        this.nombreEstadoJob = nombreEstadoJob;
    }
}