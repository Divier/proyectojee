package co.com.datatools.c2.dto;

import java.math.BigDecimal;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:40:35 COT 2016
 */
public class DetallePorcentajeCuotaIniciDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private BigDecimal valorMinimoFinanciar;
    private BigDecimal valorMaximoFinanciar;
    private BigDecimal porcentajeCuotaInicial;
    private VariableCondicionFinanDTO variableCondicionFinan;
    private ConfiguracionFinanciacionDTO configuracionFinanciacion;

    /**
     * Attribute to Identify the registry on graphic interface
     */
    private int index;
    /**
     * Attribute to manipule the style of text
     */
    private String styleText;

    // --- Constructor
    public DetallePorcentajeCuotaIniciDTO() {
    }

    public DetallePorcentajeCuotaIniciDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VariableCondicionFinanDTO getVariableCondicionFinan() {
        return this.variableCondicionFinan;
    }

    public void setVariableCondicionFinan(VariableCondicionFinanDTO variableCondicionFinan) {
        this.variableCondicionFinan = variableCondicionFinan;
    }

    public ConfiguracionFinanciacionDTO getConfiguracionFinanciacion() {
        return this.configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacionDTO configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStyleText() {
        return styleText;
    }

    public void setStyleText(String styleText) {
        this.styleText = styleText;
    }

    public BigDecimal getValorMinimoFinanciar() {
        return valorMinimoFinanciar;
    }

    public void setValorMinimoFinanciar(BigDecimal valorMinimoFinanciar) {
        this.valorMinimoFinanciar = valorMinimoFinanciar;
    }

    public BigDecimal getValorMaximoFinanciar() {
        return valorMaximoFinanciar;
    }

    public void setValorMaximoFinanciar(BigDecimal valorMaximoFinanciar) {
        this.valorMaximoFinanciar = valorMaximoFinanciar;
    }

    public BigDecimal getPorcentajeCuotaInicial() {
        return porcentajeCuotaInicial;
    }

    public void setPorcentajeCuotaInicial(BigDecimal porcentajeCuotaInicial) {
        this.porcentajeCuotaInicial = porcentajeCuotaInicial;
    }

}
