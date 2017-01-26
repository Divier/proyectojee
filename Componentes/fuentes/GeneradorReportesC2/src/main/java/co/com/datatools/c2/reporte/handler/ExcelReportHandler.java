package co.com.datatools.c2.reporte.handler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.com.datatools.c2.reporte.FormatoReporte;
import co.com.datatools.c2.reporte.data.AtributoEncabezado;
import co.com.datatools.c2.reporte.data.PlantillaReporte;
import co.com.datatools.c2.reporte.util.ConstantesReporte;

public class ExcelReportHandler extends AbstractReportHandler {

    private File tempFile;
    private FileOutputStream out;
    private Workbook wb;
    private ClientAnchor caATM;
    private ClientAnchor data;
    private int rowNum;
    private Sheet sheet;
    private DataFormat df;
    private Font font;
    private CellStyle cellStyle;
    private CellStyle cellStyleNumero;
    private CellStyle cellStyleDate;

    public ExcelReportHandler(String formato, String ruta, PlantillaReporte plantillaReporte) {
        super(formato, ruta, plantillaReporte);
        if (StringUtils.equalsIgnoreCase(formato, FormatoReporte.XLS.toString())) {
            wb = new HSSFWorkbook();
            caATM = new HSSFClientAnchor();
            data = new HSSFClientAnchor();
        } else if (StringUtils.equalsIgnoreCase(formato, FormatoReporte.XLSX.toString())) {
            wb = new XSSFWorkbook();
            caATM = new XSSFClientAnchor();
            data = new XSSFClientAnchor();
        }
        sheet = wb.createSheet();
        df = wb.createDataFormat();
        font = wb.createFont();
        font.setFontHeightInPoints((short) ConstantesReporte.getFuenteTamanio());
        font.setFontName(ConstantesReporte.getFuenteTipo());
        cellStyle = wb.createCellStyle();
        cellStyleNumero = wb.createCellStyle();
        cellStyleDate = wb.createCellStyle();
        cellStyle.setFont(font);
        cellStyleNumero.setFont(font);
        cellStyleDate.setFont(font);
        cellStyleNumero.setDataFormat(df.getFormat(ConstantesReporte.FORMATO_NUMERO));
        cellStyleDate.setDataFormat(
                df.getFormat(ConstantesReporte.getFormatoFecha() + " " + ConstantesReporte.getFormatoHora()));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void processHeader() {

        InputStream inputStream = null;
        try {
            Row row;
            Cell cell;
            CellStyle headerStyle = wb.createCellStyle();
            Font headerFont = wb.createFont();
            headerFont.setFontName(ConstantesReporte.getFuenteTipo());
            headerFont.setFontHeightInPoints((short) ConstantesReporte.getFuenteTamanioHeader());
            headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            headerStyle.setFont(headerFont);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource("META-INF/resources/img/static/logo-atm.jpg");
            inputStream = url.openStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            inputStream.close();
            int idImagen = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            caATM.setCol1(0);
            caATM.setRow1(0);

            if (wb instanceof XSSFWorkbook) {
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFPicture imagen = drawing.createPicture(caATM, idImagen);
                imagen.resize(2.0);
            } else if (wb instanceof HSSFWorkbook) {
                Drawing drawing = sheet.createDrawingPatriarch();
                Picture imagen = drawing.createPicture(caATM, idImagen);
                imagen.resize(2.0);
            }

            for (AtributoEncabezado atributo : plantillaReporte.getEncabezado().getAtributos()) {
                row = sheet.createRow(rowNum++);
                cell = row.createCell(2);
                if (atributo.getVariable() != null) {
                    cell.setCellValue(atributo.getVariable().getNombre());
                    cell.setCellStyle(headerStyle);
                    List list = (List) contenido.getVariablesEncabezado().get(atributo.getVariable());
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            ConstantesReporte.getFormatoFecha() + " " + ConstantesReporte.getFormatoHora());
                    for (Object o : list) {
                        row = sheet.createRow(rowNum++);
                        List subList = (List) o;
                        if (subList.size() == 2) {
                            if (subList.get(1) instanceof Date) {
                                subList.set(1, sdf.format(subList.get(1)));
                            }
                            cell = row.createCell(2);
                            cell.setCellValue(subList.get(0).toString());
                            cell.setCellStyle(headerStyle);
                            cell = row.createCell(3);
                            cell.setCellValue(subList.get(1).toString());
                            cell.setCellStyle(headerStyle);
                        }
                    }
                } else {
                    cell.setCellValue(atributo.getContenido());
                }
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
            }

            url = null;
            inputStream = null;
            url = classLoader.getResource("META-INF/resources/img/static/logo-dt-small.png");
            inputStream = url.openStream();
            bytes = IOUtils.toByteArray(inputStream);
            inputStream.close();
            idImagen = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            data.setCol1(4);
            data.setRow1(0);

            if (wb instanceof XSSFWorkbook) {
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFPicture imagen = drawing.createPicture(data, idImagen);
                imagen.resize(2.0);
            } else if (wb instanceof HSSFWorkbook) {
                Drawing drawing = sheet.createDrawingPatriarch();
                Picture imagen = drawing.createPicture(data, idImagen);
                imagen.resize(2.0);
            }

            row = sheet.createRow(rowNum++);
            int cellNum = 0;
            CellStyle headerStyle2 = wb.createCellStyle();
            headerStyle2.setFont(headerFont);
            headerStyle2.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
            headerStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
            for (AtributoEncabezado atributo : plantillaReporte.getCabecera().getCabeceras()) {
                cell = row.createCell(cellNum++);
                cell.setCellValue(atributo.getContenido());
                cell.setCellStyle(headerStyle2);
                sheet.autoSizeColumn(cellNum - 1);
            }
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("No debe suceder", e);
                }
            }
        }
    }

    @Override
    public void processLine(Collection<?> registro) {
        Row row = sheet.createRow(rowNum);
        int cellNum = 0;
        Cell cell;
        for (@SuppressWarnings("rawtypes")
        Iterator iterator = registro.iterator(); iterator.hasNext();) {
            final Object next = iterator.next();
            cell = row.createCell(cellNum);
            cellStyle.setDataFormat(df.getFormat(ConstantesReporte.FORMATO_TEXTO));
            if (next != null) {
                if (next instanceof Array) {
                    cell.setCellValue(next.toString());
                    cell.setCellStyle(cellStyle);
                } else if (next instanceof Collection) {
                    cell.setCellValue(next.toString());
                    cell.setCellStyle(cellStyle);
                } else if (next instanceof String) {
                    cell.setCellValue(next.toString());
                    cell.setCellStyle(cellStyle);
                } else if (next instanceof Date) {
                    cell.setCellValue((Date) next);
                    cell.setCellStyle(cellStyleDate);
                } else if (next instanceof Number) {
                    cell.setCellValue(((Number) next).doubleValue());
                    cell.setCellStyle(cellStyleNumero);
                } else if (next instanceof Object) {
                    cell.setCellValue(next.toString());
                    cell.setCellStyle(cellStyle);
                }
            }
            cellNum++;
        }
        rowNum++;
    }

    @Override
    public byte[] getBytes() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            wb.write(baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                throw new RuntimeException("No debe suceder", e);
            }
        }
    }

    @Override
    public File getFile() {
        try {
            if (StringUtils.isNotBlank(ruta)) {
                tempFile = new File(ruta);
            } else {
                tempFile = Files.createTempFile("", "." + formato.toString()).toFile();
            }
            out = new FileOutputStream(tempFile);
            wb.write(out);
            out.close();
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

}
