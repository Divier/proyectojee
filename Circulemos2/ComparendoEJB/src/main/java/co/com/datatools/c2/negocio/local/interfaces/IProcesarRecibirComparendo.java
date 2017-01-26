package co.com.datatools.c2.negocio.local.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaRecibirComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.configuracion.AsociaComparendoFormularioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.enumeracion.EnumOrigenNotificacionTercero;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;

/**
 * Interfaz Local de recibir comparendo
 * 
 * @author giovanni.velandia
 */
@Local
public interface IProcesarRecibirComparendo {

    /**
     * @see IRRecibirComparendo#comparendoIngresado(String, Integer)
     */
    boolean comparendoIngresado(String numeroComparendo, Integer codigoOrganismo);

    /**
     * @see IRRecibirComparendo#validarRectificaComparendo(Long)
     */
    public RespuestaValidacionDTO validarRectificaComparendo(Long cicomparendo);

    /**
     * @see IRRecibirComparendo#rectificarComparendo(ProcesaComparendoRectificadoDTO)
     */
    public RespuestaValidacionDTO rectificarComparendo(ProcesaComparendoRectificadoDTO comparendoRectificadoDTO)
            throws CirculemosNegocioException;

    /**
     * Metodo que se encarga de validar el numero de comparendo
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @param configuracionInfraccionDTO
     * @return
     * @throws CirculemosNegocioException
     */
    public boolean validarFormatoNumeroComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException;

    /**
     * Metodo que se encarga de liquidar tarifa Infraccion
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @param configuracionInfraccionDTO
     * @return
     */
    public TarifaLiquidacionDTO liquidarTarifaInfraccion(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de consultar un parametro del sistema
     * 
     * @return
     */
    public ValorParametroDTO consultarValorParametro(EnumParametro enumParametro, Integer codOrganismoTransito);

    /**
     * Se encarga de armar la respuesta para un rechazo
     * 
     * @param enumCamposDetalleComparendo
     * @param enumErrorProcesamiento
     * @author giovanni.velandia
     */
    public void errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo enumCamposDetalleComparendo,
            EnumErrorProcesamiento enumErrorProcesamiento, RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Metodo que se encarga de validar campos que son necesario para la consulta de la configuracion de infraccion sin estos campos no se puede
     * continuar con el procesamiento
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @return
     */
    public void validacionesCamposProcesarComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Se encarga de consultar la configuraccion de infraccion
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @return
     */
    public ConfiguracionInfraccionDTO consultarConfiguracionInfraccion(ProcesaComparendoDTO procesaComparendoDTO);

    /**
     * Metodo que se encarga solo de validar los campos de procesar comparendo
     * 
     * @param procesarComparendoDTO
     * @return
     * @author giovanni.velandia
     */
    public void validacionesCamposProcesarComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, RespuestaValidacionDTO respuestaValidacionDTO)
            throws CirculemosNegocioException;

