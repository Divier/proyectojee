package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.FuenteConsultaDTO;
import co.com.datatools.c2.entidades.FuenteConsulta;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 13 10:42:12 COT 2016
 */
public class FuenteConsultaHelper {
    // --- to DTO
    public static FuenteConsultaDTO toLevel0DTO(FuenteConsulta entidad) {
        FuenteConsultaDTO dto = new FuenteConsultaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static FuenteConsultaDTO toLevel1DTO(FuenteConsulta entidad) {
        FuenteConsultaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<FuenteConsultaDTO> toListLevel0DTO(List<FuenteConsulta> listEntidad) {
        List<FuenteConsultaDTO> listDto = new ArrayList<FuenteConsultaDTO>();
        for (FuenteConsulta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FuenteConsultaDTO> toListLevel1DTO(List<FuenteConsulta> listEntidad) {
        List<FuenteConsultaDTO> listDto = new ArrayList<FuenteConsultaDTO>();
        for (FuenteConsulta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static FuenteConsulta toLevel0Entity(FuenteConsultaDTO dto, FuenteConsulta entidad) {
        if (null == entidad) {
            entidad = new FuenteConsulta();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static FuenteConsulta toLevel1Entity(FuenteConsultaDTO dto, FuenteConsulta entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<FuenteConsulta> toListLevel0Entity(List<FuenteConsultaDTO> listDto) {
        List<FuenteConsulta> listEntidad = new ArrayList<FuenteConsulta>();
        for (FuenteConsultaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<FuenteConsulta> toListLevel1Entity(List<FuenteConsultaDTO> listDto) {
        List<FuenteConsulta> listEntidad = new ArrayList<FuenteConsulta>();
        for (FuenteConsultaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
