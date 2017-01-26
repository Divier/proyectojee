package co.com.datatools.c2.enumeracion;

/**
 * Enumeración que representa los registros del archivo de asobancaria.
 * 
 * @author robert.bautista
 * @since 2013-12-05
 */
public enum EnumArchivoAsobancaria {

    REGISTRO_ENCABEZADO_ARCHIVO("01", 55), // Encabezado de archivo
    REGISTRO_ENCABEZADO_LOTE("05", 19), // Encabezado de lote
    REGISTRO_DETALLE("06", 97), // Detalle
    REGISTRO_CONTROL_LOTE("08", 33), // Control de lote
    REGISTRO_CONTROL_ARCHIVO("09", 29), // Control de archivo
    ;

    public static final int LONGITUD_REGISTRO = 162;

    /**
     * Contiene el tipo de registro
     */
    private final String tipoRegistro;

    /**
     * Contiene la longitud de los campos del registro sin el reservado
     */
    private final int tamanioLleno;

    /**
     * @param tipoRegistro
     * @param tamanioLleno
     */
    private EnumArchivoAsobancaria(String tipoRegistro, int tamanioLleno) {
        this.tipoRegistro = tipoRegistro;
        this.tamanioLleno = tamanioLleno;
    }

    /**
     * @return the tipoRegistro
     */
    public String getTipoRegistro() {
        return this.tipoRegistro;
    }

    /**
     * @return the tamanioLleno
     */
    public int getTamanioLleno() {
        return this.tamanioLleno;
    }

    /**
     * Retorna true si el tipo de registro indicado es uno de los asociados a la enumeración
     * 
     * @param tipoRegistro
     *            el tipo de registro a verificar
     * 
     * @return true si existe
     */
    public static boolean esTipoDefinido(String tipoRegistro) {
        boolean definido = false;
        EnumArchivoAsobancaria[] valores = EnumArchivoAsobancaria.values();

        for (int i = 0; i < valores.length; i++) {
            if (valores[i].getTipoRegistro().equals(tipoRegistro)) {
                definido = true;
                break;
            }
        }

        return definido;
    }

}
