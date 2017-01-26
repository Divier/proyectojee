package co.com.datatools.c2.negocio.interfaces.formularios;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRAdministracionFormularios {

    /**
     * Se encarga de consultar la numeraciones que cumplen con los filtros de entrada
     * 
     * @param idTipoFormulario
     *            Id del tipo de formulario para el cual se van a consultar las numeraciones
     * @param estadoNumeracion
     *            Estado de las numeracionesa consultar
     * @return Numeraciones que cumplan con los filtros enviados
     * 
     * @author claudia.rodriguez
     */
    List<NumeracionFormularioDTO> consultarNumeracionFormulario(Integer idTipoFormulario, Boolean estadoNumeracion);

    /**
     * Se encarga de registrar la informacion de la numeracion, el detalle se persiste en cascada para no persistrir un detalle a la vez
     * 
     * @param NumeracionFormularioDTO
     *            Dto con los datos de la numeracion a persistir
     * @throws CirculemosNegocioException
     *             NUM_016001=Error de validacion: Para una casilla no incremental, Los carácteres inicial y final deben ser iguales<br>
     *             NUM_016002=Error de validacion: Para una casilla incremental, El carácter final debe ser mayor al inicial<br>
     *             NUM_016003=Error de validacion: Los carácteres inicial y final deben ser del mismo tipo de dato: ambos numéricos o ambos
     *             alfabéticos<br>
     *             NUM_016004=Error de validacion: Los carácteres inicial y final deben estar ambos en mayúscula o ambos en minúscula<br>
     *             NUM_016005=Error de validacion: Ya existe otra numeración en el sistema con la misma configuración<br>
     *             NUM_016008=Error de validacion: La fecha final de vigencia debe ser mayor a la fecha inicial de vigencia <br>
     *             NUM_016009=Error de validacion: La cantidad de digitos debe ser mayor a cero y menor o igual a 50 <br>
     *             NUM_016010=Error de validacion: El tipo de formulario de la numeracion es obligatorio<br>
     *             NUM_016011=Error de validacion: La fecha inicial de vigencia de la numeracion es obligatoria<br>
     *             NUM_016012=Error de validacion: La fecha final de vigencia de la numeracion es obligatoria<br>
     *             NUM_016013=Error de validacion: El detalle de la numeracion es obligatorio<br>
     *             NUM_016014=Error de validacion: El usuario que registra la numeración es obligatorio<br>
     *             NUM_016015=Ya existe una numeracion para el mismo tipo de formulario cruzada con las fechas de vigencia ingresadas
     * 
     * @author claudia.rodriguez <br>
     *         luis.forero(mod 2015-06-04)
     */
    void registrarNumeracionFormulario(NumeracionFormularioDTO NumeracionFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Consulta los responsables de formularios que cumplan con los atributos del objeto recibido por parametro.<br>
     * Utilizado en: <b>CU_CIR20_DAT_ADM_031</b>
     * 
     * @param unificacionResponsable
     *            parametro con los atributos para los filtros de la consulta
     * @return listado de responsables de formularios filtrados mediante los datos de entrada
     * @author luis.forero
     */
    List<UnificacionResponsableDTO> consultarResponsablesFormularios(UnificacionResponsableDTO unificacionResponsable);

    /**
     * Retorna el responsable acorde al tipo de responsable, con su tipo y numero exacto de identificacion.
     * 
     * @param unificacionResponsable
     * @return
     * @throws CirculemosNegocioException
     *             Si el responsable no esta activo
     * @author rodrigo.cruz
     */
    UnificacionResponsableDTO consultarResponsableFormularios(UnificacionResponsableDTO unificacionResponsable)
            throws CirculemosNegocioException;

    // Commented unused Sergio Torres 17-dic-2015
    // /**
    // * Consultar un responsable de formulario.<br>
    // * Utilizado en: <b>CU_CIR20_DAT_ADM_031</b>
    // *
    // * <pre>
    // * <li>tipoDocumento <b>(Obligatorio)</b></li>
    // * <li>numeroDocumento <b>(Obligatorio)</b></li>
    // * <li>codigoOrganismo <b>(Obligatorio)</b></li>
    // * </pre>
    // *
    // * @param tipoDocumento
    // * tipo de documento del responsable de formulario
    // * @param numeroDocumento
    // * numero de documento del responsable del formulario
    // * @return responsable de formulario filtrado por los parametros ingresados
    // * @author luis.forero (2015-01-13)
    // */
    // @Deprecated
    // ResponsableFormularioDTO consultarResponsableFormularios(Integer tipoDocumento, String numeroDocumento);

    // Commented unused Sergio Torres 17-dic-2015
    // /**
    // * Consulta un agente de transito (responsable) através del numero de su placa.<br>
    // * Utilizado en: <b>CU_CIR20_DAT_ADM_031</b>
    // *
    // * @param numPlaca
    // * numero de placa del responsable a consultar.
    // * @return responsable de formulario con el numero de placa ingresado
    // * @author luis.forero (2015-02-06)
    // */
    // @Deprecated
    // ResponsableFormularioDTO consultarResponsablePorPlaca(String numPlaca);

    /**
     * Registra un responsable de formulario en el sistema usando el objeto recibido por parametro.<br>
     * Utilizado en: <b>CU_CIR20_DAT_ADM_031</b>
     * 
     * @param unificacionResponsableDTO
     *            contiene los atributos correspondientes del responsable de formularios.
     * @return id del responsable de formularios registrado
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_031001=Organismo de transito asociado es obligatorio<br>
     *             ADM_031002=Persona de responsable es obligatoria<br>
     *             ADM_031003=La persona asociada al responsable es obligatoria<br>
     *             ADM_031004=Fecha fin de vincualcion es inferior a la fecha de inicio de vinculacion<br>
     *             ADM_031005=Fecha fin de Servicio debe ser mayor a la fecha de inicio de servicio<br>
     *             ADM_031006=Responsable de fomulario ya se encuentra registrado en el sistema<br>
     *             ADM_031007=Datos del responsable vacios.<br>
     *             ADM_031009=El correo del responsable es obligatorio<br>
     *             ADM_031010=Formato del correo del responsable no es valido<br>
     * @author luis.forero (mod 2015-09-09)
     */
    Long registrarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * Actualiza el responsable en el sistema de acuerdo al objeto recibido.<br>
     * Utilizado en: <b>CU_CIR20_DAT_ADM_031</b>
     * 
     * @param responsableFormularioDTO
     *            responsable de formularios con los datos a ser actualizados en el sistema
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_031001=Organismo de transito asociado es obligatorio<br>
     *             ADM_031002=Persona de responsable es obligatoria<br>
     *             ADM_031003=La persona asociada al responsable es obligatoria<br>
     *             ADM_031004=Fecha fin de vincualcion es inferior a la fecha de inicio de vinculacion<br>
     *             ADM_031005=Fecha fin de Servicio debe ser mayor a la fecha de inicio de servicio<br>
     *             ADM_031006=Responsable de fomulario ya se encuentra registrado en el sistema<br>
     *             ADM_031007=Datos del responsable vacios.<br>
     *             ADM_031009=El correo del responsable es obligatorio<br>
     *             ADM_031010=Formato del correo del responsable no es valido<br>
     * @author luis.forero (mod 2015-09-09)
     */
    void actualizarResponsableFormularios(UnificacionResponsableDTO responsableFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Elimina un responsable de formularios del sistema.<br>
     * Utilizado en: <b>CU_CIR20_DAT_ADM_031</b>
     * 
     * @param idResponsable
     *            identificador del responsable a eliminar
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_031008 =El Responsable de formularios a eliminar tiene o ha tenido asociaciones de asignacion a numeros de formularios.
     * @author luis.forero
     */
    void eliminarResponsableFormularios(Long idResponsable) throws CirculemosNegocioException;

    /**
     * Valida si un detalle de numeracion es correcto
     * 
     * @param detalleNumeracionDTO
     *            Dto con los datos de la numeracion a validar
     * @throws CirculemosNegocioException
     *             NUM_016001=Error de validacion: Para una casilla no incremental, Los carácteres inicial y final deben ser iguales<br>
     *             NUM_016002=Error de validacion: Para una casilla incremental, El carácter final debe ser mayor al inicial<br>
     *             NUM_016003=Error de validacion: Los carácteres inicial y final deben ser del mismo tipo de dato: ambos numéricos o ambos
     *             alfabéticos<br>
     *             NUM_016004=Error de validacion: Los carácteres inicial y final deben estar ambos en mayúscula o ambos en minúscula
     * 
     * @author claudia.rodriguez
     */
    void validarDetalleNumeracion(DetalleNumeracionDTO detalleNumeracionDTO) throws CirculemosNegocioException;

    /**
     * Consulta los detalles de una numeracion de formulario
     * 
     * @param idNumeracionFormulario
     *            Id de la numeracion para la cual se van a consultar los detalles
     * @return Detalles de la numeracion
     * 
     * @author claudia.rodriguez
     */
    List<DetalleNumeracionDTO> consultarDetalleNumeracionFormulario(int idNumeracionFormulario);

    /**
     * Elimina la numeracion con id enviado
     * 
     * @param idNumeracionFormulario
     *            id de la numeración a eliminar
     * 
     * @throws CirculemosNegocioException
     *             NUM_016007=La numeración no puede ser eliminada o editada porque ya existen rangos en el sistema asocidos a la numeracion
     * @author claudia.rodriguez
     */
    void eliminarNumeracionFormulario(int idNumeracionFormulario) throws CirculemosNegocioException;

    /**
     * Se encarga de actualizar los datos de una numeración existente
     * 
     * @param numeracionFormularioDTO
     * @throws CirculemosNegocioException
     *             NUM_016005=Ya existe otra numeración en el sistema con la misma configuración<br>
     *             NUM_016007=La numeración no puede ser eliminada o editada porque ya existen rangos en el sistema asocidos a la numeracion
     * @author claudia.rodriguez
     */
    void actualizarNumeracionFormulario(NumeracionFormularioDTO numeracionFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * Valida que una numeracion pueda ser editada o eliminada, lo cual depende de que tenga o no rangos asociados en el sistema
     * 
     * @param idNumeracionFormulario
     *            Id de la numeracion que se desea validar
     * @return true si la numeracion puede ser editada o eliminada, de lo contrario false
     * 
     * @author claudia.rodriguez
     */
    boolean validarNumeracionEditable(int idNumeracionFormulario);

    /**
     * Consulta las relaciones de estados que cumplan con los parametros de busqueda recibidos en el objeto dtoConsulta, consulta la lista de los
     * mismos.<br>
     * Utilizado en <b>CU_CIR20_DAT_ADM_050</b>
     * 
     * @param relacionEsatdoConsulta
     *            objeto con los atributos a filtrar
     * @return listado de relaciones de estados existentes.
     * @author luis.forero (2015-01-15)
     */
    List<RelacionEstadosDTO> consultarRelacionesEstados(RelacionEstadosDTO relacionEstadoConsulta);

    /**
     * Inserta una nueva relacion de estados en la base de datos y retorna el identificador de la misma.<br>
     * Utilizado en <b>CU_CIR20_DAT_ADM_050</b>
     * 
     * @param relacionEstadosDTO
     *            contiene los datos correspondientes a la relacion entre estados de un tipo de formulario
     * @return identificador unico de la relacion de estado registrada
     * @throws CirculemosNegocioException
     * @author luis.forero (2015-01-15)
     */
    Long registrarRelacionEstados(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException;

    /**
     * Modifica en base de datos la relacion de estados que recibe por parametro.<br>
     * Utilizado en <b>CU_CIR20_DAT_ADM_050</b>
     * 
     * @param relacionEstadosDTO
     *            relacion de estados con los datos a modificar
     * @throws CirculemosNegocioException
     * @author luis.forero (2015-01-16)
     */
    void actualizarRelacionEstados(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException;

    /**
     * Elimina una reacion de estados de la base de datos siempre y cuando no este tenga dependencias.<br>
     * Utilizado en <b>CU_CIR20_DAT_ADM_050</b>
     * 
     * @param Long
     *            identificador de la relacion de estados a eliminar
     * @author luis.forero (2015-01-16)
     */
    void eliminarRelacionEstados(Long idRelacionEstados);

    /**
     * Consulta el listado de StockTipoFormularioDTO resultado de la consulta con el filtro indicado.<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_013</b>
     * 
     * @param stockTipoFormulario
     *            estructura con los filtros a consultar
     * @return listado con los stocks filtrados por tipo de formulario
     * @author luis.forero (2015-02-03)
     */
    List<StockTipoFormularioDTO> consultarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario);

    /**
     * Registra el stockTipoFormularioDTO indicado.<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_013</b>
     * 
     * @param stockTipoFormulario
     *            estructura con los datos ingresados a registrar
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_013001=Ya existen datos iguales para el organismo de transito con el tipo de formulario seleccionado. No es posible realizar la
     *             operacion<br>
     *             NUM_013002=Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%. No es posible realizar la
     *             operacion<br>
     *             NUM_013003=Stock de formularios es obligatorio. No es posible realizar la operacion.<br>
     *             NUM_013004=Tipo de Formulario debe ser seleccionado. No es posible realizar la operacion.<br>
     * @author luis.forero (2015-02-03) giovanni.velandia (mod 2015-09-02)
     */
    void registrarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario) throws CirculemosNegocioException;

    /**
     * Actualiza el StockTipoFormularioDTO indicado.<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_013</b>
     * 
     * @param stockTipoFormulario
     *            estructura con los datos modificados del stock del tipo de formulario
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_013002=Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%. No es posible realizar la
     *             operacion<br>
     *             NUM_013003=Stock de formularios es obligatorio. No es posible realizar la operacion.<br>
     *             NUM_013004=Tipo de Formulario debe ser seleccionado. No es posible realizar la operacion.<br>
     * @author luis.forero (2015-02-03)
     */
    void actualizarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario) throws CirculemosNegocioException;

    /**
     * Se encarga de eliminar el stock configurado para el tipo de formulario y el organismo de transito.<br>
     * Utilizado en <b>CU_CIR20_DAT_NUM_013</b>
     * 
     * @param idTipoFormulario
     *            identificador unico del registro del tipo de formulario al cual se le va a eliminar el stock
     * @exception CirculemosNegocioException
     * <br>
     *                NUM_013005=No se encontro el stock. No es posible realizar la operacion
     * @author luis.forero (2015-02-03)
     */
    void eliminarStockTipoFormulario(Integer idTipoFormulario) throws CirculemosNegocioException;

    /**
     * Metodo que se encarga de consultar un unificacion responsable por el numero de formulario y el tipo de formulario
     * 
     * @param numeroFormulario
     * @param idTipoFormulario
     * @return
     * @author giovanni.velandia
     */
    public UnificacionResponsableDTO consultarResponsableFormulario(String numeroFormulario, int idTipoFormulario);

    /**
     * 
     * @param codigoOrganismo
     * @param tipoFormulario
     * @author diego.fonseca(2015-11-20)
     * 
     *         metodo que retorna todos los responsables que ha tenido un organismo
     */
    public List<UnificacionResponsableDTO> consultarResponsablesOrganismo(int codigoOrganismo, Integer tipoFormulario);
}
