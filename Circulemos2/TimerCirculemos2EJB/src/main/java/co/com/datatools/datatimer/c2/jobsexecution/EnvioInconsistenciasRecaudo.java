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
import co.com.datatools.datatimer.c2.utilidades.LogGeneracionInconsistenciasRecaudo;

public class EnvioInconsistenciasRecaudo extends JobQuartz {

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.GENERACION_REPORTE_INCONSISTENCIAS_RECAUDO);
    private final static Logger logger2 = Logger.getLogger(EnvioInconsistenciasRecaudo.class.getName());

    private IRFachadaRecaudo iRFachadaRecaudo = BeanLocatorC2.locate(IRFachadaRecaudo.class,
            BeanLocatorC2.Beans.IRFachadaRecaudo.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public EnvioInconsistenciasRecaudo() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Recaudo");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            int cantidadInconsistencias = iRFachadaRecaudo.enviarInconsistencias(organismoTransitoDTO
                    .getCodigoOrganismo());
            LogGeneracionInconsistenciasRecaudo logGeneracionInconsistenciasRecaudo = new LogGeneracionInconsistenciasRecaudo();
            logGeneracionInconsistenciasRecaudo.setCodigoOrganismo(organismoTransitoDTO.getCodigoOrganismo());
            logGeneracionInconsistenciasRecaudo.setTotalInconsistencias(cantidadInconsistencias);
            logger.escribir(usuarioPersonaDTO.getLogin(), logGeneracionInconsistenciasRecaudo);
        } catch (LoginException e) {
            logger2.error(e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        // TODO Auto-generated method stub
    }
}