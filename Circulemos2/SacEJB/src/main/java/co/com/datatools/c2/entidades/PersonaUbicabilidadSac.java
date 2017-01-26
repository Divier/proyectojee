package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
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

/**
 * The persistent class for the persona_ubicabilidad_sac database table.
 * 
 */
@Entity
@Table(name = "persona_ubicabilidad_sac")
@NamedQuery(name = "PersonaUbicabilidadSac.findAll", query = "SELECT p FROM PersonaUbicabilidadSac p")
public class PersonaUbicabilidadSac implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_ubicabilidad_sac")
    private Long id;

    @Basic(optional = false)
    @Column(name = "id_persona_historico")
    private Long idPersonaHistorico;

    @Basic(optional = false)
    @Column(name = "id_ubicabilidad_sac")
    private Long idUbicabilidadSac;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_replica_sac")
    private Date fechaHoraReplicaSac;

    public PersonaUbicabilidadSac() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersonaHistorico() {
        return idPersonaHistorico;
    }

    public void setIdPersonaHistorico(Long idPersonaHistorico) {
        this.idPersonaHistorico = idPersonaHistorico;
    }

    public Long getIdUbicabilidadSac() {
        return idUbicabilidadSac;
    }

    public void setIdUbicabilidadSac(Long idUbicabilidadSac) {
        this.idUbicabilidadSac = idUbicabilidadSac;
    }

    public Date getFechaHoraReplicaSac() {
        return fechaHoraReplicaSac;
    }

    public void setFechaHoraReplicaSac(Date fechaHoraReplicaSac) {
        this.fechaHoraReplicaSac = fechaHoraReplicaSac;
    }
}