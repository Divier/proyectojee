package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Catalogo de tipos de datos de los parametros de la aplicacion
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "tipo_dato")
@NamedQuery(name = "TipoDato.findAll", query = "SELECT td FROM TipoDato td")
public class TipoDato implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_TIPODATO_ALL = "TipoDato.findAll";

    @Id
    @Column(name = "id_tipo_dato")
    private Integer idTipoDato;

    @Column(name = "nombre_tipo_dato")
    private String nombreTipoDato;

    public TipoDato() {
    }

    public TipoDato(Integer idTipoDato) {
        this.idTipoDato = idTipoDato;
    }

    public TipoDato(Integer idTipoDato, String nombreTipoDato) {
        this.idTipoDato = idTipoDato;
        this.nombreTipoDato = nombreTipoDato;
    }

    public Integer getIdTipoDato() {
        return idTipoDato;
    }

    public void setIdTipoDato(Integer idTipoDato) {
        this.idTipoDato = idTipoDato;
    }

    public String getNombreTipoDato() {
        return nombreTipoDato;
    }

    public void setNombreTipoDato(String nombreTipoDato) {
        this.nombreTipoDato = nombreTipoDato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdTipoDato() != null ? getIdTipoDato().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TipoDato)) {
            return false;
        }
        TipoDato other = (TipoDato) object;
        if ((this.getIdTipoDato() == null && other.getIdTipoDato() != null)
                || (this.getIdTipoDato() != null && !this.getIdTipoDato().equals(other.getIdTipoDato()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoDato {idTipoDato: " + getIdTipoDato() + ", nombreTipoDato: " + getNombreTipoDato() + "}";
    }

}
