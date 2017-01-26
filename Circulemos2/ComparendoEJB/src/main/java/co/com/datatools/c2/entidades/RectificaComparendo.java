package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the rectifica_comparendo database table.
 * 
 */
@Entity
@Table(name = "rectifica_comparendo")
@NamedQuery(name = "RectificaComparendo.findAll", query = "SELECT r FROM RectificaComparendo r")
public class RectificaComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rectifica_comparendo")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    // bi-directional many-to-one association to CampoRectificaComparendo
    @OneToMany(
            mappedBy = "rectificaComparendo",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<CampoRectificaComparendo> campoRectificaComparendos;

    // bi-directional many-to-one association to Comparendo
    @ManyToOne
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    // bi-directional many-to-one association to RectificaEvidencia
    @OneToMany(
            mappedBy = "rectificaComparendo",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<RectificaEvidencia> rectificaEvidencias;

    public RectificaComparendo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<CampoRectificaComparendo> getCampoRectificaComparendos() {
        return this.campoRectificaComparendos;
    }

    public void setCampoRectificaComparendos(List<CampoRectificaComparendo> campoRectificaComparendos) {
        this.campoRectificaComparendos = campoRectificaComparendos;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public List<RectificaEvidencia> getRectificaEvidencias() {
        return rectificaEvidencias;
    }

    public void setRectificaEvidencias(List<RectificaEvidencia> rectificaEvidencias) {
        this.rectificaEvidencias = rectificaEvidencias;
    }

}