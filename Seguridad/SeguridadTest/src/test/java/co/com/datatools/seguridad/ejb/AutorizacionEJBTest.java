package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.interfaces.IRAutorizacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;

@RunWith(Arquillian.class)
public class AutorizacionEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(AutorizacionEJBTest.class.getName());

    private final static String NOMBRE_APP = "SEGURIDAD";

    @EJB
    IRAutorizacion autorizacionEjb;

    @Test
    public void consultarOperacionesPermitidasSinAutenticacion() {
        // Busca las accciones permitidas para un recurso, con el rol publico
        logger.debug("AutorizacionEJBTest::consultarOperacionesPermitidasSinAutenticacion");
        Assert.assertTrue(!autorizacionEjb.consultarOperacionesPermitidasSinAutenticacion(NOMBRE_APP, "main").isEmpty());

    }

    @Test
    public void consultarOperacionesPermitidasUsuario() {
        logger.debug("AutorizacionEJBTest::consultarOperacionesPermitidasUsuario");
        Assert.assertTrue(!autorizacionEjb.consultarOperacionesPermitidasUsuario(NOMBRE_APP, "admin", "usuarios")
                .isEmpty());

    }

    @Test
    public void consultarOperacionesPermitidasRoles() {
        logger.debug("AutorizacionEJBTest::consultarOperacionesPermitidasRoles");
        List<String> nombresRol = new ArrayList<String>();
        nombresRol.add(ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);
        nombresRol.add(ConstantesSeguridad.NOMBRE_ROL_PUBLICO);
        Assert.assertTrue(!autorizacionEjb.consultarOperacionesPermitidasRoles(NOMBRE_APP, nombresRol, "roles")
                .isEmpty());

    }

    @Test
    public void esRecursoPermitidoSinAutenticacion() {
        logger.debug("AutorizacionEJBTest::esRecursoPermitidoSinAutenticacion");
        Assert.assertFalse(autorizacionEjb.esRecursoPermitidoSinAutenticacion(NOMBRE_APP, "usuarios"));
    }

    @Test
    public void esRecursoPermitidoUsuario() {
        logger.debug("AutorizacionEJBTest::esRecursoPermitidoUsuario");
        Assert.assertTrue(autorizacionEjb.esRecursoPermitidoUsuario(NOMBRE_APP, "admin", "usuarios"));
    }

    @Test
    public void esRecursoPermitidoRoles() {
        logger.debug("AutorizacionEJBTest::esRecursoPermitidoRoles");
        List<String> nombresRol = new ArrayList<String>();
        nombresRol.add(ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);
        nombresRol.add(ConstantesSeguridad.NOMBRE_ROL_PUBLICO);
        Assert.assertTrue(autorizacionEjb.esRecursoPermitidoRoles(NOMBRE_APP, nombresRol, "roles"));
    }
}
