package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoEmailDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;
    private List<OrganismoTransitoDTO> organismoTransitoList;

    // --- Constructor
    public TipoEmailDTO() {
    }

    public TipoEmailDTO(Integer codigo) {
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
    public List<OrganismoTransitoDTO> getOrganismoTransitoList() {
        if (this.organismoTransitoList == null) {
            this.organismoTransitoList = new java.util.ArrayList<>(1);
        }
        return this.organismoTransitoList;
    }

    public void setOrganismoTransitoList(List<OrganismoTransitoDTO> organismoTransitoList) {
        this.organismoTransitoList = organismoTransitoList;
    }

}