package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.EventoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IREvento {
	/**
	 * Consulta los eventos a partir de los datos de un DTO
	 * @param proceso
	 * @return lista de eventos
	 */
	public List<EventoDTO> consultarEvento(EventoDTO evento);
	
	/**
	 * registra un evento 
	 * @param proceso
	 * @throws CirculemosNegocioException
	 */
	public void registrarEvento(EventoDTO evento) throws CirculemosNegocioException;
}