package co.com.datatools.c2.enumeracion;

/**
 * Contiene los posibles valores de los medios de b�squeda para el cargue de archivos SIMIT.
 * 
 * @author robert.bautista
 * @since 2013-11-19
 */
public enum EnumMedioBusquedaArchivoSimit {
    NO_ESTABLECIDO("", -1), //
    MEDIO_BUSQUEDA_FTP("FTP", 1), //
    MEDIO_BUSQUEDA_EMAIL("MAIL", 2), //
    ;

    /**
     * Contiene el nombre de la fuente donde se extaen los archivos
     */
    private String pk;

    /**
     * Contiene el c�digo de la fuente donde se extraen los archivos
     */
    private int codigo;

    /**
     * Constructor con nombre y c�digo de la fuente donde se extraen los archivos
     * 
     * @param pk
     *            nombre de la fuente
     * 
     * @param codigo
     *            c�digo de la fuente
     */
    private EnumMedioBusquedaArchivoSimit(String pk, int codigo) {
        this.pk = pk;
        this.codigo = codigo;
    }

    /**
     * Retorna el nombre de la fuente donde se extraen los archivos
     * 
     * @return pk con el nombre de la fuente
     */
    public String getPk() {
        return pk;
    }

    /**
     * Retorna el c�digo de la fuente de donde se extraen los archivos
     * 
     * @return c�digo de la fuente
     */
    public int getCodigo() {
        return this.codigo;
    }

}