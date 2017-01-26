package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.dto.comun.ConsultaIngresoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.util.date.UtilFecha;

@RunWith(Arquillian.class)
public class UsuarioEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(UsuarioEJBTest.class.getName());
    private final static Integer ID_APP_SEGURIDAD = 1;

    @EJB
    IRUsuario usuarioEjb;

    @Test
    public void crearUsuario() {
        logger.debug("UsuarioEJBTest::crearUsuario");

        UsuarioDetalleDto usuarioDetalle = new UsuarioDetalleDto();
        usuarioDetalle.setNombres("Jose Alfredo Test");
        usuarioDetalle.setApellidos("Avella Test");
        usuarioDetalle.setEmail("jaavellat@gmail.com");

        List<RolDto> roles = new ArrayList<>();
        RolDto rol = new RolDto();
        rol.setIdRol(1);
        roles.add(rol);
        usuarioDetalle.setRoles(roles);
        usuarioDetalle.setAutenticacionConLdap(false);
        usuarioDetalle.setLogin("jaavellat");
        usuarioDetalle.setGrupos(new ArrayList<GrupoDto>());
        try {
            usuarioEjb.registrarUsuario(usuarioDetalle);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void consultarUsuario() {
        logger.debug("UsuarioEJBTest::consultarUsuario");
        UsuarioDetalleDto usuarioDto = new UsuarioDetalleDto();
        usuarioDto.setNombres("Nombre 11");
        Assert.assertNotNull(usuarioEjb.consultarUsuarios(usuarioDto, null));
    }

    @Test
    public void modificarUsuario() {

        logger.debug("UsuarioEJBTest::modificarUsuario");
        UsuarioDetalleDto usuarioDetalle = usuarioEjb.consultarUsuario("login11", true);
        usuarioDetalle.setNombres("Alfredo Jose Mod");
        try {
            usuarioEjb.actualizarUsuario(usuarioDetalle);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void eliminarUsuario() {
        logger.debug("UsuarioEJBTest::eliminarUsuario");
        UsuarioDetalleDto usuarioDetalle = usuarioEjb.consultarUsuario("login11", true);
        try {
            usuarioEjb.eliminarUsuario(usuarioDetalle.getId());
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void consultarHistoricoIngresoUsuario() {
        logger.debug("UsuarioEJBTest::consultarHistoricoIngresoUsuario");
        ConsultaIngresoDto consultaIngresoDto = new ConsultaIngresoDto();
        consultaIngresoDto.setIdAplicacion(ID_APP_SEGURIDAD);
        consultaIngresoDto.setFechaIngresoInicial(UtilFecha.stringYYYMMDDToDate("2014/01/01"));
        consultaIngresoDto.setFechaIngresoFinal(new Date());
        Assert.assertTrue(!(usuarioEjb.consultarHistoricoIngresoUsuario(consultaIngresoDto)).isEmpty());
    }

    @Test
    public void consultarHistoricoCambiosUsuario() {
        logger.debug("UsuarioEJBTest::consultarHistoricoCambiosUsuario");
        Assert.assertTrue(!(usuarioEjb.consultarHistoricoUsuario(1001, UtilFecha.stringYYYMMDDToDate("2014/01/01"),
                new Date())).isEmpty());
    }

}
