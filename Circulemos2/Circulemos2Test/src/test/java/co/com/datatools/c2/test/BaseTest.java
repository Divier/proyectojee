package co.com.datatools.c2.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import co.com.datatools.util.jdbc.ScriptRunner;

@ArquillianSuiteDeployment
public abstract class BaseTest {

    private static final String CIRCULEMOS2WSCXF = "Circulemos2WSCXF";
    private static final String CIRCULEMOS2PWEB = "Circulemos2PWEB";

    enum UbicacionBD {
        // Ajustar las siguientes conexiones para ambiente local de desarrollo
        C2("jdbc:mysql://localhost:3306/circulemos2_ut", "com.mysql.jdbc.Driver", "root", "toor"), //
        SEGURIDAD_H2("jdbc:h2:file:~/h2/seguridad/db_ut;MODE=MySQL", "org.h2.Driver", "sa", "sa"), //
        SEGURIDAD("jdbc:mysql://localhost:3306/seguridad_ut", "com.mysql.jdbc.Driver", "root", "toor");

        public String ubicacion;
        public String controlador;
        public String usuario;
        public String contrasena;

        UbicacionBD(String ubicacion, String controlador, String usuario, String contrasena) {
            this.ubicacion = ubicacion;
            this.controlador = controlador;
            this.usuario = usuario;
            this.contrasena = contrasena;
        }
    }

    /**
     * Listado de scripts para crear la estructura base
     */
    private static final String[] scriptsInitC2 = { "scripts/base/c2/00_c2_mysql.sql", //
            "scripts/base/c2/01_c2_mysql_estructura.sql", //
            "scripts/base/c2/02_c2_mysql_catalogos.sql", //
            "scripts/pruebas/00_datos_pruebas_autogen.sql",//
    };

    private static final String[] scriptsInitSeguridad = { "scripts/base/seg/00_seg_mysql.sql", //
            "scripts/base/seg/01_seg_mysql_estructura.sql", //
            "scripts/base/seg/02_seg_mysql_catalogos.sql", //
    };

    private final static Logger logger = Logger.getLogger(BaseTest.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    protected static EntityManager em;

    protected static boolean bdIniciada = false;

    protected Connection conn = null;

    @Deployment
    public static Archive<?> createDeployment() {

        final String versionTest = Maven.resolver().offline().loadPomFromFile("pom.xml")
                .resolve("co.com.datatools.c2:CarteraEJB").withoutTransitivity().asSingleResolvedArtifact()
                .getResolvedVersion();
        final String versionReflection = Maven.resolver().offline().loadPomFromFile("pom.xml")
                .resolve("org.reflections:reflections").withoutTransitivity().asSingleResolvedArtifact()
                .getResolvedVersion();

        final File mavenEar = Maven.resolver().offline()
                .resolve("co.com.datatools.c2:Circulemo2EAR:ear:" + versionTest).withoutTransitivity().asSingleFile();

        final File[] mavenReflections = Maven.resolver().offline()
                .resolve("org.reflections:reflections:jar:" + versionReflection).withTransitivity().asFile();

        final EnterpriseArchive archivo = ShrinkWrap.createFromZipFile(EnterpriseArchive.class, mavenEar);
        archivo.delete(CIRCULEMOS2WSCXF + "-" + versionTest + ".war");
        archivo.delete(CIRCULEMOS2PWEB + "-" + versionTest + ".war");
        final Node node = archivo.get("META-INF/application.xml");

        try {
            final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(node.getAsset().openStream());
            final org.w3c.dom.Node aplicacion = doc.getElementsByTagName("application").item(0);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList) xPath.evaluate("/application/module/web/context-root",
                    doc.getDocumentElement(), XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); ++i) {
                final org.w3c.dom.Node item = nodes.item(i);
                final org.w3c.dom.Node firstChild = item.getFirstChild();
                if (firstChild.getNodeValue().equals("/" + CIRCULEMOS2WSCXF)
                        || firstChild.getNodeValue().equals("/" + CIRCULEMOS2PWEB)) {
                    aplicacion.removeChild(item.getParentNode().getParentNode());
                }
            }
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            Asset asset = new StringAsset(writer.toString());
            archivo.delete(node.getPath());
            archivo.setApplicationXML(asset);
        } catch (Exception e) {
            throw new RuntimeException("No es posible leer el application.xml de Circulemo2EAR", e);
        }

        // Jar con Clases de prueba
        JavaArchive test = ShrinkWrap
                .create(JavaArchive.class, "CirculemosTest.jar")
                .addPackages(true, "co/com/datatools/c2/test")
                .addAsResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("co/com/datatools/c2/bundles/scriptCreacionUnitTest.properties",
                        "scriptCreacionUnitTest.properties").addAsResource("roles.properties")
                .addAsResource("users.properties");

        // -- Construir estructura base de la bd h2
        // crearBD(UbicacionBD.SEGURIDAD, scriptsInitSeguridad);
        crearBD(UbicacionBD.C2, scriptsInitC2);

        // -- Agrega los scripts al artefacto de pruebas
        List<String> scripts = scriptsDisponibles();
        for (String script : scripts) {
            test.addAsResource(script);
        }
        // -- Agrega los scripts al artefacto de pruebas

        // -- Agrega los archivos al artefacto de pruebas
        List<String> archivos = archivosDisponibles();
        for (String archivoPrueba : archivos) {
            test.addAsResource(archivoPrueba);
        }
        // -- Agrega los archivos al artefacto de pruebas

