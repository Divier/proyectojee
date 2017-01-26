package co.com.datatools.c2.dto;

import java.math.BigDecimal;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:40:02 COT 2016
 */
public class DetalleCantidadCuotaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private BigDecimal valorMinimoFinanciar;
    private BigDecimal valorMaximoFinanciar;
    private Integer cantidadMinimaCoutas;
    private Integer cantidadMaximaCouta;
    private VariableCondicionFinanDTO variableCondicionFinan;
    private ConfiguracionFinanciacionDTO configuracionFinanciacion;

    /**
     * Attribute para identificar el registro en la interfaz grafica
     */
    private int index;
    /**
     * Attribute para manipular el estilo del texto.
     */
    private String styleText;

    // --- Constructor
    public DetalleCantidadCuotaDTO() {
    }

    public DetalleCantidadCuotaDTO(Integer id) {
        this.id = id;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCantidadMinimaCoutas() {
        return cantidadMinimaCoutas;
    }

    public void setCantidadMinimaCoutas(Integer cantidadMinimaCoutas) {
        this.cantidadMinimaCoutas = cantidadMinimaCoutas;
    }

    public Integer getCantidadMaximaCouta() {
        return cantidadMaximaCouta;
    }

    public void setCantidadMaximaCouta(Integer cantidadMaximaCouta) {
        this.cantidadMaximaCouta = cantidadMaximaCouta;
    }

    public VariableCondicionFinanDTO getVariableCondicionFinan() {
        return variableCondicionFinan;
    }

    public void setVariableCondicionFinan(VariableCondicionFinanDTO variableCondicionFinan) {
        this.variableCondicionFinan = variableCondicionFinan;
    }

    public ConfiguracionFinanciacionDTO getConfiguracionFinanciacion() {
        return configuracionFinanciacion;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cantidadMaximaCouta == null) ? 0 : cantidadMaximaCouta.hashCode());
        result = prime * result + ((cantidadMinimaCoutas == null) ? 0 : cantidadMinimaCoutas.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + index;
        result = prime * result + ((styleText == null) ? 0 : styleText.hashCode());
        result = prime * result + ((valorMaximoFinanciar == null) ? 0 : valorMaximoFinanciar.hashCode());
        result = prime * result + ((valorMinimoFinanciar == null) ? 0 : valorMinimoFinanciar.hashCode());
        result = prime * result + ((variableCondicionFinan == null) ? 0 : variableCondicionFinan.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetalleCantidadCuotaDTO))
            return false;
        DetalleCantidadCuotaDTO other = (DetalleCantidadCuotaDTO) obj;
        if (cantidadMaximaCouta == null) {
            if (other.cantidadMaximaCouta != null)
                return false;
        } else if (!cantidadMaximaCouta.equals(other.cantidadMaximaCouta))
            return false;
        if (cantidadMinimaCoutas == null) {
            if (other.cantidadMinimaCoutas != null)
                return false;
        } else if (!cantidadMinimaCoutas.equals(other.cantidadMinimaCoutas))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (index != other.index)
            return false;
        if (styleText == null) {
            if (other.styleText != null)
                return false;
        } else if (!styleText.equals(other.styleText))
            return false;
        if (valorMaximoFinanciar == null) {
            if (other.valorMaximoFinanciar != null)
                return false;
        } else if (!valorMaximoFinanciar.equals(other.valorMaximoFinanciar))
            return false;
        if (valorMinimoFinanciar == null) {
            if (other.valorMinimoFinanciar != null)
                return false;
        } else if (!valorMinimoFinanciar.equals(other.valorMinimoFinanciar))
            return false;
        if (variableCondicionFinan == null) {
            if (other.variableCondicionFinan != null)
                return false;
        } else if (!variableCondicionFinan.equals(other.variableCondicionFinan))
            return false;
        return true;
    }

}
