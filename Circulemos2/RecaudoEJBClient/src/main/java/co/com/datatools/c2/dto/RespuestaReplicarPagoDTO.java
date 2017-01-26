package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * DTO para respuestas del job de replicar pago
 * 
 * @author julio.pinzon 2016-05-05
 * 
 */
public class RespuestaReplicarPagoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Total de recaudos leidos
     */
    private Integer totalRecaudosLeidos;

    /**
     * Total de recaudos recibidos
     */
    private Integer totalRecaudosRecibidos;

    /**
     * Total de recaudos no recibidos
     */
    private Integer totalRecaudosNoRecibidos;

    public Integer getTotalRecaudosLeidos() {
        return totalRecaudosLeidos;
    }

    public void setTotalRecaudosLeidos(Integer totalRecaudosLeidos) {
        this.totalRecaudosLeidos = totalRecaudosLeidos;
    }

    public Integer getTotalRecaudosRecibidos() {
        return totalRecaudosRecibidos;
    }

    public void setTotalRecaudosRecibidos(Integer totalRecaudosRecibidos) {
        this.totalRecaudosRecibidos = totalRecaudosRecibidos;
    }

    public Integer getTotalRecaudosNoRecibidos() {
        return totalRecaudosNoRecibidos;
    }

    public void setTotalRecaudosNoRecibidos(Integer totalRecaudosNoRecibidos) {
        this.totalRecaudosNoRecibidos = totalRecaudosNoRecibidos;
    }

}
