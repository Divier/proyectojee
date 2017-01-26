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
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRRecursoOperacion;

@RunWith(Arquillian.class)
public class RecursoEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(RecursoEJBTest.class.getName());

    @EJB
    IRRecursoOperacion recursoOperacionEjb;

    @Test
    public void crearRecurso() {
        logger.debug("RecursoEJBTest::crearRecurso");
        RecursoDetalleDto recursoDetalle = new RecursoDetalleDto();
        recursoDetalle.setIdAplicacion(String.valueOf(1));
        recursoDetalle.setNombreRecurso("recursoTest");
        recursoDetalle.setDescripcion("Recurso Test 002");

        RecursoDto recursoFiltro = new RecursoDto();
        recursoFiltro.setNombreRecurso("recurso0001");
        List<RecursoDto> recPadre = recursoOperacionEjb.consultarRecursos(recursoFiltro, false);
        recursoDetalle.setPadre(recPadre.get(0));
        List<OperacionDto> operaciones = new ArrayList<>();

        OperacionDto operacionIngreso = new OperacionDto();
        operacionIngreso.setIdOperacion(1);
        operacionIngreso.setNombreOperacion("ingresar");
        operaciones.add(operacionIngreso);
        recursoDetalle.setOperaciones(operaciones);

        try {
            recursoOperacionEjb.registrarRecurso(recursoDetalle);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void consultarRecurso() {
        logger.debug("RecursoEJBTest::consultarRecurso");
        RecursoDto recursoFiltro = new RecursoDto();
        recursoFiltro.setNombreRecurso("recurso0001");
        Assert.assertNotNull(recursoOperacionEjb.consultarRecursos(recursoFiltro, false));
    }

    @Test
    public void modificarRecurso() {
        logger.debug("RecursoEJBTest::modificarRecurso");
        RecursoDto recursoFiltro = new RecursoDto();
        recursoFiltro.setNombreRecurso("recurso0001");
        List<RecursoDto> resultado = recursoOperacionEjb.consultarRecursos(recursoFiltro, false);
        RecursoDetalleDto detalleRecurso = recursoOperacionEjb.consultarRecurso(resultado.get(0).getIdRecurso());
        detalleRecurso.setDescripcion("Recurso 0001 Mod");

        List<OperacionDto> operacionesNuevas = new ArrayList<>();
        OperacionDto operacionIngresar = new OperacionDto();
        operacionIngresar.setIdOperacion(1);
        operacionesNuevas.add(operacionIngresar);
        OperacionDto operacionConsultar = new OperacionDto();
        operacionConsultar.setIdOperacion(3);
        operacionesNuevas.add(operacionConsultar);
        detalleRecurso.setOperaciones(operacionesNuevas);

        try {
            recursoOperacionEjb.actualizarRecurso(detalleRecurso);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void eliminarRecurso() {
        logger.debug("RecursoEJBTest::eliminarRecurso");
        RecursoDto recursoFiltro = new RecursoDto();
        recursoFiltro.setNombreRecurso("recurso0002");
        List<RecursoDto> resultado = recursoOperacionEjb.consultarRecursos(recursoFiltro, false);
        try {
            recursoOperacionEjb.eliminarRecurso(resultado.get(0).getIdRecurso());
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }
}
