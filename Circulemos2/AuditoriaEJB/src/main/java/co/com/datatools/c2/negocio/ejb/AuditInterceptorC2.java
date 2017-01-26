package co.com.datatools.c2.negocio.ejb;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

import co.com.datatools.auditoria.interfaces.AuditInterceptor;

/**
 * @author luis.forero
 * 
 */
public class AuditInterceptorC2 {

    @EJB
    private ILAuditoriaC2 auditoria;

    private static final Logger logger = Logger.getLogger(AuditInterceptor.class.getName());

    @Resource
    private SessionContext ctxEjb;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        // logger.debug("PAckage:" + ctx.getMethod().getClass().getPackage().getName() + ", Metodo:"
        // + ctx.getMethod().getName());
        if (ctx.getMethod().getClass().getPackage().getName().equalsIgnoreCase("javax.persistence")) {
            // logger.debug("Ingreso intercept(InvocationContext ctx)");

            final String strMetodo = ctx.getMethod().toString();
            // logger.debugf("InvocationContext inicio Método:%s", strMetodo);
            try {

                String principalName = ctxEjb.getCallerPrincipal().getName();
                auditoria.reportarUsuarioAplicacion(principalName);

            } finally {
                // logger.debug("Salida intercept(InvocationContext ctx)");
            }
        }
        return ctx.proceed();
    }

}
