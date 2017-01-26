package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "tipo_email")
@NamedQueries({
        @NamedQuery(name = "TipoEmail.findAll", query = "SELECT t FROM TipoEmail t"),
        @NamedQuery(
                name = "TipoEmail.findByCodOrganismoTransito",
                query = "SELECT DISTINCT(t) FROM TipoEmail t LEFT JOIN FETCH t.organismoTransitoList o WHERE o.codigoOrganismo = :pCodOrg ORDER BY t.codigo") })
public class TipoEmail implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * SELECT DISTINCT(t) FROM TipoEmail t LEFT JOIN FETCH t.organismoTransitoList o WHERE o.codigoOrganismo = :pCodOrg ORDER BY t.codigo
     */
    public static final String SQ_FIND_BY_COD_ORGANISMO = "TipoEmail.findByCodOrganismoTransito";

    @Id
    @Basic(optional = false)
    @Column(name = "codigo_tipo_email")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre_tipo_email")
    private String nombre;

    @JoinTable(name = "tipo_comunicacion_organismo", joinColumns = { @JoinColumn(
            name = "codigo_tipo_email",
            referencedColumnName = "codigo_tipo_email") }, inverseJoinColumns = { @JoinColumn(
            name = "codigo_organismo",
            referencedColumnName = "codigo_organismo") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<OrganismoTransito> organismoTransitoList;

    public TipoEmail() {
    }

    public TipoEmail(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OrganismoTransito> getOrganismoTransitoList() {
        return organismoTransitoList;
    }

    public void setOrganismoTransitoList(List<OrganismoTransito> organismoTransitoList) {
        this.organismoTransitoList = organismoTransitoList;
    }

}
