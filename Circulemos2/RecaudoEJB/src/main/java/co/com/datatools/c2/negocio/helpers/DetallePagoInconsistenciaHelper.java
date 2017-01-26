package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetallePagoInconsistenciaDTO;
import co.com.datatools.c2.entidades.DetallePagoInconsistencia;
import co.com.datatools.c2.entidades.PagoInconsistencia;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 27 12:13:21 COT 2016
 */
public class DetallePagoInconsistenciaHelper {
    // --- to DTO
    public static DetallePagoInconsistenciaDTO toLevel0DTO(DetallePagoInconsistencia entidad) {
        DetallePagoInconsistenciaDTO dto = new DetallePagoInconsistenciaDTO();
        dto.setId(entidad.getId());
        dto.setIdConceptoCartera(entidad.getIdConcepto());
        dto.setIdTipoRecaudo(entidad.getIdTipoRecaudo());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setValorObligacion(entidad.getValorObligacion());

        return dto;
    }

    public static DetallePagoInconsistenciaDTO toLevel1DTO(DetallePagoInconsistencia entidad) {
        DetallePagoInconsistenciaDTO dto = toLevel0DTO(entidad);
        if (entidad.getPagoInconsistencia() != null)
            dto.setPagoInconsistencia(PagoInconsistenciaHelper.toLevel0DTO(entidad.getPagoInconsistencia()));

        return dto;
    }

    public static List<DetallePagoInconsistenciaDTO> toListLevel0DTO(List<DetallePagoInconsistencia> listEntidad) {
        List<DetallePagoInconsistenciaDTO> listDto = new ArrayList<DetallePagoInconsistenciaDTO>();
        for (DetallePagoInconsistencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetallePagoInconsistenciaDTO> toListLevel1DTO(List<DetallePagoInconsistencia> listEntidad) {
        List<DetallePagoInconsistenciaDTO> listDto = new ArrayList<DetallePagoInconsistenciaDTO>();
        for (DetallePagoInconsistencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetallePagoInconsistencia toLevel0Entity(DetallePagoInconsistenciaDTO dto,
            DetallePagoInconsistencia entidad) {
        if (null == entidad) {
            entidad = new DetallePagoInconsistencia();
        }
        entidad.setId(dto.getId());
        entidad.setIdConcepto(dto.getIdConceptoCartera());
        entidad.setIdTipoRecaudo(dto.getIdTipoRecaudo());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setValorObligacion(dto.getValorObligacion());

        return entidad;
    }

    public static DetallePagoInconsistencia toLevel1Entity(DetallePagoInconsistenciaDTO dto,
            DetallePagoInconsistencia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPagoInconsistencia() != null) {
            entidad.setPagoInconsistencia(new PagoInconsistencia());
            entidad.getPagoInconsistencia().setId(dto.getPagoInconsistencia().getId());
        }

        return entidad;
    }

    public static List<DetallePagoInconsistencia> toListLevel0Entity(List<DetallePagoInconsistenciaDTO> listDto) {
        List<DetallePagoInconsistencia> listEntidad = new ArrayList<DetallePagoInconsistencia>();
        for (DetallePagoInconsistenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetallePagoInconsistencia> toListLevel1Entity(List<DetallePagoInconsistenciaDTO> listDto) {
        List<DetallePagoInconsistencia> listEntidad = new ArrayList<DetallePagoInconsistencia>();
        for (DetallePagoInconsistenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
