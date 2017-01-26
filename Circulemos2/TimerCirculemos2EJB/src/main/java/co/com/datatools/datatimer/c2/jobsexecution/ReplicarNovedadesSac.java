package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.RespuestaProcesoSacDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.interfaces.IRFachadaSac;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.seguridad.JBossLoginContextFactory;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.LogReplicarNovedadesSac;

public class ReplicarNovedadesSac extends JobQuartz {

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.REPLICAR_NOVEDADES_SAC);
    private final static Logger logger2 = Logger.getLogger(ReplicarNovedadesSac.class.getName());

    private IRFachadaSac iRFachadaSac = BeanLocatorC2.locate(IRFachadaSac.class,
            BeanLocatorC2.Beans.IRFachadaSac.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public ReplicarNovedadesSac() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger2.debug("ReplicarNovedadesSac.execute()");
        LoginContext loginContext = null;
        try {
            // Autenticacion
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            // Datos de usuario y organismo
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            // Llamado al metodo de replicar novedades de sac
            RespuestaProcesoSacDTO respuesta = iRFachadaSac
                    .replicarNovedadesSac(organismoTransitoDTO.getCodigoOrganismo());

            LogReplicarNovedadesSac logReplicarNovedadesSac = new LogReplicarNovedadesSac();
            logReplicarNovedadesSac.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logReplicarNovedadesSac
                    .setTotalRecaudosLeidos(respuesta.getNumeroFallidos() + respuesta.getNumeroExitosos());
            logReplicarNovedadesSac.setTotalRecaudosNoRecibidos(respuesta.getNumeroFallidos());
            logReplicarNovedadesSac.setTotalRecaudosRecibidos(respuesta.getNumeroExitosos());

            // Loguear info
            logger.escribir(usuarioPersonaDTO.getLogin(), logReplicarNovedadesSac);
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