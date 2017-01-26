package co.com.datatools.c2.test.reporte;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import co.com.datatools.c2.reporte.FormatoReporte;

/**
 * Clase que encapsula las pruebas unitarias de los metodos de la clase WordReportHandler, relacionados con el caso de uso CU_CIR20_DAT_ADM_006 -
 * Generar Reporte
 * 
 * @author dixon.alvarez
 */

public class WordReportHandlerTest extends ArchivoTest {

    @Test
    public void generarReporteAsBytesDoc() {
        byte[] resultado = generador.asBytes(FormatoReporte.DOC, contenido, REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.length > 0);
    }

    @Test
    public void generarReporteAsFileDoc() {
        File resultado = generador.asFile(FormatoReporte.DOC, contenido, getRutaFinal(FormatoReporte.DOC),
                REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.exists());
    }

    @Test
    public void generarReporteAsBytesDocx() {
        byte[] resultado = generador.asBytes(FormatoReporte.DOCX, contenido, REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.length > 0);
    }

    @Test
    public void generarReporteAsFileDocx() {
        File resultado = generador.asFile(FormatoReporte.DOCX, contenido, getRutaFinal(FormatoReporte.DOCX),
                REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.exists());
    }

}
