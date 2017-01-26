package co.com.datatools.c2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase de utilidades creada para realizar tareas genéricas con archivos.
 * 
 * @author robert.bautista
 * @since 2013-10-31
 */
public class Archivo {

    /**
     * Obtiene la extension de un archivo. No importa si el nombre contiene la ruta o no.
     * 
     * @author camilo.sierra
     * @param nombre
     *            Nombre del archivo
     * @return extension del archivo.
     */
    public static String getExtension(String nombre) {
        String extension = "";
        String[] str = nombre.split("\\.");
        if (str.length > 1) {
            extension = str[str.length - 1];
        }

        return extension; // -- no extension
    }

    /**
     * Retorna el nombre del archivo especificado sin extension. Se exige que el nombre archivo sea sin ruta. Por lo tanto se espera un nombre como:
     * archivo.ext para que se retorne archivo
     * 
     * @param nombreArchivo
     *            el nombre del arhivo del cual se retornará el nombre sin extensión
     * 
     * @return nombre del archivo sin extensión
     */
    public static String retireExtension(String nombreArchivo) {
        String nombre = "";
        String[] str = nombreArchivo.split("\\.");
        if (str.length > 1) {
            nombre = str[0];
        }

        return nombre; // -- no extension
    }

    /**
     * Retorna el número de líneas del archivo con la ruta indicada
     * 
     * @param rutaArchivo
     *            contiene la ruta del archivo del cual se contarán las líneas
     * 
     * @return número de líneas del archivo con la ruta indicada
     * 
     * @throws FileNotFoundException
     *             si el archivo con la ruta indicada no existe
     * @throws IOException
     *             si hay problemas de entrada/salida con el archivo
     */
    public static int cuenteLineas(String rutaArchivo) throws FileNotFoundException, IOException {
        int lineasArchivo;
        File f = new File(rutaArchivo);
        BufferedReader entrada;

        entrada = new BufferedReader(new FileReader(f));
        lineasArchivo = 0;
        String linea;
        while ((linea = entrada.readLine()) != null) {
            if (!linea.trim().equals("")) {
                lineasArchivo++;
            }

        }

        entrada.close();

        return lineasArchivo;
    }

}
