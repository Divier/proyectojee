package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class SedesCentroIntegralAtencionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private String telefono;
    private String correoElectronico;
    private DireccionDTO direccion;
    private PersonaDTO encargado;
    private CentroIntegralAtencionDTO centroIntegralAtencion;

    // --- Constructor
    public SedesCentroIntegralAtencionDTO() {
    }

    public SedesCentroIntegralAtencionDTO(Integer id) {
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

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public PersonaDTO getEncargado() {
        return this.encargado;
    }

    public void setEncargado(PersonaDTO encargado) {
        this.encargado = encargado;
    }

    public CentroIntegralAtencionDTO getCentroIntegralAtencion() {
        return this.centroIntegralAtencion;
    }

    public void setCentroIntegralAtencion(CentroIntegralAtencionDTO centroIntegralAtencion) {
        this.centroIntegralAtencion = centroIntegralAtencion;
    }

}