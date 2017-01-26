package co.com.datatools.c2.negocio.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

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

@Local
public interface ILAdministracionFinanciacion {

    /**
     * @see IRAdministracionFinanciacion#consultarClaseGarantia(ClaseGarantiaDTO)
     */
    List<ClaseGarantiaDTO> consultarClaseGarantia(ClaseGarantiaDTO claseGarantiaDTO);

    /**
     * @see IRAdministracionFinanciacion#consultarEstadoGarantia(EstadoGarantiaDTO)
     */
    List<EstadoGarantiaDTO> consultarEstadoGarantia(EstadoGarantiaDTO estadoGarantiaDTO);

    /**
     * @see IRAdministracionFinanciacion#consultarTipoGarantia(TipoGarantiaDTO, int)
     */
    List<TipoGarantiaDTO> consultarTipoGarantia(TipoGarantiaDTO tipoGarantiaDTO, int codigoOrganismo);

    /**
     * @see IRAdministracionFinanciacion#consultarCondicionFinanciacion(int)
     */
    public List<CondicionFinanciacionDTO> consultarCondicionFinanciacion(int pCodigoOrganismo);

    /**
     * @see IRAdministracionFinanciacion#consultarConfiguracionFinanciacion(ConfiguracionFinanciacionDTO, boolean)
     */
    public List<ConfiguracionFinanciacionDTO> consultarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO pConfiguracionFinanciacionDTO, boolean vigente);

    /**
     * @see IRAdministracionFinanciacion#registrarConfiguracionFinanciacion(ConfiguracionFinanciacionDTO)
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> registrarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * @see IRAdministracionFinanciacion#modificarConfiguracionFinanciacion(ConfiguracionFinanciacionDTO)
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> modificarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * @see IRAdministracionFinanciacion#eliminarConfiguracionFinanciacion(ConfiguracionFinanciacionDTO)
     */
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> eliminarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * @see IRAdministracionFinanciacion#validarRegistroConfiguracionFinanciacion(ConfiguracionFinanciacionDTO)
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validarRegistroConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * @see IRAdministracionFinanciacion#validarModificacionFinanciacion(ConfiguracionFinanciacionDTO)
     */
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validarModificacionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * @see IRAdministracionFinanciacion#configuracionFinanciacionAsociadaFinanciacion(ConfiguracionFinanciacionDTO)
     */
    public FinanciacionDTO configuracionFinanciacionAsociadaFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO);

    /**
     * @see IRAdministracionFinanciacion#validarFinanciacion(FinanciacionDTO)
     */
    public RespuestaFinanciacionDTO validarFinanciacion(FinanciacionDTO financiacionDTO);

    /**
     * @see IRAdministracionFinanciacion#consultarCondicionesConfiguracionFinanciacion(ConfiguracionFinanciacionDTO, boolean)
     */
    public List<CondicionFinanciacionDTO> consultarCondicionesConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO, boolean activo);

    /**
     * @see IRAdministracionFinanciacion#consultarValorCondicionFinanciacion(String)
     */
    public List<ValorCondicionFinanciacionDTO> consultarValorCondicionFinanciacion(Integer variableCondicion);
}
