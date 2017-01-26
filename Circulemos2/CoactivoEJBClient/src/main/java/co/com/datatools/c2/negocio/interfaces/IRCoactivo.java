package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.AprobacionPrecoactivoDTO;
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
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Remote
public interface IRCoactivo {
    /**
     * Registra un coactivo de acuerdo a una lista de obligaciones
     * 
     * @param obligaciones
     * @param configuracionCoactivo
     * @param valoresConfiguracion
     * @throws CirculemosNegocioException
     * @author diego.lozano
     */
    public void registrarCoactivo(List<ObligacionCoactivoDTO> obligaciones,
            ConfiguracionCoactivoDTO configuracionCoactivo,
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion) throws CirculemosNegocioException;

    /**
     * Registra las obligaciones como Coactivo, provenientes del Job
     * 
     * @param codigoOrganismo
     * @return
     * @author julio.pinzon 2016-08-04
     */
    public Integer registrarCoactivoJob(Integer codigoOrganismo);

    /**
     * Lleva a cabo la consulta de seguimiento de cobros coactivos.
     * 
     * @param ConsultaSeguimientoCoactivoDTO
     *            consulta
     * 
     * @return List<RespuestaSeguimientoDTO>
     * @author divier.casas(2016-08-03)
     */
    List<RespuestaSeguimientoDTO> consultarSeguimientosCoactivo(ConsultaSeguimientoCoactivoDTO consulta)
            throws CirculemosNegocioException;

    /**
     * Lleva a cabo la consulta de la trazabilidad de seguimiento de cobros coactivos.
     * 
     * @param Long
     *            idProceso
     * 
     * @return List<RespuestaTrazabilidadDTO>
     * @author divier.casas(2016-08-03)
     */
    List<RespuestaTrazabilidadDTO> consultarTrazaSeguimiento(Long idProceso);

    /**
     * Consulta las obligaciones que estan en cobro coactivo
     * 
     * @param obligacionCoactivoDTO
     *            Filtros de la consulta
     * @return List<ObligacionCoactivoDTO>
     * @author Dixon.Alvarez 2016-08-05
     */
    List<ObligacionCoactivoDTO> consultarObligacionesCoactivo(ObligacionCoactivoDTO obligacionCoactivoDTO);

    /**
     * genera los documentos de coactivo metodo toca eliminar ojo
     * 
     * @author Jeison.Rodriguez
     */
    public void generarDocumentosCoactivosAnteriores();

    /**
     * Consulta los coactivos y los envia al sevicio de axis
     * 
     * @throws CirculemosNegocioException
     * @author Jeison.Rodriguez
     */
    public void consultarCoactivosXLS() throws CirculemosNegocioException;

    /**
     * Metodo que recorre y valida el archivo cargado
     * 
     * @param archivoTransportableDTO
     * @param configuracionCoactivoDTO
     * @return CargueArchivoDTO contiene los datos de las multas validadas
     * @author Dixon.Alvarez
     */
    public Future<CargueArchivoDTO> cargarArchivoCoactivos(CargueArchivoDTO cargueArchivo,
            ArchivoTransportableDTO archivoTransportableDTO, ConfiguracionCoactivoDTO configuracionCoactivoDTO)
            throws CirculemosNegocioException;

    /**
     * Consulta los precoactivos de acuerdo a los filtros
     * 
     * @param precoactivo
     * @param estados
     * @return Lista de dto de precoactivo
     * @author julio.pinzon 2016-09-29
     */
    public List<PrecoactivoDTO> consultarPrecoactivo(PrecoactivoDTO precoactivo, List<EnumEstadoPrecoactivo> estados);

    /**
     * Consulta los precoactivos pendientes de aprobacion
     * 
     * @return Lista de dto de precoactivo
     * @author julio.pinzon 2016-09-29
     */
    public List<AprobacionPrecoactivoDTO> consultarPrecoactivoAprobacion();

    /**
     * Cambia el estado de los precoactivos asociados a las obligaciones Se utiliza para autorizar o no autorizar coactivos
     * 
     * @param precoactivos
     * @param secretario
     *            Secretario asociado a los coactivos
     * @author julio.pinzon 2016-09-30
     */
    public void autorizarPrecoactivos(List<AprobacionPrecoactivoDTO> precoactivos, FuncionarioDTO secretario);

    /**
     * Consulta los procesos coactivos con los datos recibidos en el DTO
     * 
     * @param coactivo
     * @return procesos coactivos consultados
     * @author ricardo.chavarro
     */
    public List<CoactivoDTO> consultarCoactivos(CoactivoDTO coactivo);

    /**
     * Procesa el archivo xls con las notificaciones de procesos coactivos entregado por el courier
     * 
     * @param archivoDTO
     * @throws CirculemosNegocioException
     * @author ricardo.chavarro
     */
    public CargueNotificaCoactivoResulDTO procesarArchivoNotificacionesCoactivos(ArchivoTransportableDTO archivoDTO)
            throws CirculemosNegocioException;

    /**
     * Registra un cargue con sus respectivos precoactivos
     * 
     * @param cargueArchivoDTO
     *            Contiene la informacion a persistir
     * @param aceptar
     *            Indica si continua el proceso o no
     * @author julio.pinzon 2016-12-06
     */
    public void modificarCarguePrecoactivos(CargueArchivoDTO cargueArchivoDTO, boolean aceptar);

    /**
     * Registro de radicar excepciones
     * 
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public void radicarExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosNegocioException;

    /**
     * Registro de fallo excepciones
     * 
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public void falloExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosNegocioException;

    /**
     * Validar radicacion excepciones
     * 
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     * @param idCoactivo
     * 
     */
    public void validarRadicacionExcepciones(Long idCoactivo) throws CirculemosNegocioException;

    /**
     * 
     * Validar fallo excepciones
     * 
     * @author giovanni.velandia
     * @param idRadicarExcepcion
     * @throws CirculemosNegocioException
     */
    public void validarFalloExcepciones(Long idRadicarExcepcion) throws CirculemosNegocioException;

    /**
     * 
     * Consultar radicacion excepcion
     * 
     * @author giovanni.velandia
     * @param idCoactivo
     * @return
     */
    public RadicarExcepcionDTO consultarRadicarExcepcion(Long idCoactivo);

    /**
     * Consulta coactivo por numero de coactivo
     * 
     * @param numeroCoactivo
     * @return
     */
    public CoactivoDTO consultarCoactivo(String numeroCoactivo);

    /**
     * Consulta datos de la solicitud de un bien
     * 
     * @param filtroCoactivoDTO
     * @return
     */
    public List<RespuestaCoactivoDTO> consultarCoactivo(FiltroCoactivoDTO filtroCoactivoDTO);

    /**
     * Genrar oficio de la tabla intemedia de apertura de coactivos
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    public void generarOficioBien() throws CirculemosNegocioException;

    /**
     * Consulta las solicitudes de oficio de un coactico que no esten registrados
     * 
     * @author giovanni.velandia
     * @param idCOactivo
     * @return List<SolicitudOficioCoactivoDTO>
     */
    public List<SolicitudOficioCoactivoDTO> consultarSolicitudOficioCoactivo(Long idCoactivo);

    /**
     * Consulta las solicitudes de oficio de un coactico que no esten registrados
     * 
     * @author giovanni.velandia
     * @param idCOactivo
     * @param numeroOficio
     * @return SolicitudOficioCoactivoDTO
     */
    public SolicitudOficioCoactivoDTO consultarSolicitudOficioCoactivo(Long idCoactivo, String numeroOficio);
}