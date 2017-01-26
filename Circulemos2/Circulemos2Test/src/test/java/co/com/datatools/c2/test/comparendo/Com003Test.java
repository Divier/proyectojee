package co.com.datatools.c2.test.comparendo;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comparendo.ConsultaProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.test.BaseTest;

/**
 * CU_CIR20_DAT_COM_003 RECIBIR INCONSISTENCIAS COMPARENDO
 * 
 * @author giovanni.velandia
 */
@RunWith(Arquillian.class)
public class Com003Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Com003Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_003001.sql",
            "scripts/pruebas/comparendo/COM_003002.sql", };

    @EJB
    private IRRecibirComparendo iRRecibirComparendo;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @Test
    public void consultarProcesaComparendo() {
        logger.debug("recibirComparendoRegistroSatisfactorioBasico()");
        ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO = new ConsultaProcesaComparendoDTO();
        consultaProcesaComparendoDTO.setCodigoOrganismo(11001);
        consultaProcesaComparendoDTO.setOrigenExterno(true);
        consultaProcesaComparendoDTO.setEsPolca(false);
        List<ProcesaComparendoDTO> procesaComparendoDTOs = iRRecibirComparendo
                .consultarProcesaComparendo(consultaProcesaComparendoDTO);

        Assert.assertTrue(procesaComparendoDTOs != null && !procesaComparendoDTOs.isEmpty());
    }

    @Test
    public void recibirInconsistenciasComparendo() {
        logger.debug("recibirInconsistenciasComparendo()");
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();
        RespuestaValidacionDTO respuestaValidacionDTO = null;

        /*
         * se recibe la lista de procesa comparendos
         */
        ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO = new ConsultaProcesaComparendoDTO();
        consultaProcesaComparendoDTO.setCodigoOrganismo(11001);
        consultaProcesaComparendoDTO.setOrigenExterno(true);
        consultaProcesaComparendoDTO.setEsPolca(false);
        consultaProcesaComparendoDTO.setNumeroComparendo("11001000000000000085");
        List<ProcesaComparendoDTO> procesaComparendoDTOs = iRRecibirComparendo
                .consultarProcesaComparendo(consultaProcesaComparendoDTO);

        Assert.assertTrue(procesaComparendoDTOs != null && !procesaComparendoDTOs.isEmpty());

        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.CORREGIR_INCONSISTENCIA);
        procesarComparendoDTO.setProcesaComparendoDTO(procesaComparendoDTOs.get(0));
        try {
            respuestaValidacionDTO = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);
            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO)) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(false);
        }
    }
}
