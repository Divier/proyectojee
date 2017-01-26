package co.com.datatools.c2.negocio.helpers.eventos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.eventos.BloqueHorarioDTO;
import co.com.datatools.c2.entidades.eventos.BloqueHorario;
import co.com.datatools.c2.entidades.eventos.ConfiguracionHorario;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:25:49 COT 2016
 */
public class BloqueHorarioHelper {
    // --- to DTO
    public static BloqueHorarioDTO toLevel0DTO(BloqueHorario entidad) {
        BloqueHorarioDTO dto = new BloqueHorarioDTO();
        dto.setIdBloqueHorario(entidad.getIdBloqueHorario());
        dto.setHoraFin(entidad.getHoraFin());
        dto.setHoraInicio(entidad.getHoraInicio());

        return dto;
    }

    public static BloqueHorarioDTO toLevel1DTO(BloqueHorario entidad) {
        BloqueHorarioDTO dto = toLevel0DTO(entidad);
        if (entidad.getConfiguracionHorario() != null)
            dto.setConfiguracionHorarioDTO(ConfiguracionHorarioHelper.toLevel0DTO(entidad.getConfiguracionHorario()));
        return dto;
    }

    public static List<BloqueHorarioDTO> toListLevel0DTO(List<BloqueHorario> listEntidad) {
        List<BloqueHorarioDTO> listDto = new ArrayList<BloqueHorarioDTO>();
        for (BloqueHorario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<BloqueHorarioDTO> toListLevel1DTO(List<BloqueHorario> listEntidad) {
        List<BloqueHorarioDTO> listDto = new ArrayList<BloqueHorarioDTO>();
        for (BloqueHorario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static BloqueHorario toLevel0Entity(BloqueHorarioDTO dto, BloqueHorario entidad) {
        if (null == entidad) {
            entidad = new BloqueHorario();
        }
        entidad.setIdBloqueHorario(dto.getIdBloqueHorario());
        entidad.setHoraFin(dto.getHoraFin());
        entidad.setHoraInicio(dto.getHoraInicio());

        return entidad;
    }

    public static BloqueHorario toLevel1Entity(BloqueHorarioDTO dto, BloqueHorario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfiguracionHorarioDTO() != null) {
            entidad.setConfiguracionHorario(new ConfiguracionHorario());
            entidad.setConfiguracionHorario(
                    ConfiguracionHorarioHelper.toLevel1Entity(dto.getConfiguracionHorarioDTO(), null));
        }

        return entidad;
    }

    public static List<BloqueHorario> toListLevel0Entity(List<BloqueHorarioDTO> listDto) {
        List<BloqueHorario> listEntidad = new ArrayList<BloqueHorario>();
        for (BloqueHorarioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<BloqueHorario> toListLevel1Entity(List<BloqueHorarioDTO> listDto) {
        List<BloqueHorario> listEntidad = new ArrayList<BloqueHorario>();
        for (BloqueHorarioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
