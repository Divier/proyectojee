package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.DetalleCantidadCuota;
import co.com.datatools.c2.entidades.VariableCondicionFinan;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:06:38 COT 2016
 */
public class DetalleCantidadCuotaHelper {
    // --- to DTO
    public static DetalleCantidadCuotaDTO toLevel0DTO(DetalleCantidadCuota entidad) {
        DetalleCantidadCuotaDTO dto = new DetalleCantidadCuotaDTO();
        dto.setId(entidad.getId());
        dto.setValorMinimoFinanciar(entidad.getValorMinimoFinanciar());
        dto.setValorMaximoFinanciar(entidad.getValorMaximoFinanciar());
        dto.setCantidadMinimaCoutas(entidad.getCantidadMinimaCoutas());
        dto.setCantidadMaximaCouta(entidad.getCantidadMaximaCouta());
        return dto;
    }

    public static DetalleCantidadCuotaDTO toLevel1DTO(DetalleCantidadCuota entidad) {
        DetalleCantidadCuotaDTO dto = toLevel0DTO(entidad);
        if (entidad.getVariableCondicionFinan() != null)
            dto.setVariableCondicionFinan(VariableCondicionFinanHelper.toLevel0DTO(entidad.getVariableCondicionFinan()));
        if (entidad.getConfiguracionFinanciacion() != null)
            dto.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper.toLevel0DTO(entidad
                    .getConfiguracionFinanciacion()));

        return dto;
    }

    public static List<DetalleCantidadCuotaDTO> toListLevel0DTO(List<DetalleCantidadCuota> listEntidad) {
        List<DetalleCantidadCuotaDTO> listDto = new ArrayList<DetalleCantidadCuotaDTO>();
        for (DetalleCantidadCuota entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleCantidadCuotaDTO> toListLevel1DTO(List<DetalleCantidadCuota> listEntidad) {
        List<DetalleCantidadCuotaDTO> listDto = new ArrayList<DetalleCantidadCuotaDTO>();
        for (DetalleCantidadCuota entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleCantidadCuota toLevel0Entity(DetalleCantidadCuotaDTO dto, DetalleCantidadCuota entidad) {
        if (null == entidad) {
            entidad = new DetalleCantidadCuota();
        }
        entidad.setId(dto.getId());
        entidad.setValorMinimoFinanciar(dto.getValorMinimoFinanciar());
        entidad.setValorMaximoFinanciar(dto.getValorMaximoFinanciar());

        entidad.setCantidadMinimaCoutas(dto.getCantidadMinimaCoutas());
        entidad.setCantidadMaximaCouta(dto.getCantidadMaximaCouta());

        return entidad;
    }

    public static DetalleCantidadCuota toLevel1Entity(DetalleCantidadCuotaDTO dto, DetalleCantidadCuota entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getVariableCondicionFinan() != null) {
            entidad.setVariableCondicionFinan(new VariableCondicionFinan());
            entidad.getVariableCondicionFinan().setId(dto.getVariableCondicionFinan().getId());
        }
        if (dto.getConfiguracionFinanciacion() != null) {
            entidad.setConfiguracionFinanciacion(new ConfiguracionFinanciacion());
            entidad.getConfiguracionFinanciacion().setId(dto.getConfiguracionFinanciacion().getId());
        }

        return entidad;
    }

    public static List<DetalleCantidadCuota> toListLevel0Entity(List<DetalleCantidadCuotaDTO> listDto) {
        List<DetalleCantidadCuota> listEntidad = new ArrayList<DetalleCantidadCuota>();
        for (DetalleCantidadCuotaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleCantidadCuota> toListLevel1Entity(List<DetalleCantidadCuotaDTO> listDto) {
        List<DetalleCantidadCuota> listEntidad = new ArrayList<DetalleCantidadCuota>();
        for (DetalleCantidadCuotaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