        logger.info(test.toString(true));
        archivo.addAsLibraries(test);
        archivo.addAsLibraries(mavenReflections);
        // archivo.addAsLibraries(mavenJmockit);
        logger.info(archivo.toString(true));
        return archivo;
    }

    private static List<String> scriptsDisponibles() {
        List<String> scripts = new ArrayList<>(20);
        scripts.addAll(new Reflections("scripts", new ResourcesScanner()).getResources(Pattern.compile(".*\\.sql")));
        Collections.sort(scripts);
        return scripts;
    }

    private static List<String> archivosDisponibles() {
        List<String> archivos = new ArrayList<>(20);
        archivos.addAll(new Reflections("archivos", new ResourcesScanner()).getResources(Pattern.compile(".*\\.txt")));
        archivos.addAll(new Reflections("archivos", new ResourcesScanner()).getResources(Pattern.compile(".*\\.xml")));
        Collections.sort(archivos);
        return archivos;
    }

    public void crearBaseDatos() {
        if (bdIniciada) {
            return;
        }
        List<String> scripts = scriptsDisponibles();
        logger.debug("BaseTest::crearBaseDatos");
        String[] array = new String[scripts.size()];
        scripts.toArray(array);
        // ejecutarScript(array);

        bdIniciada = true;
    }

    /**
     * Permite ejecutar un script de base de datos definido en la propiedad 'scriptCreacionUnitTest'.
     * 
     * @author luis.forero
     * @param llaveConsulta
     *            :llave de la consulta a cargar
     */
    protected void ejecutarScriptsBD(String... nombreConsulta) {
        final StringWriter writerInfo = new StringWriter();
        final StringWriter writerError = new StringWriter();
        try {
            if (conn == null) {
                Session session = em.unwrap(Session.class);
                SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
                ConnectionProvider cp = sfi.getConnectionProvider();
                conn = cp.getConnection();
            }
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.setLogWriter(new PrintWriter(writerInfo));
            runner.setErrorLogWriter(new PrintWriter(writerError));
            final ResourceBundle bundle = ResourceBundle.getBundle("scriptCreacionUnitTest", Locale.ENGLISH);
            for (String scriptKey : nombreConsulta) {
                String consulta = bundle.getString(scriptKey);
                InputStream in = new ByteArrayInputStream(consulta.getBytes());
                runner.runScript(new InputStreamReader(in));
            }
        } catch (PersistenceException | SQLException | IOException e) {
            logger.error("\n" + writerError.toString());
            throw new RuntimeException(e);
        } finally {
            logger.debug("\n" + writerInfo.toString());
        }
    }

    protected void ejecutarScriptsSQL(String... scripts) {
        final StringWriter writerInfo = new StringWriter();
        final StringWriter writerError = new StringWriter();
        try {
            if (conn == null) {
                Session session = em.unwrap(Session.class);
                SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
                ConnectionProvider cp = sfi.getConnectionProvider();
                conn = cp.getConnection();
            }
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.setLogWriter(new PrintWriter(writerInfo));
            runner.setErrorLogWriter(new PrintWriter(writerError));
            URL resource = null;
            for (String script : scripts) {
                // logger.infov("******* Ejecutando Script: {0} *******", script);
                resource = Thread.currentThread().getContextClassLoader().getResource(script);
                runner.runScript(new InputStreamReader(resource.openStream()));
            }
        } catch (PersistenceException | SQLException | IOException e) {
            logger.error("\n" + writerError.toString());
            throw new RuntimeException(e);
        } finally {
            logger.debug("\n" + writerInfo.toString());
        }
    }

    /**
     * Crea la base de datos h2 con la estructura minima para q c2 pueda ser desplegada
     * 
     * @param scripts
     *            ruta interna de los scripts a ejecutar
     */
    private static final void crearBD(UbicacionBD bd, String... scripts) {
        final StringWriter writerInfo = new StringWriter();
        final StringWriter writerError = new StringWriter();
        Connection manualConn = null;
        try {
            Class.forName(bd.controlador);
            // ----
            // La URL de conexion debe ser la misma definida en el datasource del standalone-unit-test-c2.xml
            // ----
            manualConn = DriverManager.getConnection(bd.ubicacion, bd.usuario, bd.contrasena);

            ScriptRunner runner = new ScriptRunner(manualConn, false, true);
            runner.setLogWriter(new PrintWriter(writerInfo));
            runner.setErrorLogWriter(new PrintWriter(writerError));
            URL resource = null;
            for (String script : scripts) {
                logger.infov("******* {0} -> Ejecutando Script: {1} *******", bd, script);
                resource = Thread.currentThread().getContextClassLoader().getResource(script);
                runner.runScript(new InputStreamReader(resource.openStream()));
            }

        } catch (SQLException | IOException | ClassNotFoundException e) {
            logger.info("\n" + writerError.toString());
            throw new RuntimeException(e);
        } finally {
            logger.debug("\n" + writerInfo.toString());
            if (manualConn != null)
                try {
                    manualConn.close();
                } catch (SQLException e) {
                    logger.error("Problema cerrando conexion", e);
                }
            bdIniciada = true;
        }
    }

    /**
     * Main de pruebas para validar q la base de datos se puede crear satisfactoriamente con los scripts base
     * 
     * @param args
     *            N/A
     */
    public static void main(String[] args) {
        crearBD(UbicacionBD.C2, scriptsInitC2);
        crearBD(UbicacionBD.SEGURIDAD, scriptsInitSeguridad);
    }
}