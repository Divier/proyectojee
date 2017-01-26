package co.com.datatools.c2.reporte.util;

/**
 * Clase que contiene las constantes utilizadas por el generador de reportes
 * 
 * @author dixon.alvarez
 * 
 */
public class ConstantesReporte {

    // Formatos establecidos para reportes
    private final static String FORMATO_FECHA = "dd/MM/yyyy";
    private final static String FORMATO_HORA = "HH:mm:ss";
    public final static String FORMATO_NUMERO = "#,##0.00";
    public final static String FORMATO_TEXTO = "@";

    // Caracteristicas para texto en reportes
    private final static int FUENTE_TAMANIO = 8;
    private final static String FUENTE_TIPO = "Arial";

    // Margenes utilizadas en reportes (Medidas en cm)
    private final static int MARGEN_HORIZONTAL = 2;
    private final static int MARGEN_VERTICAL = 2;

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
        return Integer.parseInt(System.getProperty(EnumPropiedadesReporte.FUENTE_TAMANIO.value, "" + FUENTE_TAMANIO));
    }

    public static String getFuenteTipo() {
        return System.getProperty(EnumPropiedadesReporte.FUENTE_TIPO.value, FUENTE_TIPO);
    }

    public static int getFuenteTamanioHeader() {
        return getFuenteTamanio() + 1;
    }

    public static long getMargenDerecho() {
        return Long.parseLong(System
                .getProperty(EnumPropiedadesReporte.MARGEN_HORIZONTAL.value, "" + MARGEN_HORIZONTAL));
    }

    public static long getMargenIzquierdo() {
        return Long.parseLong(System
                .getProperty(EnumPropiedadesReporte.MARGEN_HORIZONTAL.value, "" + MARGEN_HORIZONTAL));
    }

    public static long getMargenSuperior() {
        return Long.parseLong(System.getProperty(EnumPropiedadesReporte.MARGEN_VERTICAL.value, "" + MARGEN_VERTICAL));
    }

    public static long getMargenInferior() {
        return Long.parseLong(System.getProperty(EnumPropiedadesReporte.MARGEN_VERTICAL.value, "" + MARGEN_VERTICAL));
    }

    public static long getMargenLateralWord() {
        return Long.parseLong(System
                .getProperty(EnumPropiedadesReporte.MARGEN_HORIZONTAL.value, "" + MARGEN_HORIZONTAL)) * 360;
    }

}
