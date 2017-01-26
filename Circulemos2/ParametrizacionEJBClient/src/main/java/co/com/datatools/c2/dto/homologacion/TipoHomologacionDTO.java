package co.com.datatools.c2.dto.homologacion;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:42:19 COT 2015
 */
public class TipoHomologacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String descripcion;
    private String nombre;
    private ServicioHomologacionDTO servicioHomologacion;

    // --- Constructor
    public TipoHomologacionDTO() {
    }

    public TipoHomologacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ServicioHomologacionDTO getServicioHomologacion() {
        return this.servicioHomologacion;
    }

    public void setServicioHomologacion(ServicioHomologacionDTO servicioHomologacion) {
        this.servicioHomologacion = servicioHomologacion;
    }

}
