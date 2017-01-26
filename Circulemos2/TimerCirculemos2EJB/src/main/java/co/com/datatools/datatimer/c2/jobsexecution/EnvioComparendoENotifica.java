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
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogNotificacionENotifica;

public class EnvioComparendoENotifica extends JobQuartz {

    private final static Logger logger2 = Logger.getLogger(EnvioComparendoENotifica.class.getName());
    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.NOTIFICACION_E_NOTIFICA);

    private IRFachadaComparendo iRFachadaComparendo = BeanLocatorC2.locate(IRFachadaComparendo.class,
            BeanLocatorC2.Beans.IRFachadaComparendo.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public EnvioComparendoENotifica() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            LogNotificacionENotifica logNotificacionENotifica = new LogNotificacionENotifica();
            Integer[] cantidad = iRFachadaComparendo.enviarNotificaciones(organismoTransitoDTO.getCodigoOrganismo());
            logNotificacionENotifica.setCantNoNotificados(cantidad[0]);
            logNotificacionENotifica.setCantNotificados(cantidad[1]);
            logNotificacionENotifica.setSolicitudesNotificacion(cantidad[0] + cantidad[1]);
            log.escribir(usuarioPersonaDTO.getLogin(), logNotificacionENotifica);

        } catch (LoginException e) {
            logger2.error(e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        // TODO Auto-generated method stub
    }
}