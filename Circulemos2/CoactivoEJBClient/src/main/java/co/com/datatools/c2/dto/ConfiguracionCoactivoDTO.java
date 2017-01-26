package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 11:56:19 COT 2016
 */
public class ConfiguracionCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private Date fechaInicial;
    private Date fechaFinal;
    private OrganismoTransitoDTO organismoTransito;
    private List<ValorCondicionCoactivoDTO> lstValorCondicionCoactivo;
    private List<EstadoCondicionCoactivoDTO> lstEstadoCondicionCoactivo;

    // --- Constructor
    public ConfiguracionCoactivoDTO() {
    }

    public ConfiguracionCoactivoDTO(Integer id) {
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

    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
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
    public List<ValorCondicionCoactivoDTO> getLstValorCondicionCoactivo() {
        if (this.lstValorCondicionCoactivo == null) {
            this.lstValorCondicionCoactivo = new java.util.ArrayList<>(1);
        }
        return this.lstValorCondicionCoactivo;
    }

    public void setLstValorCondicionCoactivo(List<ValorCondicionCoactivoDTO> lstValorCondicionCoactivo) {
        this.lstValorCondicionCoactivo = lstValorCondicionCoactivo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<EstadoCondicionCoactivoDTO> getLstEstadoCondicionCoactivo() {
        if (this.lstEstadoCondicionCoactivo == null) {
            this.lstEstadoCondicionCoactivo = new java.util.ArrayList<>(1);
        }
        return this.lstEstadoCondicionCoactivo;
    }

    public void setLstEstadoCondicionCoactivo(List<EstadoCondicionCoactivoDTO> lstEstadoCondicionCoactivo) {
        this.lstEstadoCondicionCoactivo = lstEstadoCondicionCoactivo;
    }

}
