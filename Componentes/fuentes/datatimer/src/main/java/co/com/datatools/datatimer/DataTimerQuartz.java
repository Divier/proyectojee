package co.com.datatools.datatimer;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

import co.com.datatools.datatimer.dto.JobDTO;
import co.com.datatools.datatimer.dto.TriggerDTO;
import co.com.datatools.datatimer.enumeraciones.EnumEstadosJob;
import co.com.datatools.datatimer.enumeraciones.EnumEstadosTrigger;
import co.com.datatools.datatimer.exceptions.SaveDataTimerException;
import co.com.datatools.datatimer.interfaces.IDataTimer;
import co.com.datatools.datatimer.interfaces.ILogger;

public abstract class DataTimerQuartz implements IDataTimer {

    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;
    private String schedulerInstanceId;
    private Properties props;
    private JobDetail job;

    protected JobDTO jobDTO;
    protected TypeRepositoryDTO typeRepositoryDTO;
    protected ILogger logger;

    public void initDataTimerQuartz() {

        this.initProperties();
        this.initConfigScheduler();
        this.initJobs();
    }

    public enum TypeRepositoryDTO {
        XML, DB
    }

    public TypeRepositoryDTO getTypeRepositoryDTO() {
        return typeRepositoryDTO;
    }

    public void setTypeRepositoryDTO(TypeRepositoryDTO typeRepositoryDTO) {
        this.typeRepositoryDTO = typeRepositoryDTO;
    }

    public JobDTO getJobDTO() {
        return jobDTO;
    }

