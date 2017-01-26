package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.Operador;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interfaz local del bean de manejo de Parametrizacion de Configuraciones (tambien conocidad como Reglas de negocio)
 * 
 * @author Felipe Martinez
 */
@Local
public interface ILConfiguracion {

    /**
     * @see IRConfiguracion#consultarTiposConfiguracion
     */
    List<ItemCatalogoDTO> consultarTiposConfiguracion();

    /**
     * @see IRConfiguracion#consultarPlantillaConfiguracion(String)
     */
    TipoConfiguracionDTO consultarPlantillaConfiguracion(String codigoTipoConfiguracion);

    /**
     * @see IRConfiguracion#consultarValorConfiguracion(String)
     */
    List<RegistroConfiguracionDTO> consultarValorConfiguracion(String codigoTipoConfiguracion);

    /**
     * @see IRConfiguracion#registrarValorConfiguracion(String, RegistroConfiguracionDTO)
     */
    void registrarValorConfiguracion(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion)
            throws CirculemosNegocioException;

    /**
     * @see IRConfiguracion#actualizarValorConfiguracion(String, RegistroConfiguracionDTO)
     */
    void actualizarValorConfiguracion(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion)
            throws CirculemosNegocioException;

    /**
     * @see IRConfiguracion#eliminarValorconfiguracion(int)
     */
    void eliminarValorconfiguracion(int idRegistro);

    /**
     * @see IRConfiguracion#validarOperadoresLogicos(String, RegistroConfiguracionDTO)
     */
    List<Triple<CampoConfiguracionDTO, Operador, Object>> validarOperadoresLogicos(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * @see IRConfiguracion#validarLongitudCampos(String, RegistroConfiguracionDTO)
     */
    List<Triple<CampoConfiguracionDTO, Operador, Object>> validarLongitudCampos(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * @see IRConfiguracion#validarCamposObligatorios(String, RegistroConfiguracionDTO)
     */
    List<CampoConfiguracionDTO> validarCamposObligatorios(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * @see IRConfiguracion#validarRangosFechas(String, RegistroConfiguracionDTO)
     */
    List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> validarRangosFechas(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion);

    /**
     * @see IRConfiguracion#validarEntradaUnica(String, RegistroConfiguracionDTO)
     */
    boolean validarEntradaUnica(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion);

    /**
     * @see IRConfiguracion#consultarCatalogosConfiguracion(String)
     */
    Map<String, List<ItemCatalogoDTO>> consultarCatalogosConfiguracion(String codigoTipoConfiguracion);

    /**
     * @see IRConfiguracion#consultarDatoConfiguracion(String, Object)
     */
    <T> T consultarDatoConfiguracion(String codigoTipoConfiguracion, T dto) throws CirculemosNegocioException;
}