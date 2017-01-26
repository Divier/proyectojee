package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;

@Remote
public interface IRFuenteConsulta {

    /**
     * Retorna el listado de fuentes de consulta ordenados por prioridad, relacionados al tipo de fuente de consulta y organismo de transito indicado.
     * 
     * @param codigoOrganismo
     * @param idTipoFuenteConsulta
     * @return Listado de fuentes de consulta relacionados al tipo de fuente de consulta y organismo de transito indicado.
     * @author rodrigo.cruz
     */
    List<DetalleFuenteConsultaDTO> consultarDetallesFuenteConsulta(int codigoOrganismo, int idTipoFuenteConsulta);

}
