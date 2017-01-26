package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.RespuestaReplicarFinanciacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.IRFachadaFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogReplicarFinanciaciones;
import co.com.datatools.util.date.UtilFecha;

public class ReplicarFinanciaciones extends JobQuartz {

    private final static Logger logger = Logger.getLogger(ReplicarFinanciaciones.class.getName());

    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.REPLICAR_FINANCIACIONES);

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    private IRFachadaFinanciacion iRFachadaFinanciacion = BeanLocatorC2.locate(IRFachadaFinanciacion.class,
            BeanLocatorC2.Beans.IRFachadaFinanciacion.toString());

    public ReplicarFinanciaciones() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("ReplicarFinanciaciones::execute");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            RespuestaReplicarFinanciacionDTO respuesta = iRFachadaFinanciacion
                    .replicarFinanciacionTerceros(organismoTransitoDTO.getCodigoOrganismo());
            LogReplicarFinanciaciones logReplicarFinanciaciones = new LogReplicarFinanciaciones();
            logReplicarFinanciaciones.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logReplicarFinanciaciones.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            logReplicarFinanciaciones.setTotalFinanciacionesLeidos(respuesta.getTotalFinanciacionesLeidos());
            logReplicarFinanciaciones.setTotalFinanciacionesNoRecibidos(respuesta.getTotalFinanciacionesNoRecibidos());
            logReplicarFinanciaciones.setTotalFinanciacionesRecibidos(respuesta.getTotalFinanciacionesRecibidos());
            log.escribir(usuarioPersonaDTO.getLogin(), logReplicarFinanciaciones);
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
