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
import co.com.datatools.datatimer.c2.jobsexecution.SolicitudUbicabilidadTerceros;
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
public class JobSolicitudUbicabilidadTerceros extends DataTimerQuartz implements IRDataTimer {

    @EJB
    private ILConfiguracionJob iConfiguracionJob;

    private IRSeguridadCirculemos iRSeguridadCirculemos = BeanLocatorC2.locate(IRSeguridadCirculemos.class,
            BeanLocatorC2.Beans.IRSeguridadCirculemos.toString());

    private IRParametro iRParametro = BeanLocatorC2.locate(IRParametro.class,
            BeanLocatorC2.Beans.IRParametro.toString());

    public JobSolicitudUbicabilidadTerceros() {
        super();
    }

    @PostConstruct
    public void initialize() {
        this.initDataTimerQuartz();
    }

    @Override
    protected void initLogger() {
        // TODO Auto-generated method stub
        this.logger = new LoggerImpl();
    }

    @Override
    protected void initDTOs() {

        this.logger.setLog(LevelLog.Info, "Iniciando metodo initDTOs()");
        LoginContext loginContext = null;
        try {
            List<JobDTO> lJobDTO = iConfiguracionJob
                    .consultarConfiguracionJob(EnumJob.JOB_SOLICITUD_UBICABILIDAD_TERCEROS.getId());

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
                            EnumParametro.PERIODICIDAD_SOLICITUD_UBICABILIDAD_TERCEROS,
                            organismoTransitoDTO.getCodigoOrganismo(), true);
                    triggerDTO.setExpressionCronTrigger(valorParametroDTO.getValorParam());
                }
                jobDTO.addTrigger(triggerDTO);
            }
            this.logger.setLog(LevelLog.Info, "Finalizando metodo initDTOs()");
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
    protected Class<? extends JobQuartz> getExecubleClass() {
        // TODO Auto-generated method stub
        return SolicitudUbicabilidadTerceros.class;
    }

    @Override
    protected void saveTrigger(TriggerDTO newTriggerDTO) throws SaveDataTimerException {
        this.logger.setLog(LevelLog.Info, "Iniciando metodo saveTrigger(TriggerDTO)");
        this.logger.setLog(LevelLog.Info, "Finalizando metodo saveTrigger(TriggerDTO)");
    }

    @Override
    protected void saveJob(JobDTO newJobDTO) throws SaveDataTimerException {
        this.logger.setLog(LevelLog.Info, "Iniciando metodo saveJob(JobDTO)");
        this.logger.setLog(LevelLog.Info, "Finalizando metodo saveJob(JobDTO)");
    }

    @Override
    protected void initTypeRepositoryDTO() {
        this.typeRepositoryDTO = TypeRepositoryDTO.DB;
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