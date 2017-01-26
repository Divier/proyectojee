package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * DTO para respuestas del job de replicar financiaciones
 * 
 * @author julio.pinzon 2016-05-05
 * 
 */
public class RespuestaReplicarFinanciacionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Total de financiaciones leidos
     */
    private Integer totalFinanciacionesLeidos;

    /**
     * Total de financiaciones recibidos
     */
    private Integer totalFinanciacionesRecibidos;

    /**
     * Total de financiaciones no recibidos
     */
    private Integer totalFinanciacionesNoRecibidos;

    public Integer getTotalFinanciacionesLeidos() {
        return totalFinanciacionesLeidos;
    }

    public void setTotalFinanciacionesLeidos(Integer totalFinanciacionesLeidos) {
        this.totalFinanciacionesLeidos = totalFinanciacionesLeidos;
    }

    public Integer getTotalFinanciacionesRecibidos() {
        return totalFinanciacionesRecibidos;
    }

    public void setTotalFinanciacionesRecibidos(Integer totalFinanciacionesRecibidos) {
        this.totalFinanciacionesRecibidos = totalFinanciacionesRecibidos;
    }

    public Integer getTotalFinanciacionesNoRecibidos() {
        return totalFinanciacionesNoRecibidos;
    }

    public void setTotalFinanciacionesNoRecibidos(Integer totalFinanciacionesNoRecibidos) {
        this.totalFinanciacionesNoRecibidos = totalFinanciacionesNoRecibidos;
    }

}
