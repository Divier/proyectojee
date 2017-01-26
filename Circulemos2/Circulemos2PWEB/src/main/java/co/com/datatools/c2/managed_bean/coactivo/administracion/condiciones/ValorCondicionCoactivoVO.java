package co.com.datatools.c2.managed_bean.coactivo.administracion.condiciones;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import co.com.datatools.c2.dto.ValorCondicionCoactivoDTO;
import co.com.datatools.c2.dto.VariableCondicionCoacDTO;

/**
 * Value Object que representa los valores y variables de las condiciones cargadas en la interfaz.
 * 
 * @author Dixon.Alvarez
 * 
 */
public class ValorCondicionCoactivoVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String STYLE_TEXT_LABEL = "color: #000000;";
    private static final String STYLE_TEXT_LABEL_ERROR_VALIDATE = "color: #b94a48;font-weight: bold;";

    private int index;

    /**
     * Definiciones de tipos de campos a cargar en interfaz grafica
     */
    private boolean tipEntero;
    private Integer valInteger;
    private boolean tipReal;
    private BigDecimal valDouble;
    private boolean tipBooleano;
    private Boolean valBooleano;
    private boolean tipCadena;
    private String valCadena;
    private boolean tipFecha;
    private Date valFecha;

    private boolean tipSeleccionUnica;
    private boolean tipSeleccionMultiple;

    private boolean valorValidado;

    /**
     * Variable condicion a la que pertenece el valor.
     */
    private VariableCondicionCoacDTO variableCondicionCoactivoDTO;
    /**
     * Representa el objeto cuando la variable a la que pertenece solo puede tener un solo valor.
     */
    private ValorCondicionCoactivoDTO valorCondicionCoactivoDTO;

    /**
     * Representa los valores seleccionados cuando el tipo de variable al que pertenece el valor es de seleccion multiple
     */
    private List<String> lstValoresEscogidos;
    /**
     * Represnta los nombres de los valores seleccionados cuando el tipo de variable al que pertenece es de seleccion multiple
     */
    private List<String> lstNombresValores;
    /**
     * Representa los valores de catalogos que puede tomar el anterior valor Map<Nombre,Valor>
     */
    private Map<String, String> catalogo;

    private String styleTextLabel;

    public ValorCondicionCoactivoVO() {
        styleTextLabel = STYLE_TEXT_LABEL;
        valorValidado = true;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isTipEntero() {
        return tipEntero;
    }

    public void setTipEntero(boolean tipEntero) {
        this.tipEntero = tipEntero;
    }

    public boolean isTipReal() {
        return tipReal;
    }

    public void setTipReal(boolean tipReal) {
        this.tipReal = tipReal;
    }

    public boolean isTipBooleano() {
        return tipBooleano;
    }

    public void setTipBooleano(boolean tipBooleano) {
        this.tipBooleano = tipBooleano;
    }

    public boolean isTipCadena() {
        return tipCadena;
    }

    public void setTipCadena(boolean tipCadena) {
        this.tipCadena = tipCadena;
    }

    public boolean isTipFecha() {
        return tipFecha;
    }

    public void setTipFecha(boolean tipFecha) {
        this.tipFecha = tipFecha;
    }

    public boolean isTipSeleccionUnica() {
        return tipSeleccionUnica;
    }

    public void setTipSeleccionUnica(boolean tipSeleccionUnica) {
        this.tipSeleccionUnica = tipSeleccionUnica;
    }

    public boolean isTipSeleccionMultiple() {
        return tipSeleccionMultiple;
    }

    public void setTipSeleccionMultiple(boolean tipSeleccionMultiple) {
        this.tipSeleccionMultiple = tipSeleccionMultiple;
    }

    public VariableCondicionCoacDTO getVariableCondicionCoactivoDTO() {
        return variableCondicionCoactivoDTO;
    }

    public void setVariableCondicionCoactivoDTO(VariableCondicionCoacDTO variableCondicionCoactivoDTO) {
        this.variableCondicionCoactivoDTO = variableCondicionCoactivoDTO;
    }

    public ValorCondicionCoactivoDTO getValorCondicionCoactivoDTO() {
        return valorCondicionCoactivoDTO;
    }

    public void setValorCondicionCoactivoDTO(ValorCondicionCoactivoDTO valorCondicionCoactivoDTO) {
        this.valorCondicionCoactivoDTO = valorCondicionCoactivoDTO;
    }

    public List<String> getLstValoresEscogidos() {
        return lstValoresEscogidos;
    }

    public void setLstValoresEscogidos(List<String> lstValoresEscogidos) {
        this.lstValoresEscogidos = lstValoresEscogidos;
    }

    public Map<String, String> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Map<String, String> catalogo) {
        this.catalogo = catalogo;
    }

    public Integer getValInteger() {
        return valInteger;
    }

    public void setValInteger(Integer valInteger) {
        this.valInteger = valInteger;
    }

    public BigDecimal getValDouble() {
        return valDouble;
    }

    public void setValDouble(BigDecimal valDouble) {
        this.valDouble = valDouble;
    }

    public Boolean getValBooleano() {
        return valBooleano;
    }

    public void setValBooleano(Boolean valBooleano) {
        this.valBooleano = valBooleano;
    }

    public String getValCadena() {
        return valCadena;
    }

    public void setValCadena(String valCadena) {
        this.valCadena = valCadena;
    }

    public Date getValFecha() {
        return valFecha;
    }

    public void setValFecha(Date valFecha) {
        this.valFecha = valFecha;
    }

    public String getStyleTextLabel() {
        return styleTextLabel;
    }

    public void setStyleTextLabel(String styleTextLabel) {
        this.styleTextLabel = styleTextLabel;
    }

    public boolean isValorValidado() {
        return valorValidado;
    }

    public void setValorValidado(boolean valorValidado) {
        this.valorValidado = valorValidado;
        if (this.valorValidado) {
            styleTextLabel = STYLE_TEXT_LABEL;
        } else {
            styleTextLabel = STYLE_TEXT_LABEL_ERROR_VALIDATE;
        }
    }

    public List<String> getLstNombresValores() {
        return lstNombresValores;
    }

    public void setLstNombresValores(List<String> lstNombresValores) {
        this.lstNombresValores = lstNombresValores;
    }

}
