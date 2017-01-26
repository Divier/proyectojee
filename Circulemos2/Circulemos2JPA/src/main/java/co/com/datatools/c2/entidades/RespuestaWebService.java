package co.com.datatools.c2.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the respuesta_web_services database table.
 * 
 */
@Entity
@Table(name = "respuesta_web_services")
@NamedQueries({
        @NamedQuery(name = "RespuestaWebService.findAll", query = "SELECT r FROM RespuestaWebService r"),
        @NamedQuery(
                name = "RespuestaWebService.findByIdWebServ",
                query = "SELECT r FROM RespuestaWebService r LEFT JOIN FETCH r.tipoRespuestaWebService WHERE r.webService.id = :pIdWebServ") })
public class RespuestaWebService implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Obtiene todas las respuestas posibles de un Web Service.<br>
     * Parametros:
     * <p>
     * <li>pIdWebServ Id del web service</li>
     * </p>
     * <br>
     * Consulta: <br>
     * SELECT r FROM RespuestaWebService r LEFT JOIN FETCH r.tipoRespuestaWebService WHERE r.webService.id = :pIdWebServ
     * 
     * @author luis.forero(2016-04-06)
     */
    public static final String SQ_FIND_BY_ID_WEB_SERVICE = "RespuestaWebService.findByIdWebServ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta_web_services")
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

    // bi-directional many-to-one association to TipoRespuestaWebService
    @ManyToOne
    @JoinColumn(name = "id_tipo_respuesta_web_services")
    private TipoRespuestaWebService tipoRespuestaWebService;

    // bi-directional many-to-one association to WebService
    @ManyToOne
    @JoinColumn(name = "id_web_services")
    private WebService webService;

    public RespuestaWebService() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TipoRespuestaWebService getTipoRespuestaWebService() {
        return this.tipoRespuestaWebService;
    }

    public void setTipoRespuestaWebService(TipoRespuestaWebService tipoRespuestaWebService) {
        this.tipoRespuestaWebService = tipoRespuestaWebService;
    }

    public WebService getWebService() {
        return this.webService;
    }

    public void setWebService(WebService webService) {
        this.webService = webService;
    }

}