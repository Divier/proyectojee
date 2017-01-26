package co.com.datatools.c2.reporte.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.reporte.data.AtributoEncabezado;
import co.com.datatools.c2.reporte.data.PlantillaReporte;
import co.com.datatools.c2.reporte.util.ConstantesReporte;

public class TxtReportHandler extends AbstractReportHandler {

    private File tempFile;
    private PrintWriter out;
    private static final String SEPARATOR = "|";

    public TxtReportHandler(String formato, String ruta, PlantillaReporte plantillaReporte) {
        super(formato, ruta, plantillaReporte);
        try {
            if (StringUtils.isNotBlank(ruta)) {
                tempFile = new File(ruta);
            } else {
                tempFile = Files.createTempFile("", "." + formato.toString()).toFile();
            }
            out = new PrintWriter(new BufferedWriter(new FileWriter(tempFile)));
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void processHeader() {
        for (AtributoEncabezado atributo : plantillaReporte.getEncabezado().getAtributos()) {
            if (atributo.getVariable() != null) {
                out.println(atributo.getVariable().toString());
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
                        sb.append("\t" + subList.get(0).toString() + " : " + subList.get(1).toString());
                    }
                    out.println(sb.toString());
                }
            } else {
                out.println(atributo.getContenido());
            }
        }

        for (AtributoEncabezado atributo : plantillaReporte.getCabecera().getCabeceras()) {
            if (atributo != null) {
                out.print(atributo.getContenido() + SEPARATOR);
            } else {
                out.print(SEPARATOR);
            }
        }
        out.println();
    }

    @Override
    public void processLine(Collection<?> registro) {
        for (@SuppressWarnings("rawtypes")
        Iterator iterator = registro.iterator(); iterator.hasNext();) {
            final Object next = iterator.next();
            if (next != null)
                out.print(next.toString());
            if (iterator.hasNext())
                out.print(SEPARATOR);
        }
        out.println();
    }

    @Override
    public byte[] getBytes() {
        try {
            out.close();
            return Files.readAllBytes(tempFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("No debe suceder", e);
        }
    }

    @Override
    public File getFile() {
        out.close();
        return tempFile;
    }

}
