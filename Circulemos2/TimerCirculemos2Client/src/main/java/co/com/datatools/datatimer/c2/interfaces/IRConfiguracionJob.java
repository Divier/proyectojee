package co.com.datatools.datatimer.c2.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.datatimer.dto.JobDTO;

@Remote
public interface IRConfiguracionJob {

    /**
     * Permite consultar la configuracion definida para un job
     * 
     * @param identificador
     *            de job
     * @return
     */
    public List<JobDTO> consultarConfiguracionJob(int identificador);
}