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
 * The persistent class for the pago_comparendo database table.
 * 
 */
@Entity
@Table(name = "pago_comparendo")
@NamedQuery(name = "PagoComparendo.findAll", query = "SELECT p FROM PagoComparendo p")
public class PagoComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_comparendo")
    private Long id;

    @Basic(optional = false)
    @Column(name = "id_pago")
    private Long idPago;

    // bi-directional many-to-one association to Comparendo
    @ManyToOne
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    public PagoComparendo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPago() {
        return this.idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

}