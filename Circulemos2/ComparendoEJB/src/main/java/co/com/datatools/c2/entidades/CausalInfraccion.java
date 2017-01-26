package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "causal_infraccion")
@NamedQueries({
        @NamedQuery(name = "CausalInfraccion.findAll", query = "SELECT c FROM CausalInfraccion c"),
        @NamedQuery(
                name = "CausalInfraccion.consultaCausalesInfraccionXOrganismo",
                query = "SELECT ci FROM CausalInfraccion ci ORDER BY ci.nombre"),
        @NamedQuery(
                name = "CausalInfraccion.findLastIdCodeByOrganismo",
                query = "SELECT MAX(ci.id) FROM CausalInfraccion AS ci"),
        @NamedQuery(
                name = "CausalInfraccion.countCausalByOrganismoAndNombre",
                query = "SELECT COUNT(ci) FROM CausalInfraccion AS ci WHERE UPPER(ci.nombre) LIKE :pNombre") })
public class CausalInfraccion implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    /**
     * SELECT ci FROM CausalInfraccion AS ci WHERE ci.organismoTransito.codigoOrganismo = :pCodOrganismo ORDER BY ci.nombre
     */
    public static final String CONSULTA_CAUSALES_INFR_X_ORGANISMO = "CausalInfraccion.consultaCausalesInfraccionXOrganismo";

    /**
     * SELECT MAX(ci.id) FROM CausalInfraccion AS ci WHERE ci.organismoTransito.codigoOrganismo = :pCodOrganismo
     */
    public static final String SQ_LAST_ID_CODE_BY_ORGANISMO = "CausalInfraccion.findLastIdCodeByOrganismo";

    /**
     * SELECT COUNT(ci) FROM CausalInfraccion AS ci WHERE ci.organismoTransito.codigoOrganismo = :pCodOrganismo AND UPPER(ci.Nombre) LIKE :pNombre
     */
    public static final String SQ_COUNT_BY_ORGANISMO_NOMBRE = "CausalInfraccion.countCausalByOrganismoAndNombre";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_causal_infraccion")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "sigla")
    private String sigla;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    public CausalInfraccion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String getSigla() {
        return this.sigla;
    }

    @Override
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public Boolean getActivo() {
        return this.estado;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

}
