package co.com.datatools.c2.test.simit;

import static org.junit.Assert.fail;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.ArchivoNotificacionComparendoDTO;
import co.com.datatools.c2.enumeraciones.EnumOpcionGeneracionArchivo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILNotificacionComparendoSimit;
import co.com.datatools.c2.test.BaseTest;

@RunWith(Arquillian.class)
public class Sim008Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Sim008Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/simit/SIM_008001.sql",
            "scripts/pruebas/simit/SIM_008002.sql", };

    @EJB
    ILNotificacionComparendoSimit iComparendoSimit;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    /**
     * Caso de no generar archivo
     */
    @Ignore
    @Test
    public void registrarNotificacionComparendo001() {
        logger.debug("Sim008Test::registrarNotificacionComparendo001");

        try {
            List<ArchivoNotificacionComparendoDTO> listaArchivos = iComparendoSimit.generarNotificacionComparendo(
                    11001, EnumOpcionGeneracionArchivo.NO_GENERAR_ARCHIVO);
            for (ArchivoNotificacionComparendoDTO archivoNotificacionComparendoDTO : listaArchivos) {
                if (archivoNotificacionComparendoDTO.getCantidadRegistros() <= 0
                        || archivoNotificacionComparendoDTO.getContenido() != null
                        || archivoNotificacionComparendoDTO.getNombre() != null
                        || archivoNotificacionComparendoDTO.getNumeroOficio() != null) {
                    Assert.assertTrue(false);
                }
            }
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }

    /**
     * Caso de generar archivo
     */
    @Test
    public void registrarNotificacionComparendo002() {
        logger.debug("Sim008Test::registrarNotificacionComparendo002");

        try {
            iComparendoSimit.registrarArchivoNotificacionComparendo(11001);
        } catch (CirculemosNegocioException e) {
            fail("ERROR - Sim008Test::registrarNotificacionComparendo002");
        }
    }

    /**
     * Caso de no encuentra comparendos
     */
    @Ignore
    @Test
    public void registrarNotificacionComparendo003() {
        logger.debug("Sim008Test::registrarNotificacionComparendo003");

        try {
            iComparendoSimit.generarNotificacionComparendo(11001, EnumOpcionGeneracionArchivo.GENERAR_ARCHIVO);
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(true);
        }
    }

}
