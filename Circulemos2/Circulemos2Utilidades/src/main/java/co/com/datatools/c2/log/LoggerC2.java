/**
 * 
 */
package co.com.datatools.c2.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import co.com.datatools.c2.enumeracion.EnumLogSistema;

/**
 * @author julio.pinzon
 * 
 */
public class LoggerC2 {
    /**
     * Mapa que guarda la instancias para crearlas una sola vez
     */
    private static Map<EnumLogSistema, ILog> instances;

    /**
     * 
     */
    private LoggerC2() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retorna el log requerido acorde a la implementación.
     * 
     * @param logSistema
     * @return Lo requerido acorde con implementacion
     */
    public static ILog getLogger(EnumLogSistema logSistema) {
        ILog log = null;
        if (logSistema != null) {
            if (instances == null) {
                instances = new ConcurrentHashMap<EnumLogSistema, ILog>();
            }
            if (!instances.containsKey(logSistema)) {
                instances.put(logSistema, new ArchivoLogJBoss(logSistema));
            }
            log = instances.get(logSistema);
        }
        return log;
    }

}
