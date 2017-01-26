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

import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.MovimientosCartera;
import co.com.datatools.c2.entidades.SaldoCartera;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.util.GenericDao;

/**
 * CU_CIR20_DAT_CAR_003 REGISTRAR CARTERA UT
 * 
 * @author rodrigo.cruz
 */
@RunWith(Arquillian.class)
public class Car003Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Car003Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/cartera/CAR_003001.sql",
            "scripts/pruebas/cartera/CAR_003002.sql", };

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
    public void registrarCarteraTest001() throws ParseException {
        logger.debug("registrarCarteraTest001");

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
            fail("ERROR - Car003Test::registrarCarteraTest001");
        }

        // Cartera
        GenericDao<Cartera> carDAO = new GenericDao<>(Cartera.class, em);
        Cartera cartera = carDAO.findUniqueByAttribute("id", idCartera);
        Assert.assertNotNull(cartera);

        GenericDao<MovimientosCartera> movCarDAO = new GenericDao<>(MovimientosCartera.class, em);
        List<MovimientosCartera> movCarList = movCarDAO.findByAttribute("cartera.id", cartera.getId());
        GenericDao<TrazabilidadProceso> traCarDAO = new GenericDao<>(TrazabilidadProceso.class, em);
        List<TrazabilidadProceso> traCarList = traCarDAO.findByAttribute("cartera.id", cartera.getId());
        GenericDao<SaldoCartera> salCarDAO = new GenericDao<>(SaldoCartera.class, em);
        List<SaldoCartera> salCarList = salCarDAO.findByAttribute("cartera.id", cartera.getId());

        Assert.assertFalse(movCarList.isEmpty());
        Assert.assertFalse(traCarList.isEmpty());
        Assert.assertFalse(salCarList.isEmpty());

        Assert.assertTrue(cartera.getEstadoObligacion().getId()
                .equals(EnumEstadoObligacion.PENDIENTE_POR_ACTIVAR.getValue()));
        Assert.assertTrue(cartera.getFechaCreacion().compareTo(registroCarteraDTO.getFechaObligacion()) == 0);
        Assert.assertTrue(cartera.getTipoObligacion().getId().equals(EnumTipoObligacion.COMPARENDO.getValue()));

        // Movimiento cartera
        MovimientosCartera movimientoCartera = movCarDAO.findUniqueByAttribute("id", movCarList.get(0).getId());
        Assert.assertNotNull(movimientoCartera);
        Assert.assertTrue(movimientoCartera.getValorMovimiento().compareTo(registroCarteraDTO.getValor()) == 0);
        Assert.assertTrue(movimientoCartera.getFechaMovimiento().compareTo(registroCarteraDTO.getFechaObligacion()) == 0);
        Assert.assertTrue(movimientoCartera.getConceptoCartera().getId()
                .equals(EnumConceptoCartera.REGISTRO_CARTERA.getId()));

        // Trazabilidad
        TrazabilidadProceso trazabilidadCartera = traCarDAO.findUniqueByAttribute("id", traCarList.get(0).getId());
        Assert.assertNotNull(trazabilidadCartera);
        Assert.assertTrue(trazabilidadCartera.getActividadCartera().getId()
                .equals(EnumActividadCartera.REGISTRAR_OBLIGACION_CARTERA.getValue()));
        Assert.assertTrue(trazabilidadCartera.getEstadoObligacion().getId()
                .equals(EnumEstadoObligacion.PENDIENTE_POR_ACTIVAR.getValue()));

        BigDecimal saldo = BigDecimal.ZERO;

        // Saldos cartera
        for (SaldoCartera saldoCartera : salCarList) {
            if (saldoCartera.getTipoSaldo().getId().equals(EnumTipoSaldo.CAPITAL.getId())) {
                Assert.assertTrue(saldoCartera.getSaldo().compareTo(registroCarteraDTO.getValor()) == 0);
                saldo = saldo.add(saldoCartera.getSaldo());
            } else
                Assert.assertTrue(saldoCartera.getSaldo().compareTo(BigDecimal.ZERO) == 0);
        }

        Assert.assertTrue(saldo.compareTo(registroCarteraDTO.getValor()) == 0);
    }

}
