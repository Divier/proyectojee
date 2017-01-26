package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoFuenteConsultaDTO;
import co.com.datatools.c2.entidades.TipoFuenteConsulta;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 13 14:35:44 COT 2016
 */
public class TipoFuenteConsultaHelper {
    // --- to DTO
    public static TipoFuenteConsultaDTO toLevel0DTO(TipoFuenteConsulta entidad) {
        TipoFuenteConsultaDTO dto = new TipoFuenteConsultaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoFuenteConsultaDTO toLevel1DTO(TipoFuenteConsulta entidad) {
        TipoFuenteConsultaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoFuenteConsultaDTO> toListLevel0DTO(List<TipoFuenteConsulta> listEntidad) {
        List<TipoFuenteConsultaDTO> listDto = new ArrayList<TipoFuenteConsultaDTO>();
        for (TipoFuenteConsulta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoFuenteConsultaDTO> toListLevel1DTO(List<TipoFuenteConsulta> listEntidad) {
        List<TipoFuenteConsultaDTO> listDto = new ArrayList<TipoFuenteConsultaDTO>();
        for (TipoFuenteConsulta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoFuenteConsulta toLevel0Entity(TipoFuenteConsultaDTO dto, TipoFuenteConsulta entidad) {
        if (null == entidad) {
            entidad = new TipoFuenteConsulta();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoFuenteConsulta toLevel1Entity(TipoFuenteConsultaDTO dto, TipoFuenteConsulta entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoFuenteConsulta> toListLevel0Entity(List<TipoFuenteConsultaDTO> listDto) {
        List<TipoFuenteConsulta> listEntidad = new ArrayList<TipoFuenteConsulta>();
        for (TipoFuenteConsultaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoFuenteConsulta> toListLevel1Entity(List<TipoFuenteConsultaDTO> listDto) {
        List<TipoFuenteConsulta> listEntidad = new ArrayList<TipoFuenteConsulta>();
        for (TipoFuenteConsultaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