    protected String getSchedulerInstanceId() {
        this.logger.setLog(ILogger.LevelLog.Info, "Iniciando metodo getSchedulerInstanceId()");
        if (schedulerInstanceId == null) {
            try {
                schedulerInstanceId = scheduler.getSchedulerInstanceId();
            } catch (SchedulerException e) {
                this.logger.setLog(ILogger.LevelLog.Error, "Error obteniedo schedulerInstanceId: " + e);

            }
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Iniciando metodo getSchedulerInstanceId()");
        return schedulerInstanceId;
    }

    private boolean validarInfoInit() {
        this.logger.setLog(ILogger.LevelLog.Info, "Iniciando metodo validarInfoDTOs()");
        boolean resultaVerificacion = true;
        if (this.jobDTO == null) {
            this.logger.setLog(ILogger.LevelLog.Error, "jobDTO es null");
            resultaVerificacion = false;
        } else if (this.jobDTO.getEstadoJobDTO() == null) {
            this.logger.setLog(ILogger.LevelLog.Error, "Estado de job no definido");
            resultaVerificacion = false;
        } else if (this.jobDTO.getNombreJob() == null || this.jobDTO.getNombreJob().trim().equals("")) {
            this.logger.setLog(ILogger.LevelLog.Error, "El nombre del job no definido o es vacio");
            resultaVerificacion = false;
        } else if (this.jobDTO.getGrupoJob() == null || this.jobDTO.getGrupoJob().trim().equals("")) {
            this.logger.setLog(ILogger.LevelLog.Error, "El grupo de job no definido o es vacio");
            resultaVerificacion = false;
        } else if (this.jobDTO.getListTriggers() == null || this.jobDTO.getListTriggers().isEmpty()) {
            this.logger.setLog(ILogger.LevelLog.Error, "No estan definida una lista de trigger en el Jobs");
            resultaVerificacion = false;
        } else if (this.typeRepositoryDTO == null) {
            this.logger.setLog(ILogger.LevelLog.Error, "No esta definido el tipo de repositorio de los DTO");
            resultaVerificacion = false;
        } else if (this.jobDTO.getListTriggers().isEmpty()) {
            for (TriggerDTO triggerDTO : this.jobDTO.getListTriggers()) {
                if (triggerDTO.getNombre() == null || triggerDTO.getNombre().trim().equals("")) {
                    this.logger.setLog(ILogger.LevelLog.Error, "El nombre del trigger es null o vacio ");
                    resultaVerificacion = false;
                } else if (triggerDTO.getGrupo() == null || triggerDTO.getGrupo().trim().equals("")) {
                    this.logger.setLog(ILogger.LevelLog.Error, "El grupo del trigger es null o vacio ");
                    resultaVerificacion = false;
                } else if (triggerDTO.getEstadoTriggerDTO() == null) {
                    this.logger.setLog(ILogger.LevelLog.Error, "El estado del trigger no esta definido");
                    resultaVerificacion = false;
                } else if (!triggerDTO.isTriggerAutomatico() && !triggerDTO.isTriggerProgramatico()) {
                    this.logger.setLog(ILogger.LevelLog.Error, "El tipo de trigger no esta definido");
                    resultaVerificacion = false;
                } else if (triggerDTO.isTriggerAutomatico()
                        && !org.quartz.CronExpression.isValidExpression(triggerDTO.getExpresionScheduler())) {
                    this.logger.setLog(ILogger.LevelLog.Error, "La expresión del trigger automatico no es correcta.");
                    resultaVerificacion = false;
                }

            }
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finalizando metodo validarInfoDTOs()");
        return resultaVerificacion;
    }

    private void initJobs() {

        this.logger.setLog(ILogger.LevelLog.Info, "Iniciando metodo initJobs()");
        this.initTypeRepositoryDTO();
        this.initDTOs();
        boolean saveDTO = false;
        if (this.validarInfoInit()) {
            Trigger trigger = null;
            // String schedId=null;

            this.logger.setLog(ILogger.LevelLog.Info, "Creando Job");

            job = newJob(getExecubleClass()).withIdentity(jobDTO.getNombreJob(), jobDTO.getGrupoJob()).requestRecovery()
                    .build();

            if (job != null) {
                this.logger.setLog(ILogger.LevelLog.Info, "Creando Triggers del Job");
                /**
                 * Si el estado inicial es eliminado lo cambia automaticamente a Activo
                 */
                if (jobDTO.getEstadoJobDTO().getId() == EnumEstadosJob.ELIMINADO.getId()) {
                    jobDTO.getEstadoJobDTO().setId(EnumEstadosJob.ACTIVO.getId());
                    saveDTO = true;
                }
                for (TriggerDTO triggerDTO : jobDTO.getListTriggers()) {
                    /**
                     * Si el estado inicial es eliminado lo cambia automaticamente a Activo
                     */
                    if (triggerDTO.getEstadoTriggerDTO().getId().equals(EnumEstadosTrigger.ELIMINADO.getId())) {
                        triggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.ACTIVO.getId());
                        saveDTO = true;
                    }
                    this.logger.setLog(ILogger.LevelLog.Info, "Creando Trigger con nombre {} y grupo {}",
                            triggerDTO.getNombre(), triggerDTO.getGrupo());

                    if (triggerDTO.isTriggerAutomatico()) {

                        trigger = newTrigger().withIdentity(triggerDTO.getNombre(), triggerDTO.getGrupo())
                                .withSchedule(cronSchedule(triggerDTO.getExpresionScheduler()))
                                .forJob(jobDTO.getNombreJob(), jobDTO.getGrupoJob()).build();

                    } else if (triggerDTO.isTriggerProgramatico()) {

                        trigger = triggerDTO.getTriggerProgramatico();
                    }
                    try {
                        this.logger.setLog(ILogger.LevelLog.Info, "Scheduler Trigger con nombre {} y grupo {}",
                                triggerDTO.getNombre(), triggerDTO.getGrupo());

                        if (scheduler.checkExists(jobKey(jobDTO.getNombreJob(), jobDTO.getGrupoJob()))) {
                            scheduler.scheduleJob(trigger);
                        } else {
                            scheduler.scheduleJob(job, trigger);
                        }

                        // scheduler..getJobDetail(jobKey(jobDTO.getNombreJob(), this.schedulerInstanceId)).getJobDataMap().
                    } catch (SchedulerException e) {
                        this.logger.setLog(ILogger.LevelLog.Warn, "No es posible crear el trigger con Job: " + e);

                    }

                }

                try {
                    this.logger.setLog(ILogger.LevelLog.Info, "Iniciando Job con nombre {}", jobDTO.getNombreJob());
                    if (!scheduler.isStarted())
                        scheduler.start();
                } catch (SchedulerException e) {
                    this.logger.setLog(ILogger.LevelLog.Error, "No es posible iniciar el Job: " + e);
                }
                /**
                 * Asegura que se guarde el estado activo del Job y sus Triggers
                 */
                if (saveDTO) {
                    try {
                        this.logger.setLog(ILogger.LevelLog.Info, "Salvando el jobDTO  {} en initJobs()",
                                jobDTO.getNombreJob());
                        this.saveJob(jobDTO);
                    } catch (SaveDataTimerException e) {
                        this.logger.setLog(ILogger.LevelLog.Error, "No es posible salvar el jobDTO  {} en initJobs()",
                                e);

                    }
                }

            }
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finalizando metodo initJobs()");

    }

    protected boolean isClustered() {
        String strValueCluster = props.getProperty("org.quartz.jobStore.isClustered");
        boolean isClustered = false;
        if ("true".equals(strValueCluster)) {
            isClustered = true;
        }
        return isClustered;
    }

    private void initConfigScheduler() {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia initConfigScheduler()");

        try {
            if (schedulerFactory == null) {
                schedulerFactory = new org.quartz.impl.StdSchedulerFactory(props);

                if (scheduler == null) {
                    scheduler = schedulerFactory.getScheduler();
                }
            }
        } catch (SchedulerException e) {
            this.logger.setLog(ILogger.LevelLog.Error, "Error inicializando Sheduler: " + e);

        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza initConfigScheduler()");
    }

    private void initProperties() {
        /**
         * Inicia la propiedad de Log
         */
        this.initLogger();

        this.logger.setLog(ILogger.LevelLog.Info, "Inicia initProperties()");
        props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("quartz.properties");
        if (is == null) {
            this.logger.setLog(ILogger.LevelLog.Error, "No es posible encontrar el archivo quartz.properties");
        } else {
            try {

                props.load(is);
                props.setProperty("org.quartz.scheduler.instanceName", this.getClass().getSimpleName());

            } catch (IOException e) {
                this.logger.setLog(ILogger.LevelLog.Error, "Error cargando las propiedades: " + e);
            }
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza initProperties()");
    }

    protected abstract void initLogger();

    protected abstract void initDTOs();

    protected abstract void initTypeRepositoryDTO();

    protected abstract Class<? extends JobQuartz> getExecubleClass();

    /**
     * Guarda un trigger especifico. Para obtener el jobDTO asociado se debe acceder a la variable jobDTO de la clase.
     * 
     * @param newTriggerDTO
     *            . Nuevo Trigger a ser guardado
     * @throws SaveDataTimerException.
     *             Lanza exception si no es posible guardar el Trigger
     */
    protected abstract void saveTrigger(TriggerDTO newTriggerDTO) throws SaveDataTimerException;

    /**
     * Guarda el JobDTO junto con los TriggerDTOs asociados
     * 
     * @param newJobDTO
     *            . Nuevo Job con usus Triggers
     * @throws SaveDataTimerException.
     *             Lanza exception si no es posible guardar el Job
     */
    protected abstract void saveJob(JobDTO newJobDTO) throws SaveDataTimerException;

    public void reprogramTrigger(TriggerDTO newTriggerDTO) throws SaveDataTimerException, SchedulerException {

        this.logger.setLog(ILogger.LevelLog.Info, "Inicia reprogramingTrigger(TriggerDTO)");
        Trigger oldTrigger;
        Trigger newTrigger;
        if (this.jobDTO.getEstadoJobDTO().getId() == EnumEstadosJob.ACTIVO.getId()) {
            if (this.jobDTO.getListTriggers().contains(newTriggerDTO)) {

                try {
                    oldTrigger = scheduler.getTrigger(triggerKey(newTriggerDTO.getNombre(), newTriggerDTO.getGrupo()));

                    newTrigger = newTrigger().withIdentity(newTriggerDTO.getNombre(), newTriggerDTO.getGrupo())

                            .withSchedule(cronSchedule(newTriggerDTO.getExpresionScheduler())).build();

                    scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);

                    /**
                     * Cuando un trigger es reprogramado automaticamente este pasa a un estado activo y el Job tambien en caso de estar Inactivos.
                     */
                    newTriggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.ACTIVO.getId());

                    this.replaceTriggerDTO(newTriggerDTO);

                    try {
                        this.saveTrigger(newTriggerDTO);
                        // this.saveJob(jobDTO);
                    } catch (SaveDataTimerException e) {
                        this.logger.setLog(ILogger.LevelLog.Error, "Error guardando Trigger: {}", e);
                        /**
                         * Reestablece el Trigger.
                         */
                        scheduler.rescheduleJob(newTrigger.getKey(), oldTrigger);
                        throw e;
                    }

                } catch (SchedulerException e) {
                    this.logger.setLog(ILogger.LevelLog.Error, "Error reprogramando Trigger", e);
                    throw e;
                }

            } else {

                this.logger.setLog(ILogger.LevelLog.Error,
                        "Nuevo Triger no se encuentra en la lista de triggers del DTO con nombre {}, grupo {} y tipo {}",
                        newTriggerDTO.getGrupo(), newTriggerDTO.getTipoScheduler().toString());
            }
        } else {
            this.logger.setLog(ILogger.LevelLog.Error,
                    "No es posible reprogramar el Trigger por que el Job no esta activo");
        }

        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza reprogramingTrigger(TriggerDTO)");

    }

    public void pauseTrigger(TriggerDTO triggerDTO) {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia pauseTrigger(TriggerDTO)");
        if (this.jobDTO.getEstadoJobDTO().getId() == EnumEstadosJob.ACTIVO.getId()) {
            try {

                scheduler.pauseTrigger(triggerKey(triggerDTO.getNombre(), triggerDTO.getGrupo()));
                triggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.INACTIVO.getId());
                this.replaceTriggerDTO(triggerDTO);

                try {
                    this.saveTrigger(triggerDTO);
                    // this.saveJob(this.jobDTO);
                } catch (SaveDataTimerException e) {
                    this.logger.setLog(ILogger.LevelLog.Error, "Error salvando trigger en pauseTrigger", e);
                }
            } catch (SchedulerException e) {
                this.logger.setLog(ILogger.LevelLog.Error,
                        "Error en estado 'puase' Trigger con nombre {} y grupo {} Erro {}", triggerDTO.getNombre(),
                        triggerDTO.getGrupo(), e);

            }
        } else {
            this.logger.setLog(ILogger.LevelLog.Error, "No es posible pausar el Trigger por que el Job no esta activo");
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza pauseTrigger(TriggerDTO)");
    }

    public void pauseJob() {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia pauseJob()");

        try {

            scheduler.pauseJob(jobKey(jobDTO.getNombreJob(), jobDTO.getGrupoJob()));
            jobDTO.getEstadoJobDTO().setId(EnumEstadosJob.INACTIVO.getId());
            for (TriggerDTO triggerDTO : jobDTO.getListTriggers()) {
                triggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.INACTIVO.getId());
            }
            this.saveJob(jobDTO);

        } catch (SchedulerException e) {

            this.logger.setLog(ILogger.LevelLog.Error, "Error en estado 'puase' Job con nombre {} y grupo {} Erro {}",
                    jobDTO.getNombreJob(), jobDTO.getGrupoJob(), e);

        } catch (SaveDataTimerException e) {
            this.logger.setLog(ILogger.LevelLog.Error, "Error salvando el Job en pauseJob", e);
        }

        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza pauseJob()");

    }

    public void resumeJob() {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia resumeJob()");

        try {

            scheduler.resumeJob(jobKey(jobDTO.getNombreJob(), jobDTO.getGrupoJob()));
            jobDTO.getEstadoJobDTO().setId(EnumEstadosJob.ACTIVO.getId());

            for (TriggerDTO triggerDTO : jobDTO.getListTriggers()) {
                triggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.ACTIVO.getId());
            }
            this.saveJob(jobDTO);
        } catch (SchedulerException e) {

            this.logger.setLog(ILogger.LevelLog.Error, "Error en estado 'resume' Job con nombre {} y grupo {} Erro {}",
                    jobDTO.getNombreJob(), jobDTO.getGrupoJob(), e);

        } catch (SaveDataTimerException e) {
            this.logger.setLog(ILogger.LevelLog.Error, "Error salvando el Job en resumeJob", e);
        }

        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza resumeJob()");

    }

