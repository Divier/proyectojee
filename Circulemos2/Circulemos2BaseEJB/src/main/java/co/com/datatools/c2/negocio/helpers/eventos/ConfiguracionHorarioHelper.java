package co.com.datatools.c2.negocio.helpers.eventos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.eventos.Cargo;
import co.com.datatools.c2.entidades.eventos.ConfiguracionHorario;
import co.com.datatools.c2.entidades.eventos.Dia;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:25:49 COT 2016
 */
public class ConfiguracionHorarioHelper {
    // --- to DTO
    public static ConfiguracionHorarioDTO toLevel0DTO(ConfiguracionHorario entidad) {
        ConfiguracionHorarioDTO dto = new ConfiguracionHorarioDTO();
        dto.setIdConfiguracionHorario(entidad.getIdConfiguracionHorario());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setFechaFin(entidad.getFechaFin());
        dto.setFechaInicio(entidad.getFechaInicio());
        return dto;
    }

    public static ConfiguracionHorarioDTO toLevel1DTO(ConfiguracionHorario entidad) {
        ConfiguracionHorarioDTO dto = toLevel0DTO(entidad);
        if (entidad.getCargo() != null)
            dto.setCargoDTO(CargoHelper.toLevel0DTO(entidad.getCargo()));
        if (entidad.getDia() != null)
            dto.setDiaDTO(DiaHelper.toLevel0DTO(entidad.getDia()));
        if (entidad.getUsuarioActualiza() != null)
            dto.setUsuarioActualizaDTO(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioActualiza()));
        if (entidad.getUsuarioRegistra() != null)
            dto.setUsuarioRegistraDTO(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistra()));

        return dto;
    }

    public static List<ConfiguracionHorarioDTO> toListLevel0DTO(List<ConfiguracionHorario> listEntidad) {
        List<ConfiguracionHorarioDTO> listDto = new ArrayList<ConfiguracionHorarioDTO>();
        for (ConfiguracionHorario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConfiguracionHorarioDTO> toListLevel1DTO(List<ConfiguracionHorario> listEntidad) {
        List<ConfiguracionHorarioDTO> listDto = new ArrayList<ConfiguracionHorarioDTO>();
        for (ConfiguracionHorario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConfiguracionHorario toLevel0Entity(ConfiguracionHorarioDTO dto, ConfiguracionHorario entidad) {
        if (null == entidad) {
            entidad = new ConfiguracionHorario();
        }
        entidad.setIdConfiguracionHorario(dto.getIdConfiguracionHorario());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setFechaFin(dto.getFechaFin());
        entidad.setFechaInicio(dto.getFechaInicio());
        return entidad;
    }

    public static ConfiguracionHorario toLevel1Entity(ConfiguracionHorarioDTO dto, ConfiguracionHorario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCargoDTO() != null) {
            entidad.setCargo(new Cargo());
            entidad.getCargo().setId(dto.getCargoDTO().getId());
        }
        if (dto.getDiaDTO() != null) {
            entidad.setDia(new Dia());
            entidad.getDia().setId(dto.getDiaDTO().getId());
        }
        if (dto.getUsuarioActualizaDTO() != null) {
            entidad.setUsuarioActualiza(new UsuarioPersona());
            entidad.setUsuarioActualiza(UsuarioPersonaHelper.toLevel1Entity(dto.getUsuarioActualizaDTO(), null));
        }
        if (dto.getUsuarioRegistraDTO() != null) {
            entidad.setUsuarioRegistra(new UsuarioPersona());
            entidad.setUsuarioRegistra(UsuarioPersonaHelper.toLevel1Entity(dto.getUsuarioRegistraDTO(), null));
        }

        return entidad;
    }

    public static List<ConfiguracionHorario> toListLevel0Entity(List<ConfiguracionHorarioDTO> listDto) {
        List<ConfiguracionHorario> listEntidad = new ArrayList<ConfiguracionHorario>();
        for (ConfiguracionHorarioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConfiguracionHorario> toListLevel1Entity(List<ConfiguracionHorarioDTO> listDto) {
        List<ConfiguracionHorario> listEntidad = new ArrayList<ConfiguracionHorario>();
        for (ConfiguracionHorarioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
