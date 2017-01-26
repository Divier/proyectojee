package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoProcesoDTO;
import co.com.datatools.c2.entidades.TipoProceso;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class TipoProcesoHelper {
    // --- to DTO
    public static TipoProcesoDTO toLevel0DTO(TipoProceso entidad) {
        TipoProcesoDTO dto = new TipoProcesoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoProcesoDTO toLevel1DTO(TipoProceso entidad) {
        TipoProcesoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoProcesoDTO> toListLevel0DTO(List<TipoProceso> listEntidad) {
        List<TipoProcesoDTO> listDto = new ArrayList<TipoProcesoDTO>();
        for (TipoProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoProcesoDTO> toListLevel1DTO(List<TipoProceso> listEntidad) {
        List<TipoProcesoDTO> listDto = new ArrayList<TipoProcesoDTO>();
        for (TipoProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoProceso toLevel0Entity(TipoProcesoDTO dto, TipoProceso entidad) {
        if (null == entidad) {
            entidad = new TipoProceso();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoProceso toLevel1Entity(TipoProcesoDTO dto, TipoProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoProceso> toListLevel0Entity(List<TipoProcesoDTO> listDto) {
        List<TipoProceso> listEntidad = new ArrayList<TipoProceso>();
        for (TipoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoProceso> toListLevel1Entity(List<TipoProcesoDTO> listDto) {
        List<TipoProceso> listEntidad = new ArrayList<TipoProceso>();
        for (TipoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
