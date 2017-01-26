/**
 * 
 */
package co.com.datatools.c2.negocio.opciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.StringTokenizer;

import org.jboss.logging.Logger;

import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.error.gestiondocs.ErrorGestionDocumentos.GestionDocumentos;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * @author sergio.torres
 * 
 */
public class ProcesadorArchivoFileSystem implements ProcesadorArchivos {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(ProcesadorArchivoFileSystem.class);

    private final String UBICACION_SERVER = System
            .getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM);

    private GestorArchivosOpciones opcion;

    /**
     * 
     */
    public ProcesadorArchivoFileSystem() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see co.com.datatools.c2.negocio.opciones.ProcesadorArchivos#procesarArchivo(co.com.datatools.c2.util.ArchivoTransportableDTO)
     */
    @Override
    public void procesarArchivo(ArchivoTransportableDTO archivo) throws CirculemosAlertaException {
        Path pathServer = Paths.get(UBICACION_SERVER);
        // Siempre se usa "/" ya que así se encuentra definida la numeración
        StringTokenizer tokenizer = new StringTokenizer(archivo.getRuta(), "/");
        Path tmp = Paths.get("");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            tmp = tmp.resolve(token);
            Path p = pathServer.resolve(tmp);
            if (!Files.exists(p)) {
                try {
                    Files.createDirectory(p);
                } catch (IOException e) {
                    LOG.errorv("ERROR al intentar escribir la carpeta {0}", p.toString());
                    throw new CirculemosAlertaException(GestionDocumentos.GESDOC_FILESYSTEM_002);
                }
            }
        }
        Path path = pathServer.resolve(tmp);
        Path pathArchivo = path.resolve(archivo.getNombre());
        try {
            Files.write(pathArchivo, archivo.getContenido(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            LOG.errorv("ERROR al intentar escribir el archivo {0}", pathArchivo.toString());
            throw new CirculemosAlertaException(GestionDocumentos.GESDOC_FILESYSTEM_002);
        }
    }

    @Override
    public GestorArchivosOpciones getOpcion() {
        return opcion;
    }

    @Override
    public void setOpcion(GestorArchivosOpciones opcion) {
        this.opcion = opcion;
    }

    @Override
    public byte[] obtenerArchivo(String directorio, String nombre) throws CirculemosAlertaException {
        byte[] archivo = null;
        Path pathServer = Paths.get(UBICACION_SERVER);
        Path path = pathServer.resolve(directorio);
        Path pathArchivo = path.resolve(nombre);
        try {
            archivo = Files.readAllBytes(pathArchivo);
        } catch (IOException e) {
            LOG.errorv("ERROR al intentar encontrar el archivo {0}", pathArchivo.toString());
            throw new CirculemosAlertaException(GestionDocumentos.GESDOC_FILESYSTEM_003);
        }
        return archivo;
    }

}
