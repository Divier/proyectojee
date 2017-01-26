package co.com.datatools.c2.test.formularios;

import java.util.ArrayList;
import java.util.Calendar;
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

import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaDetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.RangoDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.formularios.Num016Test.EnumTipoFormulario;

/**
 * Pruebas unitarias del caso de uso <b>CU_CIR20_DAT_NUM_017</b><br>
 * 
 * <b>NOTA:</b>Para ejecucion de las pruebas unitarias es necesario verificar que el siguiente constraint este corregido: ALTER TABLE
 * stock_tipo_asignacion ADD CONSTRAINT CK_stock_tipo_asign_02 CHECK (stock_minimo < stock_maximo);
 * 
 * @author luis.forero (2015-02-02)
 * 
 */
@RunWith(Arquillian.class)
public class Num018Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Num018Test.class.getName());

    private static final String[] scripts = { "scripts/pruebas/formularios/NUM_018001.sql",
            "scripts/pruebas/formularios/NUM_018002.sql", };

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @EJB
    private IRFormulario formularioEJB;

    /**
     * Prueba unitaria de consulta.
     * 
     * @author luis.forero (2015-02-02)
     */
    @Test
    public void consultarDetalleCambioEstadoTest() {
        logger.debug(Num018Test.class.getName().concat("::consultarDetalleCambioEstadoTest()"));
        // Crear el dto con los parametros de la consulta, minimo se envia el tipo de formulario y el estado de los formularios
        ConsultaDetalleCambioEstadoDTO consultaDetalleCambioEstadoDTO = new ConsultaDetalleCambioEstadoDTO();
        consultaDetalleCambioEstadoDTO.setCodigoOrganismo(11001);
        consultaDetalleCambioEstadoDTO.setIdTipoFormulario(EnumTipoFormulario.COMPARENDERA_MANUAL_NACIONAL.getId());
        consultaDetalleCambioEstadoDTO.setIdEstado(EnumEstadoFomulario.ASIGNADO.getValue());
        // Agregar los filtros ingresados por el usuario a los parametros de la consulta
        consultaDetalleCambioEstadoDTO.setIdTipoResponsable(EnumTipoResponsableFormulario.EMPRESA.getValue());
        consultaDetalleCambioEstadoDTO.setIdTipoIdentificacion(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor());
        consultaDetalleCambioEstadoDTO.setNumeroIdentificacion("10000000001");
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(Calendar.YEAR, 2010);
        Calendar fechaFinal = Calendar.getInstance();
        consultaDetalleCambioEstadoDTO.setFechaDesde(fechaInicial.getTime());
        consultaDetalleCambioEstadoDTO.setFechaHasta(fechaFinal.getTime());

        try {
            List<DetalleCambioEstadoDTO> resultados = formularioEJB
                    .consultarDetalleCambioEstado(consultaDetalleCambioEstadoDTO);
            Assert.assertFalse(resultados.isEmpty());
            consultaDetalleCambioEstadoDTO.setIdEstado(EnumEstadoFomulario.DEVUELTO.getValue());
            formularioEJB.consultarDetalleCambioEstado(consultaDetalleCambioEstadoDTO);
            Assert.assertFalse(resultados.isEmpty());
            consultaDetalleCambioEstadoDTO.setIdEstado(null);
            formularioEJB.consultarDetalleCambioEstado(consultaDetalleCambioEstadoDTO);
            Assert.assertFalse(resultados.isEmpty());
            consultaDetalleCambioEstadoDTO.setIdTipoResponsable(EnumTipoResponsableFormulario.ORGANISMO_TRANSITO
                    .getValue());
            consultaDetalleCambioEstadoDTO.setOrganismoResponsable(11001);
            consultaDetalleCambioEstadoDTO.setIdTipoFormulario(EnumTipoFormulario.INFORME_DE_ACCIDENTALIDAD.getId());
            consultaDetalleCambioEstadoDTO.setIdEstado(EnumEstadoFomulario.ASIGNADO.getValue());
            formularioEJB.consultarDetalleCambioEstado(consultaDetalleCambioEstadoDTO);
            Assert.assertFalse(resultados.isEmpty());
            consultaDetalleCambioEstadoDTO.setIdEstado(null);
            formularioEJB.consultarDetalleCambioEstado(consultaDetalleCambioEstadoDTO);
            Assert.assertFalse(resultados.isEmpty());
        } catch (CirculemosNegocioException e) {
            logger.error(e);
            Assert.assertFalse(true);
        }
        logger.debug(Num018Test.class.getName().concat("::consultarDetalleCambioEstadoTest()--FIN"));
    }

    /**
     * Anulacion de un detalle cambio estado
     * 
     * @author giovanni.velandia
     */
    @Test
    public void anularDetalleCambioEstado() {
        CambioEstadoFormularioDTO cambioEstadoFormularioDTO = new CambioEstadoFormularioDTO();
        try {
            cambioEstadoFormularioDTO.setCodigoOrganismo(11001);
            cambioEstadoFormularioDTO.setFechaAplicacionEstado(new Date());
            cambioEstadoFormularioDTO.setFolio("4");
            cambioEstadoFormularioDTO.setIdCausalCambioEstado(1);
            cambioEstadoFormularioDTO.setIdDetalleCambioEstado(-2L);
            cambioEstadoFormularioDTO.setIdEstadoFinal(EnumEstadoFomulario.ANULADO.getValue());
            cambioEstadoFormularioDTO.setNumeroDocumentoSoporte("123456");
            cambioEstadoFormularioDTO.setObservaciones("Test");
            ConsultaRangoFormularioDTO consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();
            consultaRangoFormularioDTO.setIdRango(-2L);
            List<RangoFormularioDTO> rangosF = formularioEJB.consultarRangoFormulario(consultaRangoFormularioDTO);
            cambioEstadoFormularioDTO.setListRangoDTO(new ArrayList<RangoDTO>());
            for (RangoFormularioDTO rangoFormularioDTO : rangosF) {
                RangoDTO rango = new RangoDTO(rangoFormularioDTO.getNumeroInicial(),
                        rangoFormularioDTO.getNumeroFinal());
                cambioEstadoFormularioDTO.getListRangoDTO().add(rango);
            }
            int cantidadFormularios = formularioEJB.cambiarEstadoFormularios(cambioEstadoFormularioDTO);
            Assert.assertFalse(cantidadFormularios > 0);
        } catch (CirculemosNegocioException e) {
            logger.error(e);
            Assert.assertFalse(true);
        }
    }

    /**
     * Actualizar un detalle cambio estado
     */
    @Test
    public void actualizarDetalleCambioEstado() {
        try {
            DetalleCambioEstadoDTO detalleCambioEstadoDTO = formularioEJB.consultarDetalleCambioEstado(-1L);
            detalleCambioEstadoDTO.setObservaciones("Prueba observaciones");
            detalleCambioEstadoDTO.setFolio("4444");
            formularioEJB.actualizarDetalleCambioEstado(detalleCambioEstadoDTO, null);
        } catch (CirculemosNegocioException e) {
            logger.error(e);
            Assert.assertFalse(true);
        }
        Assert.assertTrue(true);
    }
}
