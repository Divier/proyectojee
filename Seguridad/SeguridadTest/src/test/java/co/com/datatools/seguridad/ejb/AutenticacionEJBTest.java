package co.com.datatools.seguridad.ejb;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.comun.InfoVinculoRecuperacionDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;

@RunWith(Arquillian.class)
public class AutenticacionEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(AutenticacionEJBTest.class.getName());

    private static final String FLUJO_RECUPERACION = ConstantesSeguridad.PAGINA_RESTABLECER_PW;

    private static final String KEY_RECUPERACION = ConstantesSeguridad.NOMBRE_PARAMETRO_KEY_RECUPERACION;

    private static final String APLICACION = "SEGURIDAD";

    @EJB
    IRAutenticacion autenticacionEjb;

    @Test
    public void actualizarCorreoElectronico() {
        logger.debug("AutenticacionEJBTest::actualizarCorreoElectronico");
        try {
            autenticacionEjb.actualizarCorreoElectronico("usuarioPwTemporal", "nuevoCorreo@gmail.com");
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }

    // Pruebas para cambio de password
    @Test
    public void cambiarPasswordNoCumplePoliticas() {
        logger.debug("AutenticacionEJBTest::cambiarPassword");
        try {
            autenticacionEjb.cambiarPassword("usuarioPwActivo", "p1", "p2");
            Assert.assertTrue(false);
        } catch (SeguridadException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void cambiarPasswordCumplePoliticas() {
        logger.debug("AutenticacionEJBTest::cambiarPassword");
        try {
            autenticacionEjb.cambiarPassword("usuarioPwActivo2", "p1", "Usuario2.");
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }

    // Pruebas para recuperacion de password por olvido
    @Test
    public void enviarVinculoRecuperacionFallido() {
        try {
            InfoVinculoRecuperacionDto infoVinculoRecuperacionDto = new InfoVinculoRecuperacionDto();
            infoVinculoRecuperacionDto.setAplicacion(APLICACION);
            infoVinculoRecuperacionDto.setFlujoRecuperacionPw(FLUJO_RECUPERACION);
            infoVinculoRecuperacionDto.setKeyRecuperacion(KEY_RECUPERACION);
            infoVinculoRecuperacionDto.setLogin("usrPruebaRecuperaPw");
            autenticacionEjb.enviarVinculoRecuperacion(infoVinculoRecuperacionDto);
            Assert.assertTrue(false);
        } catch (SeguridadException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void enviarVinculoRecuperacionExitoso() {
        try {
            InfoVinculoRecuperacionDto infoVinculoRecuperacionDto = new InfoVinculoRecuperacionDto();
            infoVinculoRecuperacionDto.setAplicacion(APLICACION);
            infoVinculoRecuperacionDto.setFlujoRecuperacionPw(FLUJO_RECUPERACION);
            infoVinculoRecuperacionDto.setKeyRecuperacion(KEY_RECUPERACION);
            infoVinculoRecuperacionDto.setLogin("admin");
            autenticacionEjb.enviarVinculoRecuperacion(infoVinculoRecuperacionDto);
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void verificarSolicitudRecuperacionAbierta() {
        boolean solicitudAbierta = autenticacionEjb
                .verificarSolicitudRecuperacionAbierta("XSfKMA6JLIXDEMD4n1P9GMeWYN3vvvHYRb2RvQZ0uw4/oZh6qreirMxQPVusqGqsWXWa5JEg4K685h1Kb9EqKQ==");
        if (solicitudAbierta)
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);
    }

    @Test
    public void verificarSolicitudRecuperacionNoVencida() {
        boolean solicitudVencida = autenticacionEjb
                .verificarSolicitudRecuperacionVencida("XSfKMA6JLIXDEMD4n1P9GMeWYN3vvvHYRb2RvQZ0uw4/oZh6qreirMxQPVusqGqsWXWa5JEg4K685h1Kb9EqKQ==");
        if (solicitudVencida)
            Assert.assertTrue(false);
        else
            Assert.assertTrue(true);
    }

    @Test
    public void restablecerPasswordExitoso() {
        try {
            autenticacionEjb.restablecerPassword("usrRecuperaPw",
                    "CfDxsMh/P0OQ5wYM7pEqhuLEK1xgFDBZH6fW4TfgMxuLEP/ojqfW29MuTgAuXp6l6f3R2rnkuhh+r7kntYZcDg==",
                    "pruebarecupera@gmail.com", "pwActivo.1");
            Assert.assertTrue(true);
        } catch (SeguridadException e) {
            Assert.assertTrue(false);
        }
    }

}
