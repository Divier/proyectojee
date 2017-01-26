package co.com.datatools.util.file.archivo;

import java.util.List;

/**
 * Contenido abstracto de un archivo organizado por filas y columnas o mediante un arreglo de bytes (archivo del sistema en memoria)
 * 
 * @author rodrigo.cruz
 * 
 */
public abstract class ContenidoArchivoDTO {

    protected byte[] contenido;
    protected EnumTipoArchivo tipoArchivo;
    protected List<FilaArchivoDTO> filaArchivoList;
    protected String rutaTemporal;

    public ContenidoArchivoDTO(byte[] contenido, EnumTipoArchivo tipoArchivo) {
        this.contenido = contenido;
        this.tipoArchivo = tipoArchivo;
    }

    public ContenidoArchivoDTO(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        this.filaArchivoList = filaArchivoList;
        this.tipoArchivo = tipoArchivo;
    }

    public ContenidoArchivoDTO(byte[] contenido, EnumTipoArchivo tipoArchivo, String rutaTemporal) {
        this.contenido = contenido;
        this.tipoArchivo = tipoArchivo;
        this.rutaTemporal = rutaTemporal;
    }

    /**
     * Obtener el archivo en forma de contenido abstracto {@link ContenidoArchivoDTO#filaArchivoList} a partir de un arreglo de bytes
     * 
     * @return
     */
    public abstract List<FilaArchivoDTO> getFilas();

    /**
     * Obtener el archivo en forma de arreglo de bytes a partir de un contenido abstracto {@link ContenidoArchivoDTO}
     * 
     * @return
     */
    public abstract byte[] getBytes();

}
