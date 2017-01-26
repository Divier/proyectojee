package co.com.datatools.c2.test.cartera;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.util.GenericDao;

/**
 * CU_CIR20_DAT_CAR_031 CAMBIAR ESTADO DE CARTERA UT
 * 
 * @author rodrigo.cruz
 */
@RunWith(Arquillian.class)
public class Car031Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Car031Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/cartera/CAR_031001.sql",
            "scripts/pruebas/cartera/CAR_031002.sql", };

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @EJB
    private IRCarteraContable carteraContableEJB;

    @Test
    public void actualizarEstadoCarteraTest001() throws ParseException {
        logger.debug("actualizarEstadoCarteraTest001");

        RegistroCarteraDTO registroCarteraDTO = new RegistroCarteraDTO();
        registroCarteraDTO.setFechaObligacion(format.parse("2015-01-01"));
        registroCarteraDTO.setIdOrigen(11001);
        registroCarteraDTO.setIdPersona(-2L);
        registroCarteraDTO.setIdTipoObligacion(EnumTipoObligacion.COMPARENDO.getValue());
        registroCarteraDTO.setReferenciaObligacion("HOLA AMIGOS");
        registroCarteraDTO.setValor(new BigDecimal(4567890));

        Long idCartera = null;

        try {
            idCartera = carteraContableEJB.registrarCartera(registroCarteraDTO);
            Assert.assertNotNull(idCartera);
        } catch (CirculemosNegocioException e) {
            fail("ERROR - Car003Test::actualizarEstadoCarteraTest001");
        }

        GenericDao<Cartera> carDAO = new GenericDao<>(Cartera.class, em);
        Cartera cartera = carDAO.findUniqueByAttribute("id", idCartera);
        Assert.assertNotNull(cartera);

        CambioEstadoCarteraDTO cambioEstadoCarteraDTO = new CambioEstadoCarteraDTO();
        cambioEstadoCarteraDTO.setActividadCartera(EnumActividadCartera.ACTIVAR_OBLIGACION_CARTERA);
        cambioEstadoCarteraDTO.setEstadoObligacion(EnumEstadoObligacion.ACTIVO);
        cambioEstadoCarteraDTO.setFechaCambio(format.parse("2015-06-01"));
        cambioEstadoCarteraDTO.setIdCartera(cartera.getId());

        try {
            carteraContableEJB.actualizarEstadoCartera(cambioEstadoCarteraDTO);
        } catch (CirculemosNegocioException e) {
            fail("ERROR - Car003Test::actualizarEstadoCarteraTest001");
        }

        GenericDao<TrazabilidadProceso> traCarDAO = new GenericDao<>(TrazabilidadProceso.class, em);
        List<TrazabilidadProceso> traCarList = traCarDAO.findByAttribute("cartera.id", idCartera);

        for (TrazabilidadProceso trazabilidadCartera : traCarList) {
            if (trazabilidadCartera.getActividadCartera().getId()
                    .equals(EnumActividadCartera.ACTIVAR_OBLIGACION_CARTERA.getValue())
                    && trazabilidadCartera.getEstadoObligacion().getId()
                            .equals(EnumEstadoObligacion.ACTIVO.getValue())) {
                Assert.assertTrue(trazabilidadCartera.getCartera().getId().equals(cartera.getId()));
                cartera = trazabilidadCartera.getCartera();
                break;
            }
        }

        // GenericDao<Cartera> carActDAO = new GenericDao<>(Cartera.class, em);
        // Cartera carteraActiva = carActDAO.findUniqueByAttribute("id", idCartera);
        // Assert.assertNotNull(carteraActiva);
        //
        // Assert.assertTrue(carteraActiva.getFechaInicio().compareTo(cambioEstadoCarteraDTO.getFechaCambio()) == 0);
        // Assert.assertTrue(carteraActiva.getEstadoObligacion().getId().equals(EnumEstadoObligacion.ACTIVO.getCodigo()));
    }

}
