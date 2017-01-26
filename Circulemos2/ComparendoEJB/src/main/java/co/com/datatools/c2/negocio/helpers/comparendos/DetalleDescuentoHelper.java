package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.DetalleDescuentoDTO;
import co.com.datatools.c2.entidades.ConfiguracionDescuento;
import co.com.datatools.c2.entidades.DetalleDescuento;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 27 10:10:46 COT 2016
 */
public class DetalleDescuentoHelper {
    // --- to DTO
    public static DetalleDescuentoDTO toLevel0DTO(DetalleDescuento entidad) {
        DetalleDescuentoDTO dto = new DetalleDescuentoDTO();
        dto.setId(entidad.getId());
        dto.setPorcentajeDescuento(entidad.getPorcentajeDescuento());
        dto.setDiasFin(entidad.getDiasFin());
        dto.setDiasInicio(entidad.getDiasInicio());
        dto.setIdConcepto(entidad.getIdConcepto());

        return dto;
    }

    public static DetalleDescuentoDTO toLevel1DTO(DetalleDescuento entidad) {
        DetalleDescuentoDTO dto = toLevel0DTO(entidad);
        if (entidad.getConfiguracionDescuento() != null)
            dto.setConfiguracionDescuento(
                    ConfiguracionDescuentoHelper.toLevel0DTO(entidad.getConfiguracionDescuento()));

        return dto;
    }

    public static List<DetalleDescuentoDTO> toListLevel0DTO(List<DetalleDescuento> listEntidad) {
        List<DetalleDescuentoDTO> listDto = new ArrayList<DetalleDescuentoDTO>();
        for (DetalleDescuento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleDescuentoDTO> toListLevel1DTO(List<DetalleDescuento> listEntidad) {
        List<DetalleDescuentoDTO> listDto = new ArrayList<DetalleDescuentoDTO>();
        for (DetalleDescuento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleDescuento toLevel0Entity(DetalleDescuentoDTO dto, DetalleDescuento entidad) {
        if (null == entidad) {
            entidad = new DetalleDescuento();
        }
        entidad.setId(dto.getId());
        entidad.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        entidad.setDiasFin(dto.getDiasFin());
        entidad.setDiasInicio(dto.getDiasInicio());
        entidad.setIdConcepto(dto.getIdConcepto());

        return entidad;
    }

    public static DetalleDescuento toLevel1Entity(DetalleDescuentoDTO dto, DetalleDescuento entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfiguracionDescuento() != null) {
            entidad.setConfiguracionDescuento(new ConfiguracionDescuento());
            entidad.getConfiguracionDescuento().setId(dto.getConfiguracionDescuento().getId());
        }

        return entidad;
    }

    public static List<DetalleDescuento> toListLevel0Entity(List<DetalleDescuentoDTO> listDto) {
        List<DetalleDescuento> listEntidad = new ArrayList<DetalleDescuento>();
        for (DetalleDescuentoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleDescuento> toListLevel1Entity(List<DetalleDescuentoDTO> listDto) {
        List<DetalleDescuento> listEntidad = new ArrayList<DetalleDescuento>();
        for (DetalleDescuentoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
