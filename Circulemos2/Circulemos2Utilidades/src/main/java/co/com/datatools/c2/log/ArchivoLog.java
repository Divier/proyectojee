/**
 * 
 */
package co.com.datatools.c2.log;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumTipoLog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Implementa los servicios de ILog como un archivo de texto.
 * 
 * @author julio.pinzon
 * 
 */
public class ArchivoLog implements ILog {
    private final static Logger logger = Logger.getLogger(ArchivoLog.class.getName());

    private EnumLogSistema enumLogSistema;

    private static String EXTENSION_ARCHIVO = ".txt";
    private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static String DATE_FORMAT = "yyyyMMdd";
    private static String UBICACION_LOG = "D:\\logC2";

    /**
     * 
     */
    public ArchivoLog(EnumLogSistema enumLogSistema) {
        this.enumLogSistema = enumLogSistema;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.com.datatools.c2.log.ILog#escribir(java.lang.String, co.com.datatools.c2.log.ILogueable)
     */
    @Override
    public void escribir(String informacion, ILogueable datos) {
        logger.info("ArchivoLog::escribir(String, ILogueable)");

        // Objeto para realizar la conversion del objeto JSon guardado en la bd
        Gson gson = new GsonBuilder().setDateFormat(DATETIME_FORMAT).create();

        // Obtiene configuracion de log
        LogDTO log = obtenerLogDTO();

        // Inicializa el nombre del archivo segun el tipo de transaccion
        String nombreArchivo = obtenerNombreRotacion(enumLogSistema);

        // Fecha de generacion de log
        String fechaGeneracion = (new SimpleDateFormat(DATETIME_FORMAT)).format(new Date());

        String input = "[" + fechaGeneracion + "] " + informacion + " " + gson.toJson(datos) + "\n";

        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());

        // Inicializa el archivo a generar
        Path path = Paths.get(log.getUbicacion(), nombreArchivo);
        verifyFolder(log.getUbicacion());
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            channel.write(buffer);
        } catch (IOException e) {
            logger.error("Error al escribir log", e);
        }

    }

    /**
     * Obtiene el nombre segun la rotacion configurada del log
     * 
     * @param enumLogSistema
     * @return Nombre de archivo
     */
    private String obtenerNombreRotacion(EnumLogSistema enumLogSistema) {
        // TODO:Por el momento la rotacion siempre es por dia
        // Fecha de generacion de log
        String fechaGeneracion = (new SimpleDateFormat(DATE_FORMAT)).format(new Date());
        String nombreArchivo = enumLogSistema + "_" + fechaGeneracion + EXTENSION_ARCHIVO;
        return nombreArchivo;
    }

    /**
     * Obtiene configuracion del log
     * 
     * @return Dto de configuracion
     */
    private LogDTO obtenerLogDTO() {
        // TODO Obtener configuracion del log
        LogDTO logDTO = new LogDTO();
        logDTO.setTipoLogSistema(this.enumLogSistema);
        logDTO.setTipoLog(EnumTipoLog.ARCHIVO_PLANO);
        logDTO.setUbicacion(UBICACION_LOG);
        return logDTO;
    }

    /**
     * Verifica la existencia de un directorio y lo crea en caso que no exista
     * 
     * @param directory
     *            Ruta del directorio
     */
    public static void verifyFolder(String directory) {
        logger.debug("ArchivoLog::verifyFolder");
        // Verifica que las rutas existan, si no existen, las crea
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            createFolder(path);
        }
    }

    /**
     * Metodo para crear directorio y hacerlo editable
     * 
     * @param pathDirectory
     *            Ruta del directorio
     */
    private static void createFolder(Path pathDirectory) {
        logger.debug("ArchivoLog::createFolder");
        try {
            Path directory = Files.createDirectory(pathDirectory);
            directory.toFile().setExecutable(true, false);
            directory.toFile().setReadable(true, false);
            directory.toFile().setWritable(true, false);
        } catch (IOException e) {
            logger.error("Error al crear folder", e);
        }
    }
}
