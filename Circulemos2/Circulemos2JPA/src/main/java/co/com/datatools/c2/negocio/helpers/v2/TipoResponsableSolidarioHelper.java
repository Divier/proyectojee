package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoResponsableSolidarioDTO;
import co.com.datatools.c2.entidades.TipoResponsableSolidario;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:12:39 COT 2015
 */
public class TipoResponsableSolidarioHelper {
    // --- to DTO
    public static TipoResponsableSolidarioDTO toLevel0DTO(TipoResponsableSolidario entidad) {
        TipoResponsableSolidarioDTO dto = new TipoResponsableSolidarioDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoResponsableSolidarioDTO toLevel1DTO(TipoResponsableSolidario entidad) {
        TipoResponsableSolidarioDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoResponsableSolidarioDTO> toListLevel0DTO(List<TipoResponsableSolidario> listEntidad) {
        List<TipoResponsableSolidarioDTO> listDto = new ArrayList<TipoResponsableSolidarioDTO>();
        for (TipoResponsableSolidario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoResponsableSolidarioDTO> toListLevel1DTO(List<TipoResponsableSolidario> listEntidad) {
        List<TipoResponsableSolidarioDTO> listDto = new ArrayList<TipoResponsableSolidarioDTO>();
        for (TipoResponsableSolidario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoResponsableSolidario toLevel0Entity(TipoResponsableSolidarioDTO dto,
            TipoResponsableSolidario entidad) {
        if (null == entidad) {
            entidad = new TipoResponsableSolidario();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoResponsableSolidario toLevel1Entity(TipoResponsableSolidarioDTO dto,
            TipoResponsableSolidario entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoResponsableSolidario> toListLevel0Entity(List<TipoResponsableSolidarioDTO> listDto) {
        List<TipoResponsableSolidario> listEntidad = new ArrayList<TipoResponsableSolidario>();
        for (TipoResponsableSolidarioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoResponsableSolidario> toListLevel1Entity(List<TipoResponsableSolidarioDTO> listDto) {
        List<TipoResponsableSolidario> listEntidad = new ArrayList<TipoResponsableSolidario>();
        for (TipoResponsableSolidarioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
