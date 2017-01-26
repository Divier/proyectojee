package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:03:13 COT 2016
 */
public class MotivacionImpugnacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private String descripcionHechos;
    private TipoPeticionDTO tipoPeticion;
    private OrigenImpugnacionDTO origenImpugnacion;
    private String numeroArchivoEscrito;
    private Date fechaCargueArchivo;

    // --- Constructor
    public MotivacionImpugnacionDTO() {
    }

    public MotivacionImpugnacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return this.trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public String getDescripcionHechos() {
        return this.descripcionHechos;
    }

    public void setDescripcionHechos(String descripcionHechos) {
        this.descripcionHechos = descripcionHechos;
    }

    public TipoPeticionDTO getTipoPeticion() {
        return tipoPeticion;
    }

    public void setTipoPeticion(TipoPeticionDTO tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    public OrigenImpugnacionDTO getOrigenImpugnacion() {
        return origenImpugnacion;
    }

    public void setOrigenImpugnacion(OrigenImpugnacionDTO origenImpugnacion) {
        this.origenImpugnacion = origenImpugnacion;
    }

    public String getNumeroArchivoEscrito() {
        return numeroArchivoEscrito;
    }

    public void setNumeroArchivoEscrito(String numeroArchivoEscrito) {
        this.numeroArchivoEscrito = numeroArchivoEscrito;
    }

    public Date getFechaCargueArchivo() {
        return fechaCargueArchivo;
    }

    public void setFechaCargueArchivo(Date fechaCargueArchivo) {
        this.fechaCargueArchivo = fechaCargueArchivo;
    }
}