    /**
     * Se encarga de hacer las validaciones de origen del comparendo
     * 
     * @param procesarComparendoDTO
     * @return
     * @author giovanni.velandia
     */
    public void origenValidacion(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Metodo que se encarga de escribir en el log las transacciones de un comparendo
     * 
     * @param enumErrorProcesamiento
     * @param procesarComparendoDTO
     * @param respuestaValidacionDTO
     * @author giovanni.velandia
     */
    public void crearLogC2(EnumErrorProcesamiento enumErrorProcesamiento, ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Metodo que se encarga de registrar los errores de rechazo al recibir un proce4samiento de comparendo
     * 
     * @author giovanni.velandia
     */
    public void registroDetalleBloqueo(RespuestaValidacionDTO respuestaValidacionDTO,
            ProcesaComparendoDTO procesaComparendoDTO);

    /**
     * Metodo que se encarga solo de validar las reglas de negocio
     * 
     * @param procesarComparendoDTO
     * @return
     * @author giovanni.velandia
     */
    public RespuestaRecibirComparendoDTO validarReglasComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de crear un detalle procesamiento para los errorres al recibir un comparendo
     * 
     * @param idCampoEntidad
     * @return
     * @author giovanni.velandia
     */
    public DetalleProcesamientoDTO crearDetalleProcesamiento(EnumCamposDetalleComparendo enumCamposDetalleComparendo,
            EnumErrorProcesamiento enumErrorProcesamiento);

    /**
     * Consulta un procesa comparendo por id y retorna el resultado en una entidad
     * 
     * @param idProcesaComparendo
     * @return
     * @author giovanni.velandia
     */
    public ProcesaComparendoDTO consultarProcesaComparendo(Long idProcesaComparendo);

    /**
     * Metodo que se encarga de registrar un procesar comparendo con todas sus trazabilidades, validando la informacion de detalle proceso
     * 
     * @param procesarComparendoDTO
     * @author giovanni.velandia
     * @param respuestaValidacionDTO
     */
    public Long registrarProcesaComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Metodo que se encarga de registrar un comparendo con todas sus trazabilidades, validando la informacion de detalle proceso
     * 
     * @param procesarComparendoDTO
     * @author giovanni.velandia
     * @param respuestaValidacionDTO
     * @throws CirculemosAlertaException
     * @throws CirculemosNegocioException
     */
    public ComparendoDTO registrarComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO, ConfiguracionInfraccionDTO configuracionInfraccionDTO);

    /**
     * // Verificamos si el comparendo fue reservado solo cuando el origen sea diferente de null
     * 
     * @author giovanni.velandia
     */
    public boolean verificarComparendoResevado(String numeroComparendo);

    /**
     * 
     * Consulta reserva OCN
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @throws CirculemosNegocioException
     */
    public void consumirReservaOCN(ProcesaComparendoDTO procesaComparendoDTO) throws CirculemosNegocioException;

    /**
     * Metodo que se encarga de verificar cuales fueron los cambios realizados en el comparendo
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @param enumActividadReg
     * @param enumActividadImpo
     * @return
     */
    public ComparendoDTO rectificacionesComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            EnumActividad enumActividadReg, EnumActividad enumActividadImpo);

    /**
     * Actualiza el procesa comparendo
     * 
     * @param procesarComparendoDTO
     * @author giovanni.velandia
     */
    public void actualizarProcesaComparendo(ProcesaComparendoDTO procesaComparendoDTO);

    /**
     * Metodo que se encarga de generar cartera
     * 
     * @author giovanni.velandia
     * @throws CirculemosNegocioException
     */
    public void generarCartera(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, ComparendoDTO comparendoDTO)
            throws CirculemosNegocioException;

