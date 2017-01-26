package co.com.datatools.c2.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;

/**
 * Clase que se conecta a un servidor ftp, sube y descarga archivos.
 * 
 * @author camilo.sierra
 * @version 1.0
 * @since 26-Agt-2013
 */

public class Ftp {

    private static final Logger logger = Logger.getLogger(Ftp.class.getName());

    /**
     * 
     * Metodo para subir un archivo local a un servidor FTP
     * 
     * tenemos que pasarle los datos de:
     * 
     * ip o hostname del servidor
     * 
     * usuario
     * 
     * password
     * 
     * la ruta completa del fichero local a subir
     * 
     * la ruta en donde queramos colocar el archivo en el servidor FTP
     * 
     * ej: uploadFileByFTP("servidor", "usuario", "password", "c:\\prueba.txt", "/mi_dir/prueba.txt");
     */

    public static boolean uploadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {

        try {

            URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");

            URLConnection urlc = url.openConnection();

            try (OutputStream os = urlc.getOutputStream()) {
                try (BufferedReader br = new BufferedReader(new FileReader(localPath))) {
                    int c;

                    while ((c = br.read()) != -1) {
                        os.write(c);
                    }

                    return true;
                }
            }

        } catch (IOException ex) {
            logger.error(ex);
            return false;

        }

    }

    /**
     * Método encargado de descargar los archivos encontrados en el servidor ftp indicado dejándolos en el directorio con la ruta local indicada.
     * 
     * @param rutaServidor
     *            ruta del servidor FTP a donde conectarse
     * 
     * @param usuario
     *            usuario para realizar la conexión
     * 
     * @param clave
     *            clave del usuario indicado para realizar la conexión
     * 
     * @param rutaLocal
     *            ruta local del directorio donde se descargarán los archivos
     * 
     * @return true si el proceso se realiza satisfactoriamente
     * 
     * @throws MalformedURLException
     *             si la url de la conexión ftp no es válida
     * 
     * @throws IOException
     *             si hay problemas leyendo/escribiendo la información del servidor
     */
    public static boolean downloadFileByFTP(String rutaServidor, String usuario, String clave, String rutaLocal)
            throws MalformedURLException, IOException {

        URL url = new URL("ftp://" + usuario + ":" + clave + "@" + rutaServidor + ";type=i");
        URLConnection urlc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        List<String> archivos = new ArrayList<String>();
        String inputLine;
        // TODO no creo que esta sea la mejor manera de obtener el listado de nombres de los archivos
        while ((inputLine = in.readLine()) != null) {
            archivos.add(inputLine.substring(56, (inputLine.length())));
        }

        in.close();
        Iterator<String> iter = archivos.iterator();
        while (iter.hasNext()) {
            String nombreArchivo = (String) iter.next();
            File dirFtp = new File(rutaServidor, nombreArchivo);
            URL url2 = new URL("ftp://" + usuario + ":" + clave + "@" + dirFtp.getPath().replace("\\", "/") + ";type=i");
            URLConnection urlc2 = url2.openConnection();
            InputStream is = urlc2.getInputStream();
            int aleatorio = (int) (Math.random() * 999999 + 1);
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(rutaLocal, aleatorio + ".txt")));
            int c;
            while ((c = is.read()) != -1) {
                bw.write(c);
            }

            is.close();
            bw.flush();
            bw.close();
        }

        return true;
    }

}
