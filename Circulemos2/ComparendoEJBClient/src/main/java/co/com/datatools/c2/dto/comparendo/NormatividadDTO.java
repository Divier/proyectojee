package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu May 29 11:29:15 COT 2014
 */
public class NormatividadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private OrdenamientoPaisDTO ordenamientoPais;
    private List<OrganismoTransitoDTO> organismoTransitoList;

    // --- Constructor
    public NormatividadDTO() {
    }

    public NormatividadDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public OrdenamientoPaisDTO getOrdenamientoPais() {
        return this.ordenamientoPais;
    }

    public void setOrdenamientoPais(OrdenamientoPaisDTO ordenamientoPais) {
        this.ordenamientoPais = ordenamientoPais;
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
