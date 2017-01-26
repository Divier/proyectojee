package co.com.datatools.datatimer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

public abstract class JobQuartz implements org.quartz.InterruptableJob {


    public abstract void execute(JobExecutionContext context) throws JobExecutionException;

    public abstract void interrupt() throws UnableToInterruptJobException;

}
