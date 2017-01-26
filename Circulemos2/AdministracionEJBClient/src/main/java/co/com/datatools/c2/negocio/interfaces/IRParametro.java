package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ParametroDTO;
import co.com.datatools.c2.dto.ParametroOrganismoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;

@Remote
public interface IRParametro {

    /**
     * Devuelve el valor de un parámetro de un organismo. Los parámetros de búsqueda se encuentran en el argumento de entrada "Parametro".
     * 
     * @param parametroDTO
     *            utiliza los siguiente atributos del objeto:
     *            <ul>
     *            <li>codigoParametro: null opcional</li>
     *            <li>codigoUnidadParametro: null opcional</li>
     *            <li>editableOrganismo: null opcional</li>
     *            <li>formato: null opcional</li>
     *            <li>nombreParametro: null opcional</li>
     *            <li>valorParametroDefecto: null opcional</li>
     *            <li>parametroDTO.procesoDTO.codigoProceso: null opcional</li>
     *            <li>parametroDTO.tipoVariableDTO.codigoTipoVariable: null opcional</li>
     * 
     *            </ul>
     * @param codigoOrganismo
     *            codigo del organismo que tiene configurado los parametros
     * @return Valores del parametro para el organismo, lista vacia si no encuentra nada
     * @author felipe.martinez
     */
    @Deprecated
    public List<ParametroOrganismoDTO> consultarParametroOrganismo(ParametroDTO parametroDTO, Integer codigoOrganismo);

    /**
     * Permite identificar si un parametroOrganismo existe configurado para un organismo, dependiendo de los filtros enviados en parametroOrganismo
     * 
     * @param parametroOrganismo
     *            utiliza los siguiente atributos del objeto:
     *            <ul>
     *            <li>idParametro: null opcional</li>
     *            <li>fechaFin: null opcional</li>
     *            <li>fechaInicio: null opcional</li>
     *            <li>valorParametro: null opcional</li>
     *            <li>parametroDTO.codigoParametro: null opcional</li>
     *            </ul>
     * 
     * @param codigoOrganismo
     *            codigo del organismo que tiene configurado los parametros
     * @return <ul>
     *         <li>true si se encuentra un parametro con los filtros definidos</li>
     *         <li>false si no se encuentra ningun registro</li>
     *         </ul>
     * @author felipe.martinez
     */
    public boolean existeParametroOrganismo(ParametroOrganismoDTO parametroOrganismo, Integer codigoOrganismo);

    /**
     * Consulta la informacion de la tabla parametro utilizando como filtro los campos del objeto de entrada
     * 
     * @param parametro
     *            utiliza los siguiente atributos del objeto:
     *            <ul>
     *            <li>codigoParametro: null opcional, si se incluye en el objeto, se excluye caulquier otra variable a la consulta</li>
     *            <li>codigoUnidadParametro: null opcional</li>
     *            <li>editableOrganismo: null opcional</li>
     *            <li>formato: null opcional</li>
     *            <li>nombreParametro: null opcional</li>
     *            <li>valorParametroDefecto: null opcional</li>
     *            <li>procesoDTO.codigoProceso: null opcional</li>
     *            <li>tipoVariableDTO.codigoTipoVariable: null opcional</li>
     *            </ul>
     * @return listado de parametros encontrados, lista vacia si no encuentra nada
     * @author felipe.martinez
     */
    List<ParametroDTO> consultarParametroSistema(ParametroDTO parametro);

    /**
     * Busca el valor de un parametro por organismo de transito. En caso de no encontrar el parametro por el organismo busca el valor configurado en
     * sistema
     * 
     * @param codigoParametro
     *            codigo del parametro a encontrar
     * @param codigoOrganismo
     *            codigo del organismo que tiene configurado los parametros
     * @return valor encontrado en la parametrizacion, null si no encuentra nada configurado en el sistema
     */
    String consultarValorParametro(EnumParametro codigoParametro, int codigoOrganismo);

    /**
     * Consulta la informacion de la tabla parametro y parametro organismo, para cada registro seleccionado de parametro se buscan los parametros
     * vigentes q tiene asociado en parametro_organismo ej: <br>
     * 
     * <pre>
     * <table>
     * <caption>Parametro</caption>
     * <tbody>
     * <tr><th>codigoParametro</th><th>nombreParametro</th><th>valorPorDefecto</th></tr>
     * <tr><td>3</td><td>Parametro 1</td><td>Parametro 1 Valor defecto</td></tr>
     * <tr><td>8</td><td>Parametro 2</td><td>Parametro 2 valor defecto</td>
     * </tr>
     * </tbody>
     * </table>
     * <table>
     * <caption>ParametroOrganismo</caption>
     * <tbody>
     * <tr><th>idParametro</th><th>codigoParametro</th><th>valorParametro</th><th>codigoOrganismo</th></tr>
     * <tr><td>100</td><td>3</td><td>Valor 1</td><td>11001</td></tr>
     * <tr><td>101</td><td>3</td><td>Valor 2</td><td>11001</td></tr>
     * </tbody>
     * </table>
     * </pre>
     * 
     * <br>
     * <b>Caso 1:</b> Consultar Todos los parametros (no filtros) para el organismo 11001 <br>
     * <ul>
     * <li>3 -> [Valor 1,Valor 2]</li>
     * <li>8 -> [Parametro 2 valor defecto]</li>
     * </ul>
     * 
     * <b>Caso 2:</b> Consultar los parametro (no filtros) para el organismo 55555 <br>
     * <ul>
     * <li>3 -> [Parametro 1 Valor defecto]</li>
     * <li>8 -> [Parametro 2 valor defecto]</li>
     * </ul>
     * 
     * <b>Caso 3:</b> Consultar los parametro con codigoParametro 3 para el organismo 11001 <br>
     * <ul>
     * <li>3 -> [Valor 1,Valor 2]</li>
     * </ul>
     * 
     * @param parametro
     *            utiliza los siguiente atributos del objeto:
     *            <ul>
     *            <li>codigoParametro: null opcional</li>
     *            <li>editableOrganismo: null opcional</li>
     *            <li>procesoDTO.codigoProceso: null opcional</li>
     *            <li>tipoVariableDTO.codigoTipoVariable: null opcional</li>
     *            </ul>
     * @param codigoOrganismo
     *            codigo del organismo que tiene configurado los parametros
     * @return K: codigo de parametro, V: List de parametroDTO por cada valor q aplique para el organismo, si no hay parametro para el organismo
     *         retorna el valor configurado para el sistema
     * @author felipe.martinez
     */
    Map<Integer, List<ParametroDTO>> consultarParametro(ParametroDTO parametro, int codigoOrganismo);

    /**
     * Consulta los valores de un parametro para un organismo, si no encuentra informacion del organismo busca la informacion por defecto del
     * parametro
     * 
     * @param parametro
     *            parametro a buscar
     * @param codigoOrganismo
     *            codigo del organismo del q se busca parametrizacion
     * @param requerido
     *            se encarga de controlar si el parametro es requerido para el correcto funcionamiento de la aplicacion, si recibe un true, el metodo
     *            lanzara excepcion en caso de q no se encuentre valor para el parametro
     * @return valores del parametro para el organismo (parametro_organismo), si no la encuentra utiliza la informacion del parametro del sistema
     *         (parametro), si esta ultima es null arroja ConfiguracionParametroException cuando <b>requerido</b> es true
     * @author felipe.martinez
     */
    ValorParametroDTO consultarParametro(EnumParametro parametro, int codigoOrganismo, boolean requerido);

}
