package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 11:56:33 COT 2016
 */
public class EstadoCondicionCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Boolean activo;
    private CondicionCoactivoDTO condicionCoactivo;
    private ConfiguracionCoactivoDTO configuracionCoactivo;

    // --- Constructor
    public EstadoCondicionCoactivoDTO() {
    }

    public EstadoCondicionCoactivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return this.activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public CondicionCoactivoDTO getCondicionCoactivo() {
        return this.condicionCoactivo;
    }

    public void setCondicionCoactivo(CondicionCoactivoDTO condicionCoactivo) {
        this.condicionCoactivo = condicionCoactivo;
    }

    public ConfiguracionCoactivoDTO getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivoDTO configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

}
