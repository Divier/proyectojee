package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRRol;
import co.com.datatools.util.date.UtilFecha;

@RunWith(Arquillian.class)
public class RolEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(RolEJBTest.class.getName());

    @EJB
    IRRol rolEjb;

    @Test
    public void crearRol() {
        logger.debug("RolEJBTest::crearRol");
        RolDetalleDto rolDetalleDto = new RolDetalleDto();
        rolDetalleDto.setActivo(true);
        rolDetalleDto.setNombre("Rol Prueba 001");
        rolDetalleDto.setDescripcion("Des Rol Prueba 001");
        Map<String, List<RecursoDetalleDto>> recursosAplicacion = new HashMap<>();
        List<RecursoDetalleDto> recursos = new ArrayList<>();
        RecursoDetalleDto recDetalleDto = new RecursoDetalleDto();
        List<OperacionDto> operaciones = new ArrayList<>();
        OperacionDto operacion1 = new OperacionDto();
        // recurso-operacion 7
        operacion1.setIdOperacion(7);
        operaciones.add(operacion1);
        recDetalleDto.setOperaciones(operaciones);
        recursos.add(recDetalleDto);
        recursosAplicacion.put("SEGURIDAD", recursos);
        rolDetalleDto.setRecursosAplicacion(recursosAplicacion);

        try {
            rolEjb.registrarRol(rolDetalleDto);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void consultarRoles() {
        logger.debug("RolEJBTest::consultarRoles");
        final RolDetalleDto rolDto = new RolDetalleDto();
        rolDto.setNombre("PUBLICO");
        Assert.assertTrue(!(rolEjb.consultarRoles(rolDto)).isEmpty());
    }

    @Test
    public void modificarRol() {
        logger.debug("RolEJBTest::modificarRol");
        RolDetalleDto rolModificar = rolEjb.consultarRol(1000);
        rolModificar.setNombre("Rol prueba 000 Modificado");
        rolModificar.setDescripcion("Modificacion");
        RolDetalleDto rolPadre = new RolDetalleDto();
        rolPadre.setIdRol(2);
        rolModificar.setRolPadre(rolPadre);
        try {
            rolEjb.actualizarRol(rolModificar);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void eliminarRol() {
        logger.debug("RolEJBTest::eliminarRol");
        try {
            rolEjb.eliminarRol(1000);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarHistoricoCambiosRol() {
        logger.debug("RolEJBTest::consultarHistoricoCambiosRol");
        Assert.assertTrue(!(rolEjb.consultarDetalleHistoricoRol(1001, UtilFecha.stringYYYMMDDToDate("2014/01/01"),
                new Date())).isEmpty());
    }

}
