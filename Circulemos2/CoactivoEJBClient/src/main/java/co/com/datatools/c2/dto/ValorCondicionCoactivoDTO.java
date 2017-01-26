package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 11:57:38 COT 2016
 */
public class ValorCondicionCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String valor;
    private ConfiguracionCoactivoDTO configuracionCoactivo;
    private VariableCondicionCoacDTO variableCondicionCoac;

    // --- Constructor
    public ValorCondicionCoactivoDTO() {
    }

    public ValorCondicionCoactivoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ConfiguracionCoactivoDTO getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivoDTO configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public VariableCondicionCoacDTO getVariableCondicionCoac() {
        return this.variableCondicionCoac;
    }

    public void setVariableCondicionCoac(VariableCondicionCoacDTO variableCondicionCoac) {
        this.variableCondicionCoac = variableCondicionCoac;
    }

}
