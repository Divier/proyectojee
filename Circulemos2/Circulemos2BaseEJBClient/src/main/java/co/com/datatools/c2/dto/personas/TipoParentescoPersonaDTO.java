package co.com.datatools.c2.dto.personas;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoParentescoPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;

    // --- Constructor
    public TipoParentescoPersonaDTO() {
    }

    public TipoParentescoPersonaDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}