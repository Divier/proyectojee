package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoProcesoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoProceso;

/**
 * @author Generated
 * @version 3.0 - Tue Jun 14 10:14:52 COT 2016
 */
public class ComparendoProcesoHelper {
    // --- to DTO
    public static ComparendoProcesoDTO toLevel0DTO(ComparendoProceso entidad) {
        ComparendoProcesoDTO dto = new ComparendoProcesoDTO();
        dto.setId(entidad.getId());
        dto.setIdProceso(entidad.getIdProceso());

        return dto;
    }

    public static ComparendoProcesoDTO toLevel1DTO(ComparendoProceso entidad) {
        ComparendoProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));

        return dto;
    }

    public static List<ComparendoProcesoDTO> toListLevel0DTO(List<ComparendoProceso> listEntidad) {
        List<ComparendoProcesoDTO> listDto = new ArrayList<ComparendoProcesoDTO>();
        for (ComparendoProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoProcesoDTO> toListLevel1DTO(List<ComparendoProceso> listEntidad) {
        List<ComparendoProcesoDTO> listDto = new ArrayList<ComparendoProcesoDTO>();
        for (ComparendoProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoProceso toLevel0Entity(ComparendoProcesoDTO dto, ComparendoProceso entidad) {
        if (null == entidad) {
            entidad = new ComparendoProceso();
        }
        entidad.setId(dto.getId());
        entidad.setIdProceso(dto.getIdProceso());

        return entidad;
    }

    public static ComparendoProceso toLevel1Entity(ComparendoProcesoDTO dto, ComparendoProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }

        return entidad;
    }

    public static List<ComparendoProceso> toListLevel0Entity(List<ComparendoProcesoDTO> listDto) {
        List<ComparendoProceso> listEntidad = new ArrayList<ComparendoProceso>();
        for (ComparendoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoProceso> toListLevel1Entity(List<ComparendoProcesoDTO> listDto) {
        List<ComparendoProceso> listEntidad = new ArrayList<ComparendoProceso>();
        for (ComparendoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
