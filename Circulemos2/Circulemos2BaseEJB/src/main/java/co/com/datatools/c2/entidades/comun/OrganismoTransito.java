package co.com.datatools.c2.entidades.comun;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "organismo_transito")
@NamedQueries({
        @NamedQuery(
                name = "OrganismoTransito.findPais",
                query = "SELECT t.municipio.departamento.pais FROM OrganismoTransito t WHERE t.codigoOrganismo= :codigoOrganismo"),
        // TODO LEFT JOIN FETCH ot.telefonoOrganismoList
        @NamedQuery(
                name = "OrganismoTransito.findByCodigoDetalle",
                query = "SELECT DISTINCT(ot) FROM OrganismoTransito ot LEFT JOIN FETCH ot.municipio AS m LEFT JOIN FETCH m.departamento AS md LEFT JOIN FETCH md.pais LEFT JOIN FETCH ot.departamento AS d LEFT JOIN FETCH d.pais AS dp  WHERE ot.codigoOrganismo = :pCodOrg"),
        @NamedQuery(
                name = "OrganismoTransito.countByCodigo",
                query = "SELECT COUNT(ot) FROM OrganismoTransito ot WHERE ot.codigoOrganismo = :pCodigoOrganismo"),
        @NamedQuery(
                name = "OrganismoTransito.countByNombreOrg",
                query = "SELECT COUNT(ot) FROM OrganismoTransito ot WHERE UPPER(ot.nombreOrganismo) LIKE :pNomOrg AND ot.codigoOrganismo != :pCodigoOrganismo"),
        @NamedQuery(
                name = "OrganismoTransito.countByNombre",
                query = "SELECT COUNT(ot) FROM OrganismoTransito ot WHERE UPPER(ot.nombreOrganismo) LIKE :pNomOrg") })
public class OrganismoTransito implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    public static final String SQ_FIND_PAIS_ORG = "OrganismoTransito.findPais";

    /**
     * Consulta el organismo de transito mediante su codigo
     * <p>
     * <li>pCodOrg Codigo del organismo</li>
     * <p>
     * <br>
     * Consulta:<br>
     * SELECT DISTINCT(ot) FROM OrganismoTransito ot LEFT JOIN FETCH ot.municipio AS m LEFT JOIN FETCH m.departamento AS md LEFT JOIN FETCH md.pais
     * LEFT JOIN FETCH ot.departamento AS d LEFT JOIN FETCH d.pais AS dp LEFT JOIN FETCH ot.telefonoOrganismoList WHERE ot.codigoOrganismo = :pCodOrg
     * 
     * @author luis.forero(2015-01-26)
     */
    public static final String SQ_FIND_BY_COD_ORG = "OrganismoTransito.findByCodigoDetalle";

    /**
     * Consulta que cuenta los organismos con el codigo del parametro <br>
     * <p>
     * <li>pCodigoOrganismo Codigo del organismo</li>
     * <p>
     * <br>
     * Consulta:<br>
     * SELECT COUNT(ot) FROM OrganismoTransito ot WHERE ot.codigoOrganismo= :pCodigoOrganismo
     * 
     * @author luis.forero(2015-01-22)
     */
    public static final String SQ_COUNT_BY_CODIGO = "OrganismoTransito.countByCodigo";
    /**
     * Consulta que cuenta los nombres de los organismos de transito teniendo en cuenta que no sea un codigo determinado
     * <p>
     * <li>pCodigoOrganismo Codigo del organismo</li>
     * <li>pNomOrg Nombre del organismo</li>
     * <p>
     * <br>
     * Consulta:<br>
     * SELECT COUNT(ot) FROM OrganismoTransito ot WHERE UPPER(ot.nombreOrganismo) LIKE :pNomOrg AND ot.codigoOrganismo != :pCodigoOrganismo
     * 
     * @author luis.forero(2015-01-22)
     */
    public static final String SQ_COUNT_BY_NOMBRE_ORG = "OrganismoTransito.countByNombreOrg";
    /**
     * Consulta que cuenta los nombres de los organismos de transito
     * <p>
     * <li>pNomOrg Nombre del organismo</li>
     * <p>
     * <br>
     * Consulta:<br>
     * SELECT COUNT(ot) FROM OrganismoTransito ot WHERE UPPER(ot.nombreOrganismo) LIKE :pNomOrg
     * 
     * @author luis.forero(2015-01-22)
     */
    public static final String SQ_COUNT_BY_NOMBRE = "OrganismoTransito.countByNombre";

    @Id
    @Column(name = "codigo_organismo")
    private Integer codigoOrganismo;

    @Basic(optional = false)
    @Column(name = "nombre_organismo")
    private String nombreOrganismo;

    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipio;

    @Column(name = "nit")
    private String nit;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "codigo_runt")
    private String codigoRunt;

    @Column(name = "codigo_ministerio")
    private String codigoMinisterio;

    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_organismo_padre")
    private OrganismoTransito organismoTransitoPadre;

    @OneToMany(mappedBy = "organismoTransito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DireccionOrganismo> direccionOrganismo;

    public OrganismoTransito() {
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNombreOrganismo() {
        return this.nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCodigoRunt() {
        return codigoRunt;
    }

    public void setCodigoRunt(String codigoRunt) {
        this.codigoRunt = codigoRunt;
    }

    public String getCodigoMinisterio() {
        return codigoMinisterio;
    }

    public void setCodigoMinisterio(String codigoMinisterio) {
        this.codigoMinisterio = codigoMinisterio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<DireccionOrganismo> getDireccionOrganismo() {
        return direccionOrganismo;
    }

    public void setDireccionOrganismo(List<DireccionOrganismo> direccionOrganismo) {
        this.direccionOrganismo = direccionOrganismo;
    }

    public OrganismoTransito getOrganismoTransitoPadre() {
        return organismoTransitoPadre;
    }

    public void setOrganismoTransitoPadre(OrganismoTransito organismoTransitoPadre) {
        this.organismoTransitoPadre = organismoTransitoPadre;
    }

}