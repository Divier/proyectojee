package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.DejarFirmeDTO;
import co.com.datatools.c2.dto.DejarFirmeMetaDataDTO;
import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.FiltroConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaFinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaReplicarFinanciacionDTO;
import co.com.datatools.c2.dto.SimulacionFinanciacionDTO;
import co.com.datatools.c2.dto.ValidaPagoFinanciacionDTO;
import co.com.datatools.c2.dto.VariableCondicionFinanDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRFinanciacion {

    /**
     * @param financiacionDTO
     * @return
     * @author Rodrigo.Cruz
     */
    public FinanciacionDTO consultarFinanciacion(String numeroFinanciacion);

    /**
     * Guarda una financiacion con su detalles de cuotas y obligaciones
     * 
     * @param financiacionDTO
     * @return Financiacion con el id del registro
     * @author julio.pinzon (2016-05-16)
     * @throws CirculemosNegocioException
     */
    public FinanciacionDTO registrarFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException;

    /**
     * Recibe el codigo de organismo para realizar la tarea de replicar financiaciones utilizando funcionalidad de la integracion con terceros
     * 
     * @param codigoOrganismo
     * @return Respuesta de la ejecucion de la replica
     * @author julio.pinzon (2016-05-04)
     */
    public RespuestaReplicarFinanciacionDTO replicarFinanciacionTerceros(Integer codigoOrganismo);

    /**
     * Permite concocer las variables pertenecientes a una condicion de financiacion. (consulta sobre tabla variable_condicion_finan).
     * 
     * @author luis.forero
     * @param pCondicionFinanciacionDTO
     *            : condicion de la financiacion a la cual pertenece la variable.
     * @return List<VariableCondicionFinanDTO> listado de variables para una determinada condicion de financiacion
     * @throws CirculemosNegocioException
     */
    public List<VariableCondicionFinanDTO> consultarVariablesCondicionFinanciacion(
            CondicionFinanciacionDTO pCondicionFinanciacionDTO) throws CirculemosNegocioException;

    /**
     * lleva a cabo el calculo de la simulacion de una financiacion.
     * 
     * @param simulacionFinanciacionDTO
     *            Datos de entrada para llevar a cabo el calculo de la financiacion
     * @return financiacion simulada con respecto a los valores entrantes.
     * @author luis.forero(2016-06-23)
     * @throws CirculemosNegocioException
     */
    public SimulacionFinanciacionDTO calcularSimulacionFinanciacion(SimulacionFinanciacionDTO simulacionFinanciacionDTO)
            throws CirculemosNegocioException;

    /**
     * Registra una simulacion de financiacion
     * 
     * @param financiacionDTO
     *            Contiene la informacion a persistir
     * @return FinanciacionDTO persistida
     * @throws CirculemosNegocioException
     * @author dixon.alvarez
     */
    public RespuestaFinanciacionDTO registrarSimulacionFinanciacion(FinanciacionDTO financiacionDTO)
            throws CirculemosNegocioException;

    /**
     * Se encarga de consultar procesos de financiacion por medio de unos filtros en especifico
     * 
     * @param financiacionDTO
     * @return
     * @author giovanni.velandia
     */
    public List<FinanciacionDTO> consultarFinanciacion(FinanciacionDTO financiacionDTO);

    /**
     * Se encarga de consultar procesos de financiacion por medio de unos filtros en especifico para la consulta de procesos
     * 
     * @param filtroConsultaFinanciacionDTO
     * @return
     * @author giovanni.velandia
     */
    public List<ConsultaFinanciacionDTO> consultarProcesoFinanciacion(
            FiltroConsultaFinanciacionDTO filtroConsultaFinanciacionDTO);

    /**
     * se encarga de pasar de estado prefinanciado a en firme la financiación
     * 
     * @param numeroFinanciacion
     *            número de financiación a dejar en firme
     * @author diego.fonseca
     */
    public DejarFirmeMetaDataDTO dejarFirmeFinanciacion(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException;

    /**
     * Registra y activa la cartera para una financiacion
     * 
     * @param financiacionDTO
     * @throws CirculemosNegocioException
     * @return Id de la cartera generada
     * @author julio.pinzon(2016-07-26)
     */
    public Long registrarCarteraFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de consultar la financiacion con su respectivo detalle
     * 
     * @author giovanni.velandia
     * @param numeroFinanciacion
     * @return
     */
    public ConsultaFinanciacionDTO consultaDetalleFinanciacion(String numeroFinanciacion);

    /**
     * Genera el documento de recibo de pago
     * 
     * @param idDetalleFinanciacion
     * @return
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    public byte[] imprimirReciboPago(Long idDetalleFinanciacion) throws CirculemosNegocioException;

    /**
     * Genera el documento de cuadro de amortizacion
     * 
     * @param idFinanciacion
     * @return
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    public byte[] imprimirCuadroAmortizacion(Long idFinanciacion) throws CirculemosNegocioException;

    /**
     * Lleva a cabo la validacion del pago de cuotas de financiaciones.
     * 
     * @param Integer
     *            Codigo del organismo de transito
     * @throws CirculemosNegocioException
     * @return ValidaPagoFinanciacionDTO
     * @author divier.casas(2016-08-03) mod julio.pinzon(2016-08-08)
     */
    public ValidaPagoFinanciacionDTO validarPagosFinanciaciones(Integer codigoOrganismo);

    /**
     * LLeva a cabo la validacion de financiacion para poder dejar en firme
     * 
     * @param dejarFirmeDTO
     * @return
     * @author Jeison.Rodriguez (2016-08-29)
     * @throws CirculemosNegocioException
     */
    public boolean validarDejarEnFirme(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException;

    public void validarDejarFirmePago(DetalleFinanciacionDTO detalleFinanciacionDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de dejar en firme masivamente
     * 
     * @author giovanni.velandia
     */
    public void dejarFirmeFinanciacionMasivo();

    /**
     * Se encarga de genarar documeto a financiaciones en firme
     * 
     * @author giovanni.velandia
     */
    public void generarDocumentoFirmeFinanciacionMasivo();

    /**
     * Permite llamar al servicio de envios de correo
     * 
     * @param dejarFirmeMetaData
     * @throws CirculemosNegocioException
     */
    public void enviarCorreoDejarFirme(DejarFirmeMetaDataDTO dejarFirmeMetaData) throws CirculemosNegocioException;
}