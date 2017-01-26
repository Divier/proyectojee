package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 17:52:24 COT 2016
 */
public class PruebaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaCarga;
    private String nombreArchivo;
    private String urlPrueba;
    private SolicitudPruebasBackDTO solicitudPruebasBack;
    private String numeroArchivo;
    private byte[] archivo;

    // --- Constructor
    public PruebaDTO() {
    }

    public PruebaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCarga() {
        return this.fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUrlPrueba() {
        return this.urlPrueba;
    }

    public void setUrlPrueba(String urlPrueba) {
        this.urlPrueba = urlPrueba;
    }

    public SolicitudPruebasBackDTO getSolicitudPruebasBack() {
        return this.solicitudPruebasBack;
    }

    public void setSolicitudPruebasBack(SolicitudPruebasBackDTO solicitudPruebasBack) {
        this.solicitudPruebasBack = solicitudPruebasBack;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getNumeroArchivo() {
        return numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

}
