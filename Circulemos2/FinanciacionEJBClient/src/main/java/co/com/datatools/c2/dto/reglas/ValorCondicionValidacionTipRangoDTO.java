package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO Que permite mediante drools validar si un determinado valor que toma una variable es valido segun un determinado rango definido. Siempre que
 * ocurra un error se ingresara en la respuesta DTO.
 * 
 * @author luis.forero
 * 
 */
public class ValorCondicionValidacionTipRangoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idVariableCondicionFinanciacion;
    private Integer valorVariableCondicionInt;
    private Double valorVariableCondicionReal;
    private Date valorVariableCondicionFecha;
    private String respuesta;

    public ValorCondicionValidacionTipRangoDTO() {
    }

    public Integer getIdVariableCondicionFinanciacion() {
        return idVariableCondicionFinanciacion;
    }

    public void setIdVariableCondicionFinanciacion(Integer idVariableCondicionFinanciacion) {
        this.idVariableCondicionFinanciacion = idVariableCondicionFinanciacion;
    }

    public Integer getValorVariableCondicionInt() {
        return valorVariableCondicionInt;
    }

    public void setValorVariableCondicionInt(Integer valorVariableCondicionInt) {
        this.valorVariableCondicionInt = valorVariableCondicionInt;
    }

    public Double getValorVariableCondicionReal() {
        return valorVariableCondicionReal;
    }

    public void setValorVariableCondicionReal(Double valorVariableCondicionReal) {
        this.valorVariableCondicionReal = valorVariableCondicionReal;
    }

    public Date getValorVariableCondicionFecha() {
        return valorVariableCondicionFecha;
    }

    public void setValorVariableCondicionFecha(Date valorVariableCondicionFecha) {
        this.valorVariableCondicionFecha = valorVariableCondicionFecha;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
