package co.com.datatools.c2.test.ciclonegocio;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import co.com.datatools.c2.test.comparendo.ComparendosProcesarComparendoTest;
import co.com.datatools.c2.test.comparendo.ComparendosResolucionSancionTest;

/**
 * @author luis.forero
 */
@RunWith(Suite.class)
@SuiteClasses({ ComparendosProcesarComparendoTest.class, ComparendosResolucionSancionTest.class })
public class CicloCompletoComparendos {

}
