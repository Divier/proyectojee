package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.IRFachadaRecaudo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogNotificacionRechazosRecaudo;
import co.com.datatools.util.date.UtilFecha;

public class EnvioRechazosRecaudo extends JobQuartz {

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.GENERACION_REPORTE_RECEPCION_DATOS_RECAUDO);
    private final static Logger logger2 = Logger.getLogger(EnvioRechazosRecaudo.class.getName());

    private IRFachadaRecaudo iRFachadaRecaudo = BeanLocatorC2.locate(IRFachadaRecaudo.class,
            BeanLocatorC2.Beans.IRFachadaRecaudo.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public EnvioRechazosRecaudo() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger2.debug("EnvioRechazosRecaudo.execute()");
        LoginContext loginContext = null;
        try {
            // Autenticacion
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            // Datos de usuario y organismo
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            Integer totalRechazos = iRFachadaRecaudo.enviarRechazosRecaudos(organismoTransitoDTO.getCodigoOrganismo());

            LogNotificacionRechazosRecaudo logNotifRechRec = new LogNotificacionRechazosRecaudo();
            logNotifRechRec.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logNotifRechRec.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            logNotifRechRec.setTotalRecaudosNoRecibidos(totalRechazos);

            // Loguear info
            logger.escribir(usuarioPersonaDTO.getLogin(), logNotifRechRec);
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