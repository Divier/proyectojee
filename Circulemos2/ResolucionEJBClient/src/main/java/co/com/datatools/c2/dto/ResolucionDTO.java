package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 12:23:07 COT 2016
 */
public class ResolucionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer anoResolucion;
    private Integer codigoOrganismo;
    private Date fechaResolucion;
    private Long idDocumento;
    private String numeroResolucion;
    private Boolean resolucionExitosa;
    private TipoResolucionDTO tipoResolucion;
    private EstadoResolucionDTO estadoResolucion;
    private ResolucionDTO resolucion;
    private List<ResolucionDTO> resoluciones;

    // --- Constructor
    public ResolucionDTO() {
    }

    public ResolucionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnoResolucion() {
        return this.anoResolucion;
    }

    public void setAnoResolucion(Integer anoResolucion) {
        this.anoResolucion = anoResolucion;
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaResolucion() {
        return this.fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Long getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNumeroResolucion() {
        return this.numeroResolucion;
    }

    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public Boolean getResolucionExitosa() {
        return this.resolucionExitosa;
    }

    public void setResolucionExitosa(Boolean resolucionExitosa) {
        this.resolucionExitosa = resolucionExitosa;
    }

    public TipoResolucionDTO getTipoResolucion() {
        return this.tipoResolucion;
    }

    public void setTipoResolucion(TipoResolucionDTO tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    public EstadoResolucionDTO getEstadoResolucion() {
        return this.estadoResolucion;
    }

    public void setEstadoResolucion(EstadoResolucionDTO estadoResolucion) {
        this.estadoResolucion = estadoResolucion;
    }

    public ResolucionDTO getResolucion() {
        return this.resolucion;
    }

    public void setResolucion(ResolucionDTO resolucion) {
        this.resolucion = resolucion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ResolucionDTO> getResoluciones() {
        if (this.resoluciones == null) {
            this.resoluciones = new java.util.ArrayList<>(1);
        }
        return this.resoluciones;
    }

    public void setResoluciones(List<ResolucionDTO> resoluciones) {
        this.resoluciones = resoluciones;
    }

}
