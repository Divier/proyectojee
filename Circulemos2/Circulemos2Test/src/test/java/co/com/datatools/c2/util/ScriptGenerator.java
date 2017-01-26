package co.com.datatools.c2.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase utilitaria para crear archivos script para insertar datos en catalogos
 * 
 * @author rodrigo.cruz
 * 
 */
public class ScriptGenerator {

    private static final String FORMATO_SQL = "SQL";
    private static final String FORMATO_XLS = "XLS";
    private static final String FORMATO_XLSX = "XLSX";
    private static final String PREFIJO_NO_PROCESAR = "NOSQL-";

    enum EnumSimbolos {

        COMILLA_SIMPLE("'"), //
        COMA(","), //
        ESPACIO(" "), //
        NUEVA_LINEA("\r\n"), //
        PARENTESIS_IZQ("("), //
        PARENTESIS_DER(")"), //
        PUNTO("."), //
        PUNTO_Y_COMA(";"), //
        ;

        private String simbolo;

        private EnumSimbolos(String simbolo) {
            this.simbolo = simbolo;
        }

        public String getSimbolo() {
            return simbolo;
        }

        public void setSimbolo(String simbolo) {
            this.simbolo = simbolo;
        }

    }

    enum EnumFragmentoSQL {

        COMMENT("-- "), //
        INSERT("INSERT INTO"), //
        NULL("NULL"), //
        TRUNCATE_TABLE("TRUNCATE TABLE"), //
        VALUES("VALUES"), //
        ;

        private String fragmento;

        private EnumFragmentoSQL(String fragmento) {
            this.fragmento = fragmento;
        }

        public String getFragmento() {
            return fragmento;
        }

        public void setFragmento(String fragmento) {
            this.fragmento = fragmento;
        }

    }

    private boolean esXLS;
    private boolean scriptPorTabla;

    private FileInputStream archivo;

    private String nombreArchivo;

    private static int consecutivoArchivo = 0;

    /**
     * Permite generar scripts sql basados en archivos excel
     * 
     * @param args
     *            [0] - (obligatorio) listado de archivos excel de entrada, separados por ';'<br>
     *            [1] - (opcional) ruta de los archivos de salida
     */
    public static void main(String[] args) {
        try {

            if (args.length <= 0) {
                System.err.println("ERROR: Se necesitan argumentos: URL Entrada {, URL Salida}");
            } else if (args.length == 1) {
                System.out.println("Archivos Entrada: " + args[0]);
                System.out.println("Ruta de salida: " + System.getProperty("user.dir"));
                generarArchivoScript(args[0], System.getProperty("user.dir"));
            } else if (args.length > 1) {
                System.out.println("Archivos Entrada: " + args[0]);
                System.out.println("Ruta de salida: " + args[1]);
                generarArchivoScript(args[0], args[1]);
            }

            System.out.println("FINALIZADO");
        } catch (IOException e) {
            System.err.println("ERROR: No se pudo generar archivo: " + e.getMessage());
        }
    }

    /**
     * Constructor
     * 
     * @param scriptPorTabla
     *            {@code true} si debe crear scripts por tabla
     */
    public ScriptGenerator(boolean scriptPorTabla) {
        this.scriptPorTabla = scriptPorTabla;
    }

    /**
     * Valida el formato del archivo enviado (XLS o XLSX)
     * 
     * @param url
     *            La ruta y el nombre del archivo con extension
     */
    private void validarFormatoXLS(String url) {
        if (url.toUpperCase().endsWith(FORMATO_XLS)) {
            esXLS = true;
        } else if (url.toUpperCase().endsWith(FORMATO_XLSX)) {
            esXLS = false;
        } else {
            throw new RuntimeException("Formato de archivo inválido");
        }
    }

