package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.AnulacionDTO;
import co.com.datatools.c2.entidades.Anulacion;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 14 11:53:32 COT 2016
 */
public class AnulacionHelper {
    // --- to DTO
    public static AnulacionDTO toLevel0DTO(Anulacion entidad) {
        AnulacionDTO dto = new AnulacionDTO();
        dto.setId(entidad.getId());
        dto.setCorreoSupervisor(entidad.getCorreoSupervisor());
        dto.setFechaCorreo(entidad.getFechaCorreo());
        dto.setJustificacionCorreo(entidad.getJustificacionCorreo());
        dto.setPrimerNombreFuncionario(entidad.getPrimerNombreFuncionario());
        dto.setSegundoNombreFuncionario(entidad.getSegundoNombreFuncionario());
        dto.setPrimerApellidoFuncionario(entidad.getPrimerApellidoFuncionario());
        dto.setSegundoApellidoFuncionario(entidad.getSegundoApellidoFuncionario());
        dto.setFechaAnulacion(entidad.getFechaAnulacion());

        return dto;
    }

    public static AnulacionDTO toLevel1DTO(Anulacion entidad) {
        AnulacionDTO dto = toLevel0DTO(entidad);

        if (entidad.getProceso() != null) {
            dto.setProceso(ProcesoHelper.toLevel0DTO(entidad.getProceso()));
        }

        return dto;
    }

    public static List<AnulacionDTO> toListLevel0DTO(List<Anulacion> listEntidad) {
        List<AnulacionDTO> listDto = new ArrayList<AnulacionDTO>();
        for (Anulacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<AnulacionDTO> toListLevel1DTO(List<Anulacion> listEntidad) {
        List<AnulacionDTO> listDto = new ArrayList<AnulacionDTO>();
        for (Anulacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Anulacion toLevel0Entity(AnulacionDTO dto, Anulacion entidad) {
        if (null == entidad) {
            entidad = new Anulacion();
        }
        entidad.setId(dto.getId());
        entidad.setCorreoSupervisor(dto.getCorreoSupervisor());
        entidad.setFechaCorreo(dto.getFechaCorreo());
        entidad.setJustificacionCorreo(dto.getJustificacionCorreo());
        entidad.setPrimerNombreFuncionario(dto.getPrimerNombreFuncionario());
        entidad.setSegundoNombreFuncionario(dto.getSegundoNombreFuncionario());
        entidad.setPrimerApellidoFuncionario(dto.getPrimerApellidoFuncionario());
        entidad.setSegundoApellidoFuncionario(dto.getSegundoApellidoFuncionario());
        entidad.setFechaAnulacion(dto.getFechaAnulacion());

        return entidad;
    }

    public static Anulacion toLevel1Entity(AnulacionDTO dto, Anulacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getProceso() != null) {
            entidad.setProceso(ProcesoHelper.toLevel0Entity(dto.getProceso(), null));
        }

        return entidad;
    }

    public static List<Anulacion> toListLevel0Entity(List<AnulacionDTO> listDto) {
        List<Anulacion> listEntidad = new ArrayList<Anulacion>();
        for (AnulacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Anulacion> toListLevel1Entity(List<AnulacionDTO> listDto) {
        List<Anulacion> listEntidad = new ArrayList<Anulacion>();
        for (AnulacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
