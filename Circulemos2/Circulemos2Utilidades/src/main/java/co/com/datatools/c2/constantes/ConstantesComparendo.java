package co.com.datatools.c2.constantes;

/**
 * Constantes relacionadas con operaciones sobre comparendos. (Mantener orden alfabético y agrupar por tipo de dato).
 * 
 * @author rodrigo.cruz
 */
public class ConstantesComparendo {

    public static final int LENGHT_NUM_COMPARENDO_COD_ORGANISMO = 8;
    public static final int LENGHT_NUM_COMPARENDO_CONSECUTIVO_COMPARENDO = 12;
    public static final int ID_NO_TIPO_INFRACCION = -999999;

    public static final String DIA_HABIL = "H";
    public static final String DIA_NO_HABIL = "NH";
    public static final String NOMBRE_NO_TIPO_INFRACCION = "(SIN TIPO)";
    public static final String REGEX_DIRECCION_BIS = "^BIS$";
    public static final String REGEX_DIRECCION_COMPLEMENTO = "[^\\<\\>\"'\\+\\|\\$¿\\?]{8,150}";
    public static final String REGEX_PLACA_VEHICULO = "[A-Z]{3}\\d{3}";
    public static final String REGEX_PLACA_MOTO = "[A-Z]{3}\\d{2}[A-Z]";
    public static final String USUARIO = "system";
    public static final String REGEX_CARACTER_REPETIDO = ".*(\\S)\\1{2,}.*";
    public static final String REGEX_MAS_DE_UNA_PALABRA = "^.*\\S+\\s+\\S.*";
    public static final String REGEX_DIRECCION_COMPLEMENTO_SIMPLE = "[^\\<\\>\"'\\+\\|\\$¿\\?]{0,150}";

    // Organismo Otros
    public static Integer CODIGO_OTROS = 0;
    public static String NOMBRE_OTROS = "label_option_otros";

    // Valores para archivo de simit
    public static final String VALOR_SI = "S";
    public static final String VALOR_NO = "N";
    public static final String VALOR_CERO = "0";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    public static final String FORMATO_HORA = "HHmm";

    // Notificacion
    public static final String FORMATO_FECHA_NOTIFICACION = "ddMMYYYY";
    public static final String EXTENSION_PDF = ".pdf";

    /**
     * Valores para las resoluciones de sancion
     */
    // Utilizado para obtener el tipo de documento adecuado para la resolucion de sancion
    public static final String RESOLUCION_SANCION = "RESOLUCION_SANCION_";

    // Variables de envio correo de resolucion automatica
    public static final String DIA_FECHA = "dia_fecha";
    public static final String MES_FECHA = "mes_fecha";
    public static final String ANIO_FECHA = "anio_fecha";
    public static final String DATOS_RESOLUCIONES = "datos_resoluciones";
    public static final String ORGANISMO = "organismo";
    public static final String CANTIDAD_RESOLUCIONES = "cantidad_resoluciones";

    // Valores de envio correo de resoluciones de sancion para el log
    public static final String VALOR_SI_CORREO = "SI";
    public static final String VALOR_NO_CORREO = "NO";

    // Tags de html para generar contenido de correo
    public static final String TAG_TD_INICIO = "<td>";
    public static final String TAG_TD_FIN = "</td>";
    public static final String TAG_TR_INICIO = "<tr>";
    public static final String TAG_TR_FIN = "</tr>";

    public static final String FORMATO_FECHA_CORREO_RESOLUCION = "yyyy-MM-dd HH:mm";

    // (pais Colombia) id 47
    public static final int ID_PAIS_COLOMBIA = 47;

    // Variables de envio por correo del reporte de inconsistencias de comparendos
    public static final String TOTAL_INCONSISTENCIAS = "total_inconsistencias";
    public static final String FECHA_REPORTE_INCONSISTENCIAS = "fecha_reporte_inconsistencias";
    public static final String NOMBRE_REPORTE_INCONSISTENCIAS = "reporte_inconsistencias";
}
