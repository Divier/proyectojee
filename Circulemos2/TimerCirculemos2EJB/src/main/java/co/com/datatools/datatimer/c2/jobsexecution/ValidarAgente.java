package co.com.datatools.datatimer.c2.jobsexecution;

import java.util.ArrayList;

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
import co.com.datatools.datatimer.c2.utilidades.LogGeneracionValidacionAgentes;

public class ValidarAgente extends JobQuartz {
    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.VALIDAR_AGENTE);
    private final static Logger logger2 = Logger.getLogger(ValidarAgente.class.getName());

    private IRFachadaComparendo iRFachadaComparendo = BeanLocatorC2.locate(IRFachadaComparendo.class,
            BeanLocatorC2.Beans.IRFachadaComparendo.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public ValidarAgente() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger2.debug("ValidarAgente.execute()");
        LoginContext loginContext = null;
        try {
            // Autenticacion
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            // Llamado al metodo verificarAgente
            logger2.info("Ejecutando procedimiento: ValidarAgente()");
            ArrayList<Integer> inconsistencias = iRFachadaComparendo.verificarAgente(organismoTransitoDTO);
            logger2.info("Ejecutado el procedimiento: ValidarAgente()");
            LogGeneracionValidacionAgentes logInconsistencias = new LogGeneracionValidacionAgentes();
            logInconsistencias.setAgentesNoRegistrados(inconsistencias.get(0));
            logInconsistencias.setAgentesNoVigentes(inconsistencias.get(1));
            logInconsistencias.setCitacionesSinAgente(inconsistencias.get(2));
            logInconsistencias.setTotalDeInconsistencias(
                    inconsistencias.get(0) + inconsistencias.get(1) + inconsistencias.get(2));
            logger.escribir(usuarioPersonaDTO.getLogin(), logInconsistencias);

        } catch (Exception e) {
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
        logger2.debug("ValidarAgente.interrupt()");
    }
}