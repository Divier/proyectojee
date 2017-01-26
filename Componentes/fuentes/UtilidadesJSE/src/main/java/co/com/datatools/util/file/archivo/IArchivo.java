package co.com.datatools.util.file.archivo;

/**
 * Interfaz para realizar operaciones sobre archivos mediante abstraccion de contenido
 * 
 * @author rodrigo.cruz
 * 
 */
public interface IArchivo {

    /**
     * Leer un archivo a partir de un arreglo de bytes segun el tipo solicitado
     * 
     * @param archivo
     *            Arreglo de bytes (en memoria)
     * @param tipoArchivo
     *            {@link EnumTipoArchivo}
     * @return {@link ContenidoArchivoDTO}
     */
    public abstract ContenidoArchivoDTO leerArchivo(byte[] archivo, EnumTipoArchivo tipoArchivo);

    /**
     * Escribir un archivo a partir de un contenido abstracto segun el tipo solicitado
     * 
     * @param contenido
     *            Contenido abstracto en filas ({@link FilaArchivoDTO}) y celdas organizados en un arreglo
     * @param tipoArchivo
     *            {@link EnumTipoArchivo}
     * @return Arreglo de bytes
     */
    public abstract byte[] escribirArchivo(ContenidoArchivoDTO contenido, EnumTipoArchivo tipoArchivo);

}
