package co.com.datatools.c2.negocio.interfaces.formularios;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.formularios.AsignacionDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaDetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaSeguimientoFormularioDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.enumeraciones.EnumTipoFormulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

//import co.com.datatools.c2.dto.formularios.StockTipoAsignacionDTO;

@Local
public interface ILFormulario {

    /**
     * 
     * @see IRFormulario#registrarRangoFormulario(RangoFormularioDTO)
     */
    void registrarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException,
            CirculemosAlertaException;

    /**
     * @see IRFormulario#consultaRangoFormulario(ConsultaRangoFormularioDTO)
     */
    List<RangoFormularioDTO> consultarRangoFormulario(ConsultaRangoFormularioDTO consultaRangoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRFormulario#consultarRangosFormularioDisponibles(RangoFormularioDTO)
     */
    List<RangoFormularioDTO> consultarRangosFormularioDisponibles(RangoFormularioDTO rangoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRFormulario#consultarCantidadFormularios(FormularioDTO)
     */
    int consultarCantidadFormularios(FormularioDTO formularioDTO);

    /**
     * @see IRFormulario#asignarFormularios(AsignacionDTO)
     */
    int asignarFormularios(AsignacionDTO asignacionDTO) throws CirculemosAlertaException;

    /**
     * @see IRFormulario#calcularFormularios(List)
     */
    int calcularFormularios(ConsultaRangoTipoFormularioDTO consultaRangoTipoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRFormulario#eliminarRangoFormulario(Long)
     */
    void eliminarRangoFormulario(Long idRangoFormulario) throws CirculemosNegocioException;

    /**
     * @see IRFormulario#actualizarRangoFormulario(RangoFormularioDTO)
     */
    void actualizarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException,
            CirculemosAlertaException;

    /**
     * @see IRFormulario#consultarDetalleRangoFormulario(Long)
     */
    RangoFormularioDTO consultarDetalleRangoFormulario(Long idRangoFormulario);

    /**
     * @see IRFormulario#listarNumerosDeRango(String, String, NumeracionFormularioDTO)
     */
    List<String> listarNumerosRango(String numeroInicial, String numeroFinal, NumeracionFormularioDTO numeracionDTO);

    /**
     * @see IRFormulario#consultarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
     */
    List<StockTipoResponsableDTO> consultarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO);

    /**
     * @see IRFormulario#registrarStockTipoResponsable(StockTipoResponsableDTO)
     */
    void registrarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRFormulario#actualizarStockTipoResponsable(stockTipoResponsableDTO)
     */
    public void actualizarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRFormulario#eliminarStockTipoResponsable(Integer)
     */
    void eliminarStockTipoResponsable(Integer idStockTipoResponsable);

    /**
     * @see IRFormulario#cambiarEstadoFormulario(List, boolean, EnumEstadoFomulario, boolean, boolean)
     */
    // Commented unused Sergio Torres
    // void cambiarEstadoFormulario(List<SeguimientoFormularioDTO> seguimientoFormularios, boolean validarResponsable,
    // EnumEstadoFomulario estadoSiguiente, boolean validarConfiguracionEstados, boolean registraRango)
    // throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * @see IRFormulario#listarNumerosFormularioAsignacion(String, Integer, Integer, Integer)
     */
    // Commented unused Sergio torres 17-Dic-2015
    // List<String> listarNumerosFormularioAsignacion(String numeroInicial, Integer codigoOrganismo,
    // Integer idTipoFormulario, Integer idTipoAsignacion) throws CirculemosNegocioException;

    /**
     * @see IRFormulario#consultarDetalleCambioEstado(ConsultaDetalleCambioEstadoDTO)
     */
    public List<DetalleCambioEstadoDTO> consultarDetalleCambioEstado(
            ConsultaDetalleCambioEstadoDTO consultaDetalleCambioEstadoDTO) throws CirculemosNegocioException;

    /**
     * @see IRFormulario#consultarDetalleCambioEstado(Long)
     */
    public DetalleCambioEstadoDTO consultarDetalleCambioEstado(Long codDetalleCambioEstado)
            throws CirculemosNegocioException;

    /**
     * 
     * @see IRFormulario#consultarFormulariosDetalleCambio(Long)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // List<SeguimientoFormularioDTO> consultarFormulariosDetalleCambio(Long idDetalleCambioEstado);

    /**
     * @see IRFormulario#eliminarCambioEstadoFormulario(Long)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // void eliminarCambioEstadoFormulario(Long idDetalleCambioEstado) throws CirculemosNegocioException;

    // Commented unused Sergio Torres 17/dic/2015
    // /**
    // * @see IRFormulario#validarEstadoActualFormularios(Long, Integer)
    // */
    // boolean validarEstadoActualFormularios(Long idDetalleCambioEstadoDTO, Integer idEstadoActual)
    // throws CirculemosNegocioException;

    /**
     * @see IRFormulario#actualizarDetalleCambioEstado(DetalleCambioEstadoDTO, ArchivoTransportableDTO)
     */
    void actualizarDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstadoDTO,
            ArchivoTransportableDTO archivoTransportableDTO) throws CirculemosNegocioException;

    /**
     * @see IRFormulario#consultarFormulario(ConsultaFormularioDTO)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // List<SeguimientoFormularioDTO> consultarFormulario(ConsultaFormularioDTO consultaFormulario);

    /**
     * @see IRFormulario#contarConsultaFormulario(ConsultaFormularioDTO)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // int contarConsultaFormulario(ConsultaFormularioDTO consultaFormulario);

    /**
     * @see IRFormulario#consultarSeguimientoFormulario(ConsultaSeguimientoFormularioDTO)
     */
    FormularioDTO consultarSeguimientoFormulario(ConsultaSeguimientoFormularioDTO consultaSeguimientoFormulario);

    /**
     * @see ILFormulario#consultarSeguimientoFormulario(ConsultaSeguimientoAgrupadoFormularioDTO)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // public List<FormularioDTO> consultarSeguimientoFormulario(
    // ConsultaSeguimientoAgrupadoFormularioDTO consultaSeguimientoAgrupadoFormularioDTO)
    // throws CirculemosNegocioException;

    /**
     * 
     * @see IRFormulario#cambiarEstadoFormularios(CambioEstadoFormularioDTO cambioEstadoFormularioDTO)
     */
    public int cambiarEstadoFormularios(CambioEstadoFormularioDTO cambioEstadoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * 
     * @see IRFormulario#existeEstadoFormularioOrganismo(String, Integer, Integer)
     */
    public boolean existeEstadoFormularioOrganismo(String numeroFormulario, Integer idTipoFormulario,
            Integer idCodigoOrganismo, EnumEstadoFomulario... enumEstadoFomulario);

    /**
     * 
     * @see IRFormulario#existeAsignacion(String, Integer)
     */
    // Commented unused Sergio torres 17-Dic-2015
    // public boolean existeAsignacion(String numeroFormulario, Integer idTipoFormulario);

    /**
     * 
     * @see IRFormulario#existeFormulario(String, int);
     */
    public boolean existeFormulario(String numeroFormulario, int idTipoFormulario);

    /**
     * 
     * @see IRFormulario#cambiarEstadoFormulario(CambioEstadoDTO)
     */
    public void cambiarEstadoFormulario(CambioEstadoDTO cambioEstadoDTO);

    /**
     * @see IRFormulario#validarFormatoNumeroFormulario(String, int)
     */
    public boolean validarFormatoNumeroFormulario(String numeroFormulario, int tipoFormulario)
            throws CirculemosNegocioException;

    /**
     * @see IRFormulario#consultarEstadosFormulariosAsignacion(int,int)
     */
    public EstadoFormularioAsignacionDTO consultarEstadosFormulariosAsignacion(int tipoFormulario, Long idResponsable)
            throws CirculemosNegocioException;

    /**
     * 
     * @see IRFormulario#seleccionarFormulario(UnificacionResponsableDTO, EnumTipoFormulario)
     */
    public String seleccionarFormulario(UnificacionResponsableDTO unificacionResponsableDTO,
            EnumTipoFormulario enumTipoFormulario) throws CirculemosNegocioException;
}
