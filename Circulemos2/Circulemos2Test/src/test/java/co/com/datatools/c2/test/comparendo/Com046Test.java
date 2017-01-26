package co.com.datatools.c2.test.comparendo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.IRInfraccion;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.cartera.Car003Test;

@RunWith(Arquillian.class)
public class Com046Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Car003Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_046001.sql",
            "scripts/pruebas/comparendo/COM_046002.sql", };

    @EJB
    IRInfraccion iRInfraccion;

    @EJB
    IRAdministracion iRAdministracion;

    @EJB
    private IRComparendo comparendoEjb;

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
    public void consultarInfraccionTest001() {

        ConfiguracionInfraccionDTO configuracionInfraccionDTO = null;
        Calendar calendario = Calendar.getInstance();
        calendario.set(2015, 10, 6);

        Date date = calendario.getTime();

        configuracionInfraccionDTO = iRInfraccion.consultarInfraccion("A1", date);
        Assert.assertNotNull(configuracionInfraccionDTO);
    }

    /**
     * Prueba ...
     */
    @Test
    public void consultarOrganismosAsociadosTest001() {
        List<OrganismoTransitoDTO> lstOrganismoTransitoDTO = null;
        lstOrganismoTransitoDTO = iRAdministracion.consultarOrganismosAsociados(11001);
        Assert.assertNotNull(lstOrganismoTransitoDTO);
    }

    /**
     * Prueba ...
     */
    @Test
    public void consultarComparendos() {
        logger.debug("Com046Test::consultarComparendos()");
        try {
            ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();

            consultaComparendoDTO.setCodigoOrganismo(11001);

            consultaComparendoDTO.setEsPolca(false);
            consultaComparendoDTO.setIdTipoComparendo(EnumTipoComparendo.ACTA_CONTROL.getCodigo());
            consultaComparendoDTO.setNumeroComparendo("11001000000000000054");

            List<ComparendoDTO> lstComparendoDTO = comparendoEjb.consultarComparendos(consultaComparendoDTO);

            Assert.assertTrue(lstComparendoDTO != null && !lstComparendoDTO.isEmpty());
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }
}
