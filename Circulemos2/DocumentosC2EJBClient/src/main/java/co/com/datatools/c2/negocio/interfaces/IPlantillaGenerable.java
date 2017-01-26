/**
 * 
 */
package co.com.datatools.c2.negocio.interfaces;

import java.io.Serializable;

/**
 * Interface para generar documento
 * 
 * @author julio.pinzon 2016-09-06
 *
 */
public interface IPlantillaGenerable extends Serializable {

    /**
     * Devuelve la ubicacion del documento
     * 
     * @return
     */
    public String getUbicacion();

    /**
     * Devuelve el codigo de plantilla para genera un documento
     * 
     * @return
     */
    public String getCodigoPlantilla();

    /**
     * Devuelve la descripcion de la plantilla
     * 
     * @return
     */
    public String getDescripcion();
}
