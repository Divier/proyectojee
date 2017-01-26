package co.com.datatools.c2.dto.comparendo;

import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 12:01:19 COT 2015
 */
public class GradoAlcoholemiaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer valor;
    private OrganismoTransitoDTO organismoTransito;
    private List<ComparendoDTO> comparendos;

    // --- Constructor
    public GradoAlcoholemiaDTO() {
    }

    public GradoAlcoholemiaDTO(Integer id) {
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

    public Integer getValor() {
        return this.valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ComparendoDTO> getComparendos() {
        if (this.comparendos == null) {
            this.comparendos = new java.util.ArrayList<>(1);
        }
        return this.comparendos;
    }

    public void setComparendos(List<ComparendoDTO> comparendos) {
        this.comparendos = comparendos;
    }

}