    /**
     * Metodo publico invocado por un cliente para generar uno o varios archivos SQL que contienen sentencias INSERT para insertar datos en tablas de
     * catalogos a partir de un archivo de tipo XLS o XLSX.
     * 
     * @param rutasEntrada
     *            La ruta de ubicacion del archivo XLS o XLSX en el sistema de archivos local que contiene los registros de uno o varios catalogos,
     *            cada uno separado por hoja del libro.
     * @param urlSalida
     *            La ruta del directorio adonde se guarda el script o scripts SQL generados a partir de los datos del archivo de entrada.
     * @throws FileNotFoundException
     *             Si ocurre un error al crear el archivo de entrada o si el formato del este archivo es diferente XLS o XLSX.
     * @throws IOException
     *             Si ocurre un error al leer el archivo de entrada o al crear el script o scripts de salida.
     */
    public static void generarArchivoScript(String rutasEntrada, String urlSalida) throws FileNotFoundException,
            IOException {
        final StringTokenizer tokens = new StringTokenizer(rutasEntrada, EnumSimbolos.PUNTO_Y_COMA.getSimbolo());
        while (tokens.hasMoreElements()) {
            final ScriptGenerator scriptGenerator = new ScriptGenerator(false);

            final String urlArchivoEntrada = tokens.nextToken();
            System.err.println("Procesando archivo: " + urlArchivoEntrada);
            List<Sheet> hojas = scriptGenerator.leerExcelEntrada(urlArchivoEntrada);

            scriptGenerator.generarScript(
                    hojas,
                    urlSalida,
                    scriptGenerator.nombreArchivo.toLowerCase().substring(0,
                            scriptGenerator.nombreArchivo.lastIndexOf('.')));
        }
    }

    /**
     * Valida el tipo de formato del archivo de entrada y genera el arreglo de hojas a partir de este.
     * 
     * @param urlEntrada
     *            La ruta de ubicacion del archivo XLS o XLSX en el sistema de archivos local que contiene los registros de uno o varios catalogos,
     *            cada uno separado por hoja del libro.
     * @return Una lista de objetos {@link Sheet} del archivo
     * @throws IOException
     *             Si ocurre un error al generar el archivo de entrada
     */
    private List<Sheet> leerExcelEntrada(String urlEntrada) throws IOException {
        List<Sheet> hojas = new ArrayList<Sheet>();
        // -------------------------------------
        // 1. Validar y ajustar formato
        // -------------------------------------
        validarFormatoXLS(urlEntrada);
        // -------------------------------------
        // 2. Crear archivo de entrada
        // -------------------------------------
        try {
            final File file = new File(urlEntrada);
            archivo = new FileInputStream(file);
            nombreArchivo = file.getName();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró la ruta del archivo en entrada", e);
        }

        try {
            Workbook workbook = null;

            if (esXLS) {
                workbook = new HSSFWorkbook(archivo);
            } else {
                workbook = new XSSFWorkbook(archivo);
            }

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet hoja = workbook.getSheetAt(i);
                hojas.add(hoja);
            }
        } catch (IOException e) {
            throw new IOException("Error al leer archivo de entrada", e.getCause());
        }

