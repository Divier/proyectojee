package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.negocio.fachada.IRFachadaImpugnacion;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;

public class CierrePruebasImpugnacion extends JobQuartz {

    private final static Logger logger2 = Logger.getLogger(CierrePruebasImpugnacion.class.getName());

    private IRFachadaImpugnacion iRFachadaImpugnacion = BeanLocatorC2.locate(IRFachadaImpugnacion.class,
            BeanLocatorC2.Beans.IRFachadaImpugnacion.toString());

    public CierrePruebasImpugnacion() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger2.debug("CierrePruebasImpugnacion::execute()");
        LoginContext loginContext = null;
        try {
            // Autenticacion
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            // Llamado al metodo de cerrar pruebas
            iRFachadaImpugnacion.cerrarPruebasJob();
        } catch (LoginException e) {
            logger2.error(e);
        } finally {
            if (loginContext != null) {
                try {
                    loginContext.logout();
                } catch (LoginException e) {
                    logger2.error(e);
                }
            }
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        // TODO Auto-generated method stub
    }
}