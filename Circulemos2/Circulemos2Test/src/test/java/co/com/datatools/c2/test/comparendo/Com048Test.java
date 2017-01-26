package co.com.datatools.c2.test.comparendo;

import java.util.Calendar;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.administracion.IRTarifaInfraccion;
import co.com.datatools.c2.test.BaseTest;

/**
 * CU_CIR20_DAT_COM_048 LIQUIDAR CARTERA COMPARENDO
 * 
 * @author giovanni.velandia
 */
@RunWith(Arquillian.class)
public class Com048Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Com048Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_048001.sql",
            "scripts/pruebas/comparendo/COM_048002.sql", };

    @EJB
    private IRTarifaInfraccion iRTarifaInfraccion;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @Test
    public void liquidarTarifaInfraccion() {
        logger.debug("liquidarTarifaInfraccion()");
        LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO = new LiquidarTarifaInfraccionDTO();
        // Codigo Infraccion
        liquidarTarifaInfraccionDTO.setCodigoInfraccion("A1");
        // Fecha liquidacion
        liquidarTarifaInfraccionDTO.setFechaLiquidacion(Calendar.getInstance().getTime());
        // Niega prueba alcoholemia
        liquidarTarifaInfraccionDTO.setNiegaPruebaAlcoholemia(false);

        try {
            TarifaLiquidacionDTO tarifaLiquidacionDTO = iRTarifaInfraccion
                    .liquidarTarifaInfraccion(liquidarTarifaInfraccionDTO);
            if (tarifaLiquidacionDTO == null) {
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void liquidarTarifaInfraccionEmbriaguez() {
        logger.debug("liquidarTarifaInfraccion()");
        LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO = new LiquidarTarifaInfraccionDTO();
        // Codigo Infraccion
        liquidarTarifaInfraccionDTO.setCodigoInfraccion("A1");
        // Fecha liquidacion
        liquidarTarifaInfraccionDTO.setFechaLiquidacion(Calendar.getInstance().getTime());
        // Niega prueba alcoholemia
        liquidarTarifaInfraccionDTO.setNiegaPruebaAlcoholemia(true);
        // GradoAlcoholemia
        liquidarTarifaInfraccionDTO.setGradoAlcoholemia(2);
        // Clase de servicio
        liquidarTarifaInfraccionDTO.setIdClaseServicio(3);
        // Numero reincidencias
        liquidarTarifaInfraccionDTO.setNumeroReincidencias(1);

        try {
            TarifaLiquidacionDTO tarifaLiquidacionDTO = iRTarifaInfraccion
                    .liquidarTarifaInfraccion(liquidarTarifaInfraccionDTO);
            if (tarifaLiquidacionDTO == null) {
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }
}
