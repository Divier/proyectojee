package co.com.datatools.c2.negocio.fachada;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dt.administracion.ConsultaDireccionDTO;
import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.negocio.interfaces.IRDiaNoHabil;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;

@Remote
public interface IRFachadaAdminGeneral {

    /**
     * Metodo que se encarga de retornar la fecha con la cantidad de dias sean habiles o no para un organismo de transito
     * 
     * @param codigoOrganismo
     * @param fecha
     * @param cantidadDias
     * @param diaHabil
     * @return
     * @author giovanni.velandia
     */
    public Date sumarDias(int codigoOrganismo, Date fecha, int cantidadDias, boolean diaHabil);

    /**
     * Consulta los valores de un parametro para un organismo, si no encuentra informacion del organismo busca la informacion por defecto del
     * parametro
     * 
     * @param enumParametro
     * @param codigoOrganismo
     * @param requerido
     * @return {@link ValorParametroDTO}
     */
    public ValorParametroDTO consultarValorParametro(EnumParametro enumParametro, int codigoOrganismo,
            boolean requerido);

    /**
     * Se encarga de validar un numero de documento de una persona
     * 
     * @param numeroDocumento
     * @param idFormatoDocumento
     * @return
     * @author giovanni.velandia 09-11-2015
     */
    public boolean validarNumeroDocumento(String numeroDocumento, int idTipoDocumento, Date fechaValidacion)
            throws CirculemosNegocioException;

    /**
     * Retorna null si la direccion indicada es valida. De lo contrario retorna el codigo de respuesta.
     * 
     * @param direccion
     *            Direccion a validar
     * @return DTO con el resultado del proceso. Si no existen errores la respuesta es OK, de lo contrario aparece una lista de errores por cada campo
     *         en {@link RespuestaDTO#getErroresCampo()}
     * @author rodrigo.cruz
     */
    RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> validarDireccion(DireccionDTO direccion,
            EnumTipoValidacionDireccion enumTipoValidacionDireccion);

    /**
     * Permite registrar una direccion en el sistema con fuente de informacion Circulemos 2. Invoca el servicio de negocio de
     * {@link ILDireccion#ingresarDireccion(DireccionDTO)}
     * 
     * @param direccionDTO
     * @return
     * @author giovanni.velandia
     */
    public RespuestaIngresarDireccionDTO registrarDireccion(DireccionDTO direccionDTO,
            EnumTipoValidacionDireccion enumTipoValidacionDireccion);

    /**
     * Genera un consecutivo para el organismo indicado de un tipo determinado. El tipo de consecutivo es var-args en caso de no enviar tipo se
     * utilizar el tipo EnumConsecutivo.GENERAL
     * 
     * @param codigoOrganismo
     * @param tipoConsecutivo
     * @return Devuelve el consecutivo
     * @author julio.pinzon
     */
    public String generarConsecutivo(int codigoOrganismo, EnumConsecutivo... tipoConsecutivo);

    /**
     * Consulta una direccion segun el id de Direccion que tenga ConsultaDireccionDTO
     * 
     * @param consultaDireccionDTO
     * @return
     */
    public List<DireccionDTO> consultarDireccion(ConsultaDireccionDTO consultaDireccionDTO);

    /**
     * Consulta el web service asociado junto con sus datos correspondientes.
     * 
     * @param idWebService
     *            identificador del webservice a ser consultado
     * @return Retorna el web service asociado al identificador del web service indicado como parametro.
     */
    public WebServiceDTO consultarWebService(Integer idWebService);

    /**
     * Consulta las respuestas posibles que retorna un web service
     * 
     * @param idWebService
     *            identificador del web service a obtener las respuestas posibles
     * @return Retorna un listado de respuestas correspondientes a un determinado web service configurado en el sistema.
     * @author luis.forero(2016-04-06)
     */
    public List<RespuestaWebServiceDTO> consultarRespuestasWebService(Integer idWebService);

    /**
     * Retorna el listado de {@link DetalleFuenteConsultaDTO} asociados al organismo de transito y tipo de fuente de consulta indicados. Valida que
     * los parametros tengan algun valor.
     * 
     * @param codigoOrganismo
     * @param idTipoFuenteConsulta
     * @return Listado de {@link DetalleFuenteConsultaDTO} asociados al organismo de transito y tipo de fuente de consulta indicados
     * @author rodrigo.cruz
     */
    public List<DetalleFuenteConsultaDTO> consultarDetalleFuenteConsulta(int codigoOrganismo, int idTipoFuenteConsulta);

    /**
     * @see IRDiaNoHabil#esDiaNoHabil(int, Date)
     */
    public boolean esDiaNoHabil(int codigoOrganismo, Date fecha);

    /**
     * Registra el log de la ejecucion de un Web Service
     * 
     * @param log
     * @return
     */
    public LogEjecucionWSDTO registrarLogWS(LogEjecucionWSDTO log);
}
