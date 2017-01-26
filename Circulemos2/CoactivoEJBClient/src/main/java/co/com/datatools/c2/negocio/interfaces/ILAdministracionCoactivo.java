package co.com.datatools.c2.negocio.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.enumeraciones.EnumCampoConfiguracionCoactivo;
import co.com.datatools.c2.enumeraciones.EnumErrorConfiguracionCoactivo;

@Local
public interface ILAdministracionCoactivo {

    /**
     * @see ILAdministracionCoactivo#consultarConfiguracionCoactivo(ConfiguracionCoactivoDTO,Boolean)
     */
    public List<ConfiguracionCoactivoDTO> consultarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO pConfiguracionCoactivoDTO, Boolean vigente);

    /**
     * @see ILAdministracionCoactivo#consultarCondicionesConfiguracionCoactivo(ConfiguracionCoactivoDTO,Boolean)
     */
    public List<CondicionCoactivoDTO> consultarCondicionesConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO, Boolean activo);

    /**
     * @see IRAdministracionCoactivo#consultarVariablesCondicionCoactivo(CondicionCoactivoDTO, Integer)
     */
    List<VariableCondicionCoacDTO> consultarVariablesCondicionCoactivo(CondicionCoactivoDTO condicionCoactivoDTO,
            Integer idConfiguracionCoactivo);

    /**
     * @see IRAdministracionCoactivo#consultarCondicionesCoactivo(int)
     */
    List<CondicionCoactivoDTO> consultarCondicionesCoactivo(int pCodigoOrganismo);

    /**
     * @see IRAdministracionCoactivo#eliminarConfiguracionCoactivo(ConfiguracionCoactivoDTO)
     */
    RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> eliminarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * @see IRAdministracionCoactivo#consultarCoactivosXConfiguracion(ConfiguracionCoactivoDTO)
     */
    List<CoactivoDTO> consultarCoactivosXConfiguracion(ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * @see IRAdministracionCoactivo#registrarConfiguracionCoactivo(ConfiguracionCoactivoDTO)
     */
    HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> registrarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * @see IRAdministracionCoactivo#consultarExistenciaConfCoactivoSinCerrar(ConfiguracionCoactivoDTO)
     */
    ConfiguracionCoactivoDTO consultarExistenciaConfCoactivoSinCerrar(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);

    /**
     * @see IRAdministracionCoactivo#modificarConfiguracionCoactivo(ConfiguracionCoactivoDTO)
     */
    HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> modificarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO);
}
