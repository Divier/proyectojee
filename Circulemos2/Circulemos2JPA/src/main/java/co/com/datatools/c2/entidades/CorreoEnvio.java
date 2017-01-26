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
@Table(name = "correo_envio")
@NamedQueries({
        @NamedQuery(
                name = "CorreoEnvio.findByCodOrg",
                query = "SELECT DISTINCT(ce) FROM CorreoEnvio ce LEFT JOIN FETCH ce.organismoTransito o WHERE o.codigoOrganismo = :pCodOrg"),
        @NamedQuery(
                name = "CorreoEnvio.findByCodOrgAndCorreo",
                query = "SELECT DISTINCT(ce) FROM CorreoEnvio ce LEFT JOIN FETCH ce.organismoTransito o WHERE o.codigoOrganismo = :pCodOrg AND LOWER(ce.email) LIKE :pEmail"),
        @NamedQuery(
                name = "CorreoEnvio.countByIdCorreoEnvAndEmail",
                query = "SELECT COUNT(ce) FROM CorreoEnvio ce WHERE ce.organismoTransito.codigoOrganismo = :pCodOrg AND ce.configuracionEmail.id = :pIdCnfE AND ce.email = :pEmail"),
        @NamedQuery(
                name = "CorreoEnvio.findByIdCorreoEnvAndConfig",
                query = "SELECT DISTINCT(ce) FROM CorreoEnvio ce WHERE ce.organismoTransito.codigoOrganismo = :pCodOrg AND ce.configuracionEmail.id = :pIdCnfE") })
public class CorreoEnvio implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * SELECT DISTINCT(ce) FROM CorreoEnvio ce LEFT JOIN FETCH ce.organismoTransito o WHERE o.codigoOrganismo = :pCodOrg
     */
    public static final String SQ_BY_COD_ORGANISMO = "CorreoEnvio.findByCodOrg";
    /**
     * SELECT DISTINCT(ce) FROM CorreoEnvio ce LEFT JOIN FETCH ce.organismoTransito o WHERE o.codigoOrganismo = :pCodOrg AND LOWER(ce.email) LIKE
     * :pEmail
     */
    public static final String SQ_BY_COD_ORG_AND_CORREO = "CorreoEnvio.findByCodOrgAndCorreo";
    /**
     * SELECT COUNT(ce) FROM CorreoEnvio ce WHERE ce.organismoTransito.codigoOrganismo = :pCodOrg AND ce.configuracionEmail.id = :pIdCnfE AND ce.email
     * = :pEmail
     */
    public static final String SQ_COUNT_BY_ORG_IDCONFCORR_AND_EMAIL = "CorreoEnvio.countByIdCorreoEnvAndEmail";
    /**
     * SELECT DISTINCT(ce) FROM CorreoEnvio ce WHERE ce.organismoTransito.codigoOrganismo = :pCodOrg AND ce.configuracionEmail.id = :pIdCnfE
     */
    public static final String SQ_FIND_BY_ORG_IDCONFCORR = "CorreoEnvio.findByIdCorreoEnvAndConfig";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_correo_envio")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "correo_envio")
    private String email;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "id_configuracion")
    @ManyToOne(fetch = FetchType.LAZY)
    private ConfiguracionEmail configuracionEmail;

    public CorreoEnvio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ConfiguracionEmail getConfiguracionEmail() {
        return configuracionEmail;
    }

    public void setConfiguracionEmail(ConfiguracionEmail configuracionEmail) {
        this.configuracionEmail = configuracionEmail;
    }

}
