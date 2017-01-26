package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.entidades.ProcesaComparendo;
import co.com.datatools.c2.entidades.ProcesaEvidencia;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 20 17:09:03 COT 2015
 */
public class ProcesaEvidenciaHelper {
    // --- to DTO
    public static ProcesaEvidenciaDTO toLevel0DTO(ProcesaEvidencia entidad) {
        ProcesaEvidenciaDTO dto = new ProcesaEvidenciaDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoEvidencia(entidad.getCodigoTipoEvidencia());
        dto.setFechaEvidencia(entidad.getFechaEvidencia());
        dto.setIdDocumento(entidad.getIdDocumento());
        dto.setUrl(entidad.getUrl());
        dto.setNombreEvidencia(entidad.getNombre_evidencia());

        return dto;
    }

    public static ProcesaEvidenciaDTO toLevel1DTO(ProcesaEvidencia entidad) {
        ProcesaEvidenciaDTO dto = toLevel0DTO(entidad);
        if (entidad.getProcesaComparendo() != null)
            dto.setProcesaComparendo(ProcesaComparendoHelper.toLevel0DTO(entidad.getProcesaComparendo()));

        return dto;
    }

    public static List<ProcesaEvidenciaDTO> toListLevel0DTO(List<ProcesaEvidencia> listEntidad) {
        List<ProcesaEvidenciaDTO> listDto = new ArrayList<ProcesaEvidenciaDTO>();
        for (ProcesaEvidencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProcesaEvidenciaDTO> toListLevel1DTO(List<ProcesaEvidencia> listEntidad) {
        List<ProcesaEvidenciaDTO> listDto = new ArrayList<ProcesaEvidenciaDTO>();
        for (ProcesaEvidencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ProcesaEvidencia toLevel0Entity(ProcesaEvidenciaDTO dto, ProcesaEvidencia entidad) {
        if (null == entidad) {
            entidad = new ProcesaEvidencia();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoEvidencia(dto.getCodigoTipoEvidencia());
        entidad.setFechaEvidencia(dto.getFechaEvidencia());
        entidad.setIdDocumento(dto.getIdDocumento());

        return entidad;
    }

    public static ProcesaEvidencia toLevel1Entity(ProcesaEvidenciaDTO dto, ProcesaEvidencia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getProcesaComparendo() != null) {
            entidad.setProcesaComparendo(new ProcesaComparendo());
            entidad.getProcesaComparendo().setId(dto.getProcesaComparendo().getId());
        }

        return entidad;
    }

    public static List<ProcesaEvidencia> toListLevel0Entity(List<ProcesaEvidenciaDTO> listDto) {
        List<ProcesaEvidencia> listEntidad = new ArrayList<ProcesaEvidencia>();
        for (ProcesaEvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ProcesaEvidencia> toListLevel1Entity(List<ProcesaEvidenciaDTO> listDto) {
        List<ProcesaEvidencia> listEntidad = new ArrayList<ProcesaEvidencia>();
        for (ProcesaEvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
