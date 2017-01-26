package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.entidades.DetalleNumeracion;
import co.com.datatools.c2.entidades.NumeracionFormulario;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 18:07:32 COT 2015
 */
public class DetalleNumeracionHelper {
    // --- to DTO
    public static DetalleNumeracionDTO toLevel0DTO(DetalleNumeracion entidad) {
        DetalleNumeracionDTO dto = new DetalleNumeracionDTO();
        dto.setId(entidad.getId());
        dto.setDigito(entidad.getDigito());
        dto.setCaracterInicio(entidad.getCaracterInicio());
        dto.setCaracterFin(entidad.getCaracterFin());
        dto.setIncremental(entidad.getIncremental());
        dto.setNumerico(entidad.getNumerico());

        return dto;
    }

    public static DetalleNumeracionDTO toLevel1DTO(DetalleNumeracion entidad) {
        DetalleNumeracionDTO dto = toLevel0DTO(entidad);
        if (entidad.getNumeracion() != null)
            dto.setNumeracion(NumeracionFormularioHelper.toLevel0DTO(entidad.getNumeracion()));

        return dto;
    }

    public static List<DetalleNumeracionDTO> toListLevel0DTO(List<DetalleNumeracion> listEntidad) {
        List<DetalleNumeracionDTO> listDto = new ArrayList<DetalleNumeracionDTO>();
        for (DetalleNumeracion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleNumeracionDTO> toListLevel1DTO(List<DetalleNumeracion> listEntidad) {
        List<DetalleNumeracionDTO> listDto = new ArrayList<DetalleNumeracionDTO>();
        for (DetalleNumeracion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleNumeracion toLevel0Entity(DetalleNumeracionDTO dto, DetalleNumeracion entidad) {
        if (null == entidad) {
            entidad = new DetalleNumeracion();
        }
        entidad.setId(dto.getId());
        entidad.setDigito(dto.getDigito());
        entidad.setCaracterInicio(dto.getCaracterInicio());
        entidad.setCaracterFin(dto.getCaracterFin());
        entidad.setIncremental(dto.getIncremental());
        entidad.setNumerico(dto.getNumerico());

        return entidad;
    }

    public static DetalleNumeracion toLevel1Entity(DetalleNumeracionDTO dto, DetalleNumeracion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getNumeracion() != null) {
            entidad.setNumeracion(new NumeracionFormulario());
            entidad.getNumeracion().setId(dto.getNumeracion().getId());
        }

        return entidad;
    }

    public static List<DetalleNumeracion> toListLevel0Entity(List<DetalleNumeracionDTO> listDto) {
        List<DetalleNumeracion> listEntidad = new ArrayList<DetalleNumeracion>();
        for (DetalleNumeracionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleNumeracion> toListLevel1Entity(List<DetalleNumeracionDTO> listDto) {
        List<DetalleNumeracion> listEntidad = new ArrayList<DetalleNumeracion>();
        for (DetalleNumeracionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
