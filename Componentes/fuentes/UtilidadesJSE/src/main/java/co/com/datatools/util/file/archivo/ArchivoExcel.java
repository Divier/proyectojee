package co.com.datatools.util.file.archivo;

/**
 * Clases para realizar operaciones sobre archivos MICROSOFT EXCEL (XLS, XLSX) mediante abstraccion de contenido
 * 
 * @author rodrigo.cruz
 * 
 */
public class ArchivoExcel implements IArchivo {

    private String rutaTemporal;

    public ArchivoExcel() {

    }

    public ArchivoExcel(String rutaTemporal) {
        this.rutaTemporal = rutaTemporal;
    }

    @Override
    public ContenidoArchivoDTO leerArchivo(byte[] contenido, EnumTipoArchivo tipoArchivo) {
        switch (tipoArchivo) {
        case XLS:
        case XLSX:
            return new ContenidoArchivoExcelDTO(contenido, tipoArchivo, rutaTemporal);
        default:
            return null;
        }
    }

    @Override
    public byte[] escribirArchivo(ContenidoArchivoDTO contenido, EnumTipoArchivo tipoArchivo) {
        switch (tipoArchivo) {
        case XLS:
        case XLSX:
            return ((ContenidoArchivoExcelDTO) contenido).getBytes();
        default:
            return null;
        }
    }

}
