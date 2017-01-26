package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class ArchivoExcepcionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private RadicarExcepcionDTO radicarExcepcionDTO;
    private String numeroArchivo;
    private String nombreArchivo;
    private Date fechaRegistro;
    private Boolean falloExcepcion;

    // --- Constructor
    public ArchivoExcepcionDTO() {
    }

    public ArchivoExcepcionDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroArchivo() {
        return this.numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public RadicarExcepcionDTO getRadicarExcepcionDTO() {
        return radicarExcepcionDTO;
    }

    public void setRadicarExcepcionDTO(RadicarExcepcionDTO radicarExcepcionDTO) {
        this.radicarExcepcionDTO = radicarExcepcionDTO;
    }

    public Boolean getFalloExcepcion() {
        return falloExcepcion;
    }

    public void setFalloExcepcion(Boolean falloExcepcion) {
        this.falloExcepcion = falloExcepcion;
    }

}
