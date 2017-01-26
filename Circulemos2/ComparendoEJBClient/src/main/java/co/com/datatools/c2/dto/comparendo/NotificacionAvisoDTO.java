package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Feb 25 11:49:31 COT 2016
 */
public class NotificacionAvisoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private OrganismoTransitoDTO organismoTransito;
    private Date fechaEjecucionNotificacion;
    private Date fechaGeneracion;
    private Date fechaNotificacion;
    private Boolean notificado;
    private Long idArchivoGenerado;
    private Integer cantidadComparendos;
    private String consecutivo;
    private List<ComparendoDTO> comparendos;

    // --- Constructor
    public NotificacionAvisoDTO() {
    }

    public NotificacionAvisoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaEjecucionNotificacion() {
        return this.fechaEjecucionNotificacion;
    }

    public void setFechaEjecucionNotificacion(Date fechaEjecucionNotificacion) {
        this.fechaEjecucionNotificacion = fechaEjecucionNotificacion;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Boolean getNotificado() {
        return this.notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public Long getIdArchivoGenerado() {
        return this.idArchivoGenerado;
    }

    public void setIdArchivoGenerado(Long idArchivoGenerado) {
        this.idArchivoGenerado = idArchivoGenerado;
    }

    public Integer getCantidadComparendos() {
        return this.cantidadComparendos;
    }

    public void setCantidadComparendos(Integer cantidadComparendos) {
        this.cantidadComparendos = cantidadComparendos;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
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
