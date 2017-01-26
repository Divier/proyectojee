package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.Operador;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interfaz remota del bean de manejo de Parametrizacion de Configuraciones (tambien conocidad como Reglas de negocio)
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRConfiguracion {

    /**
     * Retorna el listado de tipos de configuracion existentes.
     * 
     * @return listado de tipos de configuracion definidos, lista vacia si no existen registros, Null safe
     */
    List<ItemCatalogoDTO> consultarTiposConfiguracion();

    /**
     * Retorna el tipo de configuracion solicitada con el xml procesado
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo configuracion
     * @return objeto con la plantilla xml procesada
     */
    TipoConfiguracionDTO consultarPlantillaConfiguracion(String codigoTipoConfiguracion);

    /**
     * Retorna el listado de registros existentes para un tipo de configuracion indicado
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo configuracion
     * @return listado de registros existentes, lista vacia si no existen registros, null safe
     */
    List<RegistroConfiguracionDTO> consultarValorConfiguracion(String codigoTipoConfiguracion);

    /**
     * Registra un nuevo valor para la configuracion indicada, valida que la informacion recibida cumpla con estructura definida para la
     * configuracion.
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo configuracion al que pertenece el registro a crear
     * @param registroConfiguracion
     *            listado de objetos <campo, valor> que define un registro de la configuracion
     * @throws CirculemosNegocioException
     */
    void registrarValorConfiguracion(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion)
            throws CirculemosNegocioException;

    /**
     * Actualiza el valor de un registro de la configuracion indicada, valida que la informacion recibida cumpla con estructura definida para la
     * configuracion.
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a actualizar
     * @param registroConfiguracion
     *            listado de objetos <campo, valor> que define un registro de la configuracion
     * @throws CirculemosNegocioException
     */
    void actualizarValorConfiguracion(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion)
            throws CirculemosNegocioException;

    /**
     * Elimina el registro de la configuracion indicado
     * 
     * @param idRegistro
     *            identificador unico del registro a borrar
     */
    void eliminarValorconfiguracion(int idRegistro);

    /**
     * Valida las restricciones parametrizadas para los campos de la configuracion
     * <ul>
     * <li>{@link Operador#MENOR}</li>
     * <li>{@link Operador#MENOR_IGUAL}</li>
     * <li>{@link Operador#MAYOR}</li>
     * <li>{@link Operador#MAYOR_IGUAL}</li>
     * <li>{@link Operador#IGUAL}</li>
     * <li>{@link Operador#DIFERENTE}</li>
     * </ul>
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a validar
     * @param registroConfiguracion
     *            registro a validar
     * @return listado de los campos inconsistentes con la validacion que no cumple, null safe
     */
    List<Triple<CampoConfiguracionDTO, Operador, Object>> validarOperadoresLogicos(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * Valida que los campos no excedan la longitud maxima permitida
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a validar
     * @param registroConfiguracion
     *            registro a validar
     * @return listado de los campos inconsistentes con la validacion que no cumple, null safe
     */
    List<Triple<CampoConfiguracionDTO, Operador, Object>> validarLongitudCampos(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * Valida que los campos obligatorios de una configuracion hallan sido ingresados
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a validar
     * @param registroConfiguracion
     *            registro a validar
     * @return listado de campos inconsistentes, null safe
     */
    List<CampoConfiguracionDTO> validarCamposObligatorios(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * Validas que los campos de rangos de fechas la fecha inicial sea menor a la final
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a validar
     * @param registroConfiguracion
     *            registro a validar
     * @return listado de parejas de campos que son inconsistentes en el registro, null safe
     */
    List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> validarRangosFechas(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * Retorna los identificadores de los registros que tienen los mismos valores en los campos de entrada que el registro de configuracion
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a validar
     * @param registroConfiguracion
     *            registro a validar
     * @return true si los campos de entrada del registro son unicos, si no false
     */
    boolean validarEntradaUnica(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion);

    /**
     * Permite consultar los valores asocidados a los catalogos definidos en la configuracion
     * 
     * @param codigoTipoConfiguracion
     *            identificador de negocio del tipo de configuracion al que pertenece el registro a validar
     * @return mapa con los valores del catalogo, asociado a cada campo de tipo catalogo de la configuracion, null safe
     */
    Map<String, List<ItemCatalogoDTO>> consultarCatalogosConfiguracion(String codigoTipoConfiguracion);

    /**
     * <p>
     * Permite consultar el valor de la configuracion que corresponde con la llave de entrada asignada a los campos definidos en el dto.
     * </p>
     * <p>
     * Este metodo hace uso de reflexion para extraer los valores de los campos del dto (getters) que tienen el mismo nombre de los campos de entrada
     * de la configuracion definida por codigoTipoConfiguracion
     * </p>
     * <p>
     * Despues de consultar la informacion, asigna en los campos de salida los valores obtenidos del valor de la configuracion haciendo uso de
     * reflexion para ejecutar los setters de los atributos.
     * </p>
     * 
     * <p>
     * EJEMPLO:<br/>
     * Una configuracion que contiene un descriptor como el siguiente:
     * 
     * <pre>
     *  {@code
     * <entrada>
     *     <NUMERO obligatorio="true">
     *         <codigo>entrada1</codigo>
     *         ...
     *     </NUMERO>
     *     <CADENA obligatorio="true" longitud="5">
     *         <codigo>entrada2</codigo>
     *         ...
     *     </CADENA>
     * </entrada>
     * <salida>
     *     <CATALOGOSIMPLE obligatorio="true">
     *         <codigo>valoresCatalogo</codigo>
     *         ...
     *     </CATALOGOSIMPLE>
     * </salida>
     *      }
     * </pre>
     * 
     * Debe estar soportado en un javabean con sus respectivos metodos get y set para sus campos:
     * <ul>
     * <li>BigDecimal entrada1;</li>
     * <li>String entrada2;</li>
     * <li>List<String> valoresCatalogo;</li>
     * </ul>
     * 
     * El tipo de los campos en el objeto dto, dependen del tipo de campo en la configuracion de la siguiente manera:
     * <ul>
     * <li>{@link Boolean}: BOOLEANO</li>
     * <li>{@link String}: CADENA, URL, CORREO</li>
     * <li>{@link BigDecimal}: DECIMAL, NUMERO</li>
     * <li>{@link Date}: FECHA, FECHAHORA, HORA</li>
     * 
     * <li>Map<String, List<String>>: CATALOGOCOMPUESTO</li>
     * <li>List<String>: CATALOGOSIMPLE, CATALOGOINDEPENDIENTE - Ids de cada registro seleccionado</li>
     * </ul>
     * </p>
     * 
     * 
     * @param codigoTipoConfiguracion
     *            configuracion contra la que se validan los campos de entrada para obtener un registro correspondiente
     * @param dto
     *            dto de entrada con los campos que se usan para encontrar el registro, los nombres de los campos deben corresponder con los codigos
     *            de los campos de la configuracion asociada
     * @return dto recibido como parametro, asignando
     * @throws CirculemosNegocioException
     *             <ul>
     *             <li>ADM_066007: No se encuentra ninguna parametrizacion valida para la configuracion</li>
     *             <li>ADM_066008: Se encontraron multiples registros parametrizados que cumplen con los filtros, se esperaba solo uno</li>
     *             </ul>
     */
    <T> T consultarDatoConfiguracion(String codigoTipoConfiguracion, T dto) throws CirculemosNegocioException;

}