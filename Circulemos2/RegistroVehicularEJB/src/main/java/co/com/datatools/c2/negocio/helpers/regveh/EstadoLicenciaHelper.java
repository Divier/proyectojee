package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.EstadoLicenciaDTO;
import co.com.datatools.c2.entidades.EstadoLicencia;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:07:28 COT 2015
 */
public class EstadoLicenciaHelper {
    // --- to DTO
    public static EstadoLicenciaDTO toLevel0DTO(EstadoLicencia entidad) {
        EstadoLicenciaDTO dto = new EstadoLicenciaDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static EstadoLicenciaDTO toLevel1DTO(EstadoLicencia entidad) {
        EstadoLicenciaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoLicenciaDTO> toListLevel0DTO(List<EstadoLicencia> listEntidad) {
        List<EstadoLicenciaDTO> listDto = new ArrayList<EstadoLicenciaDTO>();
        for (EstadoLicencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoLicenciaDTO> toListLevel1DTO(List<EstadoLicencia> listEntidad) {
        List<EstadoLicenciaDTO> listDto = new ArrayList<EstadoLicenciaDTO>();
        for (EstadoLicencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoLicencia toLevel0Entity(EstadoLicenciaDTO dto, EstadoLicencia entidad) {
        if (null == entidad) {
            entidad = new EstadoLicencia();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static EstadoLicencia toLevel1Entity(EstadoLicenciaDTO dto, EstadoLicencia entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoLicencia> toListLevel0Entity(List<EstadoLicenciaDTO> listDto) {
        List<EstadoLicencia> listEntidad = new ArrayList<EstadoLicencia>();
        for (EstadoLicenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoLicencia> toListLevel1Entity(List<EstadoLicenciaDTO> listDto) {
        List<EstadoLicencia> listEntidad = new ArrayList<EstadoLicencia>();
        for (EstadoLicenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
