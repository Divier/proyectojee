package co.com.datatools.c2.negocio.interfaces.patios;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.patios.PatioDTO;

/**
 * Interface que expone los servicios necesarios para el negocio de patios.
 * 
 * @author luis.forero (2015-10-26)
 * 
 */
@Remote
public interface IRPatio {

    /**
     * Consulta patios dentro del sistema.
     * 
     * @param patioDTO
     *            contiene los atributos con los cuales va a ser efectuada la consulta
     * @return Retorna la lista de patios que cumplen con el filtro indicados. Si el filtro es nulo retorna todos los patios.
     * @author luis.forero (2015-10-26)
     */
    List<PatioDTO> consultarPatios(PatioDTO patioDTO);
}
