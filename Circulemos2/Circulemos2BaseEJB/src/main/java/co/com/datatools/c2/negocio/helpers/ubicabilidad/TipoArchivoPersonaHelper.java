package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.TipoArchivoPersonaDTO;
import co.com.datatools.c2.entidades.ubicabilidad.TipoArchivoPersona;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:56 COT 2016
 */
public class TipoArchivoPersonaHelper {
    // --- to DTO
    public static TipoArchivoPersonaDTO toLevel0DTO(TipoArchivoPersona entidad) {
        TipoArchivoPersonaDTO dto = new TipoArchivoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoArchivoPersonaDTO toLevel1DTO(TipoArchivoPersona entidad) {
        TipoArchivoPersonaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoArchivoPersonaDTO> toListLevel0DTO(List<TipoArchivoPersona> listEntidad) {
        List<TipoArchivoPersonaDTO> listDto = new ArrayList<TipoArchivoPersonaDTO>();
        for (TipoArchivoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoArchivoPersonaDTO> toListLevel1DTO(List<TipoArchivoPersona> listEntidad) {
        List<TipoArchivoPersonaDTO> listDto = new ArrayList<TipoArchivoPersonaDTO>();
        for (TipoArchivoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoArchivoPersona toLevel0Entity(TipoArchivoPersonaDTO dto, TipoArchivoPersona entidad) {
        if (null == entidad) {
            entidad = new TipoArchivoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoArchivoPersona toLevel1Entity(TipoArchivoPersonaDTO dto, TipoArchivoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoArchivoPersona> toListLevel0Entity(List<TipoArchivoPersonaDTO> listDto) {
        List<TipoArchivoPersona> listEntidad = new ArrayList<TipoArchivoPersona>();
        for (TipoArchivoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoArchivoPersona> toListLevel1Entity(List<TipoArchivoPersonaDTO> listDto) {
        List<TipoArchivoPersona> listEntidad = new ArrayList<TipoArchivoPersona>();
        for (TipoArchivoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
