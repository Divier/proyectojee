package co.com.datatools.c2.constantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Constantes para carga de archivos
 * 
 * @author ricardo.chavarro
 *
 */
public class ConstantesCargaArchivos {
    public final static String FORMATO_FECHA_ARCHIVO = "MM-dd-yyyy_HH-mm-ss";
    public final static String NOMBRE_ARCHIVO_ANALISIS_CARTERA = "ConsultaAnalisisCartera";
    public final static String EXTENSION_CSV = ".csv";
    public static final String CSV_SEPARADOR = ";";
    public static final String LINE_SEPARATOR_PROP = "line.separator";
    public static final String DIRECCION_VALIDO_SI = "SI";
    public static final String DIRECCION_VALIDO_NO = "NO";
    public static final String NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC_DIR = "UBICABILIDAD_DIRECCIONES";
    public static final String NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC_TEL = "UBICABILIDAD_TELEFONOS";
    public static final String NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC_CORR = "UBICABILIDAD_CORREOS";
    public static final String NOMBRE_ARCHIVO_REPORTE_INCONSISTENCIAS = "INCONSISTENCIAS_ANALISIS_UBICABILIDAD";
    public static final String CONTENT_TYPE_CSV = "text/csv";

    /**
     * HU_CIR20_DAT_UBI_006
     */
    public final static String NOMBRE_ARCHIVO_CARGUE_MASIVO_UBIC = "CargueMasivoUbic";
    public static final String NOMBRE_INCONSISTENCIAS = "Inconsistencias";
    public static final String SEPARADOR_INCONSISTENCIAS = "-";
    public static final String RUTA_MANUALES_FILE_SYSTEM = "PATH_MANUALES";
    public static final String NOMBRE_INSTRUCTIVO = "MANUAL_ARCHIVO_CSV.pdf";
    public static final String UBICACION_SERVER = System.getProperty(RUTA_MANUALES_FILE_SYSTEM);
    public static final String NOMBRE_MULTAS_VALIDAS = "MultasValidas";
    public static final int LONGITUD_NOMBRE_ARCHIVO = 80;

    public static String getSeparadorLineaSistema() {
        return System.getProperty(LINE_SEPARATOR_PROP);
    }

    /**
     * Recorre un archivo tipo CSV y retorna el número de registros contenidos
     * 
     * @param csvFile
     * @return número de registros en el archivo
     */
    public static int getNumeroRegistrosCSV(File csvFile) {
        int numFilas = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                numFilas++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numFilas;
    }
}
