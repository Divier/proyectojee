package co.com.datatools.util.file;

import org.apache.commons.lang3.StringUtils;

/**
 * Utilidades para procesar rutas de archivos
 * 
 * @author felipe.martinez
 */
public class RutaArchivoUtil {

    /**
     * Permite reemplazar el caracter ~ al inicio de la cadena por la ruta del usuario del sistema
     * 
     * @param ruta
     *            cadena a procesar
     * @return cadena precedida por la ruta del usuario si aplica
     */
    public static String procesarRutaUsuario(String ruta) {
        final String rutaUsuario = System.getProperty("user.home");
        if (StringUtils.isNotBlank(ruta) && ruta.startsWith("~")) {
            ruta = rutaUsuario + ruta.substring(1);
        }
        return ruta;
    }

}
