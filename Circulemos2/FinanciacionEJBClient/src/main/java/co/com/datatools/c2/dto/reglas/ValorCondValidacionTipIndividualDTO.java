package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO que permite identificar si el valor que toma una determinada variable de condicion de financiación es valida.
 * 
 * @author luis.forero
 * 
 */
public class ValorCondValidacionTipIndividualDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idVarCondFinanciacion;
    private Integer valorInt;
    private Double valorReal;
    private Date valorFecha;
    private String valorStr;
    private String respuesta;

    public ValorCondValidacionTipIndividualDTO() {
    }

    public Integer getIdVarCondFinanciacion() {
        return idVarCondFinanciacion;
    }

    public void setIdVarCondFinanciacion(Integer idVarCondFinanciacion) {
        this.idVarCondFinanciacion = idVarCondFinanciacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getValorInt() {
        return valorInt;
    }

    public void setValorInt(Integer valorInt) {
        this.valorInt = valorInt;
    }

    public Double getValorReal() {
        return valorReal;
    }

    public void setValorReal(Double valorReal) {
        this.valorReal = valorReal;
    }

    public Date getValorFecha() {
        return valorFecha;
    }

    public void setValorFecha(Date valorFecha) {
        this.valorFecha = valorFecha;
    }

    public String getValorStr() {
        return valorStr;
    }

    public void setValorStr(String valorStr) {
        this.valorStr = valorStr;
    }

}
