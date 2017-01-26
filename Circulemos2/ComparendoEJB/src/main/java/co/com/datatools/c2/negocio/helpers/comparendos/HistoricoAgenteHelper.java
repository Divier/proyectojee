package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.HistoricoAgenteDTO;
import co.com.datatools.c2.entidades.HistoricoAgente;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 13 14:53:53 COT 2015
 */
public class HistoricoAgenteHelper {
    // --- to DTO
    public static HistoricoAgenteDTO toLevel0DTO(HistoricoAgente entidad) {
        HistoricoAgenteDTO dto = new HistoricoAgenteDTO();
        dto.setId(entidad.getId());
        dto.setFechaFinVigencia(entidad.getFechaFinVigencia());
        dto.setFechaInicioVigencia(entidad.getFechaInicioVigencia());
        dto.setEntidadAgenteTransito(entidad.getEntidadAgenteTransito());
        dto.setPersona(entidad.getPersona());
        dto.setPlaca(entidad.getPlaca());
        dto.setOrganismoTransito(entidad.getOrganismoTransito());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setUsuarioActualizacion(entidad.getUsuarioActualizacion());
        dto.setMotivoVigenciaAgente(entidad.getMotivoVigenciaAgente());

        return dto;
    }

    public static HistoricoAgenteDTO toLevel1DTO(HistoricoAgente entidad) {
        HistoricoAgenteDTO dto = toLevel0DTO(entidad);
        if (entidad.getAgente() != null)
            dto.setAgente(AgenteHelper.toLevel0DTO(entidad.getAgente()));

        return dto;
    }

    public static List<HistoricoAgenteDTO> toListLevel0DTO(List<HistoricoAgente> listEntidad) {
        List<HistoricoAgenteDTO> listDto = new ArrayList<HistoricoAgenteDTO>();
        for (HistoricoAgente entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<HistoricoAgenteDTO> toListLevel1DTO(List<HistoricoAgente> listEntidad) {
        List<HistoricoAgenteDTO> listDto = new ArrayList<HistoricoAgenteDTO>();
        for (HistoricoAgente entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static HistoricoAgente toLevel0Entity(HistoricoAgenteDTO dto, HistoricoAgente entidad) {
        if (null == entidad) {
            entidad = new HistoricoAgente();
        }
        entidad.setId(dto.getId());
        entidad.setFechaFinVigencia(dto.getFechaFinVigencia());
        entidad.setFechaInicioVigencia(dto.getFechaInicioVigencia());
        entidad.setEntidadAgenteTransito(dto.getEntidadAgenteTransito());
        entidad.setPersona(dto.getPersona());
        entidad.setPlaca(dto.getPlaca());
        entidad.setOrganismoTransito(dto.getOrganismoTransito());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setUsuarioActualizacion(dto.getUsuarioActualizacion());
        entidad.setMotivoVigenciaAgente(dto.getMotivoVigenciaAgente());

        return entidad;
    }

    public static HistoricoAgente toLevel1Entity(HistoricoAgenteDTO dto, HistoricoAgente entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getAgente() != null)
            entidad.setAgente(AgenteHelper.toLevel0Entity(dto.getAgente(), null));

        return entidad;
    }

    public static List<HistoricoAgente> toListLevel0Entity(List<HistoricoAgenteDTO> listDto) {
        List<HistoricoAgente> listEntidad = new ArrayList<HistoricoAgente>();
        for (HistoricoAgenteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<HistoricoAgente> toListLevel1Entity(List<HistoricoAgenteDTO> listDto) {
        List<HistoricoAgente> listEntidad = new ArrayList<HistoricoAgente>();
        for (HistoricoAgenteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