        return hojas;
    }

    /**
     * Crea un archivo de formato SQL que contiene el script de insercion de filas de una o varias tablas, obtenidas del archivo referenciado en la
     * URL de salida.
     * 
     * @param urlSalida
     *            Direccion en formato URL de la ubicacion del directorio de salida
     * @param nombreArchivo
     *            nombre base del archivo de salida
     * @param consecutivo
     *            Una cadena con el consecutivo con ceros a la izquierda
     * @return Un objeto {@link BufferedWriter} que contiene la referencia al bufer de escritura al archivo de salida
     * @throws IOException
     *             Cuando ocurre un error al generar el archivo de salida
     */
    private BufferedWriter crearArchivoSalida(String urlSalida, String nombreArchivo, String consecutivo) {
        if (consecutivo == null) {
            consecutivo = "";
        }

        StringBuilder archivoSalida = new StringBuilder();
        archivoSalida.append(urlSalida);
        archivoSalida.append(File.separator);
        archivoSalida.append(consecutivo + "_" + nombreArchivo);
        archivoSalida.append(EnumSimbolos.PUNTO.getSimbolo());
        archivoSalida.append(FORMATO_SQL.toLowerCase());

        BufferedWriter out = null;

        try {

            out = new BufferedWriter(new FileWriter(archivoSalida.toString()));
            System.out.println("Creacion archivo: " + archivoSalida);
        } catch (IOException e) {
            throw new RuntimeException("Error al crear archivo de salida", e);
        }

        return out;
    }

    /**
     * Genera script(s) de un conjunto de hojas. <br>
     * <br>
     * <i>Si se genera por tabla:</i>
     * <ol>
     * <li>Recorre hojas:
     * <ol>
     * <li>Genera consecutivo de hoja
     * <li>Crea archivo
     * <li>Genera sentencias SQL
     * <li>Guarda el archivo
     * </ol>
     * </ol>
     * <i>Si se genera completo:</i> <br>
     * <br>
     * <ol>
     * <li>Crea archivo
     * <li>Recorre hojas:
     * <ol>
     * <li>Genera consecutivo de hoja
     * <li>Genera sentencia SQL
     * <li>Escribe sentencia en archivo
     * </ol>
     * <li>Guarda el archivo
     * </ol>
     * 
     * @param sheets
     *            Una lista de objetos genericos tipo {@link Sheet}
     * @param urlSalida
     *            Direccion en formato URL de la ubicacion del directorio de salida
     * @throws IOException
     */
    private <T extends Sheet> void generarScript(List<T> sheets, String urlSalida, String nombreArchivo)
            throws IOException {
        String consecutivo = new String();

        BufferedWriter out = null;

        ListIterator<T> sheetIterator = sheets.listIterator();

        consecutivo = generarConsecutivo(consecutivoArchivo);
        if (scriptPorTabla) {
            int consecutivoHoja = 1000;

            while (sheetIterator.hasNext()) {
                T hoja = sheetIterator.next();
                if (hoja.getSheetName().startsWith(PREFIJO_NO_PROCESAR)) {
                    continue;
                }
                out = crearArchivoSalida(urlSalida, nombreArchivo + "_" + consecutivoHoja + "_" + hoja.getSheetName(),
                        consecutivo);
                procesarHoja(hoja, out);
                out.close();
                consecutivoHoja++;
            }
        } else {
            // 1. Generar sentencias TRUNCATE
            out = crearArchivoSalida(urlSalida, "truncate_" + nombreArchivo, consecutivo);
            ListIterator<T> inverseIterator = sheets.listIterator(sheets.size());
            while (inverseIterator.hasPrevious()) {
                T hoja = inverseIterator.previous();
                if (hoja.getSheetName().startsWith(PREFIJO_NO_PROCESAR)) {
                    continue;
                }
                generarSentenciaTruncate(hoja, out);
            }
            out.close();

            // 2. Generar sentencias INSERT
            out = crearArchivoSalida(urlSalida, nombreArchivo, consecutivo);
            while (sheetIterator.hasNext()) {
                T hoja = sheetIterator.next();
                if (hoja.getSheetName().startsWith(PREFIJO_NO_PROCESAR)) {
                    continue;
                }
                procesarHoja(hoja, out);
            }

            out.close();
        }
        consecutivoArchivo++;
    }

    private String generarConsecutivo(int numero) {
        if (numero < 10) {
            return "0" + numero;
        }
        return "" + numero;
    }

    /**
     * Crea una sentencia SQL de borrado de datos de una tabla en la hoja enviada como parametro.
     * 
     * @param hoja
     *            Objeto {@link Sheet} que referencia el contenido de una hoja de un archivo XLS o XLSX de entrada
     * @param out
     *            Un objeto {@link BufferedWriter} que contiene la referencia al bufer de escritura al archivo de salida
     * @throws IOException
     *             Cuando ocurre un error al generar el archivo de salida
     */
    private void generarSentenciaTruncate(Sheet hoja, BufferedWriter out) throws IOException {
        String nombreTabla = hoja.getSheetName().trim();

        if (nombreTabla.startsWith(PREFIJO_NO_PROCESAR)) {
            return;
        }

        // -------------------------------------
        // 1. Generar sentencia TRUNCATE
        // -------------------------------------
        StringBuilder sql = new StringBuilder();

        sql.append(EnumFragmentoSQL.TRUNCATE_TABLE.getFragmento());
        sql.append(EnumSimbolos.ESPACIO.getSimbolo());
        sql.append(nombreTabla);
        sql.append(EnumSimbolos.PUNTO_Y_COMA.getSimbolo());
        sql.append(EnumSimbolos.NUEVA_LINEA.getSimbolo());
        // -------------------------------------
        // 2. Escribir sentencia en archivo
        // -------------------------------------
        out.write(sql.toString());
    }

    /**
     * Crea una sentencia SQL de insercion a partir de los datos referenciados en la hoja enviada como parametro. Hace un recorrido por todas las
     * filas de la hoja, tomando la primera como el encabezado con los nombres de las columnas.
     * 
     * @param hoja
     *            Objeto {@link Sheet} que referencia el contenido de una hoja de un archivo XLS o XLSX de entrada
     * @param out
     *            Un objeto {@link BufferedWriter} que contiene la referencia al bufer de escritura al archivo de salida
     * @throws IOException
     *             Cuando ocurre un error al generar el archivo de salida
     */
    private void procesarHoja(Sheet hoja, BufferedWriter out) throws IOException {
        String nombreTabla = hoja.getSheetName().trim();

        System.out.println("Procesando hoja " + nombreTabla);
        Iterator<Row> filaIterator = hoja.rowIterator();

        final StringBuilder columnas = new StringBuilder();
        final List<Integer> indicesProcesar = new ArrayList<>(10);

        // Obtener nombres de columnas
        if (filaIterator.hasNext()) {
            // Escribir encabezado de tabla
            StringBuilder encabezadoTabla = new StringBuilder();

            encabezadoTabla.append(EnumFragmentoSQL.COMMENT.getFragmento() + "\n");
            encabezadoTabla.append(EnumFragmentoSQL.COMMENT.getFragmento() + " " + nombreTabla + "\n");
            encabezadoTabla.append(EnumFragmentoSQL.COMMENT.getFragmento() + "\n");

            out.write(encabezadoTabla.toString());

            Row fila = filaIterator.next();

            Iterator<Cell> celdaIterator = fila.iterator();

            while (celdaIterator.hasNext()) {
                Cell celda = celdaIterator.next();
                final int indiceColumna = celda.getColumnIndex();
                final String nombreColumna = procesarCadena(celda.getStringCellValue());

                if (!StringUtils.isBlank(nombreColumna) && !nombreColumna.startsWith(PREFIJO_NO_PROCESAR)) {
                    columnas.append(nombreColumna.toUpperCase());
                    columnas.append(EnumSimbolos.COMA.getSimbolo());
                    columnas.append(EnumSimbolos.ESPACIO.getSimbolo());
                    indicesProcesar.add(Integer.valueOf(indiceColumna));
                }
            }
            columnas.delete(columnas.length() - 2, columnas.length());
        } else {
            System.out.println("Hoja vacia " + nombreTabla);
            return;
        }

        // 2.2. Generar sentencia INSERT
        fila: while (filaIterator.hasNext()) {
            Row fila = filaIterator.next();

            // 2.1. Obtener valores de columnas
            final StringBuilder valoresInsert = new StringBuilder();
            Cell celda = null;
            for (Integer indiceColumna : indicesProcesar) {
                celda = fila.getCell(indiceColumna, Row.CREATE_NULL_AS_BLANK);

                switch (celda.getCellType()) {
                case Cell.CELL_TYPE_ERROR:
                    throw new RuntimeException("Error en fila " + (celda.getRowIndex() + 1) + ", columna "
                            + (celda.getColumnIndex() + 1) + ", hoja " + celda.getSheet().getSheetName().toUpperCase());
                case Cell.CELL_TYPE_BOOLEAN:
                    valoresInsert.append(celda.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    valoresInsert.append(EnumSimbolos.COMILLA_SIMPLE.getSimbolo());
                    valoresInsert.append(BigDecimal.valueOf(celda.getNumericCellValue()).longValueExact());
                    valoresInsert.append(EnumSimbolos.COMILLA_SIMPLE.getSimbolo());
                    break;
                default:
                    // case Cell.CELL_TYPE_BLANK:
                    // case Cell.CELL_TYPE_FORMULA:
                    // case Cell.CELL_TYPE_STRING:
                    final String stringCellValue = procesarCadena(celda.getStringCellValue());
                    if (celda.getColumnIndex() == 0 && StringUtils.isBlank(stringCellValue)) {
                        continue fila;
                    }
                    if (!stringCellValue.equals(EnumFragmentoSQL.NULL.getFragmento())) {
                        valoresInsert.append(EnumSimbolos.COMILLA_SIMPLE.getSimbolo());
                    }
                    valoresInsert.append(stringCellValue);
                    if (!celda.getStringCellValue().equals(EnumFragmentoSQL.NULL.getFragmento())) {
                        valoresInsert.append(EnumSimbolos.COMILLA_SIMPLE.getSimbolo());
                    }
                    break;
                }

                valoresInsert.append(EnumSimbolos.COMA.getSimbolo());
                valoresInsert.append(EnumSimbolos.ESPACIO.getSimbolo());

            }

            valoresInsert.delete(valoresInsert.length() - 2, valoresInsert.length());

            StringBuilder sql = new StringBuilder();

            sql.append(EnumFragmentoSQL.INSERT.getFragmento());
            sql.append(EnumSimbolos.ESPACIO.getSimbolo());
            sql.append(nombreTabla);
            sql.append(EnumSimbolos.ESPACIO.getSimbolo());
            sql.append(EnumSimbolos.PARENTESIS_IZQ.getSimbolo());
            sql.append(columnas);
            sql.append(EnumSimbolos.PARENTESIS_DER.getSimbolo());
            sql.append(EnumSimbolos.ESPACIO.getSimbolo());
            sql.append(EnumFragmentoSQL.VALUES.getFragmento());
            sql.append(EnumSimbolos.ESPACIO.getSimbolo());
            sql.append(EnumSimbolos.PARENTESIS_IZQ.getSimbolo());
            sql.append(valoresInsert);
            sql.append(EnumSimbolos.PARENTESIS_DER.getSimbolo());
            sql.append(EnumSimbolos.PUNTO_Y_COMA.getSimbolo());
            sql.append(EnumSimbolos.NUEVA_LINEA.getSimbolo());
            // -------------------------------------
            // 2.2. Escribir sentencia en archivo
            // -------------------------------------
            out.write(sql.toString());
            out.flush();
        }
    }

    private String procesarCadena(String stringCellValue) {

        // La hoja proceso, tiene caracteres extraños q se ven como espacios pero se deben remover con el valor hexa del caracter
        stringCellValue = stringCellValue.replace(String.valueOf((char) 0xa0), "");
        stringCellValue = stringCellValue.replace(EnumSimbolos.COMILLA_SIMPLE.getSimbolo(),
                EnumSimbolos.COMILLA_SIMPLE.getSimbolo() + EnumSimbolos.COMILLA_SIMPLE.getSimbolo());
        stringCellValue = StringUtils.trim(stringCellValue);
        return stringCellValue;
    }
}
