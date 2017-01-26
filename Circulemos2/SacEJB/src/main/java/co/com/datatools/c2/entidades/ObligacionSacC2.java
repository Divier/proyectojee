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
@Table(name = "obligacion_sac_c2")
@NamedQuery(name = "ObligacionSacC2.findAll", query = "SELECT o FROM ObligacionSacC2 o")
public class ObligacionSacC2 implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obligacion_sac_c2")
    private Long id;

    @Column(name = "id_cartera")
    private Long idCartera;

    @Column(name = "id_obligacion_sac")
    private Long idObligacionSac;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_replica_sac")
    private Date fechaHoraReplicaSac;

    public ObligacionSacC2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Long getIdObligacionSac() {
        return idObligacionSac;
    }

    public void setIdObligacionSac(Long idObligacionSac) {
        this.idObligacionSac = idObligacionSac;
    }

    public Date getFechaHoraReplicaSac() {
        return fechaHoraReplicaSac;
    }

    public void setFechaHoraReplicaSac(Date fechaHoraReplicaSac) {
        this.fechaHoraReplicaSac = fechaHoraReplicaSac;
    }

}
