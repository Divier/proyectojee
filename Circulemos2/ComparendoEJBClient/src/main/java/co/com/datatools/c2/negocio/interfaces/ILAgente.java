package co.com.datatools.c2.negocio.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoAgenteDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleHistoricoAgenteDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILAgente {

    /**
     * @see IRAgente#consultarAgente(AgenteDTO)
     */
    List<AgenteDTO> consultarAgente(AgenteDTO agenteDTO);

    /**
     * Metodo que inserta en la tabla comparendo agente
     * 
     * @author Jeison.Rodriguez (2016-09-13)
     * 
     * @param consultaComparendoAgenteDTO
     */
    public void insertarComparendoAgente(ConsultaComparendoAgenteDTO consultaComparendoAgenteDTO);

    /**
     * @see IRAgente#validarAgente()
     */
    public ArrayList<Integer> validarAgente(OrganismoTransitoDTO organismo);

    /**
     * @see IRAgente#registrarAgente(AgenteDTO)
     */
    void registrarAgente(AgenteDTO agenteDTO) throws CirculemosNegocioException;

    /**
     * @see IRAgente#actualizarAgente(AgenteDTO)
     */
    void actualizarAgente(AgenteDTO agenteDTO) throws CirculemosNegocioException;

    /**
     * @see IRAgente#consultarHistoricoAgente(Integer, String)
     */
    public List<DetalleHistoricoAgenteDTO> consultarHistoricoAgente(Integer organismoTransito, String placa);

    /**
     * @see IRAgente#consultarAgenteValido(AgenteDTO)
     */
    public AgenteDTO consultarAgenteValido(AgenteDTO agenteDTO) throws CirculemosNegocioException;

    /**
     * Metodo encargado de Consultar los comparendos electronicos de comparendo y comparendo_agente y retorna los comparendo que no existan en
     * comparendo agente
     * 
     * @return ComparendoDTO lista de comparendos que esten en comparendo y no esten en comaprendo agente
     * @author Jeison.Rodriguez (2016-09-13)
     */
    public List<ConsultaComparendoAgenteDTO> consultarComparendoAgenteComparendo();
}