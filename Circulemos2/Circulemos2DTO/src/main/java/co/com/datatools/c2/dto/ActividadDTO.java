package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Mar 14 17:52:34 COT 2016
 */
public class ActividadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private ProcesoNegocioDTO proceso;
    private List<OrganismoTransitoDTO> actividadOrganismo;

    // --- Constructor
    public ActividadDTO() {
    }

    public ActividadDTO(Integer id) {
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

    public ProcesoNegocioDTO getProceso() {
        return this.proceso;
    }

    public void setProceso(ProcesoNegocioDTO proceso) {
        this.proceso = proceso;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<OrganismoTransitoDTO> getActividadOrganismo() {
        if (this.actividadOrganismo == null) {
            this.actividadOrganismo = new java.util.ArrayList<>(1);
        }
        return this.actividadOrganismo;
    }

    public void setActividadOrganismo(List<OrganismoTransitoDTO> actividadOrganismo) {
        this.actividadOrganismo = actividadOrganismo;
    }

}
