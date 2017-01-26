package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.SolicitudOrdenCompaNacioDTO;
import co.com.datatools.c2.entidades.SolicitudOrdenCompaNacio;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 13 16:09:35 COT 2016
 */
public class SolicitudOrdenCompaNacioHelper {
    // --- to DTO
    public static SolicitudOrdenCompaNacioDTO toLevel0DTO(SolicitudOrdenCompaNacio entidad) {
        SolicitudOrdenCompaNacioDTO dto = new SolicitudOrdenCompaNacioDTO();
        dto.setId(entidad.getId());
        dto.setFechaConsumo(entidad.getFechaConsumo());
        dto.setFechaImposicion(entidad.getFechaImposicion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setHoraImposicion(entidad.getHoraImposicion());
        dto.setIdAgente(entidad.getIdAgente());
        dto.setIdUsuario(entidad.getIdUsuario());
        dto.setIdentificacionVehiculo(entidad.getIdentificacionVehiculo());
        dto.setNumeroComparendo(entidad.getNumeroComparendo());
        dto.setPlacaAgente(entidad.getPlacaAgente());
        dto.setPlacaVehiculo(entidad.getPlacaVehiculo());

        return dto;
    }

    public static SolicitudOrdenCompaNacioDTO toLevel1DTO(SolicitudOrdenCompaNacio entidad) {
        SolicitudOrdenCompaNacioDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<SolicitudOrdenCompaNacioDTO> toListLevel0DTO(List<SolicitudOrdenCompaNacio> listEntidad) {
        List<SolicitudOrdenCompaNacioDTO> listDto = new ArrayList<SolicitudOrdenCompaNacioDTO>();
        for (SolicitudOrdenCompaNacio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SolicitudOrdenCompaNacioDTO> toListLevel1DTO(List<SolicitudOrdenCompaNacio> listEntidad) {
        List<SolicitudOrdenCompaNacioDTO> listDto = new ArrayList<SolicitudOrdenCompaNacioDTO>();
        for (SolicitudOrdenCompaNacio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SolicitudOrdenCompaNacio toLevel0Entity(SolicitudOrdenCompaNacioDTO dto,
            SolicitudOrdenCompaNacio entidad) {
        if (null == entidad) {
            entidad = new SolicitudOrdenCompaNacio();
        }
        entidad.setId(dto.getId());
        entidad.setFechaConsumo(dto.getFechaConsumo());
        entidad.setFechaImposicion(dto.getFechaImposicion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setHoraImposicion(dto.getHoraImposicion());
        entidad.setIdAgente(dto.getIdAgente());
        entidad.setIdUsuario(dto.getIdUsuario());
        entidad.setIdentificacionVehiculo(dto.getIdentificacionVehiculo());
        entidad.setNumeroComparendo(dto.getNumeroComparendo());
        entidad.setPlacaAgente(dto.getPlacaAgente());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());

        return entidad;
    }

    public static SolicitudOrdenCompaNacio toLevel1Entity(SolicitudOrdenCompaNacioDTO dto,
            SolicitudOrdenCompaNacio entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<SolicitudOrdenCompaNacio> toListLevel0Entity(List<SolicitudOrdenCompaNacioDTO> listDto) {
        List<SolicitudOrdenCompaNacio> listEntidad = new ArrayList<SolicitudOrdenCompaNacio>();
        for (SolicitudOrdenCompaNacioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SolicitudOrdenCompaNacio> toListLevel1Entity(List<SolicitudOrdenCompaNacioDTO> listDto) {
        List<SolicitudOrdenCompaNacio> listEntidad = new ArrayList<SolicitudOrdenCompaNacio>();
        for (SolicitudOrdenCompaNacioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
