package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO que permite llevar a cabo la consulta de la validación del valor que toma una determinada variable contra un segundo valor cargado previamente
 * desde su variable a comparar representada en el DTO ValorCondVariablesComparacionDTO.
 * 
 * @author luis.forero
 * 
 */
public class ValorConValidacionTipCompVarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idVariableCondicionFinan;

    /**
     * Variable 1 representada como valor entero
     */
    private Integer valorVariable1Int;
    /**
     * Variable 1 representada como valor Real
     */
    private Double valorVariable1Real;
    /**
     * Variable 1 representada como valor Fecha
     */
    private Date valorVariable1Fecha;
    /**
     * Variable 1 representada en cadena
     */
    private String valorVariable1Cadena;

    /**
     * Variable 2 representada como valor entero
     */
    private Integer valorVariable2Int;
    /**
     * Variable 2 representada como valor Real
     */
    private Double valorVariable2Real;
    /**
     * Variable 2 representada como valor Fecha
     */
    private Date valorVariable2Fecha;
    /**
     * Variable 2 representada en cadena
     */
    private String valorVariable2Cadena;

    /**
     * Atributo que representa la respuesta de la validacion respectiva.
     */
    private String respuesta;

    public ValorConValidacionTipCompVarDTO() {
    }

    public Integer getIdVariableCondicionFinan() {
        return idVariableCondicionFinan;
    }

    public void setIdVariableCondicionFinan(Integer idVariableCondicionFinan) {
        this.idVariableCondicionFinan = idVariableCondicionFinan;
    }

    public Integer getValorVariable1Int() {
        return valorVariable1Int;
    }

    public void setValorVariable1Int(Integer valorVariable1Int) {
        this.valorVariable1Int = valorVariable1Int;
    }

    public Double getValorVariable1Real() {
        return valorVariable1Real;
    }

    public void setValorVariable1Real(Double valorVariable1Real) {
        this.valorVariable1Real = valorVariable1Real;
    }

    public Date getValorVariable1Fecha() {
        return valorVariable1Fecha;
    }

    public void setValorVariable1Fecha(Date valorVariable1Fecha) {
        this.valorVariable1Fecha = valorVariable1Fecha;
    }

    public Integer getValorVariable2Int() {
        return valorVariable2Int;
    }

    public void setValorVariable2Int(Integer valorVariable2Int) {
        this.valorVariable2Int = valorVariable2Int;
    }

    public Double getValorVariable2Real() {
        return valorVariable2Real;
    }

    public void setValorVariable2Real(Double valorVariable2Real) {
        this.valorVariable2Real = valorVariable2Real;
    }

    public Date getValorVariable2Fecha() {
        return valorVariable2Fecha;
    }

    public void setValorVariable2Fecha(Date valorVariable2Fecha) {
        this.valorVariable2Fecha = valorVariable2Fecha;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getValorVariable1Cadena() {
        return valorVariable1Cadena;
    }

    public void setValorVariable1Cadena(String valorVariable1Cadena) {
        this.valorVariable1Cadena = valorVariable1Cadena;
    }

    public String getValorVariable2Cadena() {
        return valorVariable2Cadena;
    }

    public void setValorVariable2Cadena(String valorVariable2Cadena) {
        this.valorVariable2Cadena = valorVariable2Cadena;
    }

}
