package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoAgenteImpositorDTO;
import co.com.datatools.c2.entidades.TipoAgenteImpositor;

/**
 * @author Generated
 * @version 3.0 - Fri May 06 16:18:55 COT 2016
 */
public class TipoAgenteImpositorHelper {
    // --- to DTO
    public static TipoAgenteImpositorDTO toLevel0DTO(TipoAgenteImpositor entidad) {
        TipoAgenteImpositorDTO dto = new TipoAgenteImpositorDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoAgenteImpositorDTO toLevel1DTO(TipoAgenteImpositor entidad) {
        TipoAgenteImpositorDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoAgenteImpositorDTO> toListLevel0DTO(List<TipoAgenteImpositor> listEntidad) {
        List<TipoAgenteImpositorDTO> listDto = new ArrayList<TipoAgenteImpositorDTO>();
        for (TipoAgenteImpositor entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoAgenteImpositorDTO> toListLevel1DTO(List<TipoAgenteImpositor> listEntidad) {
        List<TipoAgenteImpositorDTO> listDto = new ArrayList<TipoAgenteImpositorDTO>();
        for (TipoAgenteImpositor entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoAgenteImpositor toLevel0Entity(TipoAgenteImpositorDTO dto, TipoAgenteImpositor entidad) {
        if (null == entidad) {
            entidad = new TipoAgenteImpositor();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoAgenteImpositor toLevel1Entity(TipoAgenteImpositorDTO dto, TipoAgenteImpositor entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoAgenteImpositor> toListLevel0Entity(List<TipoAgenteImpositorDTO> listDto) {
        List<TipoAgenteImpositor> listEntidad = new ArrayList<TipoAgenteImpositor>();
        for (TipoAgenteImpositorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoAgenteImpositor> toListLevel1Entity(List<TipoAgenteImpositorDTO> listDto) {
        List<TipoAgenteImpositor> listEntidad = new ArrayList<TipoAgenteImpositor>();
        for (TipoAgenteImpositorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
