package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.Evidencia;
import co.com.datatools.c2.entidades.TipoEvidencia;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:02:59 COT 2015
 */
public class EvidenciaHelper {
    // --- to DTO
    public static EvidenciaDTO toLevel0DTO(Evidencia entidad) {
        EvidenciaDTO dto = new EvidenciaDTO();
        dto.setId(entidad.getId());
        dto.setFechaEvidencia(entidad.getFechaEvidencia());
        dto.setIdDocumento(entidad.getIdDocumento());
        dto.setUrl(entidad.getUrl());
        dto.setNombreEvidencia(entidad.getNombre_evidencia());

        return dto;
    }

    public static EvidenciaDTO toLevel1DTO(Evidencia entidad) {
        EvidenciaDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getTipoEvidencia() != null)
            dto.setTipoEvidencia(TipoEvidenciaHelper.toLevel0DTO(entidad.getTipoEvidencia()));

        return dto;
    }

    public static List<EvidenciaDTO> toListLevel0DTO(List<Evidencia> listEntidad) {
        List<EvidenciaDTO> listDto = new ArrayList<EvidenciaDTO>();
        for (Evidencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EvidenciaDTO> toListLevel1DTO(List<Evidencia> listEntidad) {
        List<EvidenciaDTO> listDto = new ArrayList<EvidenciaDTO>();
        for (Evidencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Evidencia toLevel0Entity(EvidenciaDTO dto, Evidencia entidad) {
        if (null == entidad) {
            entidad = new Evidencia();
        }
        entidad.setId(dto.getId());
        entidad.setFechaEvidencia(dto.getFechaEvidencia());
        entidad.setIdDocumento(dto.getIdDocumento());

        return entidad;
    }

    public static Evidencia toLevel1Entity(EvidenciaDTO dto, Evidencia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getTipoEvidencia() != null) {
            entidad.setTipoEvidencia(new TipoEvidencia());
            entidad.getTipoEvidencia().setId(dto.getTipoEvidencia().getId());
        }

        return entidad;
    }

    public static List<Evidencia> toListLevel0Entity(List<EvidenciaDTO> listDto) {
        List<Evidencia> listEntidad = new ArrayList<Evidencia>();
        for (EvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Evidencia> toListLevel1Entity(List<EvidenciaDTO> listDto) {
        List<Evidencia> listEntidad = new ArrayList<Evidencia>();
        for (EvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
