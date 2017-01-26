package co.com.datatools.c2.enumeracion;

/**
 * @author robert.bautista
 * @since 2013-11-19
 */
public enum EnumMedioBusquedaArchivoAsobancaria {

    NO_ESTABLECIDO(""), //
    MEDIO_BUSQUEDA_FTP("FTP"), //
    MEDIO_BUSQUEDA_EMAIL("MAIL"), //
    ;

    /**
     * Contiene el nombre de la fuente donde se extaen los archivos
     */
    private String nombreMedio;

    /**
     * Constructor con nombre y código de la fuente donde se extraen los archivos
     * 
     * @param nombreMedio
     *            nombre de la fuente
     */
    private EnumMedioBusquedaArchivoAsobancaria(String nombreMedio) {
        this.nombreMedio = nombreMedio;
    }

    /**
     * Retorna el nombre de la fuente donde se extraen los archivos
     * 
     * @return pk con el nombre de la fuente
     */
    public String getNombreMedio() {
        return this.nombreMedio;
    }

}
