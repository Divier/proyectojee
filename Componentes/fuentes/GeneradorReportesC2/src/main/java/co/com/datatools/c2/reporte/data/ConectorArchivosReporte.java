package co.com.datatools.c2.reporte.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.util.file.RutaArchivoUtil;

import com.thoughtworks.xstream.XStream;

/**
 * Conector que busca la estructura de las plantillas de reportes en archivos
 * 
 * @author felipe.martinez
 */
public class ConectorArchivosReporte extends AbstractConectorPlantillaReporte {

    private static final String PREFIJO_RECURSO = "resource:";

    private static final String PREFIJO_ARCHIVOS_ENCABEZADO = "ENC_";

    private boolean isRecurso = false;
    private String rutaPlantillas;
    private String extension = ".xml";

    /**
     * 
     * @param ruta
     */
    public ConectorArchivosReporte(String ruta) {
        this.rutaPlantillas = ruta;
        if (rutaPlantillas == null) {
            throw new IllegalArgumentException("La ruta de las plantillas no puede ser null");
        }
    }

    @Override
    public PlantillaReporte findPlantilla(String codigoReporte) {
        InputStream rutaCabeceraContenido = null;
        PlantillaReporte plantilla = new PlantillaReporte();
        try {
            if (isRecurso) {
                rutaCabeceraContenido = Thread.currentThread().getContextClassLoader()
                        .getResource(rutaPlantillas + "/" + codigoReporte + extension).openStream();

            } else {
                rutaCabeceraContenido = new FileInputStream(rutaPlantillas + "/" + codigoReporte + extension);
            }
            XStream cabeceraXstream = new XStream();
            cabeceraXstream.alias("reporte", CabeceraReporte.class);
            cabeceraXstream.alias("atributo", AtributoEncabezado.class);
            CabeceraReporte cabeceraContenido = (CabeceraReporte) cabeceraXstream.fromXML(rutaCabeceraContenido);
            plantilla.setCabecera(cabeceraContenido);
            plantilla.setEncabezado(findEncabezado(cabeceraContenido.getCodigo_encabezado()));
            if (rutaCabeceraContenido != null) {
                rutaCabeceraContenido.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return plantilla;
    }

    /**
     * Busca el encabezado del documento a generar
     * 
     * @param codigoEncabezado
     *            Codigo que identifica el nombre del encabezado a buscar
     * @return Encabezado del documento a generar
     */
    private Encabezado findEncabezado(String codigoEncabezado) {
        InputStream rutaEncabezado = null;
        Encabezado encabezado = null;
        try {
            if (isRecurso) {
                rutaEncabezado = Thread.currentThread().getContextClassLoader()
                        .getResource(rutaPlantillas + "/" + PREFIJO_ARCHIVOS_ENCABEZADO + codigoEncabezado + extension)
                        .openStream();
            } else {
                rutaEncabezado = new FileInputStream("/" + PREFIJO_ARCHIVOS_ENCABEZADO + codigoEncabezado + extension);
            }
            XStream encabezadoXstream = new XStream();
            encabezadoXstream.alias("encabezado", Encabezado.class);
            encabezadoXstream.alias("atributo", AtributoEncabezado.class);
            encabezado = (Encabezado) encabezadoXstream.fromXML(rutaEncabezado);
            if (rutaEncabezado != null) {
                rutaEncabezado.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        }
        return encabezado;
    }

    @Override
    public void init() {
        if (StringUtils.startsWith(rutaPlantillas, PREFIJO_RECURSO)) {
            rutaPlantillas = rutaPlantillas.replaceFirst(PREFIJO_RECURSO, "");
            isRecurso = true;
        } else {
            rutaPlantillas = RutaArchivoUtil.procesarRutaUsuario(rutaPlantillas);
        }
    }
}
