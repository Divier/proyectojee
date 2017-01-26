package co.com.datatools.c2.negocio.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.AnulacionDTO;
import co.com.datatools.c2.dto.comparendo.AvisoGeneradoDTO;
import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
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
import co.com.datatools.c2.dto.comparendo.SolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudOrdenCompaNacioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoInconsistenciaAgente;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Remote
public interface IRComparendo {

    /**
     * Consulta los comparendos de un organismo de transito segun los parametros enviados
     * 
     * @param consultaComparendoDTO
     *            filtros de busqueda
     * @return listado de comaprendos que cumplen con los criterios de busqueda
     * @author divier.casas
     */
    public List<ComparendoDTO> consultarComparendos(ConsultaComparendoDTO consultaComparendoDTO)
            throws CirculemosNegocioException;

    /**
     * Verifica la existencia del comparendo
     * 
     * @param numeroComparendo
     *            numero del comparendo a buscar
     * @param codigoOrganismo
     *            organismo al que pertenece el comparendo
     * @return true si el comparendo existe, false si no
     * @author felipe.martinez
     */
    boolean existeComparendo(String numeroComparendo, int codigoOrganismo);

    /**
     * Consulta toda la informacion de un comparendo por su id
     * 
     * @param cicomparendo
     *            id del comparendo a retornar
     * @return ComparendoDTO con la informacion del comparendo con el id indicado o CirculemosRuntimeException si no existe
     * @author maria.torres
     */
    ComparendoDTO consultarComparendo(Long cicomparendo);

    /**
     * Medoto que se encarga de verificar si un comparendo esta dentro de un rango valido con el tipo de formulario
     * 
     * @param numeroComparendo
     * @param tipoComparendo
     * @return
     * @author giovanni.velandia
     * @exception CirculemosNegocioException
     */
    public boolean validarFormatoNumeroComparendo(String numeroComparendo, int tipoComparendo)
            throws CirculemosNegocioException;

    /**
     * Consulta comparendos por los filtros indicados y el tipoNotificacion. Si no se envía tipo de notificación retorna los comparendos sin tipo
     * notificación asociada. Al enviarse cualquier otro tipo de modo var-args se retornan los comparendos con dichos tipos de notificación.
     * 
     * @param consultaNotificacionComparendoDTO
     *            filtros de busqueda
     * @param EnumTipoNotificacionCOmparendo
     *            Tipo de notificacion
     * @return Retorna el resultado de la consulta de comparendos por los filtros indicados y el tipoNotificacion
     * @author julio.pinzon
     * @exception CirculemosNegocioException
     */
    public List<ResultadoConsultaNotificacionComparendoDTO> consultarComparendosNotificados(
            ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO,
            EnumTipoNotificacionComparendo... tipoNotificacion) throws CirculemosNegocioException;

    /**
     * Actualiza el tipo de notificación de los comparendos con los números indicados al tipo recibido generando su trazabilidad. Se debe validar
     * que la fecha de notificación sea mayor a la fecha de imposición, que los datos no sean nulos. Activa la cartera si es necesario. Genera el
     * documento de notificación realizada.
     * 
     * @param notificacionComparendo
     *            Objeto con datos de notificacion de un comparendo
     * @return Retorna el archivo de resultado de la notificacion
     * @author julio.pinzon
     * @exception CirculemosNegocioException
     */
    public byte[] notificarComparendos(NotificacionComparendoDTO notificacionComparendo)
            throws CirculemosNegocioException;

    /**
     * Contiene la informacion de la evidencia a la cual se le generara un nombre. No tiene en cuenta el id ni el id del documento.
     * 
     * @param evidencia
     * @return Nombre de archivo de evidencia
     * @throws CirculemosNegocioException
     * @author rodrigo.cruz
     */
    String obtenerNombreEvidencia(EvidenciaDTO evidencia) throws CirculemosNegocioException;

    /**
     * Retorna el listado de evidencias registradas asociandolas al comparendo con el identificador indicado. En su retorno no devuelve el contenido
     * de la evidencia solo su identificador y campos basicos.
     * 
     * @param cicomparendo
     * @param evidencias
     * @return
     * @throws CirculemosAlertaException
     * @throws CirculemosNegocioException
     * @author rodrigo.cruz
     */
    List<EvidenciaDTO> registrarEvidencias(long cicomparendo, List<EvidenciaDTO> evidencias)
            throws CirculemosAlertaException, CirculemosNegocioException;

