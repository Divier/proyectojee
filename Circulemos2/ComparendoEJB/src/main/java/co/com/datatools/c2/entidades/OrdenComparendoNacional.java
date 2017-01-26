package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "orden_comparendo_nacional")
public class OrdenComparendoNacional implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cicomparendo")
    private Long cicomparendo;

    @Column(name = "numero_comparendo")
    private String numeroComparendo;

    @OneToOne(mappedBy = "ordenComparendoNacional")
    private Comparendo comparendo;

    @ManyToOne
    @JoinColumn(name = "codigo_organismo_transito")
    private OrganismoTransito organismoTransito;

    public OrdenComparendoNacional() {
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}