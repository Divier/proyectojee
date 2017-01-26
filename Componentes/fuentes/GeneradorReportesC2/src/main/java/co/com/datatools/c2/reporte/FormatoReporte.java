package co.com.datatools.c2.reporte;

import co.com.datatools.c2.reporte.handler.AbstractReportHandler;
import co.com.datatools.c2.reporte.handler.ExcelReportHandler;
import co.com.datatools.c2.reporte.handler.PdfReportHandler;
import co.com.datatools.c2.reporte.handler.RtfReportHandler;
import co.com.datatools.c2.reporte.handler.TxtReportHandler;
import co.com.datatools.c2.reporte.handler.WordReportHandler;

/**
 * Catalogo de Tipos de formatos que pueden ser generados por el Componente
 * 
 * @author felipe.martinez
 */
public enum FormatoReporte {

    TXT("text/plain", TxtReportHandler.class), //
    PDF("application/pdf", PdfReportHandler.class), //
    XLS("application/vnd.ms-excel", ExcelReportHandler.class), //
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ExcelReportHandler.class), //
    DOC("application/msword", WordReportHandler.class), //
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", WordReportHandler.class), //
    RTF("application/rtf", RtfReportHandler.class);

    /**
     * MIME Type asociado al formato del archivo
     */
    public String contentType;
    Class<? extends AbstractReportHandler> formatHandler;

    private FormatoReporte(String contentType, Class<? extends AbstractReportHandler> formatHandler) {
        this.contentType = contentType;
        this.formatHandler = formatHandler;
    }

}