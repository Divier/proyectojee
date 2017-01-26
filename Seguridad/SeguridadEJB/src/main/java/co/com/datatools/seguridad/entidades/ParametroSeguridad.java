package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Parametros configurados en la aplicacion Nombre - Valor
 * 
 * @author Felipe Martinez <br>
 *         rodrigo.cruz (mod: 21/07/2015) - Auditoria
 */
@Entity
@Table(name = "parametro_seguridad")
// @Audited
@NamedQuery(name = "ParametroSeguridad.findAll", query = "SELECT p FROM ParametroSeguridad p")
public class ParametroSeguridad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro_seguridad")
    private Integer idParametroSeguridad;

    @Column(name = "nombre_parametro_seguridad")
    private String nombreParametroSeguridad;

    @Lob
    // @Type(type = "org.hibernate.type.TextType")
    @Column(name = "valor_parametro_seguridad")
    private String valorParametroSeguridad;

    // @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_dato")
    private TipoDato tipoDato;

    @Column(name = "editable")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean editable;

    public ParametroSeguridad() {
    }

    public Integer getIdParametroSeguridad() {
        return idParametroSeguridad;
    }

    public void setIdParametroSeguridad(Integer idParametroSeguridad) {
        this.idParametroSeguridad = idParametroSeguridad;
    }

    public String getNombreParametroSeguridad() {
        return nombreParametroSeguridad;
    }

    public void setNombreParametroSeguridad(String nombreParametroSeguridad) {
        this.nombreParametroSeguridad = nombreParametroSeguridad;
    }

    public String getValorParametroSeguridad() {
        return valorParametroSeguridad;
    }

    public void setValorParametroSeguridad(String valorParametroSeguridad) {
        this.valorParametroSeguridad = valorParametroSeguridad;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroSeguridad != null ? idParametroSeguridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ParametroSeguridad)) {
            return false;
        }
        ParametroSeguridad other = (ParametroSeguridad) object;
        if ((this.idParametroSeguridad == null && other.idParametroSeguridad != null)
                || (this.idParametroSeguridad != null && !this.idParametroSeguridad.equals(other.idParametroSeguridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParametroSeguridad {idParametroSeguridad: " + getIdParametroSeguridad()
                + ", nombreParametroSeguridad: " + getNombreParametroSeguridad() + ", valorParametroSeguridad: "
                + getValorParametroSeguridad() + "}";
    }

}