package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ejb.Local;

import co.com.datatools.c2.dto.AprobacionPrecoactivoDTO;
import co.com.datatools.c2.dto.CargueCoactivoDTO;
import co.com.datatools.c2.dto.CargueNotificaCoactivoResulDTO;
import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.ConsultaSeguimientoCoactivoDTO;
import co.com.datatools.c2.dto.FiltroCoactivoDTO;
import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.dto.PrecoactivoDTO;
import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.dto.RegistroRadicarExcepcionDTO;
import co.com.datatools.c2.dto.RespuestaCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaSeguimientoDTO;
import co.com.datatools.c2.dto.RespuestaTrazabilidadDTO;
import co.com.datatools.c2.dto.SolicitudOficioCoactivoDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoPrecoactivo;
import co.com.datatools.c2.enumeraciones.EnumVariableCondicionCoactivo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILCoactivo {

    /**
     * @see IRCoactivo#registrarCoactivo(List, ConfiguracionCoactivoDTO, Map)
     */
    public void registrarCoactivo(List<ObligacionCoactivoDTO> obligaciones,
            ConfiguracionCoactivoDTO configuracionCoactivo,
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion) throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#registrarCoactivoJob(Integer)
     */
    public Integer registrarCoactivoJob(Integer codigoOrganismo);

    /**
     * @see IRCoactivo#consultarSeguimientosCoactivo(ConsultaSeguimientoCoactivoDTO)
     */
    List<RespuestaSeguimientoDTO> consultarSeguimientosCoactivo(ConsultaSeguimientoCoactivoDTO consulta)
            throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#consultarTrazaSeguimiento(Long)
     */
    List<RespuestaTrazabilidadDTO> consultarTrazaSeguimiento(Long idProceso);

    /**
     * @see IRCoactivo#consultarObligacionesCoactivo(ObligacionCoactivoDTO)
     */
    List<ObligacionCoactivoDTO> consultarObligacionesCoactivo(ObligacionCoactivoDTO obligacionCoactivoDTO);

    /**
     * @see IRCoactivo#consultarPrecoactivo(PrecoactivoDTO, List)
     */
    public List<PrecoactivoDTO> consultarPrecoactivo(PrecoactivoDTO precoactivo, List<EnumEstadoPrecoactivo> estados);

    /**
     * registra los coactivos en axis
     */
    void registrarCoactivoAxis(CoactivoDTO coactivoDTOs, String proceso) throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#cargarArchivoCoactivos(CargueArchivoDTO, ArchivoTransportableDTO, ConfiguracionCoactivoDTO)
     */
    public Future<CargueArchivoDTO> cargarArchivoCoactivos(CargueArchivoDTO cargueArchivo,
            ArchivoTransportableDTO archivoTransportableDTO, ConfiguracionCoactivoDTO configuracionCoactivoDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#consultarPrecoactivoAprobacion()
     */
    public List<AprobacionPrecoactivoDTO> consultarPrecoactivoAprobacion();

    /**
     * @see IRCoactivo#autorizarPrecoactivos(List, FuncionarioDTO)
     */
    public void autorizarPrecoactivos(List<AprobacionPrecoactivoDTO> precoactivos, FuncionarioDTO secretario);

    /**
     * @see IRCoactivo#consultarCoactivos(CoactivoDTO)
     */
    public List<CoactivoDTO> consultarCoactivos(CoactivoDTO coactivo);

    /**
     * @see IRCoactivo#procesarArchivoNotificacionesCoactivos(ArchivoTransportableDTO)
     */
    public CargueNotificaCoactivoResulDTO procesarArchivoNotificacionesCoactivos(ArchivoTransportableDTO archivoDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#modificarCarguePrecoactivos(CargueArchivoDTO, boolean)
     */
    public void modificarCarguePrecoactivos(CargueArchivoDTO cargueArchivoDTO, boolean aceptar);

    /**
     * Genera documentos coactivo
     * 
     * @param filaCoactivo
     * @throws CirculemosNegocioException
     * @throws CirculemosAlertaException
     */
    public void generarDocumentosAnteriores(Object[] filaCoactivo)
            throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * Registra un cargue con sus respectivos precoactivos
     * 
     * @param cargueCoactivoDTO
     *            Contiene la informacion a persistir
     * @param archivoCargado
     *            Contiene el archivo utilizado en el cargue
     * @return CargueCoactivoDTO persistido
     * @author julio.pinzon 2016-12-05
     */
    public CargueCoactivoDTO registrarCarguePrecoactivos(CargueCoactivoDTO cargueCoactivoDTO);

    /**
     * @see IRCoactivo#radiarExcepcion(RegistroRadicarExcepcionDTO)
     */
    public void radicarExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#falloExcepcion(RegistroRadicarExcepcionDTO)
     */
    public void falloExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#validarRadicacionExcepciones(Long)
     */
    public void validarRadicacionExcepciones(Long idCoactivo) throws CirculemosNegocioException;

    /**
     * @See IRCoactivo#validarRadicacionExcepciones(Long)
     */
    public void validarFalloExcepciones(Long idRadicarExcepcion) throws CirculemosNegocioException;

    /**
     * @see IRCoactivo#consultarRadicarExcepcion(Long)
     */
    public RadicarExcepcionDTO consultarRadicarExcepcion(Long idCoactivo);

    /**
     * @see IRCoactivo#consultarCoactivo(String)
     */
    public CoactivoDTO consultarCoactivo(String numeroCoactivo);

    /**
     * @see IRCoactivo#consultarSolicitudOficioBien(FiltroCoactivoDTO)
     */
    public List<RespuestaCoactivoDTO> consultarCoactivo(FiltroCoactivoDTO FiltroCoactivoDTO);

    /**
     * @see IRCoactivo#consultarSolicitudOficioCoactivo(Long)
     */
    public List<SolicitudOficioCoactivoDTO> consultarSolicitudOficioCoactivo(Long idCoactivo);

    /**
     * @see IRCoactivo#consultarSolicitudOficioCoactivo(Long,String)
     */
    public SolicitudOficioCoactivoDTO consultarSolicitudOficioCoactivo(Long idCoactivo, String numeroOficio);
}
