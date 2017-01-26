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
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "comparendo_cartera")
@NamedQueries({ @NamedQuery(name = "ComparendoCartera.findAll", query = "SELECT c FROM ComparendoCartera c"),
        @NamedQuery(
                name = "ComparendoCartera.findByComparendo",
                query = "SELECT c FROM ComparendoCartera c WHERE c.comparendo.cicomparendo = :pCicomp"),
        @NamedQuery(
                name = "ComparendoCartera.findByCartera",
                query = "SELECT c FROM ComparendoCartera c WHERE c.idCartera = :pIdCartera") })
public class ComparendoCartera implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * consulta una cartera de un comparendo<br>
     * 
     * SELECT c FROM ComparendoCartera c WHERE c.comparendo.cicomparendo = :pCicomp
     * 
     * @author luis.forero(2016-02-29)
     */
    public static final String SQ_FIND_BY_COMPARENDO = "ComparendoCartera.findByComparendo";
    /**
     * consulta una cartera de un comparendo por su id de cartera<br>
     * 
     * SELECT c FROM ComparendoCartera c WHERE c.idCartera = :pIdCartera
     * 
     * @author luis.forero(2016-02-29)
     */
    public static final String SQ_FIND_BY_CARTERA = "ComparendoCartera.findByCartera";

    @Id
    @Basic(optional = false)
    @Column(name = "id_comparendo_cartera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComparendoCartera;

    @Basic(optional = false)
    @Column(name = "id_cartera")
    private long idCartera;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Comparendo comparendo;

    public ComparendoCartera() {
    }

    public ComparendoCartera(Long idComparendoCartera) {
        this.idComparendoCartera = idComparendoCartera;
    }

    public ComparendoCartera(Long idComparendoCartera, long idCartera) {
        this.idComparendoCartera = idComparendoCartera;
        this.idCartera = idCartera;
    }

    public Long getIdComparendoCartera() {
        return idComparendoCartera;
    }

    public void setIdComparendoCartera(Long idComparendoCartera) {
        this.idComparendoCartera = idComparendoCartera;
    }

    public long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(long idCartera) {
        this.idCartera = idCartera;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComparendoCartera != null ? idComparendoCartera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComparendoCartera)) {
            return false;
        }
        ComparendoCartera other = (ComparendoCartera) object;
        if ((this.idComparendoCartera == null && other.idComparendoCartera != null)
                || (this.idComparendoCartera != null && !this.idComparendoCartera.equals(other.idComparendoCartera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ComparendoCartera[ idComparendoCartera=" + idComparendoCartera + " ]";
    }

}
