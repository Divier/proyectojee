package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * @author Generated
 * @version 3.0 - Thu Jun 30 17:53:23 COT 2016
 */
public class ImpulsoPruebaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String contenidoImpulso;
    private Boolean definitivo;
    private Date fechaGeneracion;
    private Long numeroDocumento;
    private SolicitudPruebasBackDTO solicitudPruebasBack;

    // --- Constructor
    public ImpulsoPruebaDTO() {
    }

    public ImpulsoPruebaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenidoImpulso() {
        return this.contenidoImpulso;
    }

    public void setContenidoImpulso(String contenidoImpulso) {
        this.contenidoImpulso = contenidoImpulso;
    }

    public Boolean getDefinitivo() {
        return this.definitivo;
    }

    public void setDefinitivo(Boolean definitivo) {
        this.definitivo = definitivo;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Long getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public SolicitudPruebasBackDTO getSolicitudPruebasBack() {
        return this.solicitudPruebasBack;
    }

    public void setSolicitudPruebasBack(SolicitudPruebasBackDTO solicitudPruebasBack) {
        this.solicitudPruebasBack = solicitudPruebasBack;
    }
}
