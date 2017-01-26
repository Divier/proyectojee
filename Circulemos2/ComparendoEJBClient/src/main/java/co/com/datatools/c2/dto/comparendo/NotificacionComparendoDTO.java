package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Contiene el listado de comparendos para cambiar su tipo de notificación a la indicada.
 * 
 * @author julio.pinzon
 * 
 */
public class NotificacionComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    /**
     * Identificador del tipo de notificación de los comparendos.
     */
    private int idTipoNotificacion;

    /**
     * Fecha del notificación de los comparendos.
     */
    private Date fechaNotificacion;

    /**
     * Contiene los números de los comparendos que a los cuales se cambiarán de tipo de notificación.
     */
    private List<String> comparendos;

    // --- Constructor
    public NotificacionComparendoDTO() {
    }

    public int getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(int idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public List<String> getComparendos() {
        return comparendos;
    }

    public void setComparendos(List<String> comparendos) {
        this.comparendos = comparendos;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

}