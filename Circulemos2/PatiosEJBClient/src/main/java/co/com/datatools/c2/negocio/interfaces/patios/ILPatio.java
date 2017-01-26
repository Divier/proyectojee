package co.com.datatools.c2.negocio.interfaces.patios;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.patios.PatioDTO;

/**
 * @author luis.forero
 * 
 */
@Local
public interface ILPatio {

    /**
     * @see IRPatio#consultarPatios(PatioDTO)
     */
    List<PatioDTO> consultarPatios(PatioDTO patioDTO);
}
