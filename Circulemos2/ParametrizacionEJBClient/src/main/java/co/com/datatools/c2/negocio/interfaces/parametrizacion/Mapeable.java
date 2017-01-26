/**
 * 
 */
package co.com.datatools.c2.negocio.interfaces.parametrizacion;

/**
 * Interface que permite mapear valores para servicios de la aplicación.
 * 
 * @author julio.pinzon
 * 
 */
public interface Mapeable {

    /**
     * Retorna el identificador del servicio que provee los mapeos.
     * 
     * @return Retorna el identificador del servicio que provee los mapeos.
     * @author julio.pinzon
     */
    public int getOrigen();

    /**
     * Retorna el valor del tipo de mapeo que utiliza el servicio.
     * 
     * @return Retorna el valor del tipo de mapeo que utiliza el servicio.
     * @author julio.pinzon
     */
    public Long getValue();
}
