package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * DTO para respuestas del job de conciliar pago
 * 
 * @author julio.pinzon 2016-07-26
 * 
 */
public class RespuestaConciliarPagoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Total de recaudos consultados
     */
    private Integer totalRecaudosConsultados;

    /**
     * Total de recaudos conciliados
     */
    private Integer totalRecaudosConciliados;

    /**
     * Total de recaudos no conciliados
     */
    private Integer totalRecaudosNoConciliados;

    public Integer getTotalRecaudosConsultados() {
        return totalRecaudosConsultados;
    }

    public void setTotalRecaudosConsultados(Integer totalRecaudosConsultados) {
        this.totalRecaudosConsultados = totalRecaudosConsultados;
    }

    public Integer getTotalRecaudosConciliados() {
        return totalRecaudosConciliados;
    }

    public void setTotalRecaudosConciliados(Integer totalRecaudosConciliados) {
        this.totalRecaudosConciliados = totalRecaudosConciliados;
    }

    public Integer getTotalRecaudosNoConciliados() {
        return totalRecaudosNoConciliados;
    }

    public void setTotalRecaudosNoConciliados(Integer totalRecaudosNoConciliados) {
        this.totalRecaudosNoConciliados = totalRecaudosNoConciliados;
    }

}
