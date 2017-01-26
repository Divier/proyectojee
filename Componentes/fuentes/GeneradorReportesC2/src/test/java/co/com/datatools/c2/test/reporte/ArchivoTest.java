package co.com.datatools.c2.test.reporte;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Before;

import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.FormatoReporte;
import co.com.datatools.c2.reporte.GeneradorReporte;
import co.com.datatools.c2.reporte.data.ConectorArchivosReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;

/**
 * Clase utilitaria que permite leer el archivo que contiene la informacion de el contenido de los reportes de prueba
 * 
 * @author dixon.alvarez
 * 
 */
public class ArchivoTest {

    public static final String REPORTE_PRUEBA = "REPORTE_GENERAL";
    public static final ConectorArchivosReporte conecArchivo = new ConectorArchivosReporte("resource:plantilla_reporte");

    protected GeneradorReporte generador;
    protected ContenidoReporte contenido;
    private String rutaArchivoGenerado = System.getProperty("co.com.datatools.c2.reporte.RUTA_GEN_PRUEBAS", null);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Before
    public void init() {
        generador = new GeneradorReporte(conecArchivo);
        contenido = new ContenidoReporte();
        List<Object> registros = ArchivoTest.leerArchivo("src/test/resources/contenido.txt");
        contenido.getParametrosEncabezado().put(0, new Object[] { "Reporte de prueba" });
        contenido.getParametrosEncabezado().put(2, new Object[] { "Usuario Prueba", "user" });
        contenido.setContenido(registros);
        List listFiltros = new ArrayList<>();
        listFiltros.add(Arrays.asList("Comparendo", "ABC123"));
        listFiltros.add(Arrays.asList("Identificación", "123456789"));
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);
    }

    public static List<Object> leerArchivo(String ruta) {
        List<Object> contenidoLista = new ArrayList<Object>();
        try {
            FileReader fr = new FileReader(new File(ruta));
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            List<Object> lista;
            while ((linea = br.readLine()) != null) {
                lista = new ArrayList<Object>();
                token = new StringTokenizer(linea, "|");
                while (token.hasMoreTokens()) {
                    lista.add(token.nextElement());
                }
                contenidoLista.add(lista);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        }
        return contenidoLista;
    }

    public static void saveFile(byte[] data, String ruta) {
        final File file = Paths.get(ruta).toFile();
        try {
            final FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRutaFinal(FormatoReporte extension) {
        return rutaArchivoGenerado == null ? null : rutaArchivoGenerado + "." + extension.toString();
    }
}
