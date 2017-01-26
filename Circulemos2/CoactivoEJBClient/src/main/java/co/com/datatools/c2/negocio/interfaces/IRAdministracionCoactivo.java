package co.com.datatools.c2.negocio.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.enumeraciones.EnumCampoConfiguracionCoactivo;
import co.com.datatools.c2.enumeraciones.EnumErrorConfiguracionCoactivo;

@Remote
public interface IRAdministracionCoactivo {

    /**
     * Consulta la configuracion de condiciones de coactivo
     * 
     * @param pConfiguracionCoactivoDTO
     * @param vigente
     * @return
     */
    public List<ConfiguracionCoactivoDTO> consultarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO pConfiguracionCoactivoDTO, Boolean vigente);

    /**
     * Consulta las condiciones de configuracion de coactivo
     * 
     * @param configuracionCoactivoDTO
     * @param activo
     * @return
     */
    public List<CondicionCoactivoDTO> consultarCondicionesConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO, Boolean activo);

    /**
     * Consulta las variables con valores de una condicion para una configuracion especifica
     * 
     * @param condicionCoactivoDTO
     * @param idConfiguracionCoactivo
     * @return lista de dto de variables con sus valores
     * @author julio.pinzon 2016-08-05
     */
    List<VariableCondicionCoacDTO> consultarVariablesCondicionCoactivo(CondicionCoactivoDTO condicionCoactivoDTO,
            Integer idConfiguracionCoactivo);

    /**
     * Consulta las condiciones que se pueden configurar para un organismo de transito
     * 
     * @param pCodigoOrganismo
     * @return
     * @author Dixon.Alvarez 2016-09-02
     */
    List<CondicionCoactivoDTO> consultarCondicionesCoactivo(int pCodigoOrganismo);

    /**
     * Permite eliminar una configuracion de coactivo y sus datos relacionados.
     * 
     * @param configuracionCoactivonDTO
     * @return RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>
     * @author Dixon.Alvarez 2016-09-02
     */
    RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> eliminarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * Consulta los coactivos que se encuentren asociados a una configuración de coactivo
     * 
     * @param configuracionCoactivoDTO
     * @return List<CoactivoDTO> coactivos asociados a una configuración de coactivo, solo retorna el primer proceso encontrado teniendo en cuenta el
     *         orden de fecha de inicio
     * @author Dixon.Alvarez 2016-09-02
     */
    List<CoactivoDTO> consultarCoactivosXConfiguracion(ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * Permite registrar una configuracion de coactivo y la relacion de valores de las condiciones
     * 
     * Retorna los errores posibles para los diferentes objetos contenidos.
     * 
     * 
     * @param configuracionCoactivoDTO
     * @return HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>>
     * @author Dixon.Alvarez 2016-09-05
     */
    HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> registrarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * Consulta la existencia de una configuracion de coactivo sin cerrar.
     * 
     * @param configuracionCoactivoDTO
     *            : configuracion de Coactivo con el organismo a consultar
     * @return ConfiguracionCoactivoDTO
     * @author Dixon.Alvarez 206-09-05
     */
    ConfiguracionCoactivoDTO consultarExistenciaConfCoactivoSinCerrar(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * Permite modificar sobre los datos de una configuracion de coactivo con su informacion relacionada.
     * 
     * @param configuracionCoactivoDTO
     * @return HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>>
     * @author Dixon.Alvarez 2016-09-06
     */
    HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> modificarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

}
