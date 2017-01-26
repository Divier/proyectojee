package co.com.datatools.c2.util;

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeraciones.EnumCatalogo;

/**
 * Utilidades para replicar recaudo
 * 
 * @author julio.pinzon 2016-05-26
 * 
 */
public final class RecaudoUtil {

    private static final Logger LOGGER = Logger.getLogger(RecaudoUtil.class.getName());

    // Cache de valores de homologaciones
    private static Map<EnumCatalogo, Map<String, Integer>> cacheHomologacion = null;

    private RecaudoUtil() {
    }

    /**
     * Valida si el valor de homologacion ya se encuentra en el cache
     * 
     * @param catalogo
     * @param codigo
     * @return Valor encontrado, de lo contrario null
     * @author julio.pinzon
     */
    public static Integer obtenerValorHomologacionCache(EnumCatalogo catalogo, String codigo) {
        LOGGER.debug("RecaudoUtil::valorHomologacionCache(EnumServicioEnvioSimit, String)");
        Integer valorHomologado = null;
        if (!cacheHomologacion.containsKey(catalogo)) {
            cacheHomologacion.put(catalogo, new HashMap<String, Integer>());
        } else if (cacheHomologacion.get(catalogo).containsKey(codigo)) {
            valorHomologado = cacheHomologacion.get(catalogo).get(codigo);
        }
        return valorHomologado;
    }

    /**
     * Agrega valor al cache de homologacion
     * 
     * @param catalogo
     * @param valorOrigen
     * @param valorHomologado
     */
    public static void agregarValorHomologacionCache(EnumCatalogo catalogo, String valorOrigen,
            Integer valorHomologado) {
        LOGGER.debug("RecaudoUtil::agregarValorHomologacionCache(EnumServicioEnvioSimit, String, Integer)");
        cacheHomologacion.get(catalogo).put(valorOrigen, valorHomologado);
    }

    /**
     * Limpia cache de homologacion
     * 
     * @author julio.pinzon
     */
    public static void limpiarCacheHomologacion() {
        LOGGER.debug("RecaudoUtil::limpiarCacheHomologacion()");
        cacheHomologacion = null;
    }

    /**
     * Inicializa el cache
     * 
     * @author julio.pinzon
     */
    public static void inicializarCacheHomologacion() {
        LOGGER.debug("RecaudoUtil::inicializarCacheHomologacion()");
        cacheHomologacion = new HashMap<EnumCatalogo, Map<String, Integer>>();
    }
}
