package co.com.datatools.c2.negocio.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.DetalleHistoricoAgenteDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Permite llevar a cabo la administracion de los agentes en el sistema.
 * 
 * @author luis.forero(2015-10-13)
 * 
 */
@Remote
public interface IRAgente {

    /**
     * Consulta un agente vigente utilizando los filtros del parámetro ingresado. Puede consultarse por Tipo y numero de identificacion de la persona.
     * 
     * @param agenteDTO
     *            contiene los filtros tipo y numero de documento de la persona
     * @return agenteDTO con los datos del agente y los basicos de la persona.
     * @author luis.forero(2015-10-13)
     */
    List<AgenteDTO> consultarAgente(AgenteDTO agenteDTO);

    /**
     * Valida si el comparendo viene con Codigo de agente y si es asi valida que el comparendo no se encuentre en la tabla comaprendo agente y lo
     * inserte
     * 
     * @param organismo
     */
    public ArrayList<Integer> validarAgente(OrganismoTransitoDTO organismo);

    /**
     * Permite registrar un agente de tránsito
     * 
     * @param agenteDTO
     * 
     * @return agenteDTO con los datos del agente y los basicos de la persona
     * @throws CirculemosNegocioException
     * @author divier.casas(2016-09-14)
     */
    void registrarAgente(AgenteDTO agenteDTO) throws CirculemosNegocioException;

    /**
     * Permite actualizar los datos de un agente de tránsito
     * 
     * @param agenteDTO
     * 
     * @return agenteDTO con los datos del agente y los basicos de la persona
     * @throws CirculemosNegocioException
     * @author divier.casas(2016-09-15)
     */
    void actualizarAgente(AgenteDTO agenteDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de consultar el historico de un agente de tansito
     * 
     * @author giovanni.velandia
     * @param organismoTransito
     * @param placa
     * @return
     */
    public List<DetalleHistoricoAgenteDTO> consultarHistoricoAgente(Integer organismoTransito, String placa);

    /**
     * Consulta un agente valido
     * 
     * @param agenteDTO
     * @return agente valido
     * @author ricardo.chavarro
     */
    public AgenteDTO consultarAgenteValido(AgenteDTO agenteDTO) throws CirculemosNegocioException;

    /**
     * Retorna el estado del agente validando las fechas de vigencia
     * 
     * @param agenteDTO
     * @return
     */
    public boolean vigenciaAgente(AgenteDTO agenteDTO);
}