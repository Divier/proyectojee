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
import co.com.datatools.c2.negocio.interfaces.IRProcesaComparendo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogNotificacionInconsistenciasComparendos;
import co.com.datatools.util.date.UtilFecha;

public class EnvioInconsistenciasComparendos extends JobQuartz {

    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.NOTIFI_INCONSISTENCIAS_COMPARENDOS);
    private final static Logger logger = Logger.getLogger(SolicitudComparendosTerceros.class.getName());

    private IRProcesaComparendo iRProcesaComparendo = BeanLocatorC2.locate(IRProcesaComparendo.class,
            BeanLocatorC2.Beans.IRProcesaComparendo.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public EnvioInconsistenciasComparendos() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("EnvioInconsistenciasComparendos::execute");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            int totalInconsistencias = iRProcesaComparendo.enviarInconsistenciasComparendos(organismoTransitoDTO);
            LogNotificacionInconsistenciasComparendos logNotifInconCompa = new LogNotificacionInconsistenciasComparendos();
            logNotifInconCompa.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logNotifInconCompa.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            logNotifInconCompa.setTotalInconsistencias(totalInconsistencias);
            log.escribir(usuarioPersonaDTO.getLogin(), logNotifInconCompa);
        } catch (LoginException e) {
            logger.error(e);
        } finally {
            if (loginContext != null) {
                try {
                    loginContext.logout();
                } catch (LoginException e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        // TODO Auto-generated method stub
    }
}