package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

public class ErrorFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Describe el error de una validacion de financiacion
     */
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
