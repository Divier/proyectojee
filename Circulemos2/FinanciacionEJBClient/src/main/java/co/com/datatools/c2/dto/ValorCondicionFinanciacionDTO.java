package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:42:15 COT 2016
 */
public class ValorCondicionFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String valor;
    private ConfiguracionFinanciacionDTO configuracionFinanciacion;
    private VariableCondicionFinanDTO variableCondicionFinan;

    // --- Constructor
    public ValorCondicionFinanciacionDTO() {
    }

    public ValorCondicionFinanciacionDTO(Integer id) {
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

    public ConfiguracionFinanciacionDTO getConfiguracionFinanciacion() {
        return this.configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacionDTO configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

    public VariableCondicionFinanDTO getVariableCondicionFinan() {
        return this.variableCondicionFinan;
    }

    public void setVariableCondicionFinan(VariableCondicionFinanDTO variableCondicionFinan) {
        this.variableCondicionFinan = variableCondicionFinan;
    }

}
