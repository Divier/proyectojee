package co.com.datatools.seguridad.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.Set;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;

import com.google.common.base.Predicate;

/**
 * Clase encargada de buscar los archivos de propiedades de mensajes internacionazados de negocio y cargarlos en un solo bundle
 * 
 * @author felipe.martinez
 * 
 */
public class SeguridadMultiplePropertiesControl extends Control {

    /**
     * Ubicacion en el claspath de los archivos de mensajes de error de negocio internacionalizados
     */
    private static final String PATH_BUNDLES_NEGOCIO = "co.com.datatools.seguridad.bundle.ejb";

    private final static Logger logger = Logger.getLogger(SeguridadMultiplePropertiesControl.class.getName());

    public List<String> getFormats(String baseName) {
        if (baseName == null)
            throw new NullPointerException();
        return Arrays.asList("properties");
    }

    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException {
        if (baseName == null || locale == null || format == null || loader == null)
            throw new IllegalArgumentException();

        ResourceBundle bundle = null;

        if (format.equals("properties")) {
            final String finalLocale = toBundleName("Mensajes", locale);

            Reflections reflections = new Reflections(PATH_BUNDLES_NEGOCIO, new ResourcesScanner());
            final Pattern pattern = Pattern.compile(".*" + finalLocale + "\\.properties");

            Set<String> resolved = reflections.getResources(new Predicate<String>() {
                public boolean apply(String input) {
                    if (!pattern.matcher(input).matches())
                        return false;
                    return true;
                }
            });
            logger.debugv("Buscando Archivos de propiedades para {0}, se encontraron {1}", finalLocale, resolved);

            Properties propGlobal = new Properties();
            for (String string : resolved) {
                Properties temp = new Properties();
                InputStream resourceAsStream = ClasspathHelper.contextClassLoader().getResourceAsStream(string);
                temp.load(resourceAsStream);
                propGlobal.putAll(temp);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            propGlobal.store(baos, "");
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            bundle = new PropertyResourceBundle(bais);
        }
        return bundle;
    }

}