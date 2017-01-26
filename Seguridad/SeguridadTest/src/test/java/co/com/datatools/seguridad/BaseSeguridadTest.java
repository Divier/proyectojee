package co.com.datatools.seguridad;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;

import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.jdbc.ScriptRunner;

public abstract class BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(BaseSeguridadTest.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    private static boolean bdIniciada = false;
    /**
     * Listado de scripts q inicializan la bd en cada prueba
     */
    private static final String[] scriptsInit = { "scripts/base/00_seguridad.sql",//
            "scripts/base/h2/01_seguridad.sql",//
            "scripts/base/h2/02_seguridad.sql",//
            "scripts/base/h2/03_seguridad.sql",//
            "scripts/base/h2/04_seguridad.sql",//
            "scripts/datos/app/seg/comun/00_datos_seguridad.sql",//
            "scripts/datos/app/seg/comun/01_datos_roles.sql",//
            "scripts/datos/app/seg/comun/03_datos_usuario_admin.sql",//
            "scripts/pruebas/01_datos_pruebas_recursos.sql",//
            "scripts/pruebas/02_datos_pruebas_usuarios.sql",//
            "scripts/pruebas/03_datos_pruebas_roles.sql",//
            "scripts/pruebas/04_datos_pruebas_operaciones.sql",//
            "scripts/pruebas/05_datos_pruebas_menu.sql",//
            "scripts/pruebas/06_datos_pruebas_autenticacion.sql",//
            "scripts/pruebas/07_datos_pruebas_parametros.sql",//
            "scripts/pruebas/08_datos_pruebas_grupos.sql"//
    //
    };

    @Deployment
    public static Archive<?> createDeployment() {

        final String versionTest = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve("co.com.datatools.seguridad:SeguridadEJB").withoutTransitivity().asSingleResolvedArtifact()
                .getResolvedVersion();

        final File mavenEar = Maven.resolver().offline()
                .resolve("co.com.datatools.seguridad:SeguridadEAR:ear:" + versionTest).withoutTransitivity()
                .asSingleFile();

        final EnterpriseArchive archivo = ShrinkWrap.createFromZipFile(EnterpriseArchive.class, mavenEar);
        // Jar con Clases de prueba
        JavaArchive test = ShrinkWrap.create(JavaArchive.class, "SeguridadTest.jar")
                .addPackages(true, "co/com/datatools/seguridad").addAsResource(EmptyAsset.INSTANCE, "beans.xml");
        for (String script : scriptsInit) {
            test.addAsResource(script);
        }
        logger.info(test.toString(true));
        archivo.addAsLibraries(test);
        logger.info(archivo.toString(true));
        return archivo;
    }

    @Before
    public void crearBaseDatos() {
        if (bdIniciada) {
            return;
        }
        logger.debug("BaseSeguridadTest::crearBaseDatos");
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        final StringWriter writerInfo = new StringWriter();
        final StringWriter writerError = new StringWriter();
        try {
            Connection conn = cp.getConnection();
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.setLogWriter(new PrintWriter(writerInfo));
            runner.setErrorLogWriter(new PrintWriter(writerError));
            URL resource = null;
            for (String script : scriptsInit) {
                resource = Thread.currentThread().getContextClassLoader().getResource(script);
                runner.runScript(new InputStreamReader(resource.openStream()));
            }
            bdIniciada = true;
        } catch (SQLException | IOException e) {
            logger.error("\n" + writerError.toString());
            throw new RuntimeException(e);
        } finally {
            logger.debug("\n" + writerInfo.toString());
        }
    }

}