    /**
     * Elimina el listado de evidencias indicadas que se encuentran asociadas al comparendo con el identificador indicado. Verificando primero que
     * todas se encuentren asociadas al mismo, de lo contrario lanza excepcion.
     * 
     * @param cicomparendo
     * @param idEvidencias
     * @throws CirculemosNegocioException
     * @author rodrigo.cruz
     */
    void eliminarEvidenciasComparendo(long cicomparendo, List<Integer> idEvidencias) throws CirculemosNegocioException;

    /**
     * Consulta la cantidad de Comparendos que no han sido notificados
     * 
     * @param consultarCantidadComparendosNoNotificados
     * @return
     * @author diego.fonseca(mod 2016-02-09)
     */
    Integer consultarCantidadComparendosNoNotificados(
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificados)
            throws CirculemosNegocioException;

    /**
     * Retorna el n�mero de rectificaciones realizadas al comparendo con el identificador indicado. Verifica que el comparendo exista.
     * 
     * @param cicomparendo
     * @return
     * @author giovanni.velandia
     */
    public Integer consultarCantidadRectificaciones(long cicomparendo);

    /**
     * Retorna true si existe al menos un pago asociado al comparendo indicado.
     * 
     * @return
     * @author giovanni.velandia
     */
    public boolean existePago(long cicomparendo);

    /**
     * Genera notificación vía correo certificado
     * 
     * @param consultaCantidadComparendosNoNotificados
     * @return
     * @author diego.fonseca(mod 2016-02-09)
     */
    public long generarNotificacionCorreo(
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificados,
            boolean generarDocumentos, Date fecha) throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * Carga el archivo para el organismo de transito con el codigo indicado. Marcando los comparendos como notificados y activando su cartera. Valida
     * que la estructura del archivo sea correcta.
     * 
     * @param codigoOrganismo
     *            Organismo de transito de los comparendos
     * @param archivo
     *            Archivo Excel (formato XLS o XLSX) con una lista de comparendos por notificar
     * @return El archivo modificado con una observacion por cada comparendo no procesado, el numero de comparendos en el archivo y el numero de no
     *         procesado
     * @throws CirculemosNegocioException
     *             <ul>
     *             <li>Cuando el formato del archivo es diferente de los indicados
     *             <li>Cuando el archivo no cumple la estructura (el encabezado es incorrecto)
     *             <li>Cuando el estado de la cartera del comparendo ya estaba activa
     *             </ul>
     * @author rodrigo.cruz
     */
    ResultadoCargueArchivoNotificacionDTO cargarArchivoNotificacion(int codigoOrganismo,
            ArchivoTransportableDTO archivo) throws CirculemosNegocioException;

    /**
     * Genera resoluciones de sanción asociadas a comparendos que cumplen con los requisitos necesarios: plazo, saldo de cartera, resoluciones
     * generadas, procesos de impugnación, cursos pedagógicos.
     * 
     * @param fechaGeneracion
     *            Fecha de generación de la resolución de sanción
     * @param codigoOrganismoTransito
     *            Codigo Organismo Transito
     * @return Retorna la lista de comparendos a los que se generó resolución de sanción, con un código de respuesta.
     * @throws CirculemosNegocioException
     * @author julio.pinzon
     */
    public RespuestaResolucionComparendoDTO generarResolucionesSancion(Date fechaGeneracion,
            int codigoOrganismoTransito) throws CirculemosNegocioException;

    /**
     * Genera las resoluciones de sanción para el organismo indicado, usando la fecha indicada. Envía un correo notificando el resultado del proceso
     * y genera log.
     * 
     * @param codigoOrganismo
     * @param fechaGeneracion
     * @throws CirculemosNegocioException
     * @author julio.pinzon
     */
    public void generarResolucionSancionAutomatico(int codigoOrganismo, Date fechaGeneracion)
            throws CirculemosNegocioException;

    /**
     * Genera el aviso de edicto para los comparendos impuestos hace x dias y no han sido notificados. El valor de x es parametrizado en el sistema.
     * <b>CU_CIR20_DAT_COM_055 Generar documento de aviso de notificacion para comparendos</b>
     * 
     * @param codigoOrganismo
     *            codigo del organismo del cual se generan las notificaciones.
     * @return Retorna un AvisoGeneradoDTO con el identificador del aviso creado y la cantidad de comparendos de dicho aviso. Valida que existan
     *         comparendos a notificar, si no los hay, escribe en log y lanza excepcion.
     * @throws CirculemosAlertaException
     *             <br>
     *             COM_055001=NO existen Numeros de Comparendos disponibles para notificar por aviso.<br>
     *             COM_055002=Error al intentar generar el documento de la notificacion por aviso.<br>
     * @author luis.forero (2016-02-11)
     * @author diego.fonseca (mod 2016-02-26)
     */
    AvisoGeneradoDTO generarAvisoNotificacion(int codigoOrganismo)
            throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * 
     * Permite consultar y notificar los comparendos que han sido publicados por aviso.
     * 
     * @param codigoOrganismo
     * @author diego.fonseca(mod 2016-02-09)
     */
    public int notificarComparendosAviso(int codigoOrganismo) throws CirculemosNegocioException;

