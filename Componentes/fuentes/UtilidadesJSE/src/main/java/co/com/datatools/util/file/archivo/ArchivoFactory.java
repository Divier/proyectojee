package co.com.datatools.util.file.archivo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Fabrica de {@link IArchivo}
 * 
 * @author rodrigo.cruz
 * 
 */
public class ArchivoFactory {

    private static final String ERROR_LECTURA_ARCHIVO_EXCEL = "Error de lectura de archivo Excel";

    /**
     * Obtiene la clase concreta de {@link IArchivo} segun la enumeracion enviada
     * 
     * @param tipoArchivo
     * @param rutaTemporal
     *            Ruta en el servidor de aplicaciones con permisos temporales para escribir
     * @return {@link IArchivo}
     */
    public IArchivo obtenerArchivo(EnumTipoArchivo tipoArchivo, String rutaTemporal) {
        try {
            return tipoArchivo.getFabrica().getConstructor(String.class).newInstance(rutaTemporal);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(ERROR_LECTURA_ARCHIVO_EXCEL);
        }
    }

    /**
     * Obtiene la clase concreta de contenido {@link ContenidoArchivoDTO} segun la enumeracion enviada a partir de una lista de filas abstracta. Por
     * simplicidad, no se crea fabrica para esta jerarquia de clases, sino que se mantiene dentro de esta fabrica.
     * 
     * @param tipoArchivo
     * @param filaArchivoList
     * @return {@link ContenidoArchivoDTO}
     */
    public ContenidoArchivoDTO obtenerContenido(EnumTipoArchivo tipoArchivo, List<FilaArchivoDTO> filaArchivoList) {
        try {
            return tipoArchivo.getContenido().getConstructor(List.class, EnumTipoArchivo.class)
                    .newInstance(filaArchivoList, tipoArchivo);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(ERROR_LECTURA_ARCHIVO_EXCEL);
        }
    }

}
