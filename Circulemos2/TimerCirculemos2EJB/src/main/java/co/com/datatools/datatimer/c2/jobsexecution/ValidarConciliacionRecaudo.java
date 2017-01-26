package co.com.datatools.datatimer.c2.jobsexecution;

import java.util.Date;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.RespuestaConciliarPagoDTO;
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
import co.com.datatools.datatimer.c2.utilidades.LogValidarConciliacionRecaudo;

public class ValidarConciliacionRecaudo extends JobQuartz {

    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.VALIDAR_CONCILIACION_RECAUDO);
    private final static Logger logger = Logger.getLogger(ValidarConciliacionRecaudo.class.getName());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());
    private IRFachadaRecaudo iFachadaRecaudo = BeanLocatorC2.locate(IRFachadaRecaudo.class,
            BeanLocatorC2.Beans.IRFachadaRecaudo.toString());

    public ValidarConciliacionRecaudo() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("ValidarConciliacionRecaudo::execute");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            // Realizar conciliacion
            RespuestaConciliarPagoDTO respuesta = iFachadaRecaudo.realizarConciliacionRecaudo();

            // Registrar resultado en log
            LogValidarConciliacionRecaudo logConciliacion = new LogValidarConciliacionRecaudo();
            logConciliacion.setCodigoOrganismo(organismoTransitoDTO.getCodigoOrganismo());
            logConciliacion.setFechaHoraEjecucion(new Date());
            // Numero de recaudos que han sido enviados a validar
            logConciliacion.setTotalRecaudosValidados(respuesta.getTotalRecaudosConsultados());
            // Numero de recaudos con validacion exitosa
            logConciliacion.setTotalRecaudosRegistrados(respuesta.getTotalRecaudosConciliados());
            // Numero de recaudos con validacion NO exitosa
            logConciliacion.setTotalRecaudosNoConciliados(respuesta.getTotalRecaudosNoConciliados());

            log.escribir(usuarioPersonaDTO.getLogin(), logConciliacion);
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