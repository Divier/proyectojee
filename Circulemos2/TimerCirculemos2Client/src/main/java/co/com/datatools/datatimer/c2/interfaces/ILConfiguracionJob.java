package co.com.datatools.datatimer.c2.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.datatimer.dto.JobDTO;

@Local
public interface ILConfiguracionJob {

    /**
     * @see IRConfiguracionJob#consultarConfiguracionJob(int)
     */
    public List<JobDTO> consultarConfiguracionJob(int identificador);
}