    /**
     * @param codigoOrganismo
     * @param cargarContenido
     * @author diego.fonseca(mod 2016-02-25)
     * 
     *         Permite generar el aviso de notificacion y retorna el archivo de notificacion
     */
    public DocumentoAvisoGeneradoDTO generarAvisoNotificacion(int codigoOrganismo, boolean cargarContenido)
            throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * 
     * @param consultaAvisosNotificacionDTO
     * @author diego.fonseca(mod 2016-02-25)
     * 
     *         Retorna el listado de AvisoNotificacionDTO que cumplan con los filtros recibidos como parámetro
     */
    public List<NotificacionAvisoDTO> consultarAvisosNotificacion(
            ConsultaAvisosNotificacionDTO consultaAvisosNotificacionDTO) throws CirculemosNegocioException;

    /**
     * Consulta la informacion basica del comparendo junto con su trazabilidad ordenada de la mas reciente a la mas antigua. Valida que el comparendo
     * exista.
     * 
     * @param cicomparendo
     *            identificador del comparendo
     * @return Datos del comparendo junto con su respectiva trazabilidad y saldos.
     * @exception CirculemosNegocioException
     *                <br>
     *                COM_016005=El comparendo no fue encontrado en el sistema.<br>
     * @author luis.forero(2016-02-25)
     */
    SeguimientoComparendoDTO consultarSeguimientoComparendo(long cicomparendo) throws CirculemosNegocioException;

    /**
     * Calcula el saldo de un comparendo a capital e intereses asociado a un organismo y una fecha indicados.
     * 
     * @param codigoOrganismo
     *            organismo de transito indicado
     * @param cicomparendo
     *            identificador del comparendo a calcular saldo
     * @param fechaLiquidacion
     *            fecha a la cual se desea saber el saldo del comparendo.
     * @return Retorna el saldo del comparendo a capital e intereses para la fecha indicada.
     * @author divier.casas(2016-04-07)
     */
    public SaldoComparendoDTO calcularSaldoComparendo(int codigoOrganismo, Long cicomparendo, Date fechaLiquidacion)
            throws CirculemosNegocioException;

    /**
     * Consulta el comparendo asociado al organismo transito y numero de comparendo exacto indicados.
     * 
     * @param numeroComparendo
     *            numero del comparendo igresado
     * @param codigoOrganismo
     *            codigo de organismo de transito ingresado
     * @return Retorna el comparendo asociado al organismo transito y numero de comparendo exacto indicados.
     * @author luis.forero(2016-03-16)
     */
    ComparendoDTO consultarComparendo(String numeroComparendo, Integer codigoOrganismo);

    /**
     * 
     * 
     * @param solicitudNumeroComparendoDTO
     * @return
     */
    RespuestaSolicitudNumeroComparendoDTO solicitarNumeroComparendo(
            SolicitudNumeroComparendoDTO solicitudNumeroComparendoDTO) throws CirculemosNegocioException;

    /**
     * Actualiza el estado del envio del comparendo a SIMIT a 'vigente'. Genera trazabilidad en el comparendo acorde a la accion recibida.
     * 
     * @param codigoOrganismo
     *            codigo de organismo donde se aactualiza el comparendo
     * @param cicomparendo
     *            identificador del comparendo a actualizar
     * @param accion
     *            accion ejectuada
     * @author luis.forero(2016-03-30)
     * @throws CirculemosNegocioException
     *             SIM_001101(Comparendo a actualizar notificacion de SIMIT no encontrado)<br>
     *             SIM_001102(Identificador del comparendo notificado no puede ser nulo)<br>
     *             SIM_001103(Organismo de transito no puede ser nulo<br>
     *             SIM_001104(Accion ejecutada no puede ser nula)
     */
    void notificarComparendoSIMIT(Integer codigoOrganismo, Long cicomparendo, EnumAccionComparendo accion)
            throws CirculemosNegocioException;

