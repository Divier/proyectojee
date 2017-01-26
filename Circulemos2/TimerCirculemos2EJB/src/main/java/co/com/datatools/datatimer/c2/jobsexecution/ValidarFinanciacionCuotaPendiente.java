package co.com.datatools.datatimer.c2.jobsexecution;

import java.math.BigInteger;
import java.util.Date;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.ValidaPagoFinanciacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaCoactivo;
import co.com.datatools.c2.negocio.fachada.IRFachadaFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogValidarFinanciacionCuotaPendiente;
import co.com.datatools.util.date.UtilFecha;

public class ValidarFinanciacionCuotaPendiente extends JobQuartz {

    private static final ILog log = LoggerC2.getLogger(EnumLogSistema.VALIDAR_FINANCIACION_CUOTA_PENDIENTE);
    private final static Logger logger = Logger.getLogger(ValidarFinanciacionCuotaPendiente.class.getName());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    private IRFachadaFinanciacion iRFachadaFinanciacion = BeanLocatorC2.locate(IRFachadaFinanciacion.class,
            BeanLocatorC2.Beans.IRFachadaFinanciacion.toString());

    private IRFachadaAdminGeneral iRFachadaAdminGeneral = BeanLocatorC2.locate(IRFachadaAdminGeneral.class,
            BeanLocatorC2.Beans.IRFachadaAdminGeneral.toString());

    private IRFachadaCoactivo iRFachadaCoactivo = BeanLocatorC2.locate(IRFachadaCoactivo.class,
            BeanLocatorC2.Beans.IRFachadaCoactivo.toString());

    public ValidarFinanciacionCuotaPendiente() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("ValidarFinanciacionCuotaPendiente::execute");
        LoginContext loginContext = null;
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            LogValidarFinanciacionCuotaPendiente logValidarFinan = new LogValidarFinanciacionCuotaPendiente();
            logValidarFinan.setFechaHoraEjecucion(UtilFecha.buildCalendar().getTime());
            logValidarFinan.setOrganismoTransito(organismoTransitoDTO.getCodigoOrganismo());
            logValidarFinan.setTotalFinanValidadas(BigInteger.ZERO.intValue());
            logValidarFinan.setTotalFinanAnuladas(BigInteger.ZERO.intValue());
            if (validarEjecucionValidadorPagos(organismoTransitoDTO.getCodigoOrganismo(),
                    UtilFecha.buildCalendar().getTime())) {
                ValidaPagoFinanciacionDTO validaPagoFinanciacionDTO = iRFachadaFinanciacion
                        .validarPagosFinanciaciones(organismoTransitoDTO.getCodigoOrganismo());
                logValidarFinan.setTotalFinanValidadas(validaPagoFinanciacionDTO.getCantFinanValidadas());
                logValidarFinan.setTotalFinanAnuladas(validaPagoFinanciacionDTO.getCantFinanAnuladas());
            }
            log.escribir(usuarioPersonaDTO.getLogin(), logValidarFinan);
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

    private boolean validarEjecucionValidadorPagos(Integer codigoOrganismo, Date fechaSistema) {
        boolean estado = false;
        if (codigoOrganismo != null) {
            if (!iRFachadaAdminGeneral.esDiaNoHabil(codigoOrganismo, fechaSistema)) {
                estado = true;
            }
        }
        return estado;
    }
}