package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
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

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the financiacion_proceso database table.
 * 
 */
@Entity
@Table(name = "financiacion_proceso")
@NamedQuery(name = "FinanciacionProceso.findAll", query = "SELECT f FROM FinanciacionProceso f")
public class FinanciacionProceso implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financiacion_proceso")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "id_proceso")
    private long idProceso;

    // bi-directional many-to-one association to Financiacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_financiacion")
    private Financiacion financiacion;

    public FinanciacionProceso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(long idProceso) {
        this.idProceso = idProceso;
    }

    public Financiacion getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
    }

}