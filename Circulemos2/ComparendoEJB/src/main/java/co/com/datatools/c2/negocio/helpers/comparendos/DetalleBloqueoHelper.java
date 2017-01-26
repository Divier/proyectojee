package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.DetalleBloqueoDTO;
import co.com.datatools.c2.entidades.BloqueoComparendo;
import co.com.datatools.c2.entidades.CampoEntidad;
import co.com.datatools.c2.entidades.DetalleBloqueo;
import co.com.datatools.c2.entidades.ErrorProcesamiento;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 07:55:33 COT 2016
 */
public class DetalleBloqueoHelper {
    // --- to DTO
    public static DetalleBloqueoDTO toLevel0DTO(DetalleBloqueo entidad) {
        DetalleBloqueoDTO dto = new DetalleBloqueoDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static DetalleBloqueoDTO toLevel1DTO(DetalleBloqueo entidad) {
        DetalleBloqueoDTO dto = toLevel0DTO(entidad);
        if (entidad.getBloqueoComparendo() != null)
            dto.setBloqueoComparendo(BloqueoComparendoHelper.toLevel0DTO(entidad.getBloqueoComparendo()));
        if (entidad.getErrorProcesamiento() != null)
            dto.setErrorProcesamiento(ErrorProcesamientoHelper.toLevel0DTO(entidad.getErrorProcesamiento()));
        if (entidad.getCampoEntidad() != null)
            dto.setCampoEntidad(CampoEntidadHelper.toLevel0DTO(entidad.getCampoEntidad()));

        return dto;
    }

    public static List<DetalleBloqueoDTO> toListLevel0DTO(List<DetalleBloqueo> listEntidad) {
        List<DetalleBloqueoDTO> listDto = new ArrayList<DetalleBloqueoDTO>();
        for (DetalleBloqueo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleBloqueoDTO> toListLevel1DTO(List<DetalleBloqueo> listEntidad) {
        List<DetalleBloqueoDTO> listDto = new ArrayList<DetalleBloqueoDTO>();
        for (DetalleBloqueo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleBloqueo toLevel0Entity(DetalleBloqueoDTO dto, DetalleBloqueo entidad) {
        if (null == entidad) {
            entidad = new DetalleBloqueo();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static DetalleBloqueo toLevel1Entity(DetalleBloqueoDTO dto, DetalleBloqueo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getBloqueoComparendo() != null) {
            entidad.setBloqueoComparendo(new BloqueoComparendo());
            entidad.getBloqueoComparendo().setId(dto.getBloqueoComparendo().getId());
        }
        if (dto.getErrorProcesamiento() != null) {
            entidad.setErrorProcesamiento(new ErrorProcesamiento());
            entidad.getErrorProcesamiento().setId(dto.getErrorProcesamiento().getId());
        }
        if (dto.getCampoEntidad() != null) {
            entidad.setCampoEntidad(new CampoEntidad());
            entidad.getCampoEntidad().setCodigo(dto.getCampoEntidad().getCodigo());
        }

        return entidad;
    }

    public static List<DetalleBloqueo> toListLevel0Entity(List<DetalleBloqueoDTO> listDto) {
        List<DetalleBloqueo> listEntidad = new ArrayList<DetalleBloqueo>();
        for (DetalleBloqueoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleBloqueo> toListLevel1Entity(List<DetalleBloqueoDTO> listDto) {
        List<DetalleBloqueo> listEntidad = new ArrayList<DetalleBloqueo>();
        for (DetalleBloqueoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}