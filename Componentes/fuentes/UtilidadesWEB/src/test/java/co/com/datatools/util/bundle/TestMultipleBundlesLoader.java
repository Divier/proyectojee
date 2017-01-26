package co.com.datatools.util.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.util.ClasspathHelper;

public class TestMultipleBundlesLoader {
    static final String PATH_BUNDLES = "co.com.datatools.util.bundle";

    static final String BUNDLES_1_EN = "co/com/datatools/util/bundle/ruta1/Mensajes_en.properties";
    static final String BUNDLES_1_ES_PE = "co/com/datatools/util/bundle/ruta1/Mensajes_es_PE.properties";
    static final String OTRO_BUNDLE_1_ES_PE = "co/com/datatools/util/bundle/ruta1/OtrosMensajes_es_PE.properties";
    static final String BUNDLES_1_ES = "co/com/datatools/util/bundle/ruta1/Mensajes_es.properties";
    static final String BUNDLES_1 = "co/com/datatools/util/bundle/ruta1/Mensajes.properties";
    static final String BUNDLES_2_EN = "co/com/datatools/util/bundle/ruta2/Mensajes_en.properties";
    static final String BUNDLES_2_ES_PE = "co/com/datatools/util/bundle/ruta2/Mensajes_es_PE.properties";
    static final String BUNDLES_2_ES = "co/com/datatools/util/bundle/ruta2/Mensajes_es.properties";
    static final String BUNDLES_2 = "co/com/datatools/util/bundle/ruta2/Mensajes.properties";

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
    }

    @Test
    public void test1() {
        final MultipleBundlesLoader loader = new MultipleBundlesLoader(PATH_BUNDLES);
        final ResourceBundle bundle = loader.getResourceBundle(Locale.ENGLISH);
        Properties collection = new Properties();
        collection = loadResource(collection, BUNDLES_1, BUNDLES_1_EN, BUNDLES_2, BUNDLES_2_EN);
        assertEquals(convertResourceBundleToProperties(bundle), collection);
        assertEquals(10, bundle.keySet().size());
    }

    @Test
    public void test2() {
        final MultipleBundlesLoader loader = new MultipleBundlesLoader(PATH_BUNDLES);
        final ResourceBundle bundle = loader.getResourceBundle(Locale.forLanguageTag("es"));
        Properties collection = new Properties();
        collection = loadResource(collection, BUNDLES_1, BUNDLES_1_ES, BUNDLES_2, BUNDLES_2_ES);
        assertEquals(convertResourceBundleToProperties(bundle), collection);
        assertEquals(8, bundle.keySet().size());
    }

    @Test
    public void test3() {
        final MultipleBundlesLoader loader = new MultipleBundlesLoader(PATH_BUNDLES);
        final ResourceBundle bundle = loader.getResourceBundle(new Locale("es", "PE"));
        Properties collection = new Properties();
        collection = loadResource(collection, BUNDLES_1, BUNDLES_1_ES, BUNDLES_1_ES_PE, OTRO_BUNDLE_1_ES_PE, BUNDLES_2,
                BUNDLES_2_ES, BUNDLES_2_ES_PE);
        assertEquals(convertResourceBundleToProperties(bundle), collection);
        assertEquals(11, bundle.keySet().size());
    }

    static Properties loadResource(Properties base, String... names) {
        for (String name : names) {
            InputStream stream = ClasspathHelper.contextClassLoader().getResourceAsStream(name);
            try {
                base.load(stream);
                stream.close();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
        return base;
    }

    static Properties convertResourceBundleToProperties(ResourceBundle resource) {
        Properties properties = new Properties();

        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, resource.getString(key));
        }

        return properties;
    }
}
