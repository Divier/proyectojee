package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.RespuestaReplicarPagoDTO;
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
import co.com.datatools.datatimer.c2.utilidades.LogNotificacionReplicaPago;
import co.com.datatools.util.date.UtilFecha;

public class ReplicarPago extends JobQuartz {

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.RECEPCION_DATOS_RECAUDO_TERCEROS);
    private final static Logger logger2 = Logger.getLogger(ReplicarPago.class.getName());

    private IRFachadaRecaudo iRFachadaRecaudo = BeanLocatorC2.locate(IRFachadaRecaudo.class,
            BeanLocatorC2.Beans.IRFachadaRecaudo.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public ReplicarPago() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger2.debug("ReplicarPago.execute()");
        LoginContext loginContext = null;
        try {
            // Autenticacion
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            // Datos de usuario y organismo
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            // Llamado al metodo de replicar pagos
            RespuestaReplicarPagoDTO respuestaReplicarPagos = iRFachadaRecaudo
                    .replicarPagoTerceros(organismoTransitoDTO.getCodigoOrganismo());

            LogNotificacionReplicaPago logNotifRepPago = new LogNotificacionReplicaPago();
            logNotifRepPago.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logNotifRepPago.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            logNotifRepPago.setTotalRecaudosLeidos(respuestaReplicarPagos.getTotalRecaudosLeidos());
            logNotifRepPago.setTotalRecaudosRecibidos(respuestaReplicarPagos.getTotalRecaudosRecibidos());
            logNotifRepPago.setTotalRecaudosNoRecibidos(respuestaReplicarPagos.getTotalRecaudosNoRecibidos());

            // Loguear info
            logger.escribir(usuarioPersonaDTO.getLogin(), logNotifRepPago);
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