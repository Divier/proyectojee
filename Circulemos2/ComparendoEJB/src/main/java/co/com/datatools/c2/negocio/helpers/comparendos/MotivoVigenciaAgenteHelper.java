package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.entidades.MotivoVigenciaAgente;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 27 10:28:51 COT 2016
 */
public class MotivoVigenciaAgenteHelper {
    // --- to DTO
    public static MotivoVigenciaAgenteDTO toLevel0DTO(MotivoVigenciaAgente entidad) {
        MotivoVigenciaAgenteDTO dto = new MotivoVigenciaAgenteDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static MotivoVigenciaAgenteDTO toLevel1DTO(MotivoVigenciaAgente entidad) {
        MotivoVigenciaAgenteDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<MotivoVigenciaAgenteDTO> toListLevel0DTO(List<MotivoVigenciaAgente> listEntidad) {
        List<MotivoVigenciaAgenteDTO> listDto = new ArrayList<MotivoVigenciaAgenteDTO>();
        for (MotivoVigenciaAgente entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<MotivoVigenciaAgenteDTO> toListLevel1DTO(List<MotivoVigenciaAgente> listEntidad) {
        List<MotivoVigenciaAgenteDTO> listDto = new ArrayList<MotivoVigenciaAgenteDTO>();
        for (MotivoVigenciaAgente entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static MotivoVigenciaAgente toLevel0Entity(MotivoVigenciaAgenteDTO dto, MotivoVigenciaAgente entidad) {
        if (null == entidad) {
            entidad = new MotivoVigenciaAgente();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static MotivoVigenciaAgente toLevel1Entity(MotivoVigenciaAgenteDTO dto, MotivoVigenciaAgente entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<MotivoVigenciaAgente> toListLevel0Entity(List<MotivoVigenciaAgenteDTO> listDto) {
        List<MotivoVigenciaAgente> listEntidad = new ArrayList<MotivoVigenciaAgente>();
        for (MotivoVigenciaAgenteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<MotivoVigenciaAgente> toListLevel1Entity(List<MotivoVigenciaAgenteDTO> listDto) {
        List<MotivoVigenciaAgente> listEntidad = new ArrayList<MotivoVigenciaAgente>();
        for (MotivoVigenciaAgenteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
