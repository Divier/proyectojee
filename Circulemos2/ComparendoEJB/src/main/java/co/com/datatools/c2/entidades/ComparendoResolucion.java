package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the comparendo_resolucion database table.
 * 
 */
@Entity
@Table(name = "comparendo_resolucion")
@NamedQuery(name = "ComparendoResolucion.findAll", query = "SELECT c FROM ComparendoResolucion c")
public class ComparendoResolucion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comparendo_resolucion")
    private Long id;

    @Basic(optional = false)
    @Column(name = "id_resolucion")
    private Long idResolucion;

    // bi-directional many-to-one association to Comparendo
    @ManyToOne
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    public ComparendoResolucion() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdResolucion() {
        return this.idResolucion;
    }

    public void setIdResolucion(Long idResolucion) {
        this.idResolucion = idResolucion;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

}