    /**
     * Retorna true si existe una solicitud con los filtros indicados.
     * 
     * @author giovanni.velandia
     * @param solicitudOrdenCompaNacioDTO
     * @return
     */
    public boolean existeSolicitudOrdenComparendoNacional(SolicitudOrdenCompaNacioDTO solicitudOrdenCompaNacioDTO);

    /**
     * Asigna la fecha de consumo indicada a la asignaci�n asociada al n�mero de comparendo indicado. Valida que: Los par�metros tengan
     * informaci�n. Exista una reserva con dicho n�mero de comparendo Que la reserva no tenga asignada una fecha de consumo.
     * 
     * @author giovanni.velandia
     * @param numeroComparendo
     * @param fechaConsumo
     * @throws CirculemosNegocioException
     */
    public void consumirReservaOCN(String numeroComparendo, Date fechaConsumo) throws CirculemosNegocioException;

    /**
     * Actualiza el estado del comparendo segun los datos enviados
     * 
     * @param cambioEstadoComparendo
     *            dto con datos de cambio de estado
     * @author julio.pinzon 2016-04-28
     */
    public void actualizarEstadoComparendo(CambioEstadoComparendoDTO cambioEstadoComparendo);

    /**
     * Consulta el infractor del comparendo
     * 
     * @param idComparendo
     * @return
     * @author giovanni.velandia MOD 03-08-2016
     */
    public PersonaDTO consultarInfractorComparendo(Long idComparendo);

    /**
     * Consulta las obligaciones de tipo comparendo de un determinado infractor con sus valores respectivos en cartera
     * 
     * @param consultaInfractorDTO
     *            persona con los datos de consulta
     * @param estadosComparendo
     *            Lista de los ids de estados que quiere consultar
     * @return listado de obligaciones del infractor encontradas en el comparendo
     * @author luis.forero mod julio.pinzon 2016-08-23
     * @throws CirculemosNegocioException
     */
    public List<ConsultaObligacionesDTO> consultarComparendosSimulacionFinanciacion(PersonaDTO consultaInfractorDTO,
            List<Integer> estadosComparendo) throws CirculemosNegocioException;

    /**
     * Consulta los comparendos que cumplen con los condiciones para anularlos
     * 
     * @author diego.fonseca
     * @param filtros
     * @return
     */
    public List<ComparendoConsultaAnulacionDTO> consultarComparendoAnulacion(ConsultaComparendoAnulacionDTO filtros)
            throws CirculemosNegocioException;

    /**
     * Registra las anulaciones sobre los comparendos
     * 
     * @author diego.fonseca
     * @param anulacion
     * @throws CirculemosNegocioException
     */
    public void registrarAnulacion(AnulacionDTO anulacion, List<Long> comparendos) throws CirculemosNegocioException;

    /**
     * Registra las anulaciones sobre los comparendos
     * 
     * @author giovanni.velandia
     * @param comparendos
     * @throws CirculemosNegocioException
     */
    public void registrarAnulacion(List<Long> comparendos) throws CirculemosNegocioException;

    /**
     * Consulta la trazabilidad de un comparendo
     * 
     * @param String
     *            numeroComparendo
     * @param Integer
     *            codigoOrganismo
     * @return ComparendoDTO
     * @author divier.casas 2016-07-27
     */
    public ComparendoDTO consultarComparendoTrazabilidad(String numeroComparendo, Integer codigoOrganismo);

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
     * Consulta los comparendos con inconsistencias en el dato de agente impositor
     * 
     * @param idInconsistencia
     * @return lista de comparendos con inconsistencias.
     * @author ricardo.chavarro
     */
    public List<ComparendoAgenteInconsistenteDTO> consultarComparendosIncosistenteAgente(
            EnumTipoInconsistenciaAgente inconsistencia, Date fechaInicialImposicion, Date fechaFinalImposicion)
            throws CirculemosNegocioException;

    /**
     * Actualiza los agentes de comparendos incosistentes
     * 
     * @param comparendos
     *            a actualizar
     * @param agente
     *            a establecer
     * @author ricardo.chavarro
     */
    public ArrayList<Integer> actualizarAgenteComparendoMasivo(List<ComparendoDTO> comparendos, AgenteDTO agente)
            throws CirculemosNegocioException;

    /**
     * Permite actualizar la fecha de notificacion de un comparendo
     * 
     * @param ciComparendo
     * @param codigoOrganismo
     * @param fechaNotificacion
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