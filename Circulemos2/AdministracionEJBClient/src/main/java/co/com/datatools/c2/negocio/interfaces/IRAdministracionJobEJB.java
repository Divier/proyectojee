package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.TareaAutomaticaDTO;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

@Remote
public interface IRAdministracionJobEJB {

    /**
     * Retorna el listado de jobs
     * 
     * @return Una Lista DTO de los jobs que estan presentes
     * @author divier.casas
     */
    List<TareaAutomaticaDTO> consultarJobs() throws CirculemosRuntimeException;

    /**
     * Permite modificar la ejecucion del job
     * 
     * @param tareaAutomatica
     * @author divier.casas
     */
    void cambiarEjecucion(TareaAutomaticaDTO tareaAutomatica);

    /**
     * Permite validar si una expresion de programacion de cron es valida o no
     * 
     * @param expresionCron
     * @return
     */
    boolean validarExpresion(String expresionCron);

    /**
     * Permite la ejecucion inmediata del job
     * 
     * @param tareaAutomatica
     * @return
     */
    void ejecutarInmediatamente(TareaAutomaticaDTO tareaAutomatica);

    /**
     * Permite detener la siguiente ejecucion del job
     * 
     * @param tareaAutomatica
     */
    void detenerSiguienteEjecucion(TareaAutomaticaDTO tareaAutomatica);
}