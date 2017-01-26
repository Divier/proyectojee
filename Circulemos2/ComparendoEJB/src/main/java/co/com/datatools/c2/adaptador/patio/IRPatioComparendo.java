package co.com.datatools.c2.adaptador.patio;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.PatioComparendoDTO;

@Remote
public interface IRPatioComparendo {

    /**
     * Consultar patios acorde al filtro indicado.
     * 
     * @param patioComparendoDTO
     * @return Retorna el listado de patios acorde al filtro indicado.
     */
    public List<PatioComparendoDTO> consultarPatios(PatioComparendoDTO patioComparendoDTO);
}
