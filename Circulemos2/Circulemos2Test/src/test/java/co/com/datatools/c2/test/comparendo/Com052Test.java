package co.com.datatools.c2.test.comparendo;

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

import co.com.datatools.c2.dto.comparendo.ConsultaCantidadComparendosNoNotificadosDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.cartera.Car003Test;

@RunWith(Arquillian.class)
public class Com052Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Car003Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_052001.sql",
            "scripts/pruebas/comparendo/COM_052002.sql", };

    private static String UBICACION_LOG = "D:\\logC2";

    @EJB
    private IRComparendo comparendoEjb;

    @EJB
    private UsuarioSesionEJB usuarioEJB;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    private static final String NOMBRE_ARCHIVO_DESCARGA = "DocumentoNotificacion_";

    /**
     * Prueba de una notificación vía correo certificado generada correctamente
     */
    @Test
    public void generarNotificacion() {
        logger.debug("Com052Test::consultarComparendosNoNotificados()");
        // Error de filtros
        try {
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificadosDTO = new ConsultaCantidadComparendosNoNotificadosDTO();
            consultarCantidadComparendosNoNotificadosDTO.setCodigoOrganismo(11001);
            // comparendoEjb.consultarCantidadComparendosNoNotificados(consultarCantidadComparendosNoNotificadosDTO);
            usuarioEJB.almacenarUsuario("admin-c2", "localhost");
            comparendoEjb.generarNotificacionCorreo(consultarCantidadComparendosNoNotificadosDTO, true, new Date());
        } catch (CirculemosNegocioException e) {
            logger.debug("Com052Test::error" + e);
            Assert.assertTrue(false);
        } catch (CirculemosAlertaException e) {
            logger.debug("Com052Test::error" + e);
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        try {

            ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO = new ConsultaNotificacionComparendoDTO();
            consultaNotificacionComparendoDTO.setCodigoOrganismo(11001);
            consultaNotificacionComparendoDTO.setEsPolca(false);
            consultaNotificacionComparendoDTO.setTipoDocumentoInfractor(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA
                    .getValor());
            consultaNotificacionComparendoDTO.setNumeroDocumentoInfractor("6000002");
            List<ResultadoConsultaNotificacionComparendoDTO> lstComparendoDTO = comparendoEjb
                    .consultarComparendosNotificados(consultaNotificacionComparendoDTO);
            Assert.assertTrue(lstComparendoDTO != null && !lstComparendoDTO.isEmpty());
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }

}
