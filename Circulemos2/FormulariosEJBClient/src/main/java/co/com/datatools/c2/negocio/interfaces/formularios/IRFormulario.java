package co.com.datatools.c2.negocio.interfaces.formularios;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.formularios.AsignacionDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.CantidadRangoDTO;
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

@Remote
public interface IRFormulario {

    /**
     * Se encarga de persistir un nuevo rango de formulario en el sistema
     * 
     * @param rangoFormularioDTO
     *            Dto con los datos del rango a guardar
     * @throws CirculemosNegocioException
     * 
     * @author claudia.rodriguez
     * 
     * @throws CirculemosNegocioException
     *             ADM_030001: No hay numeraciones vigentes para la fecha de autorizacion ingresada <br>
     *             ADM_030002: El numero final debe ser mayor al inicial <br>
     *             ADM_030003: No existe una numeración configurada para el rango de números ingresados<br>
     *             ADM_030004: El rango ingresado ya esta registrado o hace parte de otro rango registrado en el sistema
     */
    void registrarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException,
            CirculemosAlertaException;

    /**
     * Se encarga de consultar los rangos que cumplen con los valores recibidos en el DTO de consulta
     * 
     * @param consultaRangoFormularioDTO
     * @return Lista con los Dtos de los rangos consultados
     * @throws CirculemosNegocioException
     *             ADM_030005 Los números inicial y final deben tener el mismo formato de numeración
     * @author claudia.rodriguez
     */
    List<RangoFormularioDTO> consultarRangoFormulario(ConsultaRangoFormularioDTO consultaRangoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Retorna los rangos de formularios que tengan disponibilidad mayor a cero (0) y que cumplan con los filtros indicados.
     * 
     * @param rangoFormularioDTO
     *            Filtros de busqueda
     * @return Lista de rangos
     * @author rodrigo.cruz
     */
    List<RangoFormularioDTO> consultarRangosFormularioDisponibles(RangoFormularioDTO rangoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Cantidad de formularios existentes
     * 
     * @param formularioDTO
     * @return Retorna la cantidad de formularios existentes acorde a los filtros de estado de formulario, responsable de formulario, organismo de
     *         transito, tipo de formulario.
     * @author rodrigo.cruz
     */
    int consultarCantidadFormularios(FormularioDTO formularioDTO);

    /**
     * Realiza la asignacion de formularios indicada en {@code asignacionDTO} sobre cada rango de formularios con el identificador indicado en orden
     * secuencial. Si el formulario se encontraba en estado pendiente por asignar o devuelto debe actualizar la cantidad disponible del rango al cual
     * pertenece. Se deben agrupar los formularios por {@link DetalleCambioEstado} de manera tal que se generen tantas como grupos de formularios
     * rangos consecutivos se encuentren (intervalos).
     * 
     * @param asignacionDTO
     *            Contiene: <br>
     *            <ul>
     *            <li>List<{@link CantidadRangoDTO}>: cada DTO tiene un id de {@link Rango} asociado a una cantidad de formularios a asignar
     *            <li>{@link DetalleCambioEstadoDTO}: informacion general de la asignacion del formulario
     * @return La cantidad de formularios a los cuales se les asocia el estado asignado
     * @throws CirculemosAlertaException
     *             Al intentar registrar un documento
     * @author rodrigo.cruz
     */
    int asignarFormularios(AsignacionDTO asignacionDTO) throws CirculemosAlertaException;

    /**
     * Calcula y retorna la cantidad de formularios contenidos en los rangos recibidos como parametro.
     * 
     * @param rangoDTO
     * @return Cantidad de formularios contenidos en los rangos recibidos como parametro.
     * @throws CirculemosNegocioException
     * @author rodrigo.cruz giovanni.velandia(mod 22-09-2015)
     */
    int calcularFormularios(ConsultaRangoTipoFormularioDTO consultaRangoTipoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Elimina un rango de formularios que se encuentre en estado pendiente por asignar
     * 
     * @param idRangoFormulario
     *            Id del rango a eliminar
     * @throws CirculemosNegocioException
     *             ADM_030006 El rango no puede ser eliminado o editado, solo los rangos en estado: Pendiente por asignar, pueder ser editados o
     *             eliminados
     * @author claudia.rodriguez
     */
    void eliminarRangoFormulario(Long idRangoFormulario) throws CirculemosNegocioException;

    /**
     * Actualiza un rango de formularios
     * 
     * @param rangoFormularioDTO
     *            Dto con los datos del rango a actualizar
     * @throws CirculemosNegocioException
     *             ADM_030001: No hay numeraciones vigentes para la fecha de autorizacion ingresada <br>
     *             ADM_030002: El numero final debe ser mayor al inicial <br>
     *             ADM_030003: No existe una numeración configurada para el rango de números ingresados<br>
     *             ADM_030004: El rango ingresado ya esta registrado o hace parte de otro rango registrado en el sistema ADM_030006:El rango no puede
     *             ser eliminado o editado, solo los rangos en estado: Pendiente por asignar, pueder ser editados o eliminados
     * @author claudia.rodriguez
     */
    void actualizarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException,
            CirculemosAlertaException;

    /**
     * Consulta el detalle de un rango de formulario: sus datos y numeros de formularios especificos relacionados
     * 
     * @param idRangoFormulario
     *            Id del rango para el cual se va a consultar el detalle
     * @return Dto del rango con toda su informacion incluyendo el listado de los formularios puntuales
     * @author claudia.rodriguez
     */
    RangoFormularioDTO consultarDetalleRangoFormulario(Long idRangoFormulario);

    /**
     * Obtiene la lista de todos los numeros de formulario contenidos entre el numero inicial y numero final enviado,de acuerdo a la configuracion de
     * la numeracion correspondiente
     * 
     * @param numeroInicial
     *            NUmero inicial del rango de formularios
     * @param numeroFinal
     *            Numero final del rango de formularios
     * @param numeracionDTO
     *            Contiene la configuracion de numeracion valida para los numeros inicial y final del rango enviado
     * @return Lista con los numeros de formularios contenidos en el rango
     * @author claudia.rodriguez
     */
    List<String> listarNumerosRango(String numeroInicial, String numeroFinal, NumeracionFormularioDTO numeracionDTO);

    /**
     * Se encarga de consultar de acuerdo a los filtros, la configuración de stock por tipo de responsable de los formularios
     * 
     * @param stockTipoResponsableDTO
     * @return Listado de lo dtos de StockTipoResponsable consultados
     * 
     * @author claudia.rodriguez giovanni.velandia (mod 2015-08-20)
     */

    List<StockTipoResponsableDTO> consultarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO);

    /**
     * Se encarga de regisdtrar una nueva configuracion de strock por tipo de formulario y tipo de asignacion.<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_017</b>
     * 
     * @param stockTipoResponsableDTO
     *            estructura con los datos a registrar del stock de tipos de asignacion de formularios
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_017001=Tipo de Formulario es obligatorio no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017002=Tipo de Asignacion de formulario es obligatorio no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017003=Rango Minimo es obligatorio, no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017004=Rango Maximo es obligatorio, no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017005=Valor maximo de stock NO acepta valores negativos Debe ser MAYOR o IGUAL a el rango minimo. No se completo la operacion<br>
     *             NUM_017006=No es posible realizar la operacion debido a que los datos ya se encuentran registrados para ese tipo de formulario y
     *             tipo de asignacion seleccionados<br>
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-20)
     */
    void registrarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * Se encarga de actualizar los valores de una configuracion existente en el sistema.<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_017</b>
     * 
     * @param stockTipoResponsableDTO
     *            estructura con los datos a actualizar del stock
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_017001=Tipo de Formulario es obligatorio no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017002=Tipo de Asignacion de formulario es obligatorio no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017003=Rango Minimo es obligatorio, no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017004=Rango Maximo es obligatorio, no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017005=Valor maximo de stock NO acepta valores negativos Debe ser MAYOR o IGUAL a el rango minimo. No se completo la operacion<br>
     *             tipo de asignacion seleccionados<br>
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-21)
     */
    public void actualizarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * Se encarga de eliminar un regtistro ya existente en el sistema cuyo id es el recibido como parametro<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_017</b>
     * 
     * @param idStockTipoResponsable
     *            identificador unico del registro a eliminar
     * @author luis.forero(2015-01-30) giovanni.velandia (mod 2015-08-21)
     */
    void eliminarStockTipoResponsable(Integer idStockTipoResponsable);

    /**
     * Se encarga de actualizar el estado recibido en el parametro de formularios, con los datos recibidos en el objeto de seguimientoFormulario
     * (Estado, observaciones,, detalle, documento, usuario, fechas, etc)
     * 
     * @param seguimientoFormularios
     *            Contiene la informacion de los formularios con su respectivo seguimiento de cambio de estado
     * @param validarResponsable
     *            Indica si se debe validar que el formulario tenga un responsable
     * @param estadoSiguiente
     *            Estado siguiente al cual pasara el formulario
     * @param validarConfiguracionEstados
     *            Indica si se debe validar la configuración de estados para cambiar de un estado a otro
     * @param registraRango
     *            Indica si la lista de seguimiento Formularios corresponde a todo un rango o a formularios especificos de un rango
     * @throws CirculemosNegocioException
     *             NUM_001001:Existen números entre el número inicial y número final que no estan asignados al organismo de tránsito<br>
     *             NUM_001002:No es posible realizar el cambio de estado a los formularios ingresados, revisar la configuración de relaciones de
     *             estados para este tipo de formulario
     * @throws CirculemosAlertaException
     *             NUM_001018:No es posible enviar correo al responsable
     * 
     * @author claudia.rodriguez
     * 
     */
    // Commented unused Sergio Torres
    // void cambiarEstadoFormulario(List<SeguimientoFormularioDTO> seguimientoFormularios, boolean validarResponsable,
    // EnumEstadoFomulario estadoSiguiente, boolean validarConfiguracionEstados, boolean registraRango)
    // throws CirculemosNegocioException, CirculemosAlertaException;

    /**
     * Obtiene los numeros de formulario para un tipo de formulario a partir de un numero inicial que pueden ser asignados segun la configuracion de
     * stock maximo de asignacion por tipo de asignacion
     * 
     * @param numeroInicial
     *            Numero desde el cual se van a obtener los numeros de formulario
     * @param codigoOrganismo
     *            Codigo del organismo de transito
     * @param idTipoFormulario
     *            Id del tipo de formulario
     * @param idTipoAsignacion
     *            Id del tipod e asignacion
     * @return Listado con los numeros generados
     * 
     * @throws CirculemosNegocioException
     *             NUM_001003: No es posible calcular el numero final porque no esta configurado el stock para el tipo de asignacion <br>
     *             NUM_001004: El número inicial ingresado no esta asignado al organismo de transito
     * 
     * @author claudia.rodriguez
     */
    // Commented unused Sergio torres 17-Dic-2015
    // List<String> listarNumerosFormularioAsignacion(String numeroInicial, Integer codigoOrganismo,
    // Integer idTipoFormulario, Integer idTipoAsignacion) throws CirculemosNegocioException;

    /**
     * Realiza la consulta de los cambios de estado de formularios con los parametros enviados
     * 
     * @param consultaDetalleCambioEstadoDTO
     *            DTO con los filtros de la consulta
     * @return Listado de DetalleCambioEstadoDTO
     * @throws CirculemosNegocioException
     *             NUM_001006: Los números inicial y final deben tener el mismo formato de numeración<br>
     *             NUM_001007: El número final debe ser mayor o igual al número inicial
     */
    List<DetalleCambioEstadoDTO> consultarDetalleCambioEstado(
            ConsultaDetalleCambioEstadoDTO consultaDetalleCambioEstadoDTO) throws CirculemosNegocioException;

    /**
     * 
     * Realiza la consulta del detalle cambio estado por id
     * 
     * @param codDetalleCambioEstado
     * @return DetalleCambioEstadoDTO
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public DetalleCambioEstadoDTO consultarDetalleCambioEstado(Long codDetalleCambioEstado)
            throws CirculemosNegocioException;

    /**
     * Consulta los siguimientos de formularios asociados a un detalle cambio estado
     * 
     * @param idDetalleCambioEstado
     *            Id del detalle cambio estado para el cual se van a consultar los formularios
     * @return Listado de dtos de los seguimientos de formularios consultados
     */
    // Commented unused Sergio Torres 17/dic/2015
    // List<SeguimientoFormularioDTO> consultarFormulariosDetalleCambio(Long idDetalleCambioEstado);

    /**
     * Elimina un registro de detalle_cambio_estado siempre y cuando todos los formularios contenidos en el rango del det cambio estado se encuentren
     * en el estado del detalle_cambio_estado, es decir no hayan sufrido algun cambio de estado despues del seguimiento agregado por dicho detalle
     * 
     * @param idDetalleCambioEstado
     *            Id del detalle de cambio de estado a eliminar
     * @throws CirculemosNegocioException
     */
    // Commented unused Sergio Torres 17/dic/2015
    // void eliminarCambioEstadoFormulario(Long idDetalleCambioEstado) throws CirculemosNegocioException;

    /**
     * Valida si todos los formularios ingresados como parametro, estan en un mismo estado
     * 
     * @return True, si todos los formularios estan en el mismo estado
     * @author dixon.alvarez
     */

    // Commented unused Sergio Torres 17/dic/2015
    // /**
    // * Valida si todos los formularios incluidos entre un rango de detalle cambio estado, estan en un mismo estado
    // *
    // * @param idDetalleCambioEstadoDTO
    // * Contiene el id de detalle cambio estado, de los seguimientos a comparar
    // * @param idEstadoActual
    // * Estado de formulario a validar
    // * @return True, si todos los formularios estan en el mismo estado
    // * @throws CirculemosNegocioException
    // * NUM_001011: El estado de alguno de los formularios no corresponde al requerido<br>
    // * @author dixon.alvarez
    // */
    // boolean validarEstadoActualFormularios(Long idDetalleCambioEstadoDTO, Integer idEstadoActual)
    // throws CirculemosNegocioException;

    /**
     * Actualiza el detalle de cambio estado del formulario
     * 
     * @param detalleCambioEstadoDTO
     *            detalle_cambio_estado a actualizar
     * @param archivoTransportableDTO
     *            Archivo a actualizar
     * @throws CirculemosNegocioException
     */
    void actualizarDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstadoDTO,
            ArchivoTransportableDTO archivoTransportableDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de consultar los formularios que cumplen con las condiciones recibidas en el objeto
     * 
     * @param consultaFormulario
     *            datos de entrada para la consulta:</br>
     *            <p>
     *            <li>idTipoFormulario</li>
     *            <li>idEstadoFormulario</li>
     *            <li>fechaInicial</li>
     *            <li>fechaFinal</li>
     *            <li>idTipoDocResponsable</li>
     *            <li>numeroDocResponsable</li>
     *            <li>numeroPlacaResponsable</li>
     *            </p>
     * @return listado de formularios filtrados
     * @author luis.forero (2015-05-12)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // List<SeguimientoFormularioDTO> consultarFormulario(ConsultaFormularioDTO consultaFormulario);

    /**
     * Permite determinar el número de registros existentes con respecto a la consulta ingresada
     * 
     * @param consultaFormulario
     *            datos de entrada para la consulta:</br>
     *            <p>
     *            <li>idTipoFormulario</li>
     *            <li>idEstadoFormulario</li>
     *            <li>fechaInicial</li>
     *            <li>fechaFinal</li>
     *            <li>idTipoDocResponsable</li>
     *            <li>numeroDocResponsable</li>
     *            <li>numeroPlacaResponsable</li>
     *            </p>
     * @return numero de registros existentes con los filtros ingresados
     * @author luis.forero (2015-05-25)
     */
    // Commented unused Sergio Torres 17/dic/2015
    // int contarConsultaFormulario(ConsultaFormularioDTO consultaFormulario);

    /**
     * Realiza la consulta del seguimiento de un formulario en particular
     * 
     * @param consultaSeguimientoFormulario
     *            Contiene el objeto DTO con los filtros de consulta
     * @return Objeto FormularioDTO con toda la información del formulario consultado junto con el listado de detalles de cada uno de los seguimientos
     *         de cambio de estado del formulario respectivo, retorna el listado vacio en caso de no encontrar el formulario
     * @author divier.casas (2015-09)<br>
     *         luis.forero(mod 2015-09-21)
     */
    FormularioDTO consultarSeguimientoFormulario(ConsultaSeguimientoFormularioDTO consultaSeguimientoFormulario);

    /**
     * Consulta los formularios por estados y filtros de datos de responsable y formulario
     * 
     * @param consultaSeguimientoAgrupadoFormularioDTO
     * @return List<FormularioDTO>
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    // Commented unused Sergio Torres 17/dic/2015
    // public List<FormularioDTO> consultarSeguimientoFormulario(
    // ConsultaSeguimientoAgrupadoFormularioDTO consultaSeguimientoAgrupadoFormularioDTO)
    // throws CirculemosNegocioException;

    /**
     * Cambia los formularios de diferentes intervalos de rangos a un estado final enviado
     * 
     * @param cambioEstadoFormularioDTO
     *            DTO con los datos del cambio de estado y los inetrvalos de rango a modificar
     * @return cantidad de formularios que fueron afectados por el cambio de estado
     * @throws CirculemosNegocioException
     * @author claudia.rodriguez
     */
    public int cambiarEstadoFormularios(CambioEstadoFormularioDTO cambioEstadoFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Metodo que Retorna true si el número de formulario indicado con el tipo de formulario se encuentra en estado asignado al Organismo de tránsito
     * indicado. De lo contrario retorna false.
     * 
     * @param numeroFormulario
     * @param idTipoFormulario
     * @param idCodigoOrganismo
     * @return boolean
     * @author giovanni.velandia
     */
    public boolean existeEstadoFormularioOrganismo(String numeroFormulario, Integer idTipoFormulario,
            Integer idCodigoOrganismo, EnumEstadoFomulario... enumEstadoFomulario);

    /**
     * Metodo que Retorna true si el número de formulario indicado con el tipo de formulario se encuentra en estado asignado. De lo contrario retorna
     * false.
     * 
     * @param numeroFormulario
     * @param idTipoFormulario
     * @return boolean
     * @author giovanni.velandia
     */
    // Commented unused Sergio torres 17-Dic-2015
    // public boolean existeAsignacion(String numeroFormulario, Integer idTipoFormulario);

    /**
     * Retorna true si existe un formulario con el número y tipo indicado en un rango de formularios.
     * 
     * @param numeroFormulario
     * @param idTipoFormulario
     * @return
     * @author giovanni.velandia
     */
    public boolean existeFormulario(String numeroFormulario, int idTipoFormulario);

    /**
     * Cambia el estado de un formulario y toda su trazabilidad
     * 
     * @param cambioEstadoDTO
     * @author giovanni.velandia
     */
    public void cambiarEstadoFormulario(CambioEstadoDTO cambioEstadoDTO);

    /**
     * Metodo que se encargar de validar el formato de un numero de fomulario
     * 
     * @param numeroFormulario
     * @param tipoFormulario
     * @throws CirculemosNegocioException
     * @return boolean
     * @author giovanni.velandia
     */
    public boolean validarFormatoNumeroFormulario(String numeroFormulario, int tipoFormulario)
            throws CirculemosNegocioException;

    /**
     * @author diego.fonseca
     * @param tipoFormulario
     * @param idResponsable
     * @return Retorna el listado de Asignaciones (ordenadas cronológicamente de la más reciente a la más antigua) realizadas para el tipo de
     *         formulario con el identificador indicado al responsable con el identificador
     * @throws CirculemosNegocioException
     *             Es generado en caso de que el tipo de formulario no exista o en caso de que el responsable no exista
     * 
     */
    public EstadoFormularioAsignacionDTO consultarEstadosFormulariosAsignacion(int tipoFormulario, Long idResponsable)
            throws CirculemosNegocioException;

    /**
     * 
     * @param unificacionResponsableDTO
     * @param enumTipoFormulario
     * @return Retorna El numero del siguiente formulario disponible para el UnificacionResponsable recibido
     * @throws CirculemosNegocioException
     */
    public String seleccionarFormulario(UnificacionResponsableDTO unificacionResponsableDTO,
            EnumTipoFormulario enumTipoFormulario) throws CirculemosNegocioException;
}
