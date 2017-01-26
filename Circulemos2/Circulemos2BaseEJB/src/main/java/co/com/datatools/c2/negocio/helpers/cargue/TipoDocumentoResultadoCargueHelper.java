package co.com.datatools.c2.negocio.helpers.cargue;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cargue.TipoDocumentoResultadoCargueDTO;
import co.com.datatools.c2.entidades.cargue.TipoDocumentoResultadoCargue;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 22 17:39:45 COT 2016
 */
public class TipoDocumentoResultadoCargueHelper {
    // --- to DTO
    public static TipoDocumentoResultadoCargueDTO toLevel0DTO(TipoDocumentoResultadoCargue entidad) {
        TipoDocumentoResultadoCargueDTO dto = new TipoDocumentoResultadoCargueDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoDocumentoResultadoCargueDTO toLevel1DTO(TipoDocumentoResultadoCargue entidad) {
        TipoDocumentoResultadoCargueDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoDocumentoResultadoCargueDTO> toListLevel0DTO(
            List<TipoDocumentoResultadoCargue> listEntidad) {
        List<TipoDocumentoResultadoCargueDTO> listDto = new ArrayList<TipoDocumentoResultadoCargueDTO>();
        for (TipoDocumentoResultadoCargue entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoDocumentoResultadoCargueDTO> toListLevel1DTO(
            List<TipoDocumentoResultadoCargue> listEntidad) {
        List<TipoDocumentoResultadoCargueDTO> listDto = new ArrayList<TipoDocumentoResultadoCargueDTO>();
        for (TipoDocumentoResultadoCargue entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoDocumentoResultadoCargue toLevel0Entity(TipoDocumentoResultadoCargueDTO dto,
            TipoDocumentoResultadoCargue entidad) {
        if (null == entidad) {
            entidad = new TipoDocumentoResultadoCargue();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoDocumentoResultadoCargue toLevel1Entity(TipoDocumentoResultadoCargueDTO dto,
            TipoDocumentoResultadoCargue entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoDocumentoResultadoCargue> toListLevel0Entity(List<TipoDocumentoResultadoCargueDTO> listDto) {
        List<TipoDocumentoResultadoCargue> listEntidad = new ArrayList<TipoDocumentoResultadoCargue>();
        for (TipoDocumentoResultadoCargueDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoDocumentoResultadoCargue> toListLevel1Entity(List<TipoDocumentoResultadoCargueDTO> listDto) {
        List<TipoDocumentoResultadoCargue> listEntidad = new ArrayList<TipoDocumentoResultadoCargue>();
        for (TipoDocumentoResultadoCargueDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
