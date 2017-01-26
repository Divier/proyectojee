package co.com.datatools.c2.test.administracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.parametrizacion.ErrorParametrizacion;
import co.com.datatools.c2.negocio.helpers.parametrizacion.ProcesadorXmlConfiguracion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.util.ConfiguracionPruebaDTO;
import co.com.datatools.util.date.UtilFecha;

@RunWith(Arquillian.class)
public class Adm066Test extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(Adm066Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/admin/ADM_066000.sql",
            "scripts/pruebas/admin/ADM_066001.sql" };

    public static String CODIGO_CONFIGURACION_PRUEBA = "Prueba";
    public static String CODIGO_CONFIGURACION_RANGO = "RANGO";

    public static String CODIGO_CAMPO_RANGO_1 = "inicialEntrada";
    public static String CODIGO_CAMPO_RANGO_2 = "finalEntrada";
    public static String CODIGO_CAMPO_RANGO_3 = "numeroSalida";
    public static String CODIGO_CAMPO_RANGO_4 = "cadenaSalida";

    @EJB
    private IRConfiguracion configuracionEjb;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts);
    }

    @After
    public void after() {

    }

    @Test
    public void consultarPlantillaConfiguracionTest() {
        TipoConfiguracionDTO plantilla = configuracionEjb.consultarPlantillaConfiguracion(CODIGO_CONFIGURACION_PRUEBA);
        assertNotNull(plantilla);
    }

    @Test
    public void consultarPlantillaConfiguracionTest1() {
        TipoConfiguracionDTO plantilla = configuracionEjb.consultarPlantillaConfiguracion(CODIGO_CONFIGURACION_RANGO);
        assertNotNull(plantilla);
        assertTrue(plantilla.getMapaCampos().size() == 4);
        assertTrue(plantilla.getEntradas().size() == 2);
        assertTrue(plantilla.getSalidas().size() == 2);

        assertTrue(plantilla.getEntradas().get(0).isObligatorio());
        assertTrue(!plantilla.getEntradas().get(1).isObligatorio());
        assertTrue(!plantilla.getSalidas().get(0).isObligatorio());
        assertTrue(!plantilla.getSalidas().get(1).isObligatorio());

        assertTrue(plantilla.getRangos().size() == 1);
        assertEquals(plantilla.getRangos().get(0).getLeft().getAttrRngCodigoRango(), plantilla.getRangos().get(0)
                .getRight().getAttrRngCodigoRango());
    }

    @Test
    public void consultarValorConfiguracion() {
        List<RegistroConfiguracionDTO> registros = configuracionEjb
                .consultarValorConfiguracion(CODIGO_CONFIGURACION_RANGO);

        assertTrue(registros.size() == 3);
        for (RegistroConfiguracionDTO registro : registros) {
            assertTrue(registro.getValorCampo(CODIGO_CAMPO_RANGO_1) instanceof Date);
            assertTrue(registro.getValorCampo(CODIGO_CAMPO_RANGO_3) instanceof BigDecimal);
        }
    }

    private RegistroConfiguracionDTO leerRegistro() {
        TipoConfiguracionDTO plantilla = configuracionEjb.consultarPlantillaConfiguracion(CODIGO_CONFIGURACION_RANGO);
        Scanner temp;
        String valor = (temp = new Scanner(this.getClass().getResourceAsStream("/archivos/Adm066/valor-registrar1.xml")))
                .useDelimiter("\\Z").next();
        temp.close();

        return ProcesadorXmlConfiguracion.construirRegistro(plantilla, null, valor);
    }

    @Test
    public void registrarValorConfiguracionTest1() {
        RegistroConfiguracionDTO registro = leerRegistro();
        Date fecha = UtilFecha.stringToDate("yyyy-MM-dd", "2015-01-01");
        registro.setValorCampo(CODIGO_CAMPO_RANGO_1, fecha);
        registro.setValorCampo(CODIGO_CAMPO_RANGO_2, fecha);
        try {
            configuracionEjb.registrarValorConfiguracion(CODIGO_CONFIGURACION_RANGO, registro);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(),
                    ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066001, e.getErrorInfo());
            return;
        }
        fail("No se genero la excepcion de negocio esperada "
                + ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066001.getCodigoError());
    }

    @Test
    public void registrarValorConfiguracionTest2() {
        RegistroConfiguracionDTO registro = leerRegistro();
        registro.setValorCampo(CODIGO_CAMPO_RANGO_1, null);
        try {
            configuracionEjb.registrarValorConfiguracion(CODIGO_CONFIGURACION_RANGO, registro);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(),
                    ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066002, e.getErrorInfo());
            return;
        }
        fail("No se genero la excepcion de negocio esperada "
                + ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066002.getCodigoError());
    }

    @Test
    public void registrarValorConfiguracionTest3() {
        RegistroConfiguracionDTO registro = leerRegistro();
        Date fechaIni = UtilFecha.stringToDate("yyyy-MM-dd", "2012-01-01");
        Date fechaFin = UtilFecha.stringToDate("yyyy-MM-dd", "2012-12-31");
        registro.setValorCampo(CODIGO_CAMPO_RANGO_1, fechaIni);
        registro.setValorCampo(CODIGO_CAMPO_RANGO_2, fechaFin);
        registro.setValorCampo(CODIGO_CAMPO_RANGO_4, "1234567890123");
        try {
            configuracionEjb.registrarValorConfiguracion(CODIGO_CONFIGURACION_RANGO, registro);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(),
                    ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066003, e.getErrorInfo());
            return;
        }
        fail("No se genero la excepcion de negocio esperada "
                + ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066003.getCodigoError());
    }

    @Test
    public void registrarValorConfiguracionTest4() {
        RegistroConfiguracionDTO registro = leerRegistro();
        Date fechaIni = UtilFecha.stringToDate("yyyy-MM-dd", "2012-01-01");
        Date fechaFin = UtilFecha.stringToDate("yyyy-MM-dd", "2012-12-31");
        registro.setValorCampo(CODIGO_CAMPO_RANGO_1, fechaIni);
        registro.setValorCampo(CODIGO_CAMPO_RANGO_2, fechaFin);
        registro.setValorCampo(CODIGO_CAMPO_RANGO_3, BigDecimal.ZERO);
        try {
            configuracionEjb.registrarValorConfiguracion(CODIGO_CONFIGURACION_RANGO, registro);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(),
                    ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066004, e.getErrorInfo());
            return;
        }
        fail("No se genero la excepcion de negocio esperada "
                + ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066004.getCodigoError());
    }

    @Test
    public void registrarValorConfiguracionTest5() {
        RegistroConfiguracionDTO registro = leerRegistro();
        Date fechaIni = UtilFecha.stringToDate("yyyy-MM-dd", "2012-01-01");
        Date fechaFin = UtilFecha.stringToDate("yyyy-MM-dd", "2012-12-31");
        registro.setValorCampo(CODIGO_CAMPO_RANGO_2, fechaIni);
        registro.setValorCampo(CODIGO_CAMPO_RANGO_1, fechaFin);

        try {
            configuracionEjb.registrarValorConfiguracion(CODIGO_CONFIGURACION_RANGO, registro);
        } catch (CirculemosNegocioException e) {
            assertEquals("Tipo inesperado de excepcion " + e.getMessage(),
                    ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066005, e.getErrorInfo());
            return;
        }
        fail("No se genero la excepcion de negocio esperada "
                + ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066005.getCodigoError());
    }

    @Test
    public void consultarDatoConfiguracionTest() {
        ConfiguracionPruebaDTO dto = new ConfiguracionPruebaDTO();
        Date fechaIni = UtilFecha.stringToDate("yyyy-MM-dd", "2013-01-02");
        dto.setInicialEntrada(fechaIni);
        try {
            dto = configuracionEjb.consultarDatoConfiguracion(CODIGO_CONFIGURACION_RANGO, dto);
        } catch (CirculemosNegocioException e) {
            LOGGER.error("", e);
            fail(e.getMessage());
        }
        assertEquals(dto.getCadenaSalida(), "2013");
        assertEquals(dto.getNumeroSalida(), new BigDecimal("2013"));

    }

}
