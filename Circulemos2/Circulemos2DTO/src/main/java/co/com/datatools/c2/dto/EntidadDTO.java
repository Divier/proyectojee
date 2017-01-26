package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class EntidadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;
    private List<CampoEntidadDTO> campoEntidadList;

    // --- Constructor
    public EntidadDTO() {
    }

    public EntidadDTO(Integer codigo) {
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<CampoEntidadDTO> getCampoEntidadList() {
        if (this.campoEntidadList == null) {
            this.campoEntidadList = new java.util.ArrayList<>(1);
        }
        return this.campoEntidadList;
    }

    public void setCampoEntidadList(List<CampoEntidadDTO> campoEntidadList) {
        this.campoEntidadList = campoEntidadList;
    }

}