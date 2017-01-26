package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class MetodosWebServiceDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;
    private WebServiceDTO webService;
    private List<RespuestaWebServiceDTO> respuestaWebServiceList;

    // --- Constructor
    public MetodosWebServiceDTO() {
    }

    public MetodosWebServiceDTO(Integer codigo) {
        this.codigo = codigo;
    }

    // --- Getters-Setters
    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public WebServiceDTO getWebService() {
        return this.webService;
    }

    public void setWebService(WebServiceDTO webService) {
        this.webService = webService;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<RespuestaWebServiceDTO> getRespuestaWebServiceList() {
        if (this.respuestaWebServiceList == null) {
            this.respuestaWebServiceList = new java.util.ArrayList<>(1);
        }
        return this.respuestaWebServiceList;
    }

    public void setRespuestaWebServiceList(List<RespuestaWebServiceDTO> respuestaWebServiceList) {
        this.respuestaWebServiceList = respuestaWebServiceList;
    }

}