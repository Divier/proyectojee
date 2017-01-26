package co.com.datatools.seguridad.ejb;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRGrupo;

@RunWith(Arquillian.class)
public class GrupoEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(GrupoEJBTest.class.getName());

    private static final String APLICA_USUARIOS = "Usuarios";

    @EJB
    IRGrupo grupoEjb;

    @Test
    public void crearGrupo() {
        logger.debug("GrupoEJBTest::crearGrupo");
        GrupoDetalleDto grupoDetalle = new GrupoDetalleDto();
        grupoDetalle.setActivo(true);
        grupoDetalle.setNombre("Grupo Test 001");
        grupoDetalle.setDescripcion("Descripcion Grupo Test 001");
        grupoDetalle.setClase(APLICA_USUARIOS);
        try {
            grupoEjb.registrarGrupo(grupoDetalle);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void consultarGrupo() {
        logger.debug("GrupoEJBTest::consultarGrupo");
        GrupoDto grupoConsulta = new GrupoDto();
        grupoConsulta.setNombre("Grupo1");
        Assert.assertNotNull(grupoEjb.consultarGrupos(grupoConsulta));
    }

    @Test
    public void modificarGrupo() {
        logger.debug("GrupoEJBTest::modificarGrupo");
        GrupoDto grupoConsulta = new GrupoDto();
        grupoConsulta.setNombre("Grupo1");
        List<GrupoDto> grupos = grupoEjb.consultarGrupos(grupoConsulta);
        GrupoDto grupo = grupos.get(0);
        GrupoDetalleDto grupoActualizar = new GrupoDetalleDto();
        grupoActualizar.setIdGrupo(grupo.getIdGrupo());
        grupoActualizar.setActivo(grupo.getActivo());
        grupoActualizar.setDescripcion("Descripcion Grupo 1 Modificada");
        grupoActualizar.setNombre(grupo.getNombre());
        grupoActualizar.setClase(grupo.getClase());
        try {
            grupoEjb.actualizarGrupo(grupoActualizar);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void eliminarGrupo() {
        logger.debug("GrupoEJBTest::eliminarGrupo");
        GrupoDto grupoConsulta = new GrupoDto();
        grupoConsulta.setNombre("Grupo2");
        List<GrupoDto> grupos = grupoEjb.consultarGrupos(grupoConsulta);
        GrupoDto grupo = grupos.get(0);
        try {
            grupoEjb.eliminarGrupo(grupo.getIdGrupo());
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }
}
