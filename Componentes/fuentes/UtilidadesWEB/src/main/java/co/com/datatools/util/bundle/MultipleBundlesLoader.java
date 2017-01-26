package co.com.datatools.util.bundle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.Set;
import java.util.regex.Pattern;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;

/**
 * Permite agrupar multiples archivos de propiedades con mensajes internacionalizas para un locale especifico en bundles de mensajes
 * 
 * @author felipe.martinez
 */
public class MultipleBundlesLoader {

    private final static Logger logger = LoggerFactory.getLogger(MultipleBundlesLoader.class);

    private Map<Locale, ResourceBundle> resources = new HashMap<Locale, ResourceBundle>(20);

    private final MultipleBundlesControl bundleControl;

    public MultipleBundlesLoader(String pathBundles) {
        bundleControl = new MultipleBundlesControl(pathBundles);
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        assert (null != locale);
        ResourceBundle resourceBundle = resources.get(locale);
        if (resourceBundle == null) {
            resourceBundle = loadBundle(locale);

            logger.info("ResourceBundle de negocio para Locale -{}- fue cargado", locale);
            logger.trace("Locale -{}- contiene las llaves {}", locale, resourceBundle.keySet());
            resources.put(locale, resourceBundle);

        }
        return resourceBundle;
    }

    private ResourceBundle loadBundle(Locale locale) {
        return ResourceBundle.getBundle("BundleCollection", locale, bundleControl);
    }

    public void clearBundles() {
        resources.clear();
        ResourceBundle.clearCache();
    }
}

class MultipleBundlesControl extends Control {

    /**
     * Ubicacion en el claspath de los archivos de mensajes de error de negocio internacionalizados
     */
    private final String pathBundles;

    private final static Logger logger = LoggerFactory.getLogger(MultipleBundlesControl.class);

    public MultipleBundlesControl(String pathBundles) {
        this.pathBundles = pathBundles;
    }

    @Override
    public List<String> getFormats(String baseName) {
        if (baseName == null)
            throw new NullPointerException();
        return Arrays.asList("properties");
    }

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException {
        if (baseName == null || locale == null || format == null || loader == null)
            throw new IllegalArgumentException();

        ResourceBundle bundle = null;

        if (format.equals("properties")) {
            final String finalLocale = toBundleName("", locale);

            Reflections reflections = new Reflections(pathBundles, new ResourcesScanner());
            final Pattern pattern = Pattern.compile(".*" + finalLocale + "\\.properties");

            Set<String> resolved = reflections.getResources(new Predicate<String>() {
                public boolean apply(String input) {
                    if (!pattern.matcher(input).matches())
                        return false;
                    else if (!finalLocale.startsWith("_")) {
                        if (input.contains("_")) {
                            return false;
                        }
                    }
                    return true;
                }
            });
            logger.trace("Buscando Archivos de propiedades para -{}-, se encontraron {}", finalLocale, resolved);

            Properties propGlobal = new Properties();
            for (String string : resolved) {
                InputStream resourceAsStream = ClasspathHelper.contextClassLoader().getResourceAsStream(string);
                propGlobal.load(resourceAsStream);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            propGlobal.store(baos, "");
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            bundle = new PropertyResourceBundle(bais);
        }
        return bundle;
    }

}