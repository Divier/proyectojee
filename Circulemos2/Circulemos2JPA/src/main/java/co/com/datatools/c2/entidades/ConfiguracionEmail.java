package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "configuracion_email")
@NamedQueries({
        @NamedQuery(name = "ConfiguracionEmail.findAll", query = "SELECT c FROM ConfiguracionEmail c"),
        @NamedQuery(
                name = "ConfiguracionEmail.countExistConfByTipEmailOrg",
                query = "SELECT count(c) FROM ConfiguracionEmail c WHERE c.tipoEmail.codigo = :pCodTipEmail AND c.organismoTransito.codigoOrganismo = :pCodOrg") })
public class ConfiguracionEmail implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    /**
     * SELECT count(c) FROM ConfiguracionEmail c WHERE c.tipoEmail.codigo = :pCodTipEmail AND c.organismoTransito.codigoOrganismo = :pCodOrg
     */
    public static final String SQ_COUNT_BY_TIP_EMAIL_ORG = "ConfiguracionEmail.countExistConfByTipEmailOrg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_configuracion")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @Basic(optional = false)
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;

    @Basic(optional = false)
    @Column(name = "asunto_email")
    private String asuntoEmail;

    @Column(name = "cuerpo_email")
    private String cuerpoEmail;

    @Column(name = "pie_email")
    private String pieEmail;

    @JoinColumn(name = "codigo_tipo_email", referencedColumnName = "codigo_tipo_email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoEmail tipoEmail;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioPersona usuario;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @OneToMany(mappedBy = "configuracionEmail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CorreoEnvio> emailList;

    public ConfiguracionEmail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getAsuntoEmail() {
        return asuntoEmail;
    }

    public void setAsuntoEmail(String asuntoEmail) {
        this.asuntoEmail = asuntoEmail;
    }

    public String getCuerpoEmail() {
        return cuerpoEmail;
    }

    public void setCuerpoEmail(String cuerpoEmail) {
        this.cuerpoEmail = cuerpoEmail;
    }

    public String getPieEmail() {
        return pieEmail;
    }

    public void setPieEmail(String pieEmail) {
        this.pieEmail = pieEmail;
    }

    public TipoEmail getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(TipoEmail tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public List<CorreoEnvio> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<CorreoEnvio> emailList) {
        this.emailList = emailList;
    }

}