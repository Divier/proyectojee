package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Mar 31 10:06:13 COT 2016
 */
public class WebServiceDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String descripcion;
    private Boolean estado;
    private String nombre;
    private String urlPrimaria;
    private String urlSecundaria;
    private List<RespuestaWebServiceDTO> respuestaWebServices;
    private TipoWebServiceDTO tipoWebService;
    private List<AutenticacionWebServiceDTO> autenticacionWebServices;

    // --- Constructor
    public WebServiceDTO() {
    }

    public WebServiceDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<RespuestaWebServiceDTO> getRespuestaWebServices() {
        if (this.respuestaWebServices == null) {
            this.respuestaWebServices = new java.util.ArrayList<>(1);
        }
        return this.respuestaWebServices;
    }

    public void setRespuestaWebServices(List<RespuestaWebServiceDTO> respuestaWebServices) {
        this.respuestaWebServices = respuestaWebServices;
    }

    public TipoWebServiceDTO getTipoWebService() {
        return this.tipoWebService;
    }

    public void setTipoWebService(TipoWebServiceDTO tipoWebService) {
        this.tipoWebService = tipoWebService;
    }

    public List<AutenticacionWebServiceDTO> getAutenticacionWebServices() {
        if (this.autenticacionWebServices == null) {
            this.autenticacionWebServices = new java.util.ArrayList<>(1);
        }
        return this.autenticacionWebServices;
    }

    public void setAutenticacionWebServices(List<AutenticacionWebServiceDTO> autenticacionWebServices) {
        this.autenticacionWebServices = autenticacionWebServices;
    }

}
