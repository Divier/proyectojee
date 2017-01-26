package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Mar 31 10:05:46 COT 2016
 */
public class AutenticacionWebServiceDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String clave;
    private String usuario;
    private WebServiceDTO webService;
    private OrganismoTransitoDTO organismoTransito;
    private String parametro;
    private String observacion;

    // --- Constructor
    public AutenticacionWebServiceDTO() {
    }

    public AutenticacionWebServiceDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public WebServiceDTO getWebService() {
        return this.webService;
    }

    public void setWebService(WebServiceDTO webService) {
        this.webService = webService;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
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