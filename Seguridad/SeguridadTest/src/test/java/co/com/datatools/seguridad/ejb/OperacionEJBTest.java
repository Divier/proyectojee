package co.com.datatools.seguridad.ejb;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRRecursoOperacion;

@RunWith(Arquillian.class)
public class OperacionEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(RolEJBTest.class.getName());

    @EJB
    IRRecursoOperacion recursoOperacionEjb;

    @Test
    public void crearOperacion() {
        logger.debug("OperacionEJBTest::crearOperacion");
        OperacionDto operacionDto = new OperacionDto();
        operacionDto.setNombreOperacion("Operacion Prueba");
        try {
            recursoOperacionEjb.registrarOperacion(operacionDto);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void consultarOperaciones() {
        logger.debug("OperacionEJBTest::consultarOperaciones");
        List<OperacionDto> resultado = recursoOperacionEjb.consultarOperaciones();
        Assert.assertTrue(!resultado.isEmpty());
    }

    @Test
    public void modificarOperacion() {
        logger.debug("OperacionEJBTest::modificarOperacion");

        try {
            OperacionDto operacionDto = new OperacionDto();
            operacionDto.setIdOperacion(6);
            operacionDto.setNombreOperacion("validarModificado");
            recursoOperacionEjb.actualizarOperacion(operacionDto);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void eliminarOperacion() {
        logger.debug("OperacionEJBTest::eliminarOperacion");
        try {
            recursoOperacionEjb.eliminarOperacion(7);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }

}
