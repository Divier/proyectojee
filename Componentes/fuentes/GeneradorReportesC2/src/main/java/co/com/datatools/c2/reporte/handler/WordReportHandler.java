package co.com.datatools.c2.reporte.handler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import co.com.datatools.c2.reporte.data.AtributoEncabezado;
import co.com.datatools.c2.reporte.data.PlantillaReporte;
import co.com.datatools.c2.reporte.util.ConstantesReporte;

public class WordReportHandler extends AbstractReportHandler {

    private File file;
    private XWPFDocument document;
    private XWPFTable table;
    private int rowNum;
    private XWPFRun tmpRun;

    public WordReportHandler(String formato, String ruta, PlantillaReporte plantillaReporte) {
        super(formato, ruta, plantillaReporte);
        document = new XWPFDocument();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void processHeader() {
        int cantidadColumnas = plantillaReporte.getCabecera().getCabeceras().size();
        XWPFParagraph tmpParagraph;
        XWPFRun tmpRunEncabezado;
        for (AtributoEncabezado atributo : plantillaReporte.getEncabezado().getAtributos()) {
            tmpParagraph = document.createParagraph();
            tmpParagraph.setAlignment(ParagraphAlignment.LEFT);
            tmpRunEncabezado = tmpParagraph.createRun();
            tmpRunEncabezado.setBold(true);
            tmpRunEncabezado.setFontSize(ConstantesReporte.getFuenteTamanioHeader());
            tmpRunEncabezado.setFontFamily(ConstantesReporte.getFuenteTipo());
            if (atributo.getVariable() != null) {
                tmpRunEncabezado.setText(atributo.getVariable().toString() + " :");
                List list = (List) contenido.getVariablesEncabezado().get(atributo.getVariable());
                SimpleDateFormat sdf = new SimpleDateFormat(ConstantesReporte.getFormatoFecha() + " "
                        + ConstantesReporte.getFormatoHora());
                for (Object o : list) {
                    StringBuilder sb = new StringBuilder();
                    List subList = (List) o;
                    tmpParagraph = document.createParagraph();
                    tmpParagraph.setAlignment(ParagraphAlignment.LEFT);
                    tmpRunEncabezado = tmpParagraph.createRun();
                    tmpRunEncabezado.setBold(true);
                    tmpRunEncabezado.setFontSize(ConstantesReporte.getFuenteTamanioHeader());
                    tmpRunEncabezado.setFontFamily(ConstantesReporte.getFuenteTipo());
                    if (subList.size() == 2) {
                        if (subList.get(1) instanceof Date) {
                            subList.set(1, sdf.format(subList.get(1)));
                        }
                        sb.append("\t\t\t\t\t\t\t\t\t\t" + subList.get(0).toString() + " : "
                                + subList.get(1).toString());
                        tmpRunEncabezado.setText(sb.toString());
                    }
                }
            } else {
                tmpRunEncabezado.setText(atributo.getContenido());
            }

        }
        // Procesamiento de header especial
        for (AtributoEncabezado atributo : plantillaReporte.getEncabezado().getAtributos()) {
            if (atributo.getVariable() != null) {
                tmpParagraph = document.createParagraph();
                tmpParagraph.setAlignment(ParagraphAlignment.LEFT);
                tmpRunEncabezado = tmpParagraph.createRun();
                tmpRunEncabezado.setBold(true);
                tmpRunEncabezado.setFontSize(ConstantesReporte.getFuenteTamanioHeader());
                tmpRunEncabezado.setFontFamily(ConstantesReporte.getFuenteTipo());

            }
        }
        if (cantidadColumnas > 6) {
            CTSectPr sectPr = document.getDocument().getBody().getSectPr();
            if (sectPr == null)
                sectPr = document.getDocument().getBody().addNewSectPr();
            CTPageSz pageSize = sectPr.getPgSz();
            if (pageSize == null)
                pageSize = sectPr.addNewPgSz();
            pageSize.setOrient(STPageOrientation.LANDSCAPE);
            pageSize.setW(BigInteger.valueOf(15840));
            pageSize.setH(BigInteger.valueOf(12240));
            CTPageMar margen = sectPr.addNewPgMar();
            margen.setLeft(BigInteger.valueOf(ConstantesReporte.getMargenDerecho() * 575));
            margen.setRight(BigInteger.valueOf(ConstantesReporte.getMargenDerecho() * 575));
            margen.setTop(BigInteger.valueOf(ConstantesReporte.getMargenDerecho() * 575));
            margen.setBottom(BigInteger.valueOf(ConstantesReporte.getMargenDerecho() * 575));

        }
        table = document.createTable(1, cantidadColumnas);
        table.removeRow(0);
        if (table.getRow(rowNum) == null) {
            table.createRow();
        }
        XWPFTableRow row = table.getRow(rowNum++);
        int cellNum = 0;
        for (AtributoEncabezado atributo : plantillaReporte.getCabecera().getCabeceras()) {
            if (row.getCell(cellNum) == null) {
                row.createCell();
            }
            XWPFTableCell cell = row.getCell(cellNum);
            tmpParagraph = cell.getParagraphs().get(0);
            tmpParagraph.setAlignment(ParagraphAlignment.CENTER);
            tmpParagraph.setSpacingAfter(100);
            tmpParagraph.setSpacingAfterLines(10);
            cell.setColor("CCCCCC");
            tmpRun = tmpParagraph.createRun();
            tmpRun.setBold(true);
            tmpRun.setFontSize(ConstantesReporte.getFuenteTamanioHeader());
            tmpRun.setFontFamily(ConstantesReporte.getFuenteTipo());
            tmpRun.setText(atributo.getContenido());
            cellNum++;
        }
    }

    @Override
    public void processLine(Collection<?> registro) {
        XWPFParagraph tmpParagraph;
        if (table.getRow(rowNum) == null) {
            table.createRow();
        }
        XWPFTableRow row = table.getRow(rowNum);
        int cellNum = 0;
        for (@SuppressWarnings("rawtypes")
        Iterator iterator = registro.iterator(); iterator.hasNext();) {
            final Object next = iterator.next();
            if (row.getCell(cellNum) == null) {
                row.createCell();
            }
            XWPFTableCell cell = row.getCell(cellNum);
            tmpParagraph = cell.getParagraphs().get(0);
            tmpParagraph.setAlignment(ParagraphAlignment.CENTER);
            tmpParagraph.setSpacingAfter(100);
            tmpParagraph.setSpacingAfterLines(10);
            tmpRun = tmpParagraph.createRun();
            tmpRun.setFontSize(ConstantesReporte.getFuenteTamanio());
            tmpRun.setFontFamily(ConstantesReporte.getFuenteTipo());
            if (next != null) {
                tmpRun.setText(next.toString());
            }
            cellNum++;
        }
        rowNum++;
    }

    @Override
    public byte[] getBytes() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            document.write(baos);
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
                file = new File(ruta);
            } else {
                file = Files.createTempFile("", "." + formato.toString()).toFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            document.write(out);
            out.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

}
