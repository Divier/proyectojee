package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 06 17:53:24 COT 2016
 */
public class RespuestaWebServiceDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String descripcion;
    private Boolean estado;
    private String nombre;
    private TipoRespuestaWebServiceDTO tipoRespuestaWebService;
    private WebServiceDTO webService;

    // --- Constructor
    public RespuestaWebServiceDTO() {
    }

    public RespuestaWebServiceDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
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

    public TipoRespuestaWebServiceDTO getTipoRespuestaWebService() {
        return this.tipoRespuestaWebService;
    }

    public void setTipoRespuestaWebService(TipoRespuestaWebServiceDTO tipoRespuestaWebService) {
        this.tipoRespuestaWebService = tipoRespuestaWebService;
    }

    public WebServiceDTO getWebService() {
        return this.webService;
    }

    public void setWebService(WebServiceDTO webService) {
        this.webService = webService;
    }

}
