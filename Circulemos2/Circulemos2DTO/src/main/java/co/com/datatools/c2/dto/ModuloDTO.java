package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Mar 14 17:51:46 COT 2016
 */
public class ModuloDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private String descripcion;
    private List<ProcesoNegocioDTO> procesoList;

    // --- Constructor
    public ModuloDTO() {
    }

    public ModuloDTO(Integer id) {
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ProcesoNegocioDTO> getProcesoList() {
        if (this.procesoList == null) {
            this.procesoList = new java.util.ArrayList<>(1);
        }
        return this.procesoList;
    }

    public void setProcesoList(List<ProcesoNegocioDTO> procesoList) {
        this.procesoList = procesoList;
    }

}
