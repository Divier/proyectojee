package co.com.datatools.datatimer.c2.jobs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.datatimer.DataTimerQuartz;
import co.com.datatools.datatimer.JobQuartz;
import co.com.datatools.datatimer.c2.impl.LoggerImpl;
import co.com.datatools.datatimer.c2.interfaces.ILConfiguracionJob;
import co.com.datatools.datatimer.c2.interfaces.IRDataTimer;
import co.com.datatools.datatimer.c2.jobsexecution.ValidarFinanciacionCuotaPendiente;
import co.com.datatools.datatimer.c2.utilidades.JBossLoginContextFactory;
import co.com.datatools.datatimer.dto.JobDTO;
import co.com.datatools.datatimer.dto.TriggerDTO;
import co.com.datatools.datatimer.enumeraciones.EnumJob;
import co.com.datatools.datatimer.exceptions.SaveDataTimerException;
import co.com.datatools.datatimer.interfaces.ILogger.LevelLog;

@Startup
@Singleton
@LocalBean
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobValidarFinanciacionCuotaPendiente extends DataTimerQuartz implements IRDataTimer {

    @EJB
    private ILConfiguracionJob iConfiguracionJob;

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    private IRParametro iRParametro = BeanLocatorC2.locate(IRParametro.class,
            BeanLocatorC2.Beans.IRParametro.toString());

    public JobValidarFinanciacionCuotaPendiente() {
        super();
    }

    @PostConstruct
    public void initialize() {
        this.initDataTimerQuartz();
    }

    @Override
    protected void initLogger() {
        this.logger = new LoggerImpl();
    }

    @Override
    protected void initDTOs() {
        this.logger.setLog(LevelLog.Info, "Iniciando metodo JobValidarFinanciacionCuotaPendiente::initDTOs()");
        LoginContext loginContext = null;
        try {
            List<JobDTO> lJobDTO = iConfiguracionJob
                    .consultarConfiguracionJob(EnumJob.JOB_VALIDAR_FINANCIACION_CUOTA_PENDIENTE.getId());

            if (lJobDTO != null && !lJobDTO.isEmpty()) {
                JobDTO jobConfiguradoDB = lJobDTO.get(0);
                jobDTO = new JobDTO();
                jobDTO.setNombreJob(jobConfiguradoDB.getNombreJob());
                jobDTO.setGrupoJob(jobConfiguradoDB.getGrupoJob());
                jobDTO.setIdJob(jobConfiguradoDB.getIdJob());
                jobDTO.setEstadoJobDTO(jobConfiguradoDB.getEstadoJobDTO());

                TriggerDTO triggerDTO;
                triggerDTO = new TriggerDTO();

                List<TriggerDTO> lTriggerDTO = jobConfiguradoDB.getListTriggers();
                loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
                loginContext.login();
                OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

                if (lTriggerDTO != null && !lTriggerDTO.isEmpty()) {
                    TriggerDTO triggerConfiguradoDB = lTriggerDTO.get(0);
                    triggerDTO.setNombre(triggerConfiguradoDB.getNombre());
                    triggerDTO.setGrupo(triggerConfiguradoDB.getGrupo());
                    triggerDTO.setEstadoTriggerDTO(triggerConfiguradoDB.getEstadoTriggerDTO());
                    ValorParametroDTO valorParametroDTO = iRParametro.consultarParametro(
                            EnumParametro.PERIODICIDAD_VALIDACION_FINANCIACION_PENDIENTE,
                            organismoTransitoDTO.getCodigoOrganismo(), true);
                    triggerDTO.setExpressionCronTrigger(valorParametroDTO.getValorParam());
                }
                organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
                jobDTO.addTrigger(triggerDTO);
            }
            this.logger.setLog(LevelLog.Info, "Finalizando metodo JobValidarFinanciacionCuotaPendiente::initDTOs()");
        } catch (LoginException e) {
            logger.setLog(LevelLog.Error, e.getMessage());
        } finally {
            if (loginContext != null) {
                try {
                    loginContext.logout();
                } catch (LoginException e) {
                    logger.setLog(LevelLog.Error, e.getMessage());
                }
            }
        }
    }

    @Override
    protected void initTypeRepositoryDTO() {
        this.typeRepositoryDTO = TypeRepositoryDTO.DB;
    }

    @Override
    protected Class<? extends JobQuartz> getExecubleClass() {
        return ValidarFinanciacionCuotaPendiente.class;
    }

    @Override
    protected void saveTrigger(TriggerDTO newTriggerDTO) throws SaveDataTimerException {
        this.logger.setLog(LevelLog.Info,
                "Iniciando metodo JobValidarFinanciacionCuotaPendiente::saveTrigger(TriggerDTO)");
        this.logger.setLog(LevelLog.Info,
                "Finalizando metodo JobValidarFinanciacionCuotaPendiente::saveTrigger(TriggerDTO)");
    }

    @Override
    protected void saveJob(JobDTO newJobDTO) throws SaveDataTimerException {
        this.logger.setLog(LevelLog.Info, "Iniciando metodo JobValidarFinanciacionCuotaPendiente::saveJob(JobDTO)");
        this.logger.setLog(LevelLog.Info, "Finalizando metodo JobValidarFinanciacionCuotaPendiente::saveJob(JobDTO)");
    }

    @PreDestroy
    protected void eliminandoJob() {
        if (this.isClustered()) {
            this.shutdownJob();
        } else {
            this.deleteJob();
        }
    }
}