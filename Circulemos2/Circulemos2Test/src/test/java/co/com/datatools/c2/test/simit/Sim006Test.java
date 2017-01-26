package co.com.datatools.c2.test.simit;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.ArchivoNotificacionComparendoDTO;
import co.com.datatools.c2.enumeraciones.EnumOpcionGeneracionArchivo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILNotificacionComparendoSimit;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.cartera.Car003Test;

@RunWith(Arquillian.class)
public class Sim006Test extends BaseTest {

    // private static String UBICACION_LOG = "D:\\logC2";
    private static final Logger logger = Logger.getLogger(Car003Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/simit/SIM_006001.sql",
            "scripts/pruebas/simit/SIM_006002.sql", };

    @EJB
    ILNotificacionComparendoSimit iRComparendoSimit;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    /**
     * Prueba ...
     */
    @Test
    public void generarNotificacionComparendo() {
        logger.debug("Sim006Test::generarNotificacionComparendo()");
        try {
            // Caso de generar archivo
            List<ArchivoNotificacionComparendoDTO> listaArchivos = iRComparendoSimit.generarNotificacionComparendo(
                    11001, EnumOpcionGeneracionArchivo.NO_GENERAR_ARCHIVO);
            for (ArchivoNotificacionComparendoDTO archivoNotificacionComparendoDTO : listaArchivos) {
                if (archivoNotificacionComparendoDTO.getCantidadRegistros() <= 0
                        || archivoNotificacionComparendoDTO.getContenido() != null
                        || archivoNotificacionComparendoDTO.getNombre() != null
                        || archivoNotificacionComparendoDTO.getNumeroOficio() != null) {
                    Assert.assertTrue(false);
                }
            }
            // Caso de no generar archivo
            listaArchivos = iRComparendoSimit.generarNotificacionComparendo(11001,
                    EnumOpcionGeneracionArchivo.GENERAR_ARCHIVO);
            // Para probar localmente la creacion de usuarios
            // for (ArchivoNotificacionComparendoDTO archivoNotificacionComparendoDTO : listaArchivos) {
            // if (archivoNotificacionComparendoDTO.getCantidadRegistros() <= 0
            // || archivoNotificacionComparendoDTO.getContenido() == null
            // || StringUtils.isBlank(archivoNotificacionComparendoDTO.getNombre())
            // || archivoNotificacionComparendoDTO.getNumeroOficio() == null) {
            // Assert.assertTrue(false);
            // } else {
            // ByteBuffer buffer = ByteBuffer.wrap(archivoNotificacionComparendoDTO.getContenido());
            //
            // // Inicializa el archivo a generar
            // Path path = Paths.get(UBICACION_LOG, archivoNotificacionComparendoDTO.getNombre());
            // try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,
            // StandardOpenOption.CREATE)) {
            // channel.write(buffer);
            // } catch (IOException e) {
            // logger.error("Error al guardar archivo", e);
            // }
            // }
            // }
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }

        try {
            // Caso de no encuentra comparendos
            iRComparendoSimit.generarNotificacionComparendo(11001, EnumOpcionGeneracionArchivo.GENERAR_ARCHIVO);
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(true);
        }
    }
}
