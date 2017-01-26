package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.TareaAutomaticaDTO;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

@Local
public interface ILAdministracionJobEJB {

    /**
     * @see IRAdministracionJobEJB#consultarJobs()
     */
    List<TareaAutomaticaDTO> consultarJobs() throws CirculemosRuntimeException;

    /**
     * @see IRAdministracionJobEJB#cambiarEjecucion(TareaAutomaticaDTO)
     */
    void cambiarEjecucion(TareaAutomaticaDTO tareaAutomatica);

    /**
     * @see IRAdministracionJobEJB#validarExpresion(String)
     */
    boolean validarExpresion(String expresionCron);

    /**
     * @see IRAdministracionJobEJB#ejecutarInmediatamente(TareaAutomaticaDTO)
     */
    void ejecutarInmediatamente(TareaAutomaticaDTO tareaAutomatica);

    /**
     * @see IRAdministracionJobEJB#detenerSiguienteEjecucion(TareaAutomaticaDTO)
     */
    void detenerSiguienteEjecucion(TareaAutomaticaDTO tareaAutomatica);
}