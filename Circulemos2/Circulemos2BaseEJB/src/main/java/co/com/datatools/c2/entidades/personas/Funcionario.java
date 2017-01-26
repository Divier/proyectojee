package co.com.datatools.c2.entidades.personas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
public class Funcionario implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer id;

    @Column(name = "codigo_proceso")
    private Integer codigoProceso;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_final_vigencia")
    private Date fechaFinalVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio_vigencia")
    private Date fechaInicioVigencia;

    @Column(name = "id_cargo")
    private Integer idCargo;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    public Funcionario() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoProceso() {
        return this.codigoProceso;
    }

    public void setCodigoProceso(Integer codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public Date getFechaFinalVigencia() {
        return this.fechaFinalVigencia;
    }

    public void setFechaFinalVigencia(Date fechaFinalVigencia) {
        this.fechaFinalVigencia = fechaFinalVigencia;
    }

    public Date getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Integer getIdCargo() {
        return this.idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}