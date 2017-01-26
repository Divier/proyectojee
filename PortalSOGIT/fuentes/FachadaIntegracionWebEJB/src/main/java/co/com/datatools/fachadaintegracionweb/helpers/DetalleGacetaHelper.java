package co.com.datatools.fachadaintegracionweb.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.fachadainetegracionweb.dto.DetalleGacetaDTO;
import co.com.datatools.fachadaintegracionweb.entidades.DetalleGaceta;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DetalleGacetaHelper {
    // --- to DTO
    public static DetalleGacetaDTO toLevel0DTO(DetalleGaceta entidad) {
        DetalleGacetaDTO dto = new DetalleGacetaDTO();
        dto.setId(entidad.getId());
        dto.setPlacaVehiculo(entidad.getPlacaVehiculo());
        dto.setPropietario(entidad.getPropietario());
        dto.setDescripcionInfraccion(entidad.getDescripcionInfraccion());
        dto.setLugarInfraccion(entidad.getLugarInfraccion());
        dto.setFechaImposicion(entidad.getFechaImposicion());
        dto.setValor(entidad.getValor());

        return dto;
    }

    public static DetalleGacetaDTO toLevel1DTO(DetalleGaceta entidad) {
        DetalleGacetaDTO dto = toLevel0DTO(entidad);
        if (entidad.getGaceta() != null)
            dto.setGaceta(GacetaHelper.toLevel0DTO(entidad.getGaceta()));

        return dto;
    }

    public static List<DetalleGacetaDTO> toListLevel0DTO(List<DetalleGaceta> listEntidad) {
        List<DetalleGacetaDTO> listDto = new ArrayList<DetalleGacetaDTO>();
        for (DetalleGaceta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleGacetaDTO> toListLevel1DTO(List<DetalleGaceta> listEntidad) {
        List<DetalleGacetaDTO> listDto = new ArrayList<DetalleGacetaDTO>();
        for (DetalleGaceta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Gaceta
    public static DetalleGaceta toLevel0Entity(DetalleGacetaDTO dto, DetalleGaceta entidad) {
        if (null == entidad) {
            entidad = new DetalleGaceta();
        }
        entidad.setId(dto.getId());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        entidad.setPropietario(dto.getPropietario());
        entidad.setDescripcionInfraccion(dto.getDescripcionInfraccion());
        entidad.setLugarInfraccion(dto.getLugarInfraccion());
        entidad.setFechaImposicion(dto.getFechaImposicion());
        entidad.setValor(dto.getValor());

        return entidad;
    }

    public static DetalleGaceta toLevel1Entity(DetalleGacetaDTO dto, DetalleGaceta entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getGaceta() != null)
            entidad.setGaceta(GacetaHelper.toLevel1Entity(dto.getGaceta(), null));

        return entidad;
    }

    public static List<DetalleGaceta> toListLevel0Entity(List<DetalleGacetaDTO> listDto) {
        List<DetalleGaceta> listEntidad = new ArrayList<DetalleGaceta>();
        for (DetalleGacetaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleGaceta> toListLevel1Entity(List<DetalleGacetaDTO> listDto) {
        List<DetalleGaceta> listEntidad = new ArrayList<DetalleGaceta>();
        for (DetalleGacetaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
