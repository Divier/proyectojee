package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

/**
 * Respuesta de procesamiento de lectura de comparnedos desde terceros.
 * 
 * @author Luis Forero
 * @version 1.0
 * @created 06-may-2016 08:26:01 a.m.
 */
public class RespuestaSolicitudComparendosDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Total de los comparendos que fueron leidos desde la fuente externa.
     */
    private Integer totalComparendosLeidos;
    /**
     * Total de comparendos que fueron procesados satisfactoriamente sin ningún rechazo o inconsistencias
     */
    private Integer totalComparendosRecibidos;
    /**
     * Total de comparendos que no lograron ser procesados por el sistema ya que fueron o rechazados, inconsistentes o no pudieron ser homologados los
     * datos del origen externo.
     */
    private Integer totalComparendosNoRecibidos;

    public RespuestaSolicitudComparendosDTO() {

    }

    public void finalize() throws Throwable {

    }

    public Integer getTotalComparendosLeidos() {
        return totalComparendosLeidos;
    }

    public void setTotalComparendosLeidos(Integer totalComparendosLeidos) {
        this.totalComparendosLeidos = totalComparendosLeidos;
    }

    public Integer getTotalComparendosNoRecibidos() {
        return totalComparendosNoRecibidos;
    }

    public void setTotalComparendosNoRecibidos(Integer totalComparendosNoRecibidos) {
        this.totalComparendosNoRecibidos = totalComparendosNoRecibidos;
    }

    public Integer getTotalComparendosRecibidos() {
        return totalComparendosRecibidos;
    }

    public void setTotalComparendosRecibidos(Integer totalComparendosRecibidos) {
        this.totalComparendosRecibidos = totalComparendosRecibidos;
    }

}