    public void resumeTrigger(TriggerDTO triggerDTO) {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia resumeTrigger(TriggerDTO)");
        if (this.jobDTO.getEstadoJobDTO().getId() == EnumEstadosJob.ACTIVO.getId()) {
            try {
                scheduler.resumeTrigger(triggerKey(triggerDTO.getNombre(), triggerDTO.getGrupo()));
                triggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.ACTIVO.getId());
                this.replaceTriggerDTO(triggerDTO);
                this.jobDTO.getEstadoJobDTO().setId(EnumEstadosJob.ACTIVO.getId());

                this.saveTrigger(triggerDTO);
                // this.saveJob(this.jobDTO);

            } catch (SchedulerException e) {
                this.logger.setLog(ILogger.LevelLog.Error,
                        "Error en estado 'resume' Trigger con nombre {} y grupo {} Erro {}", triggerDTO.getNombre(),
                        triggerDTO.getGrupo(), e);

            } catch (SaveDataTimerException e) {
                this.logger.setLog(ILogger.LevelLog.Error, "Error salvando trigger en resumeTrigger", e);
            }
        } else {
            this.logger.setLog(ILogger.LevelLog.Error,
                    "No es posible reactivar el Trigger por que el Job no esta activo");
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza resumeTrigger(TriggerDTO)");
    }

