package co.com.datatools.c2.test.formularios;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.entidades.StockTipoFormulario;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarStockTipoFormulario;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.test.BaseTest;

/**
 * Pruebas unitarias del caso de uso <b>CU_CIR20_DAT_NUM_013 'Administrar Stock por Tipo de Formulario y Organismo de tránsito'</b>
 * 
 * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
 * 
 */
@Ignore
@RunWith(Arquillian.class)
public class Num013Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Num013Test.class.getName());

    @EJB
    private IRAdministracionFormularios administracionFormulariosEJB;

    /**
     * Prueba unitaria de la consulta de stock
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void consultarStockTipoFormularioTest() {
        logger.debug(Num013Test.class.getName().concat("::consultarStockTipoFormularioTest()"));
        try {
            StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
            List<StockTipoFormularioDTO> stockTipoFormularioDTOs = administracionFormulariosEJB
                    .consultarStockTipoFormulario(stockTipoFormularioDTO);
            Assert.assertFalse(stockTipoFormularioDTOs.isEmpty());
            stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO(1));
            stockTipoFormularioDTOs = administracionFormulariosEJB.consultarStockTipoFormulario(stockTipoFormularioDTO);
            Assert.assertFalse(stockTipoFormularioDTOs.isEmpty());
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        logger.debug(Num013Test.class.getName().concat("::consultarStockTipoFormularioTest()--FIN"));
    }

    /**
     * Prueba unitaria de registro de un stock de tipo de formulario.<br>
     * SC1:
     * <p>
     * <li>Ya existen datos iguales para el organismo de transito con el tipo de formulario seleccionado. No es posible realizar la operacion</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void registrarStockTipoFormularioTestSC1() {
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC1()"));
        try {

            StockTipoFormularioDTO stockTipoFormularioDTO = crearStockTipoFormularioDTO();
            stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO(1));
            try {
                administracionFormulariosEJB.registrarStockTipoFormulario(stockTipoFormularioDTO);
                Assert.fail();
            } catch (CirculemosNegocioException e) {
                Assert.assertTrue(e.getErrorInfo().getCodigoError()
                        .equals(AdministrarStockTipoFormulario.NUM_013001.getCodigoError()));
            }
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC1()--FIN"));
    }

    /**
     * Prueba unitaria de registro de un stock de tipo de formulario.<br>
     * SC2:
     * <p>
     * <li>Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%.. No es posible realizar la operacion</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void registrarStockTipoFormularioTestSC2() {
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC2()"));
        StockTipoFormularioDTO stockTipoFormularioDTO = crearStockTipoFormularioDTO();
        stockTipoFormularioDTO.setPorcentajeMaximoConsumo(-1);
        try {
            administracionFormulariosEJB.registrarStockTipoFormulario(stockTipoFormularioDTO);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(e.getErrorInfo().getCodigoError()
                    .equals(AdministrarStockTipoFormulario.NUM_013002.getCodigoError()));
        }
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC2()--FIN"));
    }

    /**
     * Prueba unitaria de registro de un stock de tipo de formulario.<br>
     * SC3:
     * <p>
     * <li>Stock de formularios es obligatorio. No es posible realizar la operacion.</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void registrarStockTipoFormularioTestSC3() {
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC3()"));
        StockTipoFormularioDTO stockTipoFormulario = crearStockTipoFormularioDTO();
        stockTipoFormulario.setPorcentajeMaximoConsumo(null);
        try {
            administracionFormulariosEJB.registrarStockTipoFormulario(stockTipoFormulario);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(e.getErrorInfo().getCodigoError()
                    .equals(AdministrarStockTipoFormulario.NUM_013003.getCodigoError()));
        }
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC3()--FIN"));
    }

    /**
     * Prueba unitaria de registro de un stock de tipo de formulario.<br>
     * SC4:
     * <p>
     * <li>Tipo de Formulario debe ser seleccionado. No es posible realizar la operacion</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void registrarStockTipoFormularioTestSC4() {
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC4()"));
        StockTipoFormularioDTO stockTipoFormulario = crearStockTipoFormularioDTO();
        stockTipoFormulario.setTipoFormulario(null);
        try {
            administracionFormulariosEJB.registrarStockTipoFormulario(stockTipoFormulario);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(e.getErrorInfo().getCodigoError()
                    .equals(AdministrarStockTipoFormulario.NUM_013004.getCodigoError()));
        }
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC4()--FIN"));
    }

    /**
     * Prueba unitaria de registro de un stock de tipo de formulario.<br>
     * SC4:
     * <p>
     * <li>Registro exitoso en el sistema</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void registrarStockTipoFormularioTestSC5() {
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC5()"));
        StockTipoFormularioDTO stockTipoFormulario = crearStockTipoFormularioDTO();
        try {
            administracionFormulariosEJB.registrarStockTipoFormulario(stockTipoFormulario);
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
        logger.debug(Num013Test.class.getName().concat("::registrarStockTipoFormularioTestSC5()--FIN"));
    }

    /**
     * Prueba unitaria de actualizacion de un stock de tipo de formulario.<br>
     * SC1:
     * <p>
     * <li>Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%. No es posible realizar la operacion</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void actualizarStockTipoFormularioTestSC1() {
        logger.debug(Num013Test.class.getName().concat("::actualizarStockTipoFormularioTestSC1()"));
        try {
            StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
            stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO(2));
            stockTipoFormularioDTO = administracionFormulariosEJB.consultarStockTipoFormulario(stockTipoFormularioDTO)
                    .get(0);
            stockTipoFormularioDTO.setPorcentajeMaximoConsumo(200);

            try {
                administracionFormulariosEJB.actualizarStockTipoFormulario(stockTipoFormularioDTO);
                Assert.fail();
            } catch (CirculemosNegocioException e) {
                Assert.assertTrue(e.getErrorInfo().getCodigoError()
                        .equals(AdministrarStockTipoFormulario.NUM_013002.getCodigoError()));
            }
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        logger.debug(Num013Test.class.getName().concat("::actualizarStockTipoFormularioTestSC1()--FIN"));
    }

    /**
     * Prueba unitaria de actualizacion de un stock de tipo de formulario.<br>
     * SC2:
     * <p>
     * <li>Stock de formularios es obligatorio. No es posible realizar la operacion</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void actualizarStockTipoFormularioTestSC2() {
        logger.debug(Num013Test.class.getName().concat("::actualizarStockTipoFormularioTestSC2()"));
        try {

            StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
            stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO(2));
            stockTipoFormularioDTO = administracionFormulariosEJB.consultarStockTipoFormulario(stockTipoFormularioDTO)
                    .get(0);
            stockTipoFormularioDTO.setPorcentajeMaximoConsumo(null);

            try {
                administracionFormulariosEJB.actualizarStockTipoFormulario(stockTipoFormularioDTO);
                Assert.fail();
            } catch (CirculemosNegocioException e) {
                Assert.assertTrue(e.getErrorInfo().getCodigoError()
                        .equals(AdministrarStockTipoFormulario.NUM_013003.getCodigoError()));
            }
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        logger.debug(Num013Test.class.getName().concat("::actualizarStockTipoFormularioTestSC2()--FIN"));
    }

    /**
     * Prueba unitaria de actualizacion de un stock de tipo de formulario.<br>
     * SC3:
     * <p>
     * <li>Actualizacion exitosa</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void actualizarStockTipoFormularioTestSC3() {
        logger.debug(Num013Test.class.getName().concat("::actualizarStockTipoFormularioTestSC3()"));

        try {

            StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
            stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO(2));
            stockTipoFormularioDTO = administracionFormulariosEJB.consultarStockTipoFormulario(stockTipoFormularioDTO)
                    .get(0);
            stockTipoFormularioDTO.setPorcentajeMaximoConsumo(63);

            try {
                administracionFormulariosEJB.actualizarStockTipoFormulario(stockTipoFormularioDTO);

                Assert.assertTrue(63 == stockTipoFormularioDTO.getPorcentajeMaximoConsumo());
            } catch (CirculemosNegocioException e) {
                Assert.fail(e.getMessage());
            }
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        logger.debug(Num013Test.class.getName().concat("::actualizarStockTipoFormularioTestSC3()--FIN"));
    }

    /**
     * Prueba unitaria de eliminacion de un stock de tipo de formulario.<br>
     * SC1:
     * <p>
     * <li>Eliminacion exitosa</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void eliminarStockTipoFormularioTestSC1() {
        logger.debug(Num013Test.class.getName().concat("::eliminarStockTipoFormularioTestSC1()"));
        try {

            StockTipoFormulario stockTipoFormulario = new StockTipoFormulario();
            stockTipoFormulario.setPorcentajeMaximoConsumo(35);
            try {
                administracionFormulariosEJB.eliminarStockTipoFormulario(3);
            } catch (CirculemosNegocioException e) {
                Assert.fail(e.getMessage());
            }

        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        logger.debug(Num013Test.class.getName().concat("::eliminarStockTipoFormularioTestSC1()--FIN"));
    }

    /**
     * Prueba unitaria de eliminacion de un stock de tipo de formulario.<br>
     * SC2:
     * <p>
     * <li>No se encontro el stock. No es posible realizar la operacion</li>
     * </p>
     * <br>
     * 
     * @author luis.forero (2015-02-04) giovanni.velandia (mod 2015-08-31)
     */
    @Test
    public void eliminarStockTipoFormularioTestSC2() {
        logger.debug(Num013Test.class.getName().concat("::eliminarStockTipoFormularioTestSC2()"));
        try {
            administracionFormulariosEJB.eliminarStockTipoFormulario(3);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(e.getErrorInfo().getCodigoError()
                    .equals(AdministrarStockTipoFormulario.NUM_013005.getCodigoError()));
        }
        logger.debug(Num013Test.class.getName().concat("::eliminarStockTipoFormularioTestSC2()--FIN"));
    }

    /**
     * Crea un stock para tipo de formulario 4 con sus datos basicos
     * 
     * @return stock con los datos basicos
     * 
     * @author luis.forero (2015-02-04)
     */
    private StockTipoFormularioDTO crearStockTipoFormularioDTO() {
        StockTipoFormularioDTO stockTipoFormulario = new StockTipoFormularioDTO();
        stockTipoFormulario.setTipoFormulario(new TipoFormularioDTO(4));
        stockTipoFormulario.setPorcentajeMaximoConsumo(48);
        return stockTipoFormulario;
    }

}
