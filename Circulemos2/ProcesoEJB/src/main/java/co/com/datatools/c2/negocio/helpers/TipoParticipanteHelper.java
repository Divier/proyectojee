package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoParticipanteDTO;
import co.com.datatools.c2.entidades.TipoParticipante;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class TipoParticipanteHelper {
    // --- to DTO
    public static TipoParticipanteDTO toLevel0DTO(TipoParticipante entidad) {
        TipoParticipanteDTO dto = new TipoParticipanteDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoParticipanteDTO toLevel1DTO(TipoParticipante entidad) {
        TipoParticipanteDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoParticipanteDTO> toListLevel0DTO(List<TipoParticipante> listEntidad) {
        List<TipoParticipanteDTO> listDto = new ArrayList<TipoParticipanteDTO>();
        for (TipoParticipante entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoParticipanteDTO> toListLevel1DTO(List<TipoParticipante> listEntidad) {
        List<TipoParticipanteDTO> listDto = new ArrayList<TipoParticipanteDTO>();
        for (TipoParticipante entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoParticipante toLevel0Entity(TipoParticipanteDTO dto, TipoParticipante entidad) {
        if (null == entidad) {
            entidad = new TipoParticipante();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoParticipante toLevel1Entity(TipoParticipanteDTO dto, TipoParticipante entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoParticipante> toListLevel0Entity(List<TipoParticipanteDTO> listDto) {
        List<TipoParticipante> listEntidad = new ArrayList<TipoParticipante>();
        for (TipoParticipanteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoParticipante> toListLevel1Entity(List<TipoParticipanteDTO> listDto) {
        List<TipoParticipante> listEntidad = new ArrayList<TipoParticipante>();
        for (TipoParticipanteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