    private void replaceTriggerDTO(TriggerDTO newTriggerDTO) {
        if (this.jobDTO.getListTriggers().contains(newTriggerDTO)) {

            TriggerDTO ltriggerDTO = this.jobDTO.getListTriggers()
                    .get(this.jobDTO.getListTriggers().indexOf(newTriggerDTO));

            ltriggerDTO.setNombre(newTriggerDTO.getNombre());
            ltriggerDTO.setGrupo(newTriggerDTO.getGrupo());
            ltriggerDTO.setEstadoTriggerDTO(newTriggerDTO.getEstadoTriggerDTO());
            if (newTriggerDTO.isTriggerAutomatico()) {
                ltriggerDTO.setExpressionCronTrigger(newTriggerDTO.getExpresionScheduler());
            } else if (newTriggerDTO.isTriggerProgramatico()) {
                ltriggerDTO.setTriggerProgramatico(newTriggerDTO.getTriggerProgramatico());
            }

        } else {
            this.logger.setLog(ILogger.LevelLog.Error, "El trigger no esta en la lista");
        }

    }

    /**
     * El proceso de sincronización sirve en los contextos de Cluster donde una de las instancias actualizó la información de DTO pero esta
     * información debe ser sincronizada en las demas instancias Dependiendo del tipo de almacenamiento de DTO este salvará o no la información. Para
     * el caso de XML se deberá realizar el proceso para guardar la información
     * 
     * @param jobDTO.
     *            jobDTO a sincronizar
     * @throws SaveDataTimerException.
     *             Lanza exception si no puede realizar el proceso de salvado de informacion
     */

