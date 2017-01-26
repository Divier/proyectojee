package co.com.datatools.c2.negocio.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ClaseGarantiaDTO;
import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.EstadoGarantiaDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaFinanciacionDTO;
import co.com.datatools.c2.dto.TipoGarantiaDTO;
import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.enumeracion.EnumCampoConfFinanciacion;
import co.com.datatools.c2.enumeracion.EnumErrorConfiguracionFinanciacion;

@Remote
public interface IRAdministracionFinanciacion {

    /**
     * Permite consultar las clases de garantia del sistema.
     * 
     * @param claseGarantiaDTO
     *            - Contiene los criterios de busqueda.
     * @return Catalogo de clases de garantia. Lista vacia si no encuentra resultados.
     * @author rodrigo.cruz
     */
    List<ClaseGarantiaDTO> consultarClaseGarantia(ClaseGarantiaDTO claseGarantiaDTO);

    /**
     * Consulta los estado de garantias dependiendo del filtro indicado.
     * 
     * @param estadoGarantiaDTO
     *            - Contiene los criterios de busqueda.
     * @return Catalogo de estados de garantia. Lista vacia si no encuentra resultados.
     * @author rodrigo.cruz
     */
    List<EstadoGarantiaDTO> consultarEstadoGarantia(EstadoGarantiaDTO estadoGarantiaDTO);

    /**
     * Permite consultar los tipos de garantia del sistema.
     * 
     * @param tipoGarantiaDTO
     *            - Contiene los criterios de busqueda.
     * @param codigoOrganismo
     *            - Codigo de organismo de transio obligatorio.
     * @return Catalogo de tipos de garantia. Lista vacia si no encuentra resultados.
     * @author rodrigo.cruz
     */
    List<TipoGarantiaDTO> consultarTipoGarantia(TipoGarantiaDTO tipoGarantiaDTO, int codigoOrganismo);

    /**
     * Permite consultar las condiciones de financiacion de un organismo
     * 
     * @author luis.forero
     * @param pCodigoOrganismo
     *            : Parametro con el codigo del organismo el cual es obligatorio
     * @return List<CondicionFinanciacionDTO>
     */
    public List<CondicionFinanciacionDTO> consultarCondicionFinanciacion(int pCodigoOrganismo);

    /**
     * Permite consultar las configuraciones de financiacion.
     * 
     * @param pConfiguracionFinanciacionDTO
     *            objeto que representa los datos de filtro de la consulta
     * @param vigente
     *            Indica si solo se van a retornar configuraciones vigentes
     * @return List<ConfiguracionFinanciacionDTO>
     * @author luis.forero
     */
    public List<ConfiguracionFinanciacionDTO> consultarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO pConfiguracionFinanciacionDTO, boolean vigente);

    /**
     * Permite registrar una configuracion de financiacion y la relacion de valores de las condiciones , detalle de cuota inicial y detalle de
     * porcentaje de cuota inicial.
     * 
     * Retorna los errores posibles para los diferentes objetos contenidos.
     * 
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     * @return HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> registrarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * Permite realizar modificacion sobre los datos de una configuracion de financiacion con su informacion relacionada.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     * @return HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> modificarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * Permite eliminar una configuracion de financiacion y sus datos relacionados.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> eliminarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * Expone los servicios de validacion al registrar una nueva configuracion de financiacion. (Recorre los objetos que representan los campos de una
     * configuración de financiacion validandolos)
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : Configuracion de financiacion a validar.
     * @return HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validarRegistroConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * Expone los servicios de validacion al realizar una modificacion de configuracion.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : Configuracion de financiacion a validar.
     * @return Hashtable<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validarModificacionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * Consulta si una configuracion de financiacion esta asociada a una financiacion extrallendo la ultima asociacion.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : configuracion de financiacion con id a consultar
     * @return FinanciacionDTO : null si no encuentra financiaciones asociadas
     */
    public FinanciacionDTO configuracionFinanciacionAsociadaFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * Verifica si la financiacion pasada como parametro cumple con todas las codiciones configuradas en el sistema
     * 
     * @param financiacionDTO
     *            Datos de la financiacion a validar
     * @return ResultadoValidacionFinanciacionDTO, si codigoError = 0 encontro errores en la validacion, si codigoError = 1 cumplio con todas las
     *         validaciones
     * @author dixon.alvarez
     */
    public RespuestaFinanciacionDTO validarFinanciacion(FinanciacionDTO financiacionDTO);

    /**
     * Consulta las condiciones de una configuración de financiación
     * 
     * @param configuracionFinanciacionDTO
     *            Contiene los datos de la configuracion de financiacion a filtrar
     * @param activo
     *            Indica si se deben consultar solamente las condiciones que estan activas para esa configuración
     * @return List<CondicionFinanciacionDTO>
     * @author dixon.alvarez
     */
    public List<CondicionFinanciacionDTO> consultarCondicionesConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO, boolean activo);

    /**
     * Consulta valores de la condicion de la financiacion
     * 
     * @param valor
     * @return retorna los valores de la condicion de la financiacion
     * @author Jeison.Rodriguez (2016-31-08)
     */
    public List<ValorCondicionFinanciacionDTO> consultarValorCondicionFinanciacion(Integer variableCondicion);
}
