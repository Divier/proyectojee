package co.com.datatools.c2.reporte.handler;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.reporte.data.AtributoEncabezado;
import co.com.datatools.c2.reporte.data.PlantillaReporte;
import co.com.datatools.c2.reporte.util.ConstantesReporte;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportHandler extends AbstractReportHandler {

    private File tempFile;
    private Document document;
    private ByteArrayOutputStream baos;
    private Table table;
    private int rowNum;
    private Font font;

    public PdfReportHandler(String formato, String ruta, PlantillaReporte plantillaReporte) {
        super(formato, ruta, plantillaReporte);
        baos = new ByteArrayOutputStream();
        document = new Document();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void processHeader() {
        int cantidadColumnas = plantillaReporte.getCabecera().getCabeceras().size();
        try {
            if (!document.isOpen()) {
                if (cantidadColumnas > 6) {
                    document.setPageSize(PageSize.LETTER.rotate());
                }
                document.setMargins(ConstantesReporte.getMargenIzquierdo() * 28,
                        ConstantesReporte.getMargenDerecho() * 28, ConstantesReporte.getMargenSuperior() * 28,
                        ConstantesReporte.getMargenInferior() * 28);
                PdfWriter.getInstance(document, baos);
                document.open();
            }
            Font fontHeader = FontFactory.getFont(ConstantesReporte.getFuenteTipo(),
                    ConstantesReporte.getFuenteTamanioHeader());
            fontHeader.setStyle("bold");
            for (AtributoEncabezado atributo : plantillaReporte.getEncabezado().getAtributos()) {
                if (atributo.getVariable() != null) {
                    document.add(new Paragraph(atributo.getVariable().toString() + ":", fontHeader));
                    List list = (List) contenido.getVariablesEncabezado().get(atributo.getVariable());
                    SimpleDateFormat sdf = new SimpleDateFormat(ConstantesReporte.getFormatoFecha() + " "
                            + ConstantesReporte.getFormatoHora());
                    for (Object o : list) {
                        StringBuilder sb = new StringBuilder();
                        List subList = (List) o;
                        if (subList.size() == 2) {
                            if (subList.get(1) instanceof Date) {
                                subList.set(1, sdf.format(subList.get(1)));
                            }
                            sb.append("\t\t\t\t\t\t\t\t\t\t" + subList.get(0).toString() + " : " + subList.get(1));
                        }
                        document.add(new Paragraph(sb.toString(), fontHeader));
                    }
                } else {
                    document.add(new Paragraph(atributo.getContenido(), fontHeader));
                }
            }
            table = new Table(cantidadColumnas);
            table.setAlignment(Element.ALIGN_LEFT);
            table.setAutoFillEmptyCells(true);
            table.setPadding(2);
            font = FontFactory.getFont(ConstantesReporte.getFuenteTipo(), ConstantesReporte.getFuenteTamanio());
            Cell cell;
            int cellNum = 0;
            float[] widths = new float[table.getColumns()];
            for (int i = 0; i < table.getColumns(); i++) {
                widths[i] = 1f;
            }
            table.setWidths(widths);
            table.setWidth(100);
            for (AtributoEncabezado atributo : plantillaReporte.getCabecera().getCabeceras()) {
                cell = new Cell(new Paragraph(atributo.getContenido(), fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(cell, new Point(rowNum, cellNum++));
            }
            rowNum++;
        } catch (DocumentException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

    @Override
    public void processLine(Collection<?> registro) {
        try {
            Cell cell;
            int cantRegistros = registro.size();
            int cantColumnas = table.getColumns();
            if (cantRegistros > cantColumnas) {
                table.addColumns(cantRegistros - cantColumnas);
            }
            int cellNum = 0;
            float[] widths = new float[table.getColumns()];
            for (int i = 0; i < table.getColumns(); i++) {
                widths[i] = 1f;
            }
            table.setWidths(widths);
            for (@SuppressWarnings("rawtypes")
            Iterator iterator = registro.iterator(); iterator.hasNext();) {
                final Object next = iterator.next();
                if (next == null) {
                    cell = new Cell(new Paragraph(null, font));
                } else {
                    cell = new Cell(new Paragraph(next.toString(), font));
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell, new Point(rowNum, cellNum++));
            }
            rowNum++;
        } catch (DocumentException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

    @Override
    public byte[] getBytes() {
        try {
            document.add(table);
            document.close();
            baos.flush();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
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
        FileOutputStream fos = null;
        try {
            if (StringUtils.isNotBlank(ruta)) {
                tempFile = new File(ruta);
            } else {
                tempFile = Files.createTempFile("", "." + formato.toString()).toFile();
            }
            document.add(table);
            document.close();
            fos = new FileOutputStream(tempFile);
            fos.write(baos.toByteArray());
            baos.flush();
            return tempFile;
        } catch (IOException | DocumentException e) {
            throw new RuntimeException("No debe suceder", e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                baos.close();
            } catch (IOException e) {
                throw new RuntimeException("No debe suceder", e);
            }
        }
    }

}
