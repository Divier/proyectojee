package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

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
import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILFinanciacion {

    /**
     * @see IRFinanciacion#consultarFinanciacion(numeroFinanciacion)
     */
    public FinanciacionDTO consultarFinanciacion(String numeroFinanciacion);

    /**
     * @see IRFinanciacion#registrarFinanciacion(FinanciacionDTO)
     */
    public FinanciacionDTO registrarFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#replicarFinanciacionTerceros(Integer)
     */
    public RespuestaReplicarFinanciacionDTO replicarFinanciacionTerceros(Integer codigoOrganismo);

    /**
     * Recibe la financiacion consultada por terceros y lo envia a procesar
     * 
     * @param itFinanciacion
     * @param loginUsuario
     * @return true si recibio el recaudo, false en caso contrario
     * @author julio.pinzon (2016-05-05)
     * @throws CirculemosNegocioException
     */
    public boolean recibirFinanciacionTerceros(ItFinanciacionDTO itFinanciacion, String loginUsuario)
            throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#consultarVariablesCondicionFinanciacion(CondicionFinanciacionDTO)
     */
    public List<VariableCondicionFinanDTO> consultarVariablesCondicionFinanciacion(
            CondicionFinanciacionDTO pCondicionFinanciacionDTO) throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#calcularSimulacionFinanciacion(financiacionDTO)
     */
    public SimulacionFinanciacionDTO calcularSimulacionFinanciacion(SimulacionFinanciacionDTO simulacionFinanciacionDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#registrarSimulacionFinanciacion(FinanciacionDTO)
     */
    public RespuestaFinanciacionDTO registrarSimulacionFinanciacion(FinanciacionDTO financiacionDTO)
            throws CirculemosNegocioException;

    /**
     * 
     * @see IRFinanciacion#consultarFinanciacion(financiacionDTO)
     */
    public List<FinanciacionDTO> consultarFinanciacion(FinanciacionDTO financiacionDTO);

    /**
     * 
     * @see IRFinanciacion#consultarProcesoFinanciacion(FiltroConsultaFinanciacionDTO)
     */
    public List<ConsultaFinanciacionDTO> consultarProcesoFinanciacion(
            FiltroConsultaFinanciacionDTO filtroConsultaFinanciacionDTO);

    /**
     * 
     * @see IRFinanciacion#dejarFirmeFinanciacion(DejarFirmeDTO)
     */
    public DejarFirmeMetaDataDTO dejarFirmeFinanciacion(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException;

    /**
     * 
     * @see IRFinanciacion#registrarCarteraFinanciacion(FinanciacionDTO)
     */
    public Long registrarCarteraFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#consultaDetalleFinanciacion(String)
     */
    public ConsultaFinanciacionDTO consultaDetalleFinanciacion(String numeroFinanciacion);

    /**
     * Registra y activa la cartera para una financiacion
     * 
     * @param Long
     *            idProceso
     * @param EnumEstadoProceso
     *            enumEstadoProceso
     * @param boolean
     *            cerrarProceso
     * @param Integer
     *            codigoOrganismo
     * @author divier.casas(2016-07-28)
     * @throws CirculemosNegocioException
     */
    public void actualizarEstadosAsociadosFinanciacion(Long idProceso, String numeroFinanciacion,
            EnumEstadoProceso enumEstadoProceso, boolean cerrarProceso, Integer codigoOrganismo)
            throws CirculemosNegocioException;;

    /**
     * @see IRFinanciacion#imprimirReciboPago(Long)
     */
    public byte[] imprimirReciboPago(Long idDetalleFinanciacion) throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#imprimirCuadroAmortizacion(Long)
     */
    public byte[] imprimirCuadroAmortizacion(Long idFinanciacion) throws CirculemosNegocioException;

    /**
     * 
     * @see IRFinanciacion#validarPagosFinanciaciones(Integer)
     */
    public ValidaPagoFinanciacionDTO validarPagosFinanciaciones(Integer codigoOrganismo);

    /**
     * 
     * @see IRFinanciacion#validarDejarEnFirme(DejarFirmeDTO)
     */
    public boolean validarDejarEnFirme(DejarFirmeDTO dejarFirmeDTO) throws CirculemosNegocioException;

    public void validarDejarFirmePago(DetalleFinanciacionDTO detalleFinanciacionDTO) throws CirculemosNegocioException;

    /**
     * @see IRFinanciacion#enviarCorreoDejarFirme(DejarFirmeMetaDataDTO)
     */
    public void enviarCorreoDejarFirme(DejarFirmeMetaDataDTO dejarFirmeMetaData) throws CirculemosNegocioException;
}