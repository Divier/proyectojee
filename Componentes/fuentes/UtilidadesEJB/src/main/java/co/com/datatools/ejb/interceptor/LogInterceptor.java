package co.com.datatools.ejb.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor EJB de proposito general para registrar el momento de ingreso y salida de la ejecucion de un metodo de un ejb
 * 
 * @author felipe.martinez
 */
public class LogInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        final long timeIni = System.currentTimeMillis();
        final String strMetodo = ctx.getMethod().toString();
        final String idMetodo = "" + Math.abs((long) strMetodo.hashCode());
        logger.trace("-- INI Mtd:{} -- {}", "" + idMetodo, strMetodo);
        try {
            return ctx.proceed();
        } finally {
            final long timeFin = System.currentTimeMillis();
            logger.trace("-- FIN Mtd:{} -- {} - Tiempo:{}s", "" + idMetodo, strMetodo, (timeFin - timeIni) / 1000);
        }
    }
}
