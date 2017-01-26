package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 010 - Configuración medios de imposición para discriminar si la fecha de
 * notificación es igual a la fecha de imposición
 * 
 * @author giovanni.velandia 2015-10-13
 * 
 */
public class AsociaMedioImposicionTiposFechaDTO implements Serializable {

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
    // <descripcion>Indica si la fecha de notificación debe tomar el mismo valor de la fecha de imposición.</descripcion>
    private boolean esFechaImposicion;

    public List<String> getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(List<String> medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public boolean isEsFechaImposicion() {
        return esFechaImposicion;
    }

    public void setEsFechaImposicion(boolean esFechaImposicion) {
        this.esFechaImposicion = esFechaImposicion;
    }

    public List<String> getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(List<String> codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}
