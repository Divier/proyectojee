package co.com.datatools.c2.dto.ubicabilidad;

import java.io.Serializable;

/**
 * Respuesta de procesamiento de lectura de registros de ubicabilidad desde terceros.
 * 
 * @author Divier Casas
 * @version 1.0
 * @created 17-may-2016 08:26:01 a.m.
 */
public class RespuestaSolicitudUbicabilidadTerceroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer totalRegUbicLeidos;
    private Integer totalRegUbicRecibidos;
    private Integer totalRegUbicNoRecibidos;

    public RespuestaSolicitudUbicabilidadTerceroDTO() {
        super();
    }

    public Integer getTotalRegUbicLeidos() {
        return totalRegUbicLeidos;
    }

    public void setTotalRegUbicLeidos(Integer totalRegUbicLeidos) {
        this.totalRegUbicLeidos = totalRegUbicLeidos;
    }

    public Integer getTotalRegUbicRecibidos() {
        return totalRegUbicRecibidos;
    }

    public void setTotalRegUbicRecibidos(Integer totalRegUbicRecibidos) {
        this.totalRegUbicRecibidos = totalRegUbicRecibidos;
    }

    public Integer getTotalRegUbicNoRecibidos() {
        return totalRegUbicNoRecibidos;
    }

    public void setTotalRegUbicNoRecibidos(Integer totalRegUbicNoRecibidos) {
        this.totalRegUbicNoRecibidos = totalRegUbicNoRecibidos;
    }
}