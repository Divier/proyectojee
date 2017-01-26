package co.com.datatools.util.file.archivo;

/**
 * Clase que contiene las constantes utilizadas por el generador de archivos
 * 
 * 
 */
public class ConstantesArchivo {

    // Formatos establecidos para reportes
    private final static String FORMATO_FECHA = "dd/MM/yyyy";
    private final static String FORMATO_HORA = "HH:mm:ss";
    private final static String FORMATO_NUMERO = "#,##0.00";
    private final static String FORMATO_TEXTO = "@";

    // Caracteristicas para texto en reportes
    private final static int FUENTE_TAMANIO = 8;
    private final static String FUENTE_TIPO = "Arial";

    public static String getFormatoFecha() {
        return FORMATO_FECHA;
    }

    public static String getFormatoHora() {
        return FORMATO_HORA;
    }

    public static String getFormatoNumero() {
        return FORMATO_NUMERO;
    }

    public static String getFormatoTexto() {
        return FORMATO_TEXTO;
    }

    public static int getFuenteTamanio() {
        return FUENTE_TAMANIO;
    }

    public static String getFuenteTipo() {
        return FUENTE_TIPO;
    }

}
