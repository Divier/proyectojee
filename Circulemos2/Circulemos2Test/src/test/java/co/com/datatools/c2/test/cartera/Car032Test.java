package co.com.datatools.c2.test.cartera;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.common.ConsultaTasaInteresDTO;
import co.com.datatools.c2.dto.common.ConsultaValorInteresesDTO;
import co.com.datatools.c2.enumeracion.EnumClaseInteres;
import co.com.datatools.c2.enumeracion.EnumPeriodoInteres;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorCartera;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.test.BaseTest;

/**
 * CU_CIR20_DAT_CAR_032 Liquidar intereses
 * 
 * @author divier.casas
 */
@RunWith(Arquillian.class)
public class Car032Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Car032Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/cartera/CAR_032001.sql",
            "scripts/pruebas/cartera/CAR_032002.sql", };

    private static final BigDecimal valSujetoAplicacion = new BigDecimal(100);

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[1]);
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @EJB
    IRCarteraContable iCarteraContable;

    @Test
    public void consultarTasasInteresTest() {
        logger.debug("consultarTasasInteresTest");
        InteresDTO tasa = null;
        ConsultaTasaInteresDTO consultaTasaInteresDTO = new ConsultaTasaInteresDTO();
        consultaTasaInteresDTO.setClaseInteres(EnumClaseInteres.MORA_COMPARENDOS.getValue());
        consultaTasaInteresDTO.setPeriodoInteres(EnumPeriodoInteres.ANUAL.getValue());
        Calendar fechaAplicacion = Calendar.getInstance();
        fechaAplicacion.set(2015, 11, 1);
        consultaTasaInteresDTO.setFechaAplicacion(fechaAplicacion.getTime());
        tasa = iCarteraContable.consultarTasasInteres(consultaTasaInteresDTO);
        Assert.assertNotNull(tasa);
        Assert.assertTrue(tasa.getPorcentajeInteresDiario().compareTo(BigDecimal.ZERO) >= 1);

        consultaTasaInteresDTO.setPeriodoInteres(EnumPeriodoInteres.DIARIO.getValue());
        tasa = iCarteraContable.consultarTasasInteres(consultaTasaInteresDTO);
        Assert.assertNotNull(tasa);
        Assert.assertTrue(tasa.getPorcentajeInteresDiario().compareTo(BigDecimal.ZERO) >= 1);

        consultaTasaInteresDTO.setPeriodoInteres(EnumPeriodoInteres.MENSUAL.getValue());
        tasa = iCarteraContable.consultarTasasInteres(consultaTasaInteresDTO);
        Assert.assertNull(tasa);

        consultaTasaInteresDTO.setPeriodoInteres(EnumPeriodoInteres.TRIMESTRAL.getValue());
        tasa = iCarteraContable.consultarTasasInteres(consultaTasaInteresDTO);
        Assert.assertNull(tasa);
    }

    @Test
    public void consultarValorInteresesTest() {
        logger.debug("consultarValorInteresesTest");
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(2015, 0, 1);
        Calendar fechaFinal = Calendar.getInstance();
        fechaFinal.set(2015, 1, 9);
        BigDecimal valorIntereses = null;

        ConsultaValorInteresesDTO consultaValorInteresesDTO = new ConsultaValorInteresesDTO();
        consultaValorInteresesDTO.setClaseInteres(EnumClaseInteres.MORA_COMPARENDOS.getValue());
        consultaValorInteresesDTO.setPeriodoInteres(EnumPeriodoInteres.ANUAL.getValue());
        consultaValorInteresesDTO.setFechaInicial(fechaInicial.getTime());
        consultaValorInteresesDTO.setFechaFinal(fechaFinal.getTime());
        consultaValorInteresesDTO.setValSujetoAplicacion(valSujetoAplicacion);

        try {

            valorIntereses = iCarteraContable.consultarValorIntereses(null);
            Assert.assertEquals(valorIntereses, BigDecimal.ZERO);

            valorIntereses = iCarteraContable.consultarValorIntereses(consultaValorInteresesDTO);
            Assert.assertTrue(valorIntereses.compareTo(BigDecimal.ZERO) >= 1);

            logger.info("#######-Inicia Caso de Excepcion de Negocio-#######");
            fechaInicial.set(2030, 11, 1);
            fechaFinal.set(2030, 11, 5);
            consultaValorInteresesDTO.setFechaInicial(fechaInicial.getTime());
            consultaValorInteresesDTO.setFechaFinal(fechaFinal.getTime());
            valorIntereses = iCarteraContable.consultarValorIntereses(consultaValorInteresesDTO);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(), ErrorCartera.LiquidarIntereses.CAR_032002,
                    e.getErrorInfo());
        }

        try {
            logger.info("#######-Inicia Caso de Excepcion de Negocio-#######");
            fechaInicial.set(2015, 11, 10);
            fechaFinal.set(2015, 11, 5);
            consultaValorInteresesDTO.setFechaInicial(fechaInicial.getTime());
            consultaValorInteresesDTO.setFechaFinal(fechaFinal.getTime());
            valorIntereses = iCarteraContable.consultarValorIntereses(consultaValorInteresesDTO);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(), ErrorCartera.LiquidarIntereses.CAR_032003,
                    e.getErrorInfo());
        }

        try {
            logger.info("#######-Inicia Caso de Excepcion de Negocio-#######");
            fechaInicial.set(2014, 11, 1);
            fechaFinal.set(2014, 11, 5);
            consultaValorInteresesDTO.setFechaInicial(fechaInicial.getTime());
            consultaValorInteresesDTO.setFechaFinal(fechaFinal.getTime());
            valorIntereses = iCarteraContable.consultarValorIntereses(consultaValorInteresesDTO);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(), ErrorCartera.LiquidarIntereses.CAR_032001,
                    e.getErrorInfo());
        }
        return;
    }
}