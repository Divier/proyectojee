package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRCargo {

    /**
     * Retorna una configuracion de horarios por filtro
     * 
     * @author giovanni.velandia
     * @param configuracionHorarioFiltrosDTO
     * @return
     */
    public List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHorario(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO);

    /**
     * Retorna una configuracion de horarios por filtro
     * 
     * @author giovanni.velandia
     * @param configuracionHorarioDTO
     * @return
     */
    public List<ConfiguracionHorarioDTO> consultarConfiguracionHorario(ConfiguracionHorarioDTO configuracionHorarioDTO);

    /**
     * Metodo que se encarga de registrar la configuracion horario
     * 
     * @author giovanni.velandia
     * @param configuracionHorarioDTOs
     */
    public void registrarConfiguracionHorario(List<ConfiguracionHorarioDTO> configuracionHorarioDTOs)
            throws CirculemosNegocioException;

    /**
     * 
     * COnsulta configuraciones agrupada por dias
     * 
     * @author giovanni.velandia
     * @param configuracionHorarioFiltrosDTO
     * @return
     */
    public List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHoraioDia(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO);
}
