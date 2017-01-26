/**
 * 
 */
package co.com.datatools.c2.log;

/**
 * Interface que suple los servicios requeridos para realizar logs de operaciones del sistema.
 * 
 * @author julio.pinzon
 * 
 */
public interface ILog {

    /**
     * Escribe los datos y la información recibida en el log indicado.
     * 
     * @param informacion
     * @param datos
     */
    public void escribir(String informacion, ILogueable datos);
}
