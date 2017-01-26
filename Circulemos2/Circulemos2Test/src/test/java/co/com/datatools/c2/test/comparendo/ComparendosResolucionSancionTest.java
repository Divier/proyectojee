package co.com.datatools.c2.test.comparendo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Ciclo de negocio que permite generar las resoluciones de sanción a los comparendos.<br>
 * <br>
 * Se requiere la ejecución de al menos una de las siguientes pruebas: <br>
 * <li>Prueba Comparendos Procesar Comparendo<br> <li>Prueba Comparendos Recibir Datos
 * 
 * @author luis.forero(2016-03-15)
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ Com009Test.class, Com045Test.class })
public class ComparendosResolucionSancionTest {

}
