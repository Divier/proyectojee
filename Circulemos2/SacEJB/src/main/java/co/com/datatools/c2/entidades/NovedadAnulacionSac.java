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
@Table(name = "novedad_anulacion_sac")
@NamedQuery(name = "NovedadAnulacionSac.findAll", query = "SELECT no FROM NovedadAnulacionSac no")
public class NovedadAnulacionSac implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_novedad_anulacion_sac")
    private Long id;

    @Column(name = "id_proceso")
    private Long idProceso;

    @Column(name = "id_obligacion_sac")
    private Long idObligacionSac;

    @Column(name = "id_cartera")
    private Long idCartera;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_replica_sac")
    private Date fechaReplicaSac;

    public NovedadAnulacionSac() {
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

    public Date getFechaReplicaSac() {
        return fechaReplicaSac;
    }

    public void setFechaReplicaSac(Date fechaReplicaSac) {
        this.fechaReplicaSac = fechaReplicaSac;
    }

}
