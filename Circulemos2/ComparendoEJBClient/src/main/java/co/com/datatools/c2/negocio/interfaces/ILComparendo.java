package co.com.datatools.c2.negocio.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.AnulacionDTO;
import co.com.datatools.c2.dto.comparendo.AvisoGeneradoDTO;
import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteInconsistenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoConsultaAnulacionDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaAvisosNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaCantidadComparendosNoNotificadosDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoAnulacionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.DocumentoAvisoGeneradoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionAvisoDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaResolucionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoCargueArchivoNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SaldoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SeguimientoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudNumeroComparendoAbstractDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudOrdenCompaNacioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoInconsistenciaAgente;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILComparendo {

    /**
     * @see IRComparendo#consultarComparendos(ConsultaComparendoDTO)
     */
    public List<ComparendoDTO> consultarComparendos(ConsultaComparendoDTO consultaComparendoDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#existeComparendo(String, int)
     */
    public boolean existeComparendo(String numeroComparendo, int codigoOrganismo);

    /**
     * @see IRComparendo#consultarComparendo(Long)
     */
    public ComparendoDTO consultarComparendo(Long cicomparendo);

    /**
     * 
     * @see IRComparendo#validarFormatoNumeroComparendo(String, int)
     */
    public boolean validarFormatoNumeroComparendo(String numeroComparendo, int tipoComparendo)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarComparendosNotificados(ConsultaNotificacionComparendoDTO, EnumTipoNotificacionComparendo...)
     */
    public List<ResultadoConsultaNotificacionComparendoDTO> consultarComparendosNotificados(
            ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO,
            EnumTipoNotificacionComparendo... tipoNotificacion) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#notificarComparendos(NotificacionComparendoDTO)
     */
    public byte[] notificarComparendos(NotificacionComparendoDTO notificacionComparendo)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#obtenerNombreEvidencia(EvidenciaDTO)
     */
    String obtenerNombreEvidencia(EvidenciaDTO evidencia) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#registrarEvidencias(Long, List)
     */
    List<EvidenciaDTO> registrarEvidencias(long cicomparendo, List<EvidenciaDTO> evidencias)
            throws CirculemosAlertaException, CirculemosNegocioException;

    /**
     * @see IRComparendo#eliminarEvidenciasComparendo(long, List)
     */
    void eliminarEvidenciasComparendo(long cicomparendo, List<Integer> idEvidencias) throws CirculemosNegocioException;

    /**
     * @throws CirculemosNegocioException
     * @see IRComparendo#consultarCantidadComparendosNoNotificados(ConsultaCantidadComparendosNoNotificadosDTO)
     */
    Integer consultarCantidadComparendosNoNotificados(
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificados)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarCantidadRectificaciones(long)
     */
    public Integer consultarCantidadRectificaciones(long cicomparendo);

    /**
     * @see IRComparendo#existePago(long)
     */
    public boolean existePago(long cicomparendo);

    /**
     * @throws CirculemosNegocioException
     * @see IRComparendo#generarNotificacionCorreo(ConsultaCantidadComparendosNoNotificadosDTO,boolean,Date)
     */
    public long generarNotificacionCorreo(
            ConsultaCantidadComparendosNoNotificadosDTO consultaRCantidadComparendosNoNotificadosDTO,
            boolean generarDocumentos, Date fecha) throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * @see IRComparendo#cargarArchivoNotificacion(int, ArchivoTransportableDTO)
     */
    ResultadoCargueArchivoNotificacionDTO cargarArchivoNotificacion(int codigoOrganismo,
            ArchivoTransportableDTO archivo) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#generarResolucionesSancion(Date, int)
     */
    public RespuestaResolucionComparendoDTO generarResolucionesSancion(Date fechaGeneracion,
            int codigoOrganismoTransito) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#generarResolucionSancionAutomatico(int, Date)
     */
    public void generarResolucionSancionAutomatico(int codigoOrganismo, Date fechaGeneracion)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#generarAvisoNotificacion(int)
     */
    AvisoGeneradoDTO generarAvisoNotificacion(int codigoOrganismo)
            throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * 
     * @see IRComparendo#notificarComparendosAviso(int)
     */
    public int notificarComparendosAviso(int codigoOrganismo) throws CirculemosNegocioException;

    /**
     * 
     * @see IRComparendo#generarAvisoNotificacion(int, boolean)
     */
    public DocumentoAvisoGeneradoDTO generarAvisoNotificacion(int codigoOrganismo, boolean cargarContenido)
            throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * 
     * @see IRComparendo#consultarAvisosNotificacion(ConsultaAvisosNotificacionDTO)
     */
    public List<NotificacionAvisoDTO> consultarAvisosNotificacion(
            ConsultaAvisosNotificacionDTO consultaAvisosNotificacionDTO) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarSeguimientoComparendo(long)
     */
    SeguimientoComparendoDTO consultarSeguimientoComparendo(long cicomparendo) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#calcularSaldoComparendo(int, Long, Date)
     */
    public SaldoComparendoDTO calcularSaldoComparendo(int codigoOrganismo, Long cicomparendo, Date fechaLiquidacion)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarComparendo(String, Integer)
     */
    ComparendoDTO consultarComparendo(String numeroComparendo, Integer codigoOrganismo);

    /**
     * @see IRComparendo#solicitarNumeroComparendo(SolicitudNumeroComparendoAbstractDTO)
     */
    RespuestaSolicitudNumeroComparendoDTO solicitarNumeroComparendo(
            SolicitudNumeroComparendoDTO solicitudNumeroComparendoDTO) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#notificarComparendoSIMIT(Integer, Long, EnumAccionComparendo)
     */
    void notificarComparendoSIMIT(Integer codigoOrganismo, Long cicomparendo, EnumAccionComparendo accion)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#existeSolicitudOrdenComparendoNacional(SolicitudOrdenCompaNacioDTO)
     */
    public boolean existeSolicitudOrdenComparendoNacional(SolicitudOrdenCompaNacioDTO solicitudOrdenCompaNacioDTO);

    /**
     * @see IRComparendo#consumirReservaOCN(String, Date)
     */
    public void consumirReservaOCN(String numeroComparendo, Date fechaConsumo) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#actualizarEstadoComparendo(CambioEstadoComparendoDTO)
     */
    public void actualizarEstadoComparendo(CambioEstadoComparendoDTO cambioEstadoComparendo);

    /**
     * @see IRComparendo#consultarInfractorComparendo(Long)
     */
    public PersonaDTO consultarInfractorComparendo(Long idComparendo);

    /**
     * @see IRComparendo#consultarComparendosSimulacionFinanciacion(PersonaDTO, List)
     */
    public List<ConsultaObligacionesDTO> consultarComparendosSimulacionFinanciacion(PersonaDTO consultaInfractorDTO,
            List<Integer> estadosComparendo) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarComparendoAnulacion(ConsultaComparendoAnulacionDTO)
     */
    public List<ComparendoConsultaAnulacionDTO> consultarComparendoAnulacion(ConsultaComparendoAnulacionDTO filtros)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#registrarAnulacion(AnulacionDTO)
     */
    public void registrarAnulacion(AnulacionDTO anulacion, List<Long> comparendos) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#registrarAnulacion(List<Long>)
     */
    public void registrarAnulacion(List<Long> comparendos) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarComparendoTrazabilidad(String, Integer)
     */
    public ComparendoDTO consultarComparendoTrazabilidad(String numeroComparendo, Integer codigoOrganismo);

    /**
     * @see IRComparendo#cambiarNumeroFactura(Long, Long)
     */
    public void cambiarNumeroFactura(Long cicomparendo, Long numeroFacturaNuevo) throws CirculemosNegocioException;

    /**
     * @see IRComparendo#consultarHistoricoNumeroComparendo(HistoricoNumeroComparendoDTO)
     */
    HistoricoNumeroComparendoDTO consultarHistoricoNumeroComparendo(
            HistoricoNumeroComparendoDTO historicoNumeroComparendoDTO);

    /**
     * @see IRComparendo#consultarComparendosIncosistenteAgente(Integer)
     */
    public List<ComparendoAgenteInconsistenteDTO> consultarComparendosIncosistenteAgente(
            EnumTipoInconsistenciaAgente inconsistencia, Date fechaInicialImposicion, Date fechaFinalImposicion)
            throws CirculemosNegocioException;

    /**
     * @see IRComparendo#actualizarAgenteComparendoMasivo(List<ComparendoDTO>, AgenteDTO)
     */
    public ArrayList<Integer> actualizarAgenteComparendoMasivo(List<ComparendoDTO> comparendos, AgenteDTO agente)
            throws CirculemosNegocioException;

    /**
     * Actualiza la informacion del agente de un comparendo
     * 
     * @param comparendoAgente
     *            información del agente y el comparendo
     * @author ricardo.chavarro
     */
    public void actualizaciarAgenteComparendo(ComparendoAgenteDTO comparendoAgente);

    /**
     * @see IRComparendo#actualizarFechaNotificacion(Long, int, Date)
     */
    public void actualizarFechaNotificacion(Long ciComparendo, int codigoOrganismo, Date fechaNotificacion);

    /**
     * Valida si el comparendo esta en la vigencia de un agente para poder asignarlo
     * 
     * @param comparendoDto
     * @param agente
     * @return
     */
    public boolean comparendoValidoAgente(ComparendoDTO comparendoDto, AgenteDTO agente);
}