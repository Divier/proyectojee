package co.com.datatools.c2.test.formularios;

import static org.junit.Assert.fail;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.formularios.EstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.util.RequiereFechaSistema;

/**
 * 
 * @author diego.fonseca
 * 
 */
@RunWith(Arquillian.class)
public class Num008Test extends BaseTest {
    private static final Logger logger = Logger.getLogger(Num008Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/formularios/NUM_008_001.sql",
            "scripts/pruebas/formularios/NUM_008_002.sql", };

    @EJB
    IRAdministracionFormularios administracionFormulariosEjb;

    @EJB
    IRFormulario formularioEJB;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @Test
    public void consultarEstadosFormulariosAsignacionTest001() {
        // administracionFormulariosEjb.consultarResponsablesOrganismo(11001, 1);
        logger.debug("consultarEstadosFormulariosAsignacionTest001");
        try {
            EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO = formularioEJB
                    .consultarEstadosFormulariosAsignacion(7, 1L);
        } catch (CirculemosNegocioException e) {
            fail("ERROR consultarEstadosFormulariosAsignacionTest001");
        }
    }

    @Test
    public void consultarEstadosFormulariosAsignacionTest002() {
        // administracionFormulariosEjb.consultarResponsablesOrganismo(11001, 1);
        logger.debug("consultarEstadosFormulariosAsignacionTest002");
        try {
            EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO = formularioEJB
                    .consultarEstadosFormulariosAsignacion(1, 10L);
        } catch (CirculemosNegocioException e) {
            fail("ERROR consultarEstadosFormulariosAsignacionTest002");
        }
    }

    @Test
    public void consultarEstadosFormulariosAsignacionTest003() {
        // administracionFormulariosEjb.consultarResponsablesOrganismo(11001, 1);
        logger.debug("consultarEstadosFormulariosAsignacionTest001");
        EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO;
        try {
            estadoFormularioAsignacionDTO = formularioEJB.consultarEstadosFormulariosAsignacion(1, 1L);
            Assert.assertNotNull(estadoFormularioAsignacionDTO);
        } catch (CirculemosNegocioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void consultarResponsablesOrganismoTest001() {
        List<UnificacionResponsableDTO> unificacionResponsableDTO = administracionFormulariosEjb
                .consultarResponsablesOrganismo(11001, 1);
        Assert.assertNotNull(unificacionResponsableDTO);
    }

    @Test
    public void consultarResponsablesOrganismoTest002() {
        List<UnificacionResponsableDTO> unificacionResponsableDTO = administracionFormulariosEjb
                .consultarResponsablesOrganismo(11001, 6);
        Assert.assertFalse(unificacionResponsableDTO.size() > 0);
    }

}
