package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the autenticacion_web_service database table.
 * 
 */
/**
 * @author robert.bautista
 * 
 */
@Entity
@Table(name = "autenticacion_web_service")
@NamedQuery(name = "AutenticacionWebService.findAll", query = "SELECT a FROM AutenticacionWebService a")
public class AutenticacionWebService implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autenticacion_web_service")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;

    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;

    // bi-directional many-to-one association to WebService
    @OneToOne
    @JoinColumn(name = "id_web_services")
    private WebService webService;

    // bi-directional many-to-one association to WebService
    @ManyToOne
    @JoinColumn(name = "codigo_organismo")
    private OrganismoTransito organismoTransito;

    @Column(name = "parametro")
    private String parametro;

    @Column(name = "observacion")
    private String observacion;

    public AutenticacionWebService() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer idAutenticacionWebService) {
        this.id = idAutenticacionWebService;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public WebService getWebService() {
        return this.webService;
    }

    public void setWebService(WebService webService) {
        this.webService = webService;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}