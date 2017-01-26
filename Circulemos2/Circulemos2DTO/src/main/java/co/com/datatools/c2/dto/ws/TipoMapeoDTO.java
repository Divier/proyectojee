package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 15:09:11 COT 2015
 */
public class TipoMapeoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String descripcion;
    private String nombre;
    private WebServiceExpuestoDTO webServiceExpuesto;

    // --- Constructor
    public TipoMapeoDTO() {
    }

    public TipoMapeoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public WebServiceExpuestoDTO getWebServiceExpuesto() {
        return this.webServiceExpuesto;
    }

    public void setWebServiceExpuesto(WebServiceExpuestoDTO webServiceExpuesto) {
        this.webServiceExpuesto = webServiceExpuesto;
    }

}
