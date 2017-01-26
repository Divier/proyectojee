package co.com.datatools.c2.entidades.personas;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 3 - valida
 */
@Entity
@Table(name = "direccion_perso_fuent_exter")
public class DireccionPersonaFuenteExterna implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion_persona_externa")
    private Long id;

    @Basic(optional = false)
    @Column(name = "fecha_vigencia_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_vigencia_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Direccion direccion;

    @JoinColumn(name = "id_persona_fuente_exter", referencedColumnName = "id_persona_fuente_exter")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonaFuenteExterna personaFuenteExterna;

    public DireccionPersonaFuenteExterna() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public PersonaFuenteExterna getPersonaFuenteExterna() {
        return personaFuenteExterna;
    }

    public void setPersonaFuenteExterna(PersonaFuenteExterna personaFuenteExterna) {
        this.personaFuenteExterna = personaFuenteExterna;
    }

}
