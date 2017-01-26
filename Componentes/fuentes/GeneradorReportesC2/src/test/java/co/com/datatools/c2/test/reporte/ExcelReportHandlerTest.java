package co.com.datatools.c2.test.reporte;

import java.io.File;

import org.junit.Assert;

import co.com.datatools.c2.reporte.FormatoReporte;

/**
 * Clase que encapsula las pruebas unitarias de los metodos de la clase ExcelReportHandler, relacionados con el caso de uso CU_CIR20_DAT_ADM_006 -
 * Generar Reporte
 * 
 * @author dixon.alvarez
 */

public class ExcelReportHandlerTest extends ArchivoTest {

    // @Test
    public void generarReporteAsBytesXls() {
        byte[] resultado = generador.asBytes(FormatoReporte.XLS, contenido, REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.length > 0);
    }

    // @Test
    public void generarReporteAsFileXls() {
        File resultado = generador.asFile(FormatoReporte.XLS, contenido, getRutaFinal(FormatoReporte.XLS),
                REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.exists());
    }

    // @Test
    public void generarReporteAsBytesXlsx() {
        byte[] resultado = generador.asBytes(FormatoReporte.XLSX, contenido, REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.length > 0);
    }

    // @Test
    public void generarReporteAsFileXlsx() {
        File resultado = generador.asFile(FormatoReporte.XLSX, contenido, getRutaFinal(FormatoReporte.XLSX),
                REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.exists());
    }

}
