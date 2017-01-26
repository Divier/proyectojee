package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "novedad_impugnacion_sac")
@NamedQuery(name = "NovedadImpugnacionSac.findAll", query = "SELECT no FROM NovedadImpugnacionSac no")
public class NovedadImpugnacionSac implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_novedad_impugnacion_sac")
    private Long id;

    @Column(name = "id_proceso")
    private Long idProceso;

    @Column(name = "id_obligacion_sac")
    private Long idObligacionSac;

    @Column(name = "id_cartera")
    private Long idCartera;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_replica_apertura")
    private Date fechaReplicaApertura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_replica_fallo")
    private Date fechaReplicaFallo;

    public NovedadImpugnacionSac() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Long getIdObligacionSac() {
        return idObligacionSac;
    }

    public void setIdObligacionSac(Long idObligacionSac) {
        this.idObligacionSac = idObligacionSac;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Date getFechaReplicaApertura() {
        return fechaReplicaApertura;
    }

    public void setFechaReplicaApertura(Date fechaReplicaApertura) {
        this.fechaReplicaApertura = fechaReplicaApertura;
    }

    public Date getFechaReplicaFallo() {
        return fechaReplicaFallo;
    }

    public void setFechaReplicaFallo(Date fechaReplicaFallo) {
        this.fechaReplicaFallo = fechaReplicaFallo;
    }

}
