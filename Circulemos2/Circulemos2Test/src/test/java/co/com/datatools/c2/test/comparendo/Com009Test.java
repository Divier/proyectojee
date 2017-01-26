package co.com.datatools.c2.test.comparendo;

import java.util.Date;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comparendo.RespuestaResolucionComparendoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.test.BaseTest;

@RunWith(Arquillian.class)
public class Com009Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Com009Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_009001.sql",
            "scripts/pruebas/comparendo/COM_009002.sql", };

    @EJB
    private IRComparendo comparendoEjb;
    @EJB
    private UsuarioSesionEJB usuarioSesionEJB;

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
    public void generarResolucionesSancion() {
        logger.debug("Com009Test::generarResolucionesSancion()");
        try {
            usuarioSesionEJB.almacenarUsuario("admin-c2", "localhost");
            // Genera las resoluciones
            RespuestaResolucionComparendoDTO respuesta = comparendoEjb.generarResolucionesSancion(new Date(), 11001);
            Assert.assertTrue(!respuesta.getComparendosResolucion().isEmpty());
            logger.info("Com009Test::Numero de comparendos con resolucion: "
                    + respuesta.getComparendosResolucion().size());

            // No hay resoluciones por generar
            respuesta = comparendoEjb.generarResolucionesSancion(new Date(), 11001);
            Assert.assertTrue(respuesta.getComparendosResolucion() == null);
        } catch (CirculemosNegocioException e) {
            logger.error("Com009Test::Error: " + e.getMessage(), e);
            Assert.assertTrue(false);
        }
    }
}
