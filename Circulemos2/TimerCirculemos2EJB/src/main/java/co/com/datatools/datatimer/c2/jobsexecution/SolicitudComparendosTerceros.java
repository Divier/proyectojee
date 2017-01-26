package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudComparendosDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogSolicitudComparendosTerceros;
import co.com.datatools.util.date.UtilFecha;

public class SolicitudComparendosTerceros extends JobQuartz {

    private final static Logger logger = Logger.getLogger(SolicitudComparendosTerceros.class.getName());

    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_COMPARENDOS_TERCEROS);

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    private IRFachadaComparendo iRFachadaComparendo = BeanLocatorC2.locate(IRFachadaComparendo.class,
            BeanLocatorC2.Beans.IRFachadaComparendo.toString());

    private IRFachadaIntegracionTerceros fachadaIntegracionTercerosejb = BeanLocatorC2.locate(
            IRFachadaIntegracionTerceros.class, BeanLocatorC2.Beans.IRFachadaIntegracionTerceros.toString());

    public SolicitudComparendosTerceros() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("SolicitudComparendosTerceros::execute");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            LogSolicitudComparendosTerceros logSolicitudComparendos = new LogSolicitudComparendosTerceros();
            logSolicitudComparendos.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logSolicitudComparendos.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            RespuestaSolicitudComparendosDTO respuesta = iRFachadaComparendo.solicitarComparendosTerceros();
            logSolicitudComparendos.setTotalComparendosLeidos(respuesta.getTotalComparendosLeidos());
            logSolicitudComparendos.setTotalComparendosNoRecibidos(respuesta.getTotalComparendosNoRecibidos());
            logSolicitudComparendos.setTotalComparendosRecibidos(respuesta.getTotalComparendosRecibidos());

            logger.info("Ejecutando procedimiento: fachadaIntegracionTercerosejb.cargarEvidenciasComparendosTerceros()");
            fachadaIntegracionTercerosejb.cargarEvidenciasComparendosTerceros();
            logger.info("Ejecutado: fachadaIntegracionTercerosejb.cargarEvidenciasComparendosTerceros()");
            log.escribir(usuarioPersonaDTO.getLogin(), logSolicitudComparendos);
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
