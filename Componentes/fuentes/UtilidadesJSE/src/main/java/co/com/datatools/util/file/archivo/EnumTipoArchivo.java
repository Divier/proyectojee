package co.com.datatools.util.file.archivo;

/**
 * @author rodrigo.cruz
 * 
 */
public enum EnumTipoArchivo {

    XLS(ArchivoExcel.class, ContenidoArchivoExcelDTO.class), //
    XLSX(ArchivoExcel.class, ContenidoArchivoExcelDTO.class), //
    ;

    private Class<? extends IArchivo> fabrica;
    private Class<? extends ContenidoArchivoDTO> contenido;

    EnumTipoArchivo(Class<? extends IArchivo> fabrica, Class<? extends ContenidoArchivoDTO> contenido) {
        this.fabrica = fabrica;
        this.contenido = contenido;
    }

    public Class<? extends IArchivo> getFabrica() {
        return fabrica;
    }

    public Class<? extends ContenidoArchivoDTO> getContenido() {
        return contenido;
    }

}
