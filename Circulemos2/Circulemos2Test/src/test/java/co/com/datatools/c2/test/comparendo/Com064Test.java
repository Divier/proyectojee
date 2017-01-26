package co.com.datatools.c2.test.comparendo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comparendo.SaldoComparendoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorComparendo;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.test.BaseTest;

@RunWith(Arquillian.class)
public class Com064Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Com064Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_064001.sql",
            "scripts/pruebas/comparendo/COM_064002.sql", };

    @EJB
    private IRComparendo iRComparendo;
    private static final int CODIGO_ORGANISMO = 11001;

    /*
     * @Before public void before() { ejecutarScriptsSQL(scripts[0]); }
     */

    /*
     * @After public void after() { ejecutarScriptsSQL(scripts[1]); }
     */

    @Test
    public void testCalcularSaldoComparendo() {
        logger.debug("testCalcularSaldoComparendo");
        try {
            SaldoComparendoDTO saldoComparendoDTO = iRComparendo.calcularSaldoComparendo(CODIGO_ORGANISMO, 430L,
                    new Date());
            Assert.assertNotNull(saldoComparendoDTO);
            Assert.assertTrue(saldoComparendoDTO.getSaldoCapital().compareTo(BigDecimal.ZERO) > 0);
            Assert.assertTrue(saldoComparendoDTO.getSaldoInteres().compareTo(BigDecimal.ZERO) > 0);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(),
                    ErrorComparendo.CalcularSaldoComparendo.COM_064002, e.getErrorInfo());
        }
    }
}