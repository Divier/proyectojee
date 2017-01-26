package co.com.datatools.c2.reporte.handler;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.data.AtributoEncabezado;
import co.com.datatools.c2.reporte.data.Encabezado;
import co.com.datatools.c2.reporte.data.PlantillaReporte;
import co.com.datatools.c2.reporte.util.ConstantesReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;

public abstract class AbstractReportHandler {

    protected String formato;
    protected String ruta;
    protected PlantillaReporte plantillaReporte;
    protected ContenidoReporte contenido;

    public AbstractReportHandler(String formato, String ruta, PlantillaReporte plantillaReporte) {
        this.formato = formato;
        this.ruta = ruta;
        this.plantillaReporte = plantillaReporte;
    }

    public abstract void processHeader();

    public abstract void processLine(Collection<?> registro);

    public abstract byte[] getBytes();

    public abstract File getFile();

    // public abstract void processSpecialHeader();

    /**
     * Procesa el contenido del encabezado del reporte
     * 
     * @param pcontenido
     *            ContenidoReporte con información de encabezado
     */
    public void processHeaderFields(ContenidoReporte pcontenido) {
        this.contenido = pcontenido;
        String patternVariable = "([#][{]{1}[\\w]*[}]{1})";
        Pattern pattern = Pattern.compile(patternVariable);
        Encabezado encabezado = plantillaReporte.getEncabezado();
        int posParametroEncabezado = 0;
        for (AtributoEncabezado atributo : encabezado.getAtributos()) {
            Matcher match = pattern.matcher(atributo.getContenido());
            while (match.find()) {
                String variable = match.group();
                variable = variable.replace("#{", "");
                variable = variable.replace("}", "").toLowerCase();
                Date date = new Date();
                switch (variable) {
                case "fecha":
                    SimpleDateFormat sdFecha = new SimpleDateFormat(ConstantesReporte.getFormatoFecha());
                    atributo.setContenido(atributo.getContenido().replace(match.group(), sdFecha.format(date)));
                    break;
                case "hora":
                    SimpleDateFormat sdHora = new SimpleDateFormat(ConstantesReporte.getFormatoHora());
                    atributo.setContenido(atributo.getContenido().replace(match.group(), sdHora.format(date)));
                    break;
                case "nombre_reporte":
                    atributo.setContenido(plantillaReporte.getCabecera().getNombre());
                    break;
                case "numero_registros":
                    atributo.setContenido(atributo.getContenido().replace(match.group(),
                            String.valueOf(contenido.getContenido().size())));
                    break;
                default:
                    break;
                }
            }

            for (EnumEncabezadoEspecial var : EnumEncabezadoEspecial.values()) {
                // hace match con var
                String patternVar = "([$][{]{1}[" + var.toString() + "]*[}]{1})";
                Pattern patternEnum = Pattern.compile(patternVar);
                if (patternEnum.matcher(atributo.getContenido()).find()) {
                    atributo.setVariable(var);
                    atributo.setMultilinea(processSpecialHeader(var));
                    atributo.setContenido(null);
                }
            }

            if (!contenido.getParametrosEncabezado().isEmpty()
                    && contenido.getParametrosEncabezado().containsKey(posParametroEncabezado)) {
                MessageFormat form = new MessageFormat(atributo.getContenido());
                atributo.setContenido(form.format(contenido.getParametrosEncabezado().get(posParametroEncabezado)));
            }
            posParametroEncabezado++;
        }
    }

    /**
     * Procesa los encabezados que tengan tratamiento especial
     * 
     * @param var
     *            Tipo de encabezado especial
     * @return List, convierte contenido en un List
     */
    private List processSpecialHeader(EnumEncabezadoEspecial var) {
        List list = new ArrayList<>();
        Object object = contenido.getVariablesEncabezado().get(var);
        switch (var) {
        case filtros:
            list = (List) object;
            break;

        default:
            break;
        }
        return list;
    }
}
