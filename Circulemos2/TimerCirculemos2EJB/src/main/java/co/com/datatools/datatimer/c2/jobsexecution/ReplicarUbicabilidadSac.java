package co.com.datatools.datatimer.c2.jobsexecution;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import co.com.datatools.c2.dto.RespuestaUbicabilidadSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.fachada.interfaces.IRFachadaSac;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.c2.utilidades.LogReplicarUbicabilidadSac;

public class ReplicarUbicabilidadSac extends JobQuartz {

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.REPLICAR_UBICABILIDAD_SAC);
    private final static Logger logger2 = Logger.getLogger(ReplicarUbicabilidadSac.class.getName());

    private IRFachadaSac iFachadaSac = BeanLocatorC2.locate(IRFachadaSac.class,
            BeanLocatorC2.Beans.IRFachadaSac.toString());

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    public ReplicarUbicabilidadSac() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger2.debug("ReplicarUbicabilidadSac.execute()");
        LoginContext loginContext = null;
        try {
            // Autenticacion
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();

            // Datos de usuario y organismo
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

            // Consultar registros disponibles
            RespuestaUbicabilidadSacDTO respuesta = iFachadaSac
                    .consultarUbicabilidadSac(organismoTransitoDTO.getCodigoOrganismo());

            int contador = 0;
            for (UbicabilidadSacDTO ubicabilidad : respuesta.getUbicabilidadSacList()) {
                UbicabilidadSacDTO ubicabilidadDTO = iFachadaSac.replicarUbicabilidadSac(ubicabilidad);
                if (ubicabilidadDTO != null) {
                    ubicabilidad.setId(ubicabilidadDTO.getId());
                    respuesta.setNumeroExitosos(respuesta.getNumeroExitosos() + 1);
                    iFachadaSac.registrarUbicabilidadReplicada(organismoTransitoDTO.getCodigoOrganismo(), ubicabilidad,
                            respuesta.getLsIdPersonaHistorico().get(contador));
                } else {
                    respuesta.setNumeroFallidos(respuesta.getNumeroFallidos() + 1);
                }
                ++contador;
            }

            // Loguear info
            LogReplicarUbicabilidadSac log = new LogReplicarUbicabilidadSac();
            log.setNumeroExitosos(respuesta.getNumeroExitosos());
            log.setNumeroFallidos(respuesta.getNumeroFallidos());
            log.setTotalConsultas(respuesta.getUbicabilidadSacList().size());

            logger.escribir(usuarioPersonaDTO.getLogin(), log);
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