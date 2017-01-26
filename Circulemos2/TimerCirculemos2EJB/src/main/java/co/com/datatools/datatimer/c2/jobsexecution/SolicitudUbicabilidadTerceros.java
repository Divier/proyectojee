package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.RespuestaSolicitudUbicabilidadTerceroDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogSolicitudUbicabilidadTerceros;
import co.com.datatools.util.date.UtilFecha;

public class SolicitudUbicabilidadTerceros extends JobQuartz {

    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_UBICABILIDAD_TERCEROS);
    private final static Logger logger = Logger.getLogger(SolicitudUbicabilidadTerceros.class.getName());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    private IRFachadaIntegracionTerceros iRFachadaIntegracionTerceros = BeanLocatorC2
            .locate(IRFachadaIntegracionTerceros.class, BeanLocatorC2.Beans.IRFachadaIntegracionTerceros.toString());

    public SolicitudUbicabilidadTerceros() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("SolicitudUbicabilidadTerceros::execute");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            RespuestaSolicitudUbicabilidadTerceroDTO rSolicitudUbicabilidadTercero = iRFachadaIntegracionTerceros
                    .solicitarUbicabilidadTerceros(organismoTransitoDTO.getCodigoOrganismo());
            LogSolicitudUbicabilidadTerceros logSolicUbicaTerceros = new LogSolicitudUbicabilidadTerceros();
            logSolicUbicaTerceros.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            logSolicUbicaTerceros.setTotalRegUbicLeidos(rSolicitudUbicabilidadTercero.getTotalRegUbicLeidos());
            logSolicUbicaTerceros.setTotalRegUbicRecibidos(rSolicitudUbicabilidadTercero.getTotalRegUbicRecibidos());
            logSolicUbicaTerceros
                    .setTotalRegUbicNoRecibidos(rSolicitudUbicabilidadTercero.getTotalRegUbicNoRecibidos());
            log.escribir(usuarioPersonaDTO.getLogin(), logSolicUbicaTerceros);
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