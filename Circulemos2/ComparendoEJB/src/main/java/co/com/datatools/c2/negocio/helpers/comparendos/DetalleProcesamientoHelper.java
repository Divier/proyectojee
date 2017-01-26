package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.entidades.CampoEntidad;
import co.com.datatools.c2.entidades.DetalleProcesamiento;
import co.com.datatools.c2.entidades.ErrorProcesamiento;
import co.com.datatools.c2.entidades.ProcesaComparendo;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 17:17:33 COT 2015
 */
public class DetalleProcesamientoHelper {
    // --- to DTO
    public static DetalleProcesamientoDTO toLevel0DTO(DetalleProcesamiento entidad) {
        DetalleProcesamientoDTO dto = new DetalleProcesamientoDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static DetalleProcesamientoDTO toLevel1DTO(DetalleProcesamiento entidad) {
        DetalleProcesamientoDTO dto = toLevel0DTO(entidad);
        if (entidad.getCampoEntidad() != null)
            dto.setCampoEntidad(CampoEntidadHelper.toLevel0DTO(entidad.getCampoEntidad()));
        if (entidad.getErrorProcesamiento() != null)
            dto.setErrorProcesamiento(ErrorProcesamientoHelper.toLevel0DTO(entidad.getErrorProcesamiento()));
        if (entidad.getProcesaComparendo() != null)
            dto.setProcesaComparendo(ProcesaComparendoHelper.toLevel0DTO(entidad.getProcesaComparendo()));

        return dto;
    }

    public static List<DetalleProcesamientoDTO> toListLevel0DTO(List<DetalleProcesamiento> listEntidad) {
        List<DetalleProcesamientoDTO> listDto = new ArrayList<DetalleProcesamientoDTO>();
        for (DetalleProcesamiento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleProcesamientoDTO> toListLevel1DTO(List<DetalleProcesamiento> listEntidad) {
        List<DetalleProcesamientoDTO> listDto = new ArrayList<DetalleProcesamientoDTO>();
        for (DetalleProcesamiento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleProcesamiento toLevel0Entity(DetalleProcesamientoDTO dto, DetalleProcesamiento entidad) {
        if (null == entidad) {
            entidad = new DetalleProcesamiento();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static DetalleProcesamiento toLevel1Entity(DetalleProcesamientoDTO dto, DetalleProcesamiento entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCampoEntidad() != null) {
            entidad.setCampoEntidad(new CampoEntidad());
            entidad.getCampoEntidad().setCodigo(dto.getCampoEntidad().getCodigo());
        }
        if (dto.getErrorProcesamiento() != null) {
            entidad.setErrorProcesamiento(new ErrorProcesamiento());
            entidad.getErrorProcesamiento().setId(dto.getErrorProcesamiento().getId());
        }
        if (dto.getProcesaComparendo() != null) {
            entidad.setProcesaComparendo(new ProcesaComparendo());
            entidad.getProcesaComparendo().setId(dto.getProcesaComparendo().getId());
        }

        return entidad;
    }

    public static List<DetalleProcesamiento> toListLevel0Entity(List<DetalleProcesamientoDTO> listDto) {
        List<DetalleProcesamiento> listEntidad = new ArrayList<DetalleProcesamiento>();
        for (DetalleProcesamientoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleProcesamiento> toListLevel1Entity(List<DetalleProcesamientoDTO> listDto) {
        List<DetalleProcesamiento> listEntidad = new ArrayList<DetalleProcesamiento>();
        for (DetalleProcesamientoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
