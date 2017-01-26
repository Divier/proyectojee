package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:41:03 COT 2016
 */
public class EstadoCondicionFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Boolean activo;
    private CondicionFinanciacionDTO condicionFinanciacion;
    private ConfiguracionFinanciacionDTO configuracionFinanciacion;

    // --- Constructor
    public EstadoCondicionFinanciacionDTO() {
    }

    public EstadoCondicionFinanciacionDTO(Long id) {
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

    public CondicionFinanciacionDTO getCondicionFinanciacion() {
        return this.condicionFinanciacion;
    }

    public void setCondicionFinanciacion(CondicionFinanciacionDTO condicionFinanciacion) {
        this.condicionFinanciacion = condicionFinanciacion;
    }

    public ConfiguracionFinanciacionDTO getConfiguracionFinanciacion() {
        return this.configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacionDTO configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

}
