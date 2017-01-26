package co.com.datatools.client.timer;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.quartz.SchedulerException;

import co.com.datatools.client.timer.exception.AdministradorTimerException;
import co.com.datatools.datatimer.dto.JobDTO;
import co.com.datatools.datatimer.dto.JobDTO.EstadoJob;
import co.com.datatools.datatimer.dto.TriggerDTO;
import co.com.datatools.datatimer.exceptions.SaveDataTimerException;
import co.com.datatools.datatimer.interfaces.IDataTimer;

public class RemoteTimerEJB {

    private String strTareasProgramadasJndi;
    private String Ip;
    private int Puerto;
    private String usuario;
    private char[] password;
    private IDataTimer dataTimerQuartz;

    public RemoteTimerEJB(String strTareasProgramadasJndi, String ip, int puerto) throws AdministradorTimerException {
        super();
        this.strTareasProgramadasJndi = strTareasProgramadasJndi;
        Ip = ip;
        Puerto = puerto;
        this.dataTimerQuartz = this.getIDataTimer(Ip, Puerto);
    }

    public RemoteTimerEJB(String strTareasProgramadasJndi, String ip, int puerto, String usuario, char[] password,
            IDataTimer dataTimerQuartz) throws AdministradorTimerException {
        this(strTareasProgramadasJndi, ip, puerto);
        this.usuario = usuario;
        this.password = password;
        this.dataTimerQuartz = dataTimerQuartz;
    }

    public void pausarTriggerJob(TriggerDTO triggerDTO) throws AdministradorTimerException {
        if (dataTimerQuartz.getJobDTO().getEstadoJob().equals(EstadoJob.ACTIVO)) {
            if (triggerDTO != null) {
                dataTimerQuartz.pauseTrigger(triggerDTO);
            }
        } else {
            throw new AdministradorTimerException("No es posible detener el trigger por que el Job esta Inactivo");
        }

    }

    public void reiniciarTriggerJob(TriggerDTO triggerDTO) throws AdministradorTimerException {

        if (dataTimerQuartz.getJobDTO().getEstadoJob().equals(EstadoJob.ACTIVO)) {
            if (triggerDTO != null) {
                dataTimerQuartz.resumeTrigger(triggerDTO);
            }
        } else {
            throw new AdministradorTimerException("No es posible detener el trigger por que el Job esta Inactivo");
        }

    }

    public void pausarJob() {

        dataTimerQuartz.pauseJob();

    }

    public void eliminarJob() {
        dataTimerQuartz.deleteJob();
    }

    public void reiniciarJob() {

        dataTimerQuartz.resumeJob();
    }

    private IDataTimer getIDataTimer(String Ip, int Puerto) throws AdministradorTimerException {

        Properties jndiProps = new Properties();

        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "remote://" + Ip + ":" + Puerto);

        jndiProps.put("jboss.naming.client.ejb.context", true);

        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        // jndiProps.put(Context.SECURITY_PRINCIPAL, "username");
        // jndiProps.put(Context.SECURITY_CREDENTIALS, "password");

        Context ctx;
        try {
            ctx = new InitialContext(jndiProps);
            return (IDataTimer) ctx.lookup(strTareasProgramadasJndi);
        } catch (NamingException e) {
            throw new AdministradorTimerException(e);
        }

    }

    public JobDTO getJobDTO() {
        return dataTimerQuartz.getJobDTO();
    }

    public void sincronizarDTO(JobDTO jobDTO) throws AdministradorTimerException {
        try {
            dataTimerQuartz.synchronizeDTO(jobDTO);
        } catch (SaveDataTimerException e) {
            throw new AdministradorTimerException(e);
        }
    }

    public void reprogramarTriggerJob(TriggerDTO oldTriggerDTO, String newCronExpression)
            throws AdministradorTimerException {

        if (dataTimerQuartz.getJobDTO().getEstadoJob().equals(EstadoJob.ACTIVO)) {

            if (oldTriggerDTO != null && newCronExpression != null) {
                if (oldTriggerDTO.isTriggerProgramatico()) {
                    throw new AdministradorTimerException(
                            "El trigger no puede ser reporgramado por que es programatico");
                } else if (oldTriggerDTO.isTriggerAutomatico()) {

                    try {
                        TriggerDTO newTriggerDTO = new TriggerDTO();
                        newTriggerDTO.setNombre(oldTriggerDTO.getNombre());
                        newTriggerDTO.setGrupo(oldTriggerDTO.getGrupo());
                        newTriggerDTO.setEstadoTrigger(oldTriggerDTO.getEstadoTrigger());
                        newTriggerDTO.setExpressionCronTrigger(newCronExpression);
                        dataTimerQuartz.reprogramTrigger(newTriggerDTO);
                    } catch (SaveDataTimerException e) {
                        throw new AdministradorTimerException(e);
                    } catch (SchedulerException e) {
                        throw new AdministradorTimerException(e);
                    } catch (RuntimeException e) {
                        /**
                         * Cuando la expresion del Cron es incorrecta lanza una excepcion de tipo Runtime
                         */

                        throw new AdministradorTimerException(e);
                    }

                } else {

                    throw new AdministradorTimerException("Tipo de trigger desconocigo");
                }
            } else {
                throw new AdministradorTimerException(
                        "El TriggerDTO enviado en el parametro es null o la expresion es null");
            }
        } else {
            throw new AdministradorTimerException("No es posible reprogramar el trigger por que el Job esta Inactivo");

        }

    }

}
