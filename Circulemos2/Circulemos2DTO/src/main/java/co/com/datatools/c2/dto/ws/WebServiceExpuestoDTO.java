package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 15:07:41 COT 2015
 */
public class WebServiceExpuestoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private int id;
    private String descripcion;
    private Boolean estado;
    private String nombre;

    // --- Constructor
    public WebServiceExpuestoDTO() {
    }

    public WebServiceExpuestoDTO(int id) {
        this.id = id;

    }

    // --- Getters-Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

}
