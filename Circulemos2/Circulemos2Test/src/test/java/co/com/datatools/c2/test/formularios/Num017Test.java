package co.com.datatools.c2.test.formularios;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.test.BaseTest;

/**
 * Pruebas unitarias del caso de uso <b>CU_CIR20_DAT_NUM_017</b><br>
 * 
 * <b>NOTA:</b>Para ejecucion de las pruebas unitarias es necesario verificar que el siguiente constraint este corregido: ALTER TABLE
 * stock_tipo_asignacion ADD CONSTRAINT CK_stock_tipo_asign_02 CHECK (stock_minimo < stock_maximo);
 * 
 * @author luis.forero (2015-02-02)
 * 
 */
@Ignore
@RunWith(Arquillian.class)
public class Num017Test extends BaseTest {

    private static final Logger logger = Logger.getLogger(Num017Test.class.getName());

    @EJB
    private IRFormulario formularioEJB;

    /*  *//**
     * Prueba unitaria de consulta.
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void consultarStockTipoAsignacionTest() { logger.debug(Num017Test.class.getName().concat("::consultarStockTipoAsignacionTest()"));
     * List<StockTipoAsignacionDTO> lstResponse = formularioEJB.consultarStockTipoAsignacion(null, null, null);
     * Assert.assertFalse(lstResponse.isEmpty()); lstResponse = formularioEJB.consultarStockTipoAsignacion(1, null, null);
     * Assert.assertFalse(lstResponse.isEmpty()); lstResponse = formularioEJB.consultarStockTipoAsignacion(1, 1, null);
     * Assert.assertFalse(lstResponse.isEmpty()); lstResponse = formularioEJB.consultarStockTipoAsignacion(1, 1, true);
     * Assert.assertFalse(lstResponse.isEmpty()); lstResponse = formularioEJB.consultarStockTipoAsignacion(1, 2, false);
     * Assert.assertFalse(lstResponse.isEmpty()); logger.debug(Num017Test.class.getName().concat("::consultarStockTipoAsignacionTest()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC1:
     * <p>
     * <li>Tipo de formulario vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC1() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC1()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); stockTipoAsignacionDTO.setTipoFormulario(null); try { formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO);
     * Assert.fail(); } catch (CirculemosNegocioException e) { Assert.assertTrue(e.getErrorInfo().getCodigoError()
     * .equals(AdministrarStockTipoAsignacion.NUM_017001.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC1()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC2:
     * <p>
     * <li>Tipo de asignacion vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC2() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC2()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); stockTipoAsignacionDTO.setTipoAsignacion(null); try { formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO);
     * Assert.fail(); } catch (CirculemosNegocioException e) { Assert.assertTrue(e.getErrorInfo().getCodigoError()
     * .equals(AdministrarStockTipoAsignacion.NUM_017002.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC2()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC3:
     * <p>
     * <li>Rango stock minimo vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC3() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC3()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); stockTipoAsignacionDTO.setStockMinimo(null); try { formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO);
     * Assert.fail(); } catch (CirculemosNegocioException e) { Assert.assertTrue(e.getErrorInfo().getCodigoError()
     * .equals(AdministrarStockTipoAsignacion.NUM_017003.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC3()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC4:
     * <p>
     * <li>Rango stock maximo vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC4() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC4()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); stockTipoAsignacionDTO.setStockMaximo(null); try { formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO);
     * Assert.fail(); } catch (CirculemosNegocioException e) { Assert.assertTrue(e.getErrorInfo().getCodigoError()
     * .equals(AdministrarStockTipoAsignacion.NUM_017004.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC4()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC5:
     * <p>
     * <li>Rango maximo menor a rango minimo</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC5() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC5()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); stockTipoAsignacionDTO.setStockMaximo(-1); try { formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO);
     * Assert.fail(); } catch (CirculemosNegocioException e) { Assert.assertTrue(e.getErrorInfo().getCodigoError()
     * .equals(AdministrarStockTipoAsignacion.NUM_017005.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC5()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC6:
     * <p>
     * <li>Stock existente para los mismos tipos de asignacion y formulario</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC6() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC6()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); stockTipoAsignacionDTO.setTipoFormulario(new TipoFormularioDTO(1)); try {
     * formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO); Assert.fail(); } catch (CirculemosNegocioException e) {
     * Assert.assertTrue(e.getErrorInfo().getCodigoError() .equals(AdministrarStockTipoAsignacion.NUM_017006.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC6()--FIN")); }
     *//**
     * Prueba unitaria de registro de un stock<br>
     * SC7:
     * <p>
     * <li>Registro exitoso</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void registrarStockTipoAsignacionTestSC7() {
     * logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC7()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * crearStock(); try { formularioEJB.registrarStockTipoAsignacion(stockTipoAsignacionDTO); } catch (CirculemosNegocioException e) {
     * Assert.fail(e.getMessage()); } logger.debug(Num017Test.class.getName().concat("::registrarStockTipoAsignacionTestSC7()--FIN")); }
     *//**
     * Prueba unitaria de actualizacion de un stock<br>
     * SC1:
     * <p>
     * <li>Rango minimo no puede estar vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void actualizarStockTipoAsignacionTestSC1() {
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC1()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * formularioEJB.consultarStockTipoAsignacion(2, 2, true).get(0); stockTipoAsignacionDTO.setStockMinimo(null); try {
     * formularioEJB.actualizarStockTipoAsignacion(stockTipoAsignacionDTO); Assert.fail(); } catch (CirculemosNegocioException e) {
     * Assert.assertTrue(e.getErrorInfo().getCodigoError() .equals(AdministrarStockTipoAsignacion.NUM_017003.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC1()--FIN")); }
     *//**
     * Prueba unitaria de actualizacion de un stock<br>
     * SC2:
     * <p>
     * <li>Rango Maximo no puede estar vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void actualizarStockTipoAsignacionTestSC2() {
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC2()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * formularioEJB.consultarStockTipoAsignacion(2, 2, true).get(0); stockTipoAsignacionDTO.setStockMaximo(null); try {
     * formularioEJB.actualizarStockTipoAsignacion(stockTipoAsignacionDTO); Assert.fail(); } catch (CirculemosNegocioException e) {
     * Assert.assertTrue(e.getErrorInfo().getCodigoError() .equals(AdministrarStockTipoAsignacion.NUM_017004.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC2()--FIN")); }
     *//**
     * Prueba unitaria de actualizacion de un stock<br>
     * SC3:
     * <p>
     * <li>Rango Maximo no puede estar vacio</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void actualizarStockTipoAsignacionTestSC3() {
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC3()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * formularioEJB.consultarStockTipoAsignacion(2, 2, true).get(0); stockTipoAsignacionDTO.setStockMaximo(-1); try {
     * formularioEJB.actualizarStockTipoAsignacion(stockTipoAsignacionDTO); Assert.fail(); } catch (CirculemosNegocioException e) {
     * Assert.assertTrue(e.getErrorInfo().getCodigoError() .equals(AdministrarStockTipoAsignacion.NUM_017005.getCodigoError())); }
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC3()--FIN")); }
     *//**
     * Prueba unitaria de actualizacion de un stock<br>
     * SC4:
     * <p>
     * <li>Cambio exitoso</li>
     * </p>
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void actualizarStockTipoAsignacionTestSC4() {
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC4()")); StockTipoAsignacionDTO stockTipoAsignacionDTO =
     * formularioEJB.consultarStockTipoAsignacion(2, 2, true).get(0); stockTipoAsignacionDTO.setEstadoStock(false);
     * stockTipoAsignacionDTO.setStockMaximo(800000001); try { formularioEJB.actualizarStockTipoAsignacion(stockTipoAsignacionDTO); } catch
     * (CirculemosNegocioException e) { Assert.fail(e.getMessage()); }
     * logger.debug(Num017Test.class.getName().concat("::actualizarStockTipoAsignacionTestSC4()--FIN")); }
     *//**
     * Prueba unitaria de eliminación de stock
     * 
     * @author luis.forero (2015-02-02)
     */
    /*
     * @Test public void eliminarStockTipoAsignacionTest() { logger.debug(Num017Test.class.getName().concat("::eliminarStockTipoAsignacionTest()"));
     * formularioEJB.eliminarStockTipoAsignacion(-4); logger.debug(Num017Test.class.getName().concat("::eliminarStockTipoAsignacionTest()--FIN")); }
     *//**
     * Crea un stock con sus respectivos datos
     * 
     * @return stock inicializado
     * @author luis.forero (2015-02-02)
     */
    /*
     * private StockTipoAsignacionDTO crearStock() { StockTipoAsignacionDTO stockTipoAsignacionDTO = new StockTipoAsignacionDTO();
     * stockTipoAsignacionDTO.setTipoFormulario(new TipoFormularioDTO(2)); stockTipoAsignacionDTO.setTipoAsignacion(new
     * TipoAsignacionFormularioDTO(1)); stockTipoAsignacionDTO.setStockMinimo(1); stockTipoAsignacionDTO.setStockMaximo(5); return
     * stockTipoAsignacionDTO; }
     */
}
