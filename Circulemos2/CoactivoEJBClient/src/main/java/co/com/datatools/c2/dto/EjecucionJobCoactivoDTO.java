package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 03 18:56:35 COT 2016
 */
public class EjecucionJobCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ConfiguracionCoactivoDTO configuracionCoactivo;
    private Date fechaEjecucion;

    // --- Constructor
    public EjecucionJobCoactivoDTO() {
    }

    public EjecucionJobCoactivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfiguracionCoactivoDTO getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivoDTO configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public Date getFechaEjecucion() {
        return this.fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

}
