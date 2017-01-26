package co.com.datatools.util.jsf;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <p>
 * Permite setear en tiempo de ejecucion la propiedad de <code>org.apache.el.parser.COERCE_TO_ZERO </code> para indicarle al EL Parser que no
 * convierta numeros y String a Ceros o cadenas vacias
 * </p>
 * <a href="http://stackoverflow.com/questions/5225013/coerce-to-zero-at-runtime">
 * http://stackoverflow.com/questions/5225013/coerce-to-zero-at-runtime</a>
 * 
 * @author felipe.martinez
 */
public class NoCoerceELParser implements ServletContextListener {

    private static final String PROP_COERCE_TO_ZERO = "org.apache.el.parser.COERCE_TO_ZERO";
    private final static Logger logger = Logger.getLogger(NoCoerceELParser.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.setProperty(PROP_COERCE_TO_ZERO, "false");
        logger.info("Propiedad Global [" + PROP_COERCE_TO_ZERO + "=false]");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}