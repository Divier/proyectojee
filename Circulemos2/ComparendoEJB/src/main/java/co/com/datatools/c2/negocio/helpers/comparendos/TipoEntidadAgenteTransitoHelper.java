package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoEntidadAgenteTransitoDTO;
import co.com.datatools.c2.entidades.TipoEntidadAgenteTransito;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoEntidadAgenteTransitoHelper {
    // --- to DTO
    public static TipoEntidadAgenteTransitoDTO toLevel0DTO(TipoEntidadAgenteTransito entidad) {
        TipoEntidadAgenteTransitoDTO dto = new TipoEntidadAgenteTransitoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoEntidadAgenteTransitoDTO toLevel1DTO(TipoEntidadAgenteTransito entidad) {
        TipoEntidadAgenteTransitoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<TipoEntidadAgenteTransitoDTO> toListLevel0DTO(List<TipoEntidadAgenteTransito> listEntidad) {
        List<TipoEntidadAgenteTransitoDTO> listDto = new ArrayList<TipoEntidadAgenteTransitoDTO>();
        for (TipoEntidadAgenteTransito entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoEntidadAgenteTransitoDTO> toListLevel1DTO(List<TipoEntidadAgenteTransito> listEntidad) {
        List<TipoEntidadAgenteTransitoDTO> listDto = new ArrayList<TipoEntidadAgenteTransitoDTO>();
        for (TipoEntidadAgenteTransito entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoEntidadAgenteTransito toLevel0Entity(TipoEntidadAgenteTransitoDTO dto,
            TipoEntidadAgenteTransito entidad) {
        if (null == entidad) {
            entidad = new TipoEntidadAgenteTransito();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoEntidadAgenteTransito toLevel1Entity(TipoEntidadAgenteTransitoDTO dto,
            TipoEntidadAgenteTransito entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<TipoEntidadAgenteTransito> toListLevel0Entity(List<TipoEntidadAgenteTransitoDTO> listDto) {
        List<TipoEntidadAgenteTransito> listEntidad = new ArrayList<TipoEntidadAgenteTransito>();
        for (TipoEntidadAgenteTransitoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoEntidadAgenteTransito> toListLevel1Entity(List<TipoEntidadAgenteTransitoDTO> listDto) {
        List<TipoEntidadAgenteTransito> listEntidad = new ArrayList<TipoEntidadAgenteTransito>();
        for (TipoEntidadAgenteTransitoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
