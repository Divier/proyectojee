package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Mar 14 17:49:38 COT 2016
 */
public class ProcesoNegocioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private String descripcion;
    private ModuloDTO modulo;
    private ProcesoNegocioDTO procesoPadre;
    private List<ProcesoNegocioDTO> procesoHijoList;

    // --- Constructor
    public ProcesoNegocioDTO() {
    }

    public ProcesoNegocioDTO(Integer id) {
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

    public ModuloDTO getModulo() {
        return this.modulo;
    }

    public void setModulo(ModuloDTO modulo) {
        this.modulo = modulo;
    }

    public ProcesoNegocioDTO getProcesoPadre() {
        return this.procesoPadre;
    }

    public void setProcesoPadre(ProcesoNegocioDTO procesoPadre) {
        this.procesoPadre = procesoPadre;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ProcesoNegocioDTO> getProcesoHijoList() {
        if (this.procesoHijoList == null) {
            this.procesoHijoList = new java.util.ArrayList<>(1);
        }
        return this.procesoHijoList;
    }

    public void setProcesoHijoList(List<ProcesoNegocioDTO> procesoHijoList) {
        this.procesoHijoList = procesoHijoList;
    }

}
