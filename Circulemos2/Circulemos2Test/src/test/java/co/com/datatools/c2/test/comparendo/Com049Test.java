package co.com.datatools.c2.test.comparendo;

import java.util.ArrayList;
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

import co.com.datatools.c2.dto.comparendo.ConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.cartera.Car003Test;

@RunWith(Arquillian.class)
public class Com049Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Car003Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_049001.sql",
            "scripts/pruebas/comparendo/COM_049002.sql", };

    @EJB
    private IRComparendo comparendoEjb;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    /**
     * Prueba ...
     */
    @Test
    public void consultarComparendosNotificados() {
        logger.debug("Com049Test::consultarComparendosNotificados()");
        // Error de filtros
        try {

            ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO = new ConsultaNotificacionComparendoDTO();
            consultaNotificacionComparendoDTO.setCodigoOrganismo(11001);
            consultaNotificacionComparendoDTO.setEsPolca(false);
            comparendoEjb.consultarComparendosNotificados(consultaNotificacionComparendoDTO);
            Assert.assertTrue(false);
        } catch (CirculemosNegocioException e) {
            // Valido, continua
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

    /**
     * Valida que retorna exactamente la cantidad de comparendos sin tipo de notificacion.
     */
    @Test
    public void consultarComparendosNotificadosSinTipoNotificacion() {
        logger.debug("Com049Test::consultarComparendosNotificadosSinTipoNotificacion()");
        // Error de filtros
        try {

            ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO = new ConsultaNotificacionComparendoDTO();
            consultaNotificacionComparendoDTO.setCodigoOrganismo(11001);
            consultaNotificacionComparendoDTO.setEsPolca(false);
            List<ResultadoConsultaNotificacionComparendoDTO> resultado = comparendoEjb.consultarComparendosNotificados(
                    consultaNotificacionComparendoDTO, EnumTipoNotificacionComparendo.VACIO);
            Assert.assertTrue(resultado.size() == 1);
        } catch (CirculemosNegocioException e) {
            // Valido, continua
        }

    }

    /**
     * Prueba ...
     */
    @Test
    public void notificarComparendos() {
        logger.debug("Com049Test::notificarComparendos()");
        // Assert.assertTrue(true);
        NotificacionComparendoDTO notificacionComparendo = new NotificacionComparendoDTO();
        try {
            // Carga la lista de numeros de comparendos
            List<String> comparendos = new ArrayList<String>();
            comparendos.add("11001000000000000111");

            notificacionComparendo.setIdTipoNotificacion(EnumTipoNotificacionComparendo.NOTIFICACION_PERSONAL
                    .getValue());
            notificacionComparendo.setFechaNotificacion(new Date());
            notificacionComparendo.setComparendos(comparendos);
            byte[] archivo = comparendoEjb.notificarComparendos(notificacionComparendo);
            Assert.assertTrue(archivo != null);
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }
}
