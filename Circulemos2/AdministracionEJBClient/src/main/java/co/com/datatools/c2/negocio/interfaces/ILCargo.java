package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILCargo {

    /**
     * @see IRCargo#consultarConfiguracionHorario(configuracionHorarioFiltrosDTO)
     */
    public List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHorario(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO);

    /**
     * @see IRCargo#consultarConfiguracionHorario(ConfiguracionHorarioDTO)
     */
    public List<ConfiguracionHorarioDTO> consultarConfiguracionHorario(ConfiguracionHorarioDTO configuracionHorarioDTO);

    /**
     * @see IRCargo#registrarConfiguracionHorario(ConfiguracionHorarioDTOs)
     */
    public void registrarConfiguracionHorario(List<ConfiguracionHorarioDTO> configuracionHorarioDTOs)
            throws CirculemosNegocioException;

    /**
     * @see IRCargo#consultarConfiguracionHoraioDia(ConfiguracionHorarioFiltrosDTO)
     */
    public List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHoraioDia(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO);
}
