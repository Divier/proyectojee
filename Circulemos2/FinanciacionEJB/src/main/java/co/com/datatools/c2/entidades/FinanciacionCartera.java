package co.com.datatools.c2.entidades;

import java.io.Serializable;

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

/**
 * Relacion implicita de financiacion con cartera
 * 
 * @author julio.pinzon 2016-05-18
 */
@Entity
@Table(name = "financiacion_cartera")
@NamedQueries({
        @NamedQuery(name = "FinanciacionCartera.findAll", query = "SELECT c FROM FinanciacionCartera c"),
        @NamedQuery(
                name = "FinanciacionCartera.findByFinanciacion",
                query = "SELECT c FROM FinanciacionCartera c WHERE c.financiacion.id = :pIdFinanciacion"),
        @NamedQuery(
                name = "FinanciacionCartera.findByCartera",
                query = "SELECT c FROM FinanciacionCartera c WHERE c.idCartera = :pIdCartera") })
public class FinanciacionCartera implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * consulta una cartera de un financiacion<br>
     * 
     * SELECT c FROM FinanciacionCartera c WHERE c.financiacion.cifinanciacion = :pIdFinanciacion
     * 
     * @author luis.forero(2016-02-29)
     */
    public static final String SQ_FIND_BY_FINANCIACION = "FinanciacionCartera.findByFinanciacion";
    /**
     * consulta una cartera de un financiacion por su id de cartera<br>
     * 
     * SELECT c FROM FinanciacionCartera c WHERE c.idCartera = :pIdCartera
     * 
     * @author luis.forero(2016-02-29)
     */
    public static final String SQ_FIND_BY_CARTERA = "FinanciacionCartera.findByCartera";

    @Id
    @Basic(optional = false)
    @Column(name = "id_financiacion_cartera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinanciacionCartera;

    @Basic(optional = false)
    @Column(name = "id_cartera")
    private long idCartera;

    @JoinColumn(name = "id_financiacion", referencedColumnName = "id_financiacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Financiacion financiacion;

    public FinanciacionCartera() {
    }

    public FinanciacionCartera(Long idFinanciacionCartera) {
        this.idFinanciacionCartera = idFinanciacionCartera;
    }

    public FinanciacionCartera(Long idFinanciacionCartera, long idCartera) {
        this.idFinanciacionCartera = idFinanciacionCartera;
        this.idCartera = idCartera;
    }

    public Long getIdFinanciacionCartera() {
        return idFinanciacionCartera;
    }

    public void setIdFinanciacionCartera(Long idFinanciacionCartera) {
        this.idFinanciacionCartera = idFinanciacionCartera;
    }

    public long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(long idCartera) {
        this.idCartera = idCartera;
    }

    public Financiacion getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFinanciacionCartera != null ? idFinanciacionCartera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinanciacionCartera)) {
            return false;
        }
        FinanciacionCartera other = (FinanciacionCartera) object;
        if ((this.idFinanciacionCartera == null && other.idFinanciacionCartera != null)
                || (this.idFinanciacionCartera != null && !this.idFinanciacionCartera
                        .equals(other.idFinanciacionCartera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FinanciacionCartera[ idFinanciacionCartera=" + idFinanciacionCartera + " ]";
    }

}
