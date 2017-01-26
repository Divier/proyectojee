package co.com.datatools.c2.test.reporte;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import co.com.datatools.c2.reporte.FormatoReporte;

public class TxtReportHandlerTest extends ArchivoTest {

    @Test
    public void generarReporteAsBytesTxt() {
        byte[] resultado = generador.asBytes(FormatoReporte.TXT, contenido, REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.length > 0);
    }

    @Test
    public void generarReporteAsFileTxt() {
        File resultado = generador.asFile(FormatoReporte.TXT, contenido, getRutaFinal(FormatoReporte.TXT),
                REPORTE_PRUEBA);
        Assert.assertTrue(resultado != null && resultado.exists());
    }
}
