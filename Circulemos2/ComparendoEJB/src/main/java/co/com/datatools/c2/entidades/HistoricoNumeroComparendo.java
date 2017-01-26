package co.com.datatools.c2.entidades;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "historico_numero_comparendo")
@NamedQueries({ @NamedQuery(
        name = "HistoricoNumeroComparendo.findAll",
        query = "SELECT hnc FROM HistoricoNumeroComparendo hnc") })
public class HistoricoNumeroComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico_numero_comparendo")
    private Long id;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comparendo comparendo;

    @Column(name = "numero_comparendo_antiguo")
    private String numeroComparendoAntiguo;

    @Column(name = "numero_comparendo_nuevo")
    private String numeroComparendoNuevo;

    @Basic(optional = false)
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;

    public HistoricoNumeroComparendo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public String getNumeroComparendoAntiguo() {
        return numeroComparendoAntiguo;
    }

    public void setNumeroComparendoAntiguo(String numeroComparendoAntiguo) {
        this.numeroComparendoAntiguo = numeroComparendoAntiguo;
    }

    public String getNumeroComparendoNuevo() {
        return numeroComparendoNuevo;
    }

    public void setNumeroComparendoNuevo(String numeroComparendoNuevo) {
        this.numeroComparendoNuevo = numeroComparendoNuevo;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

}
