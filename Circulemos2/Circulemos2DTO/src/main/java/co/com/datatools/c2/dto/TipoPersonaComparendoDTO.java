package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoPersonaComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;

    // --- Constructor
    public TipoPersonaComparendoDTO() {
    }

    public TipoPersonaComparendoDTO(Integer codigo) {
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

}