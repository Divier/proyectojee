package co.com.datatools.c2.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.enumeraciones.EnumCampoArchivoSimit;
import co.com.datatools.c2.enumeraciones.EnumServicioEnvioSimit;

/**
 * Utilidades utilizadas para armar el contenido del archivo simit
 * 
 * @author julio.pinzon
 * 
 */
public final class ArchivoSimitUtil {

    private static final Logger LOGGER = Logger.getLogger(ArchivoSimitUtil.class.getName());

    private static final String REEMPLAZA_SEPARADOR = " ";

    // Cache de valores de homologaciones
    private static Map<EnumServicioEnvioSimit, Map<String, String>> cacheHomologacion = null;

    public static final String FIN_LINEA = "\r\n";

    private ArchivoSimitUtil() {
    }

    /**
     * Calcula la sumatoria de ascii de la cadena de texto
     * 
     * @param cadena
     * @return Sumatoria ascii de la cadena de texto. Si es nulo devuelve 0
     * @author julio.pinzon
     */
    public static BigInteger obtenerSumatoriaAscii(String cadena) {
        LOGGER.debug("ArchivoSimitUtil::obtenerSumatoriaAscii(String)");
        BigInteger sumatoria = BigInteger.ZERO;
        cadena = cadena.trim();
        for (int i = 0; i < cadena.length(); i++) {
            sumatoria = sumatoria.add(new BigInteger(String.valueOf(cadena.codePointAt(i))));
        }
        System.out.println("SUMATORIA: " + sumatoria);
        return sumatoria;
    }

    /**
     * Reemplazar valor booleano por el valor que se necesita en el archivo
     * 
     * @param valorBooleano
     * @return Representacion en string del valor del booleano
     * @author julio.pinzon
     */
    public static String reemplazaBoleano(Boolean valorBooleano) {
        LOGGER.debug("ArchivoSimitUtil::reemplazaBoleano(Boolean)");
        String valor = ConstantesComparendo.VALOR_SI;
        if (valorBooleano == null) {
            valor = null;
        } else if (!valorBooleano) {
            valor = ConstantesComparendo.VALOR_NO;
        }
        return valor;
    }

    /**
     * Valida el valor enviado para armar la linea del archivo. Validando longitud y na no existencia del separador en la cadena de texto
     * 
     * @param valor
     * @return Valor validado concatenado con el separador enviado
     * @author julio.pinzon
     */
    public static String validarValor(String valor, String separador, EnumCampoArchivoSimit campo) {
        LOGGER.debug("ArchivoSimitUtil::validarValor(String, EnumCampoArchivoSimit)");
        String cadenaValidada = "";
        if (valor != null) {
            cadenaValidada = StringUtils.replace(valor, separador, REEMPLAZA_SEPARADOR);
            if (cadenaValidada.length() > campo.getLongitud()) {
                cadenaValidada = cadenaValidada.substring(0, campo.getLongitud() - 1);
            }
            cadenaValidada += separador;
        }
        return cadenaValidada;
    }

    /**
     * Valida si el valor de homologacion ya se encuentra en el cache
     * 
     * @param tipoServicio
     * @param valor
     * @return Valor encontrado, de lo contrario null
     * @author julio.pinzon
     */
    public static String obtenerValorHomologacionCache(EnumServicioEnvioSimit tipoServicio, String valor) {
        LOGGER.debug("ArchivoSimitUtil::valorHomologacionCache(EnumServicioEnvioSimit, String)");
        String valorHomologado = null;
        if (!cacheHomologacion.containsKey(tipoServicio)) {
            cacheHomologacion.put(tipoServicio, new HashMap<String, String>());
        } else if (cacheHomologacion.get(tipoServicio).containsKey(valor)) {
            valorHomologado = cacheHomologacion.get(tipoServicio).get(valor);
        }
        return valorHomologado;
    }

    /**
     * Agrega valor al cache de homologacion
     * 
     * @param tipoServicio
     * @param valorOrigen
     * @param valorHomologado
     */
    public static void agregarValorHomologacionCache(EnumServicioEnvioSimit tipoServicio, String valorOrigen,
            String valorHomologado) {
        LOGGER.debug("ArchivoSimitUtil::agregarValorHomologacionCache(EnumServicioEnvioSimit, String, String)");
        cacheHomologacion.get(tipoServicio).put(valorOrigen, valorHomologado);
    }

    /**
     * Limpia cache de homologacion
     * 
     * @author julio.pinzon
     */
    public static void limpiarCacheHomologacion() {
        LOGGER.debug("ArchivoSimitUtil::limpiarCacheHomologacion()");
        cacheHomologacion = null;
    }

    /**
     * Inicializa el cache
     * 
     * @author julio.pinzon
     */
    public static void inicializarCacheHomologacion() {
        LOGGER.debug("ArchivoSimitUtil::inicializarCacheHomologacion()");
        cacheHomologacion = new HashMap<EnumServicioEnvioSimit, Map<String, String>>();
    }

    /**
     * 
     * valida la longitud de una cadena
     * 
     * @param cadena
     * @param longitud
     * @return
     */
    public static String validaLongitud(String cadena, int longitud) {
        LOGGER.debug("ArchivoSimitUtil::validaLongitud(String,int)");
        if (cadena != null) {
            if (cadena.length() > longitud) {
                return cadena.substring(0, longitud);
            } else {
                return cadena;
            }
        }
        return cadena;
    }
}
