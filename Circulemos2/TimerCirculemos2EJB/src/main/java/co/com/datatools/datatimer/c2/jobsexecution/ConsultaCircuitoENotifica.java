package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogConsultaCircuitoENotifica;

public class ConsultaCircuitoENotifica extends JobQuartz {

    private final static Logger logger = Logger.getLogger(ConsultaCircuitoENotifica.class.getName());
    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.CONSULTA_CIRCUITO_E_NOTIFICA);

    private IRFachadaNotificacionTerceros iRFachadaNotifTerceros = BeanLocatorC2
            .locate(IRFachadaNotificacionTerceros.class, BeanLocatorC2.Beans.IRFachadaNotificacionTerceros.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public ConsultaCircuitoENotifica() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            LogConsultaCircuitoENotifica lgConsulCircuENoti = new LogConsultaCircuitoENotifica();
            Integer[] cantidad = iRFachadaNotifTerceros.consultarNotificaciones();
            lgConsulCircuENoti.setCantNoProcesados(cantidad[0]);
            lgConsulCircuENoti.setCantProcesados(cantidad[1]);
            lgConsulCircuENoti.setSolicitudes(cantidad[0] + cantidad[1]);
            log.escribir(usuarioPersonaDTO.getLogin(), lgConsulCircuENoti);
        } catch (LoginException e) {
            logger.error(e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }
}