package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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

/**
 * The persistent class for the web_services database table.
 * 
 */
@Entity
@Table(name = "web_services")
@NamedQueries({
        @NamedQuery(name = "WebService.findAll", query = "SELECT w FROM WebService w"),
        @NamedQuery(
                name = "WebService.findByIdWebService",
                query = "SELECT w FROM WebService w LEFT JOIN FETCH w.tipoWebService LEFT JOIN FETCH w.autenticacionWebServices WHERE w.id = :pIdWebSer") })
public class WebService implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta la informacion correspondiente al webService.
     * <p>
     * <li>pIdWebSer identificador del webservice</li>
     * </p>
     * <br>
     * SELECT w FROM WebService w LEFT JOIN FETCH tipoWebService LEFT JOIN FETCH w.autenticacionWebServices WHERE w.id = :pIdWebSer
     */
    public static final String SQ_FIND_BY_ID_WEB_SERVICE = "WebService.findByIdWebService";

    @Id
    @Column(name = "id_web_services")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "url_primaria")
    private String urlPrimaria;

    @Column(name = "url_secundaria")
    private String urlSecundaria;

    // bi-directional many-to-one association to RespuestaWebService
    @OneToMany(mappedBy = "webService")
    private List<RespuestaWebService> respuestaWebServices;

    // bi-directional many-to-one association to TipoWebService
    @ManyToOne
    @JoinColumn(name = "id_tipo_web_service")
    private TipoWebService tipoWebService;

    // bi-directional many-to-one association to AutenticacionWebService
    @OneToMany(mappedBy = "webService", fetch = FetchType.LAZY)
    private List<AutenticacionWebService> autenticacionWebServices;

    public WebService() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer idWebServices) {
        this.id = idWebServices;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlPrimaria() {
        return this.urlPrimaria;
    }

    public void setUrlPrimaria(String urlPrimaria) {
        this.urlPrimaria = urlPrimaria;
    }

    public String getUrlSecundaria() {
        return this.urlSecundaria;
    }

    public void setUrlSecundaria(String urlSecundaria) {
        this.urlSecundaria = urlSecundaria;
    }

    public List<RespuestaWebService> getRespuestaWebServices() {
        return this.respuestaWebServices;
    }

    public void setRespuestaWebServices(List<RespuestaWebService> respuestaWebServices) {
        this.respuestaWebServices = respuestaWebServices;
    }

    public TipoWebService getTipoWebService() {
        return this.tipoWebService;
    }

    public void setTipoWebService(TipoWebService tipoWebService) {
        this.tipoWebService = tipoWebService;
    }

    public List<AutenticacionWebService> getAutenticacionWebServices() {
        return this.autenticacionWebServices;
    }

    public void setAutenticacionWebServices(List<AutenticacionWebService> autenticacionWebServices) {
        this.autenticacionWebServices = autenticacionWebServices;
    }
}