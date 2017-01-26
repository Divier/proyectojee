package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Tipos de xsd usados para interpretar el contenido de los XML en las diferentes entidades
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "xsd_historico")
@NamedQueries({ @NamedQuery(name = "XsdHistorico.findAll", query = "SELECT x FROM XsdHistorico x") })
public class XsdHistorico implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_xsd_historico")
    private Integer idXsdHistorico;

    @Lob
    // @Type(type = "org.hibernate.type.TextType")
    @Size(min = 1, max = 65535)
    @Column(name = "contenido_xsd")
    private String contenidoXsd;

    public XsdHistorico() {
    }

    public XsdHistorico(String contenidoXsd) {
        this.setContenidoXsd(contenidoXsd);
    }

    public Integer getIdXsdHistorico() {
        return idXsdHistorico;
    }

    public void setIdXsdHistorico(Integer idXsdHistorico) {
        this.idXsdHistorico = idXsdHistorico;
    }

    public String getContenidoXsd() {
        return contenidoXsd;
    }

    public void setContenidoXsd(String contenidoXsd) {
        this.contenidoXsd = contenidoXsd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdXsdHistorico() != null ? getIdXsdHistorico().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof XsdHistorico)) {
            return false;
        }
        XsdHistorico other = (XsdHistorico) object;
        if ((this.getIdXsdHistorico() == null && other.getIdXsdHistorico() != null)
                || (this.getIdXsdHistorico() != null && !this.getIdXsdHistorico().equals(other.getIdXsdHistorico()))) {
            return false;
        }
        return true;
    }

}
