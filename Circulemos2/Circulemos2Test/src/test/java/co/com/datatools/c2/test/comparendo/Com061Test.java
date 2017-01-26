package co.com.datatools.c2.test.comparendo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.TipoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoResolucion;
import co.com.datatools.c2.negocio.interfaces.administracion.IRAdministracionComparendo;
import co.com.datatools.c2.test.BaseTest;

@RunWith(Arquillian.class)
public class Com061Test extends BaseTest {

    private static final int CODIGO_ORGANISMO = 11001;
    private static final Logger logger = Logger.getLogger(Com061Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_061001.sql",
            "scripts/pruebas/comparendo/COM_061002.sql", };

    @EJB
    private IRAdministracionComparendo iAdminComparendo;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    /**
     * TEST001: CONSULTAR RESOLUCIONES CICOMPARENDO
     */
    @Test
    public void test001ConsultarResoluciones() {
        logger.debug("test001ConsultarResoluciones");
        ComparendoResolucionDTO comparendoResolucionDTO = new ComparendoResolucionDTO();
        comparendoResolucionDTO.setComparendo(new ComparendoDTO(122L));
        List<ComparendoResolucionDTO> comparendoResolucionList = iAdminComparendo
                .consultarResoluciones(comparendoResolucionDTO);
        Assert.assertTrue(comparendoResolucionList.size() == 3);
    }

    /**
     * TEST002: REQUIERE CICOMPARENDO O NUMERO COMPARENDO CODIGO ORGANISMO
     */
    @Test(expected = EJBException.class)
    public void test002ConsultarResoluciones() {
        logger.debug("test003ConsultarResoluciones");
        ComparendoResolucionDTO comparendoResolucionDTO = new ComparendoResolucionDTO();
        comparendoResolucionDTO.setComparendo(new ComparendoDTO());
        comparendoResolucionDTO.getComparendo().setOrdenComparendoNacional(new OrdenComparendoNacionalDTO());
        comparendoResolucionDTO.getComparendo().getOrdenComparendoNacional()
                .setNumeroComparendo("11001000000000000025");
        iAdminComparendo.consultarResoluciones(comparendoResolucionDTO);
    }

    /**
     * TEST003: CONSULTAR RESOLUCIONES NUMERO COMPARENDO CODIGO ORGANISMO
     */
    @Test
    public void test003ConsultarResoluciones() {
        logger.debug("test002ConsultarResoluciones");
        ComparendoResolucionDTO comparendoResolucionDTO = new ComparendoResolucionDTO();
        comparendoResolucionDTO.setComparendo(new ComparendoDTO());
        comparendoResolucionDTO.getComparendo().setOrdenComparendoNacional(new OrdenComparendoNacionalDTO());
        comparendoResolucionDTO.getComparendo().getOrdenComparendoNacional()
                .setNumeroComparendo("11001000000000000025");
        comparendoResolucionDTO.getComparendo().getOrdenComparendoNacional()
                .setOrganismoTransito(new OrganismoTransitoDTO(CODIGO_ORGANISMO));
        comparendoResolucionDTO.setTipoResolucion(new TipoResolucionDTO(EnumTipoResolucion.SANCION.getValue()));
        List<ComparendoResolucionDTO> comparendoResolucionList = iAdminComparendo
                .consultarResoluciones(comparendoResolucionDTO);
        Assert.assertTrue(comparendoResolucionList.size() == 2);
    }

}
