package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los ids de los tipos de archivos manejados en la aplicación. Su contenido debe corresponder al contenido de la tabla tipo_archivo
 * 
 * @author robert.bautista
 * @since 2013-10-29
 */
public enum EnumTipoArchivo implements SearchableEnumeration<Integer> {

    ARCHIVO_RECAUDO_COMPARENDO_SIMIT(1), //
    ARCHIVO_RECAUDO_ASOBANCARIA(2), //
    ;

    private int codigo;

    /**
     * Constructor que asigna el código a la enumeración.
     * 
     * @param codigo
     */
    EnumTipoArchivo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    @Override
    public Integer getValue() {
        return this.codigo;
    }
}
