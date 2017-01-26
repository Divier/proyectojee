package co.com.datatools.c2.test.reporte;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import co.com.datatools.c2.reporte.FormatoReporte;

/**
 * Clase que encapsula las pruebas unitarias de los metodos de la clase PdfReportHandler, relacionados con el caso de uso CU_CIR20_DAT_ADM_006 -
 * Generar Reporte
 * 
 * @author dixon.alvarez
 */

public class PdfReportHandlerTest extends ArchivoTest {

    @Test
    public void generarReporteAsBytesPdf() {
        byte[] resultado = generador.asBytes(FormatoReporte.PDF, contenido, REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.length > 0);
    }

    @Test
    public void generarReporteAsFilePdf() {
        File resultado = generador.asFile(FormatoReporte.PDF, contenido, getRutaFinal(FormatoReporte.PDF),
                REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.exists());
    }

}
