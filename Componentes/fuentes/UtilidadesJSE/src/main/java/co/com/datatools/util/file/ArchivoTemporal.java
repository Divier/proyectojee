package co.com.datatools.util.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Administra archivos temporales de la aplicacion
 * 
 * @author rodrigo.cruz
 * 
 */
public class ArchivoTemporal {

    /**
     * Crea un archivo temporal en el sistema de archivos para operaciones de lectura de archivos en memoria
     * 
     * @param contenido
     *            Archivo en memoria con el contenido a escribir en el archivo temporal
     * @param rutaTemporal
     *            Ubicacion donde se almacena el archivo temporal la cual debe tener permisos de lectura y escritura. Se debe consultar con el
     *            administrador del sistema.
     * @param extension
     *            La extension del archivo temporal
     * @return El archivo temporal con el contenido escrito
     */
    public static File crearArchivoTemporal(byte[] contenido, String rutaTemporal, String extension) {
        File file = null;

        try {
            Path ruta = Paths.get(rutaTemporal);
            if (!Files.exists(ruta)) {
                Set<PosixFilePermission> permisos = PosixFilePermissions.fromString("rwxr-xr--");
                FileAttribute<Set<PosixFilePermission>> atributos = PosixFilePermissions.asFileAttribute(permisos);
                Files.createDirectory(ruta, atributos);
            }
            ruta = Files.createTempFile(Paths.get(rutaTemporal), "DTTMP_", "." + extension);
            file = ruta.toFile();
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            output.write(contenido);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al generar archivo temporal", e);
        }

        return file;
    }

}
