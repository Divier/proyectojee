package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 010 - Configuraci�n medios de imposici�n para discriminar si la fecha de
 * notificaci�n es igual a la fecha de imposici�n
 * 
 * @author giovanni.velandia 2015-10-13
 * 
 */
public class AsociaMedioImposicionTiposFechaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Entrada
     */
    // <descripcion>Tipo de comparendo usado, corresponde al medio de imposici�n.</descripcion>
    private List<String> medioImposicion;

    // <descripcion>C�digo del organismo de tr�nsito</descripcion>
    private List<String> codigoOrganismo;

    /*
     * Salida
     */
    // <descripcion>Indica si la fecha de notificaci�n debe tomar el mismo valor de la fecha de imposici�n.</descripcion>
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