    /**
     * Se encarga de cambiar el estado de un formulario
     * 
     * @param procesarComparendoDTO
     * @param configuracionInfraccionDTO
     * @throws CirculemosNegocioException
     */
    public void registroCambioEstadoformulario(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException;

    /**
     * Registro de notificaciones
     * 
     * @param comparendo
     * @param procesaComparendoDTO
     * @param configuracionInfraccionDTO
     * @throws CirculemosNegocioException
     */
    public void registroNotificaciones(ComparendoDTO comparendoDTO, ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException;

    /**
     * Registro de notificaciones por pedio de la consulta a la configuracion de notificacion
     * 
     * @param comparendo
     * @param procesarComparendoDTO
     * @param configuracionInfraccionDTO
     * @throws CirculemosNegocioException
     */
    public void registroNotificacionesConfiguracion(ComparendoDTO comparendoDTO,
            ProcesarComparendoDTO procesarComparendoDTO, ConfiguracionInfraccionDTO configuracionInfraccionDTO)
            throws CirculemosNegocioException;

    /**
     * Realiza la actualizacion de un comparendo
     * 
     * @param comparendoDTO
     * @author giovanni.velandia
     */
    public void actualizarComparendo(ComparendoDTO comparendoDTO);

    /**
     * Notifica el comparendo a terceros
     * 
     * @param comparendo
     * @param origenNotificacion
     * @author giovanni.velandia
     */
    public void notificarComparendoTerceros(Long cicomparendo, Integer codigoOrganismo,
            EnumOrigenNotificacionTercero origenNotificacion);

    /**
     * Para el caso de recibir comparendo verificamos que no se encuentre almacenado en procesar comparendo
     * 
     * @author giovanni.velandia
     * @return
     */
    public void validarExistenciaComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Se encarga de varificar la existencia del comparendo
     * 
     * @param procesaComparendoDTO
     * @return
     */
    public boolean existeComparendo(ProcesaComparendoDTO procesaComparendoDTO);

    /**
     * obtiene el ususario de seguridad
     * 
     * @author giovanni.velandia
     * @return
     */
    public UsuarioPersonaDTO obtenerUsuario();

    /**
     * Datos de la configuracion 008 creacion de DTO con campos de entrada (Tipo comparendo) y salida (Tipo formulario)
     * 
     * @author giovanni.velandia
     * @param configuracionInfraccionDTO
     * @throws CirculemosNegocioException
     */
    public AsociaComparendoFormularioDTO consultarConfiguracion008(
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException;

    /**
     * Se verifica la existencia del formulario
     * 
     * @author giovanni.velandia
     * @param numeroFormulario
     * @param idTipoFormulario
     * @return
     */
    public boolean existeFormulario(String numeroFormulario, int idTipoFormulario);

    /**
     * se verifica el estado formulario organismo
     * 
     * @author giovanni.velandia
     * @param numeroFormulario
     * @param idTipoFormulario
     * @param idCodigoOrganismo
     * @param enumEstadoFomulario
     * @return
     */
    public boolean existeEstadoFormularioOrganismo(String numeroFormulario, Integer idTipoFormulario,
            Integer idCodigoOrganismo, EnumEstadoFomulario... enumEstadoFomulario);

    /**
     * valida los campos de los datos de el agente
     * 
     * @param procesaComparendoDTO
     * @return {@link RespuestaValidacionDTO}
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public void validarCamposAgente(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, RespuestaValidacionDTO respuestaValidacionDTO)
            throws CirculemosNegocioException;

    /**
     * Validar existe agente si no es polca
     * 
     * @author giovanni.velandia
     */
    public void validarAgentePolca(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * 
     * {@link IRFachadaAdminGeneral#validarDireccion(co.com.datatools.c2.dto.comun.DireccionDTO)}. La direccion no es valida se agrega un resultado de
     * {@link EnumErrorProcesamiento#INCONSISTENTE}.
     * 
     * @param procesaDireccionDTO
     * @author rodrigo.cruz - giovanni.velandia(mod 08/04/2016)
     */
    public void validarDirecciones(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO);

    /**
     * Se encarga de devoler un agente del sistema
     * 
     * @author giovanni.velandia
     * @param agenteDTO
     * @return
     */
    public AgenteDTO consultarAgente(AgenteDTO agenteDTO);

    /**
     * Se encarga de devoler una persona del sistema
     * 
     * @author giovanni.velandia
     * @param organismoTransito
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    public PersonaDTO consultarPersona(int organismoTransito, int tipoDocumento, String numeroDocumento)
            throws CirculemosNegocioException;

    /**
     * Se encarga de devoler una persona del sistema
     * 
     * @author giovanni.velandia
     * @param tipoIdentificacion
     * @param numeroIdentificacion
     * @return
     */
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion);

    /**
     * Consulta el organismo de transito en el sistema
     * 
     * @author giovanni.velandia
     * @param codigoOrganismoTransito
     * @return
     */
    public OrganismoTransitoDTO consultarOrganismoTransito(Integer codigoOrganismoTransito);

    /**
     * Verifica la existencia de la infraccion por el codigo
     * 
     * @author giovanni.velandia
     * @param codigoInfraccion
     * @return
     */
    public boolean existeInfraccion(String codigoInfraccion);
}
