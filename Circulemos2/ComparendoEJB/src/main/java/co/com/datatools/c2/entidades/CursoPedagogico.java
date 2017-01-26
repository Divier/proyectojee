package co.com.datatools.c2.entidades;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 10
 * 
 */
@Entity
@Table(name = "curso_pedagogico")
@NamedQuery(name = "CursoPedagogico.findAll", query = "SELECT c FROM CursoPedagogico c")
public class CursoPedagogico implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso_pedagogico")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_curso")
    private Date fechaCurso;

    @Column(name = "numero_comparendo")
    private String numeroComparendo;

    // bi-directional many-to-one association to Persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    // bi-directional many-to-one association to Comparendo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    public CursoPedagogico() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCurso() {
        return this.fechaCurso;
    }

    public void setFechaCurso(Date fechaCurso) {
        this.fechaCurso = fechaCurso;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

}