    public void synchronizeDTO(JobDTO jobDTO) throws SaveDataTimerException {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia sincronizarDTO(JobDTO)");
        this.jobDTO = jobDTO;

        if (this.typeRepositoryDTO.equals(TypeRepositoryDTO.XML)) {
            try {
                this.saveJob(jobDTO);
            } catch (SaveDataTimerException e) {
                this.logger.setLog(ILogger.LevelLog.Error,
                        "No es posible salvar el jobDTO en el proceso de sincronizacion: {}", e);

            }
        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza sincronizarDTO(JobDTO jobDTO)");

    }

    public void deleteJob() {
        this.logger.setLog(ILogger.LevelLog.Info, "Inicia deleteJob()");
        try {
            scheduler.deleteJob(jobKey(jobDTO.getNombreJob(), jobDTO.getGrupoJob()));

            jobDTO.getEstadoJobDTO().setId(EnumEstadosJob.ELIMINADO.getId());
            for (TriggerDTO triggerDTO : jobDTO.getListTriggers()) {
                triggerDTO.getEstadoTriggerDTO().setId(EnumEstadosTrigger.ELIMINADO.getId());
            }
            this.saveJob(jobDTO);

            this.shutdownJob();
        } catch (SchedulerException e) {
            this.logger.setLog(ILogger.LevelLog.Error, "Error eliminado Job: {}", e);

        } catch (SaveDataTimerException e) {
            this.logger.setLog(ILogger.LevelLog.Error, "Error salvando el Job en pauseJob", e);

        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finaliza deleteJob()");
    }

    protected void shutdownJob() {
        this.logger.setLog(ILogger.LevelLog.Info, "Iniciando shutdownJob()");
        try {
            if (scheduler != null && this.job != null) {
                scheduler.interrupt(this.job.getKey());
                scheduler.shutdown(true);
            }
        } catch (SchedulerException e) {
            this.logger.setLog(ILogger.LevelLog.Error, "Error interrumpiendo el Job: {}", e);

        }
        this.logger.setLog(ILogger.LevelLog.Info, "Finalizando shutdownJob()");
    }
}