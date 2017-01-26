package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

/**
 * Clase que representa un patio en el m�dulo de comparendos.
 * 
 * @author julio.pinzon
 */
public class PatioComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;

    // --- Constructor
    public PatioComparendoDTO() {
    }

    public PatioComparendoDTO(Integer id) {
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

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}