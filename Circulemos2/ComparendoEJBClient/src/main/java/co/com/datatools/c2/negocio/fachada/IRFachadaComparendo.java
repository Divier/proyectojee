package co.com.datatools.c2.negocio.fachada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudComparendosDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRFachadaComparendo {

    /**
     * Consulta toda la informacion de un comparendo por su id
     * 
     * @param codigoOrganismo
     *            organismo asociado al comparendo
     * @param cicomparendo
     *            id del comparendo a retornar
     * @return List<ComparendoDTO> con la informacion de los comparendos encontrados
     * @author divier.casas
     */
    public List<ComparendoDTO> consultarComparendos(int codigoOrganismo, Long cicomparendo);

    /**
     * Consulta la configuración de la infracción indicada para la fecha indicada
     * 
     * @param codigoAlfanumerico
     *            codigo alfanumerico de la infraccion a buscar
     * @param fechaVigencia
     *            fecha de vigencia de la infraccion
     * @return infraccion encontrada, null si no encuentra
     * @author divier.casas
     */
    public ConfiguracionInfraccionDTO consultarConfiguracionInfraccion(String codigoAlfanumerico, Date fechaVigencia);

    /**
     * Retorna el valor de la infracción acorde a los campos indicados dependiendo si es de embriaguez
     * 
     * @param liquidarTarifaInfraccionDTO
     * @return Retorna valor tarifa
     * @author divier.casas
     */
    public BigDecimal consultarTarifaInfraccion(LiquidarTarifaInfraccionDTO datosInfraccion)
            throws CirculemosNegocioException;

    /**
     * Actualiza el estado de envio a SIMIT del comparendo a generado, genera trazabilidad del comparendo acorde a la accion indicada. Valida que los
     * campos no sean nulos.
     * 
     * @param codigoOrganismo
     *            codigo de organismo donde se aactualiza el comparendo
     * @param cicomparendo
     *            identificador del comparendo a actualizar
     * @param accion
     *            accion ejectuada
     * @author luis.forero(2016-03-30)
     * @throws CirculemosNegocioException
     *             <bt>- SIM_001101(Comparendo a actualizar notificacion de SIMIT no encontrado)<br>
     *             - SIM_001102(Identificador del comparendo notificado no puede ser nulo) <br>
     *             - SIM_001103(Organismo de transito no puede ser nulo <br>
     *             - SIM_001104(Accion ejecutada no puede ser nula)
     */
    void notificarComparendoSIMIT(Integer codigoOrganismo, Long cicomparendo, EnumAccionComparendo accion)
            throws CirculemosNegocioException;

    /**
     * Actualiza el estado del comparendo segun los datos enviados
     * 
     * @param cambioEstadoComparendo
     *            dto con datos de cambio de estado
     * @author julio.pinzon 2016-04-28
     */
    public void actualizarEstadoComparendo(CambioEstadoComparendoDTO cambioEstadoComparendo);

    /**
     * Consulta, homologa y procesa una multa o comparendo de una fuente externa.
     * 
     * @return respuesta con las cantidades de registros leidos, procesados y no procesados.
     * @author luis.forero(2016-05-06)
     */
    RespuestaSolicitudComparendosDTO solicitarComparendosTerceros();

    /**
     * Consulta toda la informacion de un comparendo por su id
     * 
     * @param cicomparendo
     *            id del comparendo a retornar
     * @return ComparendoDTO con la informacion del comparendo con el id indicado o CirculemosRuntimeException si no existe
     * @author maria.torres
     */
    public ComparendoDTO consultarComparendo(Long cicomparendo);

    /**
     * Consulta toda la informacion de un comparendo por su numero
     * 
     * @param numeroComparendo
     *            numero del comparendo a retornar
     * @param codigoOrganismo
     *            codigo de organismo donde se aactualiza el comparendo
     * @return ComparendoDTO con la informacion del comparendo con el numero indicado
     * @author julio.pinzon 2016-07-25
     */
    public ComparendoDTO consultarComparendo(String numeroComparendo, Integer codigoOrganismo);

    /**
     * Cambia el numero de factura de AXIS al nuevo enviado y guarda la traza
     * 
     * @param cicomparendo
     * @param numeroFacturaNuevo
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-08-24
     */
    public void cambiarNumeroFactura(Long cicomparendo, Long numeroFacturaNuevo) throws CirculemosNegocioException;

    /**
     * Consulta el historico de un numero de comprarendo
     * 
     * @param historicoNumeroComparendoDTO
     *            Contiene la informacion del antiguo o nuevo numero de comparendo
     * @return HistoricoNumeroCOmparendoDTO
     * @author Dixon.Alvarez 2016-08-25
     */
    HistoricoNumeroComparendoDTO consultarHistoricoNumeroComparendo(
            HistoricoNumeroComparendoDTO historicoNumeroComparendoDTO);

    /**
     * Valida si el comparendo viene con Codigo de agente y si es asi valida que el comparendo no se encuentre en la tabla comaprendo agente y lo
     * inserte
     * 
     * @author Jeison.Rodriguez (2016-09-13)
     */
    public ArrayList<Integer> verificarAgente(OrganismoTransitoDTO organismoTransitoDTO);

    /**
     * Enviar Notificaciones a E-Notifica
     * 
     * @return
     */
    public Integer[] enviarNotificaciones(Integer codigoOrganismo);

    /**
     * Permite actualizar la fecha de notificacion de un comparendo
     * 
     * @param ciComparendo
     * @param codigoOrganismo
     * @param fechaNotificacion
     */
    public void actualizarFechaNotificacion(Long ciComparendo, int codigoOrganismo, Date fechaNotificacion);

    /**
     * Registro de anulacion de comparendos
     * 
     * @author giovanni.velandia
     * @param idComparendos
     * @throws CirculemosNegocioException
     */
    public void registrarAnulacion(List<Long> idComparendos) throws CirculemosNegocioException;
}