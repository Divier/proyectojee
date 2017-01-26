package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class ArchivoAccidentalidadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private AccidentalidadDTO accidentalidad;
    private String numeroArchivo;
    private String nombreArchivo;
    private Date fechaRegistro;
    private UsuarioPersonaDTO usuario;

    // --- Constructor
    public ArchivoAccidentalidadDTO() {
    }

    public ArchivoAccidentalidadDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccidentalidadDTO getAccidentalidad() {
        return this.accidentalidad;
    }

    public void setAccidentalidad(AccidentalidadDTO accidentalidad) {
        this.accidentalidad = accidentalidad;
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

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

}
