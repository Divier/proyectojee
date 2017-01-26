package co.com.datatools.datatimer.interfaces;

import org.quartz.SchedulerException;

import co.com.datatools.datatimer.dto.JobDTO;
import co.com.datatools.datatimer.dto.TriggerDTO;
import co.com.datatools.datatimer.exceptions.SaveDataTimerException;

public interface IDataTimer {

    public void reprogramTrigger(TriggerDTO newTriggerDTO) throws SaveDataTimerException, SchedulerException;

    public void synchronizeDTO(JobDTO jobDTO) throws SaveDataTimerException;

    public void pauseTrigger(TriggerDTO TriggerDTO);

    public void resumeTrigger(TriggerDTO TriggerDTO);

    public void resumeJob();

    public void pauseJob();

    public JobDTO getJobDTO();

    public void deleteJob();

}
