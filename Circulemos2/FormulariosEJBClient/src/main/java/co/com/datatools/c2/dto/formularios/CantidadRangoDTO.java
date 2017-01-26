package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;

/**
 * DTO util para asignacion de rangos de formularios
 * 
 * @author rodrigo.cruz
 * 
 */
public class CantidadRangoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int cantidad;
    private long idRango;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdRango() {
        return idRango;
    }

    public void setIdRango(long idRango) {
        this.idRango = idRango;
    }

}
