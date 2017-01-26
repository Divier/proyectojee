package co.com.datatools.util.file.archivo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.com.datatools.util.file.ArchivoTemporal;

/**
 * Contenido de un archivo en MICROSOFT EXCEL (XLS, XLSX) organizado por filas y columnas o mediante un arreglo de bytes (archivo del sistema en
 * memoria)
 * 
 * @author rodrigo.cruz
 * 
 */
public class ContenidoArchivoExcelDTO extends ContenidoArchivoDTO {

    private static final String FORMATO_INVALIDO = "(Formato inválido)";
    private static final String ERROR_GENERACION = "Error de generación de archivo Excel";

    private Workbook wb;
    private DataFormat df;
    private Font font;
    private CellStyle cellStyle;
    private CellStyle cellStyleNumero;
    private CellStyle cellStyleDate;

    public ContenidoArchivoExcelDTO(byte[] contenido, EnumTipoArchivo tipoArchivo) {
        super(contenido, tipoArchivo);
    }

    public ContenidoArchivoExcelDTO(byte[] contenido, EnumTipoArchivo tipoArchivo, String rutaTemporal) {
        super(contenido, tipoArchivo, rutaTemporal);
    }

    public ContenidoArchivoExcelDTO(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        super(filaArchivoList, tipoArchivo);
    }

    @Override
    public List<FilaArchivoDTO> getFilas() {
        try {
            File file = ArchivoTemporal.crearArchivoTemporal(contenido, rutaTemporal, tipoArchivo.toString());
            switch (tipoArchivo) {
            case XLS:
            case XLSX:
                wb = WorkbookFactory.create(file);
                break;
            default:
                throw new RuntimeException(ERROR_GENERACION + " " + FORMATO_INVALIDO);
            }
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            throw new RuntimeException(ERROR_GENERACION, e);
        }

        filaArchivoList = new ArrayList<>();
        Iterator<Sheet> hojaIterator = wb.sheetIterator();
        int hojaIndex = 0;

        // Hojas
        while (hojaIterator.hasNext()) {
            Sheet hoja = hojaIterator.next();
            Iterator<Row> filaIterator = hoja.rowIterator();
            int filaIndex = 0;

            // Filas
            while (filaIterator.hasNext()) {
                FilaArchivoExcelDTO filaArchivo = new FilaArchivoExcelDTO();
                filaArchivo.setHoja(hojaIndex);

                if (filaIndex++ == 0)
                    filaArchivo.setEncabezado(true);

                Row fila = filaIterator.next();
                Iterator<Cell> celdaIterator = fila.cellIterator();
                int columna = 0, columnasVacias = 0;

                // Celdas
                while (celdaIterator.hasNext()) {
                    Cell celda = celdaIterator.next();

                    while (columna++ < celda.getColumnIndex()) {
                        filaArchivo.getCeldas().add(null);
                        columnasVacias++;
                    }

                    switch (celda.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        filaArchivo.getCeldas().add(celda.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        filaArchivo.getCeldas().add(celda.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        filaArchivo.getCeldas().add(procesarCadena(celda.getStringCellValue()));
                        break;
                    case Cell.CELL_TYPE_ERROR:
                    case Cell.CELL_TYPE_BLANK:
                    case Cell.CELL_TYPE_FORMULA:
                    default:
                        filaArchivo.getCeldas().add(null);
                        columnasVacias++;
                    }
                }

                if (columnasVacias < filaArchivo.getCeldas().size())
                    filaArchivoList.add(filaArchivo);
            }

            hojaIndex++;
        }

        return filaArchivoList;
    }

    @Override
    public byte[] getBytes() {
        if (filaArchivoList == null)
            return null;

        switch (tipoArchivo) {
        case XLS:
            wb = new HSSFWorkbook();
            break;
        case XLSX:
            wb = new XSSFWorkbook();
            break;
        default:
            throw new RuntimeException(ERROR_GENERACION + " " + FORMATO_INVALIDO);
        }

        df = wb.createDataFormat();
        font = wb.createFont();
        font.setFontHeightInPoints((short) ConstantesArchivo.getFuenteTamanio());
        font.setFontName(ConstantesArchivo.getFuenteTipo());
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        cellStyleNumero = wb.createCellStyle();
        cellStyleNumero.setFont(font);
        cellStyleNumero.setDataFormat(df.getFormat(ConstantesArchivo.getFormatoNumero()));
        cellStyleDate = wb.createCellStyle();
        cellStyleDate.setFont(font);
        cellStyleDate.setDataFormat(df.getFormat(ConstantesArchivo.getFormatoFecha() + " "
                + ConstantesArchivo.getFormatoHora()));

        Sheet sheet = wb.createSheet();
        int rowNum = 0, hojaActual = 0;

        // Hojas y filas
        for (FilaArchivoDTO filaArchivo : filaArchivoList) {
            FilaArchivoExcelDTO filaArchivoExcel = (FilaArchivoExcelDTO) filaArchivo;

            if (filaArchivoExcel.getHoja() > hojaActual) {
                hojaActual = filaArchivoExcel.getHoja();
                sheet = wb.createSheet();
            }

            Row row = sheet.createRow(rowNum++);
            Cell cell;

            int cellNum = 0;

            // Celdas
            for (Object celda : filaArchivoExcel.getCeldas()) {
                cell = row.createCell(cellNum++);
                cellStyle.setDataFormat(df.getFormat(ConstantesArchivo.getFormatoTexto()));

                if (celda != null) {
                    if (celda instanceof Array) {
                        cell.setCellValue(celda.toString());
                        cell.setCellStyle(cellStyle);
                    } else if (celda instanceof Collection) {
                        cell.setCellValue(celda.toString());
                        cell.setCellStyle(cellStyle);
                    } else if (celda instanceof String) {
                        cell.setCellValue(celda.toString());
                        cell.setCellStyle(cellStyle);
                    } else if (celda instanceof Date) {
                        cell.setCellValue((Date) celda);
                        cell.setCellStyle(cellStyleDate);
                    } else if (celda instanceof Number) {
                        cell.setCellValue(((Number) celda).doubleValue());
                        cell.setCellStyle(cellStyleNumero);
                    } else if (celda instanceof Object) {
                        cell.setCellValue(celda.toString());
                        cell.setCellStyle(cellStyle);
                    }
                }
            }
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            wb.write(baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_GENERACION, e);
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                throw new RuntimeException(ERROR_GENERACION, e);
            }
        }
    }

    /**
     * La hoja tiene caracteres extranos que se ven como espacios pero se deben remover con el valor hexa del caracter
     * 
     * @param valorCelda
     * @return
     */
    private Object procesarCadena(String valorCelda) {
        valorCelda = valorCelda.replace(String.valueOf((char) 0xa0), StringUtils.EMPTY).trim();
        if (StringUtils.isBlank(valorCelda))
            return null;
        return valorCelda;
    }

}
