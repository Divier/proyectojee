package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 11:55:40 COT 2016
 */
public class CondicionCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;
    private Integer orden;
    private List<OrganismoTransitoDTO> lstCondicionOrganismo;
    private List<VariableCondicionCoacDTO> lstVariablesCondicionCoac;
    private List<EstadoCondicionCoactivoDTO> lstEstadoCondicionFinanciacion;

    // --- Constructor
    public CondicionCoactivoDTO() {
    }

    public CondicionCoactivoDTO(Integer codigo) {
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

    public Integer getOrden() {
        return this.orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<OrganismoTransitoDTO> getLstCondicionOrganismo() {
        if (this.lstCondicionOrganismo == null) {
            this.lstCondicionOrganismo = new java.util.ArrayList<>(1);
        }
        return this.lstCondicionOrganismo;
    }

    public void setLstCondicionOrganismo(List<OrganismoTransitoDTO> lstCondicionOrganismo) {
        this.lstCondicionOrganismo = lstCondicionOrganismo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<VariableCondicionCoacDTO> getLstVariablesCondicionCoac() {
        if (this.lstVariablesCondicionCoac == null) {
            this.lstVariablesCondicionCoac = new java.util.ArrayList<>(1);
        }
        return this.lstVariablesCondicionCoac;
    }

    public void setLstVariablesCondicionCoac(List<VariableCondicionCoacDTO> lstVariablesCondicionCoac) {
        this.lstVariablesCondicionCoac = lstVariablesCondicionCoac;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<EstadoCondicionCoactivoDTO> getLstEstadoCondicionFinanciacion() {
        if (this.lstEstadoCondicionFinanciacion == null) {
            this.lstEstadoCondicionFinanciacion = new java.util.ArrayList<>(1);
        }
        return this.lstEstadoCondicionFinanciacion;
    }

    public void setLstEstadoCondicionFinanciacion(List<EstadoCondicionCoactivoDTO> lstEstadoCondicionFinanciacion) {
        this.lstEstadoCondicionFinanciacion = lstEstadoCondicionFinanciacion;
    }

}
