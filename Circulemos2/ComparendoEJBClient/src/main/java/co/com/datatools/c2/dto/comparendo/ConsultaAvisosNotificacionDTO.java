package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author diego.fonseca
 * 
 *         DTO Contiene los filtros para realizar la consulta de avisos de notificación por organismo de tránsito y fecha de generación del aviso
 */
public class ConsultaAvisosNotificacionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7887535815067508032L;

    int codigoOrganismo;
    Date fechaFinalGeneracion;
    Date fechaInicialGeneracion;

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaFinalGeneracion() {
        return fechaFinalGeneracion;
    }

    public void setFechaFinalGeneracion(Date fechaFinalGeneracion) {
        this.fechaFinalGeneracion = fechaFinalGeneracion;
    }

    public Date getFechaInicialGeneracion() {
        return fechaInicialGeneracion;
    }

    public void setFechaInicialGeneracion(Date fechaInicialGeneracion) {
        this.fechaInicialGeneracion = fechaInicialGeneracion;
    }

}
