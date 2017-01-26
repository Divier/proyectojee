package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 007 - Asocia cada medio de imposición con un medio de notificación
 * 
 * @author giovanni.velandia 2015-10-13
 * 
 */
public class ConfiguraMediosImposicionNotificacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Entrada
     */
    // <descripcion>Tipo de comparendo usado, corresponde al medio de imposición.</descripcion>
    private List<String> medioImposicion;

    // <descripcion>Código del organismo de tránsito</descripcion>
    private List<String> codigoOrganismo;

    /*
     * Salida
     */
    // <descripcion>Forma en que se notifica el comparendo.</descripcion>
    private List<String> tipoNotificacion;

    public List<String> getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(List<String> medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public List<String> getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(List<String> tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public List<String> getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(List<String> codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}
