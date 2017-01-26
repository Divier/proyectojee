package co.com.datatools.c2.reporte;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.reporte.data.AbstractConectorPlantillaReporte;
import co.com.datatools.c2.reporte.data.PlantillaReporte;
import co.com.datatools.c2.reporte.handler.AbstractReportHandler;

/**
 * Clase encargada de generar reportes genericos en diferentes formatos
 * 
 * @author felipe.martinez
 */
public class GeneradorReporte {

    final AbstractConectorPlantillaReporte conectorPlantillas;

    /**
     * Inicializa el generador de reportes
     * 
     * @param conectorPlantillas
     *            indica la implementacion concreta del objeto que permite obtener las plantillas de los reportes
     */
    public <T extends AbstractConectorPlantillaReporte> GeneradorReporte(T conectorPlantillas) {
        // Instancia el conector parametrizado
        this.conectorPlantillas = conectorPlantillas;
        if (this.conectorPlantillas == null) {
            throw new IllegalArgumentException(
                    "Se requiere una instancia de" + AbstractConectorPlantillaReporte.class.getName());
        }
        this.conectorPlantillas.init();
    }

    /**
     * Genera el reporte y retorna el contenido del archivo
     * 
     * @param formato
     *            tipo de formato a generar
     * @param contenido
     *            contenido a registrar en el reporte
     * @return bytes representando el archivo generado
     */
    public byte[] asBytes(FormatoReporte formato, ContenidoReporte contenido, String codigoReporte) {
        final AbstractReportHandler handler = findHandler(formato, null, codigoReporte);
        handler.processHeaderFields(contenido);
        handler.processHeader();
        procesarContenidoAnidado(handler, contenido);
        return handler.getBytes();
    }

    /**
     * Genera el reporte y retorna el contenido del archivo en la ruta especificada
     * 
     * @param formato
     *            tipo de formato a generar
     * @param contenido
     *            contenido a registrar en el reporte
     * @param rutaArchivo
     *            ruta y nombre del archivo a generar
     * @return File generado en el formato especificado
     */
    public File asFile(FormatoReporte formato, ContenidoReporte contenido, String rutaArchivo, String codigoReporte) {
        final AbstractReportHandler handler = findHandler(formato, rutaArchivo, codigoReporte);
        handler.processHeaderFields(contenido);
        handler.processHeader();
        procesarContenidoAnidado(handler, contenido);
        return handler.getFile();
    }

    /**
     * Busca un ReportHandler especifico, teniendo en cuenta el formato enviado
     * 
     * @param formato
     *            Tipo de formato con que se generar el documento
     * @param rutaArchivo
     *            Ruta y nombre del archivo con el que va quedar el documento generado
     * @param codigoReporte
     *            Codigo con el que se idntifica el nombre del encabezado del documento a generar
     * @return ReportHandler específico
     */
    private AbstractReportHandler findHandler(FormatoReporte formato, String rutaArchivo, String codigoReporte) {
        try {
            PlantillaReporte plantillaReporte = conectorPlantillas.findPlantilla(codigoReporte);
            final AbstractReportHandler handler = formato.formatHandler
                    .getDeclaredConstructor(String.class, String.class, PlantillaReporte.class)
                    .newInstance(formato.toString(), rutaArchivo, plantillaReporte);
            return handler;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

    /**
     * Procesa el contenido del reporte a generar
     * 
     * @param handler
     *            ReportHandler con el que se va generar el reporte
     * @param contenido
     *            Objeto que contiene la información con la que se genera el reporte
     */
    @SuppressWarnings("rawtypes")
    private void procesarContenidoAnidado(AbstractReportHandler handler, ContenidoReporte contenido) {
        final List<?> registros = contenido.getContenido() == null ? Collections.emptyList() : contenido.getContenido();
        for (Object registro : registros) {
            if (registro instanceof Array) {
                handler.processLine(Arrays.asList(registro));
            } else if (registro instanceof Collection) {
                handler.processLine((Collection) registro);
            } else if (registro instanceof ContenidoReporte) {
                procesarContenidoAnidado(handler, (ContenidoReporte) registro);
            } else {
                if (registro != null)
                    handler.processLine(Arrays.asList(registro.toString()));
                else

                    handler.processLine(Arrays.asList(StringUtils.EMPTY));
            }
        }
    }

}
