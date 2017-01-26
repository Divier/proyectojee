package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.EventoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILEvento {

    /**
     * @see IRProceso#existeProceso(ProcesoDTO)
     */
    public List<EventoDTO> consultarEvento(EventoDTO evento);
    
    /**
     * @see IRProceso#registrarProceso(ProcesoDTO)
     */
    public void registrarEvento(EventoDTO evento) throws CirculemosNegocioException;

}