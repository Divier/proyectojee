package co.com.datatools.util.xss;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author luis.forero
 * 
 */
public class XSSValidator {

    private static final List<Pattern> lstValidaciones = new ArrayList<>();
    static {
        // Se cargan las expresiones sql
        for (int i = 0; i < ExpresionesRegulares.LIST_EXPRESIONES_INYECCION_SQL.length; i++) {
            Pattern pattern = Pattern.compile(ExpresionesRegulares.LIST_EXPRESIONES_INYECCION_SQL[i]);
            lstValidaciones.add(pattern);
        }
        // Se cargan las expresiones JavaScript
        for (int i = 0; i < ExpresionesRegulares.LIST_EXPRESIONES_INYECCION_JAVASCRIPT.length; i++) {
            Pattern pattern = Pattern.compile(ExpresionesRegulares.LIST_EXPRESIONES_INYECCION_JAVASCRIPT[i]);
            lstValidaciones.add(pattern);
        }
    }

    private static final Pattern EXP_NUMERICO = Pattern.compile("[0-9]+(\\.[0-9]+)?");

    /**
     * Determina si el valor es un intento de inyeccion
     * 
     * @param value
     *            valor a ser evaluado
     * @return true si el valor contiene una inyeccion, false de lo contrario
     * @author luis.forero(2015-10-06)
     */
    public static boolean isUnsafe(String value) {
        Matcher mtc = EXP_NUMERICO.matcher(value);
        if (!mtc.matches()) {
            for (Pattern pattern : lstValidaciones) {
                mtc = pattern.matcher(value);
                if (mtc.matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getSafeValue(String oldValue) {
        Matcher mtc = EXP_NUMERICO.matcher(oldValue);
        if (!mtc.matches()) {
            for (Pattern pattern : lstValidaciones) {
                Matcher matcher = pattern.matcher(oldValue);
                if (matcher.matches()) {
                    return matcher.replaceAll("");
                }
            }
        }
        return oldValue;
    }

    public static boolean tieneComillaSencilla(String value) {
        Matcher mtc = EXP_NUMERICO.matcher(value);
        if (!mtc.matches()) {
            Pattern pattern = Pattern.compile(ExpresionesRegulares.EXP_COMILLA_SENCILLAS);
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
