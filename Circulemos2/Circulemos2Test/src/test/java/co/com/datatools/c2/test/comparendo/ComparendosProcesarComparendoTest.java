package co.com.datatools.c2.test.comparendo;

import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Ciclo de negocio donde se almacenan comparendos, inconsistencias y se rectifican comparendos.
 * 
 * @author luis.forero(2016-03-14)
 */
@RunWith(Suite.class)
@SuiteClasses({ IngresarInconsistenciasTest.class, IngresarComparendosTest.class, RectificarComaprendosTest.class,
        CorregirInconsistenciasTest.class })
public class ComparendosProcesarComparendoTest {

    private static final Logger logger = Logger.getLogger(ComparendosProcesarComparendoTest.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_002001.sql",
            "scripts/pruebas/comparendo/COM_002002.sql", };

    @Before
    public void before() {
        // ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        // ejecutarScriptsSQL(s.cripts[1]);
    